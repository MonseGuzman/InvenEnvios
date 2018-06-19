package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;

public class EntregasActivity extends AppCompatActivity
{
    MetodosInternos metodosInternos = new MetodosInternos(this);
    String[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas);

        cuadroDialogo();
    }

    public void cuadroDialogo()
    {
        final AlertDialog.Builder mensaje = new AlertDialog.Builder(this);

        View v = getLayoutInflater().inflate(R.layout.nombre_cliente, null);
        mensaje.setTitle(R.string.seleccionarCliente);

        Button aceptarDialogoButton = (Button) v.findViewById(R.id.aceptarDialogoButton);
        final EditText nombreDialogoEditText = (EditText)v.findViewById(R.id.nombreDialogoEditText);

        mensaje.setView(nombreDialogoEditText);
        mensaje.setView(v);

        //se crea el nuevo
        final AlertDialog dialog = mensaje.create();

        aceptarDialogoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!TextUtils.isEmpty(nombreDialogoEditText.getText().toString()))
                {
                    filtar(nombreDialogoEditText.getText().toString());

                    if(result.length > 1)
                        listaClientes();
                    else
                    {
                        //lo que sea que haga para seleccionar
                        Toast.makeText(EntregasActivity.this, "regreso uno", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(EntregasActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
            }
        });

        dialog.setCancelable(true);
        dialog.show();
    }

    private void listaClientes()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.seleccionarCliente);
        builder.setItems(result, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //lo que sea que haga para seleccionar
                Toast.makeText(EntregasActivity.this, "lo logro", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(true); //false
        builder.show();
    }

    public void filtar(String nombre)
    {
        VwClientes[] clientes;

        //todos los que se aparecen con el where
        if(metodosInternos.conexionRed())
        {
            try
            {
                nombre = "%" + nombre;
                nombre += "%";
                VwClientesDao _dao = getVwClientesDao();
                clientes = _dao.findWhereNombreEquals(nombre);

                for(int x=0; x<clientes.length-1;x++)
                {
                    result[x] = clientes[x].getNombre();
                }
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

    public static VwClientesDao getVwClientesDao()
    {
        return VwClientesDaoFactory.create();
    }
}
