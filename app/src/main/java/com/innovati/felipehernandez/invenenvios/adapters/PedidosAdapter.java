package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PedidosAdapter extends BaseAdapter
{
    private Context context;
    private Pedidos lista[];
    private int layout;
    private int tipo; //CONSULTA - 1 ENTREGA 2

    //editar
    public PedidosAdapter(Context context, int layout, Pedidos lista[], int tipo)
    {
        this.context = context;
        this.layout = layout;
        this.lista = lista;
        this.tipo = tipo;
    }

    @Override
    public int getCount() {
        return lista.length;
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
            vh.EstatusCheckbox_P = (CheckBox) convertView.findViewById(R.id.EstatusCheckbox_P);
            vh.TotalTextView_P = (TextView) convertView.findViewById(R.id.TotalTextView_P);
            vh.ClienteTextView_P = (TextView) convertView.findViewById(R.id.ClienteTextView_P);

            convertView.setTag(vh);
        }
        else
            vh = (PedidosAdapter.ViewHolder) convertView.getTag();

        Pedidos pedidos = lista[position];
        vh.FolioTextView_P.setText(pedidos.getFolio());
        //fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        vh.FechaTextView_P.setText(dateFormat.format(pedidos.getFecha()));

        if(pedidos.getEstatus() == 1)
            vh.EstatusCheckbox_P.setSelected(true);
        else
            vh.EstatusCheckbox_P.setSelected(false);

        vh.TotalTextView_P.setText("Total: $" + String.valueOf(pedidos.getTotal()));
        //vh.ClienteTextView_P.setText(pedidos.getIdUsuario());

        if(tipo == 2) //si es una entrega
        {
            vh.EstatusCheckbox_P.setVisibility(View.VISIBLE);

            if (pedidos.getEstatus() == 1)
                vh.EstatusCheckbox_P.setSelected(true);
            else
                vh.EstatusCheckbox_P.setSelected(false);
        }
        else
            vh.EstatusCheckbox_P.setVisibility(View.INVISIBLE);

        return convertView;
    }

    public class ViewHolder
    {
        TextView FolioTextView_P, FechaTextView_P, TotalTextView_P, ClienteTextView_P;
        CheckBox EstatusCheckbox_P;
    }
}
