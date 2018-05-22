package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;

public class LoginActivity extends AppCompatActivity
{
    private SharedPreferences preferences;
    private CheckBox chRecordarme;
    private EditText etUsuario, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        MetodosInternos wifi = new MetodosInternos(this);

        if(wifi.conexionRed())
        {
            //si la conexion wifi es conectada
        }
        Intent i = new Intent(this, MenuActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        guardarPreferencias(etUsuario.getText().toString(), etPassword.getText().toString());
    }

    private void Alerta(String titulo, String mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
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

    private void guardarPreferencias(String email, String contra)
    { //cuando abre la actividad
        if (chRecordarme.isChecked()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("usuario", email);
            editor.putString("contraseña", contra);
            editor.commit(); // empieza a guardar los put*
            editor.apply(); //guarda todos los cambios aunque no se guarden todos
        }
    }
}
