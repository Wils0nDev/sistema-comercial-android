package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.Ruta;

import java.util.List;

public class RutaViewModel extends AndroidViewModel {
    private RutaRepository Repository;

    public RutaViewModel (Application application) {
        super(application);
        Repository = new RutaRepository(application);
    }

    public LiveData<List<Ruta>> obtenerRutas() {
        return Repository.obtenerRutas();
    }

}
