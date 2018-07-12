package com.innovati.felipehernandez.invenenvios.activitys;

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
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ListaArticulosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.PedidosAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwAbastecimientoDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwAbastecimiento;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwAbastecimientoDaoFactory;
import com.innovati.felipehernandez.invenenvios.fragments.DetallePedidoFragment;

public class AbastecimientosActivity extends AppCompatActivity {

    private ListView AbastecimientoListView;
    private RecyclerView AbastecimientoRecycleView;
    private Animation animationUp, animationDown;

    private ListaArticulosAdapter listaArticulosAdapter;
    private VwAbastecimiento lista[];
    private MetodosInternos metodosInternos = new MetodosInternos(this);

    private PedidosAdapter adaptador;
    Pedidos result[];
    int tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimientos);

        inicializar();

        tipo = getIntent().getExtras().getInt("Tipo", 0);
        this.setTitle(R.string.tituloAbastecimiento);

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        AbastecimientoRecycleView.setLayoutManager(linearLayoutManager);

        animationUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        animationDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);

        cargarDatos(tipo);
    }

    private void inicializar()
    {
        AbastecimientoListView = (ListView)findViewById(R.id.AbastecimientoListView);
        AbastecimientoRecycleView = (RecyclerView) findViewById(R.id.AbastecimientoRecycleView);
    }

    private void cargarDatos(int item)
    {
        if(metodosInternos.conexionRed())
        {
            switch (item)
            {
                case 1:
                    try
                    {
                        PedidosDao _dao = getPedidosDao();
                        result = _dao.findAll();
                        adaptador = new PedidosAdapter(this,  R.layout.listview_pedidos, result, 1);
                        AbastecimientoListView.setAdapter(adaptador);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                    break;
                case 2:
                    try
                    {
                        VwAbastecimientoDao _dao = getVwAbastecimientoDao();
                        lista = _dao.findAll();

                        listaArticulosAdapter = new ListaArticulosAdapter(this, animationUp, animationDown, lista);
                        AbastecimientoRecycleView.setAdapter(listaArticulosAdapter);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
        else
        {
            //bd interna
            metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
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
                int menu = 0;
                if(AbastecimientoRecycleView.getVisibility() == View.VISIBLE)
                {
                    AbastecimientoRecycleView.setVisibility(View.INVISIBLE);
                    AbastecimientoListView.setVisibility(View.VISIBLE);
                    menu = 1;
                }
                else
                {
                    AbastecimientoRecycleView.setVisibility(View.VISIBLE);
                    AbastecimientoListView.setVisibility(View.INVISIBLE);
                    menu = 2;
                }

                cargarDatos(menu);
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
}
