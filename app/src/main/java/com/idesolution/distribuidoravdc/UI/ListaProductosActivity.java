package com.idesolution.distribuidoravdc.UI;

import android.content.Intent;
import android.os.Bundle;
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
import com.idesolution.distribuidoravdc.Adaptador.ProductoListAdapter;
import com.idesolution.distribuidoravdc.BD.Entity.TuplaListaProdEntity;
import com.idesolution.distribuidoravdc.BD.Repository.ProductoViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

import java.util.ArrayList;
import java.util.List;

public class ListaProductosActivity extends AppCompatActivity {
    private ProductoViewModel mWordViewModel;
    List<TuplaListaProdEntity> productos_ent;
    SearchView searchView;
    int opcSeleccion = Constante.g_const_0;
    int opcVacio = Constante.g_const_m1;
    BottomNavigationView bottomNavigation;
    private ProductoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        Integer codCategoria = getIntent().getIntExtra(Constante.g_const_list_cad_cod_cat,0);
        String desCategoria = getIntent().getStringExtra(Constante.g_const_list_cad_desc_prod);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewProductos);
         adapter = new ProductoListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Agregar menu action bar
        bottomNavigation = findViewById(R.id.bottomNavigationPrincipal);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        //Opcion de menu con el que se inicializará
        bottomNavigation.setSelectedItemId(R.id.action_productos);

        mWordViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);

        if (desCategoria.equals(Constante.G_CONST_VACIO) && codCategoria > Constante.g_const_0){

            mWordViewModel.findProductosByCodigoId(codCategoria).observe(this, productoEntities -> {
                opcVacio = productoEntities.size();
                /*
                if(opcVacio == Constante.g_const_0){
                    Intent intent = new Intent(getApplicationContext(), ListaVaciaProductoActivity.class);
                    startActivity(intent);
                    return;
                }

                 */
                productos_ent = productoEntities;
                adapter.setProductos(productoEntities);
            });
        }else{
            mWordViewModel.findProductosCodDesc(desCategoria).observe(this, productoEntities -> {
                opcVacio = productoEntities.size();
                /*
                if(opcVacio == Constante.g_const_0){
                    Intent intent = new Intent(getApplicationContext(), ListaVaciaProductoActivity.class);
                    startActivity(intent);
                    return;
                }

                 */
                productos_ent = productoEntities;
                adapter.setProductos(productoEntities);
            });
        }
/*
        if(opcVacio == Constante.g_const_0){
            Intent intent = new Intent(getApplicationContext(), ListaVaciaProductoActivity.class);
            startActivity(intent);
        }

 */

        adapter.setOnClickListener(v -> {

            TuplaListaProdEntity rowCat = productos_ent.get(recyclerView.getChildAdapterPosition(v));
            Intent intent = new Intent(getApplicationContext(), ProductoDetalleActivity.class);
            intent.putExtra(Constante.g_const_list_prod_cod_pro, rowCat.getCodProducto());
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
                            //Intent intent = new Intent(getApplicationContext(), CategoriasListaActivity.class);
                            //startActivity(intent);
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

                Intent intent = new Intent(getApplicationContext(), ListaProductosActivity.class);
                intent.putExtra(Constante.g_const_list_cad_cod_cat, Constante.g_const_0);
                intent.putExtra(Constante.g_const_list_cad_desc_prod, query);
                startActivity(intent);

                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                //Toast.makeText(getApplication(),s + " - mensaje onQueryTextChange ",Toast.LENGTH_SHORT).show();
                // Cuando tecleas en el buscador
                buscar_producto(s);
                return false;
            }
        });
        return true;
    }

    public void buscar_producto(String producto){
        mWordViewModel.findProductosCodDesc(producto).observe(this, productos -> {
            productos_ent = new ArrayList<>();
            productos_ent = productos;
            adapter.setProductos(productos);
        });
    }
}
