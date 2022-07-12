package com.idesolution.distribuidoravdc.BD.Repository;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.BD.Entity.FilaPresentacion;
import com.idesolution.distribuidoravdc.BD.Entity.FilaProducto;
import com.idesolution.distribuidoravdc.BD.Entity.ProductoDetalleEntity;
import com.idesolution.distribuidoravdc.BD.Entity.ProductoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.TuplaListaProdEntity;
import com.idesolution.distribuidoravdc.Entidad.Constante;
import com.idesolution.distribuidoravdc.IO.Response.ListProducto;

import java.util.ArrayList;
import java.util.List;

public class ProductoViewModel extends AndroidViewModel {
    private ProductoRepository mRepository;

    //private LiveData<List<ProductoEntity>> mAllProductos;
    private LiveData<List<TuplaListaProdEntity>> mAllProductos;
    private LiveData<ProductoEntity> mProductos;
    //,Integer codigo, String descripcion
    public ProductoViewModel(Application application) {
        super(application);
        mRepository = new ProductoRepository(application);
        mAllProductos = mRepository.getAllProductos();
    }
/*
    public LiveData<List<ProductoEntity>> getmAllProductos() { return mAllProductos; }
    public LiveData<List<ProductoEntity>> findProductosByCodigoId(int codCategoria)
    {
        return mRepository.findProductosByCodigoId(codCategoria);
    }

    public LiveData<List<ProductoEntity>> findProductosCodDesc(String descripcion)
    {
        return mRepository.findProductosCodDesc(descripcion);
    }
    */
    public LiveData<List<TuplaListaProdEntity>> getmAllProductos() { return mAllProductos; }
    public LiveData<List<TuplaListaProdEntity>> findProductosByCodigoId(int codCategoria)
    {
        return mRepository.findProductosTuplaByCodigoId(codCategoria);
    }

    public LiveData<List<TuplaListaProdEntity>> findProductosCodDesc(String descripcion)
    {
        return mRepository.findProductosCodDesc(descripcion);
    }

    public LiveData<ProductoEntity> findProductoById(String id){
        return mRepository.findProductoById(id);
    }

    public LiveData<ProductoDetalleEntity> loadProductoDetalleFindCodProd(String codProducto){
        return mRepository.loadProductoDetalleFindCodProd(codProducto);
    }

    public void insert(ProductoEntity producto) { mRepository.insert(producto); }

    public LiveData<List<TuplaListaProdEntity>> listarProductosPreventa(Short tipoPrecio, String descripcion){return mRepository.listarProductosPreventa(tipoPrecio, descripcion);}

    public LiveData<FilaProducto> buscarProductoSeleccion(Short tipoPrecio, String codProducto){return mRepository.buscarProductoSeleccion(tipoPrecio, codProducto);}

    public LiveData<List<Concepto>> obtenerPresentaciones(String codProducto){return mRepository.obtenerPresentaciones(codProducto);}

    public LiveData<List<FilaPresentacion>> obtenerPresentacionesCombo(String codProducto){return mRepository.obtenerPresentacionesCombo(codProducto);}

    public void InsertAll(List<ListProducto> listProductos) {
        List<ProductoEntity> lista = new ArrayList<>();
        ProductoEntity productoEntity;
        for (ListProducto producto: listProductos) {
            productoEntity = new ProductoEntity(producto.getCodProducto(),producto.getDescripcion(),producto.getCodCategoria(),
                    producto.getCodUnidadBaseventa(),producto.getCodDes(), Constante.g_const_0,
                    Constante.g_const_0,Constante.g_const_0,producto.getTipoProducto());
            lista.add(productoEntity);

        }
        mRepository.insertList(lista);
    }
}
