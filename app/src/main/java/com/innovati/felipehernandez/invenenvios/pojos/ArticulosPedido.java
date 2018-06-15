package com.innovati.felipehernandez.invenenvios.pojos;

public class ArticulosPedido {
    String idArticulo;
    String nombre;
    String presentacion;
    float cantidad;
    float precio;
    float subTotal;

    public ArticulosPedido() {
    }

    public ArticulosPedido(String idArticulo, String nombre, String presentacion, float cantidad, float precio, float subTotal) {
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
}
