package com.innovati.felipehernandez.invenenvios.activitys;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.innovati.felipehernandez.invenenvios.API.DelayedProgressDialog;
import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ListaAbastecimientoAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.ListaArticulosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.PedidosAdapter;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwAbastecimientoDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwDetallePedidoDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwAbastecimiento;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwDetallePedido;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwPedidos;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwAbastecimientoDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwDetallePedidoDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.VwAbastecimientos_I;
import com.innovati.felipehernandez.invenenvios.database.VwAbastecimientos_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwDetallePedido_I;
import com.innovati.felipehernandez.invenenvios.database.VwDetallePedido_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwPedidos_I;
import com.innovati.felipehernandez.invenenvios.database.VwPedidos_IDao;
import com.innovati.felipehernandez.invenenvios.fragments.DetallePedidoFragment;
import com.innovati.felipehernandez.invenenvios.pojos.ExpandableListDataPump;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AbastecimientosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private ListView AbastecimientoListView;
    private ExpandableListView AbastecimientoExpandableListView;

    private MetodosInternos metodosInternos = new MetodosInternos(this);
    private DaoSession daoSession;
    private PedidosAdapter adaptador;
    VwPedidos result[];
    int tipo;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Float>> expandableListDetail;
    DelayedProgressDialog progressDialog = new DelayedProgressDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimientos);

        inicializar();

        tipo = getIntent().getExtras().getInt("Tipo", 0);
        this.setTitle(R.string.tituloAbastecimiento);
        daoSession = ((MyApp) getApplication()).getDaoSession();

        AbastecimientoListView.setOnItemClickListener(this);

        cargarDatos(tipo);
    }

    private void inicializar()
    {
        AbastecimientoListView = (ListView)findViewById(R.id.AbastecimientoListView);
        AbastecimientoExpandableListView = (ExpandableListView) findViewById(R.id.AbastecimientoExpandableListView);
    }

    private void cargarDatos(int item)
    {
        if(metodosInternos.conexionRed())
        {
            switch (item)
            {
                case 1:
                    VwPedidosDao _daoP = getPedidosDao();
                    ConsultaPedidos conP = new ConsultaPedidos();
                    conP.execute(_daoP);

                    break;
                case 2:
                    VwAbastecimientoDao _dao = getVwAbastecimientoDao();
                    LlenarAdaptador c = new LlenarAdaptador();
                    c.execute(_dao);
                    break;
            }
        }
        else
            try
            {
                internaBD(item);
            }catch (Exception e) {
                metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
            }
    }

    private void internaBD(int item)
    {
        switch (item)
        {
            case 1:
                VwPedidos_IDao pedidos_iDao = daoSession.getVwPedidos_IDao();
                QueryBuilder<VwPedidos_I> qbPedido = pedidos_iDao.queryBuilder();
                qbPedido.where(VwPedidos_IDao.Properties.Estatus.eq(1));
                List<VwPedidos_I> pedidos = qbPedido.orderAsc(VwPedidos_IDao.Properties.Folio).list();
                result = new VwPedidos[pedidos.size()];

                for(int x=0; x<pedidos.size(); x++)
                {
                    VwPedidos objetoPedidos = new VwPedidos();

                    objetoPedidos.setIdPedido(pedidos.get(x).getIdPedido());
                    objetoPedidos.setNombre(pedidos.get(x).getNombre());
                    objetoPedidos.setIdUsuario(pedidos.get(x).getIdUsuario());
                    objetoPedidos.setFolio(pedidos.get(x).getFolio());
                    objetoPedidos.setClaveCliente(pedidos.get(x).getClaveCliente());
                    objetoPedidos.setFecha(pedidos.get(x).getFecha());
                    objetoPedidos.setEstatus(pedidos.get(x).getEstatus());
                    objetoPedidos.setSubtotal(pedidos.get(x).getSubtotal());
                    objetoPedidos.setTotal(pedidos.get(x).getTotal());
                    objetoPedidos.setIva(pedidos.get(x).getIva());

                    result[x] = objetoPedidos;
                }

                adaptador = new PedidosAdapter(AbastecimientosActivity.this,  R.layout.listview_pedidos, result);
                AbastecimientoListView.setAdapter(adaptador);

                break;
            case 2:
                VwAbastecimientos_IDao vwAbastecimientos_iDao = daoSession.getVwAbastecimientos_IDao();
                VwDetallePedido_IDao vwDetallePedido_iDao = daoSession.getVwDetallePedido_IDao();
                List<VwAbastecimientos_I> abastecimientos_is = vwAbastecimientos_iDao.loadAll();

                List<String> list = new ArrayList<>();
                List<Object> objectList = new ArrayList<>();
                for(VwAbastecimientos_I abastecimiento: abastecimientos_is)
                {
                    String nombre = abastecimiento.getNombre() + " " + abastecimiento.getCantidad() + abastecimiento.getUnidadPrimaria();
                    QueryBuilder<VwDetallePedido_I> qb = vwDetallePedido_iDao.queryBuilder();

                    qb.where(VwDetallePedido_IDao.Properties.Nombre.eq(abastecimiento.getNombre()), VwDetallePedido_IDao.Properties.Surtido.eq(1));

                    List<VwDetallePedido_I> detalles = qb.list();

                    List<Float> numeros = new ArrayList<>();
                    for(VwDetallePedido_I cantidades: detalles)
                    {
                        numeros.add((float)cantidades.getCantidad());
                    }
                    objectList.add(numeros);
                    list.add(nombre);
                }
                ExpandableListDataPump e = new ExpandableListDataPump(list,objectList);
                expandableListDetail = e.getData();
                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                expandableListAdapter = new ListaAbastecimientoAdapter(AbastecimientosActivity.this, list, expandableListDetail);
                AbastecimientoExpandableListView.setAdapter(expandableListAdapter);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_abastecimiento, menu);
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
            case R.id.menu_cambia:
                progressDialog.setCancelable(false);
                progressDialog.show(getSupportFragmentManager(), "tag");

                if(AbastecimientoExpandableListView.getVisibility() == View.VISIBLE)
                {
                    AbastecimientoExpandableListView.setVisibility(View.INVISIBLE);
                    AbastecimientoListView.setVisibility(View.VISIBLE);
                    tipo = 1;
                }
                else
                {
                    AbastecimientoExpandableListView.setVisibility(View.VISIBLE);
                    AbastecimientoListView.setVisibility(View.INVISIBLE);
                    tipo = 2;
                }

                cargarDatos(tipo);
                progressDialog.cancel();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        //cantidad de fragmentos que estan actualmente apilados.
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

    public static VwAbastecimientoDao getVwAbastecimientoDao()
    {
        return VwAbastecimientoDaoFactory.create();
    }

    public static VwPedidosDao getPedidosDao()
    {
        return VwPedidosDaoFactory.create();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        DetallePedidoFragment detallePedidoFragment = new DetallePedidoFragment();
        Bundle args;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_regresar);
        args = new Bundle();
        args.putString("pedido", result[position].getIdPedido());
        args.putBoolean("bandera",true);
        args.putString("folio", String.valueOf(result[position].getFolio()));
        args.putString("fecha", String.valueOf(result[position].getFecha()));
        detallePedidoFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.abastecimientoConsulta, detallePedidoFragment).addToBackStack(null).commit();
    }

    private class ConsultaPedidos extends AsyncTask<VwPedidosDao,Void, VwPedidos[]>
    {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            progressDialog.setCancelable(false);
            progressDialog.show(getSupportFragmentManager(), "tag");
        }

        @Override
        protected VwPedidos[] doInBackground(VwPedidosDao... pedidosDaos) {
            try
            {
                result = pedidosDaos[0].findWhereEstatusEquals((short) 1);
            }
            catch (Exception e){

            }

            return result;
        }

        @Override
        protected void onPostExecute(VwPedidos[] pedidos) {
            super.onPostExecute(pedidos);
            progressDialog.cancel();

            adaptador = new PedidosAdapter(AbastecimientosActivity.this,  R.layout.listview_pedidos, result);
            AbastecimientoListView.setAdapter(adaptador);
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            progressDialog.cancel();
        }
    }

    public static VwDetallePedidoDao getVwDetallesPedidosDao()
    {
        return VwDetallePedidoDaoFactory.create();
    }

    private class LlenarAdaptador extends AsyncTask<VwAbastecimientoDao, Void, List<String>>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            progressDialog.setCancelable(false);
            progressDialog.show(getSupportFragmentManager(), "tag");
        }

        @Override
        protected List<String> doInBackground(VwAbastecimientoDao... vwAbastecimientoDaos)
        {
            try
            {
                VwDetallePedidoDao _daoDetalle = getVwDetallesPedidosDao();

                VwAbastecimiento[] abastecimientos = vwAbastecimientoDaos[0].findAll();
                List<String> list = new ArrayList<>();
                List<Object> objectList = new ArrayList<>();
                for (VwAbastecimiento abastecimiento: abastecimientos)
                {

                    String nombre = abastecimiento.getNombre() + " " + abastecimiento.getTotal() + abastecimiento.getUnidadPrimaria();
                    VwDetallePedido[] detalles = _daoDetalle.findByDynamicSelect("SELECT NULL, NULL, NULL, NULL, Cantidad, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL FROM VwDetallePedido WHERE Surtido = ? AND Nombre = ?",new Object[]{1,abastecimiento.getNombre()});

                    List<Float> numeros = new ArrayList<>();
                    for(VwDetallePedido cantidades: detalles)
                    {
                        numeros.add((float)cantidades.getCantidad());
                    }
                    objectList.add(numeros);
                    list.add(nombre);
                }
                ExpandableListDataPump e = new ExpandableListDataPump(list,objectList);
                expandableListDetail = e.getData();
                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

                return list;
            }
            catch (Exception e)
            {

            }
            return null;
        }

        @Override
        protected void onPostExecute(List<String> strings)
        {
            super.onPostExecute(strings);
            progressDialog.cancel();

            expandableListAdapter = new ListaAbastecimientoAdapter(AbastecimientosActivity.this, strings, expandableListDetail);
            AbastecimientoExpandableListView.setAdapter(expandableListAdapter);
            //AbastecimientoExpandableListView.setOnGroupExpandListener -- este sirve para cuando expanda la lista
            //AbastecimientoExpandableListView.setOnGroupCollapseListener -- este sirve para cuando se collapsa la lista
            //AbastecimientoExpandableListView.setOnChildClickListener --este sirve para el clic de los items
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            progressDialog.cancel();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargarDatos(tipo);
    }
}
