
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPrevDescuento {

    @SerializedName("codPreventa")
    @Expose
    private Integer codPreventa;
    @SerializedName("codDescuento")
    @Expose
    private Integer codDescuento;
    @SerializedName("itemPreventa")
    @Expose
    private Integer itemPreventa;
    @SerializedName("importe")
    @Expose
    private Double importe;

    public Integer getCodPreventa() {
        return codPreventa;
    }

    public void setCodPreventa(Integer codPreventa) {
        this.codPreventa = codPreventa;
    }

    public Integer getCodDescuento() {
        return codDescuento;
    }

    public void setCodDescuento(Integer codDescuento) {
        this.codDescuento = codDescuento;
    }

    public Integer getItemPreventa() {
        return itemPreventa;
    }

    public void setItemPreventa(Integer itemPreventa) {
        this.itemPreventa = itemPreventa;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

}
