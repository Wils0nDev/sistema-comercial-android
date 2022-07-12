package com.idesolution.distribuidoravdc.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import android.widget.SearchView;

import com.idesolution.distribuidoravdc.Adaptador.ClienteListAdapter;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;
import com.idesolution.distribuidoravdc.BD.Entity.Cabecera;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCliente;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Repository.CabeceraViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.PreventaViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Entidad.LoginLocal;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_preventa_selec_cliente extends Fragment {

    private ClienteListAdapter adapter;
    private RecyclerView recyclerView;
    private ClienteViewModel clienteVM;
    //private PreventaViewModel preventaVM;
    private CabeceraViewModel cabeceraVM;
    private Cabecera cabecera;
    private String numDocumento;

    private List<FilaCliente> lista_clientes;
    //private Preventa preventa;
    private Bundle parametroSalida;
    private FragmentTransaction ft;
    private f_preventa_lista_producto lista_producto;
    private SearchView searchView;
    SessionManager sessionManager;
    LoginLocal loginLocal;

    public f_preventa_selec_cliente() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_preventa_selec_cliente, container, false);
        if(v != null){
            sessionManager = new SessionManager(getContext());
            loginLocal = sessionManager.getUserDetail();
            recyclerView = v.findViewById(R.id.rvSeleccionarCliente_pre);
            adapter = new ClienteListAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            getActivity().setTitle(R.string.titulo_seleccion_cliente);
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        clienteVM.buscarClientes("").observe(this, clientes -> {
            lista_clientes = clientes;
            adapter.setClientes(clientes);
        });

        adapter.setOnClickListener(v -> {
            FilaCliente fc = lista_clientes.get(recyclerView.getChildAdapterPosition(v));
            if(fc.getTipoPersona() == Constante.g_const_2){
                numDocumento = fc.getRuc();
            }else{
                numDocumento = fc.getNumeroDocumento();
            }

            /*preventaVM = new ViewModelProvider(this).get(PreventaViewModel.class);
            preventa = new Preventa(
                    Constante.g_const_0,
                    fc.getNtra(),
                    fc.getCodCliente(),
                    Constante.g_const_1,
                    Constante.g_const_0,
                    Constante.g_s_const_0,
                    Constante.g_s_const_0,
                    Constante.g_s_const_0,
                    Constante.G_CONST_VACIO,
                    Constante.g_d_const_0,
                    Constante.g_d_const_0,
                    Constante.g_d_const_0,
                    Constante.g_d_const_0,
                    Constante.g_s_const_0,
                    Short.parseShort(String.valueOf(Constante.g_const_m1))
            );
            preventaVM.insert(preventa);*/
            cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
            cabecera = new Cabecera(
                    //Constante.g_const_0,
                    /*fc.getNtra(),*/
                    fc.getCodCliente(),
                    fc.getTipoPersona(),
                    numDocumento,
                    loginLocal.getNtraUsuario(),
                    Constante.g_const_0,
                    Constante.g_s_const_1,
                    Constante.g_s_const_1,
                    Constante.g_s_const_1,
                    MetodosGlobales.obtenerFechaActual(),
                    Constante.g_s_const_0,
                    Constante.g_d_const_0,
                    Constante.g_d_const_0,
                    Constante.g_d_const_0,
                    Constante.g_d_const_0,
                    Constante.g_s_const_1, //,
                    Constante.g_s_const_1,
                    Constante.G_CONST_VACIO,
                    Constante.G_CONST_VACIO,
                    loginLocal.getCodSucursal()
                    //Short.parseShort(String.valueOf(Constante.g_const_m1))
            );
            cabeceraVM.insert(cabecera);

            /*parametroSalida = new Bundle();
            parametroSalida.putInt("ntra_cliente_pre", fc.getNtra());
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_nueva_preventa, new f_preventa_lista_producto().newInstance(parametroSalida));
            ft.addToBackStack(null);
            ft.commit();*/
            lista_producto = new f_preventa_lista_producto();
            parametroSalida = new Bundle();
            parametroSalida.putInt("par_pro_", Constante.g_const_1);
            lista_producto.setArguments(parametroSalida);
            alertas();
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_nueva_preventa, lista_producto);
            ft.addToBackStack(null);
            ft.commit();
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search_productos, menu);
        MenuItem myActionMenuItem = menu.findItem( R.id.app_search_productos);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                //LOGICA AL PRESIONAR UNA TECLA
                buscar_cliente(s);
                return false;
            }
        });
    }

    public void buscar_cliente (String cadena){
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        clienteVM.buscarClientes(cadena).observe(this, clientes -> {
            lista_clientes = clientes;
            adapter.setClientes(clientes);
        });
    }

    public void alertas(){
        //
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);

        AlertDialog.Builder tipoDocumentoVenta = new AlertDialog.Builder(getContext());
        tipoDocumentoVenta.setTitle("¿VENTA CON FACTURA?");

        tipoDocumentoVenta.setCancelable(false);
        tipoDocumentoVenta.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cabeceraVM.actualizarTipoDocumentoVenta(Constante.g_const_2);
                //getActivity().finish();
            }
        });
        tipoDocumentoVenta.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cabeceraVM.actualizarTipoDocumentoVenta(Constante.g_const_1);
            }
        });
        tipoDocumentoVenta.show();

        AlertDialog.Builder tipoVenta = new AlertDialog.Builder(getContext());
        tipoVenta.setTitle("¿VENTA AL CREDITO?");

        tipoVenta.setCancelable(false);
        tipoVenta.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cabeceraVM.actualizarTipoVenta(Constante.g_const_2);
            }
        });
        tipoVenta.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cabeceraVM.actualizarTipoVenta(Constante.g_const_1);
            }
        });
        tipoVenta.show();
        //
    }

}
