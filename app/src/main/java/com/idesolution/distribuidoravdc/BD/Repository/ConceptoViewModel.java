package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.IO.Response.ListConcepto;

import java.util.ArrayList;
import java.util.List;

public class ConceptoViewModel extends AndroidViewModel {
    private ConceptoRepository Repository;
    private LiveData<List<Concepto>> AllConceptos;

    public ConceptoViewModel (Application application) {
        super(application);
        Repository = new ConceptoRepository(application);
        AllConceptos = Repository.getAllConceptos();
    }

    public LiveData<List<Concepto>> getAllConceptos() { return AllConceptos; }
    public LiveData<List<String>> obtenerConceptos(Integer codigo) {
        return Repository.obtenerConceptos(codigo);
    }

    public LiveData<String> obtenerConcepto(Integer codigo, short correlativo) {
        return Repository.obtenerConcepto(codigo, correlativo);
    }
    public void insert(Concepto concepto) { Repository.insert(concepto); }

    public void InsertAll(List<ListConcepto> listConceptos) {
        List<Concepto> lista = new ArrayList<>();
        Concepto conceptoEntity;
        for (ListConcepto concepto: listConceptos) {
            conceptoEntity = new Concepto(concepto.getCodConcepto(),concepto.getCorrelativo(),concepto.getDescripcion());
            lista.add(conceptoEntity);
        }
        Repository.insertList(lista);
    }


    public LiveData<List<Concepto>> getConceptoEntidad(int codConcepto) {
        return Repository.getConceptoEntidad(codConcepto);
    }
}
