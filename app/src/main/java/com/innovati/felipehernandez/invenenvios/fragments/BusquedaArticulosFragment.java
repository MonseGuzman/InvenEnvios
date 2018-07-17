package com.innovati.felipehernandez.invenenvios.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.ArticuloActivity;
import com.innovati.felipehernandez.invenenvios.adapters.ArticuloAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;


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
    static String fragmento = "";
    private static RelativeLayout ArticuloBlock;
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
                ArticuloBlock.setEnabled(false);
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
                    getFragmentManager().beginTransaction().replace(R.id.ArticuloFrameLayout, fragment).addToBackStack(null).commit();



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
        ArticuloBlock = (RelativeLayout)view.findViewById(R.id.ArticuloBlock);
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
                VwArticulosDao _dao = getVwArticulosDao();
                ConsultaArticulos c = new ConsultaArticulos(nombre);
                c.execute(_dao);

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
                    ConsultaArticulos c = new ConsultaArticulos(nombre);
                    c.execute(_dao);
                }
                catch(Exception e)
                {
                    //Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
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
                //Toast.makeText(getActivity(), "homeeeeeeee", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class ConsultaArticulos extends AsyncTask<VwArticulosDao, VwArticulos, VwArticulos[]>
    {
        String nombre;

        public ConsultaArticulos(String nombre)
        {
            this.nombre = nombre;
        }



        @Override
        protected VwArticulos[] doInBackground(VwArticulosDao... vwArticulosDaos)
        {
            try
            {
                if(nombre.equals(""))
                    result = vwArticulosDaos[0].findAll();
                else
                    result = vwArticulosDaos[0].findWhereNombreEquals(nombre);
            }
            catch (Exception e){

            }
            return result;
        }

        @Override
        protected void onPostExecute(VwArticulos[] vwArticulos)
        {
            super.onPostExecute(vwArticulos);
            adaptador = new ArticuloAdapter(getActivity(),  R.layout.listview_articulos, result);
            datitosListView.setAdapter(adaptador);
        }
    }
    public static void blockeo(){
        ArticuloBlock.setEnabled(true);
    }

}
