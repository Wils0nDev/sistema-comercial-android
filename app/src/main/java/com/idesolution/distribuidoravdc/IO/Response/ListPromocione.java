
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListPromocione {

    @SerializedName("ntraPromocion")
    @Expose
    private Integer ntraPromocion;
    @SerializedName("flag")
    @Expose
    private Integer flag;
    @SerializedName("ntra_flag")
    @Expose
    private String ntraFlag;
    @SerializedName("desc_promo")
    @Expose
    private String descPromo;
    @SerializedName("horaInicial")
    @Expose
    private String horaInicial;
    @SerializedName("horaFin")
    @Expose
    private String horaFin;
    @SerializedName("desc_det")
    @Expose
    private Object descDet;
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
    @SerializedName("listDetallePromocion")
    @Expose
    private List<ListDetallePromocion> listDetallePromocion = null;

    public Integer getNtraPromocion() {
        return ntraPromocion;
    }

    public void setNtraPromocion(Integer ntraPromocion) {
        this.ntraPromocion = ntraPromocion;
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

    public String getDescPromo() {
        return descPromo;
    }

    public void setDescPromo(String descPromo) {
        this.descPromo = descPromo;
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

    public Object getDescDet() {
        return descDet;
    }

    public void setDescDet(Object descDet) {
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

    public List<ListDetallePromocion> getListDetallePromocion() {
        return listDetallePromocion;
    }

    public void setListDetallePromocion(List<ListDetallePromocion> listDetallePromocion) {
        this.listDetallePromocion = listDetallePromocion;
    }

}
