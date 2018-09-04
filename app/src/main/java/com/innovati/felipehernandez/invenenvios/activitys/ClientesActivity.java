package com.innovati.felipehernandez.invenenvios.activitys;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.API.DelayedProgressDialog;
import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ClientesAdaptador;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.VwClientes_I;
import com.innovati.felipehernandez.invenenvios.database.VwClientes_IDao;
import com.innovati.felipehernandez.invenenvios.fragments.ClienteFragment;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class ClientesActivity extends AppCompatActivity
{
    private static ListView datitosListView;
    private EditText buscarEditText;
    private Button BuscarButton;

    private Bundle args;
    private ClientesAdaptador adaptador;
    VwClientes result[];
    MetodosInternos metodosInternos = new MetodosInternos(this);
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private int posicion;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        inicializacion();

        this.setTitle(R.string.tituloClientes);
        buscarEditText.setHint(R.string.seleccionarCliente);
        daoSession = ((MyApp) getApplication()).getDaoSession();

        datitosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ClienteFragment clienteFragment = new ClienteFragment();
                args = new Bundle();

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_regresar);

                args.putString("nombre", result[position].getNombre());
                args.putString("rfc", result[position].getRfc());
                args.putString("calle", result[position].getCalle());
                args.putString("numeroExterior", result[position].getNumeroExterior());
                args.putString("numeroInterior", result[position].getNumeroInterior());
                args.putString("colonia", result[position].getColonia());
                args.putString("telefono", result[position].getTelefono());
                clienteFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.ClienteConstraintLayout, clienteFragment).addToBackStack(null).commit();
            }
        });

        BuscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtar(v);
            }
        });
        //menú de contexto
        registerForContextMenu(datitosListView);
    }

    private void inicializacion()
    {
        datitosListView = (ListView)findViewById(R.id.datitosListView);
        buscarEditText = (EditText)findViewById(R.id.buscarEditText);
        BuscarButton = (Button)findViewById(R.id.BuscarButton);
    }

    //menú de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_listview_cliente ,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public static VwClientesDao getVwClientesDao()
    {
        return VwClientesDaoFactory.create();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        posicion = info.position;

        switch (item.getItemId()) {
            case R.id.Llamar_C:
                if(!checkPermission())
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                else
                    telefono(posicion);

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_home:
                finish();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {//cantidad de fragmentos que estan actualmente apilados.
        if(getSupportFragmentManager().getBackStackEntryCount() != 0)
        {
            //regresa
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        else
            finish();
    }

    public void telefono(int position)
    {
        String telefono = result[position].getTelefono();
        if(!TextUtils.isEmpty(telefono))
        {
            telefono.replace("-", "");
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + telefono));

            if (intent.resolveActivity(getPackageManager()) != null) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        }

    }

    public void filtar(View v)
    {
        String nombre = buscarEditText.getText().toString();

        if(metodosInternos.conexionRed())
        {
            //sin filtro = todos
            if(TextUtils.isEmpty(nombre))
            {
                try
                {
                    VwClientesDao _dao = getVwClientesDao();
                    ConsultaClientes c = new ConsultaClientes(nombre);
                    c.execute(_dao);

                    hideKeyboardFrom(this, buscarEditText);
                }
                catch(Exception e)
                {
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                try
                {
                    nombre = "%" + nombre;
                    nombre += "%";
                    VwClientesDao _dao = getVwClientesDao();
                    ConsultaClientes c = new ConsultaClientes(nombre);
                    c.execute(_dao);

                    hideKeyboardFrom(this, buscarEditText);
                }
                catch(Exception e)
                {
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
        else
            try
            {
                internaBD();
            } catch (Exception e)
            {
                metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
                e.printStackTrace();
            }
    }

    private void internaBD()
    {
        String nombre = buscarEditText.getText().toString();
        VwClientes_IDao vwClientes_iDao = daoSession.getVwClientes_IDao();
        List<VwClientes_I> clientes;

        //si esta vacio
        if(TextUtils.isEmpty(nombre))
        {
            QueryBuilder<VwClientes_I> qb = vwClientes_iDao.queryBuilder();
            clientes = qb.list();
            result = new VwClientes[clientes.size()];
        }
        else
        {
            nombre = "%" + nombre;
            nombre += "%";

            QueryBuilder<VwClientes_I> qb = vwClientes_iDao.queryBuilder();
            qb.where(VwClientes_IDao.Properties.Nombre.like(nombre));

            clientes = qb.list();
            result = new VwClientes[clientes.size()];
        }

        for(int x=0; x<clientes.size(); x++)
        {
            VwClientes objetoCliente = new VwClientes();

            objetoCliente.setClave(clientes.get(x).getClave());
            objetoCliente.setNombre(clientes.get(x).getNombre());
            objetoCliente.setRfc(clientes.get(x).getRfc());
            objetoCliente.setCalle(clientes.get(x).getCalle());
            objetoCliente.setNumeroExterior(clientes.get(x).getNumeroExterior());
            objetoCliente.setNumeroInterior(clientes.get(x).getNumeroInterior());
            objetoCliente.setColonia(clientes.get(x).getColonia());
            objetoCliente.setTelefono(clientes.get(x).getTelefono());

            result[x] = objetoCliente;
        }

        cargarDatos(result);
    }

    private void cargarDatos(VwClientes[] result)
    {
        adaptador = new ClientesAdaptador(this,  R.layout.listview_cliente, result);
        datitosListView.setAdapter(adaptador);
    }

    public static void bloqueo(){
        datitosListView.setVisibility(View.VISIBLE);
    }

    public boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //Aquí lo que se hace si aceptan el permiso
                    telefono(posicion);
                }
                else
                {
                    //Aquí lo que se hace si no lo aceptan
                    metodosInternos.Alerta(R.string.error, R.string.noLlamar);
                }
                break;
        }
    }

    public class ConsultaClientes extends AsyncTask<VwClientesDao, VwClientes[], VwClientes[]>
    {
        String nombre;
        DelayedProgressDialog progressDialog = new DelayedProgressDialog();

        public ConsultaClientes(String nombre)
        {
            this.nombre = nombre;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            progressDialog.setCancelable(false);
            progressDialog.show(getSupportFragmentManager(), "tag");
        }

        @Override
        protected VwClientes[] doInBackground(VwClientesDao... vwClientesDaos)
        {
            try
            {
                if(nombre.equals(""))
                    result = vwClientesDaos[0].findAll();
                else
                    result = vwClientesDaos[0].findWhereNombreEquals(nombre);

            }
            catch (Exception e){

            }
            return result;
        }

        @Override
        protected void onPostExecute(VwClientes[] vwArticulos)
        {
            super.onPostExecute(vwArticulos);
            cargarDatos(result);

            progressDialog.cancel(); //o .cancel();
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            progressDialog.cancel();
        }
    }

    //Este método sirve para bajar el teclado :v
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
