package com.innovati.felipehernandez.invenenvios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.activitys.PedidoActivity;
import com.innovati.felipehernandez.invenenvios.adapters.PedidosAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;

public class AbastecimientosActivity extends AppCompatActivity {

    private ListView AbastecimientoListView;

    MetodosInternos metodosInternos = new MetodosInternos(this);
    private PedidosAdapter adaptador;
    Pedidos result[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimientos);

        inicializar();

        this.setTitle(R.string.tituloAbastecimiento);

        cargarDatos();
    }

    private void inicializar()
    {
        AbastecimientoListView = (ListView)findViewById(R.id.AbastecimientoListView);
    }

    private void cargarDatos()
    {
        if(metodosInternos.conexionRed())
        {
            try
            {
                PedidosDao _dao = getPedidosDao();
                result = _dao.findAll();
                adaptador = new PedidosAdapter(this,  R.layout.listview_pedidos, result);
                AbastecimientoListView.setAdapter(adaptador);
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

    @Override
    public void onBackPressed()
    {//cantidad de fragmentos que estan actualmente apilados.
        if(getSupportFragmentManager().getBackStackEntryCount() != 0)
        {
            //regresa
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();

        }
        else
            finish();
    }

    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
    }
}
