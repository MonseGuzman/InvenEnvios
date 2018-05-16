package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;

public class ClientesAdaptador extends BaseAdapter
{
    private Context context;
    //private List<Board> lista; agregar Lista de clientes
    private int layaout;

    public ClientesAdaptador(Context context, int layaout) {
        this.context = context;
        this.layaout = layaout;
    }

    @Override
    public int getCount() {
        //return lista.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        //return lista.get(position);
        return 0;
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

        vh.nombreCliente.setText("prueba");
        vh.saldoCliente.setText("$10");
        return convertView;
    }

    public class ViewHolder
    {
        TextView nombreCliente, saldoCliente;

    }
}
