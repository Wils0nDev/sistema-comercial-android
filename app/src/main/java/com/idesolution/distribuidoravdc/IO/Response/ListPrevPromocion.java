
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPrevPromocion {

    @SerializedName("codPreventa")
    @Expose
    private Integer codPreventa;
    @SerializedName("codPromocion")
    @Expose
    private Integer codPromocion;
    @SerializedName("itemPreventa")
    @Expose
    private Integer itemPreventa;
    @SerializedName("itemPromocionado")
    @Expose
    private Integer itemPromocionado;

    public Integer getCodPreventa() {
        return codPreventa;
    }

    public void setCodPreventa(Integer codPreventa) {
        this.codPreventa = codPreventa;
    }

    public Integer getCodPromocion() {
        return codPromocion;
    }

    public void setCodPromocion(Integer codPromocion) {
        this.codPromocion = codPromocion;
    }

    public Integer getItemPreventa() {
        return itemPreventa;
    }

    public void setItemPreventa(Integer itemPreventa) {
        this.itemPreventa = itemPreventa;
    }

    public Integer getItemPromocionado() {
        return itemPromocionado;
    }

    public void setItemPromocionado(Integer itemPromocionado) {
        this.itemPromocionado = itemPromocionado;
    }

}
