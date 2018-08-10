package com.innovati.felipehernandez.invenenvios.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ArticuloAdapter;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.VwArticulos_I;
import com.innovati.felipehernandez.invenenvios.database.VwArticulos_IDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


public class BusquedaArticulosFragment extends Fragment
{

    private ArticuloAdapter adaptador;
    private static ListView datitosListView;
    private static EditText buscarEditText;
    private static Button BuscarButton;
    private FloatingActionButton AgregarFAB_A;

    VwArticulos result[];
    MetodosInternos metodosInternos;
    Bundle args;
    private static RelativeLayout ArticuloBlock;
    private DaoSession daoSession;

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
        daoSession = ((MyApp) getActivity().getApplication()).getDaoSession();
        datitosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(result[position].getExistenciaTotal() > 0)
                {
                    ArticuloFragment fragment = new ArticuloFragment();
                    ArticuloBlock.setVisibility(View.INVISIBLE);
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
                }else{
                    Snackbar.make(getView(), "No Existe Existencias", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        BuscarButton.setOnClickListener(new View.OnClickListener() {
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
        BuscarButton = (Button) view.findViewById(R.id.BuscarButton);
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
            else//código para buscar en la bd interna
                internaBD();
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
            else//código para buscar en la bd interna
                internaBD();
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
        ArticuloBlock.setVisibility(View.VISIBLE);
    }

    private void internaBD()
    {
        String nombre = buscarEditText.getText().toString();
        VwArticulos_IDao vwArticulos_iDao = daoSession.getVwArticulos_IDao();
        List<VwArticulos_I> articulos;

        //si esta vacio
        if(TextUtils.isEmpty(nombre))
        {
            QueryBuilder<VwArticulos_I> qb = vwArticulos_iDao.queryBuilder();
            articulos = qb.list();
            result = new VwArticulos[articulos.size()];
        }
        else
        {
            nombre = "%" + nombre;
            nombre += "%";

            QueryBuilder<VwArticulos_I> qb = vwArticulos_iDao.queryBuilder();
            qb.where(VwArticulos_IDao.Properties.Nombre.like(nombre));

            articulos = qb.list();
            result = new VwArticulos[articulos.size()];
        }

        for(int x=0; x<articulos.size(); x++)
        {
            VwArticulos vwArticulos = new VwArticulos();

            vwArticulos.setClave(articulos.get(x).getClave());
            vwArticulos.setNombre(articulos.get(x).getNombre());
            vwArticulos.setActivo(articulos.get(x).getActivo());
            vwArticulos.setTiempoSurtido(Double.valueOf(articulos.get(x).getTiempoSurtido().toString()));
            vwArticulos.setExistenciaTotal(articulos.get(x).getExistenciaTotal());
            vwArticulos.setPrecio1(articulos.get(x).getPrecio1());
            vwArticulos.setUnidadPrimaria(articulos.get(x).getUnidadPrimaria());

            result[x] = vwArticulos;
        }
        adaptador = new ArticuloAdapter(getActivity(),  R.layout.listview_articulos, result);
        datitosListView.setAdapter(adaptador);
    }

}
