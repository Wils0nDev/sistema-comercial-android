package com.idesolution.distribuidoravdc.IO.Request;

public class RequestEstadoAcRA {
    //@SerializedName("codRuta")
    private Integer codRuta; //tipo persona

    //@SerializedName("estado")
    private Integer estado; //tipo documento



    //@SerializedName("codUsuario")
    private Integer codUsuario; //tipo documento


    public RequestEstadoAcRA(Integer codRuta, Integer estado, Integer codUsuario ) {
        this.codRuta = codRuta;
        this.estado = estado;
        this.codUsuario = codUsuario;

    }

    public Integer getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(Integer codRuta) {
        this.codRuta = codRuta;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }



}
