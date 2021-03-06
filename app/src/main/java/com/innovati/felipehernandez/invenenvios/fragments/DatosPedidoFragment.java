package com.innovati.felipehernandez.invenenvios.fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.util.ArrayList;
import java.util.List;

public class DatosPedidoFragment extends Fragment implements View.OnClickListener
{
    private static RecyclerView recyclerArticulos;
    private Button btnReg, btnMas, btnMeno, btnAceptar, btnCancelar;
    private static EditText editCantida;
    private static ConstraintLayout datosEditArticle;
    private static float cantidaNum;/*exitArticul = 0,*/
    private static int positionList;
    static List<ArticulosPedido> articuloEdit = new ArrayList<ArticulosPedido>();

    public DatosPedidoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_datos_pedido, container, false);
        inicialize(v);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerArticulos.setLayoutManager(manager);
        recyclerArticulos.setHasFixedSize(true);
        updateAdapter();
        btnReg.setVisibility(View.VISIBLE);
        btnReg.setOnClickListener(this);
        btnMas.setOnClickListener(this);
        btnMeno.setOnClickListener(this);
        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        editCantida.setOnClickListener(this);
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int  position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.RIGHT){
                    PedidoActivity.articulosPedidoList.remove(position);
                }else if (direction == ItemTouchHelper.LEFT){
                    ArticulosPedidosAdapter adapter = (ArticulosPedidosAdapter) recyclerArticulos.getAdapter();
                    ArticulosPedido articulosPedido = new ArticulosPedido();
                    articulosPedido = adapter.articulosPedidos.get(position);
                    if(articulosPedido.isStatus()){
                        articulosPedido.setStatus(false);
                    }else{
                        articulosPedido.setStatus(true);
                    }
                    adapter.articulosPedidos.set(position,articulosPedido);
                }
                updateAdapter();
                PedidoActivity.calTotal();
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerArticulos);
        editCantida.getOnFocusChangeListener();
        return v;
    }

    private void inicialize(View v){
        recyclerArticulos = (RecyclerView) v.findViewById(R.id.listaCarritoRecycle);
        datosEditArticle = v.findViewById(R.id.includeEditoArticlePedido);
        btnReg = v.findViewById(R.id.btnRegistrarPedido);
        btnMas = v.findViewById(R.id.MasButton_AEdit);
        btnMeno = v.findViewById(R.id.MenosButton_AEdit);
        btnAceptar = v.findViewById(R.id.editArticuloListCancelar);
        btnCancelar = v.findViewById(R.id.editArticuloListAceptar);
        editCantida = v.findViewById(R.id.cantidadEditText_AEdit);
        datosEditArticle.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v)
    {
        validacionCatidad();
        if(v.getId() == R.id.btnRegistrarPedido)
        {
            if(!PedidoActivity.articulosPedidoList.isEmpty())
            {
                String mensaje;
                //falta consulta del folio y agregarlo a este string
                mensaje = "¡Pedido guardado!" + " con Folio *";

                PedidoActivity.addPedidoDb();
                datosEditArticle.setVisibility(View.INVISIBLE);

                Snackbar.make(v, mensaje, Snackbar.LENGTH_INDEFINITE).setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        PedidoActivity.limpiar();
                    }
                })
                        .setActionTextColor(getResources().getColor(R.color.secondaryColor, getContext().getTheme()))
                        .show();
            }else
                Snackbar.make(v, "Faltan añadir articulos", Snackbar.LENGTH_SHORT).show();
            //this.getActivity().finish();
        }else{

            switch (v.getId())
            {
                case R.id.MasButton_AEdit:
                    if(cantidaNum != 0){
                        cantidaNum -=1;
                    }
                    break;
                case R.id.MenosButton_AEdit:
                    //float exit = exitArticul;
                    /*if(cantidaNum < exit){
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
        PedidoActivity.calTotal();
    }

    public static void updateAdapter(){
        recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(PedidoActivity.articulosPedidoList, new RecycleViewOnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Log.d("punto post---:", String.valueOf(position));
                        positionList =position;
                            updateAr(position);

                    }
                }));
    }

    public static void updateAr(int x){
        updateArticle(x);
            try{

            }catch (Exception e){
                //updateArticle(0);
            }
    }

    public static   void updateArticle(int position){
        try{
            articuloEdit.set(0,PedidoActivity.articulosPedidoList.get(position));
        }catch (Exception e){
            articuloEdit.add(PedidoActivity.articulosPedidoList.get(position));
        }
        updateAdapterArt(articuloEdit);
        //exitArticul = articuloEdit.get(0).getExits();
       editCantida.setText(String.valueOf(PedidoActivity.articulosPedidoList.get(position).getCantidad()));
        datosEditArticle.setVisibility(View.VISIBLE);
    }
    public static void updateAdapterArt(List<ArticulosPedido> articuloEdit){
        recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(articuloEdit, new RecycleViewOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                updateAr(position);
            }
        }));
    }
    public static VwArticulosDao getVwArticulosDao()
    {
        return VwArticulosDaoFactory.create();
    }

    public void updateArticleList(boolean ban){
        if (ban){
            float precioAux = Float.valueOf(PedidoActivity.articulosPedidoList.get(positionList).getPrecio());
            ArticulosPedido articulosPedido = new ArticulosPedido();
            articulosPedido.setIdArticulo(PedidoActivity.articulosPedidoList.get(positionList).getIdArticulo());
            articulosPedido.setNombre(PedidoActivity.articulosPedidoList.get(positionList).getNombre());
            articulosPedido.setPrecio(precioAux);
            articulosPedido.setCantidad(cantidaNum);
            articulosPedido.setSubTotal((float) (precioAux*cantidaNum));
            articulosPedido.setPresentacion(PedidoActivity.articulosPedidoList.get(positionList).getPresentacion());
            articulosPedido.setStatus(true);
            float ivaAux = (float) (articulosPedido.getTotal()*0.16);
            articulosPedido.setIva(ivaAux);
            //ivaAux = PedidoActivity.articulosPedidoList.get(positionList).getExits();
            //articulosPedido.setExits(ivaAux);
            articulosPedido.setTotal(articulosPedido.getSubTotal()+articulosPedido.getIva());
            PedidoActivity.addArticulo(articulosPedido);
        }
        datosEditArticle.setVisibility(View.INVISIBLE);
        updateAdapter();
        PedidoActivity.calTotal();
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

}
