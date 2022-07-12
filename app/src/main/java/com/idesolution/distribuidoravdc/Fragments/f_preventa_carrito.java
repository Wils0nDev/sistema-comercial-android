package com.idesolution.distribuidoravdc.Fragments;


import android.app.Application;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.idesolution.distribuidoravdc.Adaptador.FilaCarritoListAdapter;
import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.ParametroDao;
import com.idesolution.distribuidoravdc.BD.Entity.DescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.Detalle;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCarrito;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDireccion;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Repository.CabeceraViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.DescuentoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ParametroViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PreventaViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.CompletableOnSubscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_preventa_carrito extends Fragment {

    private FragmentTransaction ft;
    private RecyclerView recyclerView;
    private FilaCarritoListAdapter adapter;
    private CabeceraViewModel cabeceraVM;
    private ParametroViewModel parametroVM;
    private List<FilaCarrito> listaCarrito;
    private int cantidadItems;
    private double subTotal;
    private double recargo;
    private double igv;
    private double total;
    private double totalSalvado;
    private TextView tvCantidad;
    private TextView tvSubtotal;
    private TextView tvRecargo;
    private TextView tvDescuento;
    private TextView tvIgv;
    private TextView tvTotal;
    //private TextView tvNombreCliente;
    private TextView tvFechaEntrega;
    private TextView tvHoraEntrega;
    //private RadioButton rbContado;
    //private RadioButton rbCredito;
    //private RadioButton rbBoleta;
    //private RadioButton rbFactura;
    //private Button btnAgregarDirecciones;
    //private Button btnCancelarPreventa;
    private Button btnFechaEntrega;
    private CheckBox cbAplicaRecargo;
    //private Spinner spDescuentos;
    private short flagRecargo;
    private String nombre;
    private String apePat;
    private String apeMat;
    private short tipoVenta;
    private short tipoDB;

    private double m_subTotal;
    private double m_recargo;
    private double m_igv;
    private double m_total;
    private DescuentoViewModel descuentoVM;
    private ArrayList<String> listaDescuentosCombo;
    private ArrayList<Integer> listaNtraDescuentosCombo;
    private int ntraDescuento;
    private double descuento;
    private double totalDos;
    private int positionCombo;
    private double descuentoSalvado;

    //FECHA ENTREGA
    private String diaCadena;
    private String mesCadena;
    private Calendar calendario;
    private int dia;
    private int mes;
    private int anio;

    private int hora;
    private int minuto;
    private String horaCadena;
    private String minutoCadena;

    //EDITAR PREVENTA
    private Bundle parametroEntreda;
    private Bundle parametroSalida;
    private int proceso;
    private int transaccion;
    private f_preventa_lista_producto lista_producto;
    private f_preventa_selec_direccion selec_direccion;
    private int cantidadPE;
    private PreventaViewModel preventaVM;
    private Preventa preventa;
    private FilaDireccion fd;
    private ImageButton quitar;

    public static f_preventa_carrito newInstance(Bundle arguments){
        f_preventa_carrito f = new f_preventa_carrito();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    private FragmentManager fragmentManager;

    public f_preventa_carrito() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        parametroEntreda = this.getArguments();
        proceso = Constante.g_const_0;
        transaccion = Constante.g_const_0;
        if(parametroEntreda != null){
            proceso = parametroEntreda.getInt("par_proceso_");
            transaccion = parametroEntreda.getInt("par_transaccion_");
        }

        cantidadItems = Constante.g_const_0;
        subTotal = Constante.g_const_0;
        recargo = Constante.g_const_0;
        igv = Constante.g_const_0;
        total = Constante.g_const_0;
        totalSalvado = Constante.g_const_0;
        flagRecargo = Constante.g_const_0;
        tipoVenta = Constante.g_s_const_0;
        tipoDB = Constante.g_s_const_0;


        m_subTotal = Constante.g_const_0;
        m_recargo = Constante.g_const_0;
        m_igv = Constante.g_const_0;
        m_total = Constante.g_const_0;

        listaDescuentosCombo = new ArrayList<String>();
        listaNtraDescuentosCombo = new ArrayList<Integer>();
        ntraDescuento = Constante.g_const_0;
        descuento = Constante.g_const_0;
        totalDos = Constante.g_const_0;
        positionCombo = Constante.g_const_0;
        descuentoSalvado = Constante.g_const_0;

        cantidadPE = Constante.g_const_0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_preventa_carrito, container, false);
        if(v != null){
            recyclerView = v.findViewById(R.id.rvListaProductos_carrito);
            adapter = new FilaCarritoListAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            tvCantidad = (TextView) v.findViewById(R.id.tvCantidadProductos_carrito_pre);
            tvSubtotal = (TextView) v.findViewById(R.id.tvSubtotal_carrito_pre);
            tvRecargo = (TextView) v.findViewById(R.id.tvRecargo_carrito_pre);
            tvDescuento = (TextView) v.findViewById(R.id.tvDescuento_carrito_pre);
            tvIgv = (TextView) v.findViewById(R.id.tvIgv_Carrito_pre);
            tvTotal = (TextView) v.findViewById(R.id.tvTotal_carrito_pre);
            //tvNombreCliente = (TextView) v.findViewById(R.id.tvNombreCliente_carrito_pre);
            tvFechaEntrega = v.findViewById(R.id.tvFechaEntrega_carrito_pre);
            tvHoraEntrega = v.findViewById(R.id.tvHoraEntrega_carrito_pre);
            //rbContado = (RadioButton) v.findViewById(R.id.rbContado_carrito_pre);
            //rbCredito = (RadioButton) v.findViewById(R.id.rbCredito_carrito_pre);
            //rbBoleta = (RadioButton) v.findViewById(R.id.rbBoleta_carrito_pre);
            //rbFactura = (RadioButton) v.findViewById(R.id.rbFactura_carrito_pre);
            cbAplicaRecargo = (CheckBox) v.findViewById(R.id.cbAplicarRecargo_carrito_pre);
            //btnCancelarPreventa = (Button) v.findViewById(R.id.btnCancelar_carrito_pre);
            //btnAgregarDirecciones = (Button) v.findViewById(R.id.btnAgregarDireccion_carrito_pre);
            btnFechaEntrega = (Button) v.findViewById(R.id.btnFechaEntrega_carrito_pre);
            //quitar = (ImageButton) v.findViewById(R.id.btnQuitarProducto_carrito);
            //spDescuentos = (Spinner) v.findViewById(R.id.spDescuentoVenta_carrito_pre);



            //rbContado.setChecked(true);
            //rbBoleta.setChecked(true);
            tipoVenta = Constante.g_s_const_1;
            tipoDB = Constante.g_s_const_1;



            getActivity().setTitle("Carrito de ventas");
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        parametroVM = new ViewModelProvider(this).get(ParametroViewModel.class);
        descuentoVM = new ViewModelProvider(this).get(DescuentoViewModel.class);

        cabeceraVM.verificarCantidadPuntosEntrega().observe(this, cant -> {
            cantidadPE = cant;
        });
        cabeceraVM.obtenerDireccionesPreventaDos().observe(this, dire -> {
            fd = dire;
        });

        /*quitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MetodosGlobales.mostrarMensaje(getContext(), "NOOOOOOO");
            }
        });*/

        descuentoVM.obtenerDescuentos(Constante.G_CONST_VACIO, Constante.g_const_2);
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        cabeceraVM.obtenerDatoVenta().observe(this, c ->{
            if(c.getTipoVenta() == Constante.g_s_const_1) {
                tipoVenta = Constante.g_s_const_1;
                //rbContado.setChecked(true);
                flagRecargo = Constante.g_s_const_0;
                cbAplicaRecargo.setChecked(false);
                cbAplicaRecargo.setEnabled(false);
            }else{
                //rbCredito.setChecked(true);
                tipoVenta = Constante.g_s_const_2;
                if(c.getFlagRecargo() == Constante.g_s_const_1){
                    cbAplicaRecargo.setChecked(true);
                    flagRecargo = Constante.g_s_const_1;
                        verificarFlagRecargo();
                }else{
                    cbAplicaRecargo.setChecked(false);
                    flagRecargo = Constante.g_s_const_0;
                }
            }

            if(c.getTipoDocumentoVenta() == Constante.g_s_const_1) {
                tipoDB = Constante.g_s_const_1;
                //rbBoleta.setChecked(true);
            }else{
                //rbFactura.setChecked(true);
                tipoDB = Constante.g_s_const_2;
            }

            tvFechaEntrega.setText(c.getFechaEntrega());
            tvHoraEntrega.setText(c.getHoraEntrega());
        });

        cabeceraVM.obtenerDatoVenta().observe(this, c ->{
            /*if(c.getTipoVenta() == Constante.g_s_const_1) {
                rbContado.setChecked(true);
            }else{
                rbCredito.setChecked(true);
            }*/

            /*if(c.getTipoDocumentoVenta() == Constante.g_s_const_1) {
                rbBoleta.setChecked(true);
            }else{
                rbFactura.setChecked(true);
            }*/
        });

        nombre = Constante.G_CONST_VACIO;
        apePat = Constante.G_CONST_VACIO;
        apeMat = Constante.G_CONST_VACIO;
        cabeceraVM.obtenerNombreCliente().observe(this, c -> {
            if(c.getTipoPersona() == Constante.g_const_2){
                nombre = c.getRazonSocial();
            }else{
                nombre = c.getNombres();
                apePat = c.getApellidoPaterno();
                apeMat = c.getApellidoMaterno();
            }
            if(nombre == null)
                nombre = Constante.G_CONST_VACIO;
            if(apePat == null)
                apePat = Constante.G_CONST_VACIO;
            if(apeMat == null)
                apeMat = Constante.G_CONST_VACIO;

            //tvNombreCliente.setText(nombre + Constante.g_const_espacio + apePat + Constante.g_const_espacio + apeMat);
        });

        cabeceraVM.obtenerFilaCarrito().observe(this, filaCarritos -> {
            listaCarrito = filaCarritos;
            fragmentManager = getFragmentManager();
            adapter.setFilaCarrito(filaCarritos, this, getViewLifecycleOwner(), fragmentManager);

        });

        cabeceraVM.getAllDetalles().observe(this, detalles ->{
            /*m_total = 0;
            m_recargo = 0;
            descuento = 0;
            total = 0;
            setearValores(0, 0);*/
            parametroVM.obtenerDouble(Constante.g_const_1).observe(this, valor -> {
                cantidadItems = Constante.g_const_0;
                subTotal = Constante.g_const_0;
                recargo = Constante.g_const_0;
                igv = Constante.g_const_0;
                total = Constante.g_const_0;

                for(Detalle d: detalles){
                    cantidadItems = cantidadItems + Constante.g_const_1;
                    total = total + d.getCantidadUnidadBase() * d.getPrecioVenta();
                }
                totalSalvado = total;

                if(flagRecargo == Constante.g_const_1) {
                    recargo = valor / 100 * total;
                    total = total + recargo;
                }
                else {
                    recargo = Constante.g_const_0;
                    total = totalSalvado;
                }
                //recargo = valor * total;
                descuento = Constante.g_d_const_0;
                //obtenerDescuentosTotales(total);
                setearValores(recargo, total);

                //Redondeamos
                /*igv = total - (total/(1.18));
                subTotal = total - igv;

                total = MetodosGlobales.formatearDecimales(total, Constante.g_const_2);
                igv = MetodosGlobales.formatearDecimales(igv, Constante.g_const_2);
                subTotal = MetodosGlobales.formatearDecimales(subTotal, Constante.g_const_2);
                recargo = MetodosGlobales.formatearDecimales(recargo, Constante.g_const_2);

                tvCantidad.setText(String.valueOf(cantidadItems));
                tvSubtotal.setText(String.valueOf(subTotal));
                tvRecargo.setText(String.valueOf(recargo));
                tvDescuento.setText(String.valueOf(0));
                tvIgv.setText(String.valueOf(igv));
                tvTotal.setText(String.valueOf(total));*/


                /*rbContado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        if(rbContado.isChecked()){
                            tipoVenta = Constante.g_s_const_1;
                            cbAplicaRecargo.setChecked(false);
                            cbAplicaRecargo.setEnabled(false);
                            flagRecargo = Constante.g_const_0;
                            recargo = 0;
                            total = totalSalvado;

                            setearValores(recargo, total);
                            //obtenerDescuentosTotales(total);

                        }
                    }

                });

                rbCredito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        if(rbCredito.isChecked()) {
                            tipoVenta = Constante.g_s_const_2;
                            cbAplicaRecargo.setChecked(true);
                            //cbAplicaRecargo.setEnabled(true);
                            verificarFlagRecargo();
                            total = totalSalvado;
                            flagRecargo = Constante.g_const_1;
                            recargo = valor/100 * total;
                            total = total + recargo;

                            //igv = total - (total/(1.18));
                            //subTotal = total - igv;

                            setearValores(recargo, total);
                            //obtenerDescuentosTotales(total);


                        }
                    }
                });*/

                cbAplicaRecargo.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(cbAplicaRecargo.isChecked()){
                            recargo = valor/100 * total;
                            total = total + recargo;
                            flagRecargo = Constante.g_const_1;
                        }
                        else{
                            recargo = 0;
                            total = totalSalvado;
                            flagRecargo = Constante.g_const_0;
                        }
                        setearValores(recargo, total);
                        //obtenerDescuentosTotales(total);

                        /*igv = total - (total/(1.18));
                        subTotal = total - igv;

                        total = MetodosGlobales.formatearDecimales(total, Constante.g_const_2);
                        igv = MetodosGlobales.formatearDecimales(igv, Constante.g_const_2);
                        subTotal = MetodosGlobales.formatearDecimales(subTotal, Constante.g_const_2);
                        recargo = MetodosGlobales.formatearDecimales(recargo, Constante.g_const_2);

                        tvCantidad.setText(String.valueOf(cantidadItems));
                        tvSubtotal.setText(String.valueOf(subTotal));
                        tvRecargo.setText(String.valueOf(recargo));
                        tvDescuento.setText(String.valueOf(0));
                        tvIgv.setText(String.valueOf(igv));
                        tvTotal.setText(String.valueOf(total));*/
                    }
                });

                /*rbBoleta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tipoDB = Constante.g_s_const_1;
                    }
                });*/

                /*rbFactura.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tipoDB = Constante.g_s_const_2;
                    }
                });*/

            });

            /*cantidadItems = Constante.g_const_0;
            subTotal = Constante.g_const_0;
            recargo = Constante.g_const_0;
            igv = Constante.g_const_0;
            total = Constante.g_const_0;

            for(Detalle d: detalles){
                cantidadItems = cantidadItems + Constante.g_const_1;
                total = total + d.getCantidadUnidadBase() * d.getPrecioVenta();
            }
            totalSalvado = total;
            igv = total - (total/(1.18));
            subTotal = total - igv;
            recargo = porccentajeRecargo * total;

            //Redondeamos
            total = MetodosGlobales.formatearDecimales(total, Constante.g_const_2);
            igv = MetodosGlobales.formatearDecimales(igv, Constante.g_const_2);
            subTotal = MetodosGlobales.formatearDecimales(subTotal, Constante.g_const_2);

            tvCantidad.setText(String.valueOf(cantidadItems));
            tvSubtotal.setText(String.valueOf(subTotal));
            tvRecargo.setText(String.valueOf(0));
            tvDescuento.setText(String.valueOf(0));
            tvIgv.setText(String.valueOf(igv));
            tvTotal.setText(String.valueOf(total));*/

        });

        /*rbContado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(rbContado.isChecked()){
                    cbAplicaRecargo.setChecked(false);
                    cbAplicaRecargo.setEnabled(false);
                }
            }

        });*/
        /*rbCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(rbCredito.isChecked()) {
                    cbAplicaRecargo.setChecked(true);
                    cbAplicaRecargo.setEnabled(true);
                }
            }
        });*/
        /*cbAplicaRecargo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(cbAplicaRecargo.isChecked()){
                    total = total + recargo;
                }
                else{
                    total = totalSalvado;
                }
            }
        });*/
        /*btnCancelarPreventa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(v.getContext());
                if(proceso == Constante.g_const_1)
                    mensaje.setTitle("¿Desea cancelar la preventa?");
                else
                    mensaje.setTitle("¿Desea anular los cambios realizados en la preventa?");
                mensaje.setCancelable(false);
                mensaje.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cabeceraVM.deletePreventaInconclusa();
                        getActivity().finish();
                    }
                });
                mensaje.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mensaje.show();
            }
        });

        btnAgregarDirecciones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(validaciones()){
                    descuentosProrrateo();
                    parametroSalida = new Bundle();
                    parametroSalida.putInt("par_p_", proceso);
                    parametroSalida.putInt("par_t_", transaccion);
                    selec_direccion = new f_preventa_selec_direccion();
                    selec_direccion.setArguments(parametroSalida);
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_nueva_preventa, selec_direccion);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });*/

        /*spDescuentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionCombo = position;
                obtenerImporteDescuento(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        btnFechaEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFechaEntrega();
            }
        });

    }

    public void obtenerFechaEntrega(){
        calendario  = Calendar.getInstance();
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minuto = calendario.get(Calendar.MINUTE);
        horaCadena = Constante.G_CONST_VACIO;


        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                //String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                //String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario

                if(hourOfDay < 10)
                    horaCadena = "0" + hourOfDay;
                else
                    horaCadena = hourOfDay + Constante.G_CONST_VACIO;

                if(minute < 10)
                    minutoCadena = "0" + minute;
                else
                    minutoCadena = minute + Constante.G_CONST_VACIO;

                //Muestro la hora con el formato deseado
                //etHora.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
                tvHoraEntrega.setText(horaCadena.trim() + ":" + minutoCadena.trim() + ":00");
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);
        timePickerDialog.show();



        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH);
        anio = calendario.get(Calendar.YEAR);
        diaCadena = Constante.G_CONST_VACIO;
        mesCadena = Constante.G_CONST_VACIO;

        Calendar calendarioSel = Calendar.getInstance();

        Calendar calendarioMin = Calendar.getInstance();
        calendarioMin.add(Calendar.MONTH, - 0); //Mes actual
        calendarioMin.add(Calendar.DAY_OF_MONTH, - 0); //dia actual

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarioSel.set(year, month, dayOfMonth);
                if (calendarioSel.getTimeInMillis() < (calendarioMin.getTimeInMillis() - 1000))
                {
                    MetodosGlobales.mostrarMensaje(getContext(), "FECHA SELECCIONADA NO ES VALIDA");
                }
                else{
                    month = month + Constante.g_const_1;
                    if(month < 10)
                        mesCadena = "0" + month;
                    else
                        mesCadena = month + "";

                    if(dayOfMonth < 10)
                        diaCadena = "0" + dayOfMonth;
                    else
                        diaCadena = dayOfMonth + "";

                    tvFechaEntrega.setText(diaCadena.trim() + "/" + mesCadena.trim() + "/" + year);
                }

            }

        }
                ,anio,mes,dia);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    public boolean validaciones(){

        if(listaCarrito.size() == Constante.g_s_const_0) {
            MetodosGlobales.mostrarMensaje(getContext(), "No agregó productos al carrito");
            return false;
        }

        if(tvFechaEntrega.getText().toString().trim().isEmpty()){
            MetodosGlobales.mostrarMensaje(getContext(), "Debe seleccionar una fecha de entrega");
            return false;
        }

        return true;
    }

    /*private class obtenerDouble extends AsyncTask<Integer, Integer, Double>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Double doInBackground(Integer... integers) {
            Double result = 0.0;
            AppDataBase db;
            ParametroDao parametroDao;
            db = AppDataBase.getDatabase(getActivity().getApplication());
            parametroDao = db.parametroDao();
            result = parametroDao.obtenerDouble1(integers[0]);
            return result;
        }
        @Override
        protected void onPostExecute(Double resultado) {
            super.onPostExecute(resultado);
            porccentajeRecargo = resultado;
        }
    }*/

    public void setearValores(
            double recargo,
            double total
    ){
        //double subTotal = Constante.g_const_0;
        //double igv = Constante.g_const_0;
        m_recargo = recargo;
        m_total = total;



        parametroVM = new ViewModelProvider(this).get(ParametroViewModel.class);
        /*parametroVM.obtenerDouble(Constante.g_const_2).observe(this, i -> {
            m_igv = m_total - (m_total/(i));
            //m_igv = m_total - (m_total/(1.18));
            m_subTotal = m_total - m_igv;

            m_total = MetodosGlobales.formatearDecimales(m_total, Constante.g_const_2);
            m_igv = MetodosGlobales.formatearDecimales(m_igv, Constante.g_const_2);
            m_subTotal = MetodosGlobales.formatearDecimales(m_subTotal, Constante.g_const_2);
            m_recargo = MetodosGlobales.formatearDecimales(m_recargo, Constante.g_const_2);

            tvCantidad.setText(String.valueOf(cantidadItems));
            tvSubtotal.setText(String.valueOf(m_subTotal));
            tvRecargo.setText(String.valueOf(m_recargo));
            tvDescuento.setText(String.valueOf(MetodosGlobales.formatearDecimales(descuento, Constante.g_const_2)));
            tvIgv.setText(String.valueOf(m_igv));
            tvTotal.setText(String.valueOf(m_total));

        });*/

        descuento = Constante.g_d_const_0;
        cabeceraVM.importeDescuentosItem().observe(this , desc -> {
            if (desc != null) {
                descuento = descuento + desc;
                m_total = m_total - descuento;
                if(m_total <= 0){
                    m_total = 0;
                    descuento = 0;
                }
            }

            parametroVM.obtenerDouble(Constante.g_const_2).observe(this, i -> {
                m_igv = m_total - (m_total/(i));
                //m_igv = m_total - (m_total/(1.18));
                m_subTotal = m_total - m_igv;

                m_total = MetodosGlobales.formatearDecimales(m_total, Constante.g_const_2);
                m_igv = MetodosGlobales.formatearDecimales(m_igv, Constante.g_const_2);
                m_subTotal = MetodosGlobales.formatearDecimales(m_subTotal, Constante.g_const_2);
                m_recargo = MetodosGlobales.formatearDecimales(m_recargo, Constante.g_const_2);

                tvCantidad.setText(String.valueOf(cantidadItems));
                tvSubtotal.setText(String.format("%.2f",m_subTotal));
                tvRecargo.setText(String.format("%.2f",m_recargo));
                tvDescuento.setText(String.format("%.2f",MetodosGlobales.formatearDecimales(descuento, Constante.g_const_2)));
                tvIgv.setText(String.format("%.2f",m_igv));
                tvTotal.setText(String.format("%.2f",m_total));

            });
        });



    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();
        //MetodosGlobales.mostrarMensaje(getContext(), tipoVenta +"-" + tipoDB + "-" + flagRecargo +"-"+ m_recargo +"-"+ m_igv+ "-"+ m_total);
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        cabeceraVM.salvarCabecera(tipoVenta, tipoDB, flagRecargo, m_recargo, m_igv, m_total, tvFechaEntrega.getText().toString().trim(), tvHoraEntrega.getText().toString().trim());
    }


    public void verificarFlagRecargo(){
        parametroVM = new ViewModelProvider(this).get(ParametroViewModel.class);
        parametroVM.obtenerEntero(Constante.g_const_3).observe(this, flag ->{
            if(flag == Constante.g_const_1){
                cbAplicaRecargo.setEnabled(true);
            }else
            {
                cbAplicaRecargo.setEnabled(false);
            }
        });
    }

    /*public void obtenerDescuentosTotales(double importe){

        descuentoVM = new ViewModelProvider(this).get(DescuentoViewModel.class);
        listaNtraDescuentosCombo = new ArrayList<Integer>();
        listaDescuentosCombo = new ArrayList<String>();

        descuentoVM.descuentosHabilitados().observe(this, dsctos -> {
            spDescuentos.setAdapter(null);
            for (DescuentoEntity f : dsctos){
                descuentoVM.obtener_flag_descuento(f.getNtra(), Constante.g_const_9).observe(this, des -> {
                    if(des != null){
                        if(importe >= Double.parseDouble(des.getValorInicial().trim())){
                            listaDescuentosCombo.add(f.getDescripcion());
                            listaNtraDescuentosCombo.add(f.getNtra());
                        }

                        if(listaDescuentosCombo.size() > Constante.g_s_const_0){
                            spDescuentos.setAdapter(new ArrayAdapter<String>( getContext(), android.R.layout.simple_spinner_item, listaDescuentosCombo));
                        spDescuentos.setSelection(positionCombo);
                        }
                    }
                });
            }

        });
    }*/

    public void obtenerImporteDescuento(int pos){
        if(listaNtraDescuentosCombo.size() > Constante.g_s_const_0 ){
            cabeceraVM.importeDescuentosItem().observe(this , desc -> {
                descuento = Constante.g_d_const_0;
                if (desc != null)
                    descuento = descuento + desc;
                m_total = total;
                m_total = m_total - descuento;
                setearValores(m_recargo, m_total);

                //double m;
                //if(desc != null){
                    /*descuentoVM.obtener_flag_descuento(listaNtraDescuentosCombo.get(pos), Constante.g_const_3).observe(this, des -> {
                        if(des != null){
                            if(Integer.parseInt(des.getValorFinal().trim()) == Constante.g_s_const_1){ //IMPORTE
                                descuento = Double.parseDouble(des.getValorInicial().trim());
                            }else{ //PORCENTAJE
                                descuento = (m_total * (Double.parseDouble(des.getValorInicial().trim()) / 100));
                            }
                            if(descuento < Constante.g_s_const_0)
                                descuento = Constante.g_s_const_0;
                            descuentoSalvado = descuento;
                            if (desc != null)
                                descuento = descuento + desc;
                            m_total = total;
                            m_total = m_total - descuento;
                            setearValores(m_recargo, m_total);
                        }
                    });*/

                //}
            });

        }
    }

    /*public void descuentosProrrateo(){
        if(listaNtraDescuentosCombo.size() > Constante.g_s_const_0){
            cabeceraVM.prorrateoDescuentoTotal(listaNtraDescuentosCombo.get(spDescuentos.getSelectedItemPosition()), descuentoSalvado);
        }
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_carrito, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnCancelarP:
                //MetodosGlobales.mostrarMensaje(getContext(), "CANCELAR");

                new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("CANCELAR PREVENTA")
                        .setContentText("¿Desea anular los cambios realizados en la preventa?")
                        .setConfirmText("SI")

                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                Integer conexion = MetodosGlobales.verificarConexionInternet(getContext());
                                if (conexion == Constante.g_const_1){
                                    sDialog.dismissWithAnimation();
                                    getActivity().finish();
                                }else{
                                    Toast.makeText(getContext(),Constante.g_const_vald_conexion,Toast.LENGTH_SHORT).show();
                                }
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
                if(proceso == Constante.g_const_1)
                    mensaje.setTitle("¿Desea cancelar la preventa?");
                else
                    mensaje.setTitle("¿Desea anular los cambios realizados en la preventa?");
                mensaje.setCancelable(false);
                mensaje.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cabeceraVM.deletePreventaInconclusa();
                        getActivity().finish();
                    }
                });
                mensaje.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mensaje.show();
                */
                break;
            case R.id.mnAgregarP:
                //MetodosGlobales.mostrarMensaje(getContext(), "AGREGAR ");
                if(proceso == Constante.g_const_1)
                    getActivity().onBackPressed();
                else{
                    lista_producto = new f_preventa_lista_producto();
                    parametroSalida = new Bundle();
                    parametroSalida.putInt("par_pro_", Constante.g_const_2);
                    lista_producto.setArguments(parametroSalida);
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_nueva_preventa, lista_producto);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;

            case R.id.mnContinuarP:
                //MetodosGlobales.mostrarMensaje(getContext(), "CONTINUAR");
                if(validaciones()){
                    //descuentosProrrateo();
                    parametroSalida = new Bundle();
                    parametroSalida.putInt("par_p_", proceso);
                    parametroSalida.putInt("par_t_", transaccion);
                    selec_direccion = new f_preventa_selec_direccion();
                    selec_direccion.setArguments(parametroSalida);
                    ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_nueva_preventa, selec_direccion);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                /*if(validaciones()) {
                    proceso();
                }*/
                break;
        }
        return true;
    }

    public void proceso(){
        if(cantidadPE == Constante.g_const_1){
            procesoPreventa();
        }
        else{
            //descuentosProrrateo();
            parametroSalida = new Bundle();
            parametroSalida.putInt("par_p_", proceso);
            parametroSalida.putInt("par_t_", transaccion);
            selec_direccion = new f_preventa_selec_direccion();
            selec_direccion.setArguments(parametroSalida);
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_nueva_preventa, selec_direccion);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    public void procesoPreventa(){
        android.app.AlertDialog.Builder mensaje = new android.app.AlertDialog.Builder(getContext());
        if(proceso == Constante.g_const_1)
            mensaje.setTitle("¿DESEA GUARDAR LA PREVENTA?");
        else
            mensaje.setTitle("¿DESEA GUARDAR LOS CAMBIOS EN LA PREVENTA?");

        mensaje.setCancelable(false);
        mensaje.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(proceso == Constante.g_const_1){
                    registrarPreventa();
                    MetodosGlobales.mostrarMensaje(getContext(), "Preventa registrada");
                }else{
                    //PROCESO MOD
                    //transaccion, fd.getId()
                    modificarPreventa();
                    MetodosGlobales.mostrarMensaje(getContext(), "Preventa Modificada");
                }

                getActivity().finish();
            }
        });
        mensaje.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mensaje.show();
    }

    public void registrarPreventa(){
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        preventaVM = new ViewModelProvider(this).get(PreventaViewModel.class);

        //Guardamos cabecera
        cabeceraVM.obtenerDatoVenta().observe(this, cabecera ->{
            //cabeceraVM.obtenerDireccionesPreventaDos().observe(this, fd -> {
                if(fd != null){
                    preventa = new Preventa(
                            Constante.g_const_0,
                            cabecera.getCodCliente(),
                            cabecera.getTipoDocumento(),
                            cabecera.getNumeroDocumento(),
                            cabecera.getCodUsuario(),
                            fd.getId(),
                            cabecera.getMoneda(),
                            cabecera.getTipoVenta(),
                            cabecera.getTipoDocumentoVenta(),
                            cabecera.getFecha(),
                            cabecera.getFlagRecargo(),
                            cabecera.getRecargo(),
                            cabecera.getIgv(),
                            cabecera.getIsc(),
                            cabecera.getTotal(),
                            cabecera.getEstado(),
                            Constante.g_s_const_0,
                            Constante.g_s_const_1,
                            cabecera.getFechaEntrega(),
                            cabecera.getHoraEntrega(),
                            cabecera.getCodSucursal()
                    );
                    preventaVM.insert(preventa, getContext());
                }
            //});
        });



    }

    public void modificarPreventa(){
        preventaVM = new ViewModelProvider(this).get(PreventaViewModel.class);
        //cabeceraVM.obtenerDireccionesPreventaDos().observe(this, fd -> {
            if(fd != null){
                preventaVM.modificar(transaccion, fd.getId(), getContext());
            }
        //});
    }
}
