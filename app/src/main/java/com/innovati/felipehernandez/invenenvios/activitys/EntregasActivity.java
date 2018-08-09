package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.API.DelayedProgressDialog;
import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.EntregasRecycleView;
import com.innovati.felipehernandez.invenenvios.adapters.RecycleViewOnItemClickListener;
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

import java.util.Date;
import java.util.List;

public class EntregasActivity extends AppCompatActivity
{
    private VwPedidos result[];
    private MetodosInternos metodosInternos = new MetodosInternos(this);
    private DaoSession daoSession;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas);

        this.setTitle(R.string.tituloEntregas);
        inicializacion();

        daoSession = ((MyApp) getApplication()).getDaoSession();

        cargarDatos();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int  position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.RIGHT){
                    SharedPreferences preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

                    Pedidos pedidos = new Pedidos();

                    if(result[position].getEstatus() == 3){
                        result[position].setEstatus((short) 4);
                    }else{
                        result[position].setEstatus((short) 3);
                    }
                    pedidos.setIdPedido(result[position].getIdPedido());
                    pedidos.setIdUsuario(result[position].getIdUsuario());
                    pedidos.setFolio(result[position].getFolio());
                    pedidos.setClaveCliente(result[position].getClaveCliente());
                    pedidos.setFecha(result[position].getFecha());
                    pedidos.setEstatus(result[position].getEstatus());
                    pedidos.setSubtotal(result[position].getSubtotal());
                    pedidos.setIva(result[position].getIva());
                    pedidos.setTotal(result[position].getTotal());
                    pedidos.setUltimaFechaActualizacion( new Date());
                    pedidos.setUltimoUsuarioActualizacion(preferences.getString("idUsuario", ""));
                    ActualizarPedido a = new ActualizarPedido();
                    a.execute(pedidos);

                }
                updateAdapter();
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void inicializacion()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewEntrega);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
    }

    private void updateAdapter(){
        recyclerView.setAdapter(new EntregasRecycleView(this, result , new RecycleViewOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
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
        }));

        if(result.length == 0)
            metodosInternos.Alerta(R.string.vacioTitulo, R.string.vacioDescripcion);
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
        if(metodosInternos.conexionRed())
        {
            VwPedidosDao _dao = getVwPedidosDao();
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
        updateAdapter();
    }

    private class ConsultaPedidos extends AsyncTask<VwPedidosDao,Void, VwPedidos[]>
    {
        DelayedProgressDialog progressDialog = new DelayedProgressDialog();

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            progressDialog.setCancelable(false);
            progressDialog.show(getSupportFragmentManager(), "tag");
        }

        @Override
        protected VwPedidos[] doInBackground(VwPedidosDao... pedidosDaos)
        {
            try
            {
                result = pedidosDaos[0].findWhereEstatusEquals((short)3);
            }
            catch (Exception e){

            }

            return result;
        }

        @Override
        protected void onPostExecute(VwPedidos[] pedidos) {
            super.onPostExecute(pedidos);

            progressDialog.cancel();
            updateAdapter();
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            progressDialog.cancel();
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
