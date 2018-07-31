package com.innovati.felipehernandez.invenenvios.activitys;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.PedidosAdapter;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwPedidos;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.Pedidos_I;
import com.innovati.felipehernandez.invenenvios.database.Pedidos_IDao;
import com.innovati.felipehernandez.invenenvios.fragments.DetallePedidoFragment;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class EntregasActivity extends AppCompatActivity
{
    private ListView EntregasListView;

    private PedidosAdapter adaptador;
    private VwPedidos result[];
    private MetodosInternos metodosInternos = new MetodosInternos(this);
    private DaoSession daoSession;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas);

        this.setTitle(R.string.tituloEntregas);
        inicializacion();

        daoSession = ((MyApp) getApplication()).getDaoSession();

        EntregasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                DetallePedidoFragment datosPedidoFragment = new DetallePedidoFragment();
                Bundle args;
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_regresar);
                args = new Bundle();
                args.putString("pedido", result[position].getIdPedido());
                args.putBoolean("bandera",false);
                datosPedidoFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.EntregasRelativeLayout, datosPedidoFragment).addToBackStack(null).commit();

            }
        });

        EntregasListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                Snackbar.make(view, "¿Desea cambiar el estado a 'Entregado' sobre el pedido con folio "+ result[position].getFolio() +"?", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Si", new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        result[position].setEstatus((short) 4); //no sé que se manda aquí
                        ActualizarPedido a = new ActualizarPedido();
                        a.execute(result);

                    }
                }).show();

                return false;
            }
        });

        cargarDatos();
    }

    private void inicializacion()
    {
        EntregasListView = (ListView)findViewById(R.id.EntregasListView);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Cargando...");
        dialog.setCancelable(false);
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

    public static VwPedidosDao getVwPedidosDao()
    {
        return VwPedidosDaoFactory.create();
    }


    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
    }
    public void cargarDatos()
    {
        dialog.show();
        if(metodosInternos.conexionRed())
        {
            VwPedidosDao _dao = getPedidosDao();
            ConsultaPedidos conP = new ConsultaPedidos();
            conP.execute(_dao);
        }
        else //código para buscar en la bd interna
            try
            {
                internaBD();
            }catch (Exception e){
            metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
        }
        dialog.hide();
    }

    private void internaBD()
    {
        Pedidos_IDao pedidos_iDao = daoSession.getPedidos_IDao();
        QueryBuilder<Pedidos_I> qb = pedidos_iDao.queryBuilder();

        List<Pedidos_I> pedidos = qb.list();
        result = new VwPedidos[pedidos.size()];

        for(int x=0; x<pedidos.size(); x++)
        {
            VwPedidos objetoPedidos = new VwPedidos();

            objetoPedidos.setIdPedido(pedidos.get(x).getIdPedido());
            objetoPedidos.setIdUsuario(pedidos.get(x).getIdUsuario());
            //objetoPedidos.setNombre(pedidos.get(x).getNombre());
            objetoPedidos.setFolio(pedidos.get(x).getFolio());
            objetoPedidos.setClaveCliente(pedidos.get(x).getClaveCliente());
            objetoPedidos.setFecha(pedidos.get(x).getFecha());
            objetoPedidos.setEstatus(pedidos.get(x).getEstatus());
            objetoPedidos.setSubtotal(pedidos.get(x).getSubtotal());
            objetoPedidos.setTotal(pedidos.get(x).getTotal());
            objetoPedidos.setIva(pedidos.get(x).getIva());
            result[x] = objetoPedidos;
        }

        adaptador = new PedidosAdapter(EntregasActivity.this,  R.layout.listview_pedidos, result, 2);
        EntregasListView.setAdapter(adaptador);
    }

    private class ConsultaPedidos extends AsyncTask<VwPedidosDao,Void, VwPedidos[]>
    {
        @Override
        protected VwPedidos[] doInBackground(VwPedidosDao... pedidosDaos)
        {
            try
            {
                result = pedidosDaos[0].findAll();
            }
            catch (Exception e){

            }

            return result;
        }

        @Override
        protected void onPostExecute(VwPedidos[] pedidos) {
            super.onPostExecute(pedidos);

            adaptador = new PedidosAdapter(EntregasActivity.this,  R.layout.listview_pedidos, result, 2);
            EntregasListView.setAdapter(adaptador);
        }
    }

    private static class ActualizarPedido extends AsyncTask<Pedidos, Void, Void>
    {
        @Override
        protected Void doInBackground(Pedidos... pedidos)
        {
            PedidosDao _dao = getPedidosDao();
            try
            {
                String parametros[] = new String[]{pedidos[0].getIdPedido()};
                _dao.update(pedidos[0], "IdPedido = ?", parametros);
            }
            catch (Exception e)
            {

            }
            return null;
        }
    }
}
