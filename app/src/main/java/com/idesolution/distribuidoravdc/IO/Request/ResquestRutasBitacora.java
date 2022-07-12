package com.idesolution.distribuidoravdc.IO.Request;

public class ResquestRutasBitacora {

    //@SerializedName("codRuta")
    private Integer codRuta; //tipo persona
    //@SerializedName("codCliente")
    private String codCliente; //tipo documento
    //@SerializedName("fecha")
    private String fecha; //numero documento
    //@SerializedName("visita")
    private Integer visita; //ruc
    //@SerializedName("motivo")
    private String motivo; //razon social
    //@SerializedName("usuario")
    private String usuario; //nombres
    //@SerializedName("ip")
    private String ip; //apellido paterno
    //@SerializedName("mac")
    private String mac; //apellido materno
    //@SerializedName("cordenadaX")
     private String cordenadaX; //direccion
    //@SerializedName("cordenadaY")
    private String cordenadaY; //direccion
    //@SerializedName("estado")
    private Integer estado; //direccion


    public ResquestRutasBitacora(Integer codRuta, String codCliente, String fecha, Integer visita, String motivo, String usuario, String ip, String mac, String cordenadaX, String cordenadaY, Integer estado ) {
        this.codRuta = codRuta;
        this.codCliente = codCliente;
        this.fecha = fecha;
        this.visita = visita;
        this.motivo = motivo;
        this.usuario = usuario;
        this.ip = ip;
        this.mac = mac;
        this.cordenadaX = cordenadaX;
        this.cordenadaY = cordenadaY;
        this.estado = estado;

    }

    public Integer getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(Integer codRuta) {
        this.codRuta = codRuta;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getVisita() {
        return visita;
    }

    public void setVisita(Integer visita) {
        this.visita = visita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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

    public String getCordenadaX() {
        return cordenadaX;
    }

    public void setCordenadaX(String cordenadaX) {
        this.cordenadaX = cordenadaX;
    }

    public String getCordenadaY() {
        return cordenadaY;
    }

    public void setCordenadaY(String cordenadaY) {
        this.cordenadaY = cordenadaY;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }




}


