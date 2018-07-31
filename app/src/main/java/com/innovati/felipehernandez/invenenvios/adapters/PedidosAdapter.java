package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
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
    private int tipo; //CONSULTA - 1 ENTREGA 2

    public PedidosAdapter(Context context, int layout, VwPedidos lista[], int tipo)
    {
        this.context = context;
        this.layout = layout;
        this.lista = lista;
        this.tipo = tipo;
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
            vh.EstatusCheckbox_P = (CheckBox) convertView.findViewById(R.id.EstatusCheckbox_P);
            vh.TotalTextView_P = (TextView) convertView.findViewById(R.id.TotalTextView_P);
            vh.ClienteTextView_P = (TextView) convertView.findViewById(R.id.ClienteTextView_P);

            convertView.setTag(vh);
        }
        else
            vh = (PedidosAdapter.ViewHolder) convertView.getTag();

        VwPedidos pedidos = lista[position];

        vh.FolioTextView_P.setText(String.valueOf(pedidos.getFolio()));
        //fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        vh.FechaTextView_P.setText(dateFormat.format(pedidos.getFecha()));
        vh.TotalTextView_P.setText("Total: $" + String.valueOf(pedidos.getTotal()));
        vh.ClienteTextView_P.setText(pedidos.getNombre());

        if(tipo == 2) //si es una entrega
        {
            vh.EstatusCheckbox_P.setVisibility(View.VISIBLE);

            if (pedidos.getEstatus() == 2)
                vh.EstatusCheckbox_P.setChecked(true);
            else
                vh.EstatusCheckbox_P.setChecked(false);
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
