package com.idesolution.distribuidoravdc.Fragments;


import android.content.Intent;
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
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.idesolution.distribuidoravdc.Adaptador.ClienteListAdapter;
import com.idesolution.distribuidoravdc.BD.Dao.SessionManager;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCliente;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;
import com.idesolution.distribuidoravdc.UI.registro_cliente;

import java.util.List;

import static android.app.Activity.RESULT_CANCELED;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_lista_cliente extends Fragment {
    private ClienteViewModel clienteVM;
    private ClienteListAdapter adapter;
    private SearchView searchView;
    private List<FilaCliente> lista_clientes;
    private FloatingActionButton NuevoCliente;
    private RecyclerView recyclerView;
    private FragmentTransaction ft;

    private Bundle parametroSalida;

    private final static int RESPUESTA_VISITA_CLIENTE = Constante.g_const_1;
    SessionManager sessionManager;

    public static f_lista_cliente newInstance(Bundle arguments){
        f_lista_cliente f = new f_lista_cliente();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    public f_lista_cliente() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_lista_cliente, container, false);
        if(v != null){
            recyclerView = v.findViewById(R.id.rvListaCliente_cli);
            adapter = new ClienteListAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            sessionManager = new SessionManager(getContext());
            NuevoCliente = (FloatingActionButton) v.findViewById(R.id.btnNuevoCliente_cli);
            getActivity().setTitle("Clientes");
        }

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        resultCode = Constante.g_const_0;
        sessionManager.checkLogin();

        if(resultCode == RESULT_CANCELED){
            sessionManager.checkLogin();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        clienteVM.buscarClientes("").observe(this, clientes -> {
            lista_clientes = clientes;
            adapter.setClientes(clientes);
        });

        NuevoCliente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentNuevoCliente = new Intent(getContext(), registro_cliente.class);
                intentNuevoCliente.putExtra("parametro_proceso", Constante.g_const_1);
                intentNuevoCliente.putExtra("parametro_transaccion", Constante.g_const_0);
                startActivityForResult(intentNuevoCliente, RESPUESTA_VISITA_CLIENTE);
            }
        });

        adapter.setOnClickListener(v -> {
            parametroSalida = new Bundle();
            FilaCliente fc = lista_clientes.get(recyclerView.getChildAdapterPosition(v));
            parametroSalida.putString("num_tra_cliente", fc.getNtra().toString());
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_principal, new f_cliente_detalle().newInstance(parametroSalida));
            ft.addToBackStack(null);
            ft.commit();
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //BUSCAR CLIENTE
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
}
