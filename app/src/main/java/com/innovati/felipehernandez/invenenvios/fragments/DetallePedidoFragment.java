package com.innovati.felipehernandez.invenenvios.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.innovati.felipehernandez.invenenvios.clases.dao.VwDetallePedidoDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwDetallePedido;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwDetallePedidoDaoFactory;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.util.ArrayList;
import java.util.List;

public class DetallePedidoFragment extends Fragment
{
    static List<ArticulosPedido> articulosPedidos = new ArrayList<ArticulosPedido>();
    VwDetallePedido result[];
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

        inicializar(v);

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
        btnReg = (Button)v.findViewById(R.id.btnRegistrarPedido);
    }

    public void loadData(){
        try
        {
            articulosPedidos.clear();

            VwDetallePedidoDao detallesPedidos = getVwDetallePedidoDao();
            result = detallesPedidos.findWhereIdPedidoEquals(clavePedido);

            for(VwDetallePedido pedidos: result){
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

        }catch (Exception e){}
    }
    public static VwDetallePedidoDao getVwDetallePedidoDao()
    {
        return VwDetallePedidoDaoFactory.create();
    }

    public static void updateAdapter(){
        recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(articulosPedidos, new RecycleViewOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        }));
    }
}
