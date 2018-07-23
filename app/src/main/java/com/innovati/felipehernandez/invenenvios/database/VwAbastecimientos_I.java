package com.innovati.felipehernandez.invenenvios.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VwAbastecimientos_I
{
    private String nombre;
    private float cantidad;
    private String unidadPrimaria;
    private short estatus;
    @Generated(hash = 88919372)
    public VwAbastecimientos_I(String nombre, float cantidad, String unidadPrimaria,
            short estatus) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadPrimaria = unidadPrimaria;
        this.estatus = estatus;
    }
    @Generated(hash = 681867513)
    public VwAbastecimientos_I() {
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public float getCantidad() {
        return this.cantidad;
    }
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    public String getUnidadPrimaria() {
        return this.unidadPrimaria;
    }
    public void setUnidadPrimaria(String unidadPrimaria) {
        this.unidadPrimaria = unidadPrimaria;
    }
    public short getEstatus() {
        return this.estatus;
    }
    public void setEstatus(short estatus) {
        this.estatus = estatus;
    }
}
