package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.PrecioEntity;
import com.idesolution.distribuidoravdc.IO.Response.ListPrecio;

import java.util.ArrayList;
import java.util.List;

public class PrecioViewModel extends AndroidViewModel {
    private PrecioRepository mRepository;

    private LiveData<List<PrecioEntity>> mAllPrecios;

    public PrecioViewModel(Application application) {
        super(application);
        mRepository = new PrecioRepository(application);
        mAllPrecios = mRepository.getAllPrecios();
    }


    public LiveData<List<PrecioEntity>> getAllPrecios() { return mAllPrecios; }


    public void InsertAll(List<ListPrecio> listPrecios) {
        List<PrecioEntity> lista = new ArrayList<>();
        PrecioEntity precioEntity;
        for (ListPrecio precio: listPrecios) {
            precioEntity = new PrecioEntity(precio.getNtraPrecio(),precio.getCodProducto(),precio.getTipoListaPrecio(),
                                precio.getPrecioVenta(),precio.getDescripcion());
            lista.add(precioEntity);

        }
        mRepository.insertList(lista);
    }
}
