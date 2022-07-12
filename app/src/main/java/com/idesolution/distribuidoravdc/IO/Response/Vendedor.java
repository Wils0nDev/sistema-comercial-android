
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vendedor {

    @SerializedName("ntraUsuario")
    @Expose
    private Integer ntraUsuario;
    @SerializedName("codPerfil")
    @Expose
    private Integer codPerfil;
    @SerializedName("tipoDocumento")
    @Expose
    private Integer tipoDocumento;
    @SerializedName("numeroDocumento")
    @Expose
    private String numeroDocumento;
    @SerializedName("apellidoPaterno")
    @Expose
    private String apellidoPaterno;
    @SerializedName("apellidoMaterno")
    @Expose
    private String apellidoMaterno;
    @SerializedName("nombres")
    @Expose
    private String nombres;
    @SerializedName("nombreCompleto")
    @Expose
    private String nombreCompleto;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("celular")
    @Expose
    private String celular;
    @SerializedName("codRuta")
    @Expose
    private Integer codRuta;
    @SerializedName("descRuta")
    @Expose
    private String descRuta;

    public Integer getNtraUsuario() {
        return ntraUsuario;
    }

    public void setNtraUsuario(Integer ntraUsuario) {
        this.ntraUsuario = ntraUsuario;
    }

    public Integer getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(Integer codPerfil) {
        this.codPerfil = codPerfil;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(Integer codRuta) {
        this.codRuta = codRuta;
    }

    public String getDescRuta() {
        return descRuta;
    }

    public void setDescRuta(String descRuta) {
        this.descRuta = descRuta;
    }

}
