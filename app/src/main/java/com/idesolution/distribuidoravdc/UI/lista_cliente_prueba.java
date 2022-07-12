package com.idesolution.distribuidoravdc.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.idesolution.distribuidoravdc.Adaptador.ClienteListAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.BD.Entity.Ruta;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ConceptoViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.RutaViewModel;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

public class lista_cliente_prueba extends AppCompatActivity {
    private ClienteViewModel mClienteViewModel;
    private ConceptoViewModel conceptoViewM;
    private RutaViewModel rutaViewM;
    List<Cliente> words_ent;
    List<Concepto> listaConceptos;
    List<Ruta> listaRutas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente_prueba);

        /*RecyclerView recyclerView = findViewById(R.id.rvListaCliente);
        final ClienteListAdapter adapter = new ClienteListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mClienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);

        mClienteViewModel.getmAllClientes().observe(this, clientes -> {
            words_ent = clientes;
            adapter.setClientes(clientes);
        });*/

        /*RecyclerView recyclerView = findViewById(R.id.rvListaCliente);
        final ClienteListAdapter adapter = new ClienteListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rutaViewM = new ViewModelProvider(this).get(RutaViewModel.class);

        rutaViewM.obtenerRutas().observe(this, conceptos -> {
            listaRutas = conceptos;
            adapter.setConceptos(conceptos);
        });*/
    }
}
