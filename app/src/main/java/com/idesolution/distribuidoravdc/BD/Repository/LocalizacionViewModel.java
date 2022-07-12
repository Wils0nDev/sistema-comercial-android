package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionClienteEntity;
import com.idesolution.distribuidoravdc.BD.Entity.LocalizacionEntity;

import java.util.List;

public class LocalizacionViewModel  extends AndroidViewModel {
    private LocalizacionRepository localRepository;


    public LocalizacionViewModel( Application application) {
        super(application);
        localRepository = new LocalizacionRepository(application);
    }

    public LiveData<List<LocalizacionClienteEntity>> obtenerCoordenadas() { return localRepository.obtenerCoordenadas(); }
}
