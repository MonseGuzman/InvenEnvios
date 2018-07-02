package com.innovati.felipehernandez.invenenvios.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.PedidosAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;

public class PedidosActivity extends AppCompatActivity
{
    private ListView PedidosListView;
    private PedidosAdapter adaptador;

    Pedidos result[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        inicializacion();
        this.setTitle(R.string.tituloEntregas);

        cargarDatos();
    }

    private void cargarDatos()
    {
        adaptador = new PedidosAdapter(this,  R.layout.listview_pedidos, result);
        PedidosListView.setAdapter(adaptador);
    }

    private void inicializacion()
    {
        PedidosListView = (ListView)findViewById(R.id.PedidosListView);
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

            //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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
            /*case android.R.id.home:
                onBackPressed();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
