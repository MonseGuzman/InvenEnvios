package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;

public class ClientesAdaptador extends BaseAdapter
{
    private Context context;
    private VwClientes lista[];// agregar Lista de clientes
    private int layaout;

    public ClientesAdaptador(Context context, int layaout, VwClientes lista[])
    {
        this.context = context;
        this.layaout = layaout;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        //return lista.size();
        return lista.length;
    }

    @Override
    public Object getItem(int position) {
        //return lista.get(position);
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
            convertView = LayoutInflater.from(context).inflate(layaout, null);
            vh = new ViewHolder();
            vh.nombreCliente = (TextView)convertView.findViewById(R.id.NombreClienteTextView_C);
            vh.saldoCliente = (TextView)convertView.findViewById(R.id.SaldoTextView_C);

            convertView.setTag(vh);
        }
        else
        {
            vh = (ViewHolder) convertView.getTag();
        }


        vh.nombreCliente.setText(lista[position].getNombre());
        vh.saldoCliente.setText(lista[position].getRfc());
        return convertView;
    }

    public class ViewHolder
    {
        TextView nombreCliente, saldoCliente;

    }
}
