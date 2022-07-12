package com.idesolution.distribuidoravdc.IO.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestLogin {
    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("contra")
    @Expose
    private String contra;
    @SerializedName("sucursal")
    @Expose
    private Integer sucursal;

    @SerializedName("tipo")
    @Expose
    private Integer tipo;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
