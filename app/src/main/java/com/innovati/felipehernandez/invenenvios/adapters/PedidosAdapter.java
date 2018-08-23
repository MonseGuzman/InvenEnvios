package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwPedidos;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class PedidosAdapter extends BaseAdapter
{
    private Context context;
    private VwPedidos lista[];
    private int layout;

    public PedidosAdapter(Context context, int layout, VwPedidos lista[])
    {
        this.context = context;
        this.layout = layout;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        if(lista != null)
            return lista.length;
        else return 0;
    }

    @Override
    public Object getItem(int position) {
        return lista[position];
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PedidosAdapter.ViewHolder vh;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new PedidosAdapter.ViewHolder();
            vh.FolioTextView_P = (TextView)convertView.findViewById(R.id.FolioTextView_P);
            vh.FechaTextView_P = (TextView)convertView.findViewById(R.id.FechaTextView_P);
            vh.TotalTextView_P = (TextView) convertView.findViewById(R.id.TotalTextView_P);
            vh.ClienteTextView_P = (TextView) convertView.findViewById(R.id.ClienteTextView_P);
            vh.CantidadDetallesTextView = (TextView) convertView.findViewById(R.id.CantidadDetallesTextView);

            convertView.setTag(vh);
        }
        else
            vh = (PedidosAdapter.ViewHolder) convertView.getTag();

        VwPedidos pedidos = lista[position];
        String estado;

        vh.FolioTextView_P.setText("Folio: "+String.valueOf(pedidos.getFolio()));
        //fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        vh.FechaTextView_P.setText(dateFormat.format(pedidos.getFecha()));
        vh.TotalTextView_P.setText("Total: $" + String.valueOf(pedidos.getTotal()));
        vh.ClienteTextView_P.setText(pedidos.getNombre());

        switch (pedidos.getEstatus())
        {
            case 0:
                estado = "Estatus: Ninguno";
                break;
            case 1:
                estado = "Estatus: Proceso";
                break;
            case 2:
                estado = "Estatus: Cancelado";
                break;
            case 3:
                estado = "Estatus: Surtido";
                break;
            case 4:
                estado = "Estatus: Entregado";
                break;
                default:
                    estado = "Error";
            //0: Ninguno, 1:Proceso, 2:Cancelado, 3: Surtido, 4:Entregado (Devuelto?)
        }
        vh.CantidadDetallesTextView.setText(estado);

        return convertView;
    }

    public class ViewHolder
    {
        TextView FolioTextView_P, FechaTextView_P, TotalTextView_P, ClienteTextView_P, CantidadDetallesTextView;
    }
}
