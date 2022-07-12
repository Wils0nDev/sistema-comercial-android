package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Dao.AppDataBase;
import com.idesolution.distribuidoravdc.BD.Dao.ProductoDao;
import com.idesolution.distribuidoravdc.BD.Dao.ProductoDetalleDao;
import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.BD.Entity.FilaPresentacion;
import com.idesolution.distribuidoravdc.BD.Entity.FilaProducto;
import com.idesolution.distribuidoravdc.BD.Entity.ProductoDetalleEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProductoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.TuplaListaProdEntity;

import java.util.List;

public class ProductoRepository {
    private ProductoDao mproductoDao;
    private ProductoDetalleDao mproductoDetalleDao;
    //private LiveData<List<ProductoEntity>> mallProductos;
    private LiveData<List<TuplaListaProdEntity>> mallProductos;
    private LiveData<ProductoDetalleEntity> mAllProdDet;
    ProductoEntity productoEntity;
    LiveData<ProductoEntity> liveproductoEntity;

    ProductoRepository(Application application){
        AppDataBase db = AppDataBase.getDatabase(application);
        mproductoDao = db.ProductoDao();
        mproductoDetalleDao = db.productoDetalleDao();
        //mallProductos = mproductoDao.getAllProductos();
        mallProductos = mproductoDao.getAllProductosByLista();
    }
/*
    LiveData<List<ProductoEntity>> getAllProductos() {
        return mallProductos;
    }

    LiveData<List<ProductoEntity>> findProductosByCodigoId(int codCategoria) {
        mallProductos = mproductoDao.findProductosByCodigoId(codCategoria);
        return mallProductos;
    }

    LiveData<List<ProductoEntity>> findProductosCodDesc(String descripcion) {
        mallProductos = mproductoDao.findProductosCodDesc(descripcion);
        return mallProductos;
    }
    */
    LiveData<List<TuplaListaProdEntity>> getAllProductos() {
        return mallProductos;
    }

    LiveData<List<TuplaListaProdEntity>> findProductosTuplaByCodigoId(int codCategoria) {
        mallProductos = mproductoDao.findProductosByLista(codCategoria);
        return mallProductos;
    }

    LiveData<List<TuplaListaProdEntity>> findProductosCodDesc(String descripcion) {
        return mproductoDao.findProductosCodDesc(descripcion);
    }

    LiveData<ProductoEntity> findProductoById(String id){
        liveproductoEntity = mproductoDao.findProductoById(id);
        return  liveproductoEntity;
    }

    LiveData<ProductoDetalleEntity> loadProductoDetalleFindCodProd(String codDetalle){
        mAllProdDet = mproductoDetalleDao.loadProductoDetalleFindCodProd(codDetalle);
        return  mAllProdDet;
    }



    void insert(ProductoEntity producto) {
        productoEntity = producto;

        AppDataBase.databaseWriteExecutor.execute(() -> {
            mproductoDao.insert(productoEntity);
        });


    }

    LiveData<List<TuplaListaProdEntity>> listarProductosPreventa(Short tipoPrecio, String descripcion){
        return mproductoDao.listarProductosPreventa(tipoPrecio, descripcion);
    }

    LiveData<FilaProducto> buscarProductoSeleccion(Short tipoPrecio, String codProducto){
        return mproductoDao.buscarProductoSeleccion(tipoPrecio, codProducto);
    }

    LiveData<List<Concepto>> obtenerPresentaciones(String codProducto){
        return mproductoDao.obtenerPresentaciones(codProducto);
    }

    LiveData<List<FilaPresentacion>> obtenerPresentacionesCombo(String codProducto){
        return mproductoDao.obtenerPresentacionesCombo(codProducto);
    }

    public void insertList(List<ProductoEntity> lista) {
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mproductoDao.deleteAll();
            mproductoDao.deleteSequence();
            mproductoDao.insertList(lista);
        });
    }
}
