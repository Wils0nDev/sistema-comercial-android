package com.idesolution.distribuidoravdc.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

public class ListaVaciaProductoActivity extends AppCompatActivity {
    SearchView searchView;
    BottomNavigationView bottomNavigation;
    int opcSeleccion = Constante.g_const_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vacia_producto);

        bottomNavigation = findViewById(R.id.bottomNavigationPrincipal);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        //Opcion de menu con el que se inicializará
        bottomNavigation.setSelectedItemId(R.id.action_productos);
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
        searchView.onActionViewExpanded();
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
                //Toast.makeText(getApplication(),s + " - mensaje onQueryTextChange ",Toast.LENGTH_SHORT).show();
                // Cuando tecleas en el buscador
                return false;
            }
        });
        return true;
    }
}
