package com.innovati.felipehernandez.invenenvios;

import java.util.ArrayList;

public class Clientes
{
    private String nombre;
    private String RFC;
    private String telefono;

    public Clientes() {
    }

    public Clientes(String nombre, String RFC, String telefono)
    {
        this.nombre = nombre;
        this.RFC = RFC;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
