
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("DescripcionResp")
    @Expose
    private String descripcionResp;
    @SerializedName("respuesta")
    @Expose
    private Integer respuesta;

    @SerializedName("ntraUsuario")
    @Expose
    private Integer ntraUsuario;

    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public String getDescripcionResp() {
        return descripcionResp;
    }

    public void setDescripcionResp(String descripcionResp) {
        this.descripcionResp = descripcionResp;
    }

    public Integer getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Integer respuesta) {
        this.respuesta = respuesta;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }

    public Integer getNtraUsuario() {
        return ntraUsuario;
    }

    public void setNtraUsuario(Integer ntraUsuario) {
        this.ntraUsuario = ntraUsuario;
    }
}
