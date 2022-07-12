package com.idesolution.distribuidoravdc.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.idesolution.distribuidoravdc.BD.Entity.TuplaListaProdEntity;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

public class ProductoPreventaListAdapter extends RecyclerView.Adapter<ProductoPreventaListAdapter.ProductoPreventaViewHolder> implements View.OnClickListener {

    class ProductoPreventaViewHolder extends RecyclerView.ViewHolder {
        private final TextView ItemDescripcion;
        private final TextView ItemPrecio;
        private final TextView ItemStock;

        private ProductoPreventaViewHolder(View itemView) {
            super(itemView);
            ItemDescripcion = itemView.findViewById(R.id.tvDescProducto);
            ItemPrecio = itemView.findViewById(R.id.tvPrecioProducto);
            ItemStock = itemView.findViewById(R.id.tvStockProducto);
        }
    }
    private  View.OnClickListener listener;
    private final LayoutInflater mInflater;
    private List<TuplaListaProdEntity> mProductos;

    public ProductoPreventaListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public ProductoPreventaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fila_producto, parent, false);
        itemView.setOnClickListener(this);
        return new ProductoPreventaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductoPreventaViewHolder holder, int position) {
        if (mProductos != null) {
            TuplaListaProdEntity productoEntity = mProductos.get(position);
            holder.ItemDescripcion.setText(productoEntity.getDescripcion() + "-" + productoEntity.getCodProducto());
            holder.ItemPrecio.setText(String.format ("%.2f", productoEntity.getPrecioVenta()));
            holder.ItemStock.setText(( String.valueOf( productoEntity.getStock())));
        }
    }

    public void setProductos(List<TuplaListaProdEntity> productos){
        mProductos = productos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mProductos != null)
            return mProductos.size();
        else return 0;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }
}
