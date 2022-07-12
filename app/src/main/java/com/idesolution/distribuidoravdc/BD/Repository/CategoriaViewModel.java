package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.CategoriaEntity;
import com.idesolution.distribuidoravdc.IO.Response.ListCategoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaViewModel extends AndroidViewModel {
    private CategoriaRepository mRepository;

    private LiveData<List<CategoriaEntity>> mAllCategorias;

    public CategoriaViewModel (Application application) {
        super(application);
        mRepository = new CategoriaRepository(application);
        mAllCategorias = mRepository.getAllCategorias();
    }

    public LiveData<List<CategoriaEntity>> getAllCategorias() { return mAllCategorias; }

    public void insert(CategoriaEntity categoria) { mRepository.insert(categoria); }
    public void insertList(List<CategoriaEntity> categoria) { mRepository.insertList(categoria); }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void InsertAll(List<ListCategoria> listCategorias) {
        List<CategoriaEntity> lista = new ArrayList<>();
        CategoriaEntity categoriaEntity;
        for (ListCategoria categoria: listCategorias) {
            categoriaEntity = new CategoriaEntity(categoria.getNtraCategoria(),categoria.getDescripcion());
            lista.add(categoriaEntity);

        }
        mRepository.insertList(lista);
    }
}
