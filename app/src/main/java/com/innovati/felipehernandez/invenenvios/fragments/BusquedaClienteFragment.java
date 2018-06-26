package com.innovati.felipehernandez.invenenvios.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.ClientesActivity;
import com.innovati.felipehernandez.invenenvios.adapters.ClientesAdaptador;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;

public class BusquedaClienteFragment extends Fragment
{
    private ImageButton BuscarImageButton;
    private EditText buscarEditText;
    private ListView datitosListView;

    MetodosInternos metodosInternos = new MetodosInternos(getContext());
    private ClientesAdaptador adaptador;
    VwClientes result[];

    public BusquedaClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_busqueda_cliente, container, false);

        inicializacion(v);

        BuscarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                filtar(v);
            }
        });
        return v;
    }

    private void inicializacion(View view)
    {
        BuscarImageButton = (ImageButton)view.findViewById(R.id.BuscarImageButton);
        buscarEditText = (EditText)view.findViewById(R.id.buscarEditText);
        datitosListView = (ListView)view.findViewById(R.id.datitosListView);
    }

    public void filtar(View v)
    {
        String nombre = buscarEditText.getText().toString();
        if(TextUtils.isEmpty(nombre))
        {
            //sin filtro = todos
            if(metodosInternos.conexionRed())
            {
                try
                {
                    VwClientesDao _dao = getVwClientesDao();
                    result = _dao.findAll();
                    adaptador = new ClientesAdaptador(getContext(),  R.layout.listview_cliente, result);
                    datitosListView.setAdapter(adaptador);

                }
                catch(Exception e)
                {
                    Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //bd interna
            }
        }
        else
        {
            //todos los que se parecen con el where
            if(metodosInternos.conexionRed())
            {
                try
                {
                    nombre = "%" + nombre;
                    nombre += "%";
                    VwClientesDao _dao = getVwClientesDao();
                    result = _dao.findWhereNombreEquals(nombre);
                    adaptador = new ClientesAdaptador(getContext(),  R.layout.listview_cliente, result);
                    datitosListView.setAdapter(adaptador);
                }
                catch(Exception e)
                {
                    Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //bd interna
            }
        }
    }

    public static VwClientesDao getVwClientesDao()
    {
        return VwClientesDaoFactory.create();
    }


}
