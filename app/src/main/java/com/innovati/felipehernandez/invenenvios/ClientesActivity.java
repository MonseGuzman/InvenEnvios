package com.innovati.felipehernandez.invenenvios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.innovati.felipehernandez.invenenvios.adapters.ClientesAdaptador;

public class ClientesActivity extends AppCompatActivity {

    private ListView clienteListView_C;
    private ClientesAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        inicializacion();

        adaptador = new ClientesAdaptador(this, R.layout.listview_cliente);
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
}
