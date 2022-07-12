package com.idesolution.distribuidoravdc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.idesolution.distribuidoravdc.Adaptador.SpinnerComboAdapter;
import com.idesolution.distribuidoravdc.Adaptador.SpinnerListAdapter;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;
import com.idesolution.distribuidoravdc.BD.Entity.BitacoraVendedorEntity;
import com.idesolution.distribuidoravdc.BD.Entity.CategoriaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.EntregaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaTotalEntity;
import com.idesolution.distribuidoravdc.BD.Entity.SucursalEntity;
import com.idesolution.distribuidoravdc.BD.Repository.AlmacenViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.BitacoraVendedorViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.CategoriaViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ConceptoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.DescuentoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.EntregaViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.InventarioViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ParametroViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PrecioViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PresentacionViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PreventaViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ProductoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PromocionViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.SincronizacionViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.VendedorViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.IO.ApiAdapter;
import com.idesolution.distribuidoravdc.IO.Request.RequestClienteSinc;
import com.idesolution.distribuidoravdc.IO.Request.RequestEntrega;
import com.idesolution.distribuidoravdc.IO.Request.RequestLogin;
import com.idesolution.distribuidoravdc.IO.Request.RequestSesion;
import com.idesolution.distribuidoravdc.IO.Request.RequestSincronizacion;
import com.idesolution.distribuidoravdc.IO.Request.RequestSincronizacionRutas;
import com.idesolution.distribuidoravdc.IO.Request.ResquestRutasBitacora;
import com.idesolution.distribuidoravdc.IO.Response.ListDetPreventum;
import com.idesolution.distribuidoravdc.IO.Response.ListPrevDescuento;
import com.idesolution.distribuidoravdc.IO.Response.ListPrevPromocion;
import com.idesolution.distribuidoravdc.IO.Response.ListPreventa;
import com.idesolution.distribuidoravdc.IO.Response.ListSucursale;
import com.idesolution.distribuidoravdc.IO.Response.LoginResponse;
import com.idesolution.distribuidoravdc.IO.Response.ResponseRegistroBitacoras;
import com.idesolution.distribuidoravdc.IO.Response.ResponseSesion;
import com.idesolution.distribuidoravdc.IO.Response.SincronizacionResponse;
import com.idesolution.distribuidoravdc.IO.Response.SucursalResponse;
import com.idesolution.distribuidoravdc.UI.principal;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Completable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    Button boton_sincronizar ;
    Button boton_login;
    Button boton_recargar;
    int estado_sincro = Constante.g_const_0;
    SincronizacionViewModel sincronizacionViewModel;// = new SincronizacionViewModel();
    CategoriaViewModel categoriaViewModel;
    ClienteViewModel clienteViewModel;
    VendedorViewModel vendedorViewModel;
    ProductoViewModel productoViewModel;
    PresentacionViewModel presentacionViewModel;
    PromocionViewModel promocionViewModel;
    DescuentoViewModel descuentoViewModel;
    AlmacenViewModel almacenViewModel;
    InventarioViewModel inventarioViewModel;
    EntregaViewModel entregaViewModel;
    //PreventaViewModel preventaViewModel;
    PrecioViewModel precioViewModel;
    ConceptoViewModel conceptoViewModel;
    BitacoraVendedorViewModel bitacoraVendedorViewModel;
    ParametroViewModel parametroViewModel;

    SessionManager sessionManager;

    TextInputLayout ti_usuario;
    TextInputLayout ti_contra;
    TextView txtUsuario;
    TextView txtContra;
    Spinner sp_sucursal;
    SpinnerListAdapter adapter;
    SpinnerAdapter adapter2;
    private RecyclerView recyclerSnniper;
    List<CategoriaEntity> list_categorias;
    List<CategoriaEntity> mCountryList;

    private ArrayList<String> listaDescripcionSucursales;

    List<EntregaEntity> listEntregaT = new ArrayList<>();
    ArrayList<RequestEntrega> listReqEntregas= new ArrayList<>();
    RequestEntrega req_ent;

    List<Cliente> listClientesT = new ArrayList<>();
    ArrayList<RequestClienteSinc> listResClientes = new ArrayList<>();
    RequestClienteSinc cli;

    List<ListPreventa> listPreventas = new ArrayList<>();
    List<ListDetPreventum> listPreventaDetalles = new ArrayList<>();
    List<ListPrevPromocion> listPrevPromocions = new ArrayList<>();
    List<ListPrevDescuento> listPrevDescuentos = new ArrayList<>();

    List<Preventa> listPreventaT = new ArrayList<>();
    List<PreventaTotalEntity> totalPreventaT = new ArrayList<>();
    ListPreventa ent_preventa ;

    List<BitacoraVendedorEntity> listaBitacoras = new ArrayList<>();
    ArrayList<ResquestRutasBitacora> listRestRutasBitacora = new ArrayList<>();
    ResquestRutasBitacora rbv;

    //Sucursales
    List<ListSucursale> listaSucursalServicio = new ArrayList<>();
    List<SucursalEntity>  listSucursal = new ArrayList<>();
    SucursalEntity sucursal = null;
    int codSucursal = Constante.g_const_0;
    String codUbigeo = Constante.G_CONST_VACIO;
    boolean estado;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        getSupportActionBar().hide();

        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);


        }
        //setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sincronizacionViewModel = new ViewModelProvider(this).get(SincronizacionViewModel.class);
        categoriaViewModel = new ViewModelProvider(this).get(CategoriaViewModel.class);
        clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);
        vendedorViewModel = new ViewModelProvider(this).get(VendedorViewModel.class);
        productoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);
        presentacionViewModel = new ViewModelProvider(this).get(PresentacionViewModel.class);
        promocionViewModel = new ViewModelProvider(this).get(PromocionViewModel.class);
        descuentoViewModel = new  ViewModelProvider(this).get(DescuentoViewModel.class);
        almacenViewModel = new ViewModelProvider(this).get(AlmacenViewModel.class);
        inventarioViewModel = new ViewModelProvider(this).get(InventarioViewModel.class);
        entregaViewModel = new ViewModelProvider(this).get(EntregaViewModel.class);
        //preventaViewModel = new ViewModelProvider(this).get(PreventaViewModel.class);
        precioViewModel = new ViewModelProvider(this).get(PrecioViewModel.class);
        conceptoViewModel = new ViewModelProvider(this).get(ConceptoViewModel.class);
        parametroViewModel = new ViewModelProvider(this).get(ParametroViewModel.class);
        txtUsuario = findViewById(R.id.txt_usuario_log_edit);
        txtContra = findViewById(R.id.txt_contra_log_edit);
        sessionManager = new SessionManager(this);
        sp_sucursal = (Spinner) findViewById(R.id.spSucursalLogin);
        ti_usuario = (TextInputLayout) findViewById(R.id.txt_usuario_log);
        ti_contra = (TextInputLayout) findViewById(R.id.txt_contra_log);
        //listaDescripcionSucursales = new ArrayList<String>();
        sessionManager.LogueoActivo();

        boton_sincronizar = findViewById(R.id.btn_sincronizar);
        boton_login = findViewById(R.id.btn_entrar);
        boton_recargar = findViewById(R.id.btn_recargar_login);

        //CARGAR SUCURSALES SERVICIO
        Integer conexion = MetodosGlobales.verificarConexionInternet(getApplication());
        if (conexion == Constante.g_const_1) {
            CargarSucursales(this);
            //obtenerBitacorasSincro();
        }else{
            boton_recargar.setVisibility(View.VISIBLE);
            Toast.makeText(getApplication(),Constante.g_const_vald_conexion,Toast.LENGTH_SHORT).show();
        }



        boton_sincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer conexion = MetodosGlobales.verificarConexionInternet(getApplicationContext());
                if (conexion == Constante.g_const_1){
                    boton_recargar.setVisibility(View.INVISIBLE);
                    ValidarLoginUsuario();
                }else{
                    boton_recargar.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplication(),Constante.g_const_vald_conexion,Toast.LENGTH_SHORT).show();
                }
            }


        });

        boton_recargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer conexion = MetodosGlobales.verificarConexionInternet(getApplicationContext());
                if (conexion == Constante.g_const_1){
                    CargarSucursalesProceso();

                }else{
                    Toast.makeText(getApplication(),Constante.g_const_vald_conexion,Toast.LENGTH_SHORT).show();
                }
            }


        });

        boton_login.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), principal.class);
            //Intent intent = new Intent(getApplicationContext(), CategoriasListaActivity.class);
            startActivity(intent);

        });

        /*
        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();


        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setConfirmText("Yes,delete it!")

                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
        */
    }
    private void CargarSucursalesProceso(){
        CargarSucursales(this);
    }

    private boolean ValidarLoginUsuario() {
        //codSucursal - codigo de sucursal
        int estadoVal= Constante.g_const_0;

        if(txtUsuario.getText().toString().isEmpty()){
            ti_usuario.setError("Ingrese usuario");
            estadoVal = Constante.g_const_1;

        }else{
            ti_usuario.setErrorEnabled(false);
        }

        if(txtContra.getText().toString().isEmpty()){
            ti_contra.setError("Ingrese contraseña");
            estadoVal = Constante.g_const_1;

        }else{
            ti_contra.setErrorEnabled(false);
        }

        if(estadoVal == Constante.g_const_0){
            return ConsultaLoginServidor();
        }else{
            return false;
        }

    }

    private boolean ConsultaLoginServidor() {
        estado = false;
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setUsuario(txtUsuario.getText().toString());
        requestLogin.setContra(txtContra.getText().toString());
        requestLogin.setSucursal(codSucursal);
        requestLogin.setTipo(Constante.g_const_1);
        Call<LoginResponse> call = ApiAdapter.getApiService().ConsultaLoginServidor(requestLogin);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_2000 ) {
                        if(rb.getRespuesta() == Constante.g_const_0){
                            //INICIAR SESION, PERO ANTES REGISTRAR SESION INICIO EN SERVICIO
                            RequestSesion requestSesion = new RequestSesion();
                            requestSesion.setCodUsuario(rb.getNtraUsuario());
                            requestSesion.setTipoLogueo(Constante.g_const_2);
                            requestSesion.setTipoRegistro(Constante.g_const_1);
                            RegistrarSesionInicio(requestSesion);

                            //sincronizacionRutasBitacora();
                            //sincronizacion(rb.getNtraUsuario());
                        }else{
                            Toast.makeText(MainActivity.this, rb.getDescripcionResp(), Toast.LENGTH_SHORT).show();
                        }

                    }
                    else if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_1000) {

                        Toast.makeText(MainActivity.this, rb.getDescripcionResp(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error al consultar", Toast.LENGTH_SHORT).show();
                    }
                    /*
                    if(rb.getRespuesta() == Constante.g_const_0){
                        estado = true ;
                    }else{
                        estado = false ;
                    }

                     */
                }
                else{
                    Toast.makeText(getApplicationContext(), "Hubo un error en el Servicio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), Constante.g_const_error_conexion, Toast.LENGTH_SHORT).show();

            }
        });
        return estado;
    }

    private void RegistrarSesionInicio(RequestSesion requestSesion) {
        Call<ResponseSesion> call = ApiAdapter.getApiService().ControlSesionUsu(requestSesion);
        call.enqueue(new Callback<ResponseSesion>() {
            @Override
            public void onResponse(Call<ResponseSesion> call, Response<ResponseSesion> response) {
                if(response.isSuccessful()){
                    ResponseSesion rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_2000 ) {
                        //sincronizacionRutasBitacora();
                        txtContra.setText("");
                        sincronizacion(requestSesion.getCodUsuario());

                    }
                    else{
                        Toast.makeText(MainActivity.this, rb.getDescripcionResp(), Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(), "Hubo un error en el Servicio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSesion> call, Throwable t) {
                Toast.makeText(getApplicationContext(), Constante.g_const_error_conexion, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void CargarSucursales(Context context) {
        listSucursal = new ArrayList<>();
        Call<SucursalResponse> call = ApiAdapter.getApiService().ObtenerSucursales();
        call.enqueue(new Callback<SucursalResponse>() {
            @Override
            public void onResponse(Call<SucursalResponse> call, Response<SucursalResponse> response) {
                if(response.isSuccessful()){
                    SucursalResponse rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_2000) {

                        listaSucursalServicio = rb.getResultadoSucursal().getRespuesta().getListSucursales();
                        for (int i = Constante.g_const_0;i<listaSucursalServicio.size();i++){
                            sucursal = new SucursalEntity(listaSucursalServicio.get(i).getNtraSucursal(),listaSucursalServicio.get(i).getDescripcion(),
                                    listaSucursalServicio.get(i).getCodUbigeo(),listaSucursalServicio.get(i).getFactor());
                            listSucursal.add(sucursal);
                        }

                        adapter2 = new SpinnerComboAdapter(context, listSucursal);
                        sp_sucursal.setAdapter(adapter2);
                        boton_recargar.setVisibility(View.INVISIBLE);



                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error en el registro de sucursales", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(), "Hubo un error en el Servicio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SucursalResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), Constante.g_const_error_conexion, Toast.LENGTH_SHORT).show();

            }
        });

        sp_sucursal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SucursalEntity clickedItem = (SucursalEntity) parent.getItemAtPosition(position);
                codSucursal = clickedItem.getNtraSucursal();
                codUbigeo = clickedItem.getCodUbigeo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void obtenerBitacorasSincro(){
        //ListasRutasBitacoras

        bitacoraVendedorViewModel = new ViewModelProvider(this).get(BitacoraVendedorViewModel.class);
        bitacoraVendedorViewModel.getmAllBitacorasVendedorSin().observe(this, bitsinc -> {

            if( bitsinc != null){
                listaBitacoras = bitsinc;
                listRestRutasBitacora = new ArrayList<>();

                for (BitacoraVendedorEntity bitacoraVendedorEntity: listaBitacoras){

                    rbv = new ResquestRutasBitacora(bitacoraVendedorEntity.getCodRuta(),bitacoraVendedorEntity.getCodCliente(),bitacoraVendedorEntity.getFecha(),
                            bitacoraVendedorEntity.getVisita(),bitacoraVendedorEntity.getMotivo(),bitacoraVendedorEntity.getUsuario(),bitacoraVendedorEntity.getIp(),bitacoraVendedorEntity.getMac(),bitacoraVendedorEntity.getCordenadaX(),
                            bitacoraVendedorEntity.getCordenadaY(),bitacoraVendedorEntity.getEstado());
                    listRestRutasBitacora.add(rbv);
                }
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    MetodosGlobales.mostrarMensaje(this, "Porfavor Acceda al permiso");
                    finish();

                }

            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


    private void sincronizacion(int codVendedor) {
        //Cod vendedor temporal para hacer la sincronizacion

        sessionManager.createSession(codVendedor,codUbigeo,codSucursal);


        sincronizacionViewModel.SincronizarDataCompleta(codVendedor,codUbigeo,this,Constante.g_const_2);


    }
    /*
    private  void sincronizacionRutasBitacora(){

        if(listRestRutasBitacora.size() != 0){
            RequestSincronizacionRutas bitRu = new RequestSincronizacionRutas(listRestRutasBitacora);

            registrar_bitacora_ruta_post_sinc(bitRu);
        }


    }


    public void registrar_bitacora_ruta_post_sinc(RequestSincronizacionRutas bitRu){
        //Integer codCliente = Constante.g_const_0;
        Call<ResponseRegistroBitacoras> call = ApiAdapter.getApiService().registrarRutasSinBitacoras(bitRu);
        call.enqueue(new Callback<ResponseRegistroBitacoras>() {
            @Override
            public void onResponse(Call<ResponseRegistroBitacoras> call, Response<ResponseRegistroBitacoras> response) {
                if(response.isSuccessful()){
                    ResponseRegistroBitacoras rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_2000) {


                        MetodosGlobales.mostrarMensaje( MainActivity.this, "Bitacoras enviadas");
                        bitacoraVendedorViewModel = new ViewModelProvider(MainActivity.this).get(BitacoraVendedorViewModel.class);
                        bitacoraVendedorViewModel.deleteAll();



                    }
                    else{
                        if(rb.getErrorWebSer().getTipoErr() == 0){
                            Toast.makeText(getApplicationContext(), rb.getErrorWebSer().getDescripcionErr(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error en el registro de la Bitacora", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(), "Hubo un error en el Servicio", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegistroBitacoras> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

}
