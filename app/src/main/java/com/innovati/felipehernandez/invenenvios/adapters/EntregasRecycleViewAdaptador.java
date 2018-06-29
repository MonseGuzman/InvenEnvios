package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;

public class EntregasRecycleViewAdaptador extends RecyclerView.Adapter<EntregasRecycleViewAdaptador.ViewHolder>
{
    private VwClientes lista[];
    private int layout;
    private OnItemClickListener listener;
    private Context context;

    public EntregasRecycleViewAdaptador(VwClientes lista[], int layout, OnItemClickListener listener) {
        this.lista = lista;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        //cambiar datos del layout
        holder.bind(lista[position], listener);
    }

    @Override
    public int getItemCount() {
        if(lista != null)
            return lista.length;
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView NombreApellidoTextView;
        public TextView RFCTextView_RV;

        public ViewHolder(View v)
        {
            super(v);
            NombreApellidoTextView = (TextView)v.findViewById(R.id.NombreApellidoTextView);
            RFCTextView_RV = (TextView) v.findViewById(R.id.RFCTextView_RV);
        }

        public void bind(final VwClientes datos, final OnItemClickListener listener)
        {
            NombreApellidoTextView.setText(datos.getNombre());
            RFCTextView_RV.setText(datos.getRfc());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(datos, getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener
    {
        void onItemClick(VwClientes datos, int posicion);
    }
}
