package com.innovati.felipehernandez.invenenvios.activitys;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.TabsAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;
import com.innovati.felipehernandez.invenenvios.fragments.BusquedaArticulosFragment;
import com.innovati.felipehernandez.invenenvios.fragments.BusquedaClienteFragment;
import com.innovati.felipehernandez.invenenvios.fragments.DatosPedidoFragment;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EntregasActivity extends AppCompatActivity
{
    public static List<ArticulosPedido> articulosPedidoList;
    MetodosInternos metodosInternos = new MetodosInternos(this);
    String[] result;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView fechaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);

        inicializacion();

        //fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        fechaTextView.setText(dateFormat.format(date));

        //crea tab y darle una gravedad
        tabLayout.addTab(tabLayout.newTab().setText("Seleccione cliente"));
        tabLayout.addTab(tabLayout.newTab().setText("Seleccione los artículos"));
        tabLayout.addTab(tabLayout.newTab().setText("Detalles del pedido"));
        //tabLayout.setSelectedTabIndicatorColor(getColor(R.color.primaryTextColor));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //viewpager
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                //cuando se selecciona un tab
                int posicion = tab.getPosition();
                viewPager.setCurrentItem(posicion);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void inicializacion()
    {
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        fechaTextView = (TextView)findViewById(R.id.fechaTextView);
    }

    @Override
    public void onBackPressed() {
        //cantidad de fragmentos que estan actualmente apilados.
        if(getSupportFragmentManager().getBackStackEntryCount() != 0)
        {
            //regresa
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();

        }
        else
            finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /*public void filtar(String nombre)
    {
        VwClientes[] clientes;

        //todos los que se aparecen con el where
        if(metodosInternos.conexionRed())
        {
            try
            {
                nombre = "%" + nombre;
                nombre += "%";
                VwClientesDao _dao = getVwClientesDao();
                clientes = _dao.findWhereNombreEquals(nombre);

                for(int x=0; x<clientes.length-1;x++)
                {
                    result[x] = clientes[x].getNombre();
                }
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

    public static VwClientesDao getVwClientesDao()
    {
        return VwClientesDaoFactory.create();
    }*/
}
