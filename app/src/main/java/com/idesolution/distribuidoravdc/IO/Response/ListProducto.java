
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListProducto {

    @SerializedName("codProducto")
    @Expose
    private String codProducto;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("cod_des")
    @Expose
    private String codDes;
    @SerializedName("codCategoria")
    @Expose
    private Integer codCategoria;
    @SerializedName("codUnidadBaseventa")
    @Expose
    private Integer codUnidadBaseventa;

    @SerializedName("tipoProducto")
    @Expose
    private Integer tipoProducto;

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodDes() {
        return codDes;
    }

    public void setCodDes(String codDes) {
        this.codDes = codDes;
    }

    public Integer getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Integer codCategoria) {
        this.codCategoria = codCategoria;
    }

    public Integer getCodUnidadBaseventa() {
        return codUnidadBaseventa;
    }

    public void setCodUnidadBaseventa(Integer codUnidadBaseventa) {
        this.codUnidadBaseventa = codUnidadBaseventa;
    }

    public Integer getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(Integer tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
}
