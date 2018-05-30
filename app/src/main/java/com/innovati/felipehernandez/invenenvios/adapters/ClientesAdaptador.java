package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.Clientes;
import com.innovati.felipehernandez.invenenvios.R;

import java.util.List;

public class ClientesAdaptador extends BaseAdapter
{
    private Context context;
    private List<Clientes> lista; //agregar Lista de clientes
    private int layout;

    public ClientesAdaptador(Context context, List<Clientes> lista, int layout) {
        this.context = context;
        this.lista = lista;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.nombreCliente = (TextView)convertView.findViewById(R.id.NombreClienteTextView_C);
            vh.RFCCliente = (TextView)convertView.findViewById(R.id.SaldoTextView_C);

            convertView.setTag(vh);
        }
        else
        {
            vh = (ViewHolder) convertView.getTag();
        }

        Clientes clientes = lista.get(position);
        vh.nombreCliente.setText(clientes.getNombre());
        vh.RFCCliente.setText(clientes.getRFC());
        return convertView;
    }

    public class ViewHolder
    {
        TextView nombreCliente, RFCCliente;

    }
}
