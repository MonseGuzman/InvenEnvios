package com.innovati.felipehernandez.invenenvios;

public class Clientes
{
    private String nombre;
    private String RFC;

    public Clientes(String nombre, String RFC)
    {
        this.nombre = nombre;
        this.RFC = RFC;
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
}
