package com.idesolution.distribuidoravdc.IO.Request;

import com.idesolution.distribuidoravdc.IO.Response.ListPreventa;

import java.util.ArrayList;
import java.util.List;

public class RequestSincronizacion {
    private int codVendedor;
    private String codUbigeo;
    public ArrayList<RequestClienteSinc> listClientes;
    public List<ListPreventa> listPreventas;
    public List<RequestEntrega> listEntregas;
    public ArrayList<ResquestRutasBitacora> listaBitacoras;
    //CONSTRUCTOR

    public RequestSincronizacion(int codVendedor, String codUbigeo, ArrayList<RequestClienteSinc> listClientes, List<ListPreventa> listPreventas, List<RequestEntrega> listEntregas, ArrayList<ResquestRutasBitacora> listaBitacoras) {
        this.codVendedor = codVendedor;
        this.codUbigeo = codUbigeo;
        this.listClientes = listClientes;
        this.listPreventas = listPreventas;
        this.listEntregas = listEntregas;
        this.listaBitacoras = listaBitacoras;
    }


    //GETTER AND SETTER


    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
    }

    public int getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(int codVendedor) {
        this.codVendedor = codVendedor;
    }

    public ArrayList<RequestClienteSinc> getListClientes() {
        return listClientes;
    }

    public void setListClientes(ArrayList<RequestClienteSinc> listClientes) {
        this.listClientes = listClientes;
    }

    public List<ListPreventa> getListPreventas() {
        return listPreventas;
    }

    public void setListPreventas(List<ListPreventa> listPreventas) {
        this.listPreventas = listPreventas;
    }

    public List<RequestEntrega> getListEntregas() {
        return listEntregas;
    }

    public void setListEntregas(List<RequestEntrega> listEntregas) {
        this.listEntregas = listEntregas;
    }

    public ArrayList<ResquestRutasBitacora> getListaBitacoras() {
        return listaBitacoras;
    }

    public void setListaBitacoras(ArrayList<ResquestRutasBitacora> listaBitacoras) {
        this.listaBitacoras = listaBitacoras;
    }
}
