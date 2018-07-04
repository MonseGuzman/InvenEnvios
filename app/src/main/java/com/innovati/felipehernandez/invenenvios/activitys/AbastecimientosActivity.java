package com.innovati.felipehernandez.invenenvios.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.PedidoActivity;
import com.innovati.felipehernandez.invenenvios.adapters.PedidosAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.fragments.DatosPedidoFragment;
import com.innovati.felipehernandez.invenenvios.fragments.DetallePedidoFragment;

public class AbastecimientosActivity extends AppCompatActivity {

    private ListView AbastecimientoListView;

    MetodosInternos metodosInternos = new MetodosInternos(this);
    private PedidosAdapter adaptador;
    Pedidos result[];
    int tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimientos);

        inicializar();

        tipo = getIntent().getExtras().getInt("Tipo", 0); //CONSULTA - 1 ENTREGA 2

        switch (tipo)
        {
            case 1:
                this.setTitle(R.string.tituloAbastecimiento);
                break;
            case 2:
                this.setTitle(R.string.tituloEntregas);
                break;
        }

        AbastecimientoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                DetallePedidoFragment datosPedidoFragment = new DetallePedidoFragment();
                Bundle args;
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_regresar);
                args = new Bundle();
                args.putString("pedido", result[position].getIdPedido());
                datosPedidoFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.abastecimientoConsulta, datosPedidoFragment).addToBackStack(null).commit();
            }
        });

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
                adaptador = new PedidosAdapter(this,  R.layout.listview_pedidos, result, tipo);
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
            case android.R.id.home:
                onBackPressed();
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
            //getSupportFragmentManager().popBackStack();
        }
        else
            finish();
    }

    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
    }
}
