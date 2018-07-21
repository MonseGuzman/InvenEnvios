package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.TabsAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.DetallesPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwUsuariosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwUsuarios;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwUsuariosDaoFactory;
import com.innovati.felipehernandez.invenenvios.fragments.DatosPedidoFragment;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class PedidoActivity extends AppCompatActivity
{
    public static List<ArticulosPedido> articulosPedidoList = new ArrayList<ArticulosPedido>();
    private SharedPreferences preferences;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static TextView fechaTextView, tvTotal;
    public static TextView ClienteEntTextView, tvAgente, tvFolio;
    public static String clave = "";
    public static String nombreC = "No elegido";
    public static String agente;
    private static String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        inicializacion();
        geneFolio();
        //fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        fechaTextView.setText("Fecha: "+dateFormat.format(date));
        agente = preferences.getString("agente", " Falta Dato");
        idUsuario = preferences.getString("idUsuario", null);
        tvAgente.setText("Agente: " + agente);
        //crea tab y darle una gravedad
        tabLayout.addTab(tabLayout.newTab().setText("Seleccione cliente"));
        tabLayout.addTab(tabLayout.newTab().setText("Seleccione los artículos"));
        tabLayout.addTab(tabLayout.newTab().setText("Detalles del pedido"));
        //tabLayout.setSelectedTabIndicatorColor(getColor(R.color.primaryTextColor));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ClienteEntTextView.setText("Cliente: " + nombreC);
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
                if(siPasa()){
                    viewPager.setCurrentItem(posicion);
                }else{
                    Log.i("test","Falta Elegir un Cliente");
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setOffscreenPageLimit(3); //mantener las tabs en vista
        calTotal();
    }

    private void inicializacion()
    {
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        fechaTextView = (TextView)findViewById(R.id.fechaTextView);
        tvTotal = findViewById(R.id.tvTotalEnt);
        ClienteEntTextView = (TextView)findViewById(R.id.ClienteEntTextView);
        tvAgente = (TextView)findViewById(R.id.tvAgente);
        tvFolio = (TextView)findViewById(R.id.tvFolioEnt);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void addArticulo(ArticulosPedido a){
        boolean ban = false;
        int position = -1;
        if(articulosPedidoList != null){
            for(ArticulosPedido ar: articulosPedidoList){
                position +=1;
                if (ar.getIdArticulo() == a.getIdArticulo()){
                    ban = true;
                    break;
                }
            }
        }
        if (ban){
            articulosPedidoList.set(position,a);
        }else{
            articulosPedidoList.add(a);
        }
        calTotal();
        DatosPedidoFragment.updateAdapter();
    }

    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
    }

    public static DetallesPedidosDao getDetallesPedidosDao()
    {
        return DetallesPedidosDaoFactory.create();
    }
    static public void calTotal(){
        float total = 0;
        if (articulosPedidoList != null){
            for(ArticulosPedido ar: articulosPedidoList){
                if (ar.isStatus()){
                    total += ar.getTotal();

                }
            }
        }
        tvTotal.setText("Total: "+String.valueOf(total));
    }

    public static void addPedidoDb(){
        //"%05d%n", 5
        String idPedido = UUID.randomUUID().toString();
        Date date = new Date();
        String auxFolio = tvFolio.getText().toString();

        insertar(idPedido,idUsuario,clave,date,Short.valueOf("1"),getSub(),getIva(),getTotal(),"No Hay", auxFolio);
        for (ArticulosPedido ar:articulosPedidoList){
            if (ar.isStatus()){
                detPedido(idUsuario,idPedido,ar);
            }
        }
        articulosPedidoList = null;
        calTotal();
        DatosPedidoFragment.updateAdapter();
        nombreC = "No elegido";
    }

    public static void insertar(String idPedido,String idUsuario, String claveCliente, Date fecha, short estatus, float subtotal, float iva, float total, String observaciones, String folio)
    {
        Pedidos pedidos = new Pedidos();
        pedidos.setIdPedido(idPedido);
        pedidos.setIdUsuario(idUsuario);
        pedidos.setClaveCliente(claveCliente);
        pedidos.setFecha(fecha);
        pedidos.setEstatus(estatus);
        pedidos.setSubtotal(subtotal);
        pedidos.setIva(iva);
        pedidos.setTotal(total);
        pedidos.setFolio((folio));
        pedidos.setObservaciones(observaciones);
        pedidos.setUltimoUsuarioActualizacion(idUsuario);
        pedidos.setUltimaFechaActualizacion(Calendar.getInstance().getTime());
        InsertarPedido insertar = new InsertarPedido();
        insertar.execute(pedidos);

    }
    public static void detPedido(String idUsuario,String idPedido,ArticulosPedido a){
        DetallesPedidos detalle = new DetallesPedidos();
        String idDetallePedido = UUID.randomUUID().toString();
        detalle.setIdDetallePedido(idDetallePedido);
        detalle.setIdPedido(idPedido);
        detalle.setClaveArticulo(a.getIdArticulo());
        detalle.setCantidad(a.getCantidad());
        detalle.setPrecio(a.getPrecio());
        detalle.setSubtotal(a.getSubTotal());
        detalle.setIva(a.getIva());
        detalle.setTotal(a.getTotal());
        detalle.setUltimaFechaActualizacion(Calendar.getInstance().getTime());
        detalle.setUltimoUsuarioActualizacion(idUsuario);
        InsertarDetalle detalleInsertar = new InsertarDetalle();
        detalleInsertar.execute(detalle);

    }
    public static float getTotal(){
        float total = 0;
        if (articulosPedidoList != null){
            for(ArticulosPedido ar: articulosPedidoList){
                if (ar.isStatus()){
                    total += ar.getTotal();
                }
            }
        }
        return total;
    }
    public static float getSub(){
        float sub = 0;
        if (articulosPedidoList != null){
            for(ArticulosPedido ar: articulosPedidoList){
                if (ar.isStatus()){
                    sub += ar.getSubTotal();
                }
            }
        }
        return sub;
    }
    public static float getIva(){
        float iva = 0;
        if (articulosPedidoList != null){
            for(ArticulosPedido ar: articulosPedidoList){
                if (ar.isStatus()){
                    iva += ar.getIva();
                }
            }
        }
        return iva;
    }
    public static VwUsuariosDao getVwUsuariosDao() {
        return VwUsuariosDaoFactory.create();
    }

    public void geneFolio()
    {

        //"%05d%n", 5ffff
        Pedidos pedidosResult[] = null;
        PedidosDao daoPedidos = getPedidosDao();
        Consulta c = new Consulta();
        c.execute(daoPedidos, daoPedidos);
    }

    public class Consulta extends AsyncTask<PedidosDao, PedidosDao, Pedidos[]>
    {

        @Override
        protected Pedidos[] doInBackground(PedidosDao... pedidosDaos)
        {
            Pedidos result[] = null;
            try
            {
                String folioAux;
                result = pedidosDaos[0].findByDynamicSelect("SELECT NULL, NULL, Folio, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL FROM Pedidos_I", null);
            }
            catch (Exception e)
            {

            }
            return result;
        }

        @Override
        protected void onPostExecute(Pedidos[] pedidos)
        {
            super.onPostExecute(pedidos);
            String folioAux = String.format("%05d%n",pedidos.length+1);
            tvFolio.setText(folioAux);
        }
    }

    private static class InsertarPedido extends AsyncTask<Pedidos, Void, Void>
    {

        @Override
        protected Void doInBackground(Pedidos... pedidos)
        {
            PedidosDao _dao = getPedidosDao();
            try
            {
                _dao.insert(pedidos[0]);
            }
            catch(Exception e)
            {

            }
            return null;
        }
    }

    private static class InsertarDetalle extends AsyncTask<DetallesPedidos, Void, Void>
    {

        @Override
        protected Void doInBackground(DetallesPedidos... detallesPedidos)
        {
            DetallesPedidosDao _dao = getDetallesPedidosDao();
            try
            {

            }
            catch (Exception e)
            {

            }
            return null;
        }
    }
    private boolean siPasa(){
        return ((nombreC == "No elegido")? false: true);
    }

}
//consulta del pedidio junto con sus detalles
//este pedidio es para las entregas jajaja