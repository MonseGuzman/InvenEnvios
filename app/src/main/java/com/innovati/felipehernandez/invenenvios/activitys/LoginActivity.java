package com.innovati.felipehernandez.invenenvios.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.SettingActivity;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwUsuariosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwUsuarios;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwUsuariosDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.VwUsuarios_I;
import com.innovati.felipehernandez.invenenvios.database.VwUsuarios_IDao;
import com.innovati.felipehernandez.invenenvios.security.EncryptionAndDecryption;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class LoginActivity extends AppCompatActivity
{
    private SharedPreferences preferences;
    private CheckBox chRecordarme;
    private EditText etUsuario, etPassword;
    private int touch= 0;
    private EncryptionAndDecryption EaD= new EncryptionAndDecryption();
    private MetodosInternos conectado = new MetodosInternos(this);
    private DaoSession daoSession;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Cargando...");
        dialog.setCancelable(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.logo_carmenta);

        inicializacion();
        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        verificaCredenciales();
        touch = 0;
        daoSession = ((MyApp) getApplication()).getDaoSession();
    }

    private void inicializacion()
    {
        chRecordarme = (CheckBox)findViewById(R.id.chRecordarme);
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void login(View v)
    {
        dialog.show();
        //si la conexion wifi/datos es conectada

        String usuario, password;

        usuario = etUsuario.getText().toString();
        password = EaD.encry(etPassword.getText().toString());
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
            VwUsuarios_IDao vwUsuarios_iDao = daoSession.getVwUsuarios_IDao();

            QueryBuilder<VwUsuarios_I> qb = vwUsuarios_iDao.queryBuilder();
            qb.where(VwUsuarios_IDao.Properties.Password.eq(password), VwUsuarios_IDao.Properties.NickName.eq(usuario));
            List<VwUsuarios_I> usuarios = qb.list();
            if(!usuarios.isEmpty())
            {
                progress(usuario,usuarios.get(0).getIdUsuario());
                Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                guardarPreferencias(usuario, password);
                startActivity(i);
            }
            else
                conectado.Alerta(R.string.error,R.string.sinDatos);
        }
        dialog.hide();
    }

    private void verificaCredenciales()
    {
        dialog.show();
        String email = preferences.getString("usuario", "");
        String pass = preferences.getString("contraseña", "");

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass))
        {
            etUsuario.setText(email);
            etPassword.setText(pass);
        }
        dialog.hide();
    }

    private void guardarPreferencias(String email, String contra)
    {
        //cuando abre la actividad
        if (chRecordarme.isChecked())
        {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("usuario", email);
            editor.putString("contraseña", contra);

            editor.commit(); // empieza a guardar los put*
            editor.apply(); //guarda todos los cambios aunque no se guarden todos
        }
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
        protected void onPostExecute(VwUsuarios[] vwUsuarios)
        {

            super.onPostExecute(vwUsuarios);
            String usuario, password;
            usuario = etUsuario.getText().toString();
            password = EaD.encry(etPassword.getText().toString());
            if(vwUsuarios != null)
            {
                if(vwUsuarios.length > 0)
                {
                    progress(usuario,vwUsuarios[0].getIdUsuario());
                    Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    guardarPreferencias(usuario, password);
                }
                else
                    conectado.Alerta(R.string.error,R.string.sinDatos);
            }
            else
            {
                conectado.Alerta(R.string.error,R.string.sinDatos);
            }
        }
    }

    public static VwUsuariosDao getVwUsuariosDao() {
        return VwUsuariosDaoFactory.create();
    }

    public void runSeting(View view){
        touch ++;
        if(touch == 10){
            touch = 0;
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
    }
    private void progress(String user, String id){
        try{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("agente", user);
            editor.putString("idUsuario", id);
            editor.commit(); // empieza a guardar los put*
            editor.apply(); //guarda todos los cambios aunque no se guarden todos
        }catch (Exception e){}

        dialog.hide();
    }

}
