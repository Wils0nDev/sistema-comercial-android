
package com.idesolution.distribuidoravdc.IO.Response.ServSunatReniec;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaWsSunat {

    @SerializedName("ruc")
    @Expose
    private String ruc;
    @SerializedName("razon_social")
    @Expose
    private String razonSocial;
    @SerializedName("ciiu")
    @Expose
    private String ciiu;
    @SerializedName("fecha_actividad")
    @Expose
    private String fechaActividad;
    @SerializedName("contribuyente_condicion")
    @Expose
    private String contribuyenteCondicion;
    @SerializedName("contribuyente_tipo")
    @Expose
    private String contribuyenteTipo;
    @SerializedName("contribuyente_estado")
    @Expose
    private String contribuyenteEstado;
    @SerializedName("nombre_comercial")
    @Expose
    private String nombreComercial;
    @SerializedName("fecha_inscripcion")
    @Expose
    private String fechaInscripcion;
    @SerializedName("domicilio_fiscal")
    @Expose
    private String domicilioFiscal;
    @SerializedName("sistema_emision")
    @Expose
    private String sistemaEmision;
    @SerializedName("sistema_contabilidad")
    @Expose
    private String sistemaContabilidad;
    @SerializedName("actividad_exterior")
    @Expose
    private String actividadExterior;
    @SerializedName("emision_electronica")
    @Expose
    private String emisionElectronica;
    @SerializedName("fecha_inscripcion_ple")
    @Expose
    private String fechaInscripcionPle;
    @SerializedName("Oficio")
    @Expose
    private String oficio;
    @SerializedName("fecha_baja")
    @Expose
    private String fechaBaja;

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

    public String getCiiu() {
        return ciiu;
    }

    public void setCiiu(String ciiu) {
        this.ciiu = ciiu;
    }

    public String getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(String fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public String getContribuyenteCondicion() {
        return contribuyenteCondicion;
    }

    public void setContribuyenteCondicion(String contribuyenteCondicion) {
        this.contribuyenteCondicion = contribuyenteCondicion;
    }

    public String getContribuyenteTipo() {
        return contribuyenteTipo;
    }

    public void setContribuyenteTipo(String contribuyenteTipo) {
        this.contribuyenteTipo = contribuyenteTipo;
    }

    public String getContribuyenteEstado() {
        return contribuyenteEstado;
    }

    public void setContribuyenteEstado(String contribuyenteEstado) {
        this.contribuyenteEstado = contribuyenteEstado;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getDomicilioFiscal() {
        return domicilioFiscal;
    }

    public void setDomicilioFiscal(String domicilioFiscal) {
        this.domicilioFiscal = domicilioFiscal;
    }

    public String getSistemaEmision() {
        return sistemaEmision;
    }

    public void setSistemaEmision(String sistemaEmision) {
        this.sistemaEmision = sistemaEmision;
    }

    public String getSistemaContabilidad() {
        return sistemaContabilidad;
    }

    public void setSistemaContabilidad(String sistemaContabilidad) {
        this.sistemaContabilidad = sistemaContabilidad;
    }

    public String getActividadExterior() {
        return actividadExterior;
    }

    public void setActividadExterior(String actividadExterior) {
        this.actividadExterior = actividadExterior;
    }

    public String getEmisionElectronica() {
        return emisionElectronica;
    }

    public void setEmisionElectronica(String emisionElectronica) {
        this.emisionElectronica = emisionElectronica;
    }

    public String getFechaInscripcionPle() {
        return fechaInscripcionPle;
    }

    public void setFechaInscripcionPle(String fechaInscripcionPle) {
        this.fechaInscripcionPle = fechaInscripcionPle;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

}
