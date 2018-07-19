package com.innovati.felipehernandez.invenenvios.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Usuarios_I
{
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long Id;
    private String idUsuario;
    private int clave;
    private String nickname;
    private String password;
    private short estatus;
    private Date ultimaFechaActualizacion;
    private String ultimoUsuarioActualizacion;
    @Generated(hash = 1679109034)
    public Usuarios_I(Long Id, String idUsuario, int clave, String nickname,
                      String password, short estatus, Date ultimaFechaActualizacion,
                      String ultimoUsuarioActualizacion) {
        this.Id = Id;
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.nickname = nickname;
        this.password = password;
        this.estatus = estatus;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
        this.ultimoUsuarioActualizacion = ultimoUsuarioActualizacion;
    }
    @Generated(hash = 225815021)
    public Usuarios_I() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getIdUsuario() {
        return this.idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getClave() {
        return this.clave;
    }
    public void setClave(int clave) {
        this.clave = clave;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public short getEstatus() {
        return this.estatus;
    }
    public void setEstatus(short estatus) {
        this.estatus = estatus;
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
