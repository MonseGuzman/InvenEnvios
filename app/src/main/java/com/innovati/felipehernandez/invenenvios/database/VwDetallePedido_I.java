package com.innovati.felipehernandez.invenenvios.database;

import org.greenrobot.greendao.annotation.Entity;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VwDetallePedido_I
{
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long Id;
    private String idDetallePedido;
    private String idPedido;
    private String claveArticulo;
    private String nombre;
    private float cantidad;
    private float precio;
    private float subtotal;
    private float iva;
    private float total;
    private Date fechaActualizacion;
    private String usuarioActualizacion;
    @Generated(hash = 1959607518)
    public VwDetallePedido_I(Long Id, String idDetallePedido, String idPedido,
            String claveArticulo, String nombre, float cantidad, float precio,
            float subtotal, float iva, float total, Date fechaActualizacion,
            String usuarioActualizacion) {
        this.Id = Id;
        this.idDetallePedido = idDetallePedido;
        this.idPedido = idPedido;
        this.claveArticulo = claveArticulo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioActualizacion = usuarioActualizacion;
    }
    @Generated(hash = 1716617429)
    public VwDetallePedido_I() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getIdDetallePedido() {
        return this.idDetallePedido;
    }
    public void setIdDetallePedido(String idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }
    public String getIdPedido() {
        return this.idPedido;
    }
    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }
    public String getClaveArticulo() {
        return this.claveArticulo;
    }
    public void setClaveArticulo(String claveArticulo) {
        this.claveArticulo = claveArticulo;
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
    public float getPrecio() {
        return this.precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public float getSubtotal() {
        return this.subtotal;
    }
    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
    public float getIva() {
        return this.iva;
    }
    public void setIva(float iva) {
        this.iva = iva;
    }
    public float getTotal() {
        return this.total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    public String getUsuarioActualizacion() {
        return this.usuarioActualizacion;
    }
    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }
}
