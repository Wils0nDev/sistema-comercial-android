/*package com.idesolution.distribuidoravdc.Fragments;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.Ruta;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ConceptoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.RutaViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.IO.ApiAdapter;
import com.idesolution.distribuidoravdc.IO.Request.ResquestCliente;
import com.idesolution.distribuidoravdc.IO.Response.ResponseRegistroCliente;
import com.idesolution.distribuidoravdc.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
/*public class f_registro_cliente extends Fragment {

    private Bundle parametrosEntrada;
    private ClienteViewModel clienteVM;
    private ConceptoViewModel conceptoVM;
    private RutaViewModel rutaVM;
    private List<String> listaConceptos;
    private ArrayList<String> listaDescripcionRutas;
    private ArrayList<Integer> listaCodigoRutas;
    private List<Ruta> listaRutas;
    private Integer codigoPersona;
    Integer cont;
    Integer flag;
    Integer proceso;
    Integer transaccion;

    Spinner spinnerTipoPersona;
    Spinner spinnerTipoDocumento;
    Spinner spinnerListaPrecios;
    Spinner spinnerListaFrecuencia;
    EditText numDocumento;
    EditText nombre;
    EditText apePaterno;
    EditText apeMaterno;
    EditText direccion;
    EditText correo;
    EditText telefono;
    EditText celular;
    private Toolbar toolbar;

    public static f_registro_cliente newInstance(Bundle arguments){
        f_registro_cliente f = new f_registro_cliente();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    public f_registro_cliente() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        parametrosEntrada = this.getArguments();
        proceso = Constante.g_const_0;
        transaccion = Constante.g_const_0;
        if(parametrosEntrada != null){
            proceso = parametrosEntrada.getInt("parametro_proceso");
            transaccion = parametrosEntrada.getInt("parametro_transaccion");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_registro_cliente, container, false);
        if(v != null){
            spinnerTipoPersona = (Spinner) v.findViewById(R.id.spTipoPersona_cli);
            spinnerTipoDocumento = (Spinner) v.findViewById(R.id.spTipoDocumento_cli);
            spinnerListaPrecios = (Spinner) v.findViewById(R.id.spListaPrecios_cli);
            spinnerListaFrecuencia = (Spinner) v.findViewById(R.id.spFrecuencia_cli);
            numDocumento = (EditText) v.findViewById(R.id.etNumeroDocumento_cli);
            nombre = (EditText) v.findViewById(R.id.etNombres_cli);
            apePaterno = (EditText) v.findViewById(R.id.etApellidoPaterno_cli);
            apeMaterno = (EditText) v.findViewById(R.id.etApellidoMaterno_cli);
            direccion = (EditText) v.findViewById(R.id.etDireccion_cli);
            correo = (EditText) v.findViewById(R.id.etCorreo_cli);
            telefono = (EditText) v.findViewById(R.id.etTelefono_cli);
            celular = (EditText) v.findViewById(R.id.etCelular_cli);

            cargar_combos();
            codigoPersona = Constante.g_const_0;
            if(proceso == Constante.g_const_2){
                getActivity().setTitle(R.string.titulo_editar_cliente);
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
                getActivity().setTitle(R.string.titulo_nuevo_cliente);
            }

            cont = 0;
            flag = 0;
            numDocumento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus) {
                        if(proceso == Constante.g_const_1)
                            buscarNumeroDocumento();
                    }
                }
            });

        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*codigoPersona = Constante.g_const_0;
        if(proceso == Constante.g_const_2){
            //getActivity().setTitle(R.string.titulo_editar_cliente);
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
            //getActivity().setTitle(R.string.titulo_nuevo_cliente);
        }*/
   /* }

    public void cargar_combos() {
        conceptoVM = new ViewModelProvider(this).get(ConceptoViewModel.class);
        rutaVM = new ViewModelProvider(this).get(RutaViewModel.class);

        //Tipo Persona
        conceptoVM.obtenerConceptos(1).observe(this, conceptos ->{
            listaConceptos = conceptos;
            spinnerTipoPersona.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaConceptos));
        });

        //Tipos Documento
        conceptoVM.obtenerConceptos(2).observe(this, conceptos ->{
            listaConceptos = conceptos;
            spinnerTipoDocumento.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaConceptos));
        });

        //Frecuencia
        conceptoVM.obtenerConceptos(5).observe(this, conceptos ->{
            listaConceptos = conceptos;
            spinnerListaFrecuencia.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaConceptos));
        });

        //Precios
        conceptoVM.obtenerConceptos(7).observe(this, conceptos ->{
            listaConceptos = conceptos;
            spinnerListaPrecios.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaConceptos));
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
  /*  }

    //MENU SUPERIOR
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_registro_cliente, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Integer conexion = MetodosGlobales.verificarConexionInternet(getContext());
        switch (item.getItemId()) {
            case R.id.mnRegistrarCliente:
                if (validarDatos()){
                    flag = 1;
                    if (conexion == 0)
                        registrarCliente(Constante.g_const_0, Constante.g_const_0);
                    else
                        registroClientePost();
                }
        }
        return true;
    }

    //VALIDACIONES
    public void buscarNumeroDocumento(){
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);

        clienteVM.buscarNumeroDocumento(numDocumento.getText().toString()).observe(this, contador ->{
            cont = contador;
            if(cont != 0 && flag == 0){
                MetodosGlobales.mostrarMensaje(getContext(), "Numero de documento ya existe");
                numDocumento.requestFocus();
            }
        });
    }

    public boolean validarSoloLetras(String cadena){
        char letra;
        for (int i = 0; i < cadena.length() -1 ; i++ ){
            letra = cadena.charAt(i);
            if(!(Character.isLetter(letra) || Character.isSpaceChar(letra)))
                return false;
        }
        return true;
    }

    public boolean validarDatos(){
        //Numero documento
        if(!MetodosGlobales.validarNulos(numDocumento.getText().toString())){
            MetodosGlobales.mostrarMensaje(getContext(), "Debe igresar el número de documento");
            numDocumento.requestFocus();
            return false;
        }else
        {
            if((spinnerTipoDocumento.getSelectedItemPosition() + 1) == 1 || (spinnerTipoDocumento.getSelectedItemPosition() + 1) == 2){
                if((spinnerTipoDocumento.getSelectedItemPosition() + 1) == 1){
                    if(numDocumento.getText().toString().length() != 8){
                        MetodosGlobales.mostrarMensaje(getContext(), "Número de documento debe tener 8 digitos");
                        numDocumento.requestFocus();
                        return false;
                    }
                }
                else{
                    if((spinnerTipoDocumento.getSelectedItemPosition() + 1) == 2){
                        if(numDocumento.getText().toString().length() != 12){
                            MetodosGlobales.mostrarMensaje(getContext(), "Número de documento debe tener 12 digitos");
                            numDocumento.requestFocus();
                            return false;
                        }
                    }
                }
            }else
            {
                if((spinnerTipoDocumento.getSelectedItemPosition() + 1) == 3){
                    if(numDocumento.getText().toString().length() != 11){
                        MetodosGlobales.mostrarMensaje(getContext(), "Ruc debe tener 11 digitos");
                        numDocumento.requestFocus();
                        return false;
                    }
                }
            }
        }

        //Nombres / Razon Social
        if(!MetodosGlobales.validarNulos(nombre.getText().toString())){
            MetodosGlobales.mostrarMensaje(getContext(), "Debe igresar el Nombre / Razon Social");
            nombre.requestFocus();
            return false;
        }else{
            if(spinnerTipoDocumento.getSelectedItemPosition() + 1 != 3){
                if(!validarSoloLetras(nombre.getText().toString())){
                    MetodosGlobales.mostrarMensaje(getContext(), "Nombre solo debe contener letras");
                    nombre.requestFocus();
                    return false;
                }
            }
        }

        //Apellidos
        if(MetodosGlobales.validarNulos(apePaterno.getText().toString())){
            if(!validarSoloLetras(apePaterno.getText().toString())){
                MetodosGlobales.mostrarMensaje(getContext(), "Apellido solo debe contener letras");
                apePaterno.requestFocus();
                return false;
            }
        }

        if(MetodosGlobales.validarNulos(apeMaterno.getText().toString())){
            if(!validarSoloLetras(apeMaterno.getText().toString())){
                MetodosGlobales.mostrarMensaje(getContext(), "Apellido solo debe contener letras");
                apeMaterno.requestFocus();
                return false;
            }
        }

        //Direccion
        if  (!MetodosGlobales.validarNulos(direccion.getText().toString())){
            MetodosGlobales.mostrarMensaje(getContext(), "Debe igresar la dirección");
            direccion.requestFocus();
            return false;
        }

        return true;
    }

    //LOGICA REGISTRO
    public void registrarCliente(Integer codigo, Integer flagSincronizacion){
        flag = 1;
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);

        short tPersona = Short.parseShort(String.valueOf(spinnerTipoPersona.getSelectedItemPosition() + 1));
        short tDocumento = Short.parseShort(String.valueOf(spinnerTipoDocumento.getSelectedItemPosition() + 1));
        String nDocumento = Constante.G_CONST_VACIO;
        String ruc = Constante.G_CONST_VACIO;
        String nom = Constante.G_CONST_VACIO;
        String razonSocial = Constante.G_CONST_VACIO;
        String apePat = Constante.G_CONST_VACIO;
        String apeMat = Constante.G_CONST_VACIO;
        short frec = Short.parseShort(String.valueOf(spinnerListaFrecuencia.getSelectedItemPosition() + 1));
        short tPrecio = Short.parseShort(String.valueOf(spinnerListaPrecios.getSelectedItemPosition() + 1));
        String busqueda = null;

        if(tDocumento == 3) {
            ruc = numDocumento.getText().toString();
            razonSocial = nombre.getText().toString();
        }
        else {
            nDocumento = numDocumento.getText().toString();
            nom = nombre.getText().toString();
            apePat = apePaterno.getText().toString();
            apeMat = apeMaterno.getText().toString();
        }

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

        busqueda = nom + " " + apePat + " " + apeMat + " " + nDocumento + " " + razonSocial + " " + ruc;

        Cliente nc = new Cliente(
                codigo,
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
                frec,
                tPrecio,
                Short.parseShort(String.valueOf(1)), //ruta
                busqueda,
                flagSincronizacion,null,null
        );
        if(proceso == Constante.g_const_1){
             clienteVM.insert(nc, getContext());
            MetodosGlobales.mostrarMensaje(getContext(), "Cliente registrado");
        }else
        {
            if(proceso == Constante.g_const_2){
                clienteVM.update(transaccion, nc.getRazonSocial(), nc.getNombres(), nc.getApellidoPaterno(), nc.getApellidoMaterno(), nc.getDireccion(), nc.getCorreo(), nc.getTelefono(), nc.getCelular(), nc.getFlag());
                MetodosGlobales.mostrarMensaje(getContext(), "Cliente modificado");
            }
        }
        //onDestroy();
        //finish();
    }

    public void registroClientePost(){
        flag = 1;

        short tPersona = Short.parseShort(String.valueOf(spinnerTipoPersona.getSelectedItemPosition() + 1));
        short tDocumento = Short.parseShort(String.valueOf(spinnerTipoDocumento.getSelectedItemPosition() + 1));
        String nDocumento = Constante.G_CONST_VACIO;
        String ruc = Constante.G_CONST_VACIO;
        String nom = Constante.G_CONST_VACIO;
        String razonSocial = Constante.G_CONST_VACIO;
        String apePat = Constante.G_CONST_VACIO;
        String apeMat = Constante.G_CONST_VACIO;
        short frec = Short.parseShort(String.valueOf(spinnerListaFrecuencia.getSelectedItemPosition() + 1));
        short tPrecio = Short.parseShort(String.valueOf(spinnerListaPrecios.getSelectedItemPosition() + 1));

        if(tDocumento == 3) {
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
                    if(c.getErrorWebSer().getCodigoErr() == 2000) {
                        registrarCliente(c.getResultado().getRegMod().getCodCliente(), Constante.g_const_1);
                    }
                    else{
                        if(c.getErrorWebSer().getTipoErr() == 0){
                            MetodosGlobales.mostrarMensaje(getContext(), c.getErrorWebSer().getDescripcionErr());
                        }
                        else{
                            MetodosGlobales.mostrarMensaje(getContext(), "Error en el registro del cliente");
                        }
                    }

                }
                else{
                    MetodosGlobales.mostrarMensaje(getContext(), "Hubo un error en el registro del cliente");
                }
            }

            @Override
            public void onFailure(Call<ResponseRegistroCliente> call, Throwable t) {
                MetodosGlobales.mostrarMensaje(getContext(), "Error de conexion");
            }
        });
    }


}*/
