package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPresentacione {
    @SerializedName("codProducto")
    @Expose
    private String codProducto;
    @SerializedName("codPresentancion")
    @Expose
    private Integer codPresentancion;
    @SerializedName("cantidadUnidadBase")
    @Expose
    private Integer cantidadUnidadBase;

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public Integer getCodPresentancion() {
        return codPresentancion;
    }

    public void setCodPresentancion(Integer codPresentancion) {
        this.codPresentancion = codPresentancion;
    }

    public Integer getCantidadUnidadBase() {
        return cantidadUnidadBase;
    }

    public void setCantidadUnidadBase(Integer cantidadUnidadBase) {
        this.cantidadUnidadBase = cantidadUnidadBase;
    }
}
