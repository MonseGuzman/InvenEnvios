package com.innovati.felipehernandez.invenenvios.activitys;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.adapters.ListaAbastecimientoAdapter;
import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ListaArticulosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.PedidosAdapter;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.DetallesPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwAbastecimientoDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwAbastecimiento;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwAbastecimientoDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.Pedidos_I;
import com.innovati.felipehernandez.invenenvios.database.Pedidos_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwAbastecimientos_I;
import com.innovati.felipehernandez.invenenvios.database.VwAbastecimientos_IDao;
import com.innovati.felipehernandez.invenenvios.fragments.DetallePedidoFragment;
import com.innovati.felipehernandez.invenenvios.prueba2;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AbastecimientosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView AbastecimientoListView;
    private RecyclerView AbastecimientoRecycleView;
    private ExpandableListView AbastecimientoExpandableListView;

    private ListaArticulosAdapter listaArticulosAdapter;
    private VwAbastecimiento lista[];
    private MetodosInternos metodosInternos = new MetodosInternos(this);
    private DaoSession daoSession;
    private ProgressDialog dialog;
    private PedidosAdapter adaptador;
    Pedidos result[];
    int tipo;

    //borrar
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimientos);

        inicializar();

        tipo = getIntent().getExtras().getInt("Tipo", 0);
        this.setTitle(R.string.tituloAbastecimiento);
        daoSession = ((MyApp) getApplication()).getDaoSession();

        AbastecimientoListView.setOnItemClickListener(this);

        /*expandableListDetail = prueba.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ListaAbastecimientoAdapter(this, lista, expandableListDetail);
        AbastecimientoExpandableListView.setAdapter(expandableListAdapter);*/

        //AbastecimientoExpandableListView.setOnGroupExpandListener -- este sirve para cuando expanda la lista
        //AbastecimientoExpandableListView.setOnGroupCollapseListener -- este sirve para cuando se collapsa la lista
        //AbastecimientoExpandableListView.setOnChildClickListener --este sirve para el clic de los items
        cargarDatos(tipo);
    }

    private void inicializar()
    {
        AbastecimientoListView = (ListView)findViewById(R.id.AbastecimientoListView);
        AbastecimientoRecycleView = (RecyclerView) findViewById(R.id.AbastecimientoRecycleView);
        AbastecimientoExpandableListView = (ExpandableListView) findViewById(R.id.AbastecimientoExpandableListView);

        dialog=new ProgressDialog(this);
        dialog.setMessage("Cargando...");
        dialog.setCancelable(false);
    }

    private void cargarDatos(int item)
    {
        dialog.show();
        if(metodosInternos.conexionRed())
        {
            switch (item)
            {
                case 1:
                    PedidosDao _daoP = getPedidosDao();
                    ConsultaPedidos conP = new ConsultaPedidos();
                    conP.execute(_daoP);

                    break;
                case 2:
                    /*VwAbastecimientoDao _daoA = getVwAbastecimientoDao();
                    ConsultaAbastecimientos conA = new ConsultaAbastecimientos();
                    conA.execute(_daoA);*/
                    break;
            }
        }
        else //bd interna
            try
            {
                internaBD(item);
            }catch (Exception e) {
                metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
            }
            dialog.hide();
    }

    private void internaBD(int item)
    {
        switch (item)
        {
            case 1:
                Pedidos_IDao pedidos_iDao = daoSession.getPedidos_IDao();
                QueryBuilder<Pedidos_I> qb = pedidos_iDao.queryBuilder();

                List<Pedidos_I> pedidos = qb.list();
                result = new Pedidos[pedidos.size()];

                for(int x=0; x<pedidos.size(); x++)
                {
                    Pedidos objetoPedidos = new Pedidos();

                    objetoPedidos.setIdPedido(pedidos.get(x).getIdPedido());
                    objetoPedidos.setIdUsuario(pedidos.get(x).getIdUsuario());
                    objetoPedidos.setFolio(pedidos.get(x).getFolio());
                    objetoPedidos.setClaveCliente(pedidos.get(x).getClaveCliente());
                    objetoPedidos.setFecha(pedidos.get(x).getFecha());
                    objetoPedidos.setEstatus(pedidos.get(x).getEstatus());
                    objetoPedidos.setSubtotal(pedidos.get(x).getSubtotal());
                    objetoPedidos.setTotal(pedidos.get(x).getTotal());
                    objetoPedidos.setIva(pedidos.get(x).getIva());
                    objetoPedidos.setObservaciones(pedidos.get(x).getObservaciones());
                    objetoPedidos.setUltimaFechaActualizacion(pedidos.get(x).getUltimaFechaActualizacion());
                    objetoPedidos.setUltimoUsuarioActualizacion(pedidos.get(x).getUltimoUsuarioActualizacion());

                    result[x] = objetoPedidos;
                }

                adaptador = new PedidosAdapter(AbastecimientosActivity.this,  R.layout.listview_pedidos, result, 1);
                AbastecimientoListView.setAdapter(adaptador);

                break;
            case 2:
                /*VwAbastecimientos_IDao vwAbastecimientosIDao = daoSession.getVwAbastecimientos_IDao();
                QueryBuilder<VwAbastecimientos_I> qb2 = vwAbastecimientosIDao.queryBuilder();

                List<VwAbastecimientos_I> abastecimientos = qb2.list();
                lista = new VwAbastecimiento[abastecimientos.size()];

                for(int x=0; x<abastecimientos.size(); x++)
                {
                    VwAbastecimiento objetoAbastecimiento = new VwAbastecimiento();

                    objetoAbastecimiento.setNombre(abastecimientos.get(x).getNombre());
                    objetoAbastecimiento.setTotal(abastecimientos.get(x).getCantidad());
                    objetoAbastecimiento.setUnidadPrimaria(abastecimientos.get(x).getUnidadPrimaria());

                    lista[x] = objetoAbastecimiento;
                }

                listaArticulosAdapter = new ListaArticulosAdapter(AbastecimientosActivity.this, animationUp, animationDown, lista);
                AbastecimientoRecycleView.setAdapter(listaArticulosAdapter);*/

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

    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
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
        detallePedidoFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.abastecimientoConsulta, detallePedidoFragment).addToBackStack(null).commit();
    }

    private class ConsultaPedidos extends AsyncTask<PedidosDao,Void, Pedidos[]>
    {

        @Override
        protected Pedidos[] doInBackground(PedidosDao... pedidosDaos) {
            try
            {
                result = pedidosDaos[0].findAll();
            }
            catch (Exception e){

            }

            return result;
        }

        @Override
        protected void onPostExecute(Pedidos[] pedidos) {
            super.onPostExecute(pedidos);

            adaptador = new PedidosAdapter(AbastecimientosActivity.this,  R.layout.listview_pedidos, result, 1);
            AbastecimientoListView.setAdapter(adaptador);
        }
    }

    private class ConsultaAbastecimientos extends AsyncTask<VwAbastecimientoDao, Void, VwAbastecimiento[]>
    {

        @Override
        protected VwAbastecimiento[] doInBackground(VwAbastecimientoDao... vwAbastecimientoDaos)
        {
            try
            {
                //lista = vwAbastecimientoDaos[0].findAll();
            }
            catch (Exception e){

            }

            return lista;
        }

        @Override
        protected void onPostExecute(VwAbastecimiento[] vwAbastecimientos)
        {
            super.onPostExecute(vwAbastecimientos);
            /*listaArticulosAdapter = new ListaArticulosAdapter(AbastecimientosActivity.this, animationUp, animationDown, lista);
            AbastecimientoRecycleView.setAdapter(listaArticulosAdapter);*/
        }
    }

    public static DetallesPedidosDao getDetallesPedidosDao()
    {
        return DetallesPedidosDaoFactory.create();
    }
}
