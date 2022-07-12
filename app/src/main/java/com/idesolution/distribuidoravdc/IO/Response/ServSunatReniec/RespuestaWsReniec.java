
package com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaWsReniec {

    @SerializedName("dni")
    @Expose
    private String dni;
    @SerializedName("cui")
    @Expose
    private Integer cui;
    @SerializedName("apellido_paterno")
    @Expose
    private String apellidoPaterno;
    @SerializedName("apellido_materno")
    @Expose
    private String apellidoMaterno;
    @SerializedName("nombres")
    @Expose
    private String nombres;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getCui() {
        return cui;
    }

    public void setCui(Integer cui) {
        this.cui = cui;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

}
