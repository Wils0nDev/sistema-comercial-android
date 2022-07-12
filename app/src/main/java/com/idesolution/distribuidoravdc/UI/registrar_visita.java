package com.idesolution.distribuidoravdc.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.location.Address;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.idesolution.distribuidoravdc.Adaptador.CategoriaListAdapter;
import com.idesolution.distribuidoravdc.Adaptador.SpinnerConceptoAdapter;
import com.idesolution.distribuidoravdc.Adaptador.SpinnerDepartAdapter;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;
import com.idesolution.distribuidoravdc.BD.Entity.BitacoraVendedorEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ClienteSpinnerEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.BD.Repository.BitacoraVendedorViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ConceptoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.VendedorViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Entidad.LoginLocal;
import com.idesolution.distribuidoravdc.Fragments.f_lista_menu;
import com.idesolution.distribuidoravdc.IO.ApiAdapter;
import com.idesolution.distribuidoravdc.IO.Request.ResquestCliente;
import com.idesolution.distribuidoravdc.IO.Request.ResquestRutasBitacora;
import com.idesolution.distribuidoravdc.IO.Response.ResponseRegistroBitacoras;
import com.idesolution.distribuidoravdc.IO.Response.ResponseRegistroCliente;
import com.idesolution.distribuidoravdc.MainActivity;
import com.idesolution.distribuidoravdc.R;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registrar_visita extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    int  ntraUsuario;
    private Bundle parametrosEntrada;
    SessionManager sessionManager;
    LoginLocal loginLocal;
    private VendedorViewModel RutaVM;
    private  BitacoraVendedorViewModel bitacoraVendedorVM;
    private EditText fechaEdit;
    private int dia, mes, anio;
    private Calendar calendario;
    private List<ClienteSpinnerEntity> listaClientes;
    private ArrayList<String> listaCodigoCliente;
    private ArrayList<String> listaCodigoRuc;
    private ArrayList<String> listaRazonSocial;
    private ArrayList<String> listaNombreCliente;
    private TextView spDescRutas;
    private Button btnConfirmarVisita, btnfechaCalendar, btnCancelarV;
    private EditText editMotivo_text;
    private RadioGroup radioGroup_value;
    private RadioButton radiobutton_value;
    private f_lista_menu lista_menu;
    private String dias, mess;


    //variables para llenar combos
    private ClienteViewModel clienteVM;
    private Spinner spSelectCliente;
    private ConceptoViewModel conceptoVM;
    private List<String> listaConceptos;
    private Spinner spMotivoConcepto;
    //valores a enviar
    private String textFecha;
    private Integer icodruta;
    private Integer pocision;
    private Integer pocisionMotivo;
    private String cod_cliente;
    private String cod_ruc;
    private String text_motivo;
    private Integer rgButtonValor;
    private String rdButtonValor;
    public String bestProvider;
    private String  latitud, longitud;
    private  Integer valorVisita;
    private Integer estado = 1;

    SpinnerAdapter adapterCliente;


    private FusedLocationProviderClient fusedLocationClient;
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_visita);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        sessionManager = new SessionManager(this);
        loginLocal = sessionManager.getUserDetail();
        ntraUsuario = loginLocal.getNtraUsuario();

        btnfechaCalendar = (Button) findViewById(R.id.btnFecha);
        fechaEdit = (EditText) findViewById(R.id.editFecha);
        spSelectCliente = (Spinner) findViewById(R.id.spSelectCliente);
        spDescRutas = (TextView) findViewById(R.id.spDescRuta);
        btnConfirmarVisita = (Button) findViewById(R.id.btnConfirmar);
        spMotivoConcepto = (Spinner) findViewById(R.id.editMotivo);
        radioGroup_value = (RadioGroup) findViewById(R.id.radioGroup);
        btnCancelarV = (Button) findViewById(R.id.btnCancelarVisita);



            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        Date date = new Date();
        fechaEdit.setText(dateFormat.format(date));

        btnfechaCalendar.setOnClickListener(this);
        cargar_datos_ruta_form();
        cargar_combos();

        btnConfirmarVisita.setOnClickListener(this);
        btnCancelarV.setOnClickListener(this);
       // radioGroup_value.setOnClickListener(this);
        radioGroup_value.setOnCheckedChangeListener(this);
        locationStart();
        obtenerCoordenadas();
        cargar_combos_motivo_si_no(15);
        valorVisita = 1;

    }



    private void obtenerCoordenadas() {



        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                             latitud = (String.valueOf(location.getLatitude()));
                            longitud = (String.valueOf(location.getLongitude()));

                        }
                    }
                });
    }


      private void locationStart(){
         // Acquire a reference to the system Location Manager
                LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
         // Define a listener that responds to location updates
         LocationListener locationListener = new LocationListener() {
         public void onLocationChanged(Location location) {
             if (location != null) {
                 latitud = (String.valueOf(location.getLatitude()));
                 longitud = (String.valueOf(location.getLongitude()));

             }
         }
         public void onStatusChanged(String provider, int status, Bundle extras) {}
         public void onProviderEnabled(String provider) {}
             public void onProviderDisabled(String provider) {
                 Intent i = getIntent();
                 i.putExtra("RESULTADO",2);
                 setResult(RESULT_CANCELED,i);
                 finish();
          }
          };
         int permissionCheck = ContextCompat.checkSelfPermission(this,
        Manifest.permission.ACCESS_FINE_LOCATION);
         // Register the listener with the Location Manager to receive location updates
         locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
  }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        radiobutton_value = (RadioButton) findViewById(checkedId);

        rdButtonValor = radiobutton_value.getText().toString();

        if (rdButtonValor.equals("Si")){
            cargar_combos_motivo_si_no(15);
            valorVisita = 1;
        }else {

            valorVisita = 2;
            cargar_combos_motivo_si_no(16);
        }
       // RadioButton rb=(RadioButton)findViewById(checkedId);
       // textViewChoice.setText("You Selected " + rb.getText());
    }

   /*radioGroup_value.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
        // checkedId is the RadioButton selected
        RadioButton rb=(RadioButton)findViewById(checkedId);
        textViewChoice.setText("You Selected " + rb.getText());
        //Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
    }
    });*/


    @Override
    public  void  onClick(View v){

        if(v==btnfechaCalendar){
              calendario  = Calendar.getInstance();
              dia = calendario.get(Calendar.DAY_OF_MONTH);
              mes = calendario.get(Calendar.MONTH) +1 ;
              anio = calendario.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    if(dayOfMonth < 10  ){

                        dias = "0"+dayOfMonth;

                    }else{
                        dias = String.valueOf(dayOfMonth);
                    }

                    if(month < 10  ){
                         mess = "0"+month;
                    }else{
                        mess =  String.valueOf(month);
                    }

                    fechaEdit.setText(dias+"/"+mess+"/"+year);



                }
            }
            ,anio,mes,dia);
            datePickerDialog.show();
        }
        if(v==btnConfirmarVisita){

            textFecha = fechaEdit.getText().toString();
            pocision = spSelectCliente.getSelectedItemPosition();
            cod_cliente = listaCodigoCliente.get(pocision);
            text_motivo = String.valueOf(spMotivoConcepto.getSelectedItem());
            //text_motivo = editMotivo_text.getText().toString();
          /*  rgButtonValor = radioGroup_value.getCheckedRadioButtonId();
            radiobutton_value = (RadioButton) findViewById(rgButtonValor);
            rdButtonValor = radiobutton_value.getText().toString();*/

             if(textFecha.isEmpty()){
                 MetodosGlobales.mostrarMensaje(this, "Ingrese una fecha");
             }else {


                 //registro datos

                 Integer conexion = MetodosGlobales.verificarConexionInternet(getApplicationContext());
                 if(conexion != 0){
                     registrarVisita(icodruta,cod_cliente,textFecha,"",valorVisita,text_motivo,"evasquez","192.168.1.1","WW:AA:24:RR",latitud,longitud,estado,1);
                     registroBitacoraPost(icodruta,cod_cliente,textFecha,valorVisita,text_motivo,"","","",latitud,longitud,estado);
                 }else{
                   MetodosGlobales.mostrarMensaje(registrar_visita.this, "se registro correctamente");

                     registrarVisita(icodruta,cod_cliente,textFecha,"",valorVisita,text_motivo,"evasquez","192.168.1.1","WW:AA:24:RR",latitud,longitud,estado,0);

                 }

                 Intent i = getIntent();
                 i.putExtra("RESULTADO",1);
                 setResult(RESULT_OK,i);
                 finish();

                 //
             }


        }

        if(v==btnCancelarV){
            Intent i = getIntent();
            i.putExtra("RESULTADO",0);
            setResult(RESULT_OK,i);
            finish();
        }

    }
    public void setFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_principal, fragment);
        ft.commit();
    }



    public void cargar_datos_ruta_form(){
        RutaVM = new ViewModelProvider(this).get(VendedorViewModel.class);
        RutaVM.obtenerDescRuta(ntraUsuario).observe(this, ruta -> {
            spDescRutas.setText(ruta);
        });
        RutaVM = new ViewModelProvider(this).get(VendedorViewModel.class);
        RutaVM.obtenerCodRuta(ntraUsuario).observe(this, codruta -> {
            icodruta =  codruta;
        });

    }

    public void cargar_combos() {
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        //final CategoriaListAdapter adapter = new CategoriaListAdapter(this);
        //Clientes
        clienteVM.obtenerCliente().observe(this, sclientes ->{
            listaClientes = sclientes;
            listaCodigoCliente = new ArrayList<String>();
            listaNombreCliente = new ArrayList<String >();

            for(ClienteSpinnerEntity c: sclientes){


                if(!c.getNombreCompleto().replace(" ","").isEmpty()){
                    listaNombreCliente.add(c.getNombreCompleto());
                }
                if(!c.getRazonSocial().replace(" ","").isEmpty()){
                    listaNombreCliente.add(c.getRazonSocial());
                }
                if(!c.getNumeroDocumento().replace(" ","").isEmpty()) {
                    listaCodigoCliente.add(c.getNumeroDocumento());
                }
                if(!c.getRuc().replace(" ","").isEmpty()) {
                    listaCodigoCliente.add(c.getRuc());
                }


            }

            spSelectCliente.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaNombreCliente));
           // spSelectCliente.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaRazonSocial));
        });
    }

    public void cargar_combos_motivo_si_no(Integer codigo) {
        conceptoVM = new ViewModelProvider(this).get(ConceptoViewModel.class);
        //final CategoriaListAdapter adapter = new CategoriaListAdapter(this);
        //Clientes
        conceptoVM.obtenerConceptos(codigo).observe(this, conceptos ->{
            listaConceptos = conceptos;


            spMotivoConcepto.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaConceptos));
            // spSelectCliente.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaRazonSocial));

        });
    }


    public void registrarVisita(int codRuta,String codCliente , String fecha, String horaFinal, int visita, String motivo, String usuario, String ip, String mac,
                                String cordenadaX, String cordenadaY,int estado, int internet ){

        bitacoraVendedorVM = new ViewModelProvider(this).get(BitacoraVendedorViewModel.class);

        if(usuario.isEmpty()){

            usuario =  Constante.G_CONST_VACIO;
        }
        if(ip.isEmpty()){

            ip = Constante.G_CONST_VACIO;
        }
        if(mac.isEmpty()){

            mac = Constante.G_CONST_VACIO;
        }

        BitacoraVendedorEntity bve = new BitacoraVendedorEntity(

                codRuta,
                codCliente,
                fecha,
                horaFinal,
                visita,
                motivo,
                usuario,
                ip,
                mac,
                cordenadaX,
                cordenadaY,
                estado,
                internet
        );

        bitacoraVendedorVM.insert(bve);

    }

    public void registroBitacoraPost(int codRuta, String codCliente, String fecha, int visita, String motivo, String usuario, String ip, String mac,
                                     String cordenadaX, String cordenadaY, int estado){

        if(usuario.isEmpty()){

            usuario =  Constante.G_CONST_VACIO;
        }
        if(ip.isEmpty()){

            ip = Constante.G_CONST_VACIO;
        }
        if(mac.isEmpty()){

            mac = Constante.G_CONST_VACIO;
        }
        ResquestRutasBitacora bitRu = new ResquestRutasBitacora(
                codRuta,
                codCliente,
                fecha,
                visita,
                motivo,
                "evasquez",
                "15.15.15.16",
                "AB:CD:EF",
                cordenadaX,
                cordenadaY,
                estado
        );

        registrar_bitacora_ruta_post(bitRu);

    }


    public void registrar_bitacora_ruta_post(ResquestRutasBitacora bitRu){
        //Integer codCliente = Constante.g_const_0;
        Call<ResponseRegistroBitacoras> call = ApiAdapter.getApiService().registrarRutasBitacoras(bitRu);
        call.enqueue(new Callback<ResponseRegistroBitacoras>() {
            @Override
            public void onResponse(Call<ResponseRegistroBitacoras> call, Response<ResponseRegistroBitacoras> response) {
                if(response.isSuccessful()){
                    ResponseRegistroBitacoras rb = response.body();
                    if(rb.getErrorWebSer().getCodigoErr() == 2000) {
                        MetodosGlobales.mostrarMensaje(registrar_visita.this, "Se registro correctamente");
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
    }


}
