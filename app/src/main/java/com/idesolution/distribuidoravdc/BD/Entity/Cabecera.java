package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblCabecera")
public class Cabecera {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ntra")
    private Integer ntra;               //numero de transaccion
    /*@ColumnInfo(name = "ntraCliente")
    private Integer ntraCliente;        //numero de transaccion de cliente*/
    @ColumnInfo(name = "codCliente")
    private Integer codCliente;         //codigo cliente
    @ColumnInfo(name = "tipoDocumento")
    private short tipoDocumento;     //tipo documento
    @ColumnInfo(name = "numeroDocumento")
    private String numeroDocumento;     //numero documento
    @ColumnInfo(name = "codUsuario")
    private Integer codUsuario;         //codigo usuario
    @ColumnInfo(name = "codPuntoEntrega")
    private Integer codPuntoEntrega;    //codigo punto entrega
    @ColumnInfo(name = "tipoMoneda")
    private short moneda;               //moneda
    @ColumnInfo(name = "tipoVenta")
    private short tipoVenta;            //tipo venta
    @ColumnInfo(name = "tipoDocumentoVenta")
    private short tipoDocumentoVenta;   //tipo documento de venta
    @ColumnInfo(name = "fecha")
    private String fecha;               //fecha
    @ColumnInfo(name = "flagRecargo")
    private short flagRecargo;           //flag de recargo
    @ColumnInfo(name = "recargo")
    private double recargo;             //recargo
    @ColumnInfo(name = "igv")
    private double igv;                 //igv
    @ColumnInfo(name = "isc")
    private double isc;                 //isc
    @ColumnInfo(name = "total")
    private double total;               //total
    @ColumnInfo(name = "estado")
    private short estado;               //estado
    @ColumnInfo(name = "origenVenta")
    private short origenVenta;                 //
    @ColumnInfo(name = "fechaEntrega")
    private String fechaEntrega;                 //
    @ColumnInfo(name = "horaEntrega")
    private String horaEntrega;                 //
    @ColumnInfo(name = "codSucursal")
    private Integer codSucursal;                 //

    /*public Cabecera(Integer codCliente, short tipoDocumento, String numeroDocumento, Integer codUsuario, Integer codPuntoEntrega, short moneda, short tipoVenta, short tipoDocumentoVenta, String fecha, short flagRecargo, double recargo, double igv, double isc, double total, short estado) {
        this.codCliente = codCliente;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.codUsuario = codUsuario;
        this.codPuntoEntrega = codPuntoEntrega;
        this.moneda = moneda;
        this.tipoVenta = tipoVenta;
        this.tipoDocumentoVenta = tipoDocumentoVenta;
        this.fecha = fecha;
        this.flagRecargo = flagRecargo;
        this.recargo = recargo;
        this.igv = igv;
        this.isc = isc;
        this.total = total;
        this.estado = estado;
    }*/

    public Cabecera(Integer codCliente, short tipoDocumento, String numeroDocumento, Integer codUsuario, Integer codPuntoEntrega, short moneda, short tipoVenta, short tipoDocumentoVenta, String fecha, short flagRecargo, double recargo, double igv, double isc, double total, short estado, short origenVenta, String fechaEntrega, String horaEntrega, Integer codSucursal) {
        this.codCliente = codCliente;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.codUsuario = codUsuario;
        this.codPuntoEntrega = codPuntoEntrega;
        this.moneda = moneda;
        this.tipoVenta = tipoVenta;
        this.tipoDocumentoVenta = tipoDocumentoVenta;
        this.fecha = fecha;
        this.flagRecargo = flagRecargo;
        this.recargo = recargo;
        this.igv = igv;
        this.isc = isc;
        this.total = total;
        this.estado = estado;
        this.origenVenta = origenVenta;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
        this.codSucursal = codSucursal;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public Integer getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(Integer codSucursal) {
        this.codSucursal = codSucursal;
    }

    public short getOrigenVenta() {
        return origenVenta;
    }

    public void setOrigenVenta(short origenVenta) {
        this.origenVenta = origenVenta;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @NonNull
    public Integer getNtra() {
        return ntra;
    }

    public void setNtra(@NonNull Integer ntra) {
        this.ntra = ntra;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public short getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(short tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Integer getCodPuntoEntrega() {
        return codPuntoEntrega;
    }

    public void setCodPuntoEntrega(Integer codPuntoEntrega) {
        this.codPuntoEntrega = codPuntoEntrega;
    }

    public short getMoneda() {
        return moneda;
    }

    public void setMoneda(short moneda) {
        this.moneda = moneda;
    }

    public short getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(short tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public short getTipoDocumentoVenta() {
        return tipoDocumentoVenta;
    }

    public void setTipoDocumentoVenta(short tipoDocumentoVenta) {
        this.tipoDocumentoVenta = tipoDocumentoVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public short getFlagRecargo() {
        return flagRecargo;
    }

    public void setFlagRecargo(short flagRecargo) {
        this.flagRecargo = flagRecargo;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getIsc() {
        return isc;
    }

    public void setIsc(double isc) {
        this.isc = isc;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }
}
