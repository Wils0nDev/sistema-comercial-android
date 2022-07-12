
package com.idesolution.distribuidoravdc.IO.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSincronizacion {

    @SerializedName("DescripcionResp")
    @Expose
    private String descripcionResp;

    @SerializedName("estadoUsuario")
    @Expose
    private int estadoUsuario;
    //

    @SerializedName("vendedor")
    @Expose
    private Vendedor vendedor;
    @SerializedName("listCategorias")
    @Expose
    private List<ListCategoria> listCategorias = null;
    @SerializedName("listProductos")
    @Expose
    private List<ListProducto> listProductos = null;
    @SerializedName("listPresentaciones")
    @Expose
    private List<ListPresentacione> listPresentaciones = null;
    @SerializedName("listClientes")
    @Expose
    private List<ListCliente> listClientes = null;
    @SerializedName("listPromociones")
    @Expose
    private List<ListPromocione> listPromociones = null;
    @SerializedName("listPromocionesDesc")
    @Expose
    private List<ListPromocione> listPromocionesDesc = null;
    @SerializedName("listDescuentos")
    @Expose
    private List<ListDescuento> listDescuentos = null;
    @SerializedName("listAlmacenes")
    @Expose
    private List<ListAlmacene> listAlmacenes = null;
    @SerializedName("listInventarios")
    @Expose
    private List<ListInventario> listInventarios = null;
    @SerializedName("listEntregas")
    @Expose
    private List<ListEntrega> listEntregas = null;
    @SerializedName("listPreventas")
    @Expose
    private List<ListPreventa> listPreventas = null;
    @SerializedName("listPrecios")
    @Expose
    private List<ListPrecio> listPrecios = null;
    @SerializedName("listConceptos")
    @Expose
    private List<ListConcepto> listConceptos = null;
    @SerializedName("listParametros")
    @Expose
    private List<ListParametro> listParametros = null;
    @SerializedName("listLocalizacion")
    @Expose
    private List<ListLocalizacion> listLocalizacion = null;

    public String getDescripcionResp() {
        return descripcionResp;
    }

    public void setDescripcionResp(String descripcionResp) {
        this.descripcionResp = descripcionResp;
    }

    public int getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(int estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<ListCategoria> getListCategorias() {
        return listCategorias;
    }

    public void setListCategorias(List<ListCategoria> listCategorias) {
        this.listCategorias = listCategorias;
    }

    public List<ListProducto> getListProductos() {
        return listProductos;
    }

    public void setListProductos(List<ListProducto> listProductos) {
        this.listProductos = listProductos;
    }

    public List<ListPresentacione> getListPresentaciones() {
        return listPresentaciones;
    }

    public void setListPresentaciones(List<ListPresentacione> listPresentaciones) {
        this.listPresentaciones = listPresentaciones;
    }

    public List<ListCliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(List<ListCliente> listClientes) {
        this.listClientes = listClientes;
    }

    public List<ListPromocione> getListPromociones() {
        return listPromociones;
    }

    public void setListPromociones(List<ListPromocione> listPromociones) {
        this.listPromociones = listPromociones;
    }

    public List<ListPromocione> getListPromocionesDesc() {
        return listPromocionesDesc;
    }

    public void setListPromocionesDesc(List<ListPromocione> listPromocionesDesc) {
        this.listPromocionesDesc = listPromocionesDesc;
    }

    public List<ListDescuento> getListDescuentos() {
        return listDescuentos;
    }

    public void setListDescuentos(List<ListDescuento> listDescuentos) {
        this.listDescuentos = listDescuentos;
    }

    public List<ListAlmacene> getListAlmacenes() {
        return listAlmacenes;
    }

    public void setListAlmacenes(List<ListAlmacene> listAlmacenes) {
        this.listAlmacenes = listAlmacenes;
    }

    public List<ListInventario> getListInventarios() {
        return listInventarios;
    }

    public void setListInventarios(List<ListInventario> listInventarios) {
        this.listInventarios = listInventarios;
    }

    public List<ListEntrega> getListEntregas() {
        return listEntregas;
    }

    public void setListEntregas(List<ListEntrega> listEntregas) {
        this.listEntregas = listEntregas;
    }

    public List<ListPreventa> getListPreventas() {
        return listPreventas;
    }

    public void setListPreventas(List<ListPreventa> listPreventas) {
        this.listPreventas = listPreventas;
    }

    public List<ListPrecio> getListPrecios() {
        return listPrecios;
    }

    public void setListPrecios(List<ListPrecio> listPrecios) {
        this.listPrecios = listPrecios;
    }

    public List<ListConcepto> getListConceptos() {
        return listConceptos;
    }

    public void setListConceptos(List<ListConcepto> listConceptos) {
        this.listConceptos = listConceptos;
    }

    public List<ListParametro> getListParametros() {
        return listParametros;
    }

    public void setListParametros(List<ListParametro> listParametros) {
        this.listParametros = listParametros;
    }

    public List<ListLocalizacion> getListLocalizacion() {
        return listLocalizacion;
    }

    public void setListLocalizacion(List<ListLocalizacion> listLocalizacion) {
        this.listLocalizacion = listLocalizacion;
    }
}
