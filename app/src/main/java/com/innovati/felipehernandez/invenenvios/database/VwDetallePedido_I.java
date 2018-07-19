package com.innovati.felipehernandez.invenenvios.database;

import org.greenrobot.greendao.annotation.Entity;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VwDetallePedido_I
{
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long Id;
    private String idUsuario;
    private String clave;
    private String nickName;
    private String password;
    private short status;
    private Date fechaActualizacion;
    private String idUsuarioActualizacion;
    private String sucursal;
    @Generated(hash = 1030152931)
    public VwDetallePedido_I(Long Id, String idUsuario, String clave, String nickName,
                             String password, short status, Date fechaActualizacion,
                             String idUsuarioActualizacion, String sucursal) {
        this.Id = Id;
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.nickName = nickName;
        this.password = password;
        this.status = status;
        this.fechaActualizacion = fechaActualizacion;
        this.idUsuarioActualizacion = idUsuarioActualizacion;
        this.sucursal = sucursal;
    }
    @Generated(hash = 292960178)
    public VwDetallePedido_I() {
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
    public String getClave() {
        return this.clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public short getStatus() {
        return this.status;
    }
    public void setStatus(short status) {
        this.status = status;
    }
    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    public String getIdUsuarioActualizacion() {
        return this.idUsuarioActualizacion;
    }
    public void setIdUsuarioActualizacion(String idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }
    public String getSucursal() {
        return this.sucursal;
    }
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }
}
