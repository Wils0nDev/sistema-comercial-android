package com.idesolution.distribuidoravdc.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCliente;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

public class ClienteListAdapter extends RecyclerView.Adapter<ClienteListAdapter.ClienteViewHolder> implements View.OnClickListener {

    class ClienteViewHolder extends RecyclerView.ViewHolder {
        private final TextView clienteItemView;
        private final TextView ndocItemView;

        private ClienteViewHolder(View itemView) {
            super(itemView);
            clienteItemView = itemView.findViewById(R.id.tvNombreCliente);
            ndocItemView = itemView.findViewById(R.id.tvNumeroDocumento);
        }
    }

    private  View.OnClickListener listener;
    private final LayoutInflater mInflater;
    private List<FilaCliente> mCliente;

    public ClienteListAdapter(Context context) { mInflater = LayoutInflater.from(context); }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fila_cliente, parent, false);
        itemView.setOnClickListener(this);
        return new ClienteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClienteViewHolder holder, int position) {
        String nom = null;
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
            if(current.getTipoPersona() == 2) {
                holder.ndocItemView.setText(current.getRuc());
                holder.clienteItemView.setText(rzo);
            }
            else {
                holder.ndocItemView.setText(current.getNumeroDocumento());
                holder.clienteItemView.setText(nom + " " + app + " " + apm);
            }
        }
    }

    public void setClientes(List<FilaCliente> clientes){
        mCliente = clientes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCliente != null)
            return mCliente.size();
        else return 0;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }
}
