package com.innovati.felipehernandez.invenenvios.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.innovati.felipehernandez.invenenvios.R;

public class ArticuloFragment extends Fragment
{
    private LinearLayout PerdidoslinearLayout;

    String fragmento;


    public ArticuloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_articulo, container, false);

        inicializar(v);

        Bundle args = getArguments();
        fragmento = args.getString("fragmento", "");

        if(fragmento.equals("Agregar"))
        {
            //agregar al carrito
            PerdidoslinearLayout.setVisibility(View.INVISIBLE);
        }
        else if(fragmento.equals("Detalles"))
        {
            PerdidoslinearLayout.setVisibility(View.VISIBLE);
        }

        return v;
    }

    private void inicializar(View v)
    {
        PerdidoslinearLayout = (LinearLayout)v.findViewById(R.id.PerdidoslinearLayout);
    }

}
