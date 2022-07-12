package com.idesolution.distribuidoravdc.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.idesolution.distribuidoravdc.Adaptador.DireccionListAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.Cabecera;
import com.idesolution.distribuidoravdc.BD.Entity.EntregaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDireccion;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Repository.CabeceraViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.EntregaViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PreventaViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.SincronizacionViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.MainActivity;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_preventa_selec_direccion extends Fragment {

    private DireccionListAdapter adapter;
    private RecyclerView recyclerView;
    //private FloatingActionButton btnNuevaDireccion;
    private EditText etNuevaDireccion;
    private Button btnRegistrarDireccion;
    private EntregaViewModel entregaVM;
    private CabeceraViewModel cabeceraVM;
    private PreventaViewModel preventaVM;
    private ClienteViewModel clienteVM;
    private EntregaEntity entrega;
    private Preventa preventa;
    private FilaDireccion fd;
    private List<FilaDireccion> lista_direccion;
    private String nombre;
    private String apePat;
    private String apeMat;
    private TextView tvNombreCliente;

    //EDITAR PREVENTA
    private Bundle parametroEntrada;
    private int proceso;
    private int transaccion;

    //SINCRONIZACION
    private SincronizacionViewModel sincronizacionViewModel;

    public f_preventa_selec_direccion() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parametroEntrada = this.getArguments();
        proceso = Constante.g_const_0;
        transaccion = Constante.g_const_0;
        if(parametroEntrada != null){
            proceso = parametroEntrada.getInt("par_p_");
            transaccion = parametroEntrada.getInt("par_t_");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_preventa_selec_direccion, container, false);
        if(v != null){
            recyclerView = v.findViewById(R.id.rvListaDirecciones);
            adapter = new DireccionListAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            //btnNuevaDireccion = (FloatingActionButton) v.findViewById(R.id.btnNuevaDireccion);
            tvNombreCliente = (TextView) v.findViewById(R.id.tvNomCliente_selec_dir);
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Direccion de Entrega");
        entregaVM = new ViewModelProvider(this).get(EntregaViewModel.class);
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        sincronizacionViewModel = new ViewModelProvider(this).get(SincronizacionViewModel.class);

        cabeceraVM.obtenerDirecciones().observe(this, d ->{
            lista_direccion = d;
            adapter.setDirecciones(d);
        });

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

            tvNombreCliente.setText(nombre + Constante.g_const_espacio + apePat + Constante.g_const_espacio + apeMat);
        });

        /*btnNuevaDireccion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_preventa_nueva_direccion, null);
                etNuevaDireccion = (EditText) mView.findViewById(R.id.etNuevaDirec_selec_dir);
                btnRegistrarDireccion = (Button) mView.findViewById(R.id.btnRegistrarDirec_selec_dir);

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

                btnRegistrarDireccion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!etNuevaDireccion.getText().toString().isEmpty()){
                            registroDireccion();
                            MetodosGlobales.mostrarMensaje(getContext(), "Direccion agregada");
                            dialog.cancel();
                        }else{
                            MetodosGlobales.mostrarMensaje(getContext(), "Debe ingresar la nueva dirección");
                            etNuevaDireccion.requestFocus();
                        }
                    }
                });
            }
        });*/

        adapter.setOnClickListener(v -> {
            fd = lista_direccion.get(recyclerView.getChildAdapterPosition(v));
            AlertDialog.Builder mensaje = new AlertDialog.Builder(v.getContext());
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
        });
    }

    public void registroDireccion(){
        cabeceraVM.obtenerNombreCliente().observe(this, c ->{
            entrega = new EntregaEntity(
                    Constante.g_const_0,
                    null,
                    null,
                    etNuevaDireccion.getText().toString(),
                    null,
                    null,
                    c.getTipoDocumento(),
                    c.getNumeroDocumento(),
                    c.getCodCliente(),
                    Constante.g_const_0
            );
            entregaVM.insert(entrega);
        });
    }

    public void registrarPreventa(){
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        preventaVM = new ViewModelProvider(this).get(PreventaViewModel.class);

        //Guardamos cabecera
        cabeceraVM.obtenerDatoVenta().observe(this, cabecera ->{
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
            //sincronizacionViewModel.SincronizarDataCompleta(1,"140101",getContext(),0);
            /*cabeceraVM.getAllDetalles().observe(this, detalles -> {

            });*/

        });



    }

    public void modificarPreventa(){
        preventaVM = new ViewModelProvider(this).get(PreventaViewModel.class);
        preventaVM.modificar(transaccion, fd.getId(), getContext());
    }

}
