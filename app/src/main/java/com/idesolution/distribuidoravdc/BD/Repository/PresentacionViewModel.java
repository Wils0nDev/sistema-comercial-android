package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.PresentacionEntity;
import com.idesolution.distribuidoravdc.IO.Response.ListPresentacione;

import java.util.ArrayList;
import java.util.List;

public class PresentacionViewModel extends AndroidViewModel {
    private PresentacionRepository mRepository;

    private LiveData<List<PresentacionEntity>> mAllPresentaciones;

    public PresentacionViewModel(Application application) {
        super(application);
        mRepository = new PresentacionRepository(application);
        mAllPresentaciones = mRepository.getAllPresentaciones();
    }


    public LiveData<List<PresentacionEntity>> getAllPresentaciones() { return mAllPresentaciones; }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void InsertAll(List<ListPresentacione> listPresentaciones) {
        List<PresentacionEntity> lista = new ArrayList<>();
        PresentacionEntity presentacionEntity;
        for (ListPresentacione presentacion: listPresentaciones) {
            presentacionEntity = new PresentacionEntity(presentacion.getCodProducto(),presentacion.getCodPresentancion(),
                    presentacion.getCantidadUnidadBase());
            lista.add(presentacionEntity);
        }
        mRepository.insertList(lista);
    }

    public LiveData<Integer> obtenerCantidadEnUnidadesBase(String codProducto, Integer codPresentacion) {
        return mRepository.obtenerCantidadEnUnidadesBase(codProducto, codPresentacion);
    }
}
