package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;

public class ClientesAdaptador extends BaseAdapter
{
    private Context context;
    private VwClientes lista[];// agregar Lista de clientes
    private int layout;

    public ClientesAdaptador(Context context, int layaout, VwClientes lista[])
    {
        this.context = context;
        this.layout = layaout;
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
        ViewHolder vh;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.nombreCliente = (TextView)convertView.findViewById(R.id.NombreClienteTextView_C);
            vh.RFCCliente = (TextView)convertView.findViewById(R.id.RFCTextView_C);
            vh.telefonoCliente = (TextView) convertView.findViewById(R.id.TelefonoTextView_C);

            convertView.setTag(vh);
        }
        else
            vh = (ViewHolder) convertView.getTag();


        VwClientes clientes = lista[position];
        vh.nombreCliente.setText(clientes.getNombre());
        vh.RFCCliente.setText(clientes.getRfc());
        vh.telefonoCliente.setText(clientes.getTelefono());

        return convertView;
    }

    public class ViewHolder
    {
        TextView nombreCliente, RFCCliente, telefonoCliente;
    }

}
