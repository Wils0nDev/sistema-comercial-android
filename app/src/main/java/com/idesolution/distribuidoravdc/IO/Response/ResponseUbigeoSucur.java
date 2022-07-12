package com.idesolution.distribuidoravdc.IO.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUbigeoSucur {
    @SerializedName("listDepartamentos")
    @Expose
    private List<ListDepartamento> listDepartamentos = null;
    @SerializedName("listProvincias")
    @Expose
    private List<ListProvincia> listProvincias = null;
    @SerializedName("listDistritos")
    @Expose
    private List<ListDistrito> listDistritos = null;
    @SerializedName("ErrorWebSer")
    @Expose
    private ErrorWebSer errorWebSer;

    public List<ListDepartamento> getListDepartamentos() {
        return listDepartamentos;
    }

    public void setListDepartamentos(List<ListDepartamento> listDepartamentos) {
        this.listDepartamentos = listDepartamentos;
    }

    public List<ListProvincia> getListProvincias() {
        return listProvincias;
    }

    public void setListProvincias(List<ListProvincia> listProvincias) {
        this.listProvincias = listProvincias;
    }

    public List<ListDistrito> getListDistritos() {
        return listDistritos;
    }

    public void setListDistritos(List<ListDistrito> listDistritos) {
        this.listDistritos = listDistritos;
    }

    public ErrorWebSer getErrorWebSer() {
        return errorWebSer;
    }

    public void setErrorWebSer(ErrorWebSer errorWebSer) {
        this.errorWebSer = errorWebSer;
    }

}
