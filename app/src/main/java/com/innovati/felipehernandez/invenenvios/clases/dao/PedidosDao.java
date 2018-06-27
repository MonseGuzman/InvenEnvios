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

public interface PedidosDao
{
	/** 
	 * Inserts a new row in the Pedidos table.
	 */
	public void insert(Pedidos dto) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria ''.
	 */
	public Pedidos[] findAll() throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'IdPedido = :idPedido'.
	 */
	public Pedidos[] findWhereIdPedidoEquals(String idPedido) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'IdUsuario = :idUsuario'.
	 */
	public Pedidos[] findWhereIdUsuarioEquals(String idUsuario) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'ClaveCliente = :claveCliente'.
	 */
	public Pedidos[] findWhereClaveClienteEquals(String claveCliente) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'Fecha = :fecha'.
	 */
	public Pedidos[] findWhereFechaEquals(Date fecha) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'Estatus = :estatus'.
	 */
	public Pedidos[] findWhereEstatusEquals(short estatus) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'Subtotal = :subtotal'.
	 */
	public Pedidos[] findWhereSubtotalEquals(float subtotal) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'IVA = :iva'.
	 */
	public Pedidos[] findWhereIvaEquals(float iva) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'Total = :total'.
	 */
	public Pedidos[] findWhereTotalEquals(float total) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'Observaciones = :observaciones'.
	 */
	public Pedidos[] findWhereObservacionesEquals(String observaciones) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'UltimaFechaActualizacion = :ultimaFechaActualizacion'.
	 */
	public Pedidos[] findWhereUltimaFechaActualizacionEquals(Date ultimaFechaActualizacion) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the criteria 'UltimoUsuarioActualizacion = :ultimoUsuarioActualizacion'.
	 */
	public Pedidos[] findWhereUltimoUsuarioActualizacionEquals(String ultimoUsuarioActualizacion) throws PedidosDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the Pedidos table that match the specified arbitrary SQL statement
	 */
	public Pedidos[] findByDynamicSelect(String sql, Object[] sqlParams) throws PedidosDaoException;

	/** 
	 * Returns all rows from the Pedidos table that match the specified arbitrary SQL statement
	 */
	public Pedidos[] findByDynamicWhere(String sql, Object[] sqlParams) throws PedidosDaoException;

}
