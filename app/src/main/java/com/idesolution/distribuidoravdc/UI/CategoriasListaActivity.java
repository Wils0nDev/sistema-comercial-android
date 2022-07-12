package com.idesolution.distribuidoravdc.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.idesolution.distribuidoravdc.Adaptador.CategoriaListAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.CategoriaEntity;
import com.idesolution.distribuidoravdc.BD.Repository.CategoriaViewModel;
import com.idesolution.distribuidoravdc.Conexion.MetodosGlobales;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.IO.ApiAdapter;
import com.idesolution.distribuidoravdc.IO.Request.RequestSincronizacion;
import com.idesolution.distribuidoravdc.IO.Response.ResponseRegistroCliente;
import com.idesolution.distribuidoravdc.IO.Response.SincronizacionResponse;
import com.idesolution.distribuidoravdc.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriasListaActivity extends AppCompatActivity implements Callback<SincronizacionResponse> {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = Constante.g_const_1;
    BottomNavigationView bottomNavigation;
    SearchView searchView;

    private CategoriaViewModel mWordViewModel;
    List<CategoriaEntity> words_ent;
    int opcSeleccion = Constante.g_const_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_lista);


        //Agregar menu action bar

        bottomNavigation = findViewById(R.id.bottomNavigationPrincipal);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        bottomNavigation.setSelectedItemId(R.id.action_productos);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewCategoria);
        final CategoriaListAdapter adapter = new CategoriaListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel = new ViewModelProvider(this).get(CategoriaViewModel.class);

        mWordViewModel.getAllCategorias().observe(this, words -> {
            words_ent = words;
            adapter.setCategorias(words);
        });
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter.setOnClickListener(v -> {
            CategoriaEntity rowCat = words_ent.get(recyclerView.getChildAdapterPosition(v));

            Intent intent = new Intent(getApplicationContext(), ListaProductosActivity.class);
            intent.putExtra(Constante.g_const_list_cad_cod_cat, rowCat.getNtraCategoria());
            intent.putExtra(Constante.g_const_list_cad_desc_prod, Constante.G_CONST_VACIO);
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
        //searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Cuando le das enter

                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();

                Intent intent = new Intent(getApplicationContext(), ListaProductosActivity.class);
                intent.putExtra(Constante.g_const_list_cad_cod_cat, Constante.g_const_0);
                intent.putExtra(Constante.g_const_list_cad_desc_prod, query);
                startActivity(intent);

                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // Cuando tecleas en el buscador
                return false;
            }
        });
        return true;
    }

    @Override
    public void onResponse(Call<SincronizacionResponse> call, Response<SincronizacionResponse> response) {
        if(response.isSuccessful()){
            SincronizacionResponse c = response.body();

            if(c.getErrorWebSer().getCodigoErr() == 0) {
                //Log.d("vendeor",c.getResultadoSincro().getDataSincronizacion().getVendedor().getNumeroDocumento());
                //registrarCliente(c.getResultado().getRc().getCodCliente());
                Toast.makeText(this, c.getResultadoSincro().getDataSincronizacion().getVendedor().getNumeroDocumento(), Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Error en el registro del cliente", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Hubo un error en el registro del cliente", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<SincronizacionResponse> call, Throwable t) {
        Toast.makeText(this, "Error de conexion", Toast.LENGTH_SHORT).show();
    }
}
