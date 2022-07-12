package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.DescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.DetalleDescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDescuento;
import com.idesolution.distribuidoravdc.IO.Response.ListDescuento;
import com.idesolution.distribuidoravdc.IO.Response.ListDetalleDescuento;

import java.util.ArrayList;
import java.util.List;

public class DescuentoViewModel extends AndroidViewModel {
    private DescuentoRepository mRepository;

    private LiveData<List<DescuentoEntity>> mAllDescuentos;
    private LiveData<List<DetalleDescuentoEntity>> mAllDetallesDescuentos;

    public DescuentoViewModel(Application application) {
        super(application);
        mRepository = new DescuentoRepository(application);
        mAllDescuentos = mRepository.getAllDescuento();
        mAllDetallesDescuentos = mRepository.getAllDetalleDescuento();
    }


    public LiveData<List<DescuentoEntity>> getAllDescuentos() { return mAllDescuentos; }
    public LiveData<List<DetalleDescuentoEntity>> getAllDetalleDescuentos() { return mAllDetallesDescuentos; }


    public void InsertAll(List<ListDescuento> listDescuentos) {
        List<DescuentoEntity> listDescuentosEntity = new ArrayList<>();
        List<DetalleDescuentoEntity> listaDetalleDescuentos = new ArrayList<>();
        DescuentoEntity descuentoEntity;
        DetalleDescuentoEntity detalleDescuentoEntity;
        for (ListDescuento descuento: listDescuentos) {
            descuentoEntity = new DescuentoEntity(descuento.getNtraDescuento(),descuento.getNtraFlag(),descuento.getFlag(),
                                    descuento.getDescDesc(),descuento.getValorInicial(),descuento.getValorFinal(),
                                    descuento.getDetalle(),Short.parseShort(descuento.getEstado().toString()),Short.parseShort(descuento.getTipoDescuento().toString()));

            for (ListDetalleDescuento detDesc: descuento.getListDetalleDescuento()) {

                detalleDescuentoEntity = new DetalleDescuentoEntity(descuento.getNtraDescuento(),detDesc.getNtraFlag(),
                        detDesc.getFlag(),detDesc.getDescripcion(),detDesc.getValorEntero1(),
                        detDesc.getValorEntero2(),detDesc.getValorMoneda1(),detDesc.getValorMoneda2(),
                        detDesc.getValorCadena1(),detDesc.getValorCadena2(),detDesc.getValorFecha1(),
                        detDesc.getValorFecha2(),Short.parseShort(detDesc.getEstado().toString()));
                listaDetalleDescuentos.add(detalleDescuentoEntity);

            }
            listDescuentosEntity.add(descuentoEntity);

        }
        mRepository.insertList(listDescuentosEntity,listaDetalleDescuentos);
    }

    public void obtenerDescuentos(String codProducto, int flag) {
        mRepository.obtenerDescuentos(codProducto, flag);
    }

    public void deleteAllFilaDescuento(){
        mRepository.deleteAllFilaDescuento();
    }

    public LiveData<List<DescuentoEntity>> descuentosHabilitados(){
        return mRepository.descuentosHabilitados();
    }

    public LiveData<List<Integer>> descuentosHabilitadosDos(){
        return mRepository.descuentosHabilitadosDos();
    }

    public LiveData<DescuentoEntity> obtener_flag_descuento(int ntra, int flag) {
        return mRepository.obtener_flag_descuento(ntra, flag);
    }

    //DETALLE PREVENTA

    public LiveData<Double> obtenerDescuentosPreventa(int ntra){
        return mRepository.obtenerDescuentosPreventa(ntra);
    }

    public LiveData<Double> obtenerDescuentoPreventaItem(int ntra, int item){
        return mRepository.obtenerDescuentoPreventaItem(ntra, item);
    }
}
