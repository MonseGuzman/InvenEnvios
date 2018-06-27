package com.innovati.felipehernandez.invenenvios.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ArticulosPedidosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.RecycleViewOnItemClickListener;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatosPedidoFragment extends Fragment {
    List<ArticulosPedido> articulosPedidoList;
    private RecyclerView recyclerArticulos;
    private String nombre, clave;
    private TextView tvClientePedido, tvFolio, tvPrecioPedido;
    ArticulosPedidosAdapter articulosPedidosAdapter;
    public DatosPedidoFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*recyclerArticulos.setLayoutManager(linearLayoutManager);
        tvClientePedido.setText(nombre);
        tvPrecioPedido.setText(getTotal());*/
        updateAdapter();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int  position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.RIGHT){
                    articulosPedidoList.remove(position);
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
        itemTouchHelper.attachToRecyclerView(recyclerArticulos);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos_pedido, container, false);
    }

    private void updateAdapter(){
        recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(articulosPedidoList, new RecycleViewOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        }));
        /*ArticulosPedidosAdapter adapter = new ArticulosPedidosAdapter(articulosPedidoList);
        recyclerArticulos.setAdapter(adapter);*/

    }

}
