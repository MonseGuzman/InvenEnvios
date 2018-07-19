package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.DetallesPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwAgenteDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwDetallePedidoDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwUsuariosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwAgente;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwDetallePedido;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwUsuarios;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwAgenteDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwDetallePedidoDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwUsuariosDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.DetallesPedidosDao_I;
import com.innovati.felipehernandez.invenenvios.database.DetallesPedidos_I;
import com.innovati.felipehernandez.invenenvios.database.Pedidos_I;
import com.innovati.felipehernandez.invenenvios.database.PedidosDao_I;
import com.innovati.felipehernandez.invenenvios.database.VwAgenteDao_I;
import com.innovati.felipehernandez.invenenvios.database.VwAgente_I;
import com.innovati.felipehernandez.invenenvios.database.VwArticulos_I;
import com.innovati.felipehernandez.invenenvios.database.VwArticulosDao_I;
import com.innovati.felipehernandez.invenenvios.database.VwClientesDao_I;
import com.innovati.felipehernandez.invenenvios.database.VwClientes_I;
import com.innovati.felipehernandez.invenenvios.database.VwUsuarios_I;
import com.innovati.felipehernandez.invenenvios.database.VwUsuariosDao_I;

import java.util.List;

public class MenuActivity extends AppCompatActivity
{
    private SharedPreferences preferences;
    DaoSession daoSession = ((MyApp) getApplication()).getDaoSession();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.setTitle("Menú");
        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
    }


    //              |NUM | TIPO
    //----------------------------------------
    //ABASTECIMIENTO|  1 | VISTA NORMAL
    //              |  2 | VISTA CON DETALLES
    public void botones(View v)
    {
        Intent i = new Intent();

        switch (v.getId())
        {
            case R.id.ArticulosButton:
                i = new Intent(this, ArticuloActivity.class);
                break;
            case R.id.ClientesButton:
                i = new Intent(this, ClientesActivity.class);
                break;
            case R.id.PedidosButton:
                i = new Intent(this, PedidoActivity.class);
                break;
            case R.id.AbastecimientoButton:
                i = new Intent(this, AbastecimientosActivity.class);
                i.putExtra("Tipo", 2);
                break;
            case R.id.EntregaButton:
                i = new Intent (this, EntregasActivity.class);
                break;
        }
        startActivity(i);
    }

    public void salir(View v)
    {
        preferences.edit().clear().apply();

        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //cierra esta actividad
        startActivity(i);
    }

    public void actualizar()
    {
        PedidosDao_I daoPedidos = daoSession.getPedidosDaoI();
        List<Pedidos_I> pedidos = daoPedidos.loadAll();


        DetallesPedidosDao_I detallesPedidosDaoI = daoSession.getDetallesPedidosDaoI();
        List<DetallesPedidos_I> detallesPedidoIS = detallesPedidosDaoI.loadAll();
    }

    private class insertarAServidor extends AsyncTask<List<Object>,Void, Void>
    {

        @Override
        protected Void doInBackground(List<Object>... lists)
        {
            for(int x=0; x<lists.length; x++)
            {
                for(Object object: lists[x])
                {
                    if(Pedidos_I.class == object.getClass())
                    {

                    }
                    else if(DetallesPedidos_I.class == object.getClass())
                    {

                    }
                }
            }
            return null;
        }
    }
    private class insertarAInterna extends AsyncTask<Void, Void, Void>
    {
        private PedidosDao _daoPedidos;
        private VwDetallePedidoDao _daoDetallesPedidos;
        private VwUsuariosDao _daoVwUsuarios;
        private VwAgenteDao _daoAgente;
        private VwArticulosDao _daoVwArticulos;
        private VwClientesDao _daoVwClientes;

        @Override
        protected Void doInBackground(Void... voids)
        {
            _daoAgente = getVwAgenteDao();
            _daoPedidos = getPedidosDao();
            _daoDetallesPedidos = getDetallesPedidosDao();
            _daoVwClientes = getVwClientesDao();
            _daoVwArticulos = getVwArticulosDao();
            _daoVwUsuarios = getVwUsuariosDao();
            try
            {

                VwAgente[] agentes = _daoAgente.findAll();
                Pedidos[] pedidos = _daoPedidos.findAll();
                VwDetallePedido[] detallesPedidos = _daoDetallesPedidos.findAll();
                VwClientes[] clientes = _daoVwClientes.findAll();
                VwArticulos[] articulos = _daoVwArticulos.findAll();
                VwUsuarios[] usuarios = _daoVwUsuarios.findAll();

                for(VwAgente agente: agentes)
                {
                    VwAgente_I agente_i = new VwAgente_I();
                    if(agente.getClave() != null)
                        agente_i.setClave(agente.getClave());
                    if(agente.getNombre() != null)
                        agente_i.setNombre(agente.getNombre());
                    if(agente.getCelular() != null)
                        agente_i.setCelular(agente.getCelular());
                    if(agente.getTelefono() != null)
                        agente_i.setTelefono(agente.getTelefono());
                    agente_i.setComision(String.valueOf(agente.getComision()));
                    if(agente.getEmail() != null)
                        agente_i.setEmail(agente.getEmail());
                    if(agente.getSucursal() != null)
                        agente_i.setEmail(agente.getEmail());

                    VwAgenteDao_I metodo = daoSession.getVwAgenteDaoI();
                    metodo.insert(agente_i);
                }

                for(VwUsuarios usuario: usuarios)
                {
                    VwUsuarios_I vwUsuarios_i = new VwUsuarios_I();
                    if(usuario.getIdUsuario() != null)
                        vwUsuarios_i.setIdUsuario(usuario.getIdUsuario());
                    vwUsuarios_i.setClave(usuario.getClave());
                    vwUsuarios_i.setNickName(usuario.getNickName());
                    vwUsuarios_i.setPassword(usuario.getPassword());
                    vwUsuarios_i.setStatus(usuario.getStatus());
                    vwUsuarios_i.setFechaActualizacion(usuario.getFechaActualizacion());
                    vwUsuarios_i.setIdUsuarioActualizacion(usuario.getIdUsuarioActualizacion());
                    vwUsuarios_i.setSucursal(usuario.getSucursal());
                }
            }
            catch (Exception e)
            {

            }


            return null;
        }
    }

    public static VwArticulosDao getVwArticulosDao()
    {
        return VwArticulosDaoFactory.create();
    }
    public static VwClientesDao getVwClientesDao() { return VwClientesDaoFactory.create(); }
    public static VwDetallePedidoDao getDetallesPedidosDao() { return VwDetallePedidoDaoFactory.create(); }
    public static VwAgenteDao getVwAgenteDao()
    {
        return VwAgenteDaoFactory.create();
    }
    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
    }
    public static VwUsuariosDao getVwUsuariosDao() {
        return VwUsuariosDaoFactory.create();
    }

}
