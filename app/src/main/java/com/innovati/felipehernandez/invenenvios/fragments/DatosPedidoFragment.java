package com.innovati.felipehernandez.invenenvios.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.EntregasActivity;
import com.innovati.felipehernandez.invenenvios.adapters.ArticulosPedidosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.RecycleViewOnItemClickListener;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatosPedidoFragment extends Fragment {
    private RecyclerView recyclerArticulos;
    private RecyclerView.Adapter adapterArticulos;
    private RecyclerView.LayoutManager mLayour;
    public DatosPedidoFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vD = inflater.inflate(R.layout.fragment_datos_pedido, container, false);
        recyclerArticulos = vD.findViewById(R.id.listaCarritoRecycle);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerArticulos.setLayoutManager(manager);
        recyclerArticulos.setHasFixedSize(true);
        updateAdapter();
        /*mLayour = new GridLayoutManager(getContext(), 2);
        mLayour = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);*/

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int  position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.RIGHT){
                    EntregasActivity.articulosPedidoList.remove(position);
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
                //updateAdapter();
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerArticulos);

        // Inflate the layout for this fragment
        return vD;
    }


    private void updateAdapter(){
        ArticulosPedido articulosPedido = new ArticulosPedido();
        articulosPedido.setIdArticulo("dsfsdf");
        articulosPedido.setNombre("dsfsdf");
        articulosPedido.setPrecio(2);
        articulosPedido.setCantidad(2);
        articulosPedido.setSubTotal((2*2));
        articulosPedido.setPresentacion("kilos");
        float ivaAux = (float) (articulosPedido.getTotal()*0.16);
        articulosPedido.setIva(ivaAux);
        articulosPedido.setTotal(articulosPedido.getSubTotal()+articulosPedido.getIva());
        EntregasActivity.articulosPedidoList.add(articulosPedido);
                recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(EntregasActivity.articulosPedidoList, new RecycleViewOnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                    Log.d("si------","si entro");
                    }
                }));
                /*adapterArticulos = new ArticulosPedidosAdapter(EntregasActivity.articulosPedidoList, new RecycleViewOnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Log.d("si------","si entro");
                    }
                });
                recyclerArticulos.setAdapter(adapterArticulos );*/
    }

}
