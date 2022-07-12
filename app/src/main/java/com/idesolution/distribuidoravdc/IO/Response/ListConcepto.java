
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListConcepto {

    @SerializedName("codConcepto")
    @Expose
    private Integer codConcepto;
    @SerializedName("correlativo")
    @Expose
    private Integer correlativo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public Integer getCodConcepto() {
        return codConcepto;
    }

    public void setCodConcepto(Integer codConcepto) {
        this.codConcepto = codConcepto;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
