/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.innovati.felipehernandez.invenenvios.clases.dto;

import com.innovati.felipehernandez.invenenvios.clases.dao.*;
import com.innovati.felipehernandez.invenenvios.clases.factory.*;
import com.innovati.felipehernandez.invenenvios.clases.exceptions.*;
import java.io.Serializable;

public class VwAgente implements Serializable
{
	/** 
	 * This attribute maps to the column Clave in the vwAgente table.
	 */
	protected String clave;

	/** 
	 * This attribute maps to the column Nombre in the vwAgente table.
	 */
	protected String nombre;

	/** 
	 * This attribute maps to the column Telefono in the vwAgente table.
	 */
	protected String telefono;

	/** 
	 * This attribute maps to the column Celular in the vwAgente table.
	 */
	protected String celular;

	/** 
	 * This attribute maps to the column Comision in the vwAgente table.
	 */
	protected double comision;

	/** 
	 * This attribute represents whether the primitive attribute comision is null.
	 */
	protected boolean comisionNull = true;

	/** 
	 * This attribute maps to the column Email in the vwAgente table.
	 */
	protected String email;

	/** 
	 * This attribute maps to the column Sucursal in the vwAgente table.
	 */
	protected String sucursal;

	/**
	 * Method 'VwAgente_I'
	 * 
	 */
	public VwAgente()
	{
	}

	/**
	 * Method 'getClave'
	 * 
	 * @return String
	 */
	public String getClave()
	{
		return clave;
	}

	/**
	 * Method 'setClave'
	 * 
	 * @param clave
	 */
	public void setClave(String clave)
	{
		this.clave = clave;
	}

	/**
	 * Method 'getNombre'
	 * 
	 * @return String
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * Method 'setNombre'
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	/**
	 * Method 'getTelefono'
	 * 
	 * @return String
	 */
	public String getTelefono()
	{
		return telefono;
	}

	/**
	 * Method 'setTelefono'
	 * 
	 * @param telefono
	 */
	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	/**
	 * Method 'getCelular'
	 * 
	 * @return String
	 */
	public String getCelular()
	{
		return celular;
	}

	/**
	 * Method 'setCelular'
	 * 
	 * @param celular
	 */
	public void setCelular(String celular)
	{
		this.celular = celular;
	}

	/**
	 * Method 'getComision'
	 * 
	 * @return double
	 */
	public double getComision()
	{
		return comision;
	}

	/**
	 * Method 'setComision'
	 * 
	 * @param comision
	 */
	public void setComision(double comision)
	{
		this.comision = comision;
		this.comisionNull = false;
	}

	/**
	 * Method 'setComisionNull'
	 * 
	 * @param value
	 */
	public void setComisionNull(boolean value)
	{
		this.comisionNull = value;
	}

	/**
	 * Method 'isComisionNull'
	 * 
	 * @return boolean
	 */
	public boolean isComisionNull()
	{
		return comisionNull;
	}

	/**
	 * Method 'getEmail'
	 * 
	 * @return String
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Method 'setEmail'
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Method 'getSucursal'
	 * 
	 * @return String
	 */
	public String getSucursal()
	{
		return sucursal;
	}

	/**
	 * Method 'setSucursal'
	 * 
	 * @param sucursal
	 */
	public void setSucursal(String sucursal)
	{
		this.sucursal = sucursal;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof VwAgente)) {
			return false;
		}
		
		final VwAgente _cast = (VwAgente) _other;
		if (clave == null ? _cast.clave != clave : !clave.equals( _cast.clave )) {
			return false;
		}
		
		if (nombre == null ? _cast.nombre != nombre : !nombre.equals( _cast.nombre )) {
			return false;
		}
		
		if (telefono == null ? _cast.telefono != telefono : !telefono.equals( _cast.telefono )) {
			return false;
		}
		
		if (celular == null ? _cast.celular != celular : !celular.equals( _cast.celular )) {
			return false;
		}
		
		if (comision != _cast.comision) {
			return false;
		}
		
		if (comisionNull != _cast.comisionNull) {
			return false;
		}
		
		if (email == null ? _cast.email != email : !email.equals( _cast.email )) {
			return false;
		}
		
		if (sucursal == null ? _cast.sucursal != sucursal : !sucursal.equals( _cast.sucursal )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		if (clave != null) {
			_hashCode = 29 * _hashCode + clave.hashCode();
		}
		
		if (nombre != null) {
			_hashCode = 29 * _hashCode + nombre.hashCode();
		}
		
		if (telefono != null) {
			_hashCode = 29 * _hashCode + telefono.hashCode();
		}
		
		if (celular != null) {
			_hashCode = 29 * _hashCode + celular.hashCode();
		}
		
		long temp_comision = Double.doubleToLongBits(comision);
		_hashCode = 29 * _hashCode + (int) (temp_comision ^ (temp_comision >>> 32));
		_hashCode = 29 * _hashCode + (comisionNull ? 1 : 0);
		if (email != null) {
			_hashCode = 29 * _hashCode + email.hashCode();
		}
		
		if (sucursal != null) {
			_hashCode = 29 * _hashCode + sucursal.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.innovati.felipehernandez.invenenvios.dto.VwAgente_I: " );
		ret.append( "clave=" + clave );
		ret.append( ", nombre=" + nombre );
		ret.append( ", telefono=" + telefono );
		ret.append( ", celular=" + celular );
		ret.append( ", comision=" + comision );
		ret.append( ", email=" + email );
		ret.append( ", sucursal=" + sucursal );
		return ret.toString();
	}

}
