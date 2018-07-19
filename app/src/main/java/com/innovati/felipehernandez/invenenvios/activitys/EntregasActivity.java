package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.PedidosAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.fragments.DetallePedidoFragment;

public class EntregasActivity extends AppCompatActivity
{
    private ListView EntregasListView;
    private Button GuardarButton_E;

    private PedidosAdapter adaptador;
    private Pedidos result[];
    private MetodosInternos metodosInternos = new MetodosInternos(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas);

        this.setTitle(R.string.tituloEntregas);
        inicializacion();

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

                GuardarButton_E.setVisibility(View.INVISIBLE);
            }
        });

        EntregasListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                //codigo de Diego para actualizar
                Snackbar.make(view, "¿Desea cambiar el estado a 'Entregado' sobre el pedido con folio "+ result[position].getFolio() +"?", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Si", new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Pedidos pedidos = new Pedidos();
                        pedidos.setEstatus((short) 2); //no sé que se manda aquí
                        //método update
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
        GuardarButton_E = (Button)findViewById(R.id.GuardarButton_E);
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
            GuardarButton_E.setVisibility(View.VISIBLE);
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

    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
    }

    public void cargarDatos()
    {
        if(metodosInternos.conexionRed())
        {
            PedidosDao _dao = getPedidosDao();
            ConsultaPedidos conP = new ConsultaPedidos();
            conP.execute(_dao);
        }
        else
        {
            //código para buscar en la bd interna
            metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
        }
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

            adaptador = new PedidosAdapter(EntregasActivity.this,  R.layout.listview_pedidos, result, 2);
            EntregasListView.setAdapter(adaptador);
        }
    }
}
