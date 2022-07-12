package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.VendedorDao;
import com.idesolution.distribuidoravdc.BD.Entity.VendedorEntity;

import java.util.List;

public class VendedorRepository {
    private VendedorDao mVendedorDao;
    private  LiveData<VendedorEntity> vendedorEntity;
    private LiveData<List<VendedorEntity>> mAllVendedor;

    VendedorRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mVendedorDao = db.vendedorDao();
        mAllVendedor = mVendedorDao.getAllVendedor();
    }

    LiveData<List<VendedorEntity>> getAllPresentaciones() {
        return mAllVendedor;
    }

    LiveData<VendedorEntity> getVendedorID(int id) {
        vendedorEntity = mVendedorDao.findVendedorByUsuario(id);
        return vendedorEntity;
    }

    void insertVendedor(VendedorEntity vendedores) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mVendedorDao.deleteAll();
            mVendedorDao.deleteSequence();
            mVendedorDao.insert(vendedores);
        });
    }

    void deleteAll(){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mVendedorDao.deleteAll();
        });
    }

    public LiveData<String> obtenerDescRuta(int ntraUsuario) {
        return mVendedorDao.obtenerDescRuta(ntraUsuario);
    }

    public LiveData<Integer> obtenerCodRuta(int ntraUsuario) {
        return mVendedorDao.obtenerCodRuta(ntraUsuario);
    }
}
