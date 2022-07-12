package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.VendedorEntity;
import com.idesolution.distribuidoravdc.IO.Response.Vendedor;

import java.util.List;

public class VendedorViewModel extends AndroidViewModel {
    private VendedorRepository mRepository;
    private  LiveData<VendedorEntity> vendedorEntity;
    private LiveData<List<VendedorEntity>> mAllVendedor;

    public VendedorViewModel(Application application) {
        super(application);
        mRepository = new VendedorRepository(application);
        mAllVendedor = mRepository.getAllPresentaciones();
    }

    public LiveData<VendedorEntity> getVendedorID(int id){
        vendedorEntity = mRepository.getVendedorID(id);
        return vendedorEntity;
    }


    public LiveData<List<VendedorEntity>> getAllVendedor() { return mAllVendedor; }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void InsertVendedor(Vendedor vendedor) {
        VendedorEntity vendedorEntity;
        vendedorEntity = new VendedorEntity(vendedor.getNtraUsuario(),vendedor.getCodPerfil(),Short.parseShort(vendedor.getTipoDocumento().toString()),
                vendedor.getNumeroDocumento(),vendedor.getApellidoPaterno(),vendedor.getApellidoMaterno(),vendedor.getNombres(),
                vendedor.getNombreCompleto(),vendedor.getDireccion(),vendedor.getCorreo(),vendedor.getTelefono(),
                vendedor.getCelular(),vendedor.getCodRuta(),vendedor.getDescRuta());
        mRepository.insertVendedor(vendedorEntity);
    }

    public LiveData<String>  obtenerDescRuta(int ntraUsuario) {
        return mRepository.obtenerDescRuta(ntraUsuario);
    }
    public LiveData<Integer> obtenerCodRuta(int ntraUsuario) {
        return mRepository.obtenerCodRuta(ntraUsuario);
    }
}
