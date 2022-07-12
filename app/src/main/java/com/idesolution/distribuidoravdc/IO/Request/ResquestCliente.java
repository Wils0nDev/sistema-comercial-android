package com.idesolution.distribuidoravdc.IO.Request;

import com.google.gson.annotations.SerializedName;

public class ResquestCliente {

    public short proceso;
    public int codPersona;
    public short tipoPersona; //tipo persona
    public short tipoDocumento; //tipo documento
    public String numDocumento; //numero documento
    public String ruc; //ruc
    public String razonSocial; //razon social
    public String nombres; //nombres
    public String apePaterno; //apellido paterno
    public String apeMaterno; //apellido materno
    public String direccion; //direccion
    public String correo; //correo
    public String telefono; //telefono
    public String celular; //celular
    public String ubigeo; //ubigeo
    public Integer ordenAtencion; //orden atencion
    public Integer perfilCliente; //perfil cliente
    public Integer clasificacion; //clasificacion
    public short frecuencia; //frecuencia
    public short tipoListaPrecio; //tipo de lista precio
    public short codRuta; //codigo de ruta
    public String latitud; // latitud
    public String longitud; //longitud
    public String usuario; //usuario
    public String ip; //ip
    public String mac; //mac

    public ResquestCliente(short proceso, int codPersona, short tipoPersona, short tipoDocumento, String numDocumento, String ruc, String razonSocial, String nombres, String apePaterno, String apeMaterno, String direccion, String correo, String telefono, String celular, String ubigeo, Integer ordenAtencion, Integer perfilCliente, Integer clasificacion, short frecuencia, short tipoListaPrecio, short codRuta,String latitud, String longitud, String usuario, String ip, String mac) {
        this.proceso = proceso;
        this.codPersona = codPersona;
        this.tipoPersona = tipoPersona;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.nombres = nombres;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.celular = celular;
        this.ubigeo = ubigeo;
        this.ordenAtencion = ordenAtencion;
        this.perfilCliente = perfilCliente;
        this.clasificacion = clasificacion;
        this.frecuencia = frecuencia;
        this.tipoListaPrecio = tipoListaPrecio;
        this.codRuta = codRuta;
        this.latitud = latitud;
        this.longitud = longitud;
        this.usuario = usuario;
        this.ip = ip;
        this.mac = mac;
    }

    public short getProceso() {
        return proceso;
    }

    public void setProceso(short proceso) {
        this.proceso = proceso;
    }

    public int getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(int codPersona) {
        this.codPersona = codPersona;
    }

    public short getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(short tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public short getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(short tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
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

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
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

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Integer getOrdenAtencion() {
        return ordenAtencion;
    }

    public void setOrdenAtencion(Integer ordenAtencion) {
        this.ordenAtencion = ordenAtencion;
    }

    public Integer getPerfilCliente() {
        return perfilCliente;
    }

    public void setPerfilCliente(Integer perfilCliente) {
        this.perfilCliente = perfilCliente;
    }

    public Integer getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Integer clasificacion) {
        this.clasificacion = clasificacion;
    }

    public short getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(short frecuencia) {
        this.frecuencia = frecuencia;
    }

    public short getTipoListaPrecio() {
        return tipoListaPrecio;
    }

    public void setTipoListaPrecio(short tipoListaPrecio) {
        this.tipoListaPrecio = tipoListaPrecio;
    }

    public short getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(short codRuta) {
        this.codRuta = codRuta;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
