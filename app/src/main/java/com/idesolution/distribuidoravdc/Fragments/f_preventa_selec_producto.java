package com.idesolution.distribuidoravdc.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.idesolution.distribuidoravdc.Adaptador.SpinnerConceptoAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.DescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Detalle;
import com.idesolution.distribuidoravdc.BD.Entity.FilaPresentacion;
import com.idesolution.distribuidoravdc.BD.Entity.FilaProducto;
import com.idesolution.distribuidoravdc.BD.Entity.filaPromocionDos;
import com.idesolution.distribuidoravdc.BD.Repository.CabeceraViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ConceptoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.DescuentoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PresentacionViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ProductoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PromocionViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_preventa_selec_producto extends Fragment {

    private Bundle parametrosEntrada;
    private String codProducto;
    private ProductoViewModel productoVM;
    private ClienteViewModel clienteVM;
    //private PreventaViewModel preventaVM;
    private CabeceraViewModel cabeceraVM;
    private PresentacionViewModel presentacionVM;
    private ConceptoViewModel conceptoVM;
    private PromocionViewModel promocionVM;
    private DescuentoViewModel descuentoVM;
    private String Nombre;
    private String ApellidoP;
    private String ApellidoM;
    private Integer tipo;
    private TextView nombreCliente;
    private TextView nombreProducto;
    private TextView stock;
    private TextView precio;
    private TextView unidadBase;
    private EditText cantidad;
    private Spinner unidad;
    private Spinner promocion;
    private Spinner descuento;
    private Button btnAgregarCarrito;
    private TextView promociones;
    private TextView descuentos;
    //private PreventaDetalleEntity detallePreventa;
    private Detalle detalle;
    private FilaProducto productoSeleccionado;
    private Integer codPreventa;
    private short item;
    private int codPresentacion;
    private int cantidadUnidadBase;
    private double precioVenta;
    private ArrayList<String> listaDescripcionPresentaciones;
    private ArrayList<Integer> listaCodigoPresentaciones;
    private ArrayList<Integer> listaCantidadPresentaciones;
    private ArrayList<String> listaPromocionesCombo = null;
    private ArrayList<Integer> listaNtraPromocionesCombo = null;
    private ArrayList<String> listaDescuentosCombo;
    private ArrayList<Integer> listaNtraDescuentosCombo;
    private int ntraPromocion;
    private int ntraDescuento;
    private double importeProducto;
    private double importeProductoDos;
    private int cont;

    SpinnerAdapter adapterUnidad;
    SpinnerAdapter adapterPromociones;
    SpinnerAdapter adapterDescuentos;
    List<String> listVacia = new ArrayList<>();
    private Button btnRestar;
    private Button btnSumar;

    List<filaPromocionDos> a;


    /*public static f_preventa_selec_producto newInstance(Bundle arguments){
        f_preventa_selec_producto f = new f_preventa_selec_producto();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }*/

    public f_preventa_selec_producto() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        parametrosEntrada = this.getArguments();
        codProducto = Constante.G_CONST_VACIO;
        if(parametrosEntrada != null){
            codProducto = parametrosEntrada.getString("par_cod_producto");
            tipo = parametrosEntrada.getInt("par_tipo");
        }
        item = Constante.g_s_const_1;
        codPreventa = Constante.g_const_0;
        listaPromocionesCombo = new ArrayList<String>();
        listaNtraPromocionesCombo = new ArrayList<Integer>();
        listaDescuentosCombo = new ArrayList<String>();
        listaNtraDescuentosCombo = new ArrayList<Integer>();
        ntraPromocion = Constante.g_const_0;
        ntraDescuento = Constante.g_const_0;
        cont = Constante.g_const_0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_preventa_selec_producto, container, false);
        if (v != null){
            nombreCliente = (TextView) v.findViewById(R.id.tvNombreCliente_selec_pro_pre);
            nombreProducto = (TextView) v.findViewById(R.id.tvNombrePro_sel_pro_pre);
            stock = (TextView) v.findViewById(R.id.tvStock_sel_pro_pre);
            precio = (TextView) v.findViewById(R.id.tvPrecio_sel_pro_pre);
            cantidad = (EditText) v.findViewById(R.id.etCantidad_sel_pro_pre);
            unidad = (Spinner) v.findViewById(R.id.spUnidad_sel_pro_pre);
            promocion = (Spinner) v.findViewById(R.id.spPromocion_sel_pro_pre);
            descuento = (Spinner) v.findViewById(R.id.spDescuento_sel_pro_pre);
            btnAgregarCarrito = (Button) v.findViewById(R.id.btnAgregarCarrito);
            promociones = (TextView) v.findViewById(R.id.tvListaPromociones_sel_pro_pre);
            descuentos = (TextView) v.findViewById(R.id.tvListaDescuentos_sel_pro_pre);

            btnRestar = (Button) v.findViewById(R.id.btn_restar);
            btnSumar = (Button) v.findViewById(R.id.btn_sumar);

        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productoVM = new ViewModelProvider(this).get(ProductoViewModel.class);
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        conceptoVM = new ViewModelProvider(this).get(ConceptoViewModel.class);
        promocionVM = new ViewModelProvider(this).get(PromocionViewModel.class);
        descuentoVM = new ViewModelProvider(this).get(DescuentoViewModel.class);
        Nombre = null;
        ApellidoP = null;
        ApellidoM = null;

        cargar_combos();
        //if(tipo == Constante.g_const_1) {
            getActivity().setTitle(R.string.titulo_seleccion_producto);
        /*}
        else {
            getActivity().setTitle("Editar producto carrito");
            btnAgregarCarrito.setText("APLICAR CAMBIOS");
            cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
            cabeceraVM.obtenerDetalle(codProducto).observe(this, detalle -> {
                cantidad.setText(String.valueOf(detalle.getCantidadPresentacion()));

                int a = Constante.g_const_0;
                if(listaCodigoPresentaciones != null){
                    for(Integer i: listaCodigoPresentaciones){
                        if(i == detalle.getCodPresentacion()){
                            unidad.setSelection(a);
                            break;
                        }
                        a = a + Constante.g_s_const_1;
                    }
                }
            });

        }*/
        promocionVM.deleteAllFilaPromocion();
        //descuentoVM.deleteAllFilaDescuento();

        //clienteVM.buscarCliente(ntraCliente).observe(this, cliente ->{
        clienteVM.buscarClientePreventa().observe(this, cliente ->{
            if(cliente.getTipoPersona() == Constante.g_const_2){
                Nombre = cliente.getRazonSocial();
            }
            else{
                Nombre = cliente.getNombres();
                ApellidoP = cliente.getApellidoPaterno();
                ApellidoM = cliente.getApellidoMaterno();
            }
            if(Nombre == null)
                Nombre = Constante.G_CONST_VACIO;
            if(ApellidoP == null)
                ApellidoP = Constante.G_CONST_VACIO;
            if(ApellidoM == null)
                ApellidoM = Constante.G_CONST_VACIO;

            nombreCliente.setText(Nombre + Constante.g_const_espacio + ApellidoP + Constante.g_const_espacio + ApellidoM);
            productoVM.buscarProductoSeleccion(cliente.getTipoListaPrecio(), codProducto).observe(this, producto -> {

                stock.setText(String.valueOf(producto.getStock()));
                precio.setText(String.valueOf(producto.getPrecio()));
                productoSeleccionado = producto;

                conceptoVM.obtenerConcepto(Constante.g_const_12, Short.parseShort(String.valueOf(producto.codUnidadBaseVenta)) ).observe(this, descUnidad ->{
                    //unidadBase.setText("(" + descUnidad.trim() + ")");
                    nombreProducto.setText(producto.getDescripcion().trim() + "(" + descUnidad.trim() + ")");
                });

                //
                int cantidadProducto =  Integer.parseInt(cantidad.getText().toString()) * listaCantidadPresentaciones.get(unidad.getSelectedItemPosition());
                double importe = productoSeleccionado.getPrecio() * cantidadProducto;
                promocionVM.obtenerPromociones(codProducto, cantidadProducto, importe);
                descuentoVM.obtenerDescuentos(codProducto, Constante.g_const_1);
                //


            });



        });

        promocionVM.promocionesHabilitadas().observe(this, promos -> {
            String a = "";
            for (filaPromocionDos f : promos){
                a = a + "-" + f.getDescripcion() + "\n";
            }
            if(a.length() == 0){
                a = "No hay promociones disponibles";
            }
            promociones.setText(a);
        });

        descuentoVM.descuentosHabilitados().observe(this, dsctos -> {
            String a = "";
            for (DescuentoEntity f : dsctos){
                a = a + "-" + f.getDescripcion() + "\n";
            }
            if(a.length() == 0){
                a = "No hay descuentos disponibles";
            }
            descuentos.setText(a);
        });

        cantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0 ) {
                    //MetodosGlobales.mostrarMensaje(getContext(), "ENTRO 1");
                    //if(tipo == Constante.g_const_1){
                        obtenerPromociones(Integer.parseInt(s.toString()));
                        obtenerDescuentos(Integer.parseInt(s.toString()));
                    /*}else{
                        if(cont != Constante.g_const_0){
                            obtenerPromociones(Integer.parseInt(s.toString()));
                            obtenerDescuentos(Integer.parseInt(s.toString()));
                        }
                    }
                    cont = Constante.g_const_1;*/
                }
                else{
                    listaNtraPromocionesCombo = new ArrayList<Integer>();
                    listaPromocionesCombo = new ArrayList<String>();
                    adapterPromociones = new SpinnerConceptoAdapter(getContext(), listVacia);
                    promocion.setAdapter(adapterPromociones);
                    //promocion.setAdapter(null);

                    listaNtraDescuentosCombo = new ArrayList<Integer>();
                    listaDescuentosCombo = new ArrayList<String>();
                    adapterDescuentos = new SpinnerConceptoAdapter(getContext(), listVacia);
                    descuento.setAdapter(adapterDescuentos);
                    //descuento.setAdapter(null);
                }
            }
        });

        unidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (cantidad.getText().toString().length() > 0 ) {
                    //MetodosGlobales.mostrarMensaje(getContext(), "ENTRO 2");
                    obtenerPromociones(Integer.parseInt(cantidad.getText().toString()));
                    obtenerDescuentos(Integer.parseInt(cantidad.getText().toString()));
                }
                else{
                    listaNtraPromocionesCombo = new ArrayList<Integer>();
                    listaPromocionesCombo = new ArrayList<String>();
                    adapterPromociones = new SpinnerConceptoAdapter(getContext(), listVacia);
                    promocion.setAdapter(adapterPromociones);
                    //promocion.setAdapter(null);

                    listaNtraDescuentosCombo = new ArrayList<Integer>();
                    listaDescuentosCombo = new ArrayList<String>();
                    adapterDescuentos = new SpinnerConceptoAdapter(getContext(), listVacia);
                    descuento.setAdapter(adapterDescuentos);
                    //descuento.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAgregarCarrito.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(validaciones())
                    guardarProducto();
                InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(btnAgregarCarrito.getWindowToken(), 0);
            }

        });

        //RESTAR CANTIDAD
        btnRestar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cantidad.getText().toString().length() > Constante.g_const_0){
                    int cantidad_stock = Integer.parseInt(cantidad.getText().toString());
                    if(cantidad_stock > Constante.g_const_1){
                        cantidad.setText("" + (cantidad_stock -1));
                    }
                }else{
                    Toasty.error(getContext(),"Ingrese la cantidad" , Toast.LENGTH_SHORT, true).show();

                }


            }

        });
        //SUMAR CANTIDAD
        btnSumar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(cantidad.getText().toString().length() > Constante.g_const_0){
                    if(stock.getText().toString().length() > Constante.g_const_0){
                        String a = stock.getText().toString();
                        int stock = Integer.parseInt(a);
                        int cantidad_stock = Integer.parseInt(cantidad.getText().toString());
                        if(cantidad_stock < stock)
                            cantidad.setText("" + (cantidad_stock +1));
                    }else
                    {
                        Toasty.error(getContext(),"No se cargÃ³ el stock del producto" , Toast.LENGTH_SHORT, true).show();
                    }
                }else{
                    Toasty.error(getContext(),"Ingrese la cantidad" , Toast.LENGTH_SHORT, true).show();

                }
            }

        });
    }

    public void cargar_combos(){

        productoVM = new ViewModelProvider(this).get(ProductoViewModel.class);
        presentacionVM = new ViewModelProvider(this).get(PresentacionViewModel.class);
        //productoVM.obtenerPresentaciones(codProducto).observe(this, conceptos ->{
        productoVM.obtenerPresentacionesCombo(codProducto).observe(this, conceptos ->{
            listaCodigoPresentaciones = new ArrayList<Integer>();
            listaCantidadPresentaciones = new ArrayList<Integer>();
            listaDescripcionPresentaciones = new ArrayList<String>();
            for(FilaPresentacion c: conceptos){
              listaCodigoPresentaciones.add(c.getCorrelativo());
              listaDescripcionPresentaciones.add(c.getDescripcion());
              listaCantidadPresentaciones.add(c.getCantidadUnidadBase());

                /*presentacionVM.obtenerCantidadEnUnidadesBase(codProducto, c.getCorrelativo()).observe(this, cantidadUB -> {
                    listaCantidadPresentaciones.add();
                });*/
            }
            adapterUnidad = new SpinnerConceptoAdapter(getContext(), listaDescripcionPresentaciones);
            unidad.setAdapter(adapterUnidad);

            //unidad.setAdapter(new ArrayAdapter<String>( getContext(), android.R.layout.simple_spinner_item, listaDescripcionPresentaciones));
            /*if(tipo == Constante.g_const_2){
                cabeceraVM.obtenerDetalle(codProducto).observe(this, detalle -> {
                    int a = Constante.g_const_0;
                    if(listaCodigoPresentaciones != null){
                        for(Integer i: listaCodigoPresentaciones){
                            if(i == detalle.getCodPresentacion()){
                                unidad.setSelection(a);
                                break;
                            }
                            a = a + Constante.g_s_const_1;
                        }
                    }
                });
            }*/
        });
    }

    public boolean validaciones(){
        //obtenerPromociones();
        //cantidad
        if(!MetodosGlobales.validarNulos(cantidad.getText().toString())){
            MetodosGlobales.mostrarMensaje(getContext(), "Debe ingresar la cantidad");
            cantidad.requestFocus();
            return false;
        }else{
            //if(Integer.parseInt(cantidad.getText().toString()) <= Constante.g_const_0 || Integer.parseInt(cantidad.getText().toString()) > productoSeleccionado.getStock()){
            if((Integer.parseInt(cantidad.getText().toString().trim()) * listaCantidadPresentaciones.get(unidad.getSelectedItemPosition())) <= Constante.g_const_0 || (Integer.parseInt(cantidad.getText().toString().trim()) * listaCantidadPresentaciones.get(unidad.getSelectedItemPosition())) > productoSeleccionado.getStock()){
                MetodosGlobales.mostrarMensaje(getContext(), "Cantidad ingresada no válida");
                cantidad.requestFocus();
                return false;
            }
        }
        return true;
    }

    public void guardarProducto(){
        precioVenta = productoSeleccionado.getPrecio();
        //MetodosGlobales.mostrarMensaje(getContext(), String.valueOf(precioVenta));
        //obtener codigo de preventa
        //preventaVM = new ViewModelProvider(this).get(PreventaViewModel.class);
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        presentacionVM = new ViewModelProvider(this).get(PresentacionViewModel.class);

        cabeceraVM.obtenerNtraPreventa().observe(this, codigoPre -> {
            codPreventa = codigoPre;
            cabeceraVM.identificarItem().observe(this, itemProducto -> {
                //item
                item = itemProducto;
                item = (short) (item + Constante.g_s_const_1);
                //MetodosGlobales.mostrarMensaje(getContext(), codPreventa + " " + item);

                //codigo de presentacion
                codPresentacion = listaCodigoPresentaciones.get(unidad.getSelectedItemPosition());

                //cantidad en unidades base
                presentacionVM.obtenerCantidadEnUnidadesBase(codProducto, codPresentacion).observe(this, cantidadUB -> {
                    cantidadUnidadBase = cantidadUB * Integer.parseInt(cantidad.getText().toString());
                    //MetodosGlobales.mostrarMensaje(getContext(), cantidadUnidadBase + "");

                    //detalle preventa
                    /*detallePreventa = new PreventaDetalleEntity(
                            codPreventa,
                            item,
                            codPresentacion,
                            productoSeleccionado.getCodProducto(),
                            productoSeleccionado.getCodAlmacen(),
                            Integer.parseInt(cantidad.getText().toString()),
                            cantidadUnidadBase,
                            precioVenta,
                            Constante.g_s_const_1,
                            Constante.g_s_const_m1
                    );*/
                    detalle = new Detalle(
                            codPreventa,
                            item,
                            codPresentacion,
                            productoSeleccionado.getCodProducto(),
                            productoSeleccionado.getCodAlmacen(),
                            Integer.parseInt(cantidad.getText().toString()),
                            cantidadUnidadBase,
                            precioVenta,
                            Constante.g_s_const_1
                    );

                    if(listaPromocionesCombo.size() > Constante.g_s_const_0){
                        ntraPromocion = listaNtraPromocionesCombo.get(promocion.getSelectedItemPosition());
                    }else{
                        ntraPromocion = Constante.g_const_0;
                    }
                    if(listaDescuentosCombo.size() > Constante.g_s_const_1){
                        ntraDescuento = listaNtraDescuentosCombo.get(descuento.getSelectedItemPosition());
                        if(ntraDescuento == Constante.g_const_m1){
                            ntraDescuento = Constante.g_const_0;
                        }
                    }else{
                        ntraDescuento = Constante.g_const_0;
                    }

                    cabeceraVM.insertDetalle(detalle, ntraPromocion, ntraDescuento);
                    MetodosGlobales.mostrarMensaje(getContext(), "Producto agregado al carrito");
                    getActivity().onBackPressed();
                });
            });
        });
    }

    //#####################################################################################################################

    public void obtenerPromociones(int cantidad){

        promocionVM = new ViewModelProvider(this).get(PromocionViewModel.class);
        listaNtraPromocionesCombo = new ArrayList<Integer>();
        listaPromocionesCombo = new ArrayList<String>();
        adapterPromociones = new SpinnerConceptoAdapter(getContext(), listaPromocionesCombo);
        promocion.setAdapter(adapterPromociones);
        //promocion.setAdapter(new ArrayAdapter<String>( getContext(), android.R.layout.simple_spinner_item, listaPromocionesCombo));

        promocionVM.promocionesHabilitadas().observe(this, promos -> {
            int cantidadProducto =  cantidad * listaCantidadPresentaciones.get(unidad.getSelectedItemPosition());
            importeProducto = Constante.g_const_0;
            if(productoSeleccionado != null)
                importeProducto = productoSeleccionado.getPrecio() * cantidadProducto;
            adapterPromociones = new SpinnerConceptoAdapter(getContext(), listVacia);
            promocion.setAdapter(adapterPromociones);
            //promocion.setAdapter(null);
            listaNtraPromocionesCombo = new ArrayList<Integer>();
            listaPromocionesCombo = new ArrayList<String>();
            adapterPromociones = new SpinnerConceptoAdapter(getContext(), listaPromocionesCombo);
            promocion.setAdapter(adapterPromociones);
            //promocion.setAdapter(new ArrayAdapter<String>( getContext(), android.R.layout.simple_spinner_item, listaPromocionesCombo));
            for (filaPromocionDos f : promos){
                promocionVM.obtener_flag_promocion(f.getNtra(), Constante.g_const_2).observe(this, pro -> {
                    if(Integer.parseInt(pro.getCodProductoFin()) == Constante.g_const_1){ //CANTIDAD
                        if(cantidadProducto >= Integer.parseInt(pro.getCodProductoInicio())){
                            //MetodosGlobales.mostrarMensaje(getContext(), f.getDescripcion());
                            listaPromocionesCombo.add(f.getDescripcion());
                            listaNtraPromocionesCombo.add(f.getNtra());
                        }
                    }
                    else { //SOLES (IMPORTE)
                        if (importeProducto >= Double.parseDouble(pro.getCodProductoInicio())) {
                            //MetodosGlobales.mostrarMensaje(getContext(), f.getDescripcion());
                            listaPromocionesCombo.add(f.getDescripcion());
                            listaNtraPromocionesCombo.add(f.getNtra());
                        }
                    }

                    if(listaPromocionesCombo.size() > Constante.g_s_const_0){
                        adapterPromociones = new SpinnerConceptoAdapter(getContext(), listaPromocionesCombo);
                        promocion.setAdapter(adapterPromociones);
                        //promocion.setAdapter(new ArrayAdapter<String>( getContext(), android.R.layout.simple_spinner_item, listaPromocionesCombo));
                    }

                });
            }

        });
    }

    public void obtenerDescuentos(int cantidad){
        //int cantidadProducto =  cantidad * listaCantidadPresentaciones.get(unidad.getSelectedItemPosition());
        //double importe = productoSeleccionado.getPrecio() * cantidadProducto;
        descuentoVM = new ViewModelProvider(this).get(DescuentoViewModel.class);
        listaNtraDescuentosCombo = new ArrayList<Integer>();
        listaDescuentosCombo = new ArrayList<String>();

        //
        listaNtraDescuentosCombo.add(-1);
        listaDescuentosCombo.add("--SELECCIONAR--");
        //

        descuentoVM.descuentosHabilitados().observe(this, dsctos -> {
            int cantidadProducto =  cantidad * listaCantidadPresentaciones.get(unidad.getSelectedItemPosition());
            importeProductoDos = Constante.g_const_0;
            if (productoSeleccionado != null)
                importeProductoDos = productoSeleccionado.getPrecio() * cantidadProducto;
            adapterDescuentos = new SpinnerConceptoAdapter(getContext(), listVacia);
            descuento.setAdapter(adapterDescuentos);
            //descuento.setAdapter(null);
            for (DescuentoEntity f : dsctos){
                descuentoVM.obtener_flag_descuento(f.getNtra(), Constante.g_const_2).observe(this, des -> {
                    if(des != null){
                        if(Integer.parseInt(des.getValorFinal().trim()) == Constante.g_const_1){ //CANTIDAD
                            if(cantidadProducto >= Integer.parseInt(des.getValorInicial().trim())){
                                listaDescuentosCombo.add(f.getDescripcion());
                                listaNtraDescuentosCombo.add(f.getNtra());
                            }
                        }
                        else { //SOLES (IMPORTE)
                            if (importeProductoDos >= Double.parseDouble(des.getValorInicial().trim())) {
                                listaDescuentosCombo.add(f.getDescripcion());
                                listaNtraDescuentosCombo.add(f.getNtra());
                            }
                        }

                        if(listaDescuentosCombo.size() > Constante.g_s_const_1){
                            adapterDescuentos = new SpinnerConceptoAdapter(getContext(), listaDescuentosCombo);
                            descuento.setAdapter(adapterDescuentos);
                            //descuento.setAdapter(new ArrayAdapter<String>( getContext(), android.R.layout.simple_spinner_item, listaDescuentosCombo));
                        }
                    }
                });
            }

        });
    }

}
