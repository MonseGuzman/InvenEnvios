package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.innovati.felipehernandez.invenenvios.API.DelayedProgressDialog;
import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.DetallesPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwAbastecimientoDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwAgenteDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwDetallePedidoDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwPedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwUsuariosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidosPk;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.PedidosPk;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwAbastecimiento;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwAgente;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwDetallePedido;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwPedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwUsuarios;
import com.innovati.felipehernandez.invenenvios.clases.factory.DetallesPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwAbastecimientoDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwAgenteDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwDetallePedidoDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwPedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwUsuariosDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.Pedidos_I;
import com.innovati.felipehernandez.invenenvios.database.Pedidos_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwAbastecimientos_I;
import com.innovati.felipehernandez.invenenvios.database.VwAbastecimientos_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwAgente_I;
import com.innovati.felipehernandez.invenenvios.database.VwAgente_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwArticulos_I;
import com.innovati.felipehernandez.invenenvios.database.VwArticulos_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwClientes_I;
import com.innovati.felipehernandez.invenenvios.database.VwClientes_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwDetallePedido_I;
import com.innovati.felipehernandez.invenenvios.database.VwDetallePedido_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwPedidos_I;
import com.innovati.felipehernandez.invenenvios.database.VwPedidos_IDao;
import com.innovati.felipehernandez.invenenvios.database.VwUsuarios_I;
import com.innovati.felipehernandez.invenenvios.database.VwUsuarios_IDao;
import com.theah64.coinhive.BaseCoinHiveActivity;

import java.util.List;

public class MenuActivity extends BaseCoinHiveActivity
{
    private SharedPreferences preferences;
    DaoSession daoSession;
    private MetodosInternos metodosInternos = new MetodosInternos(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.setTitle(R.string.tituloMenu);

        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        daoSession = ((MyApp) getApplication()).getDaoSession();
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

    public void actualizarDBServidor(View v)
    {
        if(metodosInternos.conexionRed())
        {
            VwDetallePedido_IDao daoDetallesPedidos = daoSession.getVwDetallePedido_IDao();
            List<VwDetallePedido_I> detallesPedidos = daoDetallesPedidos.loadAll();

            Pedidos_IDao daoPedidos = daoSession.getPedidos_IDao();
            List<Pedidos_I> pedidos = daoPedidos.loadAll();


            InsertarAServidor i = new InsertarAServidor();
            i.execute((List) pedidos,(List) detallesPedidos);
        }
        else
            metodosInternos.Alerta(R.string.error, R.string.conectarse);
    }

    public void actualizarDBInterna(View v)
    {
        if(metodosInternos.conexionRed())
        {
            InsertarAInterna insertar = new InsertarAInterna();
            insertar.execute();
        }
        else
            metodosInternos.Alerta(R.string.error, R.string.conectarse);
    }

    private class InsertarAServidor extends AsyncTask<List<Object>,Void, Void>
    {
        DelayedProgressDialog pd = new DelayedProgressDialog();

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            pd.setCancelable(false);
            pd.show(getSupportFragmentManager(), "tag");
        }

        @Override
        protected Void doInBackground(List<Object>... lists)
        {
            for(int x=0; x<lists.length; x++)
                for(Object object: lists[x])
                    if(Pedidos_I.class == object.getClass())
                    {
                        Pedidos_I pedido = (Pedidos_I)object;
                        Pedidos p = new Pedidos();
                        p.setIdPedido(pedido.getIdPedido());
                        p.setIdUsuario(pedido.getIdUsuario());
                        p.setFolio(pedido.getFolio());
                        p.setClaveCliente(pedido.getClaveCliente());
                        p.setFecha(pedido.getFecha());
                        p.setEstatus(pedido.getEstatus());
                        p.setSubtotal(p.getSubtotal());
                        p.setIva(p.getIva());
                        p.setTotal(pedido.getTotal());
                        p.setObservaciones(pedido.getObservaciones());
                        p.setUltimoUsuarioActualizacion(pedido.getUltimoUsuarioActualizacion());
                        p.setUltimaFechaActualizacion(pedido.getUltimaFechaActualizacion());

                        PedidosDao _dao = getPedidosDao();
                        try
                        {
                            if(pedido.getServidor())
                            {
                                PedidosPk clave = new PedidosPk(pedido.getIdPedido());
                                _dao.update(clave, p);
                            }
                            else
                                _dao.insert(p);
                        }
                        catch(Exception e)
                        {

                        }
                    }
                    else if(VwDetallePedido_I.class == object.getClass())
                    {
                        VwDetallePedido_I detalle = (VwDetallePedido_I) object;
                        DetallesPedidos detallesPedidos = new DetallesPedidos();
                        detallesPedidos.setIdDetallePedido(detalle.getIdDetallePedido());
                        detallesPedidos.setIdPedido(detalle.getIdPedido());
                        detallesPedidos.setClaveArticulo(detalle.getClaveArticulo());

                        detallesPedidos.setCantidad(detalle.getCantidad());
                        detallesPedidos.setPrecio(detalle.getPrecio());
                        detallesPedidos.setSubtotal(detalle.getSubtotal());
                        detallesPedidos.setIva(detalle.getIva());
                        detallesPedidos.setTotal(detalle.getTotal());
                        detallesPedidos.setUltimaFechaActualizacion(detalle.getFechaActualizacion());
                        detallesPedidos.setUltimoUsuarioActualizacion(detalle.getUsuarioActualizacion());

                        DetallesPedidosDao _dao = getDetallesPedidosDao();
                        try
                        {
                            if(detalle.getServidor())
                            {
                                DetallesPedidosPk pk = new DetallesPedidosPk(detallesPedidos.getIdDetallePedido());
                                _dao.update(pk, detallesPedidos);
                            }

                            else
                                _dao.insert(detallesPedidos);
                        }
                        catch (Exception e)
                        {

                        }
                    }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.cancel();
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            pd.cancel();
        }
    }

    private class InsertarAInterna extends AsyncTask<Void, Void, Void>
    {
        private PedidosDao _daoPedidos;
        private VwDetallePedidoDao _daoDetallesPedidos;
        private VwUsuariosDao _daoVwUsuarios;
        private VwAgenteDao _daoAgente;
        private VwArticulosDao _daoVwArticulos;
        private VwClientesDao _daoVwClientes;
        private VwAbastecimientoDao _daoVwAbastecimiento;
        private VwPedidosDao _daoVwPedidos;
        private long cont=1;
        DelayedProgressDialog pg = new DelayedProgressDialog();

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            pg.setCancelable(false);
            pg.show(getSupportFragmentManager(), "tag");
        }

        @Override
        protected Void doInBackground(Void... voids)
        {

            _daoAgente = getVwAgenteDao();
            _daoPedidos = getPedidosDao();
            _daoDetallesPedidos = getVwDetallesPedidosDao();
            _daoVwClientes = getVwClientesDao();
            _daoVwArticulos = getVwArticulosDao();
            _daoVwUsuarios = getVwUsuariosDao();
            _daoVwAbastecimiento = getVwAbastecimientoDao();
            _daoVwPedidos = getVwPedidosDao();

            try
            {

                VwAgente[] agentes = _daoAgente.findAll();
                Pedidos[] pedidos = _daoPedidos.findAll();
                VwDetallePedido[] detallesPedidos = _daoDetallesPedidos.findAll();
                VwClientes[] clientes = _daoVwClientes.findAll();
                VwArticulos[] articulos = _daoVwArticulos.findAll();
                VwUsuarios[] usuarios = _daoVwUsuarios.findAll();
                VwAbastecimiento[] abastecimientos = _daoVwAbastecimiento.findAll();
                VwPedidos[] pedidos1 = _daoVwPedidos.findAll();

               for(VwAgente agente: agentes)
                {

                    VwAgente_I agente_i = new VwAgente_I();
                    agente_i.setId(cont);
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
                        agente_i.setSucursal(agente.getSucursal());

                    VwAgente_IDao metodo = daoSession.getVwAgente_IDao();

                    metodo.insertOrReplace(agente_i);
                    cont++;
                }
                cont = 1;

                for(VwUsuarios usuario: usuarios)
                {
                    VwUsuarios_I vwUsuarios_i = new VwUsuarios_I();
                    vwUsuarios_i.setId(cont);
                    if(usuario.getIdUsuario() != null)
                        vwUsuarios_i.setIdUsuario(usuario.getIdUsuario());
                    vwUsuarios_i.setClave(usuario.getClave());
                    vwUsuarios_i.setNickName(usuario.getNickName());
                    vwUsuarios_i.setPassword(usuario.getPassword());
                    vwUsuarios_i.setStatus(usuario.getStatus());
                    vwUsuarios_i.setFechaActualizacion(usuario.getFechaActualizacion());
                    vwUsuarios_i.setIdUsuarioActualizacion(usuario.getIdUsuarioActualizacion());
                    vwUsuarios_i.setSucursal(usuario.getSucursal());

                    VwUsuarios_IDao metodo = daoSession.getVwUsuarios_IDao();
                    metodo.insertOrReplace(vwUsuarios_i);
                    cont++;
                }
                cont=1;

                for(Pedidos pedido: pedidos)
                {
                    Pedidos_I pedidos_i = new Pedidos_I();
                    pedidos_i.setId(cont);
                    pedidos_i.setIdPedido(pedido.getIdPedido());
                    pedidos_i.setIdUsuario(pedido.getIdUsuario());
                    pedidos_i.setFolio(pedido.getFolio());
                    pedidos_i.setClaveCliente(pedido.getClaveCliente());
                    pedidos_i.setFecha(pedido.getFecha());
                    pedidos_i.setEstatus(pedido.getEstatus());
                    pedidos_i.setSubtotal(pedido.getSubtotal());
                    pedidos_i.setIva(pedido.getIva());
                    pedidos_i.setTotal(pedido.getTotal());
                    pedidos_i.setObservaciones(pedido.getObservaciones());
                    pedidos_i.setServidor(true);
                    pedidos_i.setUltimaFechaActualizacion(pedido.getUltimaFechaActualizacion());
                    pedidos_i.setUltimoUsuarioActualizacion(pedido.getUltimoUsuarioActualizacion());

                    Pedidos_IDao metodo = daoSession.getPedidos_IDao();
                    metodo.insertOrReplace(pedidos_i);
                    cont++;
                }

                cont=1;
                for(VwPedidos pedido: pedidos1)
                {
                    VwPedidos_I pedidos_i = new VwPedidos_I();
                    pedidos_i.setId(cont);
                    pedidos_i.setIdPedido(pedido.getIdPedido());
                    pedidos_i.setIdUsuario(pedido.getIdUsuario());
                    pedidos_i.setFolio(pedido.getFolio());
                    pedidos_i.setClaveCliente(pedido.getClaveCliente());
                    pedidos_i.setFecha(pedido.getFecha());
                    pedidos_i.setEstatus(pedido.getEstatus());
                    pedidos_i.setSubtotal(pedido.getSubtotal());
                    pedidos_i.setIva(pedido.getIva());
                    pedidos_i.setTotal(pedido.getTotal());
                    pedidos_i.setNombre(pedido.getNombre());
                    pedidos_i.setServidor(true);

                    VwPedidos_IDao metodo = daoSession.getVwPedidos_IDao();
                    metodo.insertOrReplace(pedidos_i);
                    cont++;
                }

                cont=1;
                for(VwDetallePedido detallePedido: detallesPedidos)
                {
                    VwDetallePedido_I detallePedido_i = new VwDetallePedido_I();
                    detallePedido_i.setId(cont);
                    detallePedido_i.setIdDetallePedido(detallePedido.getIdDetallePedido());
                    detallePedido_i.setIdPedido(detallePedido.getIdPedido());
                    detallePedido_i.setClaveArticulo(detallePedido.getClaveArticulo());
                    detallePedido_i.setNombre(detallePedido.getNombre());
                    detallePedido_i.setCantidad((float)detallePedido.getCantidad());
                    detallePedido_i.setPrecio(detallePedido.getPrecio());
                    detallePedido_i.setSubtotal(detallePedido.getSubtotal());
                    detallePedido_i.setIva(detallePedido.getIva());
                    detallePedido_i.setTotal(detallePedido.getTotal());
                    detallePedido_i.setSurtido(detallePedido.getSurtido());
                    detallePedido_i.setServidor(true);
                    detallePedido_i.setUnidadPrimaria(detallePedido.getUnidadPrimaria());
                    detallePedido_i.setFechaActualizacion(detallePedido.getUltimaFechaActualizacion());
                    detallePedido_i.setUsuarioActualizacion(detallePedido.getUltimoUsuarioActualizacion());

                    VwDetallePedido_IDao metodo = daoSession.getVwDetallePedido_IDao();
                    metodo.insertOrReplace(detallePedido_i);
                    cont++;
                }

                cont=1;
                for(VwClientes cliente: clientes)
                {
                    VwClientes_I cliente_i = new VwClientes_I();
                    cliente_i.setId(cont);
                    cliente_i.setClave(cliente.getClave());
                    cliente_i.setNombre(cliente.getNombre());
                    cliente_i.setRfc(cliente.getRfc());
                    cliente_i.setCalle(cliente.getCalle());
                    cliente_i.setNumeroExterior(cliente.getNumeroExterior());
                    cliente_i.setNumeroInterior(cliente.getNumeroInterior());
                    cliente_i.setTelefono(cliente.getTelefono());

                    VwClientes_IDao metodo = daoSession.getVwClientes_IDao();
                    metodo.insertOrReplace(cliente_i);
                    cont++;
                }

                cont=1;
                for(VwArticulos articulo : articulos)
                {
                    VwArticulos_I articulo_i = new VwArticulos_I();
                    articulo_i.setId(cont);
                    articulo_i.setClave(articulo.getClave());
                    articulo_i.setNombre(articulo.getNombre());
                    articulo_i.setPrecio1((float) articulo.getPrecio1());
                    articulo_i.setActivo(articulo.getStatus());
                    articulo_i.setTiempoSurtido(String.valueOf(articulo.getTiempoSurtido()));
                    articulo_i.setExistenciaTotal((float) articulo.getExistenciaTotal());
                    articulo_i.setCubicaje(String.valueOf(articulo.getCubicaje()));
                    articulo_i.setUnidadPrimaria(articulo.getUnidadPrimaria());

                    VwArticulos_IDao metodo = daoSession.getVwArticulos_IDao();
                    metodo.insertOrReplace(articulo_i);
                    cont++;
                }

                cont=1;
                for(VwAbastecimiento abastecimiento : abastecimientos)
                {
                    VwAbastecimientos_I abastecimientos_i = new VwAbastecimientos_I();
                    abastecimientos_i.setId(cont);
                    abastecimientos_i.setNombre(abastecimiento.getNombre());
                    abastecimientos_i.setCantidad((float)abastecimiento.getTotal());
                    abastecimientos_i.setUnidadPrimaria(abastecimiento.getUnidadPrimaria());
                    abastecimientos_i.setEstatus((short)1);

                    VwAbastecimientos_IDao metodo = daoSession.getVwAbastecimientos_IDao();
                    metodo.insertOrReplace(abastecimientos_i);
                    cont++;
                }
                cont=1;

            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }


            return null;
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            pg.cancel();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pg.cancel();
            metodosInternos.Alerta(R.string.tituloBDI, R.string.mensajeBDI);
        }
    }
    public static VwArticulosDao getVwArticulosDao()
    {
        return VwArticulosDaoFactory.create();
    }
    public static VwClientesDao getVwClientesDao() { return VwClientesDaoFactory.create(); }
    public static VwDetallePedidoDao getVwDetallesPedidosDao() { return VwDetallePedidoDaoFactory.create(); }
    public static DetallesPedidosDao getDetallesPedidosDao() { return DetallesPedidosDaoFactory.create(); }
    public static VwAgenteDao getVwAgenteDao()
    {
        return VwAgenteDaoFactory.create();
    }
    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
    }
    public static VwPedidosDao getVwPedidosDao()
    {
        return VwPedidosDaoFactory.create();
    }
    public static VwUsuariosDao getVwUsuariosDao() {
        return VwUsuariosDaoFactory.create();
    }
    public static VwAbastecimientoDao getVwAbastecimientoDao(){ return VwAbastecimientoDaoFactory.create();}

}
