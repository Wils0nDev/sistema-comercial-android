package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.CategoriaDao;
import com.idesolution.distribuidoravdc.BD.Entity.CategoriaEntity;

import java.util.List;

public class CategoriaRepository {
    private CategoriaDao mCategoriaDao;
    private LiveData<List<CategoriaEntity>> mAllCategorias;

    CategoriaEntity categoriaEnt;

    CategoriaRepository(Application application) {
        AppDataBase db = AppDataBase.getDatabase(application);
        mCategoriaDao = db.CategoriaDao();
        mAllCategorias = mCategoriaDao.getAllCategorias();
    }

    LiveData<List<CategoriaEntity>> getAllCategorias() {
        return mAllCategorias;
    }

    void insert(CategoriaEntity categoria) {
        categoriaEnt = categoria;

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mCategoriaDao.insert(categoria);
        });


    }

    void insertList(List<CategoriaEntity> categorias) {

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mCategoriaDao.deleteAll();
            mCategoriaDao.deleteSequence();
            mCategoriaDao.insertList(categorias);
        });


    }

    void deleteAll(){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            //mCategoriaDao.deleteSequence();
            mCategoriaDao.deleteAll();
        });
    }
}
