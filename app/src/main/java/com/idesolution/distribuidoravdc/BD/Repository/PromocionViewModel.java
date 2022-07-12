package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.idesolution.distribuidoravdc.BD.Entity.DetallePromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.FilaPromocion;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.filaPromocionDos;
import com.idesolution.distribuidoravdc.IO.Response.ListDetallePromocion;
import com.idesolution.distribuidoravdc.IO.Response.ListPromocione;

import java.util.ArrayList;
import java.util.List;

public class PromocionViewModel extends AndroidViewModel {
    private PromocionRepository mRepository;

    private LiveData<List<PromocionEntity>> mAllPromociones;
    private LiveData<List<DetallePromocionEntity>> mAllDetallesPromocion;

    public PromocionViewModel(Application application) {
        super(application);
        mRepository = new PromocionRepository(application);
        mAllPromociones = mRepository.getAllPromocion();
        mAllDetallesPromocion = mRepository.getAllDetallePromocion();
    }


    public LiveData<List<PromocionEntity>> getAllPromociones() { return mAllPromociones; }
    public LiveData<List<DetallePromocionEntity>> getAllDetallePromociones() { return mAllDetallesPromocion; }


    public void obtenerPromociones(String codProducto, int cantidad, double importe) {
        mRepository.obtenerPromociones(codProducto, cantidad, importe);
    }

    public LiveData<List<FilaPromocion>> obtenerPromos() {
        return mRepository.obtenerPromos();
    }

    public LiveData<List<filaPromocionDos>> promocionesHabilitadas() {
        return mRepository.promocionesHabilitadas();
    }

    public LiveData<PromocionEntity> obtener_flag_promocion(int ntra, int flag) {
        return mRepository.obtener_flag_promocion(ntra, flag);
    }

    public void deleteAllFilaPromocion(){
        mRepository.deleteAllFilaPromocion();
    }
    public void InsertAll(List<ListPromocione> listPromociones) {
        List<PromocionEntity> listaPromociones = new ArrayList<>();
        List<DetallePromocionEntity> listaDetallePromociones = new ArrayList<>();
        PromocionEntity presentacionEntity;
        DetallePromocionEntity detallePromocionEntity;
        for (ListPromocione promocion: listPromociones) {
            presentacionEntity = new PromocionEntity(promocion.getNtraPromocion(),promocion.getNtraFlag(),promocion.getFlag(),promocion.getDescPromo(),
                                    promocion.getValorInicial(),promocion.getValorFinal(),
                                    promocion.getDetalle(),Short.parseShort(promocion.getEstado().toString()));

            for (ListDetallePromocion detPromo: promocion.getListDetallePromocion()) {
                detallePromocionEntity = new DetallePromocionEntity(promocion.getNtraPromocion(),detPromo.getNtraFlag(),
                                                detPromo.getFlag(),detPromo.getDescripcion(),detPromo.getValorEntero1(),
                                                detPromo.getValorEntero2(),detPromo.getValorMoneda1(),detPromo.getValorMoneda2(),
                                                detPromo.getValorCadena1(),detPromo.getValorCadena2(),detPromo.getValorFecha1(),
                                                detPromo.getValorFecha2(),Short.parseShort(detPromo.getEstado().toString()));
                listaDetallePromociones.add(detallePromocionEntity);

            }
            listaPromociones.add(presentacionEntity);

        }
        mRepository.insertList(listaPromociones,listaDetallePromociones);
    }
}
