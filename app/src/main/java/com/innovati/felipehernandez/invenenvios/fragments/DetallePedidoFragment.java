package com.innovati.felipehernandez.invenenvios.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innovati.felipehernandez.invenenvios.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePedidoFragment extends Fragment {


    public DetallePedidoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_pedido, container, false);
    }

}
