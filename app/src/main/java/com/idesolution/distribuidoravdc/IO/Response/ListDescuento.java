
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListDescuento {

    @SerializedName("ntraDescuento")
    @Expose
    private Integer ntraDescuento;
    @SerializedName("flag")
    @Expose
    private Integer flag;
    @SerializedName("ntra_flag")
    @Expose
    private String ntraFlag;
    @SerializedName("desc_desc")
    @Expose
    private String descDesc;
    @SerializedName("horaInicial")
    @Expose
    private String horaInicial;
    @SerializedName("horaFin")
    @Expose
    private String horaFin;
    @SerializedName("desc_det")
    @Expose
    private String descDet;
    @SerializedName("valorInicial")
    @Expose
    private String valorInicial;
    @SerializedName("valorFinal")
    @Expose
    private String valorFinal;
    @SerializedName("detalle")
    @Expose
    private Integer detalle;
    @SerializedName("estado")
    @Expose
    private Integer estado;
    @SerializedName("tipoDescuento")
    @Expose
    private Integer tipoDescuento;
    @SerializedName("listDetalleDescuento")
    @Expose
    private List<ListDetalleDescuento> listDetalleDescuento = null;

    public Integer getNtraDescuento() {
        return ntraDescuento;
    }

    public void setNtraDescuento(Integer ntraDescuento) {
        this.ntraDescuento = ntraDescuento;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getNtraFlag() {
        return ntraFlag;
    }

    public void setNtraFlag(String ntraFlag) {
        this.ntraFlag = ntraFlag;
    }

    public String getDescDesc() {
        return descDesc;
    }

    public void setDescDesc(String descDesc) {
        this.descDesc = descDesc;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getDescDet() {
        return descDet;
    }

    public void setDescDet(String descDet) {
        this.descDet = descDet;
    }

    public String getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(String valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(String valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Integer getDetalle() {
        return detalle;
    }

    public void setDetalle(Integer detalle) {
        this.detalle = detalle;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(Integer tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public List<ListDetalleDescuento> getListDetalleDescuento() {
        return listDetalleDescuento;
    }

    public void setListDetalleDescuento(List<ListDetalleDescuento> listDetalleDescuento) {
        this.listDetalleDescuento = listDetalleDescuento;
    }

}
