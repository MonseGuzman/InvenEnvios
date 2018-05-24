/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.innovati.felipehernandez.invenenvios.innovati.felipehernandez.invenenvios.dao;

import java.util.Date;
import com.innovati.felipehernandez.invenenvios.dto.*;
import com.innovati.felipehernandez.invenenvios.exceptions.*;
import java.sql.CallableStatement;

public interface VwUsuariosDao
{
	/** 
	 * Returns all rows from the vwUsuarios table that match the criteria ''.
	 */
	public VwUsuarios[] findAll() throws VwUsuariosDaoException;

	/** 
	 * Returns all rows from the vwUsuarios table that match the criteria 'IdUsuario = :idUsuario'.
	 */
	public VwUsuarios[] findWhereIdUsuarioEquals(String idUsuario) throws VwUsuariosDaoException;

	/** 
	 * Returns all rows from the vwUsuarios table that match the criteria 'Clave = :clave'.
	 */
	public VwUsuarios[] findWhereClaveEquals(String clave) throws VwUsuariosDaoException;

	/** 
	 * Returns all rows from the vwUsuarios table that match the criteria 'NickName = :nickName'.
	 */
	public VwUsuarios[] findWhereNickNameEquals(String nickName) throws VwUsuariosDaoException;

	/** 
	 * Returns all rows from the vwUsuarios table that match the criteria 'Password = :password'.
	 */
	public VwUsuarios[] findWherePasswordEquals(String password) throws VwUsuariosDaoException;

	/** 
	 * Returns all rows from the vwUsuarios table that match the criteria 'Status = :status'.
	 */
	public VwUsuarios[] findWhereStatusEquals(short status) throws VwUsuariosDaoException;

	/** 
	 * Returns all rows from the vwUsuarios table that match the criteria 'FechaActualizacion = :fechaActualizacion'.
	 */
	public VwUsuarios[] findWhereFechaActualizacionEquals(Date fechaActualizacion) throws VwUsuariosDaoException;

	/** 
	 * Returns all rows from the vwUsuarios table that match the criteria 'IdUsuarioActualizacion = :idUsuarioActualizacion'.
	 */
	public VwUsuarios[] findWhereIdUsuarioActualizacionEquals(String idUsuarioActualizacion) throws VwUsuariosDaoException;

	/** 
	 * Returns all rows from the vwUsuarios table that match the criteria 'Sucursal = :sucursal'.
	 */
	public VwUsuarios[] findWhereSucursalEquals(String sucursal) throws VwUsuariosDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the vwUsuarios table that match the specified arbitrary SQL statement
	 */
	public VwUsuarios[] findByDynamicSelect(String sql, Object[] sqlParams) throws VwUsuariosDaoException;

	/** 
	 * Returns all rows from the vwUsuarios table that match the specified arbitrary SQL statement
	 */
	public VwUsuarios[] findByDynamicWhere(String sql, Object[] sqlParams) throws VwUsuariosDaoException;

}
