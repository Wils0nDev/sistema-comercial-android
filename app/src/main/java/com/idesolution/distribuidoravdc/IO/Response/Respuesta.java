
package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Respuesta {

    @SerializedName("DescripcionResp")
    @Expose
    private String descripcionResp;
    @SerializedName("listSucursales")
    @Expose
    private List<ListSucursale> listSucursales = null;

    public String getDescripcionResp() {
        return descripcionResp;
    }

    public void setDescripcionResp(String descripcionResp) {
        this.descripcionResp = descripcionResp;
    }

    public List<ListSucursale> getListSucursales() {
        return listSucursales;
    }

    public void setListSucursales(List<ListSucursale> listSucursales) {
        this.listSucursales = listSucursales;
    }

}
