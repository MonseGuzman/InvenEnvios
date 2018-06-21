package com.innovati.felipehernandez.invenenvios.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.innovati.felipehernandez.invenenvios.R;

import java.util.List;

public class ArticulosPedidosActivity extends AppCompatActivity {
    List<ArticulosPedidosActivity> articulos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos_pedidos);
    }
}
