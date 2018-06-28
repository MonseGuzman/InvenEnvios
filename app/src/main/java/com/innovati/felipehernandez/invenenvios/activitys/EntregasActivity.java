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
import com.innovati.felipehernandez.invenenvios.clases.dao.DetallesPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;
import com.innovati.felipehernandez.invenenvios.fragments.BusquedaArticulosFragment;
import com.innovati.felipehernandez.invenenvios.fragments.BusquedaClienteFragment;
import com.innovati.felipehernandez.invenenvios.fragments.DatosPedidoFragment;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Locale;

public class EntregasActivity extends AppCompatActivity
{
    public static List<ArticulosPedido> articulosPedidoList = new ArrayList<>();
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
    public static void addArticulo(ArticulosPedido a){
        boolean ban = false;
        int position = -1;
        for(ArticulosPedido ar: articulosPedidoList){
            position +=1;
            if (ar.getIdArticulo() == a.getIdArticulo()){
                ban = true;
                break;
            }
        }
        if (ban){
            articulosPedidoList.set(position,a);
        }else{
            articulosPedidoList.add(a);
        }
    }

    /*public void cuadroDialogo()
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

    public void insertar(String idUsuario, String claveCliente, Date fecha, short estatus, float subtotal, float iva, float total, String observaciones)
    {
        String idPedido = UUID.randomUUID().toString();
        Pedidos pedidos = new Pedidos();
        pedidos.setIdPedido(idPedido);
        pedidos.setIdUsuario(idUsuario);
        pedidos.setClaveCliente(claveCliente);
        pedidos.setFecha(fecha);
        pedidos.setEstatus(estatus);
        pedidos.setSubtotal(subtotal);
        pedidos.setIva(iva);
        pedidos.setTotal(total);
        pedidos.setObservaciones(observaciones);
        pedidos.setUltimoUsuarioActualizacion(idUsuario);
        pedidos.setUltimaFechaActualizacion(Calendar.getInstance().getTime());

        try
        {
            PedidosDao dao = getPedidosDao();
            dao.insert(pedidos);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        }
    }
    public void detPedido(String idUsuario,String idPedido,ArticulosPedido a){
        DetallesPedidos detalle = new DetallesPedidos();
        detalle.setIdPedido(idPedido);
        detalle.setClaveArticulo(a.getIdArticulo());
        detalle.setCantidad(a.getCantidad());
        detalle.setPrecio(a.getPrecio());
        detalle.setSubtotal(a.getSubTotal());
        detalle.setIva(a.getIva());
        detalle.setTotal(a.getTotal());
        detalle.setUltimaFechaActualizacion(Calendar.getInstance().getTime());
        detalle.setUltimoUsuarioActualizacion(idUsuario);

        try
        {
            DetallesPedidosDao dao = getDetallesPedidosDao();
            dao.insert(detalle);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        }
    }
    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
    }

    public static DetallesPedidosDao getDetallesPedidosDao()
    {
        return DetallesPedidosDaoFactory.create();
    }
}
