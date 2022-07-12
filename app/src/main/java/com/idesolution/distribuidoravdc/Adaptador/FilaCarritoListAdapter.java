package com.idesolution.distribuidoravdc.Adaptador;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.DetalleDao;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCarrito;
import com.idesolution.distribuidoravdc.BD.Repository.CabeceraViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.DescuentoViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Fragments.f_preventa_selec_producto;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

public class FilaCarritoListAdapter extends RecyclerView.Adapter<FilaCarritoListAdapter.FilaCarritoViewHolder> implements View.OnClickListener {
    CabeceraViewModel cabeceraVM ;//= new ViewModelProvider().get(CabeceraViewModel.class);
    private DescuentoViewModel descuentoVM;
    private AppDataBase db;
    DetalleDao detalleDao ;
    ViewModelStoreOwner vmso;
    LifecycleOwner nu;
    int item;
    private Bundle parametroSalida;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private Context contextAplication;
    class FilaCarritoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context;

        private final TextView nombreProducto;
        private final TextView oferta;
        private final TextView precio;
        private final TextView cantidad;
        private final TextView unidad;
        private final TextView descuento;
        private final ImageButton botonQuitar;
        private final ImageView imgEstadoCarrito;



        private FilaCarritoViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            nombreProducto = itemView.findViewById(R.id.tvNombreProducto_fila_carrito);
            oferta = itemView.findViewById(R.id.tvOferta_fila_Carrito);
            cantidad = itemView.findViewById(R.id.tvCantidad_fila_carrito);
            precio = itemView.findViewById(R.id.tvPrecio_fila_carrito);
            unidad = itemView.findViewById(R.id.tvUnidad_fila_carrito);
            descuento = itemView.findViewById(R.id.tvDescuentoFilaCarrito);
            botonQuitar = itemView.findViewById(R.id.btnQuitarProducto_carrito);
            imgEstadoCarrito = itemView.findViewById(R.id.imgEstadoArticulo);
        }

        void setOnClickListeners(){
         botonQuitar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int m;
            switch (v.getId()){
                case R.id.btnQuitarProducto_carrito:
                    //db.detalleDao().quitarProductoCarrito(getAdapterPosition()) ;
                    //MetodosGlobales.mostrarMensaje(v.getContext(), getAdapterPosition() + "");
                    cabeceraVM.quitarProductoCarrito(getAdapterPosition() + Constante.g_const_1);
                    filaCarrito.remove(getAdapterPosition());
                    /*m = getAdapterPosition();
                    cabeceraVM.buscarItemPromocionDos(getAdapterPosition() + Constante.g_const_1).observe(nu, i -> {
                        item = getAdapterPosition() + i;
                        for(int a = m; a <= item; a = a + 1 ){
                            filaCarrito.remove(a);
                        }
                    });*/
                    notifyDataSetChanged();
                    //notifyItemRangeRemoved(Constante.g_const_0, filaCarrito.size());
                    //cabeceraVM = new ViewModelProvider(context.getApplicationContext().li).get(CabeceraViewModel.class);
                    break;

            }
        }
    }

    private  View.OnClickListener listener;
    private final LayoutInflater mInflater;
    private List<FilaCarrito> filaCarrito;

    public FilaCarritoListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public FilaCarritoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.fila_carrito, parent, false);
        itemView.setOnClickListener(this);
        return new FilaCarritoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilaCarritoViewHolder holder, int position) {
        if (filaCarrito != null) {
            FilaCarrito current = filaCarrito.get(position);
            holder.nombreProducto.setText(current.getDescripcionProducto());
            holder.cantidad.setText(String.valueOf(current.getCantidadPresentacion()));
            holder.precio.setText(String.format("%.2f", current.getPrecioVenta()));
            holder.unidad.setText(current.getDescripcionUnidad());
            switch (current.getTipoProducto()){
                case Constante.g_s_const_1:
                    holder.oferta.setText(Constante.G_CONST_VACIO);
                    break;
                case Constante.g_s_const_2:
                    //holder.oferta.setText("D");#FEB307
                    int color = Color.parseColor(Constante.g_const_color_descuento);
                    holder.imgEstadoCarrito.setColorFilter(color);
                    break;
                case Constante.g_s_const_3:
                    //holder.oferta.setText("P"); #18BB29
                    int color2 = Color.parseColor(Constante.g_const_color_promocion);
                    holder.imgEstadoCarrito.setColorFilter(color2);
                    break;
            }
            cabeceraVM.obtenerDescuentoPreventaItem(current.getItemPreventa()).observe(nu, d -> {
                if(d != null)
                    holder.descuento.setText(String.format("%.2f", d));
                else
                    holder.descuento.setText(String.format("%.2f", 0.0));
            });
            holder.setOnClickListeners();

        }
    }

    public void setFilaCarrito(List<FilaCarrito> fila, ViewModelStoreOwner m, LifecycleOwner n, FragmentManager f){
        cabeceraVM = new ViewModelProvider(m).get(CabeceraViewModel.class);
        //descuentoVM = nn;
        vmso = m;
        nu = n;
        fm = f;
        filaCarrito = fila;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (filaCarrito != null)
            return filaCarrito.size();
        else return 0;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }
}
