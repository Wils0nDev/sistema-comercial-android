package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.Detalle;
import com.idesolution.distribuidoravdc.BD.Entity.FilaCarrito;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDetalleEntity;

import java.util.List;

@Dao
public interface DetalleDao {

    @Insert
    void insert(Detalle detalle);

    @Insert
    void insertList(List<Detalle> detalle);

    @Query("SELECT * FROM tblDetalle ")
    LiveData<List<Detalle>> getAllDetalles();

    @Query("DELETE FROM tblDetalle")
    void deleteAll();

    @Query("SELECT COUNT(ntra) FROM tblDetalle ")
    LiveData<Short> identificarItem();

    @Query("SELECT det.ntra, det.ntraPreventa, det.itemPreventa, det.codPresentacion, det.codProducto, det.codAlmacen, det.cantidadPresentacion, det.cantidadUnidadBase, " +
            "det.precioVenta, det.TipoProducto, pro.descripcion as 'descripcionProducto', con.descripcion as 'descripcionUnidad' " +
            "FROM tblDetalle det " +
            "INNER JOIN tblProducto pro ON pro.codProducto = det.codProducto " +
            "INNER JOIN table_concepto con ON det.codPresentacion = con.correlativo "+
            "WHERE con.codConcepto = 12 order by det.itemPreventa asc ")
    LiveData<List<FilaCarrito>> obtenerFilaCarrito();

    @Query("DELETE FROM tblDetalle WHERE itemPreventa = :item ")
    void quitarProductoCarrito(Integer item);

    @Query("UPDATE tblDetalle SET itemPreventa = itemPreventa - 1 WHERE itemPreventa > :item ")
    void ordenarItems(Integer item);

    @Query("SELECT * FROM tblDetalle ")
    List<Detalle> AllDetalles();

    @Query("SELECT * FROM tbldetallepreventa where flag = 0")
    LiveData<List<PreventaDetalleEntity>> detallePreventa();

    @Query("DELETE FROM tblDetalle WHERE itemPreventa IN (SELECT itemPromocionado FROM preventa_promocion WHERE itemPreventa = :item ) ")
    void quitarProductoConPromocionCarrito(Integer item);

    @Query("UPDATE tblDetalle SET itemPreventa = itemPreventa - :cant WHERE itemPreventa > :item ")
    void ordenarItemsPromocion(int item, int cant);


    @Query("SELECT * FROM tblDetalle where codProducto = :codProducto ")
    LiveData<Detalle> obtenerDetalle(String codProducto);

    @Query("SELECT COUNT(itemPreventa) FROM tblDetalle")
    LiveData<Integer> cantidadItems();
}
