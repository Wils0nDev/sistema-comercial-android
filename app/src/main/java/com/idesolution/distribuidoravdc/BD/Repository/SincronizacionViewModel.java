package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;

public class SincronizacionViewModel extends AndroidViewModel{
    private SincronizacionRepository repository;

    public SincronizacionViewModel(Application application) {
        super(application);
        repository = new SincronizacionRepository(application);
    }


    public void SincronizarDataCompleta(int codVendedor,String codUbigeo, Context context,int respuesta) {
        repository.SincronizarDataCompleta(codVendedor,codUbigeo,context,respuesta);
    }
}
