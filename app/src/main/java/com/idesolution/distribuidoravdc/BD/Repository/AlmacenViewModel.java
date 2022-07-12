package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.AlmacenEntity;
import com.idesolution.distribuidoravdc.IO.Response.ListAlmacene;

import java.util.ArrayList;
import java.util.List;

public class AlmacenViewModel extends AndroidViewModel {
    private AlmacenRepository mRepository;

    private LiveData<List<AlmacenEntity>> mAllAlmacenes;

    public AlmacenViewModel(Application application) {
        super(application);
        mRepository = new AlmacenRepository(application);
        mAllAlmacenes = mRepository.getAllAlmacenes();
    }


    public LiveData<List<AlmacenEntity>> getAllAlmacenes() { return mAllAlmacenes; }


    public void InsertAll(List<ListAlmacene> listAlmacenes) {
        List<AlmacenEntity> lista = new ArrayList<>();
        AlmacenEntity almacenEntity;
        for (ListAlmacene almacen: listAlmacenes) {
            almacenEntity = new AlmacenEntity(almacen.getNtraAlmacen(),almacen.getDescripcion(),almacen.getAbreviatura());

            lista.add(almacenEntity);
        }
        mRepository.insertList(lista);
    }
}
