
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCategoria {

    @SerializedName("ntraCategoria")
    @Expose
    private Integer ntraCategoria;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    public Integer getNtraCategoria() {
        return ntraCategoria;
    }

    public void setNtraCategoria(Integer ntraCategoria) {
        this.ntraCategoria = ntraCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
