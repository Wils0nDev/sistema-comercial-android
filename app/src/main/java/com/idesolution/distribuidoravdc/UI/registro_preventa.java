package com.idesolution.distribuidoravdc.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.app.AlertDialog;
import com.idesolution.distribuidoravdc.BD.Repository.CabeceraViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Fragments.f_preventa_carrito;
import com.idesolution.distribuidoravdc.Fragments.f_preventa_selec_cliente;
import com.idesolution.distribuidoravdc.R;

public class registro_preventa extends AppCompatActivity {

    private FrameLayout mFrame;
    private f_preventa_selec_cliente selec_cliente;
    private f_preventa_carrito carrito;
    private FragmentTransaction ft;
    private Integer proceso;
    private Integer transaccion;
    private Bundle parametroSalida;
    private CabeceraViewModel cabeceraVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_preventa);

        proceso = getIntent().getIntExtra("par_proceso_preventa", Constante.g_const_0);
        transaccion = getIntent().getIntExtra("par_transaccion_preventa", Constante.g_const_0);
        mFrame = (FrameLayout) findViewById(R.id.frame_nueva_preventa);


        if(proceso == Constante.g_const_1){
            selec_cliente = new f_preventa_selec_cliente();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_nueva_preventa, selec_cliente);
            ft.commit();
        }else{
            alertas();
            carrito = new f_preventa_carrito();
            parametroSalida = new Bundle();
            parametroSalida.putInt("par_proceso_", proceso);
            parametroSalida.putInt("par_transaccion_", transaccion);
            carrito.setArguments(parametroSalida);
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_nueva_preventa, carrito);
            ft.commit();
        }


    }

    public void alertas(){
        //
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);

        AlertDialog.Builder tipoDocumentoVenta = new AlertDialog.Builder(this);
        tipoDocumentoVenta.setTitle("¿VENTA CON FACTURA?");

        tipoDocumentoVenta.setCancelable(false);
        tipoDocumentoVenta.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cabeceraVM.actualizarTipoDocumentoVenta(Constante.g_const_2);
                //getActivity().finish();
            }
        });
        tipoDocumentoVenta.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cabeceraVM.actualizarTipoDocumentoVenta(Constante.g_const_1);
            }
        });
        tipoDocumentoVenta.show();

        AlertDialog.Builder tipoVenta = new AlertDialog.Builder(this);
        tipoVenta.setTitle("¿VENTA AL CREDITO?");

        tipoVenta.setCancelable(false);
        tipoVenta.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cabeceraVM.actualizarTipoVenta(Constante.g_const_2);
            }
        });
        tipoVenta.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cabeceraVM.actualizarTipoVenta(Constante.g_const_1);
            }
        });
        tipoVenta.show();
        //
    }



}
