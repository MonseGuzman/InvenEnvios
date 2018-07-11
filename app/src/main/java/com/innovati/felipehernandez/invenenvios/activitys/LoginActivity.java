package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwUsuariosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwUsuarios;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwUsuariosDaoFactory;

public class LoginActivity extends AppCompatActivity
{
    private SharedPreferences preferences;
    private CheckBox chRecordarme;
    private EditText etUsuario, etPassword;
    MetodosInternos metodosInternos = new MetodosInternos(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_logo_lth_bar);

        inicializacion();
        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        verificaCredenciales();
    }

    private void inicializacion()
    {
        chRecordarme = (CheckBox)findViewById(R.id.chRecordarme);
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void login(View v)
    {
        //si la conexion wifi/datos es conectada
        MetodosInternos conectado = new MetodosInternos(this);
        String usuario, password;

        usuario = etUsuario.getText().toString();
        password = etPassword.getText().toString();

        if(conectado.conexionRed())
        {
            if(conectado.validacion(usuario, password))
            {
                //login
               try
               {
                   VwUsuariosDao _dao = getVwUsuariosDao();
                   String parametros[] = {usuario,password};
                   dbManager consulta = new dbManager(parametros);
                   consulta.execute(_dao);
               }
               catch(Exception e)
               {
                   Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
               }
            }
            else
                Snackbar.make(v, R.string.sinDatos, Snackbar.LENGTH_SHORT).show();
        }
        else
        {
            //cuando no esta conectado a ninguna red
            metodosInternos.Alerta(R.string.sinInternet, R.string.conectarse);
        }
    }

    private void verificaCredenciales()
    {
        String email = preferences.getString("usuario", "");
        String pass = preferences.getString("contraseña", "");

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass))
        {
            etUsuario.setText(email);
            etPassword.setText(pass);
        }
    }

    private void guardarPreferencias(String email, String contra, String IdUsuario)
    {
        //cuando abre la actividad
        if (chRecordarme.isChecked())
        {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("usuario", email);
            editor.putString("contraseña", contra);
            editor.putString("idUsuario", IdUsuario);
            editor.commit(); // empieza a guardar los put*
            editor.apply(); //guarda todos los cambios aunque no se guarden todos
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("agente", email);
        editor.commit(); // empieza a guardar los put*
        editor.apply(); //guarda todos los cambios aunque no se guarden todos
    }

    public class dbManager extends AsyncTask<VwUsuariosDao, Void, VwUsuarios[]>
    {
        String[] parametros;

        public dbManager(String[] parametros)
        {
            this.parametros = parametros;
        }

        @Override
        protected VwUsuarios[] doInBackground(VwUsuariosDao... strings)
        {
            VwUsuarios result[] = null;
            try
            {
                result =  strings[0].findByDynamicWhere("NickName = ? AND Password = ? ", parametros);

            }
            catch(Exception e)
            {

            }
            return result;
        }

        @Override
        protected void onPostExecute(VwUsuarios[] vwUsuarios) {

            super.onPostExecute(vwUsuarios);
            String usuario, password;
            usuario = etUsuario.getText().toString();
            password = etPassword.getText().toString();

            if(vwUsuarios.length > 0)
            {
                Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                guardarPreferencias(usuario, password, vwUsuarios[0].getIdUsuario());
            }
            else
                Toast.makeText(LoginActivity.this, "Si se está ejecutando aca", Toast.LENGTH_SHORT).show();
        }
    }

    public static VwUsuariosDao getVwUsuariosDao() {
        return VwUsuariosDaoFactory.create();
    }

}
