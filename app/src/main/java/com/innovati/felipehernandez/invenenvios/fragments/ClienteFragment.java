package com.innovati.felipehernandez.invenenvios.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.innovati.felipehernandez.invenenvios.R;

public class ClienteFragment extends Fragment
{
    private EditText nombreClienteEditText_C;
    private EditText rfcEditText_C;
    private EditText calleEditText_C;
    private EditText numeroExteriorEditText_C;
    private EditText numeroInteriorEditText_C;
    private EditText coloniaEditText_C;
    private EditText telefonoEditText_C;

    private String nombre, rfc, calle, numeroExterior, numeroInterior, colonia, telefono;

    public ClienteFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_cliente, container, false);
        inicializar(v);
        Bundle args = getArguments();
        nombre = args.getString("nombre", "");
        rfc = args.getString("rfc", "");
        calle = args.getString("calle", "");
        numeroExterior = args.getString("numeroExterior", "");
        numeroInterior = args.getString("numeroInterior", "");
        colonia = args.getString("colonia", "");
        telefono = args.getString("telefono", "");

        nombreClienteEditText_C.setText(nombre);
        rfcEditText_C.setText(rfc);
        calleEditText_C.setText(calle);
        numeroExteriorEditText_C.setText(numeroExterior);
        numeroInteriorEditText_C.setText(numeroInterior);
        coloniaEditText_C.setText(colonia);
        telefonoEditText_C.setText(telefono);

        return v;
    }


    private void inicializar(View v)
    {
        nombreClienteEditText_C = v.findViewById(R.id.NombreClienteEditText_C);
        rfcEditText_C = v.findViewById(R.id.RFCEditText_C);
        calleEditText_C = v.findViewById(R.id.CalleEditText_C);
        numeroExteriorEditText_C = v.findViewById(R.id.NumeroExteriorEditText_C);
        numeroInteriorEditText_C = v.findViewById(R.id.NumeroInteriorEditText_C);
        coloniaEditText_C = v.findViewById(R.id.ColoniaEditText_C);
        telefonoEditText_C = v.findViewById(R.id.TelefonoEditText_C);
    }

}
