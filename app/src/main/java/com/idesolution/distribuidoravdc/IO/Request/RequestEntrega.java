package com.idesolution.distribuidoravdc.IO.Request;

public class RequestEntrega {
    public short proceso;
    public int idLocal;
    public Integer ntraPuntoEntrega;
    public String coordenadaX;
    public String coordenadaY;
    public String codUbigeo;
    public String direccion;
    public String referencia;
    public Integer ordenEntrega;
    public Integer codPersona;
    public String usuario;              //usuario
    public String ip;                   //ip
    public String mac;                  //mac
    public short tipoDocumento;
    public String numeroDocumento;              //usuario

    /*
    public RequestEntrega(short proceso, int idLocal, Integer ntraPuntoEntrega, String coordenadaX, String coordenadaY, String codUbigeo, String direccion, String referencia, Integer ordenEntrega, Integer codPersona, String usuario, String ip, String mac) {
        this.proceso = proceso;
        this.idLocal = idLocal;
        this.ntraPuntoEntrega = ntraPuntoEntrega;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.codUbigeo = codUbigeo;
        this.direccion = direccion;
        this.referencia = referencia;
        this.ordenEntrega = ordenEntrega;
        this.codPersona = codPersona;
        this.usuario = usuario;
        this.ip = ip;
        this.mac = mac;
    }
*/
    public RequestEntrega(short proceso, int idLocal, Integer ntraPuntoEntrega, String coordenadaX, String coordenadaY, String codUbigeo, String direccion, String referencia, Integer ordenEntrega, Integer codPersona, String usuario, String ip, String mac, short tipoDocumento, String numeroDocumento) {
        this.proceso = proceso;
        this.idLocal = idLocal;
        this.ntraPuntoEntrega = ntraPuntoEntrega;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.codUbigeo = codUbigeo;
        this.direccion = direccion;
        this.referencia = referencia;
        this.ordenEntrega = ordenEntrega;
        this.codPersona = codPersona;
        this.usuario = usuario;
        this.ip = ip;
        this.mac = mac;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    /*
    public RequestEntrega(short proceso, Integer ntraPuntoEntrega, String coordenadaX, String coordenadaY, String codUbigeo, String direccion, String referencia, Integer ordenEntrega, Integer codPersona, String usuario, String ip, String mac) {
        this.proceso = proceso;
        this.ntraPuntoEntrega = ntraPuntoEntrega;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.codUbigeo = codUbigeo;
        this.direccion = direccion;
        this.referencia = referencia;
        this.ordenEntrega = ordenEntrega;
        this.codPersona = codPersona;
        this.usuario = usuario;
        this.ip = ip;
        this.mac = mac;
    }
    */

    public short getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(short tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public short getProceso() {
        return proceso;
    }

    public void setProceso(short proceso) {
        this.proceso = proceso;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public Integer getNtraPuntoEntrega() {
        return ntraPuntoEntrega;
    }

    public void setNtraPuntoEntrega(Integer ntraPuntoEntrega) {
        this.ntraPuntoEntrega = ntraPuntoEntrega;
    }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getOrdenEntrega() {
        return ordenEntrega;
    }

    public void setOrdenEntrega(Integer ordenEntrega) {
        this.ordenEntrega = ordenEntrega;
    }

    public Integer getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(Integer codPersona) {
        this.codPersona = codPersona;
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
