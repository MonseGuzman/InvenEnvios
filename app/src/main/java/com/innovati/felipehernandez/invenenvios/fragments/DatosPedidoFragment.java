package com.innovati.felipehernandez.invenenvios.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.PedidoActivity;
import com.innovati.felipehernandez.invenenvios.adapters.ArticulosPedidosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.RecycleViewOnItemClickListener;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

public class DatosPedidoFragment extends Fragment implements View.OnClickListener{
    private static RecyclerView recyclerArticulos;
    private static Button btnReg;

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
        btnReg = v.findViewById(R.id.btnRegistrarPedido);
        btnReg.setVisibility(View.VISIBLE);
        btnReg.setOnClickListener(this);
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

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.btnRegistrarPedido){
            PedidoActivity.addPedidoDb();

            Snackbar.make(v, R.string.guardado, Snackbar.LENGTH_SHORT).show();
            this.getActivity().finish();
        }
    }

    public static void updateAdapter(){
        recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(PedidoActivity.articulosPedidoList, new RecycleViewOnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        updateAr(position);
                    }
                }));
    }

    public static void updateAr(int x){
        DatosPedidoFragment datosPedidoFragment = new DatosPedidoFragment();
        datosPedidoFragment.updateArticle(x);
    }

    public void updateArticle(int position){
        VwArticulos result[] = null;
        Bundle args;
        VwArticulosDao _dao = getVwArticulosDao();
         String  paramns[]={PedidoActivity.articulosPedidoList.get(position).getIdArticulo().toString()};
        try{
            Log.d("dsds----------",PedidoActivity.articulosPedidoList.get(position).getIdArticulo());
            result = _dao.findByDynamicWhere("CLAVE = ?" , paramns);
            Log.d("dsds-e---------",result[0].toString());
        }catch (Exception e){}
        ArticuloFragment fragment = new ArticuloFragment();

        //AGREGAR ARTICULOS A PERDIDO
        args = new Bundle();
        args.putString("clave", result[0].getClave());
        args.putString("nombre", result[0].getNombre());
        args.putString("activo", result[0].getActivo());
        args.putDouble("tiempoSurtido", result[0].getTiempoSurtido());
        args.putDouble("existencias", result[0].getExistenciaTotal());
        args.putDouble("precio", result[0].getPrecio1());
        args.putString("unidad", result[0].getUnidadPrimaria());
        fragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.CarritoListaFrameLayout, fragment).addToBackStack(null).commit();

    }
    public  VwArticulosDao getVwArticulosDao()
    {
        return VwArticulosDaoFactory.create();
    }

}
