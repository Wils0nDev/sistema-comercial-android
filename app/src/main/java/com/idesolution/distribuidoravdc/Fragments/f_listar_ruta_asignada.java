package com.idesolution.distribuidoravdc.Fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.idesolution.distribuidoravdc.BD.Repository.BitacoraVendedorViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.VendedorViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Entidad.LoginLocal;
import com.idesolution.distribuidoravdc.R;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.UI.registrar_visita;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class f_listar_ruta_asignada extends Fragment implements  View.OnClickListener {
    int  ntraUsuario;
    private Bundle parametrosEntrada;
    SessionManager sessionManager;
    LoginLocal loginLocal;
    private VendedorViewModel RutaVM;
    private TextView nombreRuta;
    private TextView TituloRuta;
    private Button iniciarVisita, finalzarVisita;
    private FragmentTransaction ft;
    private String  descRuta;
    private final static int RESPUESTA_VISITA = 1;
    private  BitacoraVendedorViewModel bitacoraVendedorVM;
    private TextView bTerminarVisita;
    private ClienteViewModel clienteVM;
    //variables para consultar visita
    private Integer codr;
    String horaFinal;

    public f_listar_ruta_asignada() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sessionManager = new SessionManager(getContext());
        loginLocal = sessionManager.getUserDetail();
        ntraUsuario = loginLocal.getNtraUsuario();
        View v = inflater.inflate(R.layout.fragment_f_listar_ruta_asignada, container, false);
        if(v != null){
            getActivity().setTitle("Ruta Asignada");
            nombreRuta = (TextView) v.findViewById(R.id.tvNombreRutaAsig);
            iniciarVisita = (Button) v.findViewById(R.id.btnIniciarVisita);
            finalzarVisita = (Button) v.findViewById(R.id.btnFinalizarVisita);
            bTerminarVisita = (TextView) v.findViewById(R.id.btnTerminarVisita);


            finalzarVisita.setOnClickListener(this);
            iniciarVisita.setOnClickListener(this);
            //bTerminarVisita.setOnClickListener(this);




        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cargar_datos_ruta(); //cargo la ruta asignada
        obtenerCodigoRuta();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            Integer resultado = data.getExtras().getInt("RESULTADO");

            if(resultado.equals(1)){
                finalzarVisita.setVisibility(View.VISIBLE);
                iniciarVisita.setVisibility(View.GONE);
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_principal, new f_lista_menu());
                // ft.addToBackStack(null);
                ft.commit();
                //list.clear();
                //list.clear();
            }
        }


        if(resultCode == RESULT_CANCELED){

            if(data == null){
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_principal,this);
                // ft.addToBackStack(null);
                ft.commit();
            }else{
                Integer resultado = data.getExtras().getInt("RESULTADO");

                if(resultado.equals(2)){
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_principal,this);
                    // ft.addToBackStack(null);
                    ft.commit();
                    MetodosGlobales.mostrarMensaje(getContext(), "ACTIVAR GPS");
                }
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_editar, menu);
    }



    public void cargar_datos_ruta(){
        RutaVM = new ViewModelProvider(this).get(VendedorViewModel.class);
         RutaVM.obtenerDescRuta(ntraUsuario).observe(this, ruta -> {
             //MetodosGlobales.mostrarMensaje(getContext(), ruta);
             nombreRuta.setText(ruta);


        });
    }


    @Override
    public void onClick(View v) {
        //Evento Click Iniciar Visita

        switch (v.getId()){
            case R.id.btnIniciarVisita:
                Intent intentRegistroVisita = new Intent(getContext(), registrar_visita.class);
                startActivityForResult(intentRegistroVisita, RESPUESTA_VISITA);
                break;
            case R.id.btnFinalizarVisita:
                Date date = new Date();
                SimpleDateFormat horaFin = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());

                horaFinal = horaFin.format(date);
                actualizar_visita(horaFinal);
               getActivity().onBackPressed();
            break;
            /*case R.id.btnTerminarVisita:
                MetodosGlobales.mostrarMensaje(getContext(), "Se termino la visita");
                getActivity().onBackPressed();
                break;*/
        }

    }

    public void consultar_visita(int codr) {


        bitacoraVendedorVM = new ViewModelProvider(this).get(BitacoraVendedorViewModel.class);

        bitacoraVendedorVM.obtenerVisita(codr).observe(this, estado ->{

            if (estado != null){
                if(estado == 1){

                    finalzarVisita.setVisibility(View.VISIBLE);
                    iniciarVisita.setVisibility(View.GONE);

                }
        }


        });
    }

    public void obtenerCodigoRuta(){

        RutaVM.obtenerCodRuta(ntraUsuario).observe(this, codruta -> {
            if(codruta != null){
                consultar_visita(codruta);
                codr = codruta;
            }
        });

    }

    public void actualizar_visita(String horaFinal){

        bitacoraVendedorVM = new ViewModelProvider(this).get(BitacoraVendedorViewModel.class);
        bitacoraVendedorVM.update(codr,horaFinal);
    }




}
