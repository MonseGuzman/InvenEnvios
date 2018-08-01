package com.innovati.felipehernandez.invenenvios.adapters;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwPedidos;


import java.text.SimpleDateFormat;
import java.util.Locale;

public class EntregasRecycleView extends RecyclerView.Adapter<EntregasRecycleView.ViewHolder> {
    private RecycleViewOnItemClickListener recyclerViewOnItemClickListener;
    private Context context;
    public VwPedidos lista[];
    //CONSULTA - 1 ENTREGA 2
    private String nombre = "Monse";

    public EntregasRecycleView( Context context, VwPedidos lista[],@NonNull RecycleViewOnItemClickListener recyclerViewOnItemClickListener)
    {
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_pedidos,parent,false);
        EntregasRecycleView.ViewHolder viewHolder = new EntregasRecycleView.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VwPedidos pedidos = lista[position];

        holder.FolioTextView_P.setText(String.valueOf(pedidos.getFolio()));
        //fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        holder.FechaTextView_P.setText(dateFormat.format(pedidos.getFecha()));
        holder.TotalTextView_P.setText("Total: $" + String.valueOf(pedidos.getTotal()));
        if(pedidos.getEstatus() == 4){
            holder.EntregasTable.setBackgroundColor(Color.rgb(176,236,222));
        }else{
            holder.EntregasTable.setBackgroundColor(Color.WHITE);
        }
        holder.ClienteTextView_P.setText(" "+String.valueOf(pedidos.getNombre()));
    }

    @Override
    public int getItemCount() {
        if(lista != null)
            return lista.length;
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView FolioTextView_P, FechaTextView_P, TotalTextView_P, ClienteTextView_P;
        CheckBox EstatusCheckbox_P;
        TableLayout EntregasTable;

        public ViewHolder(View itemView) {
            super(itemView);
            FolioTextView_P = (TextView)itemView.findViewById(R.id.FolioTextView_P);
            FechaTextView_P = (TextView)itemView.findViewById(R.id.FechaTextView_P);
            EstatusCheckbox_P = (CheckBox) itemView.findViewById(R.id.EstatusCheckbox_P);
            TotalTextView_P = (TextView) itemView.findViewById(R.id.TotalTextView_P);
            ClienteTextView_P = (TextView) itemView.findViewById(R.id.ClienteTextView_P);
            EntregasTable = (TableLayout) itemView.findViewById(R.id.EntregasTable);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewOnItemClickListener.onClick(view,getAdapterPosition());
        }
    }
}
