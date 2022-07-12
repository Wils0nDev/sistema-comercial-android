package com.idesolution.distribuidoravdc.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

public class PromocionListAdapter extends RecyclerView.Adapter<PromocionListAdapter.PromocionViewHolder> implements View.OnClickListener {

    class PromocionViewHolder extends RecyclerView.ViewHolder {
        private final TextView titulo;
        private final TextView descripcion;

        private PromocionViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tvTitulo_filapromo);
            descripcion = itemView.findViewById(R.id.tvDescripcion_filapromo);
        }
    }

    private  View.OnClickListener listener;
    private final LayoutInflater mInflater;
    private List<PromocionEntity> listaPromociones;

    public PromocionListAdapter(Context context) { mInflater = LayoutInflater.from(context); }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public PromocionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fila_promocion, parent, false);
        itemView.setOnClickListener(this);
        return new PromocionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PromocionViewHolder holder, int position) {
        if (listaPromociones != null) {
            PromocionEntity p =  listaPromociones.get(position);
            holder.titulo.setText(String.valueOf(p.getNtra()));
            holder.descripcion.setText(p.getDescripcion());
        }
    }

    public void setPromociones(List<PromocionEntity> lista){
        listaPromociones = lista;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (listaPromociones != null)
            return listaPromociones.size();
        else return 0;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

}
