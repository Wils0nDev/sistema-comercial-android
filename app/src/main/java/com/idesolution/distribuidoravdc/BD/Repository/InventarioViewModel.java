package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.InventarioEntity;
import com.idesolution.distribuidoravdc.IO.Response.ListInventario;

import java.util.ArrayList;
import java.util.List;

public class InventarioViewModel extends AndroidViewModel {
    private InventarioRepository mRepository;

    private LiveData<List<InventarioEntity>> mAllInventarios;

    public InventarioViewModel(Application application) {
        super(application);
        mRepository = new InventarioRepository(application);
        mAllInventarios = mRepository.getAllInventarios();
    }


    public LiveData<List<InventarioEntity>> getAllInventarios() { return mAllInventarios; }


    public void InsertAll(List<ListInventario> listInventarios) {
        List<InventarioEntity> lista = new ArrayList<>();
        InventarioEntity inventarioEntity;
        for (ListInventario inventario: listInventarios) {
            inventarioEntity = new InventarioEntity(inventario.getNtraInventario(),inventario.getCodAlmacen(),
                                    inventario.getCodProducto(),inventario.getStock());
            lista.add(inventarioEntity);

        }
        mRepository.insertList(lista);
    }
}
