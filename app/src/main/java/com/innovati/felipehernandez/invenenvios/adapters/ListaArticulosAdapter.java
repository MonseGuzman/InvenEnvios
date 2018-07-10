package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dto.DetallesPedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;

public class ListaArticulosAdapter extends RecyclerView.Adapter<ListaArticulosAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private Animation animationUp, animationDown;
    private Context context;
    private final int COUNTDOWN_RUNNING_TIME = 500;
    private DetallesPedidos lista[]; //por resolver

    public ListaArticulosAdapter(Animation animationUp, Animation animationDown, Context context, DetallesPedidos[] lista)
    {
        this.layoutInflater = layoutInflater;
        this.animationUp = animationUp;
        this.animationDown = animationDown;
        this.context = context;
        this.lista = lista;
    }

    @Override
    public ListaArticulosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = layoutInflater.inflate(R.layout.item_abastecimiento, parent);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListaArticulosAdapter.ViewHolder holder, int position)
    {
        //creo que añades cosas ?
        holder.NombreArticuloTextView.setText(lista[position].getClaveArticulo());
        holder.DescripcionTextView.setText((int) lista[position].getTotal());

        holder.NombreArticuloTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.DescripcionTextView.isShown())
                {
                    holder.DescripcionTextView.startAnimation(animationUp);

                    CountDownTimer countDownTimer = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 16) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            holder.DescripcionTextView.setVisibility(View.GONE);
                        }
                    };

                    countDownTimer.start();

                    //holder.NombreArticuloTextView.setCom
                }
                else
                {
                    holder.DescripcionTextView.setVisibility(View.VISIBLE);
                    holder.DescripcionTextView.startAnimation(animationDown);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(lista != null)
            return lista.length;
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView DescripcionTextView, NombreArticuloTextView;

        public ViewHolder(View itemView)
        {
            super(itemView);

            DescripcionTextView = (TextView) itemView.findViewById(R.id.DescripcionTextView);
            NombreArticuloTextView = (TextView) itemView.findViewById(R.id.NombreArticuloTextView);
        }
    }
}
