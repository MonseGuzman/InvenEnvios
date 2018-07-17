package com.innovati.felipehernandez.invenenvios.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DetallesPedidos
{
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long Id;
    private String idDetallePedido;
    private String idPedido;
    private String claveArticulo;
    private float cantidad;
    private float precio;
    private float subtotal;
    private float iva;
    private float total;
    private Date ultimaFechaActualizacion;
    private String ultimoUsuarioActualizacion;
    @Generated(hash = 388740536)
    public DetallesPedidos(Long Id, String idDetallePedido, String idPedido,
            String claveArticulo, float cantidad, float precio, float subtotal,
            float iva, float total, Date ultimaFechaActualizacion,
            String ultimoUsuarioActualizacion) {
        this.Id = Id;
        this.idDetallePedido = idDetallePedido;
        this.idPedido = idPedido;
        this.claveArticulo = claveArticulo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
        this.ultimoUsuarioActualizacion = ultimoUsuarioActualizacion;
    }
    @Generated(hash = 356426221)
    public DetallesPedidos() {
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
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }
    public String getUltimoUsuarioActualizacion() {
        return this.ultimoUsuarioActualizacion;
    }
    public void setUltimoUsuarioActualizacion(String ultimoUsuarioActualizacion) {
        this.ultimoUsuarioActualizacion = ultimoUsuarioActualizacion;
    }
}
