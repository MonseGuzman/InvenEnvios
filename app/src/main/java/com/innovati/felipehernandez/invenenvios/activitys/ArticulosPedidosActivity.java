package com.innovati.felipehernandez.invenenvios.activitys;

import android.os.SystemClock;
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
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ArticulosPedidosActivity extends AppCompatActivity {
    List<ArticulosPedido> articulosPedidoList;
    private RecyclerView recyclerArticulos;
    private String nombre, clave;
    private TextView tvClientePedido, tvFolio, tvPrecioPedido;
    ArticulosPedidosAdapter articulosPedidosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos_pedidos);
        tvClientePedido = findViewById(R.id.tvClientePedido);
        tvFolio = findViewById(R.id.tvFolioPedido);
        tvPrecioPedido = findViewById(R.id.tvPrecioPedido);
        Gson gson = new Gson();
        Bundle bundle = getIntent().getExtras();
        nombre =bundle.getString("nombre");
        clave = bundle.getString("clave");
        String stringLocation = bundle.getString("articulos");
        if(stringLocation != null) {
            Type type = new TypeToken<List<ArticulosPedido>>() {
            }.getType();
            articulosPedidoList = gson.fromJson(stringLocation, type);
            Log.d("Location Count", Integer.toString(articulosPedidoList.size()));
            updateAdapter();
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
        articulosPedidosAdapter = new ArticulosPedidosAdapter(this,articulosPedidoList);
        recyclerArticulos.setAdapter(articulosPedidosAdapter);
    }
    private  void queryArticle(int position){
       //
    }

    public void registrarPedido(View view){
        for (int x = 0; x < articulosPedidoList.size(); x++){
            List<ArticulosPedido> a;
            pedido(articulosPedidoList.get(x).getIdArticulo(),articulosPedidoList.get(x).getPrecio(),articulosPedidoList.get(x).getCantidad(),articulosPedidoList.get(x).getSubTotal(),articulosPedidoList.get(x).getIva(),articulosPedidoList.get(x).getTotal());
        }
    }

    public void pedido(String clave, double precio, double cantidad, double subTotal, double iva, double total ){

    }

    public void insertar(String idUsuario, String claveCliente, Date fecha, short estatus, float subtotal, float iva, float total, String observaciones)
    {
        String idPedido = UUID.randomUUID().toString();
        Pedidos pedidos = new Pedidos();
        pedidos.setIdPedido(idPedido);
        pedidos.setIdUsuario(idUsuario);
        pedidos.setClaveCliente(claveCliente);
        pedidos.setFecha(fecha);
        pedidos.setEstatus(estatus);
        pedidos.setSubtotal(subtotal);
        pedidos.setIva(iva);
        pedidos.setTotal(total);
        pedidos.setObservaciones(observaciones);
        pedidos.setUltimoUsuarioActualizacion(idUsuario);
        pedidos.setUltimaFechaActualizacion(Calendar.getInstance().getTime());

    }
}
