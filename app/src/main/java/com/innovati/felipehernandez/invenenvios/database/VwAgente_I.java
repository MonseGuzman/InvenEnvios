package com.innovati.felipehernandez.invenenvios.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VwAgente_I
{
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long Id;
    private String clave;
    private String nombre;
    private String telefono;
    private String celular;
    private String comision;
    private String email;
    private String sucursal;
    @Generated(hash = 215783339)
    public VwAgente_I(Long Id, String clave, String nombre, String telefono,
                      String celular, String comision, String email, String sucursal) {
        this.Id = Id;
        this.clave = clave;
        this.nombre = nombre;
        this.telefono = telefono;
        this.celular = celular;
        this.comision = comision;
        this.email = email;
        this.sucursal = sucursal;
    }
    @Generated(hash = 2109765552)
    public VwAgente_I() {
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
    public String getTelefono() {
        return this.telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCelular() {
        return this.celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getComision() {
        return this.comision;
    }
    public void setComision(String comision) {
        this.comision = comision;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSucursal() {
        return this.sucursal;
    }
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

}
