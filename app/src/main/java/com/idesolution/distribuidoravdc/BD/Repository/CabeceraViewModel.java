package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.Cabecera;
import com.idesolution.distribuidoravdc.BD.Entity.Cliente;
import com.idesolution.distribuidoravdc.BD.Entity.Detalle;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCarrito;
import com.idesolution.distribuidoravdc.BD.Entity.FilaDireccion;

import java.util.List;

public class CabeceraViewModel extends AndroidViewModel {
    private CabeceraRepository Repository;

    private LiveData<List<Cabecera>> AllCabeceras;

    public CabeceraViewModel (Application application) {
        super(application);
        Repository = new CabeceraRepository(application);
        AllCabeceras = Repository.getAllCabeceras();
    }

    public void insert(Cabecera cabecera) { Repository.insert(cabecera); }
    //public void insert(Preventa preventa) { Repository.insert(preventa); }
    public void insertDetalle(Detalle detalle, int ntraPromo, int ntraDesc) { Repository.insertDetalle(detalle, ntraPromo, ntraDesc); }
    public void deletePreventaInconclusa() { Repository.deletePreventaInconclusa(); }
    public void quitarProductoCarrito(Integer item) { Repository.quitarProductoCarrito(item); }
    public void salvarCabecera(short tipoVenta, short tipodv, short flagRecargo, double recargo, double igv, double total, String fechaEntrega, String horaEntrega) {
        Repository.salvarCabecera(tipoVenta, tipodv, flagRecargo, recargo, igv, total, fechaEntrega, horaEntrega); }

    public LiveData<Integer> obtenerNtraPreventa(){return Repository.obtenerNtraPreventa();}
    public LiveData<Short> identificarItem(){return Repository.identificarItem();}
    public LiveData<List<FilaCarrito>> obtenerFilaCarrito(){return Repository.obtenerFilaCarrito();}

    public  LiveData<List<Detalle>> getAllDetalles(){
        return Repository.getAllDetalles();
    }
    public  LiveData<Cliente> obtenerNombreCliente(){
        return Repository.obtenerNombreCliente();
    }
    public  LiveData<Cabecera> obtenerDatoVenta(){
        return Repository.obtenerDatoVenta();
    }

    public  LiveData<List<FilaDireccion>> obtenerDirecciones(){
        return Repository.obtenerDirecciones();
    }

    public  LiveData<Integer> buscarItemPromocionDos(int item){
        return Repository.buscarItemPromocionDos(item);
    }

    public LiveData<Double> importeDescuentosItem(){
        return Repository.importeDescuentosItem();
    }

    public void prorrateoDescuentoTotal(int ntraDesc, double impo){
        Repository.prorrateoDescuentoTotal(ntraDesc, impo);
    }

    public LiveData<Detalle> obtenerDetalle(String codProducto){
        return Repository.obtenerDetalle(codProducto);
    }

    public void cargarPreventa(int ntra){
        Repository.cargarPreventa(ntra);
    }

    public LiveData<Double> obtenerDescuentoPreventaItem(int item){
        return Repository.obtenerDescuentoPreventaItem(item);
    }

    public LiveData<Integer> verificarCantidadPuntosEntrega(){
        return Repository.verificarCantidadPuntosEntrega();
    }

    public LiveData<FilaDireccion> obtenerDireccionesPreventaDos(){
        return Repository.obtenerDireccionesPreventaDos();
    }

    public LiveData<Integer> cantidadItems(){
        return Repository.cantidadItems();
    }

    public void actualizarTipoVenta(int tipoVenta){
        Repository.actualizarTipoVenta(tipoVenta);
    }

    public void actualizarTipoDocumentoVenta(int tipoDocumentoVenta){
        Repository.actualizarTipoDocumentoVenta(tipoDocumentoVenta);
    }
}
