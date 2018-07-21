package com.innovati.felipehernandez.invenenvios.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.ArticuloActivity;
import com.innovati.felipehernandez.invenenvios.activitys.PedidoActivity;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

public class ArticuloFragment extends Fragment implements View.OnClickListener
{
    private LinearLayout PerdidoslinearLayout;
    private TextView claveTextView_A;
    private EditText nombreEditText_A;
    private CheckBox estatusCheckbox_A;
    private EditText tiempoSurtidoEditText_A;
    private EditText unidadEditText_A;
    private EditText precioEditText_A;
    private EditText existenciasEditText_A;
    private Button MenosButton_A;
    private Button MasButton_A;
    private Button addArticuloList;
    private EditText cantidadEditText_A;

    String fragmento;
    String clave;
    String nombre;
    String activo;
    Double tiempo;
    Double existencias;
    Double precio;
    String unidad;
    float cantidadPedido = 0;

    FloatingActionButton AgregarFAB_A;

    public ArticuloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_articulo, container, false);

        inicializar(v);

        Bundle args = getArguments();
        fragmento = args.getString("fragmento", "");
        clave = args.getString("clave", "");
        nombre = args.getString("nombre", "");
        activo = args.getString("activo", "");
        tiempo = args.getDouble("tiempoSurtido", 0);
        existencias = args.getDouble("existencias", 0);
        precio = args.getDouble("precio", 0);
        unidad = args.getString("unidad", "");

        claveTextView_A.setText(clave);
        nombreEditText_A.setText(nombre);
        if(activo.equals("S"))
            estatusCheckbox_A.setChecked(true);
        else
            estatusCheckbox_A.setChecked(false);
        tiempoSurtidoEditText_A.setText(tiempo.toString());
        existenciasEditText_A.setText(existencias.toString());
        precioEditText_A.setText(precio.toString());
        unidadEditText_A.setText(unidad);

        switch (fragmento)
        {
            case "Agregar":
                PerdidoslinearLayout.setVisibility(View.INVISIBLE);
                break;
            case "Detalles":
                PerdidoslinearLayout.setVisibility(View.VISIBLE);
                break;
            case "NuevoArticulo":
                PerdidoslinearLayout.setVisibility(View.INVISIBLE);
                limpiar();
                break;
        }

        MasButton_A.setOnClickListener(this);
        MenosButton_A.setOnClickListener(this);
        addArticuloList.setOnClickListener(this);
        validadCantidad();
        return v;
    }

    private void inicializar(View v)
    {
        PerdidoslinearLayout = (LinearLayout)v.findViewById(R.id.PerdidoslinearLayout);
        claveTextView_A = v.findViewById(R.id.ClaveTextView_A);
        nombreEditText_A = v.findViewById(R.id.NombreEditText_A);
        estatusCheckbox_A = v.findViewById(R.id.EstatusCheckbox_A);
        tiempoSurtidoEditText_A = v.findViewById(R.id.TiempoSurtidoEditText_A);
        unidadEditText_A = v.findViewById(R.id.UnidadEditText_A);
        existenciasEditText_A = v.findViewById(R.id.ExistenciaEditText_A);
        precioEditText_A = v.findViewById(R.id.PrecioEditText_A);

        MenosButton_A =  (Button) v.findViewById(R.id.MenosButton_A);
        MasButton_A = (Button) v.findViewById(R.id.MasButton_A);
        cantidadEditText_A = (EditText) v.findViewById(R.id.cantidadEditText_A);
        addArticuloList = v.findViewById(R.id.addArticuloList);
    }

    private void limpiar()
    {
        //LIMPIA
        claveTextView_A.setText("");
        nombreEditText_A.setText("");
        estatusCheckbox_A.setChecked(false);
        tiempoSurtidoEditText_A.setText("");
        existenciasEditText_A.setText("");
        precioEditText_A.setText("");
        unidadEditText_A.setText("");

        //INHABILITA
        nombreEditText_A.setFocusable(true);

        claveTextView_A.setEnabled(true);
        nombreEditText_A.setEnabled(true);
        estatusCheckbox_A.setEnabled(true);
        tiempoSurtidoEditText_A.setEnabled(true);
        existenciasEditText_A.setEnabled(true);
        precioEditText_A.setEnabled(true);
        unidadEditText_A.setEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try{
            BusquedaArticulosFragment.blockeo();
        }catch (Exception e){

        }
        try{
            ArticuloActivity.blockeo();
        }catch (Exception e){

        }
    }

    @Override
    public void onClick(View v)
    {
        cantidadEditText_A.setText(String.valueOf(cantidadPedido));
        cantidadPedido = Float.valueOf(cantidadEditText_A.getText().toString());
        switch (v.getId())
        {
            case R.id.MasButton_A:
                if(cantidadPedido != 0){
                    cantidadPedido -=1;
                }
                break;
            case R.id.MenosButton_A:
                float exit = Float.valueOf(existencias.toString());
                if(cantidadPedido < exit){
                    cantidadPedido +=1;
                }
                break;
            case R.id.addArticuloList:
                addArticuloList();
                break;
        }
        cantidadEditText_A.setText(String.valueOf(cantidadPedido));
    }
    public void validadCantidad(){
        try{
            boolean ban = false;
            int position = -1;
            for(ArticulosPedido ar: PedidoActivity.articulosPedidoList){
                position +=1;
                if (ar.getIdArticulo() == clave){
                    ban = true;
                    break;
                }
            }
            if (ban){
                cantidadPedido = PedidoActivity.articulosPedidoList.get(position).getCantidad();
            }else{
                if(existencias != 0){
                    cantidadPedido = 1;
                }else{
                    cantidadPedido = 0;
                }
            }
            cantidadEditText_A.setText(String.valueOf(cantidadPedido));
        }catch (Exception e){

        }
        cantidadEditText_A.setText(String.valueOf(cantidadPedido));
    }

    public void addArticuloList(){
        float precioAux = Float.valueOf(precio.toString());
        ArticulosPedido articulosPedido = new ArticulosPedido();
        articulosPedido.setIdArticulo(clave);
        articulosPedido.setNombre(nombre);
        articulosPedido.setPrecio(precioAux);
        articulosPedido.setCantidad(cantidadPedido);
        articulosPedido.setSubTotal((float) (precioAux*cantidadPedido));
        articulosPedido.setPresentacion(unidad);
        articulosPedido.setStatus(true);
        float ivaAux = (float) (articulosPedido.getTotal()*0.16);
        articulosPedido.setIva(ivaAux);
        ivaAux = Float.valueOf(existencias.toString());
        articulosPedido.setExits(ivaAux);
        articulosPedido.setTotal(articulosPedido.getSubTotal()+articulosPedido.getIva());
        PedidoActivity.addArticulo(articulosPedido);
        getActivity().onBackPressed();
    }
}
