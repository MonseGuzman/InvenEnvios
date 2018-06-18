package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ClientesAdaptador;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;

public class EntregasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas);
    }

    public void cuadroDialogo()
    {
        AlertDialog.Builder mensaje = new AlertDialog.Builder(this);

        mensaje.setTitle(R.string.seleccionarCliente);

        LayoutInflater inflater = getLayoutInflater();

        /*mensaje.setView(inflater.inflate(R.layout.dialog_signin, null));

        mensaje.setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        return mensaje.create();*/
    }

    /*public void filtar(View v)
    {
        String nombre = buscarClienteEditText_C.getText().toString();
        if(TextUtils.isEmpty(nombre))
        {
            //sin filtro = todos
            if(metodosInternos.conexionRed())
            {
                try
                {
                    VwClientesDao _dao = getVwClientesDao();
                    result = _dao.findAll();
                    adaptador = new ClientesAdaptador(this,  R.layout.listview_cliente, result);
                    clienteListView_C.setAdapter(adaptador);

                }
                catch(Exception e)
                {
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
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
                    adaptador = new ClientesAdaptador(this,  R.layout.listview_cliente, result);
                    clienteListView_C.setAdapter(adaptador);
                }
                catch(Exception e)
                {
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //bd interna
            }
        }
    }*/
}
