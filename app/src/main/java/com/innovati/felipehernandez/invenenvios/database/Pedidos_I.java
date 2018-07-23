package com.innovati.felipehernandez.invenenvios.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Pedidos_I
{
    @Id(autoincrement = true)
    private Long Id;
    private String idPedido;
    private String idUsuario;
    private int folio;
    private String claveCliente;
    private Date fecha;
    private short estatus;
    private float subtotal;
    private float iva;
    private float total;
    private String observaciones;
    private Date ultimaFechaActualizacion;
    private String ultimoUsuarioActualizacion;
    @Generated(hash = 1719534996)
    public Pedidos_I(Long Id, String idPedido, String idUsuario, int folio,
            String claveCliente, Date fecha, short estatus, float subtotal,
            float iva, float total, String observaciones,
            Date ultimaFechaActualizacion, String ultimoUsuarioActualizacion) {
        this.Id = Id;
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.folio = folio;
        this.claveCliente = claveCliente;
        this.fecha = fecha;
        this.estatus = estatus;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.observaciones = observaciones;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
        this.ultimoUsuarioActualizacion = ultimoUsuarioActualizacion;
    }
    @Generated(hash = 1028724488)
    public Pedidos_I() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getIdPedido() {
        return this.idPedido;
    }
    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }
    public String getIdUsuario() {
        return this.idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getFolio() {
        return this.folio;
    }
    public void setFolio(int folio) {
        this.folio = folio;
    }
    public String getClaveCliente() {
        return this.claveCliente;
    }
    public void setClaveCliente(String claveCliente) {
        this.claveCliente = claveCliente;
    }
    public Date getFecha() {
        return this.fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public short getEstatus() {
        return this.estatus;
    }
    public void setEstatus(short estatus) {
        this.estatus = estatus;
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
    public String getObservaciones() {
        return this.observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
