/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.innovati.felipehernandez.invenenvios.clases.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the Pedidos table.
 */
public class PedidosPk implements Serializable
{
	protected String idPedido;

	/** 
	 * Sets the value of idPedido
	 */
	public void setIdPedido(String idPedido)
	{
		this.idPedido = idPedido;
	}

	/** 
	 * Gets the value of idPedido
	 */
	public String getIdPedido()
	{
		return idPedido;
	}

	/**
	 * Method 'PedidosPk'
	 * 
	 */
	public PedidosPk()
	{
	}

	/**
	 * Method 'PedidosPk'
	 * 
	 * @param idPedido
	 */
	public PedidosPk(final String idPedido)
	{
		this.idPedido = idPedido;
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
		
		if (!(_other instanceof PedidosPk)) {
			return false;
		}
		
		final PedidosPk _cast = (PedidosPk) _other;
		if (idPedido == null ? _cast.idPedido != idPedido : !idPedido.equals( _cast.idPedido )) {
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
		if (idPedido != null) {
			_hashCode = 29 * _hashCode + idPedido.hashCode();
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
		ret.append( "com.innovati.felipehernandez.invenenvios.clases.dto.PedidosPk: " );
		ret.append( "idPedido=" + idPedido );
		return ret.toString();
	}

}
