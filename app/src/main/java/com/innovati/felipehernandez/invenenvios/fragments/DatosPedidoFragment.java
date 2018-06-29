package com.innovati.felipehernandez.invenenvios.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.EntregasActivity;
import com.innovati.felipehernandez.invenenvios.adapters.ArticulosPedidosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.RecycleViewOnItemClickListener;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;



/**
 * A simple {@link Fragment} subclass.
 */
public class DatosPedidoFragment extends Fragment {
    private RecyclerView recyclerArticulos;

    public DatosPedidoFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_datos_pedido, container, false);
        recyclerArticulos = (RecyclerView) v.findViewById(R.id.listaCarritoRecycle);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerArticulos.setLayoutManager(manager);
        recyclerArticulos.setHasFixedSize(true);
        updateAdapter();

        /*ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
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
                updateAdapter();
            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerArticulos);*/

        // Inflate the layout for this fragment
        return v;
    }


    private void updateAdapter(){
        recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(EntregasActivity.articulosPedidoList, new RecycleViewOnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                    Log.d("si------","si entro");
                    }
                }));
    }

}
