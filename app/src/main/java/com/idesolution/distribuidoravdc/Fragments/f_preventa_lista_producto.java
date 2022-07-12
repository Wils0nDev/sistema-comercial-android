package com.idesolution.distribuidoravdc.Fragments;


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
import android.widget.TextView;

import com.idesolution.distribuidoravdc.Adaptador.ProductoListAdapter;
import com.idesolution.distribuidoravdc.Adaptador.ProductoPreventaListAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.TuplaListaProdEntity;
import com.idesolution.distribuidoravdc.BD.Repository.CabeceraViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ProductoViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

import io.reactivex.CompletableOnSubscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_preventa_lista_producto extends Fragment {

    private ProductoViewModel productoVM;
    private ClienteViewModel clienteVM;
    private CabeceraViewModel cabeceraVM;
    private ProductoPreventaListAdapter adapter;
    private RecyclerView recyclerView;
    private List<TuplaListaProdEntity> lista_productos;
    private TextView tvNombreCliente;
    private Bundle parametrosEntrada;
    private Bundle parametroSalida;
    private Bundle parametroSalidaDos;
    private FragmentTransaction ft;
    private Integer ntraCliente;
    private String Nombre;
    private String ApellidoP;
    private String ApellidoM;
    private SearchView searchView;
    private Short tipoPrecio;
    private f_preventa_carrito carrito;
    private int contador;

    private int proceso;

    /*public static f_preventa_lista_producto newInstance(Bundle arguments){
        f_preventa_lista_producto f = new f_preventa_lista_producto();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }*/


    public f_preventa_lista_producto() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        parametrosEntrada = this.getArguments();
        proceso = Constante.g_const_0;
        if(parametrosEntrada != null){
            proceso = parametrosEntrada.getInt("par_pro_");
        }
        contador = Constante.g_const_0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_preventa_lista_producto, container, false);
        if(v != null){
            recyclerView = v.findViewById(R.id.rvListaProductos_pre);
            adapter = new ProductoPreventaListAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            tvNombreCliente = (TextView) v.findViewById(R.id.tvNombreCliente_pre);
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productoVM = new ViewModelProvider(this).get(ProductoViewModel.class);
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        Nombre = null;
        ApellidoP = null;
        ApellidoM = null;
        getActivity().setTitle(R.string.titulo_seleccion_producto);

        cabeceraVM.cantidadItems().observe(this, can -> {
            contador = can;
        });

        //clienteVM.buscarCliente(ntraCliente).observe(this, cliente ->{
        clienteVM.buscarClientePreventa().observe(this, cliente ->{
            if(cliente != null){
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

                tvNombreCliente.setText(Nombre + Constante.g_const_espacio + ApellidoP + Constante.g_const_espacio + ApellidoM);
                tipoPrecio = cliente.getTipoListaPrecio();
                productoVM.listarProductosPreventa(cliente.getTipoListaPrecio(), "").observe(this, productos -> {
                    lista_productos = productos;
                    adapter.setProductos(productos);
                });
            }
        });

        adapter.setOnClickListener(v -> {
            parametroSalida = new Bundle();
            TuplaListaProdEntity p = lista_productos.get(recyclerView.getChildAdapterPosition(v));
            parametroSalida.putString("par_cod_producto", p.getCodProducto());
            parametroSalida.putInt("par_tipo", Constante.g_const_1);
            /*ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_nueva_preventa, new f_preventa_selec_producto().newInstance(parametroSalida));
            ft.addToBackStack(null);
            ft.commit();*/

            f_preventa_selec_producto m = new f_preventa_selec_producto();
            m.setArguments(parametroSalida);
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_nueva_preventa, m);
            ft.addToBackStack(null);
            ft.commit();
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search_productos_preventa, menu);
        MenuItem myActionMenuItem = menu.findItem( R.id.app_search_prod_preventa);
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
                buscar_producto(s);
                return false;
            }
        });
    }

    public void buscar_producto (String cadena){
        //MetodosGlobales.mostrarMensaje(getContext(), tipoPrecio.toString());
        productoVM = new ViewModelProvider(this).get(ProductoViewModel.class);
        productoVM.listarProductosPreventa(tipoPrecio, cadena).observe(this, productos -> {
            adapter.setProductos(productos);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_carrito_preventa:
                if(contador == Constante.g_const_0){
                    MetodosGlobales.mostrarMensaje(getContext(), "Aun no hay productos agregados al carrito");
                }else{
                    if(proceso == Constante.g_const_1){
                        carrito = new f_preventa_carrito();
                        parametroSalidaDos = new Bundle();
                        parametroSalidaDos.putInt("par_proceso_", Constante.g_const_1);
                        parametroSalidaDos.putInt("par_transaccion_", Constante.g_const_0);
                        carrito.setArguments(parametroSalidaDos);
                        ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.frame_nueva_preventa, carrito);
                        ft.addToBackStack(null);
                        ft.commit();
                    }else{
                        getActivity().onBackPressed();
                    }
                }
        }
        return true;
    }

}
