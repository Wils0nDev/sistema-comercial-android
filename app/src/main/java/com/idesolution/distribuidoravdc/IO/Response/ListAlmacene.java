
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListAlmacene {

    @SerializedName("ntraAlmacen")
    @Expose
    private Integer ntraAlmacen;
    @SerializedName("abreviatura")
    @Expose
    private String abreviatura;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public Integer getNtraAlmacen() {
        return ntraAlmacen;
    }

    public void setNtraAlmacen(Integer ntraAlmacen) {
        this.ntraAlmacen = ntraAlmacen;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
