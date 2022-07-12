package com.idesolution.distribuidoravdc.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.idesolution.distribuidoravdc.Adaptador.CategoriaListAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.CategoriaEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCliente;
import com.idesolution.distribuidoravdc.BD.Repository.CategoriaViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;
import com.idesolution.distribuidoravdc.UI.registro_cliente;

import java.util.List;


public class f_lista_categoria extends Fragment {

    SearchView searchView;
    private CategoriaListAdapter adapter;
    private RecyclerView recyclerView;
    private CategoriaViewModel mcategoriaViewModel;
    List<CategoriaEntity> list_categorias;
    private Bundle parametroSalida;
    private FragmentTransaction ft;

    //private OnFragmentInteractionListener mListener;



    public f_lista_categoria() {
        // Required empty public constructor
    }

    public static f_lista_categoria newInstance() {
        f_lista_categoria fragment = new f_lista_categoria();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        View v = inflater.inflate(R.layout.fragment_f_lista_categoria, container, false);
        getActivity().setTitle("Productos");
        if (v != null){
            recyclerView = v.findViewById(R.id.f_recyclerviewCategoria);
            adapter = new CategoriaListAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            getActivity().setTitle(R.string.app_name);
        }
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mcategoriaViewModel = new ViewModelProvider(this).get(CategoriaViewModel.class);
        mcategoriaViewModel.getAllCategorias().observe(this, categorias -> {
            list_categorias = categorias;
            adapter.setCategorias(categorias);
        });
/*
        NuevoCliente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentNuevoCliente = new Intent(getContext(), registro_cliente.class);
                intentNuevoCliente.putExtra("parametro_proceso", Constante.g_const_1);
                intentNuevoCliente.putExtra("parametro_transaccion", Constante.g_const_0);
                startActivity(intentNuevoCliente);
            }
        });
*/
        adapter.setOnClickListener(v -> {
            parametroSalida = new Bundle();
            CategoriaEntity fila = list_categorias.get(recyclerView.getChildAdapterPosition(v));

            parametroSalida.putString(Constante.g_const_list_cad_cod_cat, String.valueOf(fila.getNtraCategoria()) );
            parametroSalida.putString(Constante.g_const_list_cad_desc_prod, Constante.G_CONST_VACIO );

            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_principal, new f_lista_producto().newInstance(parametroSalida));
            ft.addToBackStack(null);//permite que no se borre el fragmento anterior
            ft.commit();
        });

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
                enviarDatosProductos(query);

                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                //LOGICA AL PRESIONAR UNA TECLA
                //buscar_cliente(s);
                return false;
            }
        });
    }

    public void enviarDatosProductos(String query){
        parametroSalida = new Bundle();
        parametroSalida.putString(Constante.g_const_list_cad_cod_cat, String.valueOf(Constante.g_const_0) );
        parametroSalida.putString(Constante.g_const_list_cad_desc_prod, query );

        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frame_principal, new f_lista_producto().newInstance(parametroSalida));
        //ft.addToBackStack(null);//permite que no se borre el fragmento anterior
        ft.commit();
    }

    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
