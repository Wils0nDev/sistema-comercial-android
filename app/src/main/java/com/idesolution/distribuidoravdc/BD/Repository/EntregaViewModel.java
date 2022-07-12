package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.EntregaEntity;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.IO.Response.ListEntrega;

import java.util.ArrayList;
import java.util.List;

public class EntregaViewModel extends AndroidViewModel {
    private EntregaRepository mRepository;

    private LiveData<List<EntregaEntity>> mAllEntregas;
    private LiveData<List<EntregaEntity>> mAllEntregasSinc;

    public EntregaViewModel(Application application) {
        super(application);
        mRepository = new EntregaRepository(application);
        mAllEntregas = mRepository.getAllEntregas();
        mAllEntregasSinc = mRepository.getAllEntregasSinc();
    }


    public LiveData<List<EntregaEntity>> getAllEntregas() { return mAllEntregas; }
    public LiveData<List<EntregaEntity>> getAllEntregasSinc() { return mAllEntregasSinc; }

    public void deleteAll(){
        mRepository.deleteAll();
    }

    public void InsertAll(List<ListEntrega> listEntregas) {
        List<EntregaEntity> lista = new ArrayList<>();
        EntregaEntity entregaEntity;
        for (ListEntrega entrega: listEntregas) {
            entregaEntity = new EntregaEntity(entrega.getNtraPuntoEntrega(),entrega.getCoordenadaX(),entrega.getCoordenadaY(),
                                entrega.getDireccion(),entrega.getReferencia(),entrega.getOrdenEntrega(), entrega.getTipoDocumento(), entrega.getNumeroDocumento(),entrega.getCodPersona(), Constante.g_const_1);
            lista.add(entregaEntity);

        }
        mRepository.insertList(lista);
    }

    public void insert(EntregaEntity entregaEntity){
        mRepository.insert(entregaEntity);
    }
}
