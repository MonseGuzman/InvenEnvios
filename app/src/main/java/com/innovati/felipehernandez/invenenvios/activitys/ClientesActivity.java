package com.innovati.felipehernandez.invenenvios.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.Clientes;
import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ClientesAdaptador;

import java.util.ArrayList;
import java.util.List;

public class ClientesActivity extends AppCompatActivity
{
    private ListView clienteListView_C;
    private TextView TelefonoTextView_C;
    private EditText buscarClienteEditText_C;
    private ClientesAdaptador adaptador;
    private ArrayList<Clientes> clientes = new ArrayList();

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        clienteListView_C = (ListView)findViewById(R.id.clienteListView);
        TelefonoTextView_C = (TextView)findViewById(R.id.TelefonoTextView_C);
        buscarClienteEditText_C = (EditText)findViewById(R.id.buscarClienteEditText_C);

        //rellenaLista();
       /* Clientes c = new Clientes();
        c.setNombre("Andrea");
        c.setRFC("GULA586");
        c.setTelefono("3411009819");
        clientes.add(c);*/

       clienteListView_C.setFocusable(true);

        adaptador = new ClientesAdaptador(this, clientes,  R.layout.listview_cliente);
        clienteListView_C.setAdapter(adaptador);

        //menú de contexto
        registerForContextMenu(clienteListView_C);
    }

    //menú de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_listview_cliente ,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        posicion = info.position;

        switch (item.getItemId()) {
            case R.id.Llamar_C:
                if(!checkPermission())
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                else
                    telefono(posicion);

                return true;
            /*case  R.id.edit_note:
                ShowAlertForEditBoard("Editar", "Cambiar el nombre de la nota", notes.get(info.position));
                return true;*/
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void rellenaLista()
    {
        //añadir otro o el mismo con el where blah_blah = blah,
        MetodosInternos metodosInternos = new MetodosInternos(this);
        clientes = metodosInternos.consultaDeTodos();

    }

    public void telefono(int position)
    {
        String telefono = clientes.get(position).getTelefono();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + telefono));

        if(intent.resolveActivity(getPackageManager()) != null)
        {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                return;
            }
            startActivity(intent);
        }

    }

    public void filtar(View v)
    {
        String nombre = buscarClienteEditText_C.getText().toString();
        if(TextUtils.isEmpty(nombre))
        {
            //sin filtro = todos
            rellenaLista();
        }
        else
        {
            //todos los que se parecen con el where
            rellenaLista();
        }
    }

    public boolean checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //Aquí lo que se hace si aceptan el permiso
                    telefono(posicion);
                }
                else
                {
                    //Aquí lo que se hace si no lo aceptan
                }
                break;
        }
    }
}
