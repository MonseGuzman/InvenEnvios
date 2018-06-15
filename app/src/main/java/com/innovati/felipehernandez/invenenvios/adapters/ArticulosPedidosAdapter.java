package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.util.List;

public class ArticulosPedidosAdapter extends RecyclerView.Adapter<ArticulosPedidosAdapter.ViewHolder>{
    Context context;
    public List<ArticulosPedido> articulosPedidos;

    public ArticulosPedidosAdapter(Context context, List<ArticulosPedido> articulosPedidos){
        this.context = context;
        this.articulosPedidos = articulosPedidos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articulo_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNombreArticuloItem.setText(articulosPedidos.get(position).getNombre());
        holder.tvPercentacionArticuloItem.setText(articulosPedidos.get(position).getPresentacion());
        holder.tvCountArticuloItem.setText("Cantidad: " + articulosPedidos.get(position).getCantidad());
        holder.tvPriceArticuloItem.setText("precio: "+articulosPedidos.get(position).getPrecio());
        holder.tvSubArticuloItem.setText("Sub: "+articulosPedidos.get(position).getSubTotal());
    }

    @Override
    public int getItemCount() {
        return articulosPedidos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvNombreArticuloItem, tvPercentacionArticuloItem,tvCountArticuloItem,tvPriceArticuloItem,tvSubArticuloItem;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView =itemView.findViewById(R.id.cardview);
            tvNombreArticuloItem = itemView.findViewById(R.id.tvNombreArticuloItem);
            tvPercentacionArticuloItem = itemView.findViewById(R.id.tvPercentacionArticuloItem);
            tvCountArticuloItem = itemView.findViewById(R.id.tvCountArticuloItem);
            tvPriceArticuloItem = itemView.findViewById(R.id.tvPriceArticuloItem);
            tvSubArticuloItem = itemView.findViewById(R.id.tvSubArticuloItem);
        }
    }
}
