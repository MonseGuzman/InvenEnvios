package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ClientesAdaptador;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwAgenteDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwAgente;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.exceptions.VwAgenteDaoException;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwAgenteDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;

public class ClientesActivity extends AppCompatActivity
{
   private ListView clienteListView_C;
    private ClientesAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        VwClientes result[] = null;
        inicializacion();
        try
        {
            VwClientesDao _dao = getVwClientesDao();
            result = _dao.findAll();

        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }



       adaptador = new ClientesAdaptador(this, R.layout.listview_cliente, result);
        clienteListView_C.setAdapter(adaptador);

        //menú de contexto
        registerForContextMenu(clienteListView_C);
    }


    private void inicializacion()
    {
        clienteListView_C = (ListView)findViewById(R.id.clienteListView_C);
    }

    //menú de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        getMenuInflater().inflate(R.menu.menu_listview_cliente ,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public static VwClientesDao getVwClientesDao()
    {
        return VwClientesDaoFactory.create();
    }
}
