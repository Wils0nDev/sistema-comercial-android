package com.idesolution.distribuidoravdc.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idesolution.distribuidoravdc.BD.Repository.ProductoViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_producto_detalle extends Fragment {

    private ProductoViewModel mProductoViewModel;

    String codProducto;

    String textListPrecios ;
    String textListStock ;
    String textListPromociones ;
    String textListDescuentos;
    TextView tvDescipcion;
    TextView tvListaPrecios;
    TextView tvListaStockDetProd;
    TextView tvListaPromociones;
    TextView tvListaDescuentos;

    private Bundle parametrosEntrada;

    public f_producto_detalle() {
        // Required empty public constructor
    }

    public static f_producto_detalle newInstance(Bundle arguments) {
        f_producto_detalle fragment = new f_producto_detalle();
        if(arguments != null) {
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        parametrosEntrada = this.getArguments();
        codProducto = Constante.G_CONST_VACIO;
        if(parametrosEntrada != null){
            codProducto = parametrosEntrada.getString(Constante.g_const_list_prod_cod_pro);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_producto_detalle, container, false);
        if (v != null){
            textListPrecios = Constante.G_CONST_VACIO;
            textListStock = Constante.G_CONST_VACIO;
            textListPromociones = Constante.G_CONST_VACIO;
            textListDescuentos = Constante.G_CONST_VACIO;
            tvDescipcion = v.findViewById(R.id.f_tvdetProdDescripcion);
            tvListaPrecios = v.findViewById(R.id.f_tvListaPrecios);
            tvListaStockDetProd = v.findViewById(R.id.f_tvListaStockDetProd);
            tvListaPromociones = v.findViewById(R.id.f_tvListaPromociones);
            tvListaDescuentos = v.findViewById(R.id.f_tvListaDescuentos);
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mProductoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);
        cargar_datos();

    }

    private void cargar_datos() {
        mProductoViewModel.loadProductoDetalleFindCodProd(codProducto).observe(this,productoDetalleEntity -> {
            tvDescipcion.setText(productoDetalleEntity.getProducto().getDescripcion());
            for (int i=0;i<productoDetalleEntity.getListPrecios().size();i++){

                textListPrecios = textListPrecios + Constante.g_const_soles + productoDetalleEntity.getListPrecios().get(i).getPrecioVenta() + Constante.g_const_separacion1 +
                        productoDetalleEntity.getListPrecios().get(i).getDescListaPrecio() + "\n";
            }
            tvListaPrecios.setText(textListPrecios);
            for (int i=0;i <productoDetalleEntity.getListInventarios().size();i++){

                textListStock = textListStock + productoDetalleEntity.getListInventarios().get(i).getInventarioAlmacen().getDescripcion() + Constante.g_const_separacion2 +
                        productoDetalleEntity.getListInventarios().get(i).getInventarioEntity().getStock() + "\n";
            }
            tvListaStockDetProd.setText(textListStock);


            for(int i=0; i<productoDetalleEntity.getListPromociones().size();i++){
                textListPromociones = textListPromociones + productoDetalleEntity.getListPromociones().get(i).getPromocionEntity().getDescripcion() + "\n\n";

            }
            tvListaPromociones.setText(textListPromociones);

            for(int i=0; i<productoDetalleEntity.getListDescuentos().size();i++){
                textListDescuentos = textListDescuentos + productoDetalleEntity.getListDescuentos().get(i).getDescuentoEntity().getDescripcion() + "\n\n";

            }
            tvListaDescuentos.setText(textListDescuentos);
        });
    }

}
