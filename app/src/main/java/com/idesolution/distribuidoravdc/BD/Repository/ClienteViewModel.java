package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.ClienteSpinnerEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCliente;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionEntity;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.IO.Response.ListCliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteViewModel extends AndroidViewModel {
    private ClienteRepository mRepository;

    private LiveData<List<Cliente>> mAllClientes;
    private LiveData<List<Cliente>> mAllClientesSinc;

    public ClienteViewModel (Application application) {
        super(application);
        mRepository = new ClienteRepository(application);
        mAllClientes = mRepository.getmAllClientes();
        mAllClientesSinc = mRepository.getmAllClientesSinc();
    }

    public LiveData<List<Cliente>> getmAllClientes() { return mAllClientes; }
    public LiveData<List<Cliente>> getmAllClientesSinc() { return mAllClientesSinc; }

    public void insert(Cliente cliente, LocalizacionEntity localizacion, Context context) { mRepository.insert(cliente,localizacion, context); }

    public void update(Integer ntra, String razonSocial, String nombres, String apePaterno, String apeMaterno, String direccion, String correo, String telefono, String celular, Integer flag) {
        mRepository.update(ntra, razonSocial, nombres, apePaterno, apeMaterno, direccion, correo, telefono, celular, flag);
    }

    public LiveData<Integer> buscarNumeroDocumento(String numeroDoc){return mRepository.buscarNumeroDocumento(numeroDoc);}

    public LiveData<List<FilaCliente>> getListaClientes(){return mRepository.getListaClientes();}

    public LiveData<List<FilaCliente>> buscarClientes(String cadena){return mRepository.buscarClientes(cadena);}

    public LiveData<Cliente> buscarCliente(Integer transaccion){return mRepository.buscarCliente(transaccion);}

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void InsertAll(List<ListCliente> listClientes) {
        List<Cliente> lista = new ArrayList<>();
        Cliente clienteEntity;

        for (ListCliente cliente: listClientes) {
            clienteEntity = new Cliente(cliente.getCodPersona(),Short.parseShort(cliente.getTipoPersona().toString()),Short.parseShort(cliente.getTipoDocumento().toString()),
                    cliente.getNumeroDocumento(),cliente.getRuc(),cliente.getRazonSocial(),cliente.getNombres(),
                    cliente.getApellidoPaterno(),cliente.getApellidoMaterno(),cliente.getDireccion(),
                    cliente.getCorreo(),cliente.getTelefono(),cliente.getCelular(),Short.parseShort(cliente.getFrecuenciaCliente().toString()),
                    Short.parseShort(cliente.getTipoListaPrecio().toString()),Short.parseShort(cliente.getCodRuta().toString()),
                    cliente.getNombreCodigo(), Constante.g_const_1,cliente.getCodUbigeo(),cliente.getClasificacionCliente());
            lista.add(clienteEntity);

        }

        mRepository.insertList(lista);
    }

    public LiveData<Cliente> buscarClientePreventa(){return mRepository.buscarClientePreventa();}

    public LiveData<List<ClienteSpinnerEntity>> obtenerCliente(){ return mRepository.obtenerCliente();}

    public LiveData<Integer> cantidadClientes(){
        return mRepository.cantidadClientes();
    };

    //DETALLE PREVENTA
    public LiveData<String> obtenerNombreCliente(int tipo, String cadena){
        return mRepository.obtenerNombreCliente(tipo, cadena);
    }

    public void consultaReniec(String numDocumento, Context context) {
        mRepository.consultaReniec(numDocumento,context);
    }
}
