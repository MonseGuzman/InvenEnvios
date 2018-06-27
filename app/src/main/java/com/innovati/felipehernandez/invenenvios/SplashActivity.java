package com.innovati.felipehernandez.invenenvios;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.innovati.felipehernandez.invenenvios.activitys.LoginActivity;
import com.innovati.felipehernandez.invenenvios.activitys.MenuActivity;

public class SplashActivity extends AppCompatActivity
{
    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        Intent perfil = new Intent(this, MenuActivity.class);
        Intent login = new Intent(this, LoginActivity.class);

        if(!TextUtils.isEmpty(getPassPreferences(preferences)) && !TextUtils.isEmpty(getUserPreferences(preferences)))
        {
            String email = getUserPreferences(preferences);
            perfil.putExtra("usuario", email);
            startActivity(perfil);
        }
        else
            startActivity(login);

        finish();
    }

    public static String getUserPreferences(SharedPreferences preferences)
    {
        return preferences.getString("usuario", "");
    }

    public static String getPassPreferences(SharedPreferences preferences)
    {
        return preferences.getString("contraseña", "");
    }
}
