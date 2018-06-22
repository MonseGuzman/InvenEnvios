package com.innovati.felipehernandez.invenenvios.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ArticulosPedidosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.RecycleViewOnItemClickListener;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.lang.reflect.Type;
import java.util.List;

public class ArticulosPedidosActivity extends AppCompatActivity {
    List<ArticulosPedidosActivity> articulosPedidoList;
    private RecyclerView recyclerArticulos;
    private String nombre, clave;
    private TextView tvClientePedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos_pedidos);
        tvClientePedido = findViewById(R.id.tvClientePedido);
        Gson gson = new Gson();
        Bundle bundle = getIntent().getExtras();
        nombre =bundle.getString("nombre");
        clave = bundle.getString("clave");
        String stringLocation = bundle.getString("articulos");
        if(stringLocation != null) {
            Log.d("Location Count", stringLocation);
            Type type = new TypeToken<List<ArticulosPedido>>() {
            }.getType();
            articulosPedidoList = gson.fromJson(stringLocation, type);
            Log.d("Location Count", Integer.toString(articulosPedidoList.size()));
        }
        else{
            Log.d("Location Count","failed");
        }
        recyclerArticulos = findViewById(R.id.recycleCarrito);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerArticulos.setLayoutManager(linearLayoutManager);
        tvClientePedido.setText(nombre);
    }
    private void updateAdapter(){
        /*recyclerArticulos.setAdapter(new ArticulosPedidosAdapter(articulosPedidosActivityList, new RecycleViewOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                queryArticle(position);
            }
        }));*/
        //ArticulosPedidosAdapter adapter = new ArticulosPedidosAdapter(articulosPedidoList);
        //recyclerArticulos.setAdapter(adapter);
    }
    private  void queryArticle(int position){
       //
    }
}