/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.innovati.felipehernandez.invenenvios.clases.dao;

import java.util.Date;
import com.innovati.felipehernandez.invenenvios.clases.dto.*;
import com.innovati.felipehernandez.invenenvios.clases.exceptions.*;
import java.sql.CallableStatement;

public interface VwPedidosDao
{
	/** 
	 * Returns all rows from the vwPedidos table that match the criteria ''.
	 */
	public VwPedidos[] findAll() throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'IdPedido = :idPedido'.
	 */
	public VwPedidos[] findWhereIdPedidoEquals(String idPedido) throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'IdUsuario = :idUsuario'.
	 */
	public VwPedidos[] findWhereIdUsuarioEquals(String idUsuario) throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Folio = :folio'.
	 */
	public VwPedidos[] findWhereFolioEquals(int folio) throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'ClaveCliente = :claveCliente'.
	 */
	public VwPedidos[] findWhereClaveClienteEquals(String claveCliente) throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Nombre = :nombre'.
	 */
	public VwPedidos[] findWhereNombreEquals(String nombre) throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Fecha = :fecha'.
	 */
	public VwPedidos[] findWhereFechaEquals(Date fecha) throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Estatus = :estatus'.
	 */
	public VwPedidos[] findWhereEstatusEquals(short estatus) throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Subtotal = :subtotal'.
	 */
	public VwPedidos[] findWhereSubtotalEquals(float subtotal) throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'IVA = :iva'.
	 */
	public VwPedidos[] findWhereIvaEquals(float iva) throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Total = :total'.
	 */
	public VwPedidos[] findWhereTotalEquals(float total) throws VwPedidosDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the vwPedidos table that match the specified arbitrary SQL statement
	 */
	public VwPedidos[] findByDynamicSelect(String sql, Object[] sqlParams) throws VwPedidosDaoException;

	/** 
	 * Returns all rows from the vwPedidos table that match the specified arbitrary SQL statement
	 */
	public VwPedidos[] findByDynamicWhere(String sql, Object[] sqlParams) throws VwPedidosDaoException;

}
