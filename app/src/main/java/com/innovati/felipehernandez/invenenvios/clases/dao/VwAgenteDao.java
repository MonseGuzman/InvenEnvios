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

public interface VwAgenteDao
{
	/** 
	 * Returns all rows from the vwAgente table that match the criteria ''.
	 */
	public VwAgente[] findAll() throws VwAgenteDaoException;

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Clave = :clave'.
	 */
	public VwAgente[] findWhereClaveEquals(String clave) throws VwAgenteDaoException;

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Nombre = :nombre'.
	 */
	public VwAgente[] findWhereNombreEquals(String nombre) throws VwAgenteDaoException;

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Telefono = :telefono'.
	 */
	public VwAgente[] findWhereTelefonoEquals(String telefono) throws VwAgenteDaoException;

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Celular = :celular'.
	 */
	public VwAgente[] findWhereCelularEquals(String celular) throws VwAgenteDaoException;

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Comision = :comision'.
	 */
	public VwAgente[] findWhereComisionEquals(double comision) throws VwAgenteDaoException;

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Email = :email'.
	 */
	public VwAgente[] findWhereEmailEquals(String email) throws VwAgenteDaoException;

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Sucursal = :sucursal'.
	 */
	public VwAgente[] findWhereSucursalEquals(String sucursal) throws VwAgenteDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the vwAgente table that match the specified arbitrary SQL statement
	 */
	public VwAgente[] findByDynamicSelect(String sql, Object[] sqlParams) throws VwAgenteDaoException;

	/** 
	 * Returns all rows from the vwAgente table that match the specified arbitrary SQL statement
	 */
	public VwAgente[] findByDynamicWhere(String sql, Object[] sqlParams) throws VwAgenteDaoException;

}
