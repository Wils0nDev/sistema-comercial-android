package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.idesolution.distribuidoravdc.Entidad.Constante;

@Entity(tableName = Constante.NAME_TABLE_CATEGORIA)
public class CategoriaEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "ntraCategoria")
    @NonNull
    public int ntraCategoria;

    @ColumnInfo(name = "descripcion")
    @NonNull
    public String descripcion;

    public CategoriaEntity(@NonNull int ntraCategoria,@NonNull String descripcion) {
        this.ntraCategoria = ntraCategoria;
        this.descripcion = descripcion;
    }

    public int getNtraCategoria() {
        return ntraCategoria;
    }

    public void setNtraCategoria(int ntraCategoria) {
        this.ntraCategoria = ntraCategoria;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
