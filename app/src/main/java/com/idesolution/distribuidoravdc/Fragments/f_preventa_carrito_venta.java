package com.idesolution.distribuidoravdc.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idesolution.distribuidoravdc.Adaptador.FilaCarritoListAdapter;
import com.idesolution.distribuidoravdc.BD.Repository.CabeceraViewModel;
import com.idesolution.distribuidoravdc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class f_preventa_carrito_venta extends Fragment {

    private RecyclerView recyclerView;
    private FilaCarritoListAdapter adapter;
    private CabeceraViewModel cabeceraVM;

    public f_preventa_carrito_venta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f_preventa_carrito_venta, container, false);
        /*if(v != null){
            recyclerView = v.findViewById(R.id.rvListaProductos_carrito_pre);
            adapter = new FilaCarritoListAdapter(getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }*/
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cabeceraVM = new ViewModelProvider(this).get(CabeceraViewModel.class);
        cabeceraVM.obtenerFilaCarrito().observe(this, filaCarritos -> {
            //lista_clientes = clientes;
            //adapter.setFilaCarrito(filaCarritos, this, getViewLifecycleOwner());
        });

    }

}
