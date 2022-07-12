package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.DepartamentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DistritoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProvinciaEntity;

import java.util.List;

public class UbigeoViewModel extends AndroidViewModel {

    private UbigeoRepository repository;
    private LiveData<List<DepartamentoEntity>> mAllDepartamentos;
    private LiveData<List<ProvinciaEntity>> mAllProvincias;

    public UbigeoViewModel(Application application) {
        super(application);
        repository = new UbigeoRepository(application);
        mAllDepartamentos = repository.getmAllDepartamentos();

    }

    public LiveData<List<DepartamentoEntity>> getAllDepartamentos() { return mAllDepartamentos; }
    public LiveData<List<ProvinciaEntity>> getAllProvincias(String codDep) {
        return repository.getAllProvincias(codDep);
    }
    public LiveData<List<DistritoEntity>> getAllDistritos(String codDep, String codProv) {
        return repository.getAllDistritos(codDep,codProv);
    }

}
