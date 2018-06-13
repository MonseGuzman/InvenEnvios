package com.innovati.felipehernandez.invenenvios.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ClientesAdaptador;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;
import com.innovati.felipehernandez.invenenvios.fragments.ClienteFragment;

public class ClientesActivity extends AppCompatActivity
{
    private ListView clienteListView_C;
    private EditText buscarClienteEditText_C;
    private ClientesAdaptador adaptador;
    VwClientes result[];
    MetodosInternos metodosInternos = new MetodosInternos(this);

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        inicializacion();
        this.setTitle(R.string.tituloClientes);

        clienteListView_C.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ClienteFragment clienteFragment = new ClienteFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.ClienteConstraintLayout, clienteFragment).addToBackStack(null).commit();
            }
        });
        //menú de contexto
        registerForContextMenu(clienteListView_C);
    }


    private void inicializacion()
    {
        clienteListView_C = (ListView)findViewById(R.id.clienteListView);
        buscarClienteEditText_C = (EditText)findViewById(R.id.buscarClienteEditText_C);
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
            /*case  R.id.edit_note:
                ShowAlertForEditBoard("Editar", "Cambiar el nombre de la nota", notes.get(info.position));
                return true;*/
            default:
                return super.onContextItemSelected(item);
        }
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
        String nombre = buscarClienteEditText_C.getText().toString();
        if(TextUtils.isEmpty(nombre))
        {
            //sin filtro = todos
            if(metodosInternos.conexionRed())
            {
                try
                {
                    VwClientesDao _dao = getVwClientesDao();
                    result = _dao.findAll();
                    adaptador = new ClientesAdaptador(this,  R.layout.listview_cliente, result);
                    clienteListView_C.setAdapter(adaptador);

                }
                catch(Exception e)
                {
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //bd interna
            }
        }
        else
        {
            //todos los que se parecen con el where
            if(metodosInternos.conexionRed())
            {
                try
                {
                    nombre = "%" + nombre;
                    nombre += "%";
                    VwClientesDao _dao = getVwClientesDao();
                    result = _dao.findWhereNombreEquals(nombre);
                    adaptador = new ClientesAdaptador(this,  R.layout.listview_cliente, result);
                    clienteListView_C.setAdapter(adaptador);
                }
                catch(Exception e)
                {
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //bd interna
            }
        }
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
                }
                break;
        }
    }
}
