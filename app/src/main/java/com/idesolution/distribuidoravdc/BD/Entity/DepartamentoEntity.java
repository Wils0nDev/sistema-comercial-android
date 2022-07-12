package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName = Constante.NAME_TABLE_DEPARTAMENTO)
public class DepartamentoEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "codDepartamento")
    @NonNull
    public String codDepartamento;

    @ColumnInfo(name = "nombre")
    @NonNull
    public String nombre;

    public DepartamentoEntity(@NonNull String codDepartamento, @NonNull String nombre) {
        this.codDepartamento = codDepartamento;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(@NonNull String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }
}
