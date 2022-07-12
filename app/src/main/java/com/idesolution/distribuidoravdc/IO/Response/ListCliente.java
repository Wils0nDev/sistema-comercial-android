
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCliente {

    @SerializedName("codPersona")
    @Expose
    private Integer codPersona;
    @SerializedName("tipoPersona")
    @Expose
    private Integer tipoPersona;
    @SerializedName("tipoDocumento")
    @Expose
    private Integer tipoDocumento;
    @SerializedName("numeroDocumento")
    @Expose
    private String numeroDocumento;
    @SerializedName("ruc")
    @Expose
    private String ruc;
    @SerializedName("razonSocial")
    @Expose
    private String razonSocial;
    @SerializedName("nombres")
    @Expose
    private String nombres;
    @SerializedName("apellidoPaterno")
    @Expose
    private String apellidoPaterno;
    @SerializedName("apellidoMaterno")
    @Expose
    private String apellidoMaterno;
    @SerializedName("nombreCodigo")
    @Expose
    private String nombreCodigo;
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
    @SerializedName("frecuenciaCliente")
    @Expose
    private Integer frecuenciaCliente;
    @SerializedName("tipoListaPrecio")
    @Expose
    private Integer tipoListaPrecio;
    @SerializedName("codRuta")
    @Expose
    private Integer codRuta;
    @SerializedName("ordenAtencion")
    @Expose
    private Integer ordenAtencion;
    @SerializedName("codUbigeo")
    @Expose
    private String codUbigeo;
    @SerializedName("clasificacionCliente")
    @Expose
    private Integer clasificacionCliente;

    public Integer getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(Integer codPersona) {
        this.codPersona = codPersona;
    }

    public Integer getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(Integer tipoPersona) {
        this.tipoPersona = tipoPersona;
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

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public String getNombreCodigo() {
        return nombreCodigo;
    }

    public void setNombreCodigo(String nombreCodigo) {
        this.nombreCodigo = nombreCodigo;
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

    public Integer getFrecuenciaCliente() {
        return frecuenciaCliente;
    }

    public void setFrecuenciaCliente(Integer frecuenciaCliente) {
        this.frecuenciaCliente = frecuenciaCliente;
    }

    public Integer getTipoListaPrecio() {
        return tipoListaPrecio;
    }

    public void setTipoListaPrecio(Integer tipoListaPrecio) {
        this.tipoListaPrecio = tipoListaPrecio;
    }

    public Integer getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(Integer codRuta) {
        this.codRuta = codRuta;
    }

    public Integer getOrdenAtencion() {
        return ordenAtencion;
    }

    public void setOrdenAtencion(Integer ordenAtencion) {
        this.ordenAtencion = ordenAtencion;
    }

    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
    }

    public Integer getClasificacionCliente() {
        return clasificacionCliente;
    }

    public void setClasificacionCliente(Integer clasificacionCliente) {
        this.clasificacionCliente = clasificacionCliente;
    }
}
