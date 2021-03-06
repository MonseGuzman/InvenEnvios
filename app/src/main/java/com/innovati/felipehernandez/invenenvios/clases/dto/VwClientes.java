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

public class VwClientes implements Serializable
{
	/** 
	 * This attribute maps to the column Clave in the vwClientes table.
	 */
	protected String clave;

	/** 
	 * This attribute maps to the column Nombre in the vwClientes table.
	 */
	protected String nombre;

	/** 
	 * This attribute maps to the column RFC in the vwClientes table.
	 */
	protected String rfc;

	/** 
	 * This attribute maps to the column Calle in the vwClientes table.
	 */
	protected String calle;

	/** 
	 * This attribute maps to the column NumeroExterior in the vwClientes table.
	 */
	protected String numeroExterior;

	/** 
	 * This attribute maps to the column NumeroInterior in the vwClientes table.
	 */
	protected String numeroInterior;

	/** 
	 * This attribute maps to the column Colonia in the vwClientes table.
	 */
	protected String colonia;

	/** 
	 * This attribute maps to the column Telefono in the vwClientes table.
	 */
	protected String telefono;

	/**
	 * Method 'VwClientes_I'
	 * 
	 */
	public VwClientes()
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
	 * Method 'getRfc'
	 * 
	 * @return String
	 */
	public String getRfc()
	{
		return rfc;
	}

	/**
	 * Method 'setRfc'
	 * 
	 * @param rfc
	 */
	public void setRfc(String rfc)
	{
		this.rfc = rfc;
	}

	/**
	 * Method 'getCalle'
	 * 
	 * @return String
	 */
	public String getCalle()
	{
		return calle;
	}

	/**
	 * Method 'setCalle'
	 * 
	 * @param calle
	 */
	public void setCalle(String calle)
	{
		this.calle = calle;
	}

	/**
	 * Method 'getNumeroExterior'
	 * 
	 * @return String
	 */
	public String getNumeroExterior()
	{
		return numeroExterior;
	}

	/**
	 * Method 'setNumeroExterior'
	 * 
	 * @param numeroExterior
	 */
	public void setNumeroExterior(String numeroExterior)
	{
		this.numeroExterior = numeroExterior;
	}

	/**
	 * Method 'getNumeroInterior'
	 * 
	 * @return String
	 */
	public String getNumeroInterior()
	{
		return numeroInterior;
	}

	/**
	 * Method 'setNumeroInterior'
	 * 
	 * @param numeroInterior
	 */
	public void setNumeroInterior(String numeroInterior)
	{
		this.numeroInterior = numeroInterior;
	}

	/**
	 * Method 'getColonia'
	 * 
	 * @return String
	 */
	public String getColonia()
	{
		return colonia;
	}

	/**
	 * Method 'setColonia'
	 * 
	 * @param colonia
	 */
	public void setColonia(String colonia)
	{
		this.colonia = colonia;
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
		
		if (!(_other instanceof VwClientes)) {
			return false;
		}
		
		final VwClientes _cast = (VwClientes) _other;
		if (clave == null ? _cast.clave != clave : !clave.equals( _cast.clave )) {
			return false;
		}
		
		if (nombre == null ? _cast.nombre != nombre : !nombre.equals( _cast.nombre )) {
			return false;
		}
		
		if (rfc == null ? _cast.rfc != rfc : !rfc.equals( _cast.rfc )) {
			return false;
		}
		
		if (calle == null ? _cast.calle != calle : !calle.equals( _cast.calle )) {
			return false;
		}
		
		if (numeroExterior == null ? _cast.numeroExterior != numeroExterior : !numeroExterior.equals( _cast.numeroExterior )) {
			return false;
		}
		
		if (numeroInterior == null ? _cast.numeroInterior != numeroInterior : !numeroInterior.equals( _cast.numeroInterior )) {
			return false;
		}
		
		if (colonia == null ? _cast.colonia != colonia : !colonia.equals( _cast.colonia )) {
			return false;
		}
		
		if (telefono == null ? _cast.telefono != telefono : !telefono.equals( _cast.telefono )) {
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
		
		if (rfc != null) {
			_hashCode = 29 * _hashCode + rfc.hashCode();
		}
		
		if (calle != null) {
			_hashCode = 29 * _hashCode + calle.hashCode();
		}
		
		if (numeroExterior != null) {
			_hashCode = 29 * _hashCode + numeroExterior.hashCode();
		}
		
		if (numeroInterior != null) {
			_hashCode = 29 * _hashCode + numeroInterior.hashCode();
		}
		
		if (colonia != null) {
			_hashCode = 29 * _hashCode + colonia.hashCode();
		}
		
		if (telefono != null) {
			_hashCode = 29 * _hashCode + telefono.hashCode();
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
		ret.append( "com.innovati.felipehernandez.invenenvios.dto.VwClientes_I: " );
		ret.append( "clave=" + clave );
		ret.append( ", nombre=" + nombre );
		ret.append( ", rfc=" + rfc );
		ret.append( ", calle=" + calle );
		ret.append( ", numeroExterior=" + numeroExterior );
		ret.append( ", numeroInterior=" + numeroInterior );
		ret.append( ", colonia=" + colonia );
		ret.append( ", telefono=" + telefono );
		return ret.toString();
	}

}
