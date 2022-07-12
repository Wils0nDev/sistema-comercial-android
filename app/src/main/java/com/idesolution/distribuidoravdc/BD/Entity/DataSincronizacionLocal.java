package com.idesolution.distribuidoravdc.BD.Entity;

import com.idesolution.distribuidoravdc.IO.Request.RequestClienteSinc;
import com.idesolution.distribuidoravdc.IO.Request.RequestEntrega;
import com.idesolution.distribuidoravdc.IO.Response.ListPreventa;

import java.util.ArrayList;
import java.util.List;

public class DataSincronizacionLocal {
    ArrayList<RequestClienteSinc> listResClientes = new ArrayList<>();
    ArrayList<RequestEntrega> listReqEntregas= new ArrayList<>();
    List<ListPreventa> listPreventas = new ArrayList<>();

    public DataSincronizacionLocal(ArrayList<RequestClienteSinc> listResClientes, ArrayList<RequestEntrega> listReqEntregas, List<ListPreventa> listPreventas) {
        this.listResClientes = listResClientes;
        this.listReqEntregas = listReqEntregas;
        this.listPreventas = listPreventas;
    }

    public ArrayList<RequestClienteSinc> getListResClientes() {
        return listResClientes;
    }

    public void setListResClientes(ArrayList<RequestClienteSinc> listResClientes) {
        this.listResClientes = listResClientes;
    }

    public ArrayList<RequestEntrega> getListReqEntregas() {
        return listReqEntregas;
    }

    public void setListReqEntregas(ArrayList<RequestEntrega> listReqEntregas) {
        this.listReqEntregas = listReqEntregas;
    }

    public List<ListPreventa> getListPreventas() {
        return listPreventas;
    }

    public void setListPreventas(List<ListPreventa> listPreventas) {
        this.listPreventas = listPreventas;
    }
}
