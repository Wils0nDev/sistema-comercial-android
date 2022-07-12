package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.FilaDetallePreventa;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaDetalleEntity;

import java.util.List;

@Dao
public interface PreventaDetalleDao {
    @Query("SELECT * FROM tblDetallePreventa ")
    LiveData<List<PreventaDetalleEntity>> getAllPreventaDetalle();

    @Insert
    void insert(PreventaDetalleEntity detallePreventa);

    @Query("DELETE FROM tblDetallePreventa")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblDetallePreventa'")
    void deleteSequence();

    @Insert
    void insertList(List<PreventaDetalleEntity> listdetpreventa);

    @Query("DELETE FROM tblDetallePreventa WHERE flag = -1 ")
    void deleteDetallePreventaInconclusa();

    @Query("SELECT COUNT(id) FROM tblDetallePreventa WHERE flag = -1")
    LiveData<Short> identificarItem();

    @Query("SELECT det.codPreventa, det.itemPreventa, det.codPresentacion, det.codProducto, det.codAlmacen, det.cantidadPresentacion, det.cantidadUnidadBase, " +
            "det.precioVenta, det.TipoProducto, pro.descripcion as 'descripcionProducto', con.descripcion as 'descripcionUnidad' " +
            "FROM tblDetallePreventa det " +
            "INNER JOIN tblProducto pro ON pro.codProducto = det.codProducto " +
            "INNER JOIN table_concepto con ON det.codPresentacion = con.correlativo " +
            "WHERE det.codPreventa = :ntra AND con.codConcepto = 12 order by det.itemPreventa asc ")
    LiveData<List<FilaDetallePreventa>> obtenerDetallePreventa(int ntra);

    //EDITAR PREVENTA
    @Query("SELECT * FROM tblDetallePreventa WHERE codPreventa = :ntra ")
    List<PreventaDetalleEntity> obtenerDetallePreventaNtra(int ntra);

    @Query("UPDATE tblDetallePreventa SET flag = :flag WHERE codPreventa = :ntra ")
    void anularDetallePreventa(int flag, int ntra);
}
