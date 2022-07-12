package com.idesolution.distribuidoravdc.IO.Request;

import java.util.Date;
import java.util.List;

public class RequestPreventa {
    public short proceso;      //proceso
    public ResquestCliente cliente;
    public RequestEntrega entrega;
    public Integer ntraPreventa;       //codigo de preventa
    public Integer codCliente;         //codigo cliente
    public Integer codUsuario;         //codigo usuario
    public Integer codPuntoEntrega;    //codigo punto entrega
    public short tipoMoneda;           //moneda
    public short tipoVenta;            //tipo venta
    public short tipoDocumentoVenta;   //tipo documento de venta
    public String fecha;                 //fecha
    public String fechaEntrega;          //fecha entrega
    public String horaEntrega;          //hora de entrega
    public Integer codSucursal;          //codigo de sucursal
    public String fechaPago;             //flag de recargo
    public short flagRecargo;          //recargo
    public double recargo;             //recargo
    public double igv;                 //igv
    public double isc;                 //isc
    public double total;               //total
    public short estado;               //estado
    public String usuario;              //usuario
    public String ip;                   //ip
    public String mac;                  //mac
    public short origenVenta;           //origen venta
    public List<RequestDetallePreventa> listDetPreventa;
    public List<RequestPreventaPromocion> listPrevPromocion;
    public List<RequestPreventaDescuento> listPrevDescuento;

    public RequestPreventa(short proceso, ResquestCliente cliente, RequestEntrega entrega, Integer ntraPreventa, Integer codCliente, Integer codUsuario, Integer codPuntoEntrega, short tipoMoneda, short tipoVenta, short tipoDocumentoVenta, String fecha, String fechaEntrega, String fechaPago, short flagRecargo, double recargo, double igv, double isc, double total, short estado, String usuario, String ip, String mac, List<RequestDetallePreventa> listDetPreventa) {
        this.proceso = proceso;
        this.cliente = cliente;
        this.entrega = entrega;
        this.ntraPreventa = ntraPreventa;
        this.codCliente = codCliente;
        this.codUsuario = codUsuario;
        this.codPuntoEntrega = codPuntoEntrega;
        this.tipoMoneda = tipoMoneda;
        this.tipoVenta = tipoVenta;
        this.tipoDocumentoVenta = tipoDocumentoVenta;
        this.fecha = fecha;
        this.fechaEntrega = fechaEntrega;
        this.fechaPago = fechaPago;
        this.flagRecargo = flagRecargo;
        this.recargo = recargo;
        this.igv = igv;
        this.isc = isc;
        this.total = total;
        this.estado = estado;
        this.usuario = usuario;
        this.ip = ip;
        this.mac = mac;
        this.listDetPreventa = listDetPreventa;
    }

    public RequestPreventa(short proceso, ResquestCliente cliente, Integer ntraPreventa, Integer codCliente, Integer codUsuario, Integer codPuntoEntrega, short tipoMoneda, short tipoVenta, short tipoDocumentoVenta, String fecha, String fechaEntrega, String horaEntrega, Integer codSucursal, String fechaPago, short flagRecargo, double recargo, double igv, double isc, double total, short estado, String usuario, String ip, String mac, short origenVenta, List<RequestDetallePreventa> listDetPreventa, List<RequestPreventaPromocion> listPrevPromocion, List<RequestPreventaDescuento> listPrevDescuento) {
        this.proceso = proceso;
        this.cliente = cliente;
        this.ntraPreventa = ntraPreventa;
        this.codCliente = codCliente;
        this.codUsuario = codUsuario;
        this.codPuntoEntrega = codPuntoEntrega;
        this.tipoMoneda = tipoMoneda;
        this.tipoVenta = tipoVenta;
        this.tipoDocumentoVenta = tipoDocumentoVenta;
        this.fecha = fecha;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
        this.codSucursal = codSucursal;
        this.fechaPago = fechaPago;
        this.flagRecargo = flagRecargo;
        this.recargo = recargo;
        this.igv = igv;
        this.isc = isc;
        this.total = total;
        this.estado = estado;
        this.usuario = usuario;
        this.ip = ip;
        this.mac = mac;
        this.origenVenta = origenVenta;
        this.listDetPreventa = listDetPreventa;
        this.listPrevPromocion = listPrevPromocion;
        this.listPrevDescuento = listPrevDescuento;
    }

    public short getProceso() {
        return proceso;
    }

    public void setProceso(short proceso) {
        this.proceso = proceso;
    }

    public ResquestCliente getCliente() {
        return cliente;
    }

    public void setCliente(ResquestCliente cliente) {
        this.cliente = cliente;
    }

    public RequestEntrega getEntrega() {
        return entrega;
    }

    public void setEntrega(RequestEntrega entrega) {
        this.entrega = entrega;
    }

    public Integer getNtraPreventa() {
        return ntraPreventa;
    }

    public void setNtraPreventa(Integer ntraPreventa) {
        this.ntraPreventa = ntraPreventa;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Integer getCodPuntoEntrega() {
        return codPuntoEntrega;
    }

    public void setCodPuntoEntrega(Integer codPuntoEntrega) {
        this.codPuntoEntrega = codPuntoEntrega;
    }

    public short getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(short tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public short getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(short tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public short getTipoDocumentoVenta() {
        return tipoDocumentoVenta;
    }

    public void setTipoDocumentoVenta(short tipoDocumentoVenta) {
        this.tipoDocumentoVenta = tipoDocumentoVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public short getFlagRecargo() {
        return flagRecargo;
    }

    public void setFlagRecargo(short flagRecargo) {
        this.flagRecargo = flagRecargo;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getIsc() {
        return isc;
    }

    public void setIsc(double isc) {
        this.isc = isc;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
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

    public List<RequestDetallePreventa> getListDetPreventa() {
        return listDetPreventa;
    }

    public void setListDetPreventa(List<RequestDetallePreventa> listDetPreventa) {
        this.listDetPreventa = listDetPreventa;
    }

    public List<RequestPreventaPromocion> getListPrevPromocion() {
        return listPrevPromocion;
    }

    public void setListPrevPromocion(List<RequestPreventaPromocion> listPrevPromocion) {
        this.listPrevPromocion = listPrevPromocion;
    }

    public List<RequestPreventaDescuento> getListPrevDescuento() {
        return listPrevDescuento;
    }

    public void setListPrevDescuento(List<RequestPreventaDescuento> listPrevDescuento) {
        this.listPrevDescuento = listPrevDescuento;
    }

    public short getOrigenVenta() {
        return origenVenta;
    }

    public void setOrigenVenta(short origenVenta) {
        this.origenVenta = origenVenta;
    }
}
