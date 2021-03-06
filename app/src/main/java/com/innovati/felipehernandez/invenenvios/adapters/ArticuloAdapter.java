package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.ArticuloActivity;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;

public class ArticuloAdapter extends BaseAdapter
{
    private Context context;
    private VwArticulos lista[];
    private int layaout;

    public ArticuloAdapter(Context context, int layaout, VwArticulos lista[])
    {
        this.context = context;
        this.layaout = layaout;
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
        //return lista.get(position);
        return lista[position];
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
            vh = new ViewHolder();
            vh.nombreArticulo = (TextView)convertView.findViewById(R.id.articuloTextView_A);
            vh.existencia = (TextView)convertView.findViewById(R.id.ExistenciasTextView_A);
            vh.IDArticulo = (TextView)convertView.findViewById(R.id.IDTextView_A);

            convertView.setTag(vh);
        }
        else
        {
            vh = (ArticuloAdapter.ViewHolder) convertView.getTag();
        }
        vh.nombreArticulo.setText(lista[position].getNombre());
        vh.existencia.setText("Existencias: " +String.valueOf(lista[position].getExistenciaTotal()));
        vh.IDArticulo.setText(lista[position].getClave());

        //se puede agregar el onclick
        return convertView;
    }

    public class ViewHolder
    {
        TextView nombreArticulo, existencia, IDArticulo;
    }

}
