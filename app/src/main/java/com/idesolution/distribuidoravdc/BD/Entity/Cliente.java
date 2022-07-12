package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_cliente")
public class Cliente {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ntra")
    private Integer ntra; //numero de transaccion    @ColumnInfo(name = "codCliente")
    @NonNull
    @ColumnInfo(name = "codCliente")
    private Integer codCliente; //codigo cliente
    @NonNull
    @ColumnInfo(name = "tipoPersona")
    private short tipoPersona; //tipo persona
    @ColumnInfo(name = "tipoDocumento")
    private short tipoDocumento; //tipo documento
    @ColumnInfo(name = "numeroDocumento")
    private String numeroDocumento; //numero documento
    @ColumnInfo(name = "ruc")
    private String ruc; //ruc
    @ColumnInfo(name = "razonSocial")
    private String razonSocial; //razon social
    @ColumnInfo(name = "nombres")
    private String nombres; //nombres
    @ColumnInfo(name = "apellidoPaterno")
    private String apellidoPaterno; //apellido paterno
    @ColumnInfo(name = "apellidoMaterno")
    private String apellidoMaterno; //apellido materno
    @ColumnInfo(name = "direccion")
    private String direccion; //direccion
    @ColumnInfo(name = "correo")
    private String correo; //correo
    @ColumnInfo(name = "telefono")
    private String telefono; //telefono
    @ColumnInfo(name = "celular")
    private String celular; //celular
    @ColumnInfo(name = "frecuencia")
    private short frecuencia; //frecuencia
    @ColumnInfo(name = "tipoListaPrecio")
    private short tipoListaPrecio; //tipo de lista precio
    @ColumnInfo(name = "codRuta")
    private short codRuta; //codigo de ruta
    @ColumnInfo(name = "busqueda")
    private String busqueda; //cadena para busqueda de cliente
    @ColumnInfo(name = "flag")
    private Integer flag; //flag de sincronizacion
    @ColumnInfo(name = "ubigeo")
    private String ubigeo; //ubigeo
    @ColumnInfo(name = "clasificacionCliente")
    private Integer clasificacionCliente; //flag de sincronizacion


    public Cliente(@NonNull Integer codCliente, @NonNull short tipoPersona, @NonNull short tipoDocumento, String numeroDocumento, String ruc, String razonSocial, String nombres, String apellidoPaterno, String apellidoMaterno, String direccion, String correo, String telefono, String celular, short frecuencia, short tipoListaPrecio, short codRuta, String busqueda, Integer flag,String ubigeo,Integer clasificacionCliente) {
        this.codCliente = codCliente;
        this.tipoPersona = tipoPersona;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.celular = celular;
        this.frecuencia = frecuencia;
        this.tipoListaPrecio = tipoListaPrecio;
        this.codRuta = codRuta;
        this.busqueda = busqueda;
        this.flag = flag;
        this.ubigeo = ubigeo;
        this.clasificacionCliente = clasificacionCliente;
    }
    @Ignore
    public Cliente(String nombres) {
        this.nombres = nombres;
    }

    @NonNull
    public Integer getCodCliente() {
        return codCliente;
    }

    public short getTipoPersona() {
        return tipoPersona;
    }

    public short getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public Integer getNtra() { return ntra; }

    public String getRuc() {
        return ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public short getFrecuencia() {
        return frecuencia;
    }

    public short getTipoListaPrecio() {
        return tipoListaPrecio;
    }

    public short getCodRuta() {
        return codRuta;
    }

    public void setNtra(@NonNull Integer ntra) { this.ntra = ntra; }

    public String getBusqueda() { return busqueda;    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public void setCodCliente(@NonNull Integer codCliente) {
        this.codCliente = codCliente;
    }

    public void setTipoPersona(short tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public void setTipoDocumento(short tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFrecuencia(short frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setTipoListaPrecio(short tipoListaPrecio) {
        this.tipoListaPrecio = tipoListaPrecio;
    }

    public void setCodRuta(short codRuta) {
        this.codRuta = codRuta;
    }
    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public Integer getClasificacionCliente() {
        return clasificacionCliente;
    }

    public void setClasificacionCliente(Integer clasificacionCliente) {
        this.clasificacionCliente = clasificacionCliente;
    }
}
