package com.innovati.felipehernandez.invenenvios.adapters;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwAbastecimiento;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwDetallePedido;

public class ListaArticulosAdapter extends RecyclerView.Adapter<ListaArticulosAdapter.ViewHolder>
{
    private Animation animationUp, animationDown;
    private Context context;
    private final int COUNTDOWN_RUNNING_TIME = 500;
    private VwAbastecimiento lista[];

    public ListaArticulosAdapter(Context context, Animation animationUp, Animation animationDown, VwAbastecimiento[] lista)
    {
        this.animationUp = animationUp;
        this.animationDown = animationDown;
        this.context = context;
        this.lista = lista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_abastecimiento, parent, false);
        context = parent.getContext();

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListaArticulosAdapter.ViewHolder holder, int position)
    {
        holder.bind(lista[position]);
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

        public void bind(final VwAbastecimiento datos)
        {
            String descripcion = String.valueOf(datos.getTotal()) + " " + datos.getUnidadPrimaria();

            NombreArticuloTextView.setText(datos.getNombre());
            DescripcionTextView.setText(descripcion);

            NombreArticuloTextView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(DescripcionTextView.isShown())
                    {
                        DescripcionTextView.startAnimation(animationUp);

                        CountDownTimer countDownTimer = new CountDownTimer(COUNTDOWN_RUNNING_TIME, 16) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                DescripcionTextView.setVisibility(View.GONE);
                            }
                        };
                        countDownTimer.start();
                    }
                    else
                    {
                        DescripcionTextView.setVisibility(View.VISIBLE);
                        DescripcionTextView.startAnimation(animationDown);
                    }
                }
            });
        }
    }
}
