package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.idesolution.distribuidoravdc.BD.Entity.Preventa;
import com.idesolution.distribuidoravdc.BD.Entity.PreventaTotalEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionDescEntity;
import com.idesolution.distribuidoravdc.BD.Entity.PromocionEntity;

import java.util.List;

@Dao
public interface PreventaDao {

    @Query("SELECT * FROM tblPreventa ")
    LiveData<List<Preventa>> getAllPreventas();

    @Insert
    void insert(Preventa preventa);

    @Query("DELETE FROM tblPreventa")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tblPreventa'")
    void deleteSequence();

    @Query("DELETE FROM tblPreventa WHERE flag = -1 ")
    void deletePreventaInconclusa();

    @Query("SELECT ntra FROM tblPreventa WHERE flag = -1 LIMIT 1")
    LiveData<Integer> obtenerNtraPreventa();

    @Insert
    void insertList(List<Preventa> preventas);

    @Transaction
    @Query("SELECT * FROM tblPreventa WHERE tblPreventa.flag = 0 ")
    LiveData<List<PreventaTotalEntity>> totalPreventasSincro();

    @Transaction
    @Query("SELECT * FROM tblPreventa WHERE tblPreventa.flag != 1 ")
    List<PreventaTotalEntity> totalPreventasSincroRepo();

    @Query("SELECT MAX(ntra) as 'ntra' FROM tblPreventa LIMIT 1")
    Integer ntraPreventa();

    @Query("SELECT * FROM tblPreventa ORDER BY ntra DESC LIMIT 100")
    LiveData<List<Preventa>> obtenerPreventas();

    @Query("UPDATE tblPreventa SET flag = 1, ntraPreventa = :ntraPreventa WHERE ntra = :ntra")
    void actualizarNtraPreventa(Integer ntraPreventa, Integer ntra);

    @Query("UPDATE tblPreventa SET ntraPreventa = :ntraPreventa WHERE ntra = :ntraPreventa ")
    void actualizarNtraPreventaLocal(Integer ntraPreventa);

    @Query("SELECT * FROM tblPreventa WHERE fecha = :fecha ORDER BY ntra DESC LIMIT 100")
    LiveData<List<Preventa>> obtenerPreventasPorFecha(String fecha);

    @Query("UPDATE tbldetallepreventa SET flag = 1, codPreventa = :ntraPreventa WHERE codPreventa = :ntra")
    void actualizarNtraDetallePreventa(Integer ntraPreventa, Integer ntra);

    @Query("UPDATE tblPreventaPromocion SET flag = 1, codPreventa = :ntraPreventa WHERE codPreventa = :ntra")
    void actualizarNtraPreventaPromocion(Integer ntraPreventa, Integer ntra);

    @Query("UPDATE tblPreventaDescuento SET flag = 1, codPreventa = :ntraPreventa WHERE codPreventa = :ntra")
    void actualizarNtraPreventaDescuento(Integer ntraPreventa, Integer ntra);


    //DETALLE PREVENTA
    @Query("SELECT * FROM tblPreventa WHERE ntra = :ntra")
    LiveData<Preventa> obtenerPreventasPorNtra(int ntra);

    @Query("SELECT COUNT(itemPreventa) FROM tblDetallePreventa WHERE codPreventa = :ntra")
    LiveData<Integer> cantidadDeProductosPreventa(int ntra);

    @Query("SELECT COUNT(DISTINCT codPromocion) FROM tblPreventaPromocion WHERE codPreventa = :ntra")
    LiveData<Integer> cantidadDePromocionesPreventa(int ntra);

    @Query("SELECT * FROM tblPromociones " +
            " WHERE ntra IN (SELECT DISTINCT codPromocion FROM tblPreventaPromocion WHERE codPreventa = :ntra) " +
            " AND flag = 100 ")
    LiveData<List<PromocionEntity>> obtenerDescripcionPromos(int ntra);

    @Query("SELECT * FROM tblPromocionesDesc " +
            " WHERE ntraPromocion IN (SELECT DISTINCT codPromocion FROM tblPreventaPromocion WHERE codPreventa = :ntra) ")
    LiveData<List<PromocionDescEntity>> obtenerDescripcionPromosDesc(int ntra);


    //EDITAR PREVENTA
    @Query("SELECT * FROM tblPreventa WHERE ntra = :ntra")
    Preventa obtenerPreventaPorNtra(int ntra);

    @Query("UPDATE tblPreventa SET flag = :flag, tipoVenta = :tipoVenta, tipoDocumentoVenta = :tipoDocumentoVenta, " +
            "flagRecargo = :flagRecargo, recargo = :recargo, fechaEntrega = :fechaEntrega, igv = :igv, " +
            "total = :total, codPuntoEntrega = :codPuntoEntrega, horaEntrega = :horaEntrega WHERE ntra = :idPreventa")
    void actualizarPreventa(short flag, int idPreventa, short tipoVenta, short tipoDocumentoVenta, short flagRecargo, double recargo, String fechaEntrega, double igv, double total, int codPuntoEntrega, String horaEntrega);

    @Query("DELETE FROM tblDetallePreventa WHERE codPreventa = :ntra ")
    void eliminarDetallePreventa(int ntra);

    @Query("SELECT estado FROM tblPreventa WHERE ntra = :ntra")
    LiveData<Integer> obtenerEstadoPreventaNtra(int ntra);

    @Query("UPDATE tblPreventa SET estado = 2, flag = 2 WHERE ntra = :ntra")
    void anularPreventaNtra(int ntra);

    @Query("UPDATE tblPreventa SET flag = 1 WHERE ntra = :ntra")
    void actualizarPreventaAnulada(Integer ntra);
}
