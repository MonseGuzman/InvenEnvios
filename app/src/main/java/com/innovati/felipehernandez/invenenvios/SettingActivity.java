package com.innovati.felipehernandez.invenenvios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.clases.jdbc.ResourceManager;

public class SettingActivity extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting);
        t1 = findViewById(R.id.txtIp);
        t2 = findViewById(R.id.txtPort);
        t3 = findViewById(R.id.txtDataBase);
        t4 = findViewById(R.id.txtUsuario);
        t5 = findViewById(R.id.txtPassword);
        t1.setText(ResourceManager.addressIp);
        t2.setText(ResourceManager.port);
        t3.setText(ResourceManager.nameData);
        t4.setText(ResourceManager.JDBC_USER);
        t5.setText(ResourceManager.JDBC_PASSWORD);
    }

    public void updateData(View view){
        ResourceManager.addressIp = t1.getText().toString();
        ResourceManager.port = t2.getText().toString();
        ResourceManager.nameData = t3.getText().toString();
        ResourceManager.JDBC_USER = t4.getText().toString();
        ResourceManager.JDBC_PASSWORD = t5.getText().toString();
        finish();
    }
}
