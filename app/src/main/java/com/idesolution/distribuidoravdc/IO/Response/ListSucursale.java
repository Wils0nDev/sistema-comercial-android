
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListSucursale {

    @SerializedName("ntraSucursal")
    @Expose
    private Integer ntraSucursal;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("codUbigeo")
    @Expose
    private String codUbigeo;
    @SerializedName("factor")
    @Expose
    private Double factor;

    public Integer getNtraSucursal() {
        return ntraSucursal;
    }

    public void setNtraSucursal(Integer ntraSucursal) {
        this.ntraSucursal = ntraSucursal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

}
