package com.innovati.felipehernandez.invenenvios.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ArticulosPedidosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.RecycleViewOnItemClickListener;
import com.innovati.felipehernandez.invenenvios.clases.dao.DetallesPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.util.ArrayList;
import java.util.List;

public class DetallePedidoFragment extends Fragment {
    static List<ArticulosPedido> articulosPedidos = new ArrayList<ArticulosPedido>();
    DetallesPedidos result[];
    String clavePedido = "";
    private static RecyclerView recyclerArticulos;
    private Button btnReg;
    public DetallePedidoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalle_pedido, container, false);
        recyclerArticulos = (RecyclerView) v.findViewById(R.id.listaDetalleRecycle);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerArticulos.setLayoutManager(manager);
        recyclerArticulos.setHasFixedSize(true);
        btnReg = v.findViewById(R.id.btnRegistrarPedido);
        btnReg.setVisibility(View.INVISIBLE);
        Bundle args = getArguments();
        clavePedido = args.getString("pedido", "");
        loadData();
        return v;
    }
    public void loadData(){
        try{
            DetallesPedidosDao detallesPedidos = getDetalleDao();

            result = detallesPedidos.findWhereIdPedidoEquals(clavePedido);
            for(DetallesPedidos pedidos: result){
                ArticulosPedido articulo = new ArticulosPedido();
                articulo.setIdArticulo(pedidos.getClaveArticulo());
                articulo.setPresentacion("persentacion");
                articulo.setCantidad(pedidos.getCantidad());
                articulo.setIva(pedidos.getIva());
                articulo.setPrecio(pedidos.getPrecio());
                articulo.setTotal(pedidos.getTotal());
                articulo.setSubTotal(pedidos.getSubtotal());
                articulo.setStatus(true);
                articulosPedidos.add(articulo);
            }

            updateAdapter();

        }catch (Exception e){}
    }
    public static DetallesPedidosDao getDetalleDao()
    {
        return DetallesPedidosDaoFactory.create();
    }

    public static void updateAdapter(){
        recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(articulosPedidos, new RecycleViewOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        }));
    }
}
