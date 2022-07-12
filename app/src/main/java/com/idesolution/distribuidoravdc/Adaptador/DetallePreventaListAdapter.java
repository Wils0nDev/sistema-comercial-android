package com.idesolution.distribuidoravdc.Adaptador;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.idesolution.distribuidoravdc.BD.Entity.FilaDetallePreventa;
import com.idesolution.distribuidoravdc.BD.Repository.DescuentoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PreventaViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

public class DetallePreventaListAdapter extends RecyclerView.Adapter<DetallePreventaListAdapter.FilaDetallePreventaViewHolder> implements View.OnClickListener {

    private LifecycleOwner ow;
    private DescuentoViewModel descuentoVM;
    class FilaDetallePreventaViewHolder extends RecyclerView.ViewHolder {


        private final TextView nombreProducto;
        private final TextView promocion;
        private final TextView descuento;
        private final TextView precio;
        private final TextView importeDescuento;
        private final TextView cantidad;
        private final TextView unidad;
        private final ImageView imgEstadoCar1;

        private FilaDetallePreventaViewHolder(View itemView) {
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.tvProd_det);
            promocion = itemView.findViewById(R.id.tvPromo_det);
            descuento = itemView.findViewById(R.id.tvDesc_det);
            cantidad = itemView.findViewById(R.id.tvCantidad_det);
            precio = itemView.findViewById(R.id.tvPrecio_det);
            unidad = itemView.findViewById(R.id.tvUnidad_det);
            importeDescuento = itemView.findViewById(R.id.tvDescuento_det);
            imgEstadoCar1 = itemView.findViewById(R.id.imgEstadoArt1);
        }

        /*void setOnClickListeners(){
            botonQuitar.setOnClickListener(this);
        }*/

        /*@Override
        public void onClick(View v) {
            int m;
            switch (v.getId()){
                case R.id.btnQuitarProducto_carrito:
                    //db.detalleDao().quitarProductoCarrito(getAdapterPosition()) ;
                    //MetodosGlobales.mostrarMensaje(v.getContext(), getAdapterPosition() + "");
                    cabeceraVM.quitarProductoCarrito(getAdapterPosition() + Constante.g_const_1);
                    filaCarrito.remove(getAdapterPosition());
                    m = getAdapterPosition();
                    cabeceraVM.buscarItemPromocionDos(getAdapterPosition() + Constante.g_const_1).observe(nu, i -> {
                        item = getAdapterPosition() + i;
                        for(int a = m; a <= item; a = a + 1 ){
                            filaCarrito.remove(a);
                        }
                    });
                    notifyDataSetChanged();
                    //notifyItemRangeRemoved(Constante.g_const_0, filaCarrito.size());
                    //cabeceraVM = new ViewModelProvider(context.getApplicationContext().li).get(CabeceraViewModel.class);
                    break;

            }
        }*/
    }

    private  View.OnClickListener listener;
    private final LayoutInflater mInflater;
    private List<FilaDetallePreventa> filaDetallePreventas;

    public DetallePreventaListAdapter(Context context) { mInflater = LayoutInflater.from(context); }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public FilaDetallePreventaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fila_detalle, parent, false);
        itemView.setOnClickListener(this);
        return new FilaDetallePreventaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilaDetallePreventaViewHolder holder, int position) {
        if (filaDetallePreventas != null) {
            FilaDetallePreventa current = filaDetallePreventas.get(position);
            holder.nombreProducto.setText(current.getDescripcionProducto());
            holder.cantidad.setText(String.valueOf(current.getCantidadPresentacion()));
            holder.precio.setText(String.format("%.2f", current.getPrecioVenta()));
            holder.unidad.setText(current.getDescripcionUnidad());

            descuentoVM.obtenerDescuentoPreventaItem(current.getCodPreventa(), current.getItemPreventa()).observe(ow, d -> {
                if(d != null)
                    holder.importeDescuento.setText(String.format("%.2f", d));
                else
                    holder.importeDescuento.setText(String.format("%.2f", 0.0));
            });

            switch (current.getTipoProducto()){
                case Constante.g_s_const_1:
                    holder.promocion.setText(Constante.G_CONST_VACIO);
                    break;
                case Constante.g_s_const_2:
                    //holder.promocion.setText("D");
                    int color = Color.parseColor(Constante.g_const_color_descuento);
                    holder.imgEstadoCar1.setColorFilter(color);
                    break;
                case Constante.g_s_const_3:
                    //holder.promocion.setText("P");
                    int color2 = Color.parseColor(Constante.g_const_color_promocion);
                    holder.imgEstadoCar1.setColorFilter(color2);
                    break;
            }

        }
    }

    public void setFilaDetallePreventas(List<FilaDetallePreventa> fila, DescuentoViewModel n, LifecycleOwner lc){
        ow = lc;
        descuentoVM = n;
        filaDetallePreventas = fila;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (filaDetallePreventas != null)
            return filaDetallePreventas.size();
        else return 0;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }



}
