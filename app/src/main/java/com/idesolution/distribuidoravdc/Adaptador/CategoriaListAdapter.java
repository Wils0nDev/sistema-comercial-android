package com.idesolution.distribuidoravdc.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idesolution.distribuidoravdc.BD.Entity.CategoriaEntity;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

public class CategoriaListAdapter extends RecyclerView.Adapter<CategoriaListAdapter.CategoriaViewHolder> implements View.OnClickListener {

    private  View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

    class CategoriaViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private CategoriaViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textNomCategoria);
        }
    }

    private final LayoutInflater mInflater;
    private List<CategoriaEntity> mCategorias; // Cached copy of words
    public CategoriaListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = mInflater.inflate(R.layout.fila_categoria, parent, false);
        itemView.setOnClickListener(this);
        return new CategoriaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        if (mCategorias != null) {
            CategoriaEntity current = mCategorias.get(position);
            holder.wordItemView.setText(current.getDescripcion());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }


    public void setCategorias(List<CategoriaEntity> categorias) {
        mCategorias = categorias;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCategorias != null)
            return mCategorias.size();
        else return 0;
    }
}
