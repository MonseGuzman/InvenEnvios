package com.innovati.felipehernandez.invenenvios.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ListaArticulosAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.DetallesPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;

public class AbastecimientoAhoraSiActivity extends AppCompatActivity
{
    private RecyclerView recyclerView2;
    private Animation animationUp, animationDown;
    private MenuItem menu_cambia;

    private ListaArticulosAdapter listaArticulosAdapter;
    private DetallesPedidos result[];
    private boolean ban = false;
    private MetodosInternos metodosInternos = new MetodosInternos(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimiento_ahora_si);

        this.setTitle(R.string.tituloAbastecimiento);
        inicializacion();

        //creacion de la lista así
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(linearLayoutManager);

        animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        filtar();
    }

    private void inicializacion()
    {
        recyclerView2 = (RecyclerView) findViewById(R.id.recycleView2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        getMenuInflater().inflate(R.menu.menu_abastecimiento, menu);
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
            case R.id.menu_cambia:
                if(!ban)
                    menu_cambia.setIcon(R.drawable.ic_contenido);
                else
                    menu_cambia.setIcon(R.drawable.ic_detalle);
                //lo que sea que vaya a hacer aquí
                //finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static DetallesPedidosDao getVwArticulosDao()
    {
        return DetallesPedidosDaoFactory.create();
    }

    public void filtar()
    {
            if(metodosInternos.conexionRed())
            {
                //sin filtro = todos
                try
                {
                    DetallesPedidosDao _dao = getVwArticulosDao();
                    result = _dao.findAll();

                    listaArticulosAdapter = new ListaArticulosAdapter(animationUp, animationDown, this, result);
                    recyclerView2.setAdapter(listaArticulosAdapter);
                }
                catch(Exception e)
                {
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //código para buscar en la bd interna
                metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
            }
    }
}
