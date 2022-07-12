package com.idesolution.distribuidoravdc.IO.Request;

public class RequestAnularPreventa {

    public Integer ntraPreventa;       //codigo de preventa
    public Integer codUsuario;

    public RequestAnularPreventa(Integer ntraPreventa,Integer codUsuario) {
        this.ntraPreventa = ntraPreventa;
        this.codUsuario = codUsuario;
    }

    public Integer getNtraPreventa() {
        return ntraPreventa;
    }

    public void setNtraPreventa(Integer ntraPreventa) {
        this.ntraPreventa = ntraPreventa;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }
}
