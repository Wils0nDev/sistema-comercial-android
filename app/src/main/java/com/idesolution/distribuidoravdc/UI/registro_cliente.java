package com.idesolution.distribuidoravdc.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.idesolution.distribuidoravdc.Adaptador.SpinnerConceptoAdapter;
import com.idesolution.distribuidoravdc.Adaptador.SpinnerConceptoEntityAdapter;
import com.idesolution.distribuidoravdc.Adaptador.SpinnerDepartAdapter;
import com.idesolution.distribuidoravdc.Adaptador.SpinnerDistritoAdapter;
import com.idesolution.distribuidoravdc.Adaptador.SpinnerProvinciaAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.BD.Entity.DepartamentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DistritoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProvinciaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Ruta;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ConceptoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.RutaViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.UbigeoViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.IO.ApiAdapter;
import com.idesolution.distribuidoravdc.IO.ApiAdapterSunatReniec;
import com.idesolution.distribuidoravdc.IO.InterfazApiService;
import com.idesolution.distribuidoravdc.IO.Request.RequestConsultaDoc;
import com.idesolution.distribuidoravdc.IO.Request.ResquestCliente;
import com.idesolution.distribuidoravdc.IO.Response.ResponseRegistroCliente;
import com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec.ResponseWsReniec;
import com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec.ResponseWsSunat;
import com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec.ResponseWsSunatService;
import com.idesolution.distribuidoravdc.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registro_cliente extends AppCompatActivity implements View.OnClickListener{//implements Callback<ResponseRegistroCliente> {
    private ClienteViewModel clienteVM;
    private ConceptoViewModel conceptoVM;
    private RutaViewModel rutaVM;
    private List<String> listaConceptos;
    private List<String> listaA;
    private List<String> listaB;
    private ArrayList<String> listaDescripcionRutas;
    private ArrayList<Integer> listaCodigoRutas;
    private List<Ruta> listaRutas;
    private Integer codigoPersona;
    private Integer cont;
    private Integer flag;
    private Integer proceso;
    private Integer transaccion;

    private Spinner spinnerTipoPersona;
    private Spinner spinnerTipoDocumento;
    private Spinner spinnerListaPrecios;
    private Spinner spinnerListaFrecuencia;
    private EditText numDocumento;
    private EditText nombre;
    private EditText apePaterno;
    private EditText apeMaterno;
    private EditText direccion;
    private EditText correo;
    private EditText telefono;
    private EditText celular;
    private EditText tvRuc;
    private EditText tvRazonSocial;

    Spinner sp_departamento;
    Spinner sp_provincia;
    Spinner sp_distrito;
    Spinner sp_clasifCli;
    SpinnerAdapter adapterDepartamento;
    SpinnerAdapter adapterProvincia;
    SpinnerAdapter adapterDistrito;
    SpinnerAdapter adapterTipoPersona;
    SpinnerAdapter adapterTipoDocumento;
    SpinnerAdapter adapterPrecio;
    SpinnerAdapter adapterFrecuencia;
    SpinnerAdapter adapterClasifCli;
    String codDepart = Constante.G_CONST_VACIO;
    String codProvin = Constante.G_CONST_VACIO;
    String codUbigeo = Constante.G_CONST_VACIO;
    UbigeoViewModel ubigeoViewModel;

    private TextWatcher twNumDoc;
    private TextWatcher twRuc;
    ProgressDialog progressDialog;
    SweetAlertDialog pDialog;

    TextInputLayout ti_numDocu;
    TextInputLayout ti_RUC;
    TextInputLayout ti_nombres;
    TextInputLayout ti_apePat;
    TextInputLayout ti_apeMat;
    TextInputLayout ti_razSocial;
    TextInputLayout ti_direcccion;
    TextInputLayout ti_correo;
    TextInputLayout ti_latitud,ti_longitud ;
    private TextWatcher twLatitud, twLongitud;
    private EditText latitud, longitud;
    private Button btnVerMapa;
    private final static int RESPUESTA_VISITA = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cliente);

        proceso = getIntent().getIntExtra("parametro_proceso", Constante.g_const_0);
        transaccion = getIntent().getIntExtra("parametro_transaccion", Constante.g_const_0);
        spinnerTipoPersona = (Spinner) findViewById(R.id.spTipoPersona);
        spinnerTipoDocumento = (Spinner) findViewById(R.id.spTipoDocumento);
        spinnerListaPrecios = (Spinner) findViewById(R.id.spListaPrecios);
        spinnerListaFrecuencia = (Spinner) findViewById(R.id.spFrecuencia);
        numDocumento = (EditText) findViewById(R.id.etNumeroDocumento);
        nombre = (EditText) findViewById(R.id.etNombres);
        apePaterno = (EditText) findViewById(R.id.etApellidoPaterno);
        apeMaterno = (EditText) findViewById(R.id.etApellidoMaterno);
        direccion = (EditText) findViewById(R.id.etDireccion);
        correo = (EditText) findViewById(R.id.etCorreo);
        telefono = (EditText) findViewById(R.id.etTelefono);
        celular = (EditText) findViewById(R.id.etCelular);
        btnVerMapa = (Button) findViewById(R.id.btnMapaClient);
        tvRuc = (EditText) findViewById(R.id.etRUC);
        tvRazonSocial = (EditText) findViewById(R.id.etRazonSocial);
        latitud = (EditText) findViewById(R.id.etLatitud);
        longitud = (EditText) findViewById(R.id.etLongitud);

        progressDialog = new ProgressDialog(this);
        sp_departamento = (Spinner) findViewById(R.id.spDepartamentoCli);
        sp_provincia = (Spinner) findViewById(R.id.spProvinciaCli);
        sp_distrito = (Spinner) findViewById(R.id.spDistritoCli);
        sp_clasifCli = (Spinner) findViewById(R.id.spClasCliente);
        ubigeoViewModel = new ViewModelProvider(this).get(UbigeoViewModel.class);

        ti_numDocu = (TextInputLayout) findViewById(R.id.materialNumDoc);
        ti_RUC = (TextInputLayout) findViewById(R.id.materialRuc);
        ti_nombres = (TextInputLayout) findViewById(R.id.materialetNombres);
        ti_apePat = (TextInputLayout) findViewById(R.id.materialetApellidoPaterno);
        ti_apeMat = (TextInputLayout) findViewById(R.id.materialetApellidoMaterno);
        ti_razSocial = (TextInputLayout) findViewById(R.id.materialetRazonSocial);
        ti_direcccion = (TextInputLayout) findViewById(R.id.materialetDireccion);
        ti_correo = (TextInputLayout) findViewById(R.id.materialetCorreo);
        ti_latitud = (TextInputLayout) findViewById(R.id.materialLatitud);
        ti_longitud = (TextInputLayout) findViewById(R.id.materialLongitud);


        cargar_combos();
        cargarComboDepartamento();
        codigoPersona = Constante.g_const_0;
        if(proceso == Constante.g_const_2){
            this.setTitle(R.string.titulo_editar_cliente);
            clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
            clienteVM.buscarCliente(transaccion).observe(this, cliente -> {
                spinnerTipoPersona.setSelection(cliente.getTipoPersona() - Constante.g_const_1);
                spinnerTipoDocumento.setSelection(cliente.getTipoDocumento() - Constante.g_const_1);
                spinnerListaPrecios.setSelection(cliente.getTipoListaPrecio() - Constante.g_const_1);
                spinnerListaFrecuencia.setSelection(cliente.getFrecuencia() - Constante.g_const_1);

                if(cliente.getTipoDocumento() == Constante.g_const_3){
                    nombre.setText(cliente.getRazonSocial());
                    numDocumento.setText(cliente.getRuc());
                }else{
                    numDocumento.setText(cliente.getNumeroDocumento());
                    nombre.setText(cliente.getNombres());
                    apePaterno.setText(cliente.getApellidoPaterno());
                    apeMaterno.setText(cliente.getApellidoMaterno());
                }
                direccion.setText(cliente.getDireccion());
                correo.setText(cliente.getCorreo());
                telefono.setText(cliente.getTelefono());
                celular.setText(cliente.getCelular());
                codigoPersona = cliente.getCodCliente();
            });

            spinnerTipoPersona.setEnabled(false);
            spinnerTipoDocumento.setEnabled(false);
            spinnerListaPrecios.setEnabled(false);
            spinnerListaFrecuencia.setEnabled(false);
            numDocumento.setEnabled(false);
            nombre.requestFocus();
        }
        else{
            this.setTitle(R.string.titulo_nuevo_cliente);
        }

        cont = Constante.g_const_0;
        flag = Constante.g_const_0;
        numDocumento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if(proceso == Constante.g_const_1)
                        buscarNumeroDocumento();
                }
            }
        });

        sp_departamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DepartamentoEntity clickedItem = (DepartamentoEntity) parent.getItemAtPosition(position);
                codDepart = clickedItem.getCodDepartamento();

                CargarProvincia(codDepart);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_provincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProvinciaEntity clickedItem = (ProvinciaEntity) parent.getItemAtPosition(position);
                codProvin = clickedItem.getCodProvincia();

                CargarDistrito(codDepart,codProvin);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_distrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DistritoEntity clickedItem = (DistritoEntity) parent.getItemAtPosition(position);
                codUbigeo = clickedItem.getUbigeo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        numDocumento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().trim().length() != Constante.g_s_const_0){
                    buscarNumeroDocumento();
                }
            }
        });

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String c = s.toString();
                if (!c.equals(c.toUpperCase()))
                {
                    c = c.toUpperCase();
                    nombre.setText(c);
                }
                nombre.setSelection(nombre.getText().length());
            }
        });

        apePaterno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String c = s.toString();
                if (!c.equals(c.toUpperCase()))
                {
                    c = c.toUpperCase();
                    apePaterno.setText(c);
                }
                apePaterno.setSelection(apePaterno.getText().length());
            }
        });

        apeMaterno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String c = s.toString();
                if (!c.equals(c.toUpperCase()))
                {
                    c = c.toUpperCase();
                    apeMaterno.setText(c);
                }
                apeMaterno.setSelection(apeMaterno.getText().length());
            }
        });

        tvRazonSocial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String c = s.toString();
                if (!c.equals(c.toUpperCase()))
                {
                    c = c.toUpperCase();
                    tvRazonSocial.setText(c);
                }
                tvRazonSocial.setSelection(tvRazonSocial.getText().length());
            }
        });

        direccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String c = s.toString();
                if (!c.equals(c.toUpperCase()))
                {
                    c = c.toUpperCase();
                    direccion.setText(c);
                }
                direccion.setSelection(direccion.getText().length());
            }
        });

        spinnerTipoPersona.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == Constante.g_const_0){ //NATURAL
                    adapterTipoDocumento = new SpinnerConceptoAdapter(view.getContext(), listaB);
                    spinnerTipoDocumento.setAdapter(adapterTipoDocumento);
                    spinnerTipoDocumento.setEnabled(true);
                    numDocumento.setEnabled(true);
                    nombre.setEnabled(true);
                    apePaterno.setEnabled(true);
                    apeMaterno.setEnabled(true);
                    tvRuc.setEnabled(true);
                    tvRazonSocial.setEnabled(true);
                    numDocumento.requestFocus();
                }else{ //JURIDICA
                    adapterTipoDocumento = new SpinnerConceptoAdapter(view.getContext(), listaA);
                    spinnerTipoDocumento.setAdapter(adapterTipoDocumento);
                    spinnerTipoDocumento.setEnabled(false);
                    numDocumento.setText(Constante.G_CONST_VACIO);
                    nombre.setText(Constante.G_CONST_VACIO);
                    apePaterno.setText(Constante.G_CONST_VACIO);
                    apeMaterno.setText(Constante.G_CONST_VACIO);
                    spinnerTipoDocumento.setSelection(2);
                    tvRuc.requestFocus();
                    numDocumento.setEnabled(false);
                    nombre.setEnabled(false);
                    apePaterno.setEnabled(false);
                    apeMaterno.setEnabled(false);
                    tvRuc.setEnabled(true);
                    tvRazonSocial.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//Keypress

        twNumDoc = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == Constante.g_const_8 && spinnerTipoDocumento.getSelectedItemPosition() == Constante.g_const_0){
                    ConsultarUsuarioReniec(s.toString(),registro_cliente.this);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        numDocumento.addTextChangedListener(twNumDoc);

        twRuc = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == Constante.g_const_11 ){
                    ConsultarUsuarioSunat(s.toString(),registro_cliente.this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        tvRuc.addTextChangedListener(twRuc);

        btnVerMapa.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case (R.id.btnMapaClient):
                Intent mapaCliente = new Intent(registro_cliente.this, map_cliente.class);
                startActivityForResult(mapaCliente, RESPUESTA_VISITA);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            ArrayList<String> datos = data.getStringArrayListExtra("RESULTADO");
            latitud.setText(datos.get(0));
            longitud.setText(datos.get(1));

            String direcc = direccion.getText().toString();

            if(direcc.trim().isEmpty() || direcc.trim().equals("-")){
                direccion.setText(datos.get(2));
            }
            // MetodosGlobales.mostrarMensaje(this, ""+datos.get(2));
        }

    }


    private void CargarDistrito(String codDepart, String codProvin) {
        ubigeoViewModel.getAllDistritos(codDepart,codProvin).observe(this,distritos -> {
            adapterDistrito = new SpinnerDistritoAdapter(this,distritos);
            sp_distrito.setAdapter(adapterDistrito);
        });
    }

    private void CargarProvincia(String codDepart) {
        ubigeoViewModel.getAllProvincias(codDepart).observe(this, provincias ->{
            adapterProvincia = new SpinnerProvinciaAdapter(this,provincias);
            sp_provincia.setAdapter(adapterProvincia);
        });
    }

    private void cargarComboDepartamento() {
        ubigeoViewModel.getAllDepartamentos().observe(this, departamentos -> {
            //list_categorias = categorias;
            adapterDepartamento = new SpinnerDepartAdapter(this, departamentos);
            sp_departamento.setAdapter(adapterDepartamento);

        });
    }

    private void ConsultarUsuarioSunat(String numDoc, Context context) {
        ShowProgressSweetAlert(this,"Consultando cliente");
        RequestConsultaDoc request;
        request = new RequestConsultaDoc();
        request.setNumDocumento(numDoc);
        Call<ResponseWsSunat> call = ApiAdapterSunatReniec.getApiService().ConsultaDocumentoSunat(request);
        call.enqueue(new Callback<ResponseWsSunat>() {
            @Override
            public void onResponse(Call<ResponseWsSunat> call, Response<ResponseWsSunat> response) {
                if(response.isSuccessful()){
                    ResponseWsSunat rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_2000 ) {
                        tvRazonSocial.setText(rb.getRespuestaWsSunat().getRazonSocial());
                        direccion.setText(rb.getRespuestaWsSunat().getDomicilioFiscal());

                    }
                    else{
                    }

                }
                else{

                }
                FinishProgressSweetAlert();
            }

            @Override
            public void onFailure(Call<ResponseWsSunat> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), Constante.g_const_error_conexion, Toast.LENGTH_SHORT).show();
                FinishProgressSweetAlert();
                ConsultarUsuarioSunatBDServicio(numDoc,context);
            }
        });
    }

    private void ConsultarUsuarioSunatBDServicio(String numDoc, Context context) {
        ShowProgressSweetAlert(this,"Consultando cliente en servidor");
        RequestConsultaDoc request;
        request = new RequestConsultaDoc();
        request.setNumDocumento(numDoc);
        Call<ResponseWsSunatService> call = ApiAdapter.getApiService().ConsultaDocumentoSunatService(request);
        call.enqueue(new Callback<ResponseWsSunatService>() {
            @Override
            public void onResponse(Call<ResponseWsSunatService> call, Response<ResponseWsSunatService> response) {
                if(response.isSuccessful()){
                    ResponseWsSunatService rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_2000 ) {
                        tvRazonSocial.setText(rb.getDataSunat().getRazonSocial());
                        direccion.setText(rb.getDataSunat().getDireccionFizcal());

                    }

                }
                FinishProgressSweetAlert();
            }

            @Override
            public void onFailure(Call<ResponseWsSunatService> call, Throwable t) {
                Toast.makeText(getApplicationContext(), Constante.g_const_error_conexion, Toast.LENGTH_SHORT).show();
                FinishProgressSweetAlert();
            }
        });
    }

    private void ConsultarUsuarioReniec(String numDoc, Context context) {
        ShowProgressSweetAlert(this,"Consultando cliente");
        RequestConsultaDoc request;
        request = new RequestConsultaDoc();
        request.setNumDocumento(numDoc);
        Call<ResponseWsReniec> call = ApiAdapterSunatReniec.getApiService().ConsultaDocumentoReniec(request);
        call.enqueue(new Callback<ResponseWsReniec>() {
            @Override
            public void onResponse(Call<ResponseWsReniec> call, Response<ResponseWsReniec> response) {
                if(response.isSuccessful()){
                    ResponseWsReniec rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == Constante.g_const_2000 ) {
                        nombre.setText(rb.getRespuestaWsReniec().getNombres());
                        apePaterno.setText(rb.getRespuestaWsReniec().getApellidoPaterno());
                        apeMaterno.setText(rb.getRespuestaWsReniec().getApellidoMaterno());




                    }
                    else{

                        //Toast.makeText(context, rb.getDescripcionResp(), Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    // Toast.makeText(getApplicationContext(), "Hubo un error en el Servicio", Toast.LENGTH_SHORT).show();
                }
                FinishProgressSweetAlert();
                //finishDialog();
            }

            @Override
            public void onFailure(Call<ResponseWsReniec> call, Throwable t) {
                Toast.makeText(getApplicationContext(), Constante.g_const_error_conexion, Toast.LENGTH_SHORT).show();
                FinishProgressSweetAlert();
            }
        });
    }

    private void ShowProgressSweetAlert(Context context,String mensaje){
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(mensaje);
        pDialog.setCancelable(false);
        pDialog.show();
    }
    private void FinishProgressSweetAlert(){pDialog.dismiss();}

    public void buscarNumeroDocumento(){
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        clienteVM.buscarNumeroDocumento(numDocumento.getText().toString()).observe(this, contador ->{
            cont = contador;
            /*if(cont != Constante.g_const_0 && flag == Constante.g_const_0){
                Toast.makeText(this, "Número de documento ya existe", Toast.LENGTH_SHORT).show();
                numDocumento.requestFocus();
            }*/
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_registro_cliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Integer conexion = MetodosGlobales.verificarConexionInternet(getApplicationContext());
        switch (item.getItemId()) {
            case R.id.mnRegistrarCliente:
                if (validarDatos()){
                    flag = 1;
                    registrarCliente(Constante.g_const_0, Constante.g_const_0);
                    /*if (conexion == Constante.g_const_0)
                        registrarCliente(Constante.g_const_0, Constante.g_const_0);
                    else
                        registroClientePost();*/
                }
        }
        return true;
    }

    public boolean validarSoloLetras(String cadena){
        char letra;
        for (int i = Constante.g_const_0; i < cadena.length() - Constante.g_const_1 ; i++ ){
            letra = cadena.charAt(i);
            if(!(Character.isLetter(letra) || Character.isSpaceChar(letra)))
                return false;
        }
        return true;
    }

    public void LimpiarValidaciones(){
        ti_numDocu.setErrorEnabled(false);
        ti_RUC.setErrorEnabled(false);
        ti_nombres.setErrorEnabled(false);
        ti_apePat.setErrorEnabled(false);
        ti_apeMat.setErrorEnabled(false);
        ti_razSocial.setErrorEnabled(false);
        ti_direcccion.setErrorEnabled(false);
        ti_correo.setErrorEnabled(false);
        ti_latitud.setErrorEnabled(false);
        ti_longitud.setErrorEnabled(false);
    }

    public boolean validarDatos(){
        LimpiarValidaciones();
        if((spinnerTipoPersona.getSelectedItemPosition() + Constante.g_s_const_1) == Constante.g_s_const_1){ // NATURAL
            //Numero documento
            if(!MetodosGlobales.validarNulos(numDocumento.getText().toString().trim())){
                ti_numDocu.setError("Debe ingresar el número de documento");
                //Toast.makeText(this, "Debe ingresar el número de documento", Toast.LENGTH_SHORT).show();
                numDocumento.requestFocus();
                return false;
            }else{
                if((spinnerTipoDocumento.getSelectedItemPosition() + Constante.g_const_1) == Constante.g_const_1){
                    if(numDocumento.getText().toString().trim().length() != Constante.g_const_8){
                        ti_numDocu.setError("Número de documento debe tener 8 digitos");
                        //Toast.makeText(this, "Número de documento debe tener 8 digitos", Toast.LENGTH_SHORT).show();
                        numDocumento.requestFocus();
                        return false;
                    }
                }
                else{
                    if((spinnerTipoDocumento.getSelectedItemPosition() + Constante.g_const_1) == Constante.g_const_2){
                        if(numDocumento.getText().toString().trim().length() != Constante.g_const_12){
                            ti_numDocu.setError("Número de documento debe tener 12 digitos");
                            //Toast.makeText(this, "Número de documento debe tener 12 digitos", Toast.LENGTH_SHORT).show();
                            numDocumento.requestFocus();
                            return false;
                        }
                    }
                }

                if(cont != Constante.g_const_0 ){
                    ti_numDocu.setError("Numero de documento ya existe");
                    //Toast.makeText(this, "Numero de documento ya existe", Toast.LENGTH_SHORT).show();
                    numDocumento.requestFocus();
                    return false;
                }
            }

            if(MetodosGlobales.validarNulos(tvRuc.getText().toString().trim())){
                if(tvRuc.getText().toString().trim().length() != Constante.g_const_11){
                    ti_RUC.setError("Ruc debe tener 11 digitos");
                    //Toast.makeText(this, "Ruc debe tener 11 digitos", Toast.LENGTH_SHORT).show();
                    tvRuc.requestFocus();
                    return false;
                }
            }

            //Nombres
            if(!MetodosGlobales.validarNulos(nombre.getText().toString().trim())){
                ti_nombres.setError("Debe ingresar el Nombre");
                //Toast.makeText(this, "Debe ingresar el Nombre ", Toast.LENGTH_SHORT).show();
                nombre.requestFocus();
                return false;
            }else{
                if(!validarSoloLetras(nombre.getText().toString().trim())){
                    ti_nombres.setError("Nombre solo debe contener letras");
                    //Toast.makeText(this, "Nombre solo debe contener letras", Toast.LENGTH_SHORT).show();
                    nombre.requestFocus();
                    return false;
                }
            }

            //APELLIDO
            if(!MetodosGlobales.validarNulos(apePaterno.getText().toString().trim())){
                ti_apePat.setError("Debe ingresar el apellido paterno");
                //Toast.makeText(this, "Debe ingresar el apellido paterno ", Toast.LENGTH_SHORT).show();
                apePaterno.requestFocus();
                return false;
            }else{
                if(!validarSoloLetras(apePaterno.getText().toString().trim())){
                    ti_apePat.setError("Apellido paterno solo debe contener letras");
                    //Toast.makeText(this, "Apellido paterno solo debe contener letras", Toast.LENGTH_SHORT).show();
                    apePaterno.requestFocus();
                    return false;
                }
            }
            if(!MetodosGlobales.validarNulos(apeMaterno.getText().toString().trim())){
                ti_apeMat.setError("Debe ingresar el apellido materno");
                ti_apeMat.requestFocus();
                return false;
            }else
            if(MetodosGlobales.validarNulos(apeMaterno.getText().toString().trim())){
                if(!validarSoloLetras(apeMaterno.getText().toString())){
                    ti_apeMat.setError("Apellido solo debe contener letras");
                    //Toast.makeText(this, "Apellido solo debe contener letras", Toast.LENGTH_SHORT).show();
                    apeMaterno.requestFocus();
                    return false;
                }
            }

            //Validar RUC con Numero de documento
            if(MetodosGlobales.validarNulos(numDocumento.getText().toString().trim()) && MetodosGlobales.validarNulos(tvRuc.getText().toString().trim())){

                if(!tvRuc.getText().toString().trim().contains(numDocumento.getText().toString().trim())){
                    tvRuc.setError(Constante.g_const_error_val_ruc_numdoc_igual);
                    //Toast.makeText(this, Constante.g_const_error_val_ruc_numdoc_igual , Toast.LENGTH_SHORT).show();
                    tvRuc.requestFocus();
                    return false;
                }
            }

        }else{ //JURIDICA
            //RUC
            if(!MetodosGlobales.validarNulos(tvRuc.getText().toString().trim())){
                ti_RUC.setError("Debe ingresar RUC");
                //Toast.makeText(this, "Debe ingresar RUC", Toast.LENGTH_SHORT).show();
                tvRuc.requestFocus();
                return false;
            }else{
                if(tvRuc.getText().toString().trim().length() != Constante.g_const_11){
                    ti_RUC.setError("Ruc debe tener 11 digitos");
                    //Toast.makeText(this, "Ruc debe tener 11 digitos", Toast.LENGTH_SHORT).show();
                    tvRuc.requestFocus();
                    return false;
                }
            }

            //RAZON SOCIAL
            if(!MetodosGlobales.validarNulos(tvRazonSocial.getText().toString().trim())){
                ti_razSocial.setError("Debe ingresar la Razon Social");
                //Toast.makeText(this, "Debe ingresar la Razon Social", Toast.LENGTH_SHORT).show();
                tvRazonSocial.requestFocus();
                return false;
            }
        }




        /*
        //Numero documento
        if(!MetodosGlobales.validarNulos(numDocumento.getText().toString())){
            Toast.makeText(this, "Debe igresar el número de documento", Toast.LENGTH_SHORT).show();
            numDocumento.requestFocus();
            return false;
        }else
        {
            //NUEVAS VALIDACIONES
            if((spinnerTipoDocumento.getSelectedItemPosition() + Constante.g_const_1) == Constante.g_const_1 || (spinnerTipoDocumento.getSelectedItemPosition() + Constante.g_const_1) == Constante.g_const_2){
                if((spinnerTipoDocumento.getSelectedItemPosition() + Constante.g_const_1) == Constante.g_const_1){
                    if(numDocumento.getText().toString().length() != Constante.g_const_8){
                        Toast.makeText(this, "Número de documento debe tener 8 digitos", Toast.LENGTH_SHORT).show();
                        numDocumento.requestFocus();
                        return false;
                    }
                }
                else{
                    if((spinnerTipoDocumento.getSelectedItemPosition() + Constante.g_const_1) == Constante.g_const_2){
                        if(numDocumento.getText().toString().length() != Constante.g_const_12){
                            Toast.makeText(this, "Número de documento debe tener 12 digitos", Toast.LENGTH_SHORT).show();
                            numDocumento.requestFocus();
                            return false;
                        }
                    }
                }
            }else
            {
                if((spinnerTipoDocumento.getSelectedItemPosition() + Constante.g_const_1) == Constante.g_const_3){
                    if(numDocumento.getText().toString().length() != Constante.g_const_11){
                        Toast.makeText(this, "Ruc debe tener 11 digitos", Toast.LENGTH_SHORT).show();
                        numDocumento.requestFocus();
                        return false;
                    }
                }
            }
        }

        //Nombres / Razon Social
        if(!MetodosGlobales.validarNulos(nombre.getText().toString())){
            Toast.makeText(this, "Debe igresar el Nombre / Razon Social", Toast.LENGTH_SHORT).show();
            nombre.requestFocus();
            return false;
        }else{
            if(spinnerTipoDocumento.getSelectedItemPosition() + Constante.g_const_1 != Constante.g_const_3){
                if(!validarSoloLetras(nombre.getText().toString())){
                    Toast.makeText(this, "Nombre solo debe contener letras", Toast.LENGTH_SHORT).show();
                    nombre.requestFocus();
                    return false;
                }
            }
        }

        //Apellidos
        if(MetodosGlobales.validarNulos(apePaterno.getText().toString())){
            if(!validarSoloLetras(apePaterno.getText().toString())){
                Toast.makeText(this, "Apellido solo debe contener letras", Toast.LENGTH_SHORT).show();
                apePaterno.requestFocus();
                return false;
            }
        }

        if(MetodosGlobales.validarNulos(apeMaterno.getText().toString())){
            if(!validarSoloLetras(apeMaterno.getText().toString())){
                Toast.makeText(this, "Apellido solo debe contener letras", Toast.LENGTH_SHORT).show();
                apeMaterno.requestFocus();
                return false;
            }
        }*/

        //Direccion
        if  (!MetodosGlobales.validarNulos(direccion.getText().toString().trim())){
            ti_direcccion.setError("Debe ingresar la dirección");
            //Toast.makeText(this, "Debe ingresar la dirección", Toast.LENGTH_SHORT).show();
            direccion.requestFocus();
            return false;
        }

        if  (!MetodosGlobales.validarNulos(latitud.getText().toString().trim())){
            ti_latitud.setError("Debe buscar la dirección");
            //Toast.makeText(this, "Debe ingresar la dirección", Toast.LENGTH_SHORT).show();
            latitud.requestFocus();
            return false;
        }

        if  (!MetodosGlobales.validarNulos(longitud.getText().toString().trim())){
            ti_longitud.setError("Debe buscar la dirección");
            //Toast.makeText(this, "Debe ingresar la dirección", Toast.LENGTH_SHORT).show();
            longitud.requestFocus();
            return false;
        }

        if(MetodosGlobales.validarNulos(correo.getText().toString().trim())){
            if (!validarEmail(correo.getText().toString().trim())){
                ti_correo.setError("Formato de correo no valido");
                //Toast.makeText(this, "Formato de correo no valido", Toast.LENGTH_SHORT).show();
                correo.requestFocus();
                return false;
            }
        }

        return true;
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void cargar_combos() {
        conceptoVM = new ViewModelProvider(this).get(ConceptoViewModel.class);
        rutaVM = new ViewModelProvider(this).get(RutaViewModel.class);

        //Clasificacion de clientes

        conceptoVM.getConceptoEntidad(4).observe(this, listConceptos -> {
            //list_categorias = categorias;
            adapterClasifCli = new SpinnerConceptoEntityAdapter(this, listConceptos);
            sp_clasifCli.setAdapter(adapterClasifCli);

        });

        //Tipo Persona
        conceptoVM.obtenerConceptos(1).observe(this, conceptos ->{
            listaConceptos = conceptos;
            adapterTipoPersona = new SpinnerConceptoAdapter(this,conceptos);
            spinnerTipoPersona.setAdapter(adapterTipoPersona);

            //spinnerTipoPersona.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaConceptos));
        });

        //Tipos Documento
        conceptoVM.obtenerConceptos(2).observe(this, conceptos ->{
            listaConceptos = conceptos;
            listaA = new ArrayList<String>();
            listaB = new ArrayList<String>();
            int m = 0;
            for(String a: conceptos){
                listaA.add(a);
                if(m < 2){
                    listaB.add(a);
                }
                m = m + 1;
            }
            adapterTipoDocumento = new SpinnerConceptoAdapter(this,listaB);
            spinnerTipoDocumento.setAdapter(adapterTipoDocumento);

            //spinnerTipoDocumento.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaConceptos));
        });

        //Frecuencia
        conceptoVM.obtenerConceptos(5).observe(this, conceptos ->{
            listaConceptos = conceptos;
            adapterFrecuencia = new SpinnerConceptoAdapter(this,conceptos);
            spinnerListaFrecuencia.setAdapter(adapterFrecuencia);
            //spinnerListaFrecuencia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaConceptos));
        });

        //Precios
        conceptoVM.obtenerConceptos(7).observe(this, conceptos ->{
            listaConceptos = conceptos;
            adapterPrecio = new SpinnerConceptoAdapter(this,conceptos);
            spinnerListaPrecios.setAdapter(adapterPrecio);
            //spinnerListaPrecios.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaConceptos));
        });

        //Lista Rutas
        /*rutaVM.obtenerRutas().observe(this, rutas ->{
            listaRutas = rutas;
            listaCodigoRutas = new ArrayList<Integer>();
            listaDescripcionRutas = new ArrayList<String>();
            for(Ruta r: rutas){
              listaCodigoRutas.add(r.getCodigoRuta());
              listaDescripcionRutas.add(r.getDescripcion());
            }
            spinnerRuta.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaDescripcionRutas));
        });*/
    }

    public void registrarCliente(Integer codigo, Integer flagSincronizacion){
        flag = Constante.g_const_1;
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);

        short tPersona = Short.parseShort(String.valueOf(spinnerTipoPersona.getSelectedItemPosition() + Constante.g_const_1));
        short tDocumento = Short.parseShort(String.valueOf(spinnerTipoDocumento.getSelectedItemPosition() + Constante.g_const_1));
        if(tPersona == 2){
            tDocumento = 3;
        }
        String nDocumento = Constante.G_CONST_VACIO;
        String ruc = Constante.G_CONST_VACIO;
        String nom = Constante.G_CONST_VACIO;
        String razonSocial = Constante.G_CONST_VACIO;
        String apePat = Constante.G_CONST_VACIO;
        String apeMat = Constante.G_CONST_VACIO;
        short frec = Short.parseShort(String.valueOf(spinnerListaFrecuencia.getSelectedItemPosition() + Constante.g_const_1));
        short tPrecio = Short.parseShort(String.valueOf(spinnerListaPrecios.getSelectedItemPosition() + Constante.g_const_1));
        Concepto conc = (Concepto) sp_clasifCli.getSelectedItem();
        int clasificacion = conc.getCorrelativo();//getSelectedItemPosition() + Constante.g_const_1));
        String busqueda = null;
        String latitudX = Constante.G_CONST_VACIO;
        String longitudY = Constante.G_CONST_VACIO;
        String documentoLocal = "";
        String nombreCompleto = "";




        latitudX = latitud.getText().toString();
        longitudY = longitud.getText().toString();


        ruc = tvRuc.getText().toString().trim();
        razonSocial = tvRazonSocial.getText().toString().trim();
        nDocumento = numDocumento.getText().toString();
        nom = nombre.getText().toString();
        apePat = apePaterno.getText().toString();
        apeMat = apeMaterno.getText().toString();

        if(nom == null)
            nom = Constante.G_CONST_VACIO;
        if(apePat == null)
            apePat = Constante.G_CONST_VACIO;
        if(apeMat == null)
            apeMat = Constante.G_CONST_VACIO;
        if(nDocumento == null)
            nDocumento = Constante.G_CONST_VACIO;
        if(ruc == null)
            ruc = Constante.G_CONST_VACIO;
        if(razonSocial == null)
            razonSocial = Constante.G_CONST_VACIO;

        if(tPersona == Constante.g_s_const_2) {

            documentoLocal = ruc;
            nombreCompleto = razonSocial;
        }
        else {

            documentoLocal = nDocumento;
            nombreCompleto = nom+" "+apePat+" "+apeMat;
        }

        busqueda = nom + " " + apePat + " " + apeMat + " " + nDocumento + " " + razonSocial + " " + ruc;

        Cliente nc = new Cliente(
                codigo,
                tPersona,
                tDocumento,
                nDocumento.trim(),
                ruc.trim(),
                razonSocial.trim(),
                nom.trim(),
                apePat.trim(),
                apeMat.trim(),
                direccion.getText().toString().trim(),
                correo.getText().toString().trim(),
                telefono.getText().toString().trim(),
                celular.getText().toString().trim(),
                frec,
                tPrecio,
                Short.parseShort(String.valueOf(0)), //ruta
                busqueda,
                flagSincronizacion,
                codUbigeo,
                clasificacion
        );
        LocalizacionEntity lc = new LocalizacionEntity(
                codigo,
                documentoLocal,
                nombreCompleto,
                latitudX,
                longitudY,
                flagSincronizacion
        );

        if(proceso == Constante.g_const_1){
            clienteVM.insert(nc,lc, getApplicationContext());
            int conexion = MetodosGlobales.verificarConexionInternet(getApplicationContext());
            if (conexion == Constante.g_const_0)
                Toasty.success(getApplicationContext(), "Cliente registrado", Toast.LENGTH_SHORT, true).show();
            //MetodosGlobales.mostrarMensaje(getApplicationContext(), "Cliente registrado");
        }/*else
        {
            if(proceso == Constante.g_const_2){
                clienteVM.update(transaccion, nc.getRazonSocial(), nc.getNombres(), nc.getApellidoPaterno(), nc.getApellidoMaterno(), nc.getDireccion(), nc.getCorreo(), nc.getTelefono(), nc.getCelular(), nc.getFlag());
                MetodosGlobales.mostrarMensaje(getApplicationContext(), "Cliente modificado");
            }
        }*/

        finish();
    }

    /*public void registroClientePost(){
        flag = Constante.g_const_1;

        short tPersona = Short.parseShort(String.valueOf(spinnerTipoPersona.getSelectedItemPosition() + Constante.g_const_1));
        short tDocumento = Short.parseShort(String.valueOf(spinnerTipoDocumento.getSelectedItemPosition() + Constante.g_const_1));
        String nDocumento = Constante.G_CONST_VACIO;
        String ruc = Constante.G_CONST_VACIO;
        String nom = Constante.G_CONST_VACIO;
        String razonSocial = Constante.G_CONST_VACIO;
        String apePat = Constante.G_CONST_VACIO;
        String apeMat = Constante.G_CONST_VACIO;
        short frec = Short.parseShort(String.valueOf(spinnerListaFrecuencia.getSelectedItemPosition() + Constante.g_const_1));
        short tPrecio = Short.parseShort(String.valueOf(spinnerListaPrecios.getSelectedItemPosition() + Constante.g_const_1));

        if(tDocumento == Constante.g_const_3) {
            ruc = numDocumento.getText().toString();
            razonSocial = nombre.getText().toString();
        }
        else {
            nDocumento = numDocumento.getText().toString();
            nom = nombre.getText().toString();
            apePat = apePaterno.getText().toString();
            apeMat = apeMaterno.getText().toString();
        }

        ResquestCliente nc = new ResquestCliente(
                Constante.g_s_const_0,
                Constante.g_const_0,
                tPersona,
                tDocumento,
                nDocumento,
                ruc,
                razonSocial,
                nom,
                apePat,
                apeMat,
                direccion.getText().toString(),
                correo.getText().toString(),
                telefono.getText().toString(),
                celular.getText().toString(),
                null,
                null,
                null,
                null,
                frec,
                tPrecio,
                Short.parseShort(String.valueOf(1)), //ruta
                "alex",
                "15.15.15.16",
                "AB:CD:EF"
        );
        registrar_modificar_cliente_post(nc);
    }

    public void registrar_modificar_cliente_post(ResquestCliente nc){
        Call<ResponseRegistroCliente> call = ApiAdapter.getApiService().RegistroActualizacionCliente(Short.parseShort(proceso.toString()), codigoPersona, nc.getTipoPersona(), nc.getTipoDocumento(), nc.getNumDocumento(), nc.getRuc(), nc.getRazonSocial(), nc.getNombres(), nc.getApePaterno(), nc.getApeMaterno(), nc.getDireccion(), nc.getCorreo(), nc.getTelefono(), nc.getCelular(), nc.getUbigeo(), nc.getOrdenAtencion(), nc.getPerfilCliente(), nc.getClasificacion(), nc.getFrecuencia(), nc.getTipoListaPrecio(), nc.getCodRuta(), nc.getUsuario(), nc.getIp(), nc.getMac());
        call.enqueue(new Callback<ResponseRegistroCliente>() {
            @Override
            public void onResponse(Call<ResponseRegistroCliente> call, Response<ResponseRegistroCliente> response) {
                if(response.isSuccessful()){
                    ResponseRegistroCliente c = response.body();
                    if(c.getErrorWebSer().getCodigoErr() == Constante.g_const_2000) {
                        registrarCliente(c.getResultado().getRegMod().getCodCliente(), Constante.g_const_1);
                    }
                    else{
                        if(c.getErrorWebSer().getTipoErr() == Constante.g_const_0){
                            Toast.makeText(getApplicationContext(), c.getErrorWebSer().getDescripcionErr(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error en el registro del cliente", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Hubo un error en el registro del cliente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegistroCliente> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
