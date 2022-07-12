package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.FilaDetallePreventa;
import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDescuentoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDetalleEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaPromocionEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaTotalEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionDescEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.IO.Response.ListDetPreventum;
import com.idesolution.distribuidoravdc.IO.Response.ListPrevDescuento;
import com.idesolution.distribuidoravdc.IO.Response.ListPrevPromocion;
import com.idesolution.distribuidoravdc.IO.Response.ListPreventa;

import java.util.ArrayList;
import java.util.List;

public class PreventaViewModel extends AndroidViewModel {
    private PreventaRepository Repository;
    private LiveData<List<Preventa>> mAllPreventas;
    private  LiveData<List<PreventaTotalEntity>> totalPreventas;


    public PreventaViewModel (Application application) {
        super(application);
        Repository = new PreventaRepository(application);
        mAllPreventas = Repository.getAllPrevetas();
        totalPreventas = Repository.totalPrevetasSincro();
    }

    public void insert(Preventa preventa) { Repository.insert(preventa); }
    public void insertDetalle(PreventaDetalleEntity detalle) { Repository.insertDetalle(detalle); }
    public void deletePreventaInconclusa() { Repository.deletePreventaInconclusa(); }
    public LiveData<Integer> obtenerNtraPreventa(){return Repository.obtenerNtraPreventa();}
    public LiveData<Short> identificarItem(){return Repository.identificarItem();}
    public LiveData<List<PreventaDetalleEntity>> detallePreventa(){return Repository.detallePreventa();}


    public ArrayList<String> obtenerDatos(){
        ArrayList<String> a = new ArrayList<String>();


        return a;
    }

    public LiveData<List<Preventa>> getAllPreventas() { return mAllPreventas; }
    public LiveData<List<PreventaTotalEntity>> totalPrevetasSincro() { return totalPreventas; }

    public void InsertAll(List<ListPreventa> listPreventas) {
        List<Preventa> listaPreventas = new ArrayList<>();
        List<PreventaDetalleEntity> listDetalle = new ArrayList<>();
        List<PreventaPromocionEntity> listPromo = new ArrayList<>();
        List<PreventaDescuentoEntity> listDesc = new ArrayList<>();
        Preventa preventaEntity;
        PreventaDetalleEntity DetalleEntity;
        PreventaPromocionEntity promocionEntity;
        PreventaDescuentoEntity descuentoEntity;
        for (ListPreventa preventa: listPreventas) {
            preventaEntity = new Preventa(preventa.getNtraPreventa(),preventa.getCodCliente(), preventa.getTipoDocumento(),preventa.getNumeroDocumento(),
                    preventa.getCodUsuario(),preventa.getCodPuntoEntrega(),Short.parseShort(preventa.getTipoMoneda().toString()),
                    Short.parseShort(preventa.getTipoVenta().toString()),Short.parseShort(preventa.getTipoDocumentoVenta().toString()),
                    preventa.getFecha(), preventa.getFlagRecargo(),preventa.getRecargo(),preventa.getIgv(),preventa.getIsc(),
                    preventa.getTotal(),Short.parseShort(preventa.getEstado().toString()), Short.parseShort(String.valueOf(Constante.g_const_1))
                    ,Constante.g_s_const_1,"06/03/2020","10:20:20",1) ; //wsanchez

            for (ListDetPreventum detalle: preventa.getListDetPreventa()) {

                DetalleEntity = new PreventaDetalleEntity(preventa.getNtraPreventa(),Short.parseShort(detalle.getItemPreventa().toString()),
                        detalle.getCodPresentacion(),detalle.getCodProducto(),detalle.getCodAlmacen(),detalle.getCantidadPresentacion(),
                        detalle.getCantidadUnidadBase(),detalle.getPrecioVenta(),
                        Short.parseShort(detalle.getTipoProducto().toString()),
                        Short.parseShort("1"));
/*
                DetalleEntity = new PreventaDetalleEntity(preventa.getNtraPreventa(),Short.parseShort(detalle.getItemPreventa().toString()),
                        detalle.getCodPresentacion(),detalle.getCodProducto(),detalle.getCodAlmacen(),detalle.getCantidadPresentacion(),
                        detalle.getCantidadUnidadBase(),detalle.getPrecioVenta(),Short.parseShort(detalle.getTipoProducto().toString())) ;
*/

                listDetalle.add(DetalleEntity);
            }
            for (ListPrevPromocion promo: preventa.getListPrevPromocion()) {

                promocionEntity = new PreventaPromocionEntity(promo.getCodPreventa(),promo.getCodPromocion(),
                        Short.parseShort(promo.getItemPreventa().toString()),
                        Short.parseShort(promo.getItemPromocionado().toString())
                        ,0) ; //wsanchez

                listPromo.add(promocionEntity);
            }
            for (ListPrevDescuento desc: preventa.getListPrevDescuento()) {

                descuentoEntity = new PreventaDescuentoEntity(desc.getCodPreventa(),desc.getCodDescuento(),
                        Short.parseShort(desc.getItemPreventa().toString()),desc.getImporte()
                        ,0) ; //wsanchez

                listDesc.add(descuentoEntity);
            }

            listaPreventas.add(preventaEntity);

        }
        Repository.insertList(listaPreventas,listDetalle,listPromo,listDesc);
    }

    public void insert(Preventa preventa, Context context) {
        Repository.insert(preventa, context);
    }

    public LiveData<List<Preventa>> obtenerPreventas(){return Repository.obtenerPreventas();}

    public LiveData<List<Preventa>> obtenerPreventasPorFecha(String fecha){return Repository.obtenerPreventasPorFecha(fecha);}


    //DETALLE PREVENTA
    public LiveData<Preventa> obtenerPreventasPorNtra(int ntra){
        return Repository.obtenerPreventasPorNtra(ntra);
    }

    public LiveData<List<FilaDetallePreventa>> obtenerDetallePreventa(int ntra){
        return Repository.obtenerDetallePreventa(ntra);
    }

    public LiveData<Integer> cantidadDeProductosPreventa(int ntra){
        return  Repository.cantidadDeProductosPreventa(ntra);
    }

    public LiveData<Integer> cantidadDePromocionesPreventa(int ntra){
        return  Repository.cantidadDePromocionesPreventa(ntra);
    }

    public LiveData<List<PromocionEntity>> obtenerDescripcionPromos(int ntra){
        return  Repository.obtenerDescripcionPromos(ntra);
    }

    public LiveData<List<PromocionDescEntity>> obtenerDescripcionPromosDesc(int ntra){
        return Repository.obtenerDescripcionPromosDesc(ntra);
    }

    public void modificar(int idPreventa, int idPuntoEntrega, Context context) {
        Repository.modificar(idPreventa, idPuntoEntrega, context);
    }

    public LiveData<Integer> obtenerEstadoPreventaNtra(int ntra){
        return Repository.obtenerEstadoPreventaNtra(ntra);
    }

    public void anularPreventaNtra(int ntra, Context context){
        Repository.anularPreventaNtra(ntra, context);
    }


}
