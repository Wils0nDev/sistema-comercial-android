package com.idesolution.distribuidoravdc.BD.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class TuplaListaProdEntity {
        @ColumnInfo(name = "codProducto")
        @NonNull
        public String codProducto;

        @ColumnInfo(name = "descripcion")
        @NonNull
        public String descripcion;

        @ColumnInfo(name = "precioVenta")
        @NonNull
        public double precioVenta;

        @ColumnInfo(name = "stock")
        @NonNull
        public int stock;

        @NonNull
        public String getCodProducto() {
            return codProducto;
        }

        public void setCodProducto(@NonNull String codProducto) {
            this.codProducto = codProducto;
        }

        @NonNull
        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(@NonNull String descripcion) {
            this.descripcion = descripcion;
        }

        public double getPrecioVenta() {
            return precioVenta;
        }

        public void setPrecioVenta(double precioVenta) {
            this.precioVenta = precioVenta;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }
}
