package com.idesolution.distribuidoravdc.BD.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.idesolution.distribuidoravdc.BD.Entity.DescuentoEntity;

import java.util.List;

@Dao
public interface DescuentoDao {
    @Query("SELECT * FROM tbldescuentos")
    LiveData<List<DescuentoEntity>> getAllDescuento();

    @Query("DELETE FROM tbldescuentos")
    void deleteAll();

    @Query("DELETE FROM sqlite_sequence WHERE name='tbldescuentos'")
    void deleteSequence();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DescuentoEntity... descuento);

    @Insert
    void insertList(List<DescuentoEntity> descuentos);

    //Verificar producto con descuento
    @Query("SELECT COUNT(ntra) FROM tblDescuentos WHERE valorInicial = :codProducto and flag = 1 ")
    Integer verificarDescuento(String codProducto);

    //Obtener descuentos
    @Query("SELECT * FROM tblDescuentos WHERE valorInicial = :codProducto and flag = 1 ")
    List<DescuentoEntity> obtenerDescuentos(String codProducto);

    //validar existencia de flag
    @Query("SELECT COUNT(ntra) FROM tblDescuentos WHERE ntra = :ntra and flag = :flag ")
    Integer validar_exitencia_flag(int ntra, int flag);

    //obtener flag
    @Query("SELECT * FROM tblDescuentos WHERE ntra = :ntra and flag = :flag ")
    DescuentoEntity obtener_flag(int ntra, int flag);

    //cantidad de preventas que han usado el descuento
    @Query("SELECT COUNT(DISTINCT(des.codPreventa)) FROM tblPreventaDescuento des " +
            "INNER JOIN tblPreventa pre ON des.codPreventa = pre.ntraPreventa " +
            "WHERE pre.estado != 2 AND des.codDescuento = :ntra ")
    Integer cantidad_preventas_usan_descuento(int ntra);

    //cantidad de descuentos usados por un vendedor
    @Query("SELECT COUNT(DISTINCT(codPreventa)) " +
            "FROM tblPreventaDescuento des " +
            "INNER JOIN tblPreventa pre ON des.codPreventa = pre.ntraPreventa " +
            "WHERE pre.estado != 2 AND des.codDescuento = :ntra and pre.codUsuario = :ntraUsuario ")
    Integer cantidad_preventas_usan_descuento_vendedor(int ntra, int ntraUsuario);

    //cantidad de descuentos usados por un cliente
    @Query("SELECT COUNT(DISTINCT(codPreventa)) " +
            "FROM tblPreventaDescuento des " +
            "INNER JOIN tblPreventa pre ON des.codPreventa = pre.ntraPreventa " +
            "WHERE pre.estado != 2 AND des.codDescuento = :ntra and pre.codCliente = :codCliente ")
    Integer cantidad_preventas_usan_descuento_cliente(int ntra, int codCliente);

    @Query("SELECT * FROM tblDescuentos WHERE ntra = :ntra and flag = :flag ")
    LiveData<DescuentoEntity> obtener_flag_descuento(int ntra, int flag);

    //DESCUENTOS TOTALES
    //Verificar descuentos
    @Query("SELECT COUNT(ntra) FROM tblDescuentos WHERE flag = 9 ")
    Integer verificarDescuentoTotal();

    //Obtener descuentos
    @Query("SELECT * FROM tblDescuentos WHERE flag = 9 ")
    List<DescuentoEntity> obtenerDescuentosTotales();


    //DETALLE PREVENTA
    @Query("SELECT SUM(importe) FROM tblPreventaDescuento WHERE codPreventa = :ntra ")
    LiveData<Double> obtenerDescuentosPreventa(int ntra);

    @Query("SELECT SUM(importe) FROM tblPreventaDescuento WHERE codPreventa = :ntra AND itemPreventa = :item ")
    LiveData<Double> obtenerDescuentoPreventaItem(int ntra, int item);

    //EDITAR PREVENTA
    @Query("SELECT tipoDescuento FROM tblDescuentos WHERE ntra = :ntra ")
    Integer obtenerTipoDescuento(int ntra);
}
