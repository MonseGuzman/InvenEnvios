package com.innovati.felipehernandez.invenenvios.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VwClientes_I
{
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long Id;
    private String clave;
    private String nombre;
    private String rfc;
    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private String colonia;
    private String telefono;
    @Generated(hash = 324675601)
    public VwClientes_I(Long Id, String clave, String nombre, String rfc,
                        String calle, String numeroExterior, String numeroInterior,
                        String colonia, String telefono) {
        this.Id = Id;
        this.clave = clave;
        this.nombre = nombre;
        this.rfc = rfc;
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.colonia = colonia;
        this.telefono = telefono;
    }
    @Generated(hash = 963916258)
    public VwClientes_I() {
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
    public String getRfc() {
        return this.rfc;
    }
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    public String getCalle() {
        return this.calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getNumeroExterior() {
        return this.numeroExterior;
    }
    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }
    public String getNumeroInterior() {
        return this.numeroInterior;
    }
    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }
    public String getColonia() {
        return this.colonia;
    }
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    public String getTelefono() {
        return this.telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
