package com.innovati.felipehernandez.invenenvios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.innovati.felipehernandez.invenenvios.clases.jdbc.ResourceManager;

public class SettingActivity extends AppCompatActivity {
    EditText ip, puerto, base, user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ip = findViewById(R.id.txtIp);
        puerto = findViewById(R.id.txtPurto);
        user = findViewById(R.id.txtUser);
        base = findViewById(R.id.txtBase);
        pass = findViewById(R.id.txtPassword);

        ip.setText(ResourceManager.addressIp);
        puerto.setText(ResourceManager.port);
        base.setText(ResourceManager.nameData);
        user.setText(ResourceManager.JDBC_USER);
        pass.setText(ResourceManager.JDBC_PASSWORD);

    }

    public void saveSetting(View view){
        ResourceManager.addressIp = ip.getText().toString();
        ResourceManager.port = puerto.getText().toString();
        ResourceManager.nameData = base.getText().toString();
        ResourceManager.JDBC_USER = user.getText().toString();
        ResourceManager.JDBC_PASSWORD = pass.getText().toString();
        finish();
    }
}
