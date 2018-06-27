package com.innovati.felipehernandez.invenenvios.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.ArticuloActivity;
import com.innovati.felipehernandez.invenenvios.adapters.ArticuloAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaArticulosFragment extends Fragment
{

    private ArticuloAdapter adaptador;
    private static ListView datitosListView;
    private static EditText buscarEditText;
    private static ImageButton BuscarImageButton;
    private FloatingActionButton AgregarFAB_A;
    ArticuloFragment fragment = new ArticuloFragment();
    VwArticulos result[];
    MetodosInternos metodosInternos;
    String actividad, clave, nombre;
    Bundle args;
    private static boolean ban = false;
    static String fragmento = "";

    public BusquedaArticulosFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_busqueda_articulos, container, false);

        // Inflate the layout for this fragment
        inicializacion(v);

        datitosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ArticuloFragment fragment = new ArticuloFragment();

                    //AGREGAR ARTICULOS A PERDIDO
                    args = new Bundle();
                    args.putString("clave", result[position].getClave());
                    args.putString("nombre", result[position].getNombre());
                    args.putString("activo", result[position].getActivo());
                    args.putDouble("tiempoSurtido", result[position].getTiempoSurtido());
                    args.putDouble("existencias", result[position].getExistenciaTotal());
                    args.putDouble("precio", result[position].getPrecio1());
                    args.putString("unidad", result[position].getUnidadPrimaria());
                    fragment.setArguments(args);
                    AgregarFAB_A.setImageResource(R.drawable.ic_agregar_carrito);
                    disableControl();


            }
        });

        BuscarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtar(v);
            }
        });




        return v;
    }

    private void inicializacion(View view)
    {
        datitosListView = (ListView)view.findViewById(R.id.datitosListView);
        buscarEditText = (EditText) view.findViewById(R.id.buscarEditText);
        BuscarImageButton = (ImageButton) view.findViewById(R.id.BuscarImageButton);
        AgregarFAB_A = (FloatingActionButton) view.findViewById(R.id.AgregarFAB_A);
    }


    public static VwArticulosDao getVwArticulosDao()
    {
        return VwArticulosDaoFactory.create();
    }

    public void filtar(View v)
    {
        metodosInternos = new MetodosInternos(getActivity());
        String nombre = buscarEditText.getText().toString();
        if(TextUtils.isEmpty(nombre))
        {
            if(metodosInternos.conexionRed())
            {
                //sin filtro = todos
                try
                {
                    VwArticulosDao _dao = getVwArticulosDao();
                    result = _dao.findAll();
                    adaptador = new ArticuloAdapter(getActivity(),  R.layout.listview_articulos, result);
                    datitosListView.setAdapter(adaptador);
                }
                catch(Exception e)
                {
                    Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //código para buscar en la bd interna
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
                    VwArticulosDao _dao = getVwArticulosDao();
                    result = _dao.findWhereNombreEquals(nombre);
                    adaptador = new ArticuloAdapter(getActivity(),  R.layout.listview_articulos, result);
                    datitosListView.setAdapter(adaptador);
                }
                catch(Exception e)
                {
                    Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //código para buscar en la bd interna
            }
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Toast.makeText(getActivity(), "homeeeeeeee", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void disableLista(String s){
        ArticuloActivity.disableListaPedido(s);
    }
    public static void disableListaPedido(String s){
        datitosListView.setEnabled(true);
        buscarEditText.setEnabled(true);
        BuscarImageButton.setEnabled(true);
        fragmento = s;
    }
    private void disableControl(){
        datitosListView.setEnabled(false);
        buscarEditText.setEnabled(false);
        BuscarImageButton.setEnabled(false);
    }

    /*public static void addArticulo(ArticulosPedido a){
        if (ban){
            ArticuloActivity.addArticuloList(a);
        }

    }
    public static void addArticuloList(ArticulosPedido a){
        EntregasActivity.articulosPedidoList.add(a);
    }*/

}
