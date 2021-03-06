/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.innovati.felipehernandez.invenenvios.clases.dao;

import com.innovati.felipehernandez.invenenvios.clases.dto.*;
import com.innovati.felipehernandez.invenenvios.clases.exceptions.*;

public interface VwClientesDao
{
	/** 
	 * Returns all rows from the vwClientes table that match the criteria ''.
	 */
	public VwClientes[] findAll() throws VwClientesDaoException;

	/** 
	 * Returns all rows from the vwClientes table that match the criteria 'Clave = :clave'.
	 */
	public VwClientes[] findWhereClaveEquals(String clave) throws VwClientesDaoException;

	/** 
	 * Returns all rows from the vwClientes table that match the criteria 'Nombre = :nombre'.
	 */
	public VwClientes[] findWhereNombreEquals(String nombre) throws VwClientesDaoException;

	/** 
	 * Returns all rows from the vwClientes table that match the criteria 'RFC = :rfc'.
	 */
	public VwClientes[] findWhereRfcEquals(String rfc) throws VwClientesDaoException;

	/** 
	 * Returns all rows from the vwClientes table that match the criteria 'Calle = :calle'.
	 */
	public VwClientes[] findWhereCalleEquals(String calle) throws VwClientesDaoException;

	/** 
	 * Returns all rows from the vwClientes table that match the criteria 'NumeroExterior = :numeroExterior'.
	 */
	public VwClientes[] findWhereNumeroExteriorEquals(String numeroExterior) throws VwClientesDaoException;

	/** 
	 * Returns all rows from the vwClientes table that match the criteria 'NumeroInterior = :numeroInterior'.
	 */
	public VwClientes[] findWhereNumeroInteriorEquals(String numeroInterior) throws VwClientesDaoException;

	/** 
	 * Returns all rows from the vwClientes table that match the criteria 'Colonia = :colonia'.
	 */
	public VwClientes[] findWhereColoniaEquals(String colonia) throws VwClientesDaoException;

	/** 
	 * Returns all rows from the vwClientes table that match the criteria 'Telefono = :telefono'.
	 */
	public VwClientes[] findWhereTelefonoEquals(String telefono) throws VwClientesDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the vwClientes table that match the specified arbitrary SQL statement
	 */
	public VwClientes[] findByDynamicSelect(String sql, Object[] sqlParams) throws VwClientesDaoException;

	/** 
	 * Returns all rows from the vwClientes table that match the specified arbitrary SQL statement
	 */
	public VwClientes[] findByDynamicWhere(String sql, Object[] sqlParams) throws VwClientesDaoException;

}
