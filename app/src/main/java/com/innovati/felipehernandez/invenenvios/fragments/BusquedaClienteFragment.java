package com.innovati.felipehernandez.invenenvios.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.ClientesActivity;
import com.innovati.felipehernandez.invenenvios.activitys.EntregasActivity;
import com.innovati.felipehernandez.invenenvios.adapters.ClientesAdaptador;
import com.innovati.felipehernandez.invenenvios.adapters.EntregasRecycleViewAdaptador;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;

public class BusquedaClienteFragment extends Fragment
{
    private ImageButton BuscarImageButton;
    private EditText buscarEditText;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayour;

    MetodosInternos metodosInternos;
    VwClientes result[];
    public static String clave;
    public static String nombreC;

    public BusquedaClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_busqueda_cliente, container, false);

        inicializacion(v);

        mLayour = new GridLayoutManager(getContext(), 2);
        mLayour = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        BuscarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                filtar();
            }
        });
        return v;
    }

    private void inicializacion(View view)
    {
        BuscarImageButton = (ImageButton)view.findViewById(R.id.BusquedaImageButton);
        buscarEditText = (EditText)view.findViewById(R.id.buscadorEditText);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycleView);
    }

    public void filtar()
    {
        String nombre = buscarEditText.getText().toString();
        metodosInternos = new MetodosInternos(getActivity());
        if(TextUtils.isEmpty(nombre))
        {
            //sin filtro = todos
            if(metodosInternos.conexionRed())
            {
                try
                {
                    VwClientesDao _dao = getVwClientesDao();
                    result = _dao.findAll();

                    mAdapter = new EntregasRecycleViewAdaptador(result, R.layout.recycleview_clientes_item, new EntregasRecycleViewAdaptador.OnItemClickListener() {
                        @Override
                        public void onItemClick(VwClientes listita, int posicion) {
                            Log.i(result[posicion].getNombre(), "ERROR");

                            clave = result[posicion].getClave();
                            nombreC = result[posicion].getNombre();
                            EntregasActivity.ClienteEntTextView.setText("Cliente: "+nombreC);
                        }
                    });
                    //efectos en recycle view
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerView.setLayoutManager(mLayour);
                    mRecyclerView.setAdapter(mAdapter);
                }
                catch(Exception e)
                {
                    Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
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

                    mAdapter = new EntregasRecycleViewAdaptador(result, R.layout.recycleview_clientes_item, new EntregasRecycleViewAdaptador.OnItemClickListener() {
                        @Override
                        public void onItemClick(VwClientes listita, int posicion) {
                            Toast.makeText(getContext(), posicion, Toast.LENGTH_SHORT).show();
                        }
                    });

                    //efectos en recycle view
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerView.setLayoutManager(mLayour);
                    mRecyclerView.setAdapter(mAdapter);
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
