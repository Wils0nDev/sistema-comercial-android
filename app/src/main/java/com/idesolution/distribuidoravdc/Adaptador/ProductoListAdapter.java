package com.idesolution.distribuidoravdc.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idesolution.distribuidoravdc.BD.Entity.TuplaListaProdEntity;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

public class ProductoListAdapter extends RecyclerView.Adapter<ProductoListAdapter.ProductoViewHolder> implements View.OnClickListener {

    private  View.OnClickListener listener;
    private final LayoutInflater mInflater;
    //private List<ProductoEntity> mProductos;
    private List<TuplaListaProdEntity> mProductos;


    public ProductoListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        private final TextView ItemDescripcion;
        private final TextView ItemPrecio;
        private final TextView ItemStock;
        private final TextView tvNomPrecio;
        private ProductoViewHolder(View itemView) {
            super(itemView);
            ItemDescripcion = itemView.findViewById(R.id.tvDescProducto);
            ItemPrecio = itemView.findViewById(R.id.tvPrecioProducto);
            ItemStock = itemView.findViewById(R.id.tvStockProducto);
            tvNomPrecio = itemView.findViewById(R.id.textView12);
        }
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fila_producto,parent,false);
        itemView.setOnClickListener(this);
        return new ProductoViewHolder(itemView);
    }
/*
    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        if (mProductos != null){
            ProductoEntity productoEntity = mProductos.get(position);
            holder.ItemDescripcion.setText(productoEntity.getDescripcion());
            holder.ItemPrecio.setText(( String.valueOf( productoEntity.getCodCategoria())));
            holder.ItemStock.setText("0");

        }else{
            holder.ItemDescripcion.setText("SIN REGISTROS");
        }
    }

    public void setProductos(List<ProductoEntity> productos) {
        mProductos = productos;
        notifyDataSetChanged();
    }
*/
    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        if (mProductos != null){
            TuplaListaProdEntity productoEntity = mProductos.get(position);
            holder.ItemDescripcion.setText(productoEntity.getDescripcion());
            if(productoEntity.getPrecioVenta()> 0){
                holder.ItemPrecio.setText(( String.valueOf( productoEntity.getPrecioVenta())));
            }else{
                holder.ItemPrecio.setText(Constante.G_CONST_VACIO);
                holder.tvNomPrecio.setText(Constante.G_CONST_VACIO);
            }

            holder.ItemStock.setText(( String.valueOf( productoEntity.getStock())));

        }else{
            holder.ItemDescripcion.setText("SIN REGISTROS");
        }
    }

    public void setProductos(List<TuplaListaProdEntity> productos) {
        mProductos = productos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mProductos != null)
            return mProductos.size();
        else return 0;
    }

    public void clear() {
        int size = mProductos.size();
        mProductos.clear();
        notifyItemRangeRemoved(0, size);
    }


}
