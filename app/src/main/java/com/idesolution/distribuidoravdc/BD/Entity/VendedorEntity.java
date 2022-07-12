package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName =  Constante.NAME_TABLE_VENDEDOR)
public class VendedorEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "ntraUsuario")
    public int ntraUsuario;

    @ColumnInfo(name = "codPerfil")
    public int codPerfil;

    @ColumnInfo(name = "tipoDocumento")
    public short tipoDocumento;

    @ColumnInfo(name = "numeroDocumento")
    public String numeroDocumento;

    @ColumnInfo(name = "apellidoPaterno")
    public String apellidoPaterno;

    @ColumnInfo(name = "apellidoMaterno")
    public String apellidoMaterno;

    @ColumnInfo(name = "nombres")
    public String nombres;

    @ColumnInfo(name = "nombreCompleto")
    public String nombreCompleto;

    @ColumnInfo(name = "direccion")
    public String direccion;

    @ColumnInfo(name = "correo")
    public String correo;

    @ColumnInfo(name = "telefono")
    public String telefono;

    @ColumnInfo(name = "celular")
    public String celular;

    @ColumnInfo(name = "codRuta")
    public int codRuta;

    @ColumnInfo(name = "descRuta")
    public String descRuta;


    public VendedorEntity(int ntraUsuario, int codPerfil, short tipoDocumento, String numeroDocumento, String apellidoPaterno, String apellidoMaterno, String nombres, String nombreCompleto, String direccion, String correo, String telefono, String celular, int codRuta, String descRuta) {
        this.ntraUsuario = ntraUsuario;
        this.codPerfil = codPerfil;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombres = nombres;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.celular = celular;
        this.codRuta = codRuta;
        this.descRuta = descRuta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNtraUsuario() {
        return ntraUsuario;
    }

    public void setNtraUsuario(int ntraUsuario) {
        this.ntraUsuario = ntraUsuario;
    }

    public int getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(int codPerfil) {
        this.codPerfil = codPerfil;
    }

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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(int codRuta) {
        this.codRuta = codRuta;
    }

    public String getDescRuta() {
        return descRuta;
    }

    public void setDescRuta(String descRuta) {
        this.descRuta = descRuta;
    }
}
