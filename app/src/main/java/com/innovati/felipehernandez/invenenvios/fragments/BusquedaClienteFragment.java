package com.innovati.felipehernandez.invenenvios.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.PedidoActivity;
import com.innovati.felipehernandez.invenenvios.adapters.EntregasRecycleViewAdaptador;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.VwClientes_I;
import com.innovati.felipehernandez.invenenvios.database.VwClientes_IDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class BusquedaClienteFragment extends Fragment
{
    private ImageButton BuscarImageButton;
    private EditText buscarEditText;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayour;
    private ConstraintLayout fragment_Datos;

    private EditText nombreClienteEditText_C;
    private EditText rfcEditText_C;
    private EditText calleEditText_C;
    private EditText numeroExteriorEditText_C;
    private EditText numeroInteriorEditText_C;
    private EditText coloniaEditText_C;
    private EditText telefonoEditText_C;

    MetodosInternos metodosInternos;
    VwClientes result[];
    private DaoSession daoSession;


    public BusquedaClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_busqueda_cliente, container, false);

        inicializacion(v);
        daoSession = ((MyApp) getActivity().getApplication()).getDaoSession();


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
        fragment_Datos = (ConstraintLayout)view.findViewById(R.id.fragment_Datos);

        nombreClienteEditText_C = (EditText)view.findViewById(R.id.NombreClienteEditText_C);
        rfcEditText_C = (EditText)view.findViewById(R.id.RFCEditText_C);
        calleEditText_C = (EditText)view.findViewById(R.id.CalleEditText_C);
        numeroExteriorEditText_C = (EditText)view.findViewById(R.id.NumeroExteriorEditText_C);
        numeroInteriorEditText_C = (EditText)view.findViewById(R.id.NumeroInteriorEditText_C);
        coloniaEditText_C = (EditText)view.findViewById(R.id.ColoniaEditText_C);
        telefonoEditText_C = (EditText)view.findViewById(R.id.TelefonoEditText_C);
    }

    public void filtar()
    {
        String nombre = buscarEditText.getText().toString();
        metodosInternos = new MetodosInternos(getActivity());

        if(metodosInternos.conexionRed())
        {
            //sin filtro = todos
            if(TextUtils.isEmpty(nombre))
            {
                try
                {
                    VwClientesDao _dao = getVwClientesDao();
                    ConsultaClientes c = new ConsultaClientes(nombre);
                    c.execute(_dao);

                    mAdapter = new EntregasRecycleViewAdaptador(result, R.layout.recycleview_clientes_item, new EntregasRecycleViewAdaptador.OnItemClickListener() {
                        @Override
                        public void onItemClick(VwClientes listita, int posicion) {
                            Log.i(result[posicion].getNombre(), "ERROR");

                            PedidoActivity.clave = result[posicion].getClave();
                            PedidoActivity.nombreC = result[posicion].getNombre();
                            PedidoActivity.ClienteEntTextView.setText("Cliente: "+ PedidoActivity.nombreC);
                        }
                    });
                    //efectos en recycle view
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerView.setLayoutManager(mLayour);
                    mRecyclerView.setAdapter(mAdapter);
                    cargarDatos();

                }
                catch(Exception e)
                {
                    Toast.makeText(getActivity(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                try
                {
                    nombre = "%" + nombre;
                    nombre += "%";
                    VwClientesDao _dao = getVwClientesDao();
                    ConsultaClientes c = new ConsultaClientes(nombre);
                    c.execute(_dao);

                    cargarDatos();
                }
                catch(Exception e)
                {
                    Toast.makeText(getContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
        else //bd interna
        {
            try {
                internaBD();

            }catch (Exception e)
            {
                metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
            }
        }
    }

    private void cargarDatos()
    {
        if(fragment_Datos.getVisibility() == View.VISIBLE){
            mRecyclerView.setVisibility(View.VISIBLE);
            fragment_Datos.setVisibility(View.INVISIBLE);
        }


        mAdapter = new EntregasRecycleViewAdaptador(result, R.layout.recycleview_clientes_item, new EntregasRecycleViewAdaptador.OnItemClickListener() {
            @Override
            public void onItemClick(VwClientes listita, int posicion)
            {

                PedidoActivity.clave = result[posicion].getClave();
                PedidoActivity.nombreC = result[posicion].getNombre();
                PedidoActivity.ClienteEntTextView.setText("Cliente: "+ PedidoActivity.nombreC);
                //"desaparece" el layout
                mRecyclerView.setLayoutManager(null);
                //carga datos y hace visible
                fragment_Datos.setVisibility(View.VISIBLE);

                nombreClienteEditText_C.setText(result[posicion].getNombre());
                rfcEditText_C.setText(result[posicion].getRfc());
                calleEditText_C.setText(result[posicion].getCalle());
                numeroExteriorEditText_C.setText(result[posicion].getNumeroExterior());
                numeroInteriorEditText_C.setText(result[posicion].getNumeroInterior());
                coloniaEditText_C.setText(result[posicion].getColonia());
                telefonoEditText_C.setText(result[posicion].getTelefono());
                mRecyclerView.setVisibility(View.INVISIBLE);

            }
        });
        //efectos en recycle view
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayour);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void internaBD()
    {
        String nombre = buscarEditText.getText().toString();
        VwClientes_IDao vwClientes_iDao = daoSession.getVwClientes_IDao();
        List<VwClientes_I> clientes;

        //si esta vacio
        if(TextUtils.isEmpty(nombre))
        {
            QueryBuilder<VwClientes_I> qb = vwClientes_iDao.queryBuilder();
            clientes = qb.list();
            result = new VwClientes[clientes.size()];
        }
        else
        {
            nombre = "%" + nombre;
            nombre += "%";

            QueryBuilder<VwClientes_I> qb = vwClientes_iDao.queryBuilder();
            qb.where(VwClientes_IDao.Properties.Nombre.like(nombre));

            clientes = qb.list();
            result = new VwClientes[clientes.size()];
        }

        for(int x=0; x<clientes.size(); x++)
        {
            VwClientes objetoCliente = new VwClientes();

            objetoCliente.setClave(clientes.get(x).getClave());
            objetoCliente.setNombre(clientes.get(x).getNombre());
            objetoCliente.setRfc(clientes.get(x).getRfc());
            objetoCliente.setCalle(clientes.get(x).getCalle());
            objetoCliente.setNumeroExterior(clientes.get(x).getNumeroExterior());
            objetoCliente.setNumeroInterior(clientes.get(x).getNumeroInterior());
            objetoCliente.setColonia(clientes.get(x).getColonia());
            objetoCliente.setTelefono(clientes.get(x).getTelefono());

            result[x] = objetoCliente;
        }

        cargarDatos();
    }

    public static VwClientesDao getVwClientesDao()
    {
        return VwClientesDaoFactory.create();
    }

    public class ConsultaClientes extends AsyncTask<VwClientesDao, VwClientes[], VwClientes[]>
    {
        String nombre;

        public ConsultaClientes(String nombre)
        {
            this.nombre = nombre;
        }

        @Override
        protected VwClientes[] doInBackground(VwClientesDao... vwClientesDaos)
        {
            try
            {
                if(nombre.equals(""))
                    result = vwClientesDaos[0].findAll();
                else
                    result = vwClientesDaos[0].findWhereNombreEquals(nombre);

            }
            catch (Exception e){

            }
            return result;
        }
    }

}
