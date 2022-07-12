
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListDistrito {

    @SerializedName("codDepartamento")
    @Expose
    private String codDepartamento;
    @SerializedName("codProvincia")
    @Expose
    private String codProvincia;
    @SerializedName("codDistrito")
    @Expose
    private String codDistrito;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("ubigeo")
    @Expose
    private String ubigeo;

    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getCodDistrito() {
        return codDistrito;
    }

    public void setCodDistrito(String codDistrito) {
        this.codDistrito = codDistrito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }


}
