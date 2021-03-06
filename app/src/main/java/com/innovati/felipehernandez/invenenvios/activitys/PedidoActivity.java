package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.API.DelayedProgressDialog;
import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.TabsAdapter;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.DetallesPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwUsuariosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidosPk;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwUsuariosDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.VwAbastecimientos_I;
import com.innovati.felipehernandez.invenenvios.database.VwAbastecimientos_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwDetallePedido_I;
import com.innovati.felipehernandez.invenenvios.database.VwDetallePedido_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwPedidos_I;
import com.innovati.felipehernandez.invenenvios.database.VwPedidos_IDao;
import com.innovati.felipehernandez.invenenvios.fragments.DatosPedidoFragment;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class PedidoActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static TextView fechaTextView, tvTotal;
    public static TextView ClienteEntTextView, tvAgente, tvArticulosNum;
    private static int Folio;
    public static List<ArticulosPedido> articulosPedidoList;
    private SharedPreferences preferences;
    public static String clave = "";
    public static String nombreC = "No elegido";
    public static String agente;
    private static String idUsuario;
    private static MetodosInternos metodosInternos;
    private static DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        articulosPedidoList = new ArrayList<ArticulosPedido>();
        metodosInternos = new MetodosInternos(this);
        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        inicializacion();

        daoSession = ((MyApp) this.getApplication()).getDaoSession();
        geneFolio();

        limpiar();
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
        tvArticulosNum = (TextView)findViewById(R.id.tvArticulosNum);
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
        }
        else
        {
            if (ClienteEntTextView.getText().toString().equals("Cliente: No elegido"))
                finish();
            else
                mensajeAdvertencia();
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
                if (ClienteEntTextView.getText().toString().equals("Cliente: No elegido"))
                    finish();
                else
                    mensajeAdvertencia();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mensajeAdvertencia()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.advertencia);
        alert.setMessage(R.string.salirSinGuardar);
        alert.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        alert.setNeutralButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.setCancelable(false);
        alert.show();
    }

    public static void limpiar() {
        //fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        fechaTextView.setText("Fecha: " + dateFormat.format(date));

        tvAgente.setText("Agente: " + agente);
        ClienteEntTextView.setText("Cliente: No elegido");
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
        if(a != null){
            if (ban){
                articulosPedidoList.set(position,a);
            }else{
                articulosPedidoList.add(a);
            }
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
        float total = 0, x = 0;
        if (articulosPedidoList != null){
            for(ArticulosPedido ar: articulosPedidoList){
                if (ar.isStatus()){
                    total += ar.getTotal();
                    x++;
                }
            }
        }
        tvTotal.setText("Total: "+String.valueOf(total));
        tvArticulosNum.setText("Articulos: ".concat(String.valueOf(x)));
    }

    public static void addPedidoDb(){
        //"%05d%n", 5
        String idPedido = UUID.randomUUID().toString();
        Date date = new Date();
        //int auxFolio = Integer.valueOf(tvFolio.getText().toString());

        if(!metodosInternos.conexionRed()){
            insertarInterna(idPedido,idUsuario,clave,date,Short.valueOf("1"),getSub(),getIva(),getTotal(),"No Hay",Folio);

            HashMap<String, Float> cantidades = new HashMap();
            List<String> unidades = new ArrayList<>();
            List<String> nombres = new ArrayList<>();
            for (ArticulosPedido ar:articulosPedidoList)
            {
                if (ar.isStatus())
                {
                    detPedidoInterna(idUsuario,idPedido,ar);
                    if(!nombres.contains(ar.getNombre()))
                    {
                        nombres.add(ar.getNombre());
                        cantidades.put(ar.getNombre(), ar.getCantidad());
                    }
                    else
                    {
                        Float n =  cantidades.get(ar.getNombre()) + ar.getCantidad();
                        cantidades.put(ar.getNombre(),n);
                    }
                    if(!unidades.contains(ar.getPresentacion()))
                    {
                        unidades.add(ar.getPresentacion());
                    }

                }
            }
            for(int y=0; y<nombres.size(); y++)
            {
                VwAbastecimientos_I abastecimiento = new VwAbastecimientos_I();
                VwAbastecimientos_IDao vwAbastecimientos_iDao = daoSession.getVwAbastecimientos_IDao();
                QueryBuilder<VwAbastecimientos_I> qb = vwAbastecimientos_iDao.queryBuilder();
                qb.where(VwAbastecimientos_IDao.Properties.Estatus.eq(1), VwAbastecimientos_IDao.Properties.Nombre.eq(nombres.get(y)));
                List<VwAbastecimientos_I> list = qb.list();

                if(!list.isEmpty())
                    abastecimiento.setId(list.get(0).getId());

                abastecimiento.setNombre(nombres.get(y));
                abastecimiento.setUnidadPrimaria(unidades.get(y));
                abastecimiento.setEstatus((short) 1);
                Float n = cantidades.get(nombres.get(y));
                abastecimiento.setCantidad(n);

                VwAbastecimientos_IDao abastecimientos_iDao = daoSession.getVwAbastecimientos_IDao();
                abastecimientos_iDao.insertOrReplace(abastecimiento);
            }
        }else{
            insertar(idPedido,idUsuario,clave,date,Short.valueOf("1"),getSub(),getIva(),getTotal(),"No Hay", Folio);
            for (ArticulosPedido ar:articulosPedidoList){
                if (ar.isStatus()){
                    detPedido(idUsuario,idPedido,ar);
                }
            }
        }
        articulosPedidoList.clear();
        calTotal();
        DatosPedidoFragment.updateAdapter();
        //nombreC = "No elegido";
        limpiar();
    }

    public static void insertar(String idPedido,String idUsuario, String claveCliente, Date fecha, short estatus, float subtotal, float iva, float total, String observaciones, int folio)
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

    public static void detPedido(String idUsuario,String idPedido,ArticulosPedido a)
    {
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
        detalle.setSurtido((short)1);
        detalle.setUltimaFechaActualizacion(Calendar.getInstance().getTime());
        detalle.setUltimoUsuarioActualizacion(idUsuario);
        InsertarDetalle detalleInsertar = new InsertarDetalle();
        detalleInsertar.execute(detalle);

    }

    public static void insertarInterna(String idPedido,String idUsuario, String claveCliente, Date fecha, short estatus, float subtotal, float iva, float total, String observaciones, int folio)
    {
        VwPedidos_I pedidos = new VwPedidos_I();
        pedidos.setIdPedido(idPedido);
        pedidos.setIdUsuario(idUsuario);
        pedidos.setClaveCliente(claveCliente);
        pedidos.setFecha(fecha);
        pedidos.setNombre(nombreC);
        pedidos.setEstatus(estatus);
        pedidos.setSubtotal(subtotal);
        pedidos.setIva(iva);
        pedidos.setTotal(total);
        pedidos.setFolio((folio));
        pedidos.setObservaciones(observaciones);
        pedidos.setServidor(false);
        pedidos.setUltimoUsuarioActualizacion(idUsuario);
        pedidos.setUltimaFechaActualizacion(Calendar.getInstance().getTime());
        VwPedidos_IDao pedidos_iDao = daoSession.getVwPedidos_IDao();
        pedidos_iDao.insert(pedidos);
    }

    public static void detPedidoInterna(String idUsuario,String idPedido,ArticulosPedido a){


        VwDetallePedido_I detalle = new VwDetallePedido_I();
        String idDetallePedido = UUID.randomUUID().toString();
        detalle.setIdDetallePedido(idDetallePedido);
        detalle.setIdPedido(idPedido);
        detalle.setClaveArticulo(a.getIdArticulo());
        detalle.setCantidad(a.getCantidad());
        detalle.setPrecio(a.getPrecio());
        detalle.setSubtotal(a.getSubTotal());
        detalle.setIva(a.getIva());
        detalle.setTotal(a.getTotal());
        detalle.setUnidadPrimaria(a.getPresentacion());
        detalle.setServidor(false);
        detalle.setSurtido((short)1);
        detalle.setNombre(a.getNombre());
        detalle.setFechaActualizacion(Calendar.getInstance().getTime());
        detalle.setUsuarioActualizacion(idUsuario);
        VwDetallePedido_IDao detallesPedidos_iDao = daoSession.getVwDetallePedido_IDao();
        detallesPedidos_iDao.insert(detalle);
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
        if(!metodosInternos.conexionRed())
        {
            VwPedidos_IDao pedidos_iDao = daoSession.getVwPedidos_IDao();
            QueryBuilder<VwPedidos_I> qb = pedidos_iDao.queryBuilder();
            Long folio = qb.count()+1;
            Folio = Integer.valueOf(folio.toString());
            //tvFolio.setText(String.valueOf(folio));
        }
    }

    public class Consulta extends AsyncTask<PedidosDao, PedidosDao, Pedidos[]>
    {
        DelayedProgressDialog progressDialog = new DelayedProgressDialog();

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            progressDialog.setCancelable(false);
            progressDialog.show(getSupportFragmentManager(), "tag");
        }

        @Override
        protected Pedidos[] doInBackground(PedidosDao... pedidosDaos)
        {
            Pedidos result[] = null;
            try
            {
                result = pedidosDaos[0].findByDynamicSelect("SELECT NULL, NULL, Folio, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL FROM Pedidos", null);
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
            progressDialog.cancel();

            /*int folioAux;
            if(pedidos != null)
                 folioAux = pedidos.length+1;
            else
                folioAux = 1;*/

            //tvFolio.setText(String.valueOf(folioAux));
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            progressDialog.cancel();
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
                DetallesPedidosPk pk = _dao.insert(detallesPedidos[0]);
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