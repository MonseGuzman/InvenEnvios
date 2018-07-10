package com.innovati.felipehernandez.invenenvios.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ListaArticulosAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;

public class AbastecimientoAhoraSiActivity extends AppCompatActivity
{
    private RecyclerView recyclerView2;
    private Animation animationUp, animationDown;
    private MenuItem menu_cambia;

    private ListaArticulosAdapter listaArticulosAdapter;
    private DetallesPedidos detallesPedidos[];
    private boolean ban = false;

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

        listaArticulosAdapter = new ListaArticulosAdapter(animationUp, animationDown, this, detallesPedidos);
        recyclerView2.setAdapter(listaArticulosAdapter);
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
                if(ban == false)
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
}
