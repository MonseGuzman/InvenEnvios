package com.innovati.felipehernandez.invenenvios.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VwArticulos_I
{
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long Id;
    private String clave;
    private String nombre;
    private String unidadPrimaria;
    private float precio1;
    private String activo;
    private String tiempoSurtido;
    private float existenciaTotal;
    private String cubicaje;
    @Generated(hash = 2096461592)
    public VwArticulos_I(Long Id, String clave, String nombre,
            String unidadPrimaria, float precio1, String activo,
            String tiempoSurtido, float existenciaTotal, String cubicaje) {
        this.Id = Id;
        this.clave = clave;
        this.nombre = nombre;
        this.unidadPrimaria = unidadPrimaria;
        this.precio1 = precio1;
        this.activo = activo;
        this.tiempoSurtido = tiempoSurtido;
        this.existenciaTotal = existenciaTotal;
        this.cubicaje = cubicaje;
    }
    @Generated(hash = 122281887)
    public VwArticulos_I() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getClave() {
        return this.clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUnidadPrimaria() {
        return this.unidadPrimaria;
    }
    public void setUnidadPrimaria(String unidadPrimaria) {
        this.unidadPrimaria = unidadPrimaria;
    }
    public float getPrecio1() {
        return this.precio1;
    }
    public void setPrecio1(float precio1) {
        this.precio1 = precio1;
    }
    public String getActivo() {
        return this.activo;
    }
    public void setActivo(String activo) {
        this.activo = activo;
    }
    public String getTiempoSurtido() {
        return this.tiempoSurtido;
    }
    public void setTiempoSurtido(String tiempoSurtido) {
        this.tiempoSurtido = tiempoSurtido;
    }
    public float getExistenciaTotal() {
        return this.existenciaTotal;
    }
    public void setExistenciaTotal(float existenciaTotal) {
        this.existenciaTotal = existenciaTotal;
    }
    public String getCubicaje() {
        return this.cubicaje;
    }
    public void setCubicaje(String cubicaje) {
        this.cubicaje = cubicaje;
    }

}
