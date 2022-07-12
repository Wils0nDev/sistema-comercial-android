package com.idesolution.distribuidoravdc.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.idesolution.distribuidoravdc.BD.Entity.ProductoEntity;
import com.idesolution.distribuidoravdc.BD.Repository.ProductoRepository;
import com.idesolution.distribuidoravdc.BD.Repository.ProductoViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

public class ProductoDetalleActivity extends AppCompatActivity {
    private ProductoViewModel mProductoViewModel;
    private ProductoRepository productoRepository;
    private LiveData<ProductoEntity> productoEntity;
    String textListPrecios ;
    String textListStock ;
    String textListPromociones ;
    String textListDescuentos;
    TextView tvDescipcion;
    TextView tvListaPrecios;
    TextView tvListaStockDetProd;
    TextView tvListaPromociones;
    TextView tvListaDescuentos;
    BottomNavigationView bottomNavigation;
    SearchView searchView;
    int opcSeleccion = Constante.g_const_0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_detalle);
        String codCodProducto = getIntent().getStringExtra(Constante.g_const_list_prod_cod_pro);

        textListPrecios = Constante.G_CONST_VACIO;
        textListStock = Constante.G_CONST_VACIO;
        textListPromociones = Constante.G_CONST_VACIO;
        textListDescuentos = Constante.G_CONST_VACIO;
        tvDescipcion = findViewById(R.id.tvdetProdDescripcion);
        tvListaPrecios = findViewById(R.id.tvListaPrecios);
        tvListaStockDetProd = findViewById(R.id.tvListaStockDetProd);
        tvListaPromociones = findViewById(R.id.tvListaPromociones);
        tvListaDescuentos = findViewById(R.id.tvListaDescuentos);
        mProductoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);


        //Agregar menu action bar
        bottomNavigation = findViewById(R.id.bottomNavigationPrincipal);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        //Opcion de menu con el que se inicializará
        bottomNavigation.setSelectedItemId(R.id.action_productos);

        mProductoViewModel.loadProductoDetalleFindCodProd(codCodProducto).observe(this,productoDetalleEntity -> {
            tvDescipcion.setText(productoDetalleEntity.getProducto().getDescripcion());
            for (int i=0;i<productoDetalleEntity.getListPrecios().size();i++){

                textListPrecios = textListPrecios + Constante.g_const_soles + productoDetalleEntity.getListPrecios().get(i).getPrecioVenta() + Constante.g_const_separacion1 +
                        productoDetalleEntity.getListPrecios().get(i).getDescListaPrecio() + "\n";
            }
            tvListaPrecios.setText(textListPrecios);
            for (int i=0;i <productoDetalleEntity.getListInventarios().size();i++){

                textListStock = textListStock + productoDetalleEntity.getListInventarios().get(i).getInventarioAlmacen().getDescripcion() + Constante.g_const_separacion2 +
                                productoDetalleEntity.getListInventarios().get(i).getInventarioEntity().getStock() + "\n";
            }
            tvListaStockDetProd.setText(textListStock);


            for(int i=0; i<productoDetalleEntity.getListPromociones().size();i++){
                textListPromociones = textListPromociones + productoDetalleEntity.getListPromociones().get(i).getPromocionEntity().getDescripcion() + "\n\n";

            }
            tvListaPromociones.setText(textListPromociones);

            for(int i=0; i<productoDetalleEntity.getListDescuentos().size();i++){
                textListDescuentos = textListDescuentos + productoDetalleEntity.getListDescuentos().get(i).getDescuentoEntity().getDescripcion() + "\n\n";

            }
            tvListaDescuentos.setText(textListDescuentos);
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_productos, menu);
        MenuItem myActionMenuItem = menu.findItem( R.id.app_search_productos);
        searchView = (SearchView) myActionMenuItem.getActionView();
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

    //Opciones de menu bar inferior principal
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
                            Toast.makeText(getApplication(),"Menu Cliente",Toast.LENGTH_SHORT).show();
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
}
