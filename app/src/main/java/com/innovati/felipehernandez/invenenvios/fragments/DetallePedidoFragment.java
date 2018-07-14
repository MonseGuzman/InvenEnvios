package com.innovati.felipehernandez.invenenvios.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.PedidoActivity;
import com.innovati.felipehernandez.invenenvios.adapters.ArticulosPedidosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.RecycleViewOnItemClickListener;
import com.innovati.felipehernandez.invenenvios.clases.dao.DetallesPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwDetallePedidoDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwDetallePedido;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwDetallePedidoDaoFactory;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.util.ArrayList;
import java.util.List;

public class DetallePedidoFragment extends Fragment implements View.OnClickListener
{
    private List<ArticulosPedido> articulosPedidos = new ArrayList<ArticulosPedido>();
    VwDetallePedido result[];
    String clavePedido = "";
    private  RecyclerView recyclerArticulos;
    private Button btnReg;
    private Button btnMas, btnMeno, btnAceptar, btnCancelar;
    private  EditText editCantida;
    private  ConstraintLayout datosEditArticle;
    private static float exitArticul = 0, cantidaNum;
    private static int positionList;
    static List<ArticulosPedido> articuloEdit = new ArrayList<ArticulosPedido>();
    public DetallePedidoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalle_pedido, container, false);

        inicializar(v);
        btnMas.setOnClickListener(this);
        btnMeno.setOnClickListener(this);
        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        editCantida.setOnClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerArticulos.setLayoutManager(manager);
        recyclerArticulos.setHasFixedSize(true);
        //btnReg.setVisibility(View.INVISIBLE);  ARREGLAR DESPUÉS
        Bundle args = getArguments();
        clavePedido = args.getString("pedido", "");
        loadData();
        return v;
    }

    private void inicializar(View v)
    {
        recyclerArticulos = (RecyclerView) v.findViewById(R.id.listaDetalleRecycle);
        datosEditArticle = v.findViewById(R.id.includeEditoArticleDetalle);
        btnReg = (Button)v.findViewById(R.id.btnRegistrarPedido);
        btnMas = v.findViewById(R.id.MasButton_AEdit);
        btnMeno = v.findViewById(R.id.MenosButton_AEdit);
        btnAceptar = v.findViewById(R.id.editArticuloListCancelar);
        btnCancelar = v.findViewById(R.id.editArticuloListAceptar);
        editCantida = v.findViewById(R.id.cantidadEditText_AEdit);
        datosEditArticle.setVisibility(View.INVISIBLE);
    }

    public void loadData()
    {

            VwDetallePedidoDao detallesPedidos = getVwDetallePedidoDao();
            Consulta c = new Consulta();
            c.execute(detallesPedidos);

    }
    public static VwDetallePedidoDao getVwDetallePedidoDao()
    {
        return VwDetallePedidoDaoFactory.create();
    }

    public void updateAdapter(){
        recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(articulosPedidos, new RecycleViewOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                positionList =position;
                updateAr(position);
            }
        }));
    }
    public  void updateAr(int x){
        Log.d("test--------------:",""+x);
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
        exitArticul = articuloEdit.get(0).getExits();
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
        cantidaNum = Float.valueOf(editCantida.getText().toString());
        switch (view.getId())
        {
            case R.id.MasButton_AEdit:
                if(cantidaNum != 0){
                    cantidaNum -=1;
                }
                break;
            case R.id.MenosButton_AEdit:
                float exit = exitArticul;
                if(cantidaNum < exit){
                    cantidaNum +=1;
                }
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

        @Override
        protected VwDetallePedido doInBackground(VwDetallePedidoDao... vwDetallePedidoDaos)
        {
          try
          {
              result = vwDetallePedidoDaos[0].findWhereIdPedidoEquals(clavePedido);

          }
          catch (Exception e)
          {

          }
            return null;
        }

        @Override
        protected void onPostExecute(VwDetallePedido vwDetallePedido)
        {
            super.onPostExecute(vwDetallePedido);

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
                articulosPedidos.add(articulo);
            }
            updateAdapter();

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
            float ivaAux = (float) (articulosPedido.getTotal()*0.16);
            articulosPedido.setIva(ivaAux);
            ivaAux = articulosPedidos.get(positionList).getExits();
            articulosPedido.setExits(ivaAux);
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
     public float calTotal(){
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
}
