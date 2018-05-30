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

public class ArticuloAdapter extends BaseAdapter
{
    private Context context;
    private List<Clientes> lista; //agregar Lista de clientes
    private int layaout;

    public ArticuloAdapter(Context context, List<Clientes> lista, int layaout) {
        this.context = context;
        this.lista = lista;
        this.layaout = layaout;
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
        ArticuloAdapter.ViewHolder vh;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layaout, null);
            vh = new ArticuloAdapter.ViewHolder();
            vh.nombreArticulo = (TextView)convertView.findViewById(R.id.articuloTextView_A);
            vh.existencia = (TextView)convertView.findViewById(R.id.ExistenciasTextView_A);

            convertView.setTag(vh);
        }
        else
        {
            vh = (ArticuloAdapter.ViewHolder) convertView.getTag();
        }

        Clientes clientes = lista.get(position);
        vh.nombreArticulo.setText(clientes.getNombre());
        vh.existencia.setText(clientes.getRFC());
        return convertView;
    }

    public class ViewHolder
    {
        TextView nombreArticulo, existencia;

    }
}
