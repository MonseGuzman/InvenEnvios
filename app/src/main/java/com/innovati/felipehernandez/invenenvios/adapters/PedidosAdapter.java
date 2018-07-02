package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;

public class PedidosAdapter extends BaseAdapter
{
    private Context context;
    private VwClientes lista[];// editar
    private int layout;

    //editar
    public PedidosAdapter(Context context, int layout, VwClientes lista[])
    {
        this.context = context;
        this.layout = layout;
        this.lista = lista;
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

        //editar
        VwClientes clientes = lista[position];
        vh.FolioTextView_P.setText(clientes.getNombre());
        vh.FechaTextView_P.setText(clientes.getRfc());
        vh.EstatusCheckbox_P.setText(clientes.getTelefono());
        vh.TotalTextView_P.setText(clientes.getRfc());
        vh.ClienteTextView_P.setText(clientes.getTelefono());

        return convertView;
    }

    public class ViewHolder
    {
        TextView FolioTextView_P, FechaTextView_P, TotalTextView_P, ClienteTextView_P;
        CheckBox EstatusCheckbox_P;
    }
}
