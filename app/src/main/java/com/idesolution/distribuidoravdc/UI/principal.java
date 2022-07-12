package com.idesolution.distribuidoravdc.UI;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.EntregaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaTotalEntity;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.EntregaViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PreventaViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.SincronizacionViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Entidad.Global;
import com.idesolution.distribuidoravdc.Entidad.LoginLocal;
import com.idesolution.distribuidoravdc.Fragments.f_lista_categoria;
import com.idesolution.distribuidoravdc.Fragments.f_lista_cliente;
import com.idesolution.distribuidoravdc.Fragments.f_lista_menu;
import com.idesolution.distribuidoravdc.Fragments.f_lista_por_categoria;
import com.idesolution.distribuidoravdc.Fragments.f_perfil;
import com.idesolution.distribuidoravdc.Fragments.f_preventa_lista;
import com.idesolution.distribuidoravdc.IO.ApiAdapter;
import com.idesolution.distribuidoravdc.IO.Request.RequestClienteSinc;
import com.idesolution.distribuidoravdc.IO.Request.RequestEntrega;
import com.idesolution.distribuidoravdc.IO.Request.RequestSincronizacion;
import com.idesolution.distribuidoravdc.IO.Response.ListDetPreventum;
import com.idesolution.distribuidoravdc.IO.Response.ListPrevDescuento;
import com.idesolution.distribuidoravdc.IO.Response.ListPrevPromocion;
import com.idesolution.distribuidoravdc.IO.Response.ListPreventa;
import com.idesolution.distribuidoravdc.IO.Response.SincronizacionResponse;
import com.idesolution.distribuidoravdc.MainActivity;
import com.idesolution.distribuidoravdc.R;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class principal extends AppCompatActivity {
    private BottomNavigationView mMenu;
    private FrameLayout mFrame;
    private f_lista_cliente lista_cliente;
    private f_preventa_lista lista_preventa;
    private f_lista_categoria lista_categoria;
    private f_lista_por_categoria lista_categorias;
    private Bundle arguments;
    private f_lista_menu lista_menu;
    private f_perfil f_perfil;
    AlertDialog.Builder mensaje;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    LoginLocal loginLocal;

    //DATOS NECESARIOS PARA SINCRONIZACION
    SincronizacionViewModel sincronizacionViewModel;
    EntregaViewModel entregaViewModel;
    PreventaViewModel preventaViewModel;
    ClienteViewModel clienteViewModel;
    List<EntregaEntity> listEntregaT = new ArrayList<>();
    ArrayList<RequestEntrega> listReqEntregas= new ArrayList<>();
    RequestEntrega req_ent;
    List<PreventaTotalEntity> totalPreventaT = new ArrayList<>();
    ListPreventa ent_preventa ;
    List<Cliente> listClientesT = new ArrayList<>();
    ArrayList<RequestClienteSinc> listResClientes = new ArrayList<>();
    RequestClienteSinc cli;
    List<ListPreventa> listPreventas = new ArrayList<>();
    List<ListDetPreventum> listPreventaDetalles = new ArrayList<>();
    List<ListPrevPromocion> listPrevPromocions = new ArrayList<>();
    List<ListPrevDescuento> listPrevDescuentos = new ArrayList<>();


    int posFragment; //Posicion del fragmento
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        posFragment = Constante.g_const_0;
        mMenu =  (BottomNavigationView) findViewById(R.id.menu_principal);
        mFrame = (FrameLayout) findViewById(R.id.frame_principal);
        lista_categorias = f_lista_por_categoria.newInstance();
        setFragment(lista_categorias);
        entregaViewModel = new ViewModelProvider(this).get(EntregaViewModel.class);
        preventaViewModel = new ViewModelProvider(this).get(PreventaViewModel.class);
        clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);
        sincronizacionViewModel = new ViewModelProvider(this).get(SincronizacionViewModel.class);
        progressDialog = new ProgressDialog(this);
        mensaje= new AlertDialog.Builder(this);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        loginLocal = sessionManager.getUserDetail();
        //cargar_data_sincro();
        mMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.action_productos:
                        lista_categorias = f_lista_por_categoria.newInstance();
                        posFragment = Constante.g_const_1;
                        Global.g_const_onBsckpress = Constante.g_const_1;
                        sessionManager.checkLogin();
                        setFragment(lista_categorias);

                        return true;
                    case R.id.action_clientes:
                        arguments = new Bundle();
                        arguments.putString("id", "HOLAAA");
                        lista_cliente = f_lista_cliente.newInstance(arguments);
                        posFragment = Constante.g_const_2;
                        Global.g_const_onBsckpress = Constante.g_const_2;
                        sessionManager.checkLogin();
                        setFragment(lista_cliente);
                        return true;
                    case R.id.action_preventa:
                        lista_preventa = new f_preventa_lista();
                        posFragment = Constante.g_const_3;
                        Global.g_const_onBsckpress = Constante.g_const_3;
                        setFragment(lista_preventa);
                        sessionManager.checkLogin();
                        return true;
                    case R.id.action_menu:
                        //arguments = new Bundle();
                        lista_menu = new f_lista_menu();
                        posFragment = Constante.g_const_4;
                        Global.g_const_onBsckpress = Constante.g_const_4;
                        sessionManager.checkLogin();
                        setFragment(lista_menu);

                        return true;
                    case R.id.action_perfil:
                        arguments = new Bundle();
                        arguments.putInt("fPerfil_codVendedor", loginLocal.getNtraUsuario());
                        posFragment = Constante.g_const_5;
                        Global.g_const_onBsckpress = Constante.g_const_5;
                        f_perfil = f_perfil.newInstance(arguments);
                        sessionManager.checkLogin();
                        setFragment(f_perfil);
                        return true;
                }

                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {

        //if(posFragment == Constante.g_const_1){
        if(Global.g_const_onBsckpress == Constante.g_const_1){
            ValidarCerrarSesion();

        }else{
            Global.g_const_onBsckpress = Constante.g_const_1;
            lista_categorias = f_lista_por_categoria.newInstance();
            setFragment(lista_categorias);
            mMenu.setSelectedItemId(R.id.action_productos);
        }


    }

    private void ValidarCerrarSesion(){
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(Constante.g_const_msj_cerrar_sesion)
                .setContentText("¡Sincronizar datos!")
                .setConfirmText("Aceptar")

                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Integer conexion = MetodosGlobales.verificarConexionInternet(getApplication());
                        if (conexion == Constante.g_const_1){
                            sDialog.dismissWithAnimation();
                            SincronizarData();

                            //finish();
                            //sincronizacionRutasBitacora();
                        }else{
                            Toast.makeText(getApplication(),Constante.g_const_vald_conexion,Toast.LENGTH_SHORT).show();
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

    private void cargar_data_sincro() {
        entregaViewModel.getAllEntregasSinc().observe(this, words -> {
            listEntregaT = new ArrayList<>();
            listEntregaT = words;
            listReqEntregas = new ArrayList<>();

            for (EntregaEntity entrega: listEntregaT){
                req_ent = new RequestEntrega(Constante.g_s_const_1,
                        entrega.getId(),entrega.getNtraPuntoEntrega(),entrega.getCoordenadaX(),
                        entrega.getCoordenadaY(),null,entrega.getDireccion(),entrega.getReferencia(),
                        entrega.getOrdenEntrega(),entrega.getCodPersona(),"EAY","172.18.124.15","250.250.250",
                        entrega.getTipoDocumento(),entrega.getNumeroDocumento());
                listReqEntregas.add(req_ent);
            }
        });

        clienteViewModel.getmAllClientesSinc().observe(this, words -> {
            listClientesT = new ArrayList<>();
            listClientesT = words;
            listResClientes = new ArrayList<>();

            for (Cliente cliente: listClientesT){
                cli = new RequestClienteSinc(Short.parseShort(String.valueOf(Constante.g_const_1)),1,cliente.getTipoPersona(),cliente.getTipoDocumento(),
                        cliente.getNumeroDocumento(),cliente.getRuc(),cliente.getRazonSocial(),
                        cliente.getNombres(),cliente.getApellidoPaterno(),cliente.getApellidoMaterno(),
                        cliente.getDireccion(),cliente.getCorreo(),cliente.getTelefono(),
                        cliente.getCelular(),null,null,null,
                        null,cliente.getFrecuencia(),cliente.getTipoListaPrecio(),
                        cliente.getCodRuta(),"EAY","172.18.124.15","250.250.250");
                listResClientes.add(cli);
            }
        });

        preventaViewModel.totalPrevetasSincro().observe(this, w_preventas -> {

            totalPreventaT = new ArrayList<>();
            totalPreventaT = w_preventas;
            listPreventas = new ArrayList<>();

            for (int i = Constante.g_const_0; i <totalPreventaT.size();i++){


                ent_preventa = new ListPreventa();
                ent_preventa.setCodCliente(totalPreventaT.get(i).preventa.getCodCliente());
                ent_preventa.setTipoMoneda(1);
                ent_preventa.setTipoVenta(Integer.parseInt( String.valueOf(totalPreventaT.get(i).preventa.getTipoVenta())));
                if(totalPreventaT.get(i).punto_entrega != null) {
                    if(totalPreventaT.get(i).punto_entrega.ntraPuntoEntrega > Constante.g_const_0){
                        ent_preventa.setCodPuntoEntrega(totalPreventaT.get(i).punto_entrega.ntraPuntoEntrega);
                    }else{
                        ent_preventa.setCodPuntoEntrega(totalPreventaT.get(i).preventa.getCodPuntoEntrega());
                    }

                }else {
                    ent_preventa.setCodPuntoEntrega(totalPreventaT.get(i).preventa.getCodPuntoEntrega());
                }

                ent_preventa.setCodUsuario(totalPreventaT.get(i).preventa.getCodUsuario());
                ent_preventa.setEstado((int) totalPreventaT.get(i).preventa.getEstado());
                ent_preventa.setFecha(totalPreventaT.get(i).preventa.getFecha());
                //ent_preventa.setFechaEntrega(totalPreventaT.get(i).preventa.getFecha());
                //ent_preventa.setFechaPago(totalPreventaT.get(i).preventa.getFecha());
                ent_preventa.setIgv(totalPreventaT.get(i).preventa.getIgv());
                ent_preventa.setIsc(totalPreventaT.get(i).preventa.getIsc());
                ent_preventa.setRecargo(totalPreventaT.get(i).preventa.getRecargo());
                ent_preventa.setNtraPreventa(totalPreventaT.get(i).preventa.getNtraPreventa());
                ent_preventa.setTipoDocumentoVenta((int) totalPreventaT.get(i).preventa.getTipoDocumentoVenta());
                ent_preventa.setTotal(totalPreventaT.get(i).preventa.getTotal());
                ent_preventa.setTipoDocumento(totalPreventaT.get(i).preventa.getTipoDocumento());
                ent_preventa.setNumeroDocumento(totalPreventaT.get(i).preventa.getNumeroDocumento());

                listPreventaDetalles = new ArrayList<>();
                for (int y=Constante.g_const_0;y<totalPreventaT.get(i).listPreventaDetalle.size();y++){
                    if(totalPreventaT.get(i).listPreventaDetalle.get(y).getFlag() == Constante.g_const_0){
                        ListDetPreventum detalle = new ListDetPreventum();
                        detalle.setCantidadPresentacion(totalPreventaT.get(i).listPreventaDetalle.get(y).getCantidadPresentacion());
                        detalle.setCantidadUnidadBase(totalPreventaT.get(i).listPreventaDetalle.get(y).getCantidadUnidadBase());
                        detalle.setCodAlmacen(totalPreventaT.get(i).listPreventaDetalle.get(y).getCodAlmacen());
                        detalle.setCodPresentacion(totalPreventaT.get(i).listPreventaDetalle.get(y).getCodPresentacion());
                        detalle.setCodPreventa(totalPreventaT.get(i).listPreventaDetalle.get(y).getCodPreventa());
                        detalle.setCodProducto(totalPreventaT.get(i).listPreventaDetalle.get(y).getCodProducto());
                        detalle.setItemPreventa((int) totalPreventaT.get(i).listPreventaDetalle.get(y).getItemPreventa());
                        detalle.setPrecioVenta(totalPreventaT.get(i).listPreventaDetalle.get(y).getPrecioVenta());
                        detalle.setTipoProducto((int) totalPreventaT.get(i).listPreventaDetalle.get(y).getTipoProducto());
                        listPreventaDetalles.add(detalle);
                    }

                }
                ent_preventa.setListDetPreventa(listPreventaDetalles);

                listPrevPromocions = new ArrayList<>();
                for (int z = Constante.g_const_0; z<totalPreventaT.get(i).listPreventaPromocion.size();z++){
                    ListPrevPromocion promocion = new ListPrevPromocion();
                    promocion.setCodPreventa(totalPreventaT.get(i).listPreventaPromocion.get(z).getCodPreventa());
                    promocion.setCodPromocion(totalPreventaT.get(i).listPreventaPromocion.get(z).getCodPromocion());
                    promocion.setItemPreventa((int) totalPreventaT.get(i).listPreventaPromocion.get(z).getItemPreventa());
                    promocion.setItemPromocionado((int) totalPreventaT.get(i).listPreventaPromocion.get(z).getItemPromocionado());
                    listPrevPromocions.add(promocion);
                }
                ent_preventa.setListPrevPromocion(listPrevPromocions);

                listPrevDescuentos = new ArrayList<>();
                for (int a= Constante.g_const_0; a < totalPreventaT.get(i).listPreventaDescuento.size();a++){
                    ListPrevDescuento descuento = new ListPrevDescuento();
                    descuento.setCodDescuento(totalPreventaT.get(i).listPreventaDescuento.get(a).getCodDescuento());
                    descuento.setCodPreventa(totalPreventaT.get(i).listPreventaDescuento.get(a).getCodPreventa());
                    descuento.setImporte(totalPreventaT.get(i).listPreventaDescuento.get(a).getImporte());
                    descuento.setItemPreventa((int) totalPreventaT.get(i).listPreventaDescuento.get(a).getItemPreventa());
                    listPrevDescuentos.add(descuento);
                }
                ent_preventa.setListPrevDescuento(listPrevDescuentos);
                listPreventas.add(ent_preventa);
            }
        });

    }
    private void ShowDialog(){
        progressDialog.setCancelable(false);
        progressDialog.setTitle(R.string.txt_tit_prog_sinc);
        progressDialog.setMessage("Sincronizando datos");
        progressDialog.show();
    }

    private void finishDialog(){
        progressDialog.dismiss();
    }
    */


    private void SincronizarData() {
        int codVendedor = loginLocal.getNtraUsuario();
        String codUbigeo = loginLocal.getCodUbigeo();
        //ShowDialog();

        sincronizacionViewModel.SincronizarDataCompleta(codVendedor,codUbigeo,this,Constante.g_const_3); //Constante 1 - Para regresar al login
        // finishDialog();


/*
        RequestSincronizacion requestSincronizacion = new RequestSincronizacion(codVendedor,listResClientes,listPreventas,listReqEntregas);
        Gson gson = new Gson();
        Call<SincronizacionResponse> call = ApiAdapter.getApiService().sincronizacion(requestSincronizacion);
        call.enqueue(new Callback<SincronizacionResponse>() {
            @Override
            public void onResponse(Call<SincronizacionResponse> call, Response<SincronizacionResponse> response) {
                if(response.isSuccessful()){
                    SincronizacionResponse data_sincronizacion = response.body();
                    if(data_sincronizacion.getErrorWebSer().getCodigoErr() == Constante.g_const_2000) {

                        int dato = sincronizacionViewModel.LlenarBaseDatos(data_sincronizacion);

                        if(dato == Constante.g_const_1){
                            Toast.makeText(getApplication(), Constante.g_const_msj_sincro, Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else{

                }
                finishDialog();
            }

            @Override
            public void onFailure(Call<SincronizacionResponse> call, Throwable t) {
                finishDialog();
            }
        });
        */

    }

    public void setFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_principal, fragment);
        //ft.addToBackStack(null);
        //ft.addToBackStack("lista_categorias");
        ft.commit();
    }
}
