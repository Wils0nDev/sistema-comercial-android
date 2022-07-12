package com.idesolution.distribuidoravdc.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.idesolution.distribuidoravdc.Adaptador.DetallePreventaListAdapter;
import com.idesolution.distribuidoravdc.Adaptador.PromocionListAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;
import com.idesolution.distribuidoravdc.BD.Repository.CabeceraViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.DescuentoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PreventaViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;
import com.idesolution.distribuidoravdc.UI.registro_preventa;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_preventa_detalle extends Fragment {

    private ImageButton btnPromos;
    private TextView tvCliente;
    private TextView tvFechaEntrega;
    private TextView tvCantidadPromos;
    private TextView tvTipoVenta;
    //private TextView tvDocumentoVenta;
    private TextView tvTotalProductos;
    //private TextView tvSubtTotal;
    private TextView tvRecargo;
    private TextView tvDescuento;
    //private TextView tvIgv;
    private TextView tvTotal;
    private RecyclerView rvDetallePreventa;
    private Bundle parametrosEntrada;
    private int ntraPreventa;
    private int estado;
    private Preventa preventa;
    private PreventaViewModel preventaVM;
    private DescuentoViewModel descuentoVM;
    private ClienteViewModel clienteVM;
    private CabeceraViewModel cabeceraVM;
    private DetallePreventaListAdapter adapter;
    private PromocionListAdapter adapterPromo;
    private RecyclerView rvPromos;
    private String tipoVenta;
    private String documentoVenta;
    private int cantidadPromos;
    private Button btnAceptar;
    private int flag;
    private String estadoVenta;


    public static f_preventa_detalle newInstance(Bundle arguments){
        f_preventa_detalle f = new f_preventa_detalle();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    public f_preventa_detalle() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        parametrosEntrada = this.getArguments();
        ntraPreventa = Constante.g_const_0;
        if(parametrosEntrada != null){
            ntraPreventa = parametrosEntrada.getInt("par_ntraPreventa");
            estado = parametrosEntrada.getInt("par_estado");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_preventa_detalle, container, false);
        if(v != null){
            tvCliente = v.findViewById(R.id.tvNomCli_detpre);
            tvFechaEntrega = v.findViewById(R.id.tvFechaEntrega_detpre);
            tvTipoVenta = v.findViewById(R.id.tvTipoVenta_detpre);
            //tvDocumentoVenta = v.findViewById(R.id.tvTipoDocVenta_detpre);
            tvTotalProductos = v.findViewById(R.id.tvTotalProd_detpre);
            //tvSubtTotal = v.findViewById(R.id.tvSubTotal_detpre);
            tvRecargo = v.findViewById(R.id.tvRecargo_detpre);
            tvDescuento = v.findViewById(R.id.tvDescuento_detpre);
            tvCantidadPromos = v.findViewById(R.id.tvCantidadPromos_detpre);
            btnPromos = v.findViewById(R.id.btnPromociones_detpre);
            //tvIgv = v.findViewById(R.id.tvIgv_detpre);
            tvTotal = v.findViewById(R.id.tvTotal_detpre);
            rvDetallePreventa = v.findViewById(R.id.rvDetalles_detpre);
            adapter = new DetallePreventaListAdapter(getContext());
            rvDetallePreventa.setAdapter(adapter);
            rvDetallePreventa.setLayoutManager(new LinearLayoutManager(getContext()));

            /*rvPromos = v.findViewById(R.id.rvListaPromos_promoact);
            adapterPromo = new PromocionListAdapter(getContext());
            rvPromos.setAdapter(adapterPromo);
            rvPromos.setLayoutManager(new LinearLayoutManager(getContext()));*/
            getActivity().setTitle("Detalle Preventa");
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preventaVM = new ViewModelProvider(this).get(PreventaViewModel.class);
        descuentoVM = new ViewModelProvider(this).get(DescuentoViewModel.class);
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        //Toast.makeText(getContext(), "ntraPreventa: "+ ntraPreventa, Toast.LENGTH_SHORT).show();
        preventaVM.obtenerPreventasPorNtra(ntraPreventa).observe(this, p -> {
            if(p != null){
                preventaVM.cantidadDePromocionesPreventa(p.getNtraPreventa()).observe(this, c ->{
                    tvCantidadPromos.setText(c.toString());
                    cantidadPromos = Constante.g_const_0;
                    if(c != null)
                        cantidadPromos = c;
                });

                if(p.getTipoVenta() == Constante.g_s_const_1)
                    tipoVenta = "CONTADO";
                    //tvTipoVenta.setText("CONTADO");
                else
                    //tvTipoVenta.setText("CREDITO");
                    tipoVenta = "CREDITO";

                if(p.getTipoDocumentoVenta() == Constante.g_s_const_1)
                    documentoVenta = "BOLETA";
                    //tvDocumentoVenta.setText("BOLETA");
                else
                    //tvDocumentoVenta.setText("FACTURA");
                    documentoVenta = "FACTURA";

                if(p.getEstado() == Constante.g_s_const_1)
                    estadoVenta = "ACTIVA";
                else if(p.getEstado() == Constante.g_s_const_2)
                    estadoVenta = "ANULADA";

                tvTipoVenta.setText("Venta: " + tipoVenta.trim() + " - " + documentoVenta.trim() + " - " + estadoVenta);

                tvFechaEntrega.setText("Fecha Entrega: " + p.getFechaEntrega());


                //tvSubtTotal.setText(String.format("%.2f", (p.getTotal() - p.getIgv())));
                tvRecargo.setText(String.format("%.2f", p.getRecargo()));
                //tvIgv.setText(String.format("%.2f",p.getIgv()));
                tvTotal.setText(String.format("%.2f",p.getTotal()));

                descuentoVM.obtenerDescuentosPreventa(p.getNtraPreventa()).observe(this, d -> {
                    if(d != null)
                        tvDescuento.setText(String.format("%.2f", d));
                    else
                        tvDescuento.setText(String.format("%.2f", 0.0));
                });

                clienteVM.obtenerNombreCliente(p.getTipoDocumento(), p.getNumeroDocumento()).observe(this, c ->{
                    tvCliente.setText(c);
                });

                //CANTIDAD DE PRODUCTO (DETALLE)
                preventaVM.obtenerDetallePreventa(p.getNtraPreventa()).observe(this, dp -> {
                    adapter.setFilaDetallePreventas(dp, descuentoVM, getViewLifecycleOwner());
                });

                preventaVM.cantidadDeProductosPreventa(p.getNtraPreventa()).observe(this, c ->{
                    tvTotalProductos.setText(c.toString());
                });
            }


        });

        btnPromos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cantidadPromos > Constante.g_const_0){
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                    View mView = getLayoutInflater().inflate(R.layout.dialog_promociones, null);
                    btnAceptar = (Button) mView.findViewById(R.id.btnAceptar_promoact);

                    rvPromos = mView.findViewById(R.id.rvListaPromos_promoact);
                    adapterPromo = new PromocionListAdapter(getContext());
                    rvPromos.setAdapter(adapterPromo);
                    rvPromos.setLayoutManager(new LinearLayoutManager(getContext()));

                    cargarListaPromos();

                    mBuilder.setView(mView);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    btnAceptar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                }
            }
        });

    }

    public void cargarListaPromos(){
        preventaVM.obtenerPreventasPorNtra(ntraPreventa).observe(this, p -> {
            preventaVM.obtenerDescripcionPromosDesc(p.getNtraPreventa()).observe(this,listPromo -> {
                if(listPromo != null){
                    List<PromocionEntity> lista = new ArrayList<>();
                    PromocionEntity item;

                    for (int i = 0; i < listPromo.size();i++){
                        item = new PromocionEntity(listPromo.get(i).getNtraPromocion(),listPromo.get(i).getNtraPromocion() + "",
                                1,listPromo.get(i).getDescripcion(),"","",
                                1,Constante.g_s_const_1);
                        lista.add(item);
                    }
                    adapterPromo.setPromociones(lista);

                }
            });
            /*
            preventaVM.obtenerDescripcionPromos(p.getNtraPreventa()).observe(this, l ->{
                if(l != null)
                    adapterPromo.setPromociones(l);
            });
             */
        });
    }

        @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detalle_preventa, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        switch (item.getItemId()) {
            case R.id.mnAnularPre:
                //validar si preventa puede editarse
                //preventaVM.obtenerEstadoPreventaNtra(ntraPreventa).observe(this, est -> {
                if(estado != Constante.g_const_1){
                    if(estado == Constante.g_const_2){
                        new SweetAlertDialog(getContext())
                                .setTitleText("PREVENTA SE ENCUENTRA ANULADA")
                                .show();
                        //MetodosGlobales.mostrarMensaje(getContext(), "PREVENTA SE ENCUENTRA ANULADA");
                    }else{
                        new SweetAlertDialog(getContext())
                                .setTitleText("NO ES POSIBLE ANULAR LA PREVENTA")
                                .show();
                        //MetodosGlobales.mostrarMensaje(getContext(), "NO ES POSIBLE ANULAR LA PREVENTA");
                    }
                }else{

                    new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("ANULAR PREVENTA")
                            .setContentText("¿Desea anular la preventa?")
                            .setConfirmText("SI")

                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    estado = Constante.g_const_2;
                                    anularPreventa();
                                    /*
                                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Exito!")
                                            .setContentText("PREVENTA ANULADA")
                                            .show();*/
                                    Toasty.success(getContext(), "PREVENTA ANULADA", Toast.LENGTH_SHORT, true).show();
                                    //MetodosGlobales.mostrarMensaje(getContext(), "PREVENTA ANULADA");
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .setCancelButton("NO", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                    /*
                    AlertDialog.Builder mensaje = new AlertDialog.Builder(getContext());
                    mensaje.setTitle("¿DESEA ANULAR LA PREVENTA?");
                    mensaje.setCancelable(false);
                    mensaje.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            estado = Constante.g_const_2;
                            anularPreventa();
                            MetodosGlobales.mostrarMensaje(getContext(), "PREVENTA ANULADA");
                        }
                    });
                    mensaje.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    mensaje.show();
                    */
                }
                //});
                break;
            case R.id.mnEditarPre:
                //validar si preventa puede editarse
                if(estado != Constante.g_const_1 ){
                    new SweetAlertDialog(getContext())
                            .setTitleText("NO ES POSIBLE MODIFICAR LA PREVENTA")
                            .show();
                    //MetodosGlobales.mostrarMensaje(getContext(), "NO ES POSIBLE MODIFICAR LA PREVENTA");
                }else{
                    //cargar data a temporales
                    cabeceraVM.cargarPreventa(ntraPreventa);
                    //llamar a la actividad
                    Intent intentModificarPreventa = new Intent(getContext(), registro_preventa.class);
                    intentModificarPreventa.putExtra("par_proceso_preventa", Constante.g_const_2);
                    intentModificarPreventa.putExtra("par_transaccion_preventa", ntraPreventa);
                    startActivity(intentModificarPreventa);
                }

                /*preventaVM.obtenerEstadoPreventaNtra(ntraPreventa).observe(this, est -> {
                    if(est != Constante.g_const_1 ){
                        MetodosGlobales.mostrarMensaje(getContext(), "NO ES POSIBLE MODIFICAR LA PREVENTA");
                    }else{
                        //cargar data a temporales
                        cabeceraVM.cargarPreventa(ntraPreventa);
                        //llamar a la actividad
                        Intent intentModificarPreventa = new Intent(getContext(), registro_preventa.class);
                        intentModificarPreventa.putExtra("par_proceso_preventa", Constante.g_const_2);
                        intentModificarPreventa.putExtra("par_transaccion_preventa", ntraPreventa);
                        startActivity(intentModificarPreventa);
                    }
                });*/
                break;
        }
        return true;
    }

    public void anularPreventa(){
        preventaVM.anularPreventaNtra(ntraPreventa, getContext());
    }

}
