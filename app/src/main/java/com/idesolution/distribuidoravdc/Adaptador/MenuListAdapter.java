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

import java.util.ArrayList;
import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MenuViewHolder> implements View.OnClickListener {

    private  View.OnClickListener listener;
    private ArrayList<String> list_menu= new ArrayList<>();


    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        private final TextView opcion;


        private MenuViewHolder(View itemView) {
            super(itemView);

            opcion = itemView.findViewById(R.id.textNomRutaAsignada);

        }
    }

    private final LayoutInflater mInflater;

    //private List<CategoriaEntity> mCategorias; // Cached copy of words

    public MenuListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = mInflater.inflate(R.layout.fila_menu, parent, false);
        itemView.setOnClickListener(this);
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        if (list_menu != null) {
            String current = list_menu.get(position);
            holder.opcion.setText(current);
        }
    }


    public void setOpcionesMenu(ArrayList<String> lista) {

        list_menu = lista;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (list_menu != null)
            return list_menu.size();
        else return 0;
    }


}
