package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.Concepto;
import com.idesolution.distribuidoravdc.BD.Entity.FilaPresentacion;
import com.idesolution.distribuidoravdc.BD.Entity.FilaProducto;
import com.idesolution.distribuidoravdc.BD.Entity.ProductoEntity;
import com.idesolution.distribuidoravdc.BD.Entity.TuplaListaProdEntity;

import java.util.List;

@Dao
public interface ProductoDao {

    @Query("SELECT * FROM tblProducto WHERE codProducto = :codigo LIMIT 1")
    LiveData<ProductoEntity> findProductoById(String codigo);

    @Query("SELECT DISTINCT pro.codProducto,pro.descripcion,pro.precio as 'precioVenta',inv.stock FROM tblProducto as pro " +
            "INNER JOIN tblInventario inv ON inv.codProducto = pro.codProducto " +
            "INNER JOIN tblAlmacen alm ON alm.ntraAlmacen=inv.codAlamacen "+
            "INNER JOIN tblPrecio pre ON pre.codProducto = pro.codProducto "+
            " WHERE pro.codCategoria = :codCat AND alm.abreviatura= 'ALMP' AND inv.stock > 0 LIMIT 15")
    LiveData<List<TuplaListaProdEntity>> findProductosByLista(int codCat);

    @Query("SELECT pro.codProducto,pro.descripcion,pre.precioVenta,pro.stock FROM tblProducto as pro " +
            "INNER JOIN tblPrecio pre ON pro.codProducto = pre.codProducto ")
    LiveData<List<TuplaListaProdEntity>> getAllProductosByLista();

    @Query("SELECT * FROM tblProducto WHERE codCategoria = :codCat ")
    LiveData<List<ProductoEntity>> findProductosByCodigoId(int codCat);

    @Query("SELECT pro.codProducto,pro.descripcion,pro.precio as 'precioVenta',inv.stock FROM tblProducto as pro " +
            "INNER JOIN tblInventario inv ON inv.codProducto = pro.codProducto " +
            "INNER JOIN tblAlmacen alm ON alm.ntraAlmacen=inv.codAlamacen"+
            " WHERE codDesProducto LIKE '%' || :descr || '%' AND alm.abreviatura= 'ALMP' LIMIT 15")
    LiveData<List<TuplaListaProdEntity>> findProductosCodDesc(String descr);

    @Query("SELECT pro.codProducto,pro.descripcion,pre.precioVenta as 'precioVenta',inv.stock FROM tblProducto as pro " +
            "INNER JOIN tblInventario inv ON inv.codProducto = pro.codProducto " +
            "INNER JOIN tblAlmacen alm ON alm.ntraAlmacen=inv.codAlamacen "+
            "INNER JOIN tblPrecio pre ON pre.codProducto = pro.codProducto "+
            " WHERE pre.tipoListaPrecio = :tipoPrecio and codDesProducto LIKE '%' || :descripcion || '%' AND alm.abreviatura= 'ALMP'" +
            " AND inv.stock > 0 AND pro.tipoProducto = 1 AND pro.codProducto NOT IN (SELECT det.codProducto FROM tblDetalle det) LIMIT 15")
    LiveData<List<TuplaListaProdEntity>> listarProductosPreventa(Short tipoPrecio, String descripcion);

    @Query("SELECT pro.ntraProducto, pro.codProducto, pro.descripcion, pro.codCategoria, pro.codUnidadBaseVenta, inv.stock, pre.precioVenta as 'precioVenta', pro.tipoListaPrecio, alm.ntraAlmacen FROM tblProducto as pro " +
            "INNER JOIN tblInventario inv ON inv.codProducto = pro.codProducto " +
            "INNER JOIN tblAlmacen alm ON alm.ntraAlmacen=inv.codAlamacen "+
            "INNER JOIN tblPrecio pre ON pre.codProducto = pro.codProducto "+
            "WHERE pre.tipoListaPrecio = :tipoPrecio and pro.codProducto = :codProducto AND alm.abreviatura= 'ALMP' LIMIT 1")
    LiveData<FilaProducto> buscarProductoSeleccion(Short tipoPrecio, String codProducto);

    @Query("SELECT con.ntra, con.codConcepto ,con.correlativo, con.descripcion " +
            "FROM tblProducto pro " +
            "INNER JOIN tblDetallePresentacion dpr ON pro.codProducto = dpr.codProducto " +
            "INNER JOIN table_concepto con ON dpr.codPresentancion = con.correlativo "+
            "WHERE pro.codProducto = :codProducto AND con.codConcepto = 12 order by con.correlativo asc ")
    LiveData<List<Concepto>> obtenerPresentaciones(String codProducto);

    @Query("SELECT con.ntra, con.codConcepto ,con.correlativo, con.descripcion, dpr.cantidadUnidadBase " +
            "FROM tblProducto pro " +
            "INNER JOIN tblDetallePresentacion dpr ON pro.codProducto = dpr.codProducto " +
            "INNER JOIN table_concepto con ON dpr.codPresentancion = con.correlativo "+
            "WHERE pro.codProducto = :codProducto AND con.codConcepto = 12 order by con.correlativo asc ")
    LiveData<List<FilaPresentacion>> obtenerPresentacionesCombo(String codProducto);

    @Query("SELECT * FROM tblProducto ")
    LiveData<List<ProductoEntity>> getAllProductos();

    @Query("DELETE FROM tblProducto")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblProducto'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ProductoEntity... producto);

    @Insert
    void insertList(List<ProductoEntity> productos);

    @Query("SELECT * FROM tblProducto WHERE codProducto = :codigo LIMIT 1")
    ProductoEntity obtenerProducto(String codigo);
}
