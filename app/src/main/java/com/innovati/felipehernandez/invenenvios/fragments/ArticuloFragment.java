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

        //INAVILITA
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
        ArticuloActivity.disableLista("");
        ArticulosPedido articulosPedido = new ArticulosPedido();
        articulosPedido.setIdArticulo(clave);
        articulosPedido.setNombre(nombre);
        articulosPedido.setPrecio(precio);
        articulosPedido.setCantidad(cantidadPedido);
        articulosPedido.setSubTotal((precio*cantidadPedido));
        articulosPedido.setIva(articulosPedido.getSubTotal()*0.16);
        articulosPedido.setTotal(articulosPedido.getSubTotal()+articulosPedido.getIva());
        ArticuloActivity.addArticulo(articulosPedido);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.MasButton_A:
                cantidadPedido -=1;
                break;
            case R.id.MenosButton_A:
                cantidadPedido +=1;
                break;
        }
        cantidadEditText_A.setText(String.valueOf(cantidadPedido));
    }
}
