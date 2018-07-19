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

public interface DetallesPedidosDao
{
	/** 
	 * Inserts a new row in the DetallesPedidos_I table.
	 */
	public void insert(DetallesPedidos dto) throws DetallesPedidosDaoException;

	public void update(DetallesPedidos dto, String sql, String[] sqlParams) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria ''.
	 */
	public DetallesPedidos[] findAll() throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria 'IdDetallePedido = :idDetallePedido'.
	 */
	public DetallesPedidos[] findWhereIdDetallePedidoEquals(String idDetallePedido) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria 'IdPedido = :idPedido'.
	 */
	public DetallesPedidos[] findWhereIdPedidoEquals(String idPedido) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria 'ClaveArticulo = :claveArticulo'.
	 */
	public DetallesPedidos[] findWhereClaveArticuloEquals(String claveArticulo) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria 'Cantidad = :cantidad'.
	 */
	public DetallesPedidos[] findWhereCantidadEquals(double cantidad) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria 'Precio = :precio'.
	 */
	public DetallesPedidos[] findWherePrecioEquals(float precio) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria 'Subtotal = :subtotal'.
	 */
	public DetallesPedidos[] findWhereSubtotalEquals(float subtotal) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria 'IVA = :iva'.
	 */
	public DetallesPedidos[] findWhereIvaEquals(float iva) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria 'Total = :total'.
	 */
	public DetallesPedidos[] findWhereTotalEquals(float total) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria 'UltimaFechaActualizacion = :ultimaFechaActualizacion'.
	 */
	public DetallesPedidos[] findWhereUltimaFechaActualizacionEquals(Date ultimaFechaActualizacion) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the criteria 'UltimoUsuarioActualizacion = :ultimoUsuarioActualizacion'.
	 */
	public DetallesPedidos[] findWhereUltimoUsuarioActualizacionEquals(String ultimoUsuarioActualizacion) throws DetallesPedidosDaoException;

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows);

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows();

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the specified arbitrary SQL statement
	 */
	public DetallesPedidos[] findByDynamicSelect(String sql, Object[] sqlParams) throws DetallesPedidosDaoException;

	/** 
	 * Returns all rows from the DetallesPedidos_I table that match the specified arbitrary SQL statement
	 */
	public DetallesPedidos[] findByDynamicWhere(String sql, Object[] sqlParams) throws DetallesPedidosDaoException;

}
