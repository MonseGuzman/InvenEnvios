package com.innovati.felipehernandez.invenenvios.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VwAbastecimientos_I
{
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long id;
    private String nombre;
    private float cantidad;
    private String unidadPrimaria;
    private short estatus;
    @Generated(hash = 2112491576)
    public VwAbastecimientos_I(Long id, String nombre, float cantidad,
            String unidadPrimaria, short estatus) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadPrimaria = unidadPrimaria;
        this.estatus = estatus;
    }
    @Generated(hash = 681867513)
    public VwAbastecimientos_I() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
