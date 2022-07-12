package com.idesolution.distribuidoravdc.Adaptador;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

public class PreventaListAdapter extends RecyclerView.Adapter<PreventaListAdapter.PreventaViewHolder> implements View.OnClickListener {

    class PreventaViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNtra;
        private final TextView tvTotal;
        private final TextView tvFecha;
        private final TextView tvFlag;
        private final ImageView imgEstado;

        private PreventaViewHolder(View itemView) {
            super(itemView);
            tvNtra = itemView.findViewById(R.id.tvNtra_preventa);
            tvTotal = itemView.findViewById(R.id.tvTotal_preventa);
            tvFecha = itemView.findViewById(R.id.tvFecha_preventa);
            tvFlag = itemView.findViewById(R.id.tvFlag_preventa);
            imgEstado = itemView.findViewById(R.id.imgestadopreven);
        }
    }

    private  View.OnClickListener listener;
    private final LayoutInflater mInflater;
    private List<Preventa> mPreventa;

    public PreventaListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public PreventaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fila_preventa, parent, false);
        itemView.setOnClickListener(this);
        return new PreventaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PreventaViewHolder holder, int position) {
        Preventa current = mPreventa.get(position);
        holder.tvNtra.setText(current.getNtraPreventa().toString());
        holder.tvTotal.setText(String.format("%.2f", current.getTotal()));
        holder.tvFecha.setText(String.valueOf(current.getFecha()));

        if(current.getFlag() == Constante.g_s_const_1){
            holder.tvFlag.setText("Enviado");
            int color = Color.parseColor("#AFFD27");
            holder.imgEstado.setColorFilter(color);


        }else{
            holder.tvFlag.setText("Por Enviar");
            int color = Color.parseColor("#FF7928");
            holder.imgEstado.setColorFilter(color);
        }
    }

    public void setPreventas(List<Preventa> preventas){
        mPreventa = preventas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mPreventa != null)
            return mPreventa.size();
        else return 0;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }
}
