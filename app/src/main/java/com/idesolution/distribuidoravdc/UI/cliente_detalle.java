package com.idesolution.distribuidoravdc.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.idesolution.distribuidoravdc.BD.Repository.ClienteViewModel;
import com.idesolution.distribuidoravdc.BD.Repository.ConceptoViewModel;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.R;

public class cliente_detalle extends AppCompatActivity {
    Integer ntraCliente;
    private ClienteViewModel clienteVM;
    private ConceptoViewModel conceptoVM;
    private TextView nombre;
    private TextView numDocumento;
    private TextView correo;
    private TextView direccion;
    private TextView telefono;
    private TextView celular;
    private TextView precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detalle);
        this.setTitle(R.string.titulo_detalle_Cliente);
        ntraCliente = getIntent().getIntExtra("num_tra_cliente", Constante.g_const_0);
        nombre = (TextView) findViewById(R.id.tvNombresDetCliente);
        numDocumento = (TextView) findViewById(R.id.tvNumDocDetCliente);
        correo = (TextView) findViewById(R.id.tvCorreoDetCliente);
        direccion = (TextView) findViewById(R.id.tvDireccionDetCliente);
        telefono = (TextView) findViewById(R.id.tvTelefonoDetCliente);
        celular = (TextView) findViewById(R.id.tvCelularDetCliente);
        precio = (TextView) findViewById(R.id.tvTipoPrecioDetCliente);

        cargar_datos();
    }

    public void cargar_datos(){
        clienteVM = new ViewModelProvider(this).get(ClienteViewModel.class);
        conceptoVM = new ViewModelProvider(this).get(ConceptoViewModel.class);
        clienteVM.buscarCliente(ntraCliente).observe(this, cliente -> {
            if(cliente.getTipoDocumento() == Constante.g_const_3){
                nombre.setText(cliente.getRazonSocial());
                numDocumento.setText(cliente.getRuc());
            }else{
                nombre.setText(cliente.getNombres() + Constante.g_const_espacio + cliente.getApellidoPaterno() + Constante.g_const_espacio + cliente.getApellidoMaterno());
                numDocumento.setText(cliente.getNumeroDocumento());
            }
            correo.setText(cliente.getCorreo());
            direccion.setText(cliente.getDireccion());
            telefono.setText(cliente.getTelefono());
            celular.setText(cliente.getCelular());
            conceptoVM.obtenerConcepto(Constante.g_const_7, cliente.getTipoListaPrecio()).observe(this, descripcion -> {
                precio.setText(descripcion);
            });
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnEditar:
                Intent intent = new Intent(getApplicationContext(), registro_cliente.class);
                intent.putExtra("parametro_proceso", Constante.g_const_2);
                intent.putExtra("parametro_transaccion", ntraCliente);
                startActivity(intent);
        }
        return true;
    }

}
