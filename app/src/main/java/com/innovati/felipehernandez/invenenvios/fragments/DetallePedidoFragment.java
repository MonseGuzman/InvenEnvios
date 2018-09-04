package com.innovati.felipehernandez.invenenvios.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.innovati.felipehernandez.invenenvios.API.DelayedProgressDialog;
import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ArticulosPedidosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.RecycleViewOnItemClickListener;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.DetallesPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwDetallePedidoDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidosPk;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.PedidosPk;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwAbastecimiento;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwDetallePedido;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwDetallePedidoDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.Pedidos_I;
import com.innovati.felipehernandez.invenenvios.database.Pedidos_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwAbastecimientos_I;
import com.innovati.felipehernandez.invenenvios.database.VwAbastecimientos_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwArticulos_I;
import com.innovati.felipehernandez.invenenvios.database.VwArticulos_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwDetallePedido_I;
import com.innovati.felipehernandez.invenenvios.database.VwDetallePedido_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwPedidos_I;
import com.innovati.felipehernandez.invenenvios.database.VwPedidos_IDao;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DetallePedidoFragment extends Fragment implements View.OnClickListener
{
    private RecyclerView recyclerArticulos;
    private Button btnMas, btnMeno, btnAceptar, btnCancelar;
    private EditText editCantida;
    private ConstraintLayout datosEditArticle;

    private List<ArticulosPedido> articulosPedidos = new ArrayList<ArticulosPedido>();
    VwDetallePedido result[];
    String clavePedido = "";
    private static float cantidaNum; //exitArticul = 0,
    private static int positionList;
    private boolean bandera = true, si = false;
    private List<String> listDet;
    static List<ArticulosPedido> articuloEdit;
    String idUsuario = "";
    private MetodosInternos metodosInternos;
    private DaoSession daoSession;
    private List<Long> ids = new ArrayList<>();

    public DetallePedidoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalle_pedido, container, false);

        inicializar(v);
        daoSession = ((MyApp) getActivity().getApplication()).getDaoSession();

        btnMas.setOnClickListener(this);
        btnMeno.setOnClickListener(this);
        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        editCantida.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerArticulos.setLayoutManager(manager);
        recyclerArticulos.setHasFixedSize(true);

        Bundle args = getArguments();
        clavePedido = args.getString("pedido", "");
        bandera = args.getBoolean("bandera",true);
        loadData();
        si = false;
        if (bandera){
            ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    int  position = viewHolder.getAdapterPosition();
                    ArticulosPedidosAdapter adapter = (ArticulosPedidosAdapter) recyclerArticulos.getAdapter();
                    ArticulosPedido articulosPedido;
                    articulosPedido = adapter.articulosPedidos.get(position);
                    if (direction == ItemTouchHelper.RIGHT){
                        articulosPedido.setEstado((short)2);
                    }else if (direction == ItemTouchHelper.LEFT){
                        articulosPedido.setEstado((short)3);
                        articulosPedido.setCantidad(0);
                        articulosPedido.setIva(0);
                        articulosPedido.setSubTotal(0);
                        articulosPedido.setTotal(0);
                    }
                    adapter.articulosPedidos.set(position,articulosPedido);
                    updateAdapter();
                    si = true;
                }

            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recyclerArticulos);
        }

        return v;
    }

    private void inicializar(View v)
    {
        recyclerArticulos = (RecyclerView) v.findViewById(R.id.listaDetalleRecycle);
        datosEditArticle = v.findViewById(R.id.includeEditoArticleDetalle);
        btnMas = v.findViewById(R.id.MasButton_AEdit);
        btnMeno = v.findViewById(R.id.MenosButton_AEdit);
        btnAceptar = v.findViewById(R.id.editArticuloListCancelar);
        btnCancelar = v.findViewById(R.id.editArticuloListAceptar);
        editCantida = v.findViewById(R.id.cantidadEditText_AEdit);
        datosEditArticle.setVisibility(View.INVISIBLE);

        listDet = new ArrayList<String>();
        articuloEdit = new ArrayList<ArticulosPedido>();
    }

    public void loadData()
    {
        metodosInternos = new MetodosInternos(getActivity());

        if(metodosInternos.conexionRed())
        {
            VwDetallePedidoDao detallesPedidos = getVwDetallePedidoDao();
            Consulta c = new Consulta();
            c.execute(detallesPedidos);
        }
        else {
            internaBD();
        }
    }

    private void internaBD()
    {
        VwDetallePedido_IDao detallePedidoIDao = daoSession.getVwDetallePedido_IDao();
        QueryBuilder<VwDetallePedido_I> qb = detallePedidoIDao.queryBuilder();

        qb.where(VwDetallePedido_IDao.Properties.IdPedido.eq(clavePedido));
        List<VwDetallePedido_I> detallePedido = qb.list();
        result = new VwDetallePedido[detallePedido.size()];

        for(int x=0; x<detallePedido.size(); x++)
        {
            VwDetallePedido objetoDetalle = new VwDetallePedido();

            ids.add(detallePedido.get(x).getId());
            objetoDetalle.setIdPedido(detallePedido.get(x).getIdPedido());
            objetoDetalle.setIdDetallePedido(detallePedido.get(x).getIdDetallePedido());
            objetoDetalle.setClaveArticulo(detallePedido.get(x).getClaveArticulo());
            objetoDetalle.setNombre(detallePedido.get(x).getNombre());
            objetoDetalle.setCantidad(detallePedido.get(x).getCantidad());
            objetoDetalle.setPrecio(detallePedido.get(x).getPrecio());
            objetoDetalle.setSubtotal(detallePedido.get(x).getSubtotal());
            objetoDetalle.setTotal(detallePedido.get(x).getTotal());
            objetoDetalle.setIva(detallePedido.get(x).getIva());
            objetoDetalle.setUltimoUsuarioActualizacion(detallePedido.get(x).getUsuarioActualizacion());
            objetoDetalle.setUltimaFechaActualizacion(detallePedido.get(x).getFechaActualizacion());

            result[x] = objetoDetalle;
        }
        for (VwDetallePedido pedidos: result){
            ArticulosPedido articulo = new ArticulosPedido();
            articulo.setNombre(pedidos.getNombre());
            articulo.setIdArticulo(pedidos.getClaveArticulo());
            articulo.setPresentacion(pedidos.getUnidadPrimaria());
            articulo.setCantidad((float) pedidos.getCantidad());
            articulo.setIva(pedidos.getIva());
            articulo.setPrecio(pedidos.getPrecio());
            articulo.setTotal(pedidos.getTotal());
            articulo.setSubTotal(pedidos.getSubtotal());
            articulo.setStatus(true);

            articulosPedidos.add(articulo);
            listDet.add(pedidos.getIdDetallePedido());
            idUsuario = pedidos.getIdDetallePedido();
        }

        int x = 0;
        for(ArticulosPedido articulosPedido: articulosPedidos)
        {
            VwArticulos_IDao articulos_i = daoSession.getVwArticulos_IDao();
            QueryBuilder<VwArticulos_I> qbArticulos = articulos_i.queryBuilder();
            qbArticulos.where(VwArticulos_IDao.Properties.Clave.eq(articulosPedido.getIdArticulo()));
            List<VwArticulos_I> list = qbArticulos.list();
            //Float tem = list.get(0).getExistenciaTotal();
            //articulosPedido.setExits(tem);
            articulosPedidos.set(x,articulosPedido);
            x++;
        }
        updateAdapter();
    }

    public static VwDetallePedidoDao getVwDetallePedidoDao()
    {
        return VwDetallePedidoDaoFactory.create();
    }

    public static VwArticulosDao getVwArticulosDao()
    {
        return VwArticulosDaoFactory.create();
    }

    public void updateAdapter(){
        recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(true,articulosPedidos, new RecycleViewOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(bandera){
                    si = true;
                    positionList =position;
                    updateAr(position);
                }
            }
        }));
    }

    public  void updateAr(int x){
        try{
            updateArticle(x);
        }catch (Exception e){
            //updateArticle(0);
        }
    }

    public void updateArticle(int position){
        try{
            articuloEdit.set(0,articulosPedidos.get(position));
        }catch (Exception e){
            articuloEdit.add(articulosPedidos.get(position));
        }
        updateAdapterArt(articuloEdit);
        //exitArticul = articuloEdit.get(0).getExits();
        editCantida.setText(String.valueOf(articulosPedidos.get(position).getCantidad()));
        datosEditArticle.setVisibility(View.VISIBLE);
    }

    public  void updateAdapterArt(List<ArticulosPedido> articuloEdit){
        recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(articuloEdit, new RecycleViewOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                updateAr(position);
            }
        }));
    }

    @Override
    public void onClick(View view) {
        validacionCatidad();
        switch (view.getId())
        {
            case R.id.MasButton_AEdit:
                if(cantidaNum != 0){
                    cantidaNum -=1;
                }
                break;
            case R.id.MenosButton_AEdit:
                /*float exit = exitArticul;
                if(cantidaNum < exit){
                    cantidaNum +=1;
                }*/
                cantidaNum +=1;
                break;
            case R.id.editArticuloListCancelar:
                updateArticleList(false);
                break;
            case R.id.editArticuloListAceptar:
                updateArticleList(true);
                break;

        }
        editCantida.setText(String.valueOf(cantidaNum));
    }

    private class Consulta extends AsyncTask<VwDetallePedidoDao, Void, VwDetallePedido>
    {
        DelayedProgressDialog progressDialog = new DelayedProgressDialog();

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            progressDialog.setCancelable(false);
            progressDialog.show(getFragmentManager(), "tag");
        }

        @Override
        protected VwDetallePedido doInBackground(VwDetallePedidoDao... vwDetallePedidoDaos)
        {
            try
            {
                result = vwDetallePedidoDaos[0].findWhereIdPedidoEquals(clavePedido);
                articulosPedidos.clear();

                for(VwDetallePedido pedidos: result)
                {
                    ArticulosPedido articulo = new ArticulosPedido();
                    articulo.setNombre(pedidos.getNombre());
                    articulo.setIdArticulo(pedidos.getClaveArticulo());
                    articulo.setPresentacion(pedidos.getUnidadPrimaria());
                    articulo.setCantidad((float) pedidos.getCantidad());
                    articulo.setIva(pedidos.getIva());
                    articulo.setPrecio(pedidos.getPrecio());
                    articulo.setTotal(pedidos.getTotal());
                    articulo.setSubTotal(pedidos.getSubtotal());
                    articulo.setStatus(true);
                    articulo.setEstado(pedidos.getSurtido());
                    //articulo.setExits(0);
                    articulosPedidos.add(articulo);
                    listDet.add(pedidos.getIdDetallePedido());
                    idUsuario = pedidos.getIdDetallePedido();

                }

                VwArticulos vwArticulos[];
                int x = 0;
                for(ArticulosPedido articulosPedido: articulosPedidos)
                {
                    vwArticulos = getVwArticulosDao().findWhereClaveEquals(articulosPedido.getIdArticulo());
                    Double tem = vwArticulos[0].getExistenciaTotal();
                    //articulosPedido.setExits(Float.valueOf(tem.toString()));
                    articulosPedidos.set(x,articulosPedido);
                    x++;
                }
            }
            catch (Exception e) { }
            return null;
        }

        @Override
        protected void onPostExecute(VwDetallePedido vwDetallePedido)
        {
            super.onPostExecute(vwDetallePedido);

            progressDialog.cancel();
            updateAdapter();
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            progressDialog.cancel();
        }
    }

    public void updateArticleList(boolean ban){
        if (ban){
            float precioAux = Float.valueOf(articulosPedidos.get(positionList).getPrecio());
            ArticulosPedido articulosPedido = new ArticulosPedido();
            articulosPedido.setIdArticulo(articulosPedidos.get(positionList).getIdArticulo());
            articulosPedido.setNombre(articulosPedidos.get(positionList).getNombre());
            articulosPedido.setPrecio(precioAux);
            articulosPedido.setCantidad(cantidaNum);
            articulosPedido.setSubTotal((float) (precioAux*cantidaNum));
            articulosPedido.setPresentacion(articulosPedidos.get(positionList).getPresentacion());
            articulosPedido.setStatus(true);
            articulosPedido.setEstado(articulosPedidos.get(positionList).getEstado());
            float ivaAux = (float) (articulosPedido.getTotal()*0.16);
            articulosPedido.setIva(ivaAux);
            //ivaAux = articulosPedidos.get(positionList).getExits();
            //articulosPedido.setExits(ivaAux);
            articulosPedido.setTotal(articulosPedido.getSubTotal()+articulosPedido.getIva());
            addArticulo(articulosPedido);
        }
        datosEditArticle.setVisibility(View.INVISIBLE);
        updateAdapter();
    }

    public void addArticulo(ArticulosPedido a){
        boolean ban = false;
        int position = -1;
        if(articulosPedidos != null){
            for(ArticulosPedido ar: articulosPedidos){
                position +=1;
                if (ar.getIdArticulo() == a.getIdArticulo()){
                    ban = true;
                    break;
                }
            }
        }
        if (ban){
            articulosPedidos.set(position,a);
        }else
        updateAdapter();
    }


    public void uptadeExits(String idDet, String idPedido, String clave ,float cantidad, float precio, float subTotal, float iva, float total, String idUsuario, short estado)
    {
        DetallesPedidos detalle = new DetallesPedidos();
        detalle.setIdDetallePedido(idDet);
        detalle.setIdPedido(idPedido);
        detalle.setClaveArticulo(clave);
        detalle.setCantidad(cantidad);
        detalle.setPrecio(precio);
        detalle.setSubtotal(subTotal);
        detalle.setIva(iva);
        detalle.setTotal(total);
        detalle.setSurtido(estado);
        detalle.setUltimaFechaActualizacion(Calendar.getInstance().getTime());
        detalle.setUltimoUsuarioActualizacion(idPedido);
        ActualizarDetalle detalleInsertar = new ActualizarDetalle();
        detalleInsertar.execute(detalle);
    }

    private static class ActualizarDetalle extends AsyncTask<DetallesPedidos, Void, Void>
    {

        @Override
        protected Void doInBackground(DetallesPedidos... detallesPedidos)
        {
            DetallesPedidosDao _dao = getDetallesPedidosDao();
            try
            {
                DetallesPedidosPk pk = new DetallesPedidosPk(detallesPedidos[0].getIdDetallePedido());
                _dao.update(pk, detallesPedidos[0]);
            }
            catch (Exception e)
            {

            }
            return null;
        }
    }

    public static DetallesPedidosDao getDetallesPedidosDao()
    {
        return DetallesPedidosDaoFactory.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(si){
            if(bandera && valideStatus()){
                metodosInternos = new MetodosInternos(getActivity());
                if(metodosInternos.conexionRed() )
                {
                    updatePedido();
                }
                else {
                    pedidoExitsDBI();
                }
            }
            if(bandera){
                metodosInternos = new MetodosInternos(getActivity());
                if(metodosInternos.conexionRed() )
                {
                    int x = 0;
                    for (ArticulosPedido ar: articulosPedidos){
                        uptadeExits(listDet.get(x).toString(),clavePedido,ar.getIdArticulo(),ar.getCantidad(),ar.getPrecio(),ar.getSubTotal(),ar.getIva(),ar.getTotal(), idUsuario, ar.getEstado());
                        x++;
                    }
                }
                else {
                    int x = 0;
                    HashMap<String, Float> cantidades = new HashMap();
                    List<String> unidades = new ArrayList<>();
                    List<String> nombres = new ArrayList<>();
                    for (ArticulosPedido ar: articulosPedidos){
                        uptadeExitsDBI(ids.get(x), listDet.get(x).toString(), clavePedido,ar.getIdArticulo(), ar.getNombre(), ar.getCantidad(),ar.getPrecio(),ar.getSubTotal(),ar.getIva(),ar.getTotal(), idUsuario, ar.getEstado());
                        if(!nombres.contains(ar.getNombre()))
                        {
                            nombres.add(ar.getNombre());
                            unidades.add(ar.getPresentacion());
                            cantidades.put(ar.getNombre(), ar.getCantidad());
                        }
                        else
                        {
                            Float n =  cantidades.get(ar.getNombre()) + ar.getCantidad();
                            cantidades.put(ar.getNombre(),n);
                        }
                        x++;
                    }

                    for(int y=0; y<nombres.size(); y++)
                    {
                        VwAbastecimientos_IDao vwAbastecimientos_iDao = daoSession.getVwAbastecimientos_IDao();
                        QueryBuilder<VwAbastecimientos_I> qb = vwAbastecimientos_iDao.queryBuilder();
                        qb.where(VwAbastecimientos_IDao.Properties.Estatus.eq(1), VwAbastecimientos_IDao.Properties.Nombre.eq(nombres.get(y)));
                        List<VwAbastecimientos_I> list = qb.list();

                        VwAbastecimientos_I abastecimiento = new VwAbastecimientos_I();
                        abastecimiento.setId(list.get(0).getId());
                        abastecimiento.setNombre(nombres.get(y));
                        abastecimiento.setUnidadPrimaria(unidades.get(y));
                        abastecimiento.setEstatus((short) 1);
                        Float n = cantidades.get(nombres.get(y));
                        abastecimiento.setCantidad(n);

                        VwAbastecimientos_IDao abastecimientos_iDao = daoSession.getVwAbastecimientos_IDao();
                        abastecimientos_iDao.update(abastecimiento);

                    }
                }
            }
        }
    }
    private void validacionCatidad(){
        try{
            if(editCantida.getText() != null){
                cantidaNum = Float.valueOf(editCantida.getText().toString());
            }else{
                cantidaNum = 0;
            }
        }catch (Exception e){
            cantidaNum = 0;
        }
        /*if (cantidaNum > exitArticul){
            cantidaNum = exitArticul;
        }*/
        editCantida.setText(String.valueOf(cantidaNum));
    }

    public void uptadeExitsDBI(long id, String idDet, String idPedido, String clave , String nombre, float cantidad, float precio, float subTotal, float iva, float total, String idUsuario, short estado)
    {
        VwDetallePedido_IDao detallePedidoIDao = daoSession.getVwDetallePedido_IDao();
        VwDetallePedido_I detalle = new VwDetallePedido_I();
        detalle.setId(id);
        detalle.setIdDetallePedido(idDet);
        detalle.setIdPedido(idPedido);
        detalle.setClaveArticulo(clave);
        detalle.setNombre(nombre);
        detalle.setCantidad(cantidad);
        detalle.setPrecio(precio);

        detalle.setSubtotal(subTotal);
        detalle.setIva(iva);
        detalle.setTotal(total);
        detalle.setUsuarioActualizacion(idPedido);
        detalle.setFechaActualizacion(Calendar.getInstance().getTime());
        detallePedidoIDao.update(detalle);
    }

    public void pedidoExitsDBI(){
        VwPedidos_IDao pedidos_iDao = daoSession.getVwPedidos_IDao();
        QueryBuilder<VwPedidos_I> qb = pedidos_iDao.queryBuilder();
        qb.where(VwPedidos_IDao.Properties.IdPedido.eq(clavePedido));
        List<VwPedidos_I> pedidos = qb.list();
            int x= 0;
            VwPedidos_I objetoPedidos = new VwPedidos_I();
            objetoPedidos.setId(pedidos.get(x).getId());
            objetoPedidos.setIdPedido(pedidos.get(x).getIdPedido());
            objetoPedidos.setIdUsuario(pedidos.get(x).getIdUsuario());
            objetoPedidos.setFolio(pedidos.get(x).getFolio());
            objetoPedidos.setNombre(pedidos.get(x).getNombre());
            objetoPedidos.setClaveCliente(pedidos.get(x).getClaveCliente());
            objetoPedidos.setFecha(pedidos.get(x).getFecha());
            objetoPedidos.setEstatus(pedidos.get(x).getEstatus());
            objetoPedidos.setSubtotal(getSub());
            objetoPedidos.setTotal(getTotal());
            objetoPedidos.setIva(getIva());
            objetoPedidos.setEstatus((short)3);
            objetoPedidos.setObservaciones(pedidos.get(x).getObservaciones());
            objetoPedidos.setUltimaFechaActualizacion(Calendar.getInstance().getTime());
            objetoPedidos.setUltimoUsuarioActualizacion(idUsuario);
        pedidos_iDao.update(objetoPedidos);
    }

    public float getTotal(){
        float total = 0;
        if (articulosPedidos != null){
            for(ArticulosPedido ar: articulosPedidos){
                if (ar.isStatus()){
                    total += ar.getTotal();
                }
            }
        }
        return total;
    }

    public  float getSub(){
        float sub = 0;
        if (articulosPedidos != null){
            for(ArticulosPedido ar: articulosPedidos){
                if (ar.isStatus()){
                    sub += ar.getSubTotal();
                }
            }
        }
        return sub;
    }
    public  float getIva(){
        float iva = 0;
        if (articulosPedidos != null){
            for(ArticulosPedido ar: articulosPedidos){
                if (ar.isStatus()){
                    iva += ar.getIva();
                }
            }
        }
        return iva;
    }

    public void updatePedido()
    {
        ConsultaPedido consultaPedido = new ConsultaPedido();
        PedidosDao pedidosDao = getPedidosDao();
        consultaPedido.execute(pedidosDao);
    }

    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
    }

    private static class ActualizarPedido extends AsyncTask<Pedidos, Void, Void>
    {
        @Override
        protected Void doInBackground(Pedidos... pedidos)
        {
            PedidosDao _dao = getPedidosDao();
            try
            {
                PedidosPk clave = new PedidosPk(pedidos[0].getIdPedido());
                _dao.update(clave, pedidos[0]);
            }
            catch (Exception e)
            {

            }
            return null;
        }
    }

    private class ConsultaPedido extends AsyncTask<PedidosDao, Void, Pedidos>
    {
        DelayedProgressDialog progressDialog = new DelayedProgressDialog();

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Pedidos doInBackground(PedidosDao... pedidosDaos)
        {
            try
            {
                 Pedidos[] pedidos = pedidosDaos[0].findWhereIdPedidoEquals(clavePedido);
                 for(Pedidos pedido: pedidos)
                 {
                     Pedidos objetoPedidos = new Pedidos();
                     objetoPedidos.setIdPedido(pedido.getIdPedido());
                     objetoPedidos.setIdUsuario(pedido.getIdUsuario());
                     objetoPedidos.setFolio(pedido.getFolio());
                     objetoPedidos.setClaveCliente(pedido.getClaveCliente());
                     objetoPedidos.setFecha(pedido.getFecha());
                     objetoPedidos.setEstatus((short)3);
                     objetoPedidos.setSubtotal(getSub());
                     objetoPedidos.setTotal(getTotal());
                     objetoPedidos.setIva(getIva());
                     objetoPedidos.setObservaciones(pedido.getObservaciones());
                     objetoPedidos.setUltimaFechaActualizacion(Calendar.getInstance().getTime());
                     objetoPedidos.setUltimoUsuarioActualizacion(idUsuario);
                     ActualizarPedido a = new ActualizarPedido();
                     a.execute(objetoPedidos);
                 }

            }
            catch (Exception e) { }
            return null;
        }
    }
    private boolean valideStatus(){
        Boolean ban = false;
        int x = 0;
        for(ArticulosPedido a:articulosPedidos ){
            if (a.getEstado() > 1){
                x++;
            }
        }
        if(x == articulosPedidos.size()){
            ban = true;
        }
        return ban;
    }
}
