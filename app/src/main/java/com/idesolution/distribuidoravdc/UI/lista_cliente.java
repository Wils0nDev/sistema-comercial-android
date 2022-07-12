package com.idesolution.distribuidoravdc.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.idesolution.distribuidoravdc.Adaptador.ClienteListAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCliente;
import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class lista_cliente extends AppCompatActivity {
    private ClienteViewModel clienteVM;
    ClienteListAdapter adapter;
    SearchView searchView;
    List<FilaCliente> lista_clientes;
    BottomNavigationView bottomNavigation;
    int opcSeleccion = Constante.g_const_0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);
        this.setTitle("Clientes");
        FloatingActionButton NuevoCliente = (FloatingActionButton) findViewById(R.id.btnNuevoCliente);

        RecyclerView recyclerView = findViewById(R.id.rvListaClientes);
        adapter = new ClienteListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bottomNavigation = findViewById(R.id.bottomNavigationPrincipal);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        bottomNavigation.setSelectedItemId(R.id.action_productos);

        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        clienteVM.buscarClientes("").observe(this, clientes -> {
            lista_clientes = clientes;
            adapter.setClientes(clientes);
        });

        NuevoCliente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentNuevoCliente = new Intent(getApplicationContext(), registro_cliente.class);
                intentNuevoCliente.putExtra("parametro_proceso", Constante.g_const_1);
                intentNuevoCliente.putExtra("parametro_transaccion", Constante.g_const_0);
                //intentNuevoCliente.putExtra("intent_proceso", Constante.g_const_1);
                startActivity(intentNuevoCliente);
            }
        });

        adapter.setOnClickListener(v -> {
            FilaCliente fc = lista_clientes.get(recyclerView.getChildAdapterPosition(v));
            //MetodosGlobales.mostrarMensaje(getApplicationContext(), "HOLAA");
            MetodosGlobales.mostrarMensaje(getApplicationContext(), fc.getFlag().toString());
            Intent intent = new Intent(getApplicationContext(), cliente_detalle.class);
            intent.putExtra("num_tra_cliente", fc.getNtra());
            startActivity(intent);
        });
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            item -> {
                switch (item.getItemId()) {
                    case R.id.action_productos:
                        if(opcSeleccion == Constante.g_const_0){
                            opcSeleccion = Constante.g_const_1;
                        }else{
                            Intent intent = new Intent(getApplicationContext(), CategoriasListaActivity.class);
                            startActivity(intent);
                        }
                        return true;
                    case R.id.action_clientes:
                        if(opcSeleccion == Constante.g_const_0){
                            opcSeleccion = Constante.g_const_1;
                        }else{
                            Intent intent = new Intent(getApplicationContext(), lista_cliente.class);
                            startActivity(intent);
                        }
                        return true;
                    case R.id.action_preventa:
                        if(opcSeleccion == Constante.g_const_0){
                            Toast.makeText(getApplication(),"Menu Preventa",Toast.LENGTH_SHORT).show();
                            opcSeleccion = Constante.g_const_1;
                        }else{
                            //Intent intent = new Intent(getApplicationContext(), CategoriasListaActivity.class);
                            //startActivity(intent);
                        }
                        return true;
                    case R.id.action_menu:
                        if(opcSeleccion == Constante.g_const_0){
                            Toast.makeText(getApplication(),"Menu menu",Toast.LENGTH_SHORT).show();
                            opcSeleccion = Constante.g_const_1;
                        }else{
                            //Intent intent = new Intent(getApplicationContext(), CategoriasListaActivity.class);
                            //startActivity(intent);
                        }
                        return true;
                    case R.id.action_perfil:
                        if(opcSeleccion == Constante.g_const_0){
                            Toast.makeText(getApplication(),"Menu perfil",Toast.LENGTH_SHORT).show();
                            opcSeleccion = Constante.g_const_1;
                        }else{
                            //Intent intent = new Intent(getApplicationContext(), CategoriasListaActivity.class);
                            //startActivity(intent);
                        }
                        return true;
                }
                return false;
            };

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
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

                //LOGICA AL PRESIONAR ENTER

                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                //LOGICA AL PRESIONAR UNA TECLA
                buscar_cliente(s);
                return false;
            }
        });
        return true;
    }

    public void buscar_cliente (String cadena){
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        clienteVM.buscarClientes(cadena).observe(this, clientes -> {
            adapter.setClientes(clientes);
        });
    }
}
