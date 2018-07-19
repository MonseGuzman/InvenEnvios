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
    private float precio2;
    private float precio3;
    private float precio4;
    private float precio5;
    private String tipoImpuesto;
    private String grupo;
    private String tipo;
    private String status;
    private String ubicacion;
    private String moneda;
    private String tiempoSurtido;
    private String pedido;
    private float  costoPromedio;
    private float ultimoCosto;
    private float costoReposicion;
    private float existenciaTotal;
    private String activo;
    private String clase;
    private String cubicaje;
    private String peso;
    private String equivalencia;
    @Generated(hash = 988988148)
    public VwArticulos_I(Long Id, String clave, String nombre,
            String unidadPrimaria, float precio1, float precio2, float precio3,
            float precio4, float precio5, String tipoImpuesto, String grupo,
            String tipo, String status, String ubicacion, String moneda,
            String tiempoSurtido, String pedido, float costoPromedio,
            float ultimoCosto, float costoReposicion, float existenciaTotal,
            String activo, String clase, String cubicaje, String peso,
            String equivalencia) {
        this.Id = Id;
        this.clave = clave;
        this.nombre = nombre;
        this.unidadPrimaria = unidadPrimaria;
        this.precio1 = precio1;
        this.precio2 = precio2;
        this.precio3 = precio3;
        this.precio4 = precio4;
        this.precio5 = precio5;
        this.tipoImpuesto = tipoImpuesto;
        this.grupo = grupo;
        this.tipo = tipo;
        this.status = status;
        this.ubicacion = ubicacion;
        this.moneda = moneda;
        this.tiempoSurtido = tiempoSurtido;
        this.pedido = pedido;
        this.costoPromedio = costoPromedio;
        this.ultimoCosto = ultimoCosto;
        this.costoReposicion = costoReposicion;
        this.existenciaTotal = existenciaTotal;
        this.activo = activo;
        this.clase = clase;
        this.cubicaje = cubicaje;
        this.peso = peso;
        this.equivalencia = equivalencia;
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
    public float getPrecio2() {
        return this.precio2;
    }
    public void setPrecio2(float precio2) {
        this.precio2 = precio2;
    }
    public float getPrecio3() {
        return this.precio3;
    }
    public void setPrecio3(float precio3) {
        this.precio3 = precio3;
    }
    public float getPrecio4() {
        return this.precio4;
    }
    public void setPrecio4(float precio4) {
        this.precio4 = precio4;
    }
    public float getPrecio5() {
        return this.precio5;
    }
    public void setPrecio5(float precio5) {
        this.precio5 = precio5;
    }
    public String getTipoImpuesto() {
        return this.tipoImpuesto;
    }
    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }
    public String getGrupo() {
        return this.grupo;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public String getTipo() {
        return this.tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUbicacion() {
        return this.ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public String getMoneda() {
        return this.moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public String getTiempoSurtido() {
        return this.tiempoSurtido;
    }
    public void setTiempoSurtido(String tiempoSurtido) {
        this.tiempoSurtido = tiempoSurtido;
    }
    public String getPedido() {
        return this.pedido;
    }
    public void setPedido(String pedido) {
        this.pedido = pedido;
    }
    public float getCostoPromedio() {
        return this.costoPromedio;
    }
    public void setCostoPromedio(float costoPromedio) {
        this.costoPromedio = costoPromedio;
    }
    public float getUltimoCosto() {
        return this.ultimoCosto;
    }
    public void setUltimoCosto(float ultimoCosto) {
        this.ultimoCosto = ultimoCosto;
    }
    public float getCostoReposicion() {
        return this.costoReposicion;
    }
    public void setCostoReposicion(float costoReposicion) {
        this.costoReposicion = costoReposicion;
    }
    public float getExistenciaTotal() {
        return this.existenciaTotal;
    }
    public void setExistenciaTotal(float existenciaTotal) {
        this.existenciaTotal = existenciaTotal;
    }
    public String getActivo() {
        return this.activo;
    }
    public void setActivo(String activo) {
        this.activo = activo;
    }
    public String getClase() {
        return this.clase;
    }
    public void setClase(String clase) {
        this.clase = clase;
    }
    public String getCubicaje() {
        return this.cubicaje;
    }
    public void setCubicaje(String cubicaje) {
        this.cubicaje = cubicaje;
    }
    public String getPeso() {
        return this.peso;
    }
    public void setPeso(String peso) {
        this.peso = peso;
    }
    public String getEquivalencia() {
        return this.equivalencia;
    }
    public void setEquivalencia(String equivalencia) {
        this.equivalencia = equivalencia;
    }

}
