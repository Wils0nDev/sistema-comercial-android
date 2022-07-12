package com.idesolution.distribuidoravdc.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.idesolution.distribuidoravdc.Adaptador.ProductoListAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.CategoriaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProductoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.TuplaListaProdEntity;
import com.idesolution.distribuidoravdc.BD.Repository.CategoriaViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ProductoViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.Entidad.Global;
import com.idesolution.distribuidoravdc.R;
import com.idesolution.distribuidoravdc.UI.ListaVaciaProductoActivity;

import java.util.ArrayList;
import java.util.List;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_lista_producto extends Fragment {

    private SearchView searchView;
    private Bundle parametrosEntrada;
    private Bundle parametroSalida;
    private Integer codCategoria = Constante.g_const_0;
    private String descProducto = Constante.G_CONST_VACIO;
    private ProductoViewModel mProductoViewModel;
    int opcVacio = Constante.g_const_m1;
    private List<TuplaListaProdEntity> productos_ent;
    private FragmentTransaction ft;
    private RecyclerView recyclerView;
    private ProductoListAdapter adapter;

    public f_lista_producto() {
        // Required empty public constructor
    }
    public static f_lista_producto newInstance(Bundle arguments) {
        f_lista_producto fragment = new f_lista_producto();
        if(arguments != null) {
            fragment.setArguments(arguments);
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_lista_producto, container, false);
        if (v != null){
             recyclerView = v.findViewById(R.id.f_recyclerviewProductos);
            adapter = new ProductoListAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            getActivity().setTitle("Productos");
            /*productos_ent = new ArrayList<>();
            adapter.setProductos(productos_ent);*/
        }
        return  v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        parametrosEntrada = this.getArguments();
        codCategoria = Constante.g_const_0;
        Global.g_const_onBsckpress = Constante.g_const_2;
        if(parametrosEntrada != null){
            //codCategoria = Integer.parseInt(parametrosEntrada.getString(Constante.g_const_list_cad_cod_cat));
            //descProducto = parametrosEntrada.getString(Constante.g_const_list_cad_desc_prod);
            codCategoria = parametrosEntrada.getInt("codCategoria");
            descProducto = parametrosEntrada.getString("desCategoria");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        adapter.setOnClickListener(v -> {
            parametroSalida = new Bundle();
            TuplaListaProdEntity fila = productos_ent.get(recyclerView.getChildAdapterPosition(v));
            parametroSalida.putString(Constante.g_const_list_prod_cod_pro, fila.getCodProducto() );

            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_principal, new f_producto_detalle().newInstance(parametroSalida));
            ft.addToBackStack(null);//permite que no se borre el fragmento anterior
            ft.commit();
        });
        //cargar_datos();
        mProductoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);
        mProductoViewModel.findProductosByCodigoId(codCategoria).observe(this, productoEntities -> {
            //Log.i("cont_prod","error: " + productoEntities.size());
            //opcVacio = productoEntities.size();
            //productos_ent = new ArrayList<>();
            //productos_ent = productoEntities;
            //adapter.clear();
            productos_ent = productoEntities;
            adapter.setProductos(productoEntities);
        });
       // Toast.makeText(getContext(), "Productos", Toast.LENGTH_SHORT).show();
        //CargarAyuda(f_lista_producto.this);
    }



    /*public void cargar_datos() {
        adapter.clear();
        //Toast.makeText(getContext(),"codCategoria::: " + codCategoria + " descProducto:: "+descProducto,Toast.LENGTH_SHORT).show();
        if (descProducto.equals(Constante.G_CONST_VACIO) && codCategoria > Constante.g_const_0){
            mProductoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);
            mProductoViewModel.findProductosByCodigoId(codCategoria).observe(this, productoEntities -> {
                Log.i("cont_prod","error: " + productoEntities.size());
                opcVacio = productoEntities.size();
                productos_ent = new ArrayList<>();
                productos_ent = productoEntities;
                adapter.clear();
                adapter.setProductos(productoEntities);
            });
        }else{
           // buscar_producto(descProducto);
            /*
            Toast.makeText(getContext(),"desc::: " + descProducto,Toast.LENGTH_SHORT).show();
            mProductoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);
            mProductoViewModel.findProductosCodDesc(descProducto).observe(this, productoEntities -> {
                adapter.setProductos(productoEntities);
            });


        }
        /*productos_ent = new ArrayList<>();
        adapter.clear();
        adapter.setProductos(productos_ent);
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search_productos, menu);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                //CargarAyuda(f_lista_producto.this);
            }
        });

        MenuItem myActionMenuItem = menu.findItem( R.id.app_search_productos);
        searchView = (SearchView) myActionMenuItem.getActionView();
        //CargarAyuda();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                //buscar_producto(query);
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

    public void buscar_producto(String producto){
        mProductoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);
        mProductoViewModel.findProductosCodDesc(producto).observe(this, productos -> {
            productos_ent = new ArrayList<>();
            productos_ent = productos;
            adapter.setProductos(productos);
        });
    }
/*
    private void CargarAyuda(Fragment fragment){
       // Toast.makeText(getContext(), "Intentando", Toast.LENGTH_SHORT).show();
        new MaterialTapTargetPrompt.Builder(fragment)
                .setPrimaryText("consula")
                .setSecondaryText("consula")
               // .setAnimationInterpolator(FastOutSlowInInterpolator())
                //.setMaxTextWidth(R.dimen.tap_target_menu_max_width)
                .setIcon(R.drawable.ic_search)
                .setTarget(R.id.app_search_productos)
                .show();
    }
    */

}
