package com.idesolution.distribuidoravdc.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.idesolution.distribuidoravdc.BD.Entity.EntregaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDireccion;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

public class DireccionListAdapter extends RecyclerView.Adapter<DireccionListAdapter.DireccionViewHolder> implements View.OnClickListener {
    class DireccionViewHolder extends RecyclerView.ViewHolder {
        private final TextView direccion;

        private DireccionViewHolder(View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvDireccion_seleccion);
        }
    }

    private  View.OnClickListener listener;
    private final LayoutInflater mInflater;
    private List<FilaDireccion> mEntrega;

    public DireccionListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public DireccionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fila_direccion, parent, false);
        itemView.setOnClickListener(this);
        return new DireccionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DireccionViewHolder holder, int position) {
        FilaDireccion current = mEntrega.get(position);
        holder.direccion.setText(current.getDireccion());
        /*String nom = null;
        String app = null;
        String apm = null;
        String rzo = null;
        if (mCliente != null) {
            FilaCliente current = mCliente.get(position);
            nom = current.getNombres();
            app = current.getApellidoPaterno();
            apm = current.getApellidoMaterno();
            rzo = current.getRazonSocial();
            if (nom == null)
                nom = "";
            if (app == null)
                app = "";
            if (apm == null)
                apm = "";
            if (rzo == null)
                rzo = "";
            if(current.getTipoDocumento() == 3) {
                holder.ndocItemView.setText(current.getRuc());
                holder.clienteItemView.setText(rzo);
            }
            else {
                holder.ndocItemView.setText(current.getNumeroDocumento());
                holder.clienteItemView.setText(nom + " " + app + " " + apm);
            }
        }*/
    }

    public void setDirecciones(List<FilaDireccion> entrega){
        mEntrega = entrega;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mEntrega != null)
            return mEntrega.size();
        else return 0;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

}
