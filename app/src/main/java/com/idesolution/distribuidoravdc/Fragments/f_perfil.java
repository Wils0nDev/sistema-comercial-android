package com.idesolution.distribuidoravdc.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;
import com.idesolution.distribuidoravdc.BD.Repository.SincronizacionViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.VendedorViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Entidad.LoginLocal;
import com.idesolution.distribuidoravdc.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_perfil extends Fragment {

    ProgressDialog progressDialog;
    AlertDialog.Builder mensaje;
    private VendedorViewModel vendedorViewModel;
    SincronizacionViewModel sincronizacionViewModel;
    SessionManager sessionManager;
    LoginLocal loginLocal;
    Button boton_sincronizar ;
    String txt_nombreCompleto;
    String txt_Documento;
    String txt_correo;
    String txt_codPerfil;
    String txt_Celular;
    String txt_Ruta;
    TextView tvNombreCompleto;
    TextView tvDocumento;
    TextView tvCorreo;
    TextView tvCodPerfil;
    TextView tvCelular;
    TextView tvRuta;
    int ntraUsuario;
    private Bundle parametrosEntrada;
    public f_perfil() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        parametrosEntrada = this.getArguments();
        ntraUsuario = Constante.g_const_0;
        if(parametrosEntrada != null){
            ntraUsuario = parametrosEntrada.getInt("fPerfil_codVendedor");
        }
    }

    public static f_perfil newInstance(Bundle arguments) {
        f_perfil fragment = new f_perfil();
        if(arguments != null) {
            fragment.setArguments(arguments);
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_f_perfil, container, false);
        if(v!= null){

            mensaje= new AlertDialog.Builder(getContext());

            txt_Celular = Constante.G_CONST_VACIO;
            txt_codPerfil = Constante.G_CONST_VACIO;
            txt_correo = Constante.G_CONST_VACIO;
            txt_Documento = Constante.G_CONST_VACIO;
            txt_nombreCompleto = Constante.G_CONST_VACIO;
            txt_Ruta = Constante.G_CONST_VACIO;

            tvCelular = v.findViewById(R.id.tvCelularVend);
            tvCodPerfil = v.findViewById(R.id.tvCodPerVend);
            tvCorreo = v.findViewById(R.id.tvCorreoVendedor);
            tvDocumento = v.findViewById(R.id.tvNumDocVendedorPerfil);
            tvRuta = v.findViewById(R.id.tvRutaVendPerf);
            tvNombreCompleto = v.findViewById(R.id.tvNombresDetVendedor);
            boton_sincronizar = v.findViewById(R.id.btnPerfilSoncro);
            progressDialog = new ProgressDialog(getContext());
            getActivity().setTitle("Perfil");
            sessionManager = new SessionManager(getContext());
            loginLocal = sessionManager.getUserDetail();
            boton_sincronizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getApplication(),"clikc sincronizar",Toast.LENGTH_SHORT).show();
                    //Validar conexion de internet
                    Integer conexion = MetodosGlobales.verificarConexionInternet(getContext());
                    if (conexion == Constante.g_const_1){

                        SincronizarData(Constante.g_const_0);
                        //sincronizacionRutasBitacora();
                    }else{
                        Toast.makeText(getContext(), Constante.g_const_vald_conexion,Toast.LENGTH_SHORT).show();
                    }
                }




            });

        }
        return v;
    }

    //MENU SUPERIOR
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cerrar_sesion, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Integer conexion = MetodosGlobales.verificarConexionInternet(getContext());
        switch (item.getItemId()) {
            case R.id.mnCerrarSesion:
                if (conexion == Constante.g_const_0){
                    Toast.makeText(getContext(), Constante.g_const_vald_conexion, Toast.LENGTH_SHORT).show();
                }
                else{
                    ValidarCerrarSesion();

                }

        }
        return true;
    }

    private void ValidarCerrarSesion(){
        new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText(Constante.g_const_msj_cerrar_sesion)
                .setContentText("¡Sincronizar datos!")
                .setConfirmText("Aceptar")

                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Integer conexion = MetodosGlobales.verificarConexionInternet(getContext());
                        if (conexion == Constante.g_const_1){
                            sDialog.dismissWithAnimation();
                            SincronizarData(Constante.g_const_3);

                        }else{
                            Toast.makeText(getContext(), Constante.g_const_vald_conexion,Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setCancelButton("Cancelar", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

/*
    private void SalirAplicacion() {
        getActivity().finish();
    }
*/
    private void ShowDialog(){
        progressDialog.setCancelable(false);
        progressDialog.setTitle(R.string.txt_tit_prog_sinc);
        progressDialog.setMessage("Sincronizando datos");
        progressDialog.show();
    }

    private void finishDialog(){
        progressDialog.dismiss();
    }


    private void SincronizarData(int respuesta) {
        int codVendedor = loginLocal.getNtraUsuario();
        String codUbigeo = loginLocal.getCodUbigeo();
        sincronizacionViewModel.SincronizarDataCompleta(codVendedor,codUbigeo,getContext(),respuesta);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        vendedorViewModel = new ViewModelProvider(this).get(VendedorViewModel.class);
        //entregaViewModel = new ViewModelProvider(this).get(EntregaViewModel.class);
        //preventaViewModel = new ViewModelProvider(this).get(PreventaViewModel.class);
        //clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);
        sincronizacionViewModel = new ViewModelProvider(this).get(SincronizacionViewModel.class);
        cargar_datos();
        //cargar_data_sincro();


    }


    private void cargar_datos() {
        vendedorViewModel.getVendedorID(ntraUsuario).observe(this,vendedorEntity -> {
            if(vendedorEntity != null){
                tvNombreCompleto.setText(vendedorEntity.getNombreCompleto());
                tvRuta.setText(vendedorEntity.getDescRuta());
                tvDocumento.setText(vendedorEntity.getNumeroDocumento());
                tvCorreo.setText(vendedorEntity.getCorreo());
                tvCodPerfil.setText(Constante.G_CONST_VACIO+vendedorEntity.getCodPerfil());
                tvCelular.setText(vendedorEntity.getCelular());
            }

        });
    }

}
