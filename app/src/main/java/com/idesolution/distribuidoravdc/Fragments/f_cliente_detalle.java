package com.idesolution.distribuidoravdc.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ConceptoViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;
import com.idesolution.distribuidoravdc.UI.registro_cliente;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_cliente_detalle extends Fragment {
    private Integer ntraCliente;
    private ClienteViewModel clienteVM;
    private ConceptoViewModel conceptoVM;
    private TextView nombre;
    private TextView numDocumento;
    private TextView correo;
    private TextView direccion;
    private TextView telefono;
    private TextView celular;
    private TextView precio;
    private FragmentTransaction ft;


    private Bundle parametrosEntrada;
    private Bundle parametroSalida;

    public static f_cliente_detalle newInstance(Bundle arguments){
        f_cliente_detalle f = new f_cliente_detalle();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }
    public f_cliente_detalle() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        parametrosEntrada = this.getArguments();
        ntraCliente = Constante.g_const_0;
        if(parametrosEntrada != null){
            ntraCliente = Integer.parseInt(parametrosEntrada.getString("num_tra_cliente"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_cliente_detalle, container, false);
        if(v != null){
            nombre = (TextView) v.findViewById(R.id.tvNombresDetCliente_cli);
            numDocumento = (TextView) v.findViewById(R.id.tvNumDocDetCliente_cli);
            correo = (TextView) v.findViewById(R.id.tvCorreoDetCliente_cli);
            direccion = (TextView) v.findViewById(R.id.tvDireccionDetCliente_cli);
            telefono = (TextView) v.findViewById(R.id.tvTelefonoDetCliente_cli);
            celular = (TextView) v.findViewById(R.id.tvCelularDetCliente_cli);
            precio = (TextView) v.findViewById(R.id.tvTipoPrecioDetCliente_cli);
            getActivity().setTitle("Detalle Cliente");
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cargar_datos();
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_editar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnEditar:
                /*parametroSalida = new Bundle();
                parametroSalida.putInt("parametro_proceso",  Constante.g_const_2);
                parametroSalida.putInt("parametro_transaccion",  ntraCliente);
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_principal, new f_registro_cliente().newInstance(parametroSalida));
                ft.addToBackStack(null);
                ft.commit();
                Intent intent = new Intent(getContext(), registro_cliente.class);
                intent.putExtra("parametro_proceso", Constante.g_const_2);
                intent.putExtra("parametro_transaccion", ntraCliente);
                startActivity(intent);
        }
        return true;
    }*/

    public void cargar_datos(){
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        conceptoVM = new ViewModelProvider(this).get(ConceptoViewModel.class);
        clienteVM.buscarCliente(ntraCliente).observe(this, cliente -> {
            if(cliente.getTipoPersona() == Constante.g_const_2){
                nombre.setText(cliente.getRazonSocial());
                numDocumento.setText(cliente.getRuc());
            }else{
                nombre.setText(cliente.getNombres() + Constante.g_const_espacio + cliente.getApellidoPaterno() + Constante.g_const_espacio + cliente.getApellidoMaterno());
                numDocumento.setText(cliente.getNumeroDocumento());
            }
            correo.setText(cliente.getCorreo());
            direccion.setText(cliente.getDireccion());
            telefono.setText(cliente.getTelefono());
            celular.setText(cliente.getCelular());
            conceptoVM.obtenerConcepto(Constante.g_const_7, cliente.getTipoListaPrecio()).observe(this, descripcion -> {
                precio.setText(descripcion);
            });
        });
    }





}
