package com.idesolution.distribuidoravdc.IO.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSesion {
    @SerializedName("codUsuario")
    @Expose
    private Integer codUsuario;
    @SerializedName("tipoLogueo")
    @Expose
    private Integer tipoLogueo;
    @SerializedName("tipoRegistro")
    @Expose
    private Integer tipoRegistro;

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Integer getTipoLogueo() {
        return tipoLogueo;
    }

    public void setTipoLogueo(Integer tipoLogueo) {
        this.tipoLogueo = tipoLogueo;
    }

    public Integer getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(Integer tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
}
