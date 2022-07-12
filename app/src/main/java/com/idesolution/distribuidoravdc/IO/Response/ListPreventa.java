
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListPreventa {

    @SerializedName("ntraPreventa")
    @Expose
    private Integer ntraPreventa;
    @SerializedName("codCliente")
    @Expose
    private Integer codCliente;
    @SerializedName("tipoDocumento")
    @Expose
    private short tipoDocumento;     //tipo documento cliente
    @SerializedName("numeroDocumento")
    @Expose
    private String numeroDocumento;
    @SerializedName("codUsuario")
    @Expose
    private Integer codUsuario;
    @SerializedName("codPuntoEntrega")
    @Expose
    private Integer codPuntoEntrega;
    @SerializedName("tipoMoneda")
    @Expose
    private Integer tipoMoneda;
    @SerializedName("tipoVenta")
    @Expose
    private Integer tipoVenta;
    @SerializedName("tipoDocumentoVenta")
    @Expose
    private Integer tipoDocumentoVenta;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("fechaEntrega")
    @Expose
    private String fechaEntrega;
    @SerializedName("fechaPago")
    @Expose
    private String fechaPago;
    @SerializedName("flagRecargo")
    @Expose
    private short flagRecargo;
    @SerializedName("recargo")
    @Expose
    private Double recargo;

    @SerializedName("igv")
    @Expose
    private Double igv;
    @SerializedName("isc")
    @Expose
    private Double isc;
    @SerializedName("estado")
    @Expose
    private Integer estado;
    @SerializedName("total")
    @Expose
    private Double total;

    @SerializedName("horaEntrega")
    @Expose
    private String horaEntrega;

    @SerializedName("codSucursal")
    @Expose
    private int codSucursal;

    @SerializedName("origenVenta")
    @Expose
    private short origenVenta;

    @SerializedName("proceso")
    @Expose
    private short proceso;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("listDetPreventa")
    @Expose
    private List<ListDetPreventum> listDetPreventa = null;
    @SerializedName("listPrevPromocion")
    @Expose
    private List<ListPrevPromocion> listPrevPromocion = null;
    @SerializedName("listPrevDescuento")
    @Expose
    private List<ListPrevDescuento> listPrevDescuento = null;

    public Integer getNtraPreventa() {
        return ntraPreventa;
    }

    public void setNtraPreventa(Integer ntraPreventa) {
        this.ntraPreventa = ntraPreventa;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
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

    public Integer getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(Integer tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Integer getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(Integer tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public Integer getTipoDocumentoVenta() {
        return tipoDocumentoVenta;
    }

    public void setTipoDocumentoVenta(Integer tipoDocumentoVenta) {
        this.tipoDocumentoVenta = tipoDocumentoVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getIsc() {
        return isc;
    }

    public void setIsc(Double isc) {
        this.isc = isc;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<ListDetPreventum> getListDetPreventa() {
        return listDetPreventa;
    }

    public void setListDetPreventa(List<ListDetPreventum> listDetPreventa) {
        this.listDetPreventa = listDetPreventa;
    }

    public List<ListPrevPromocion> getListPrevPromocion() {
        return listPrevPromocion;
    }

    public void setListPrevPromocion(List<ListPrevPromocion> listPrevPromocion) {
        this.listPrevPromocion = listPrevPromocion;
    }

    public List<ListPrevDescuento> getListPrevDescuento() {
        return listPrevDescuento;
    }

    public void setListPrevDescuento(List<ListPrevDescuento> listPrevDescuento) {
        this.listPrevDescuento = listPrevDescuento;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public short getFlagRecargo() {
        return flagRecargo;
    }

    public void setFlagRecargo(short flagRecargo) {
        this.flagRecargo = flagRecargo;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public int getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(int codSucursal) {
        this.codSucursal = codSucursal;
    }

    public short getOrigenVenta() {
        return origenVenta;
    }

    public void setOrigenVenta(short origenVenta) {
        this.origenVenta = origenVenta;
    }

    public short getProceso() {
        return proceso;
    }

    public void setProceso(short proceso) {
        this.proceso = proceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
