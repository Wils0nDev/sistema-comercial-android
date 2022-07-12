package com.idesolution.distribuidoravdc.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.idesolution.distribuidoravdc.BD.Repository.CategoriaViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_lista_por_categoria extends Fragment {

    private CategoriaListAdapter adapter;
    private CategoriaViewModel categoriaVM;
    private List<CategoriaEntity> lista_categoria;
    private Bundle parametroSalida;
    private RecyclerView recyclerView;
    private FragmentTransaction ft;
    SearchView searchView;

    public static f_lista_por_categoria newInstance() {
        f_lista_por_categoria fragment = new f_lista_por_categoria();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public f_lista_por_categoria() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_lista_por_categoria, container, false);
        if(v != null){
            recyclerView = v.findViewById(R.id.f_recyclerviewCategoria_h);
            adapter = new CategoriaListAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            getActivity().setTitle(Constante.g_const_producto);
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoriaVM = new ViewModelProvider(this).get(CategoriaViewModel.class);
        categoriaVM.getAllCategorias().observe(this, words -> {
            lista_categoria = words;
            adapter.setCategorias(words);
        });

        adapter.setOnClickListener(v -> {
            parametroSalida = new Bundle();
            CategoriaEntity p = lista_categoria.get(recyclerView.getChildAdapterPosition(v));
            parametroSalida.putInt("codCategoria", p.getNtraCategoria());
            parametroSalida.putString("desCategoria", Constante.G_CONST_VACIO);

            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_principal, new f_lista_producto().newInstance(parametroSalida));
            ft.addToBackStack(null);
            ft.commit();
        });

    }

}
