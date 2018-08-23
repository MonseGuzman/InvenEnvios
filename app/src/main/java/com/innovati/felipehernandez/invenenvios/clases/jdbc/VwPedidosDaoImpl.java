/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.innovati.felipehernandez.invenenvios.clases.jdbc;

import com.innovati.felipehernandez.invenenvios.clases.dao.*;
import com.innovati.felipehernandez.invenenvios.clases.factory.*;
import java.util.Date;
import com.innovati.felipehernandez.invenenvios.clases.dto.*;
import com.innovati.felipehernandez.invenenvios.clases.exceptions.*;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.CallableStatement;

public class VwPedidosDaoImpl extends AbstractDAO implements VwPedidosDao
{
	/** 
	 * The factory class for this DAO has two versions of the create() method - one that
takes no arguments and one that takes a Connection argument. If the Connection version
is chosen then the connection will be stored in this attribute and will be used by all
calls to this DAO, otherwise a new Connection will be allocated for each operation.
	 */
	protected Connection userConn;

	/** 
	 * All finder methods in this class use this SELECT constant to build their queries
	 */
	protected final String SQL_SELECT = "SELECT IdPedido, IdUsuario, Folio, ClaveCliente, Nombre, Fecha, Estatus, Subtotal, IVA, Total FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * Index of column IdPedido
	 */
	protected static final int COLUMN_ID_PEDIDO = 1;

	/** 
	 * Index of column IdUsuario
	 */
	protected static final int COLUMN_ID_USUARIO = 2;

	/** 
	 * Index of column Folio
	 */
	protected static final int COLUMN_FOLIO = 3;

	/** 
	 * Index of column ClaveCliente
	 */
	protected static final int COLUMN_CLAVE_CLIENTE = 4;

	/** 
	 * Index of column Nombre
	 */
	protected static final int COLUMN_NOMBRE = 5;

	/** 
	 * Index of column Fecha
	 */
	protected static final int COLUMN_FECHA = 6;

	/** 
	 * Index of column Estatus
	 */
	protected static final int COLUMN_ESTATUS = 7;

	/** 
	 * Index of column Subtotal
	 */
	protected static final int COLUMN_SUBTOTAL = 8;

	/** 
	 * Index of column IVA
	 */
	protected static final int COLUMN_IVA = 9;

	/** 
	 * Index of column Total
	 */
	protected static final int COLUMN_TOTAL = 10;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 10;

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria ''.
	 */
	public VwPedidos[] findAll() throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT, null );
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'IdPedido = :idPedido'.
	 */
	public VwPedidos[] findWhereIdPedidoEquals(String idPedido) throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE IdPedido = ? ORDER BY IdPedido", new Object[] { idPedido } );
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'IdUsuario = :idUsuario'.
	 */
	public VwPedidos[] findWhereIdUsuarioEquals(String idUsuario) throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE IdUsuario = ? ORDER BY IdUsuario", new Object[] { idUsuario } );
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Folio = :folio'.
	 */
	public VwPedidos[] findWhereFolioEquals(int folio) throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Folio = ? ORDER BY Folio", new Object[] {  new Integer(folio) } );
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'ClaveCliente = :claveCliente'.
	 */
	public VwPedidos[] findWhereClaveClienteEquals(String claveCliente) throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ClaveCliente = ? ORDER BY ClaveCliente", new Object[] { claveCliente } );
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Nombre = :nombre'.
	 */
	public VwPedidos[] findWhereNombreEquals(String nombre) throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Nombre = ? ORDER BY Nombre", new Object[] { nombre } );
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Fecha = :fecha'.
	 */
	public VwPedidos[] findWhereFechaEquals(Date fecha) throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Fecha = ? ORDER BY Fecha", new Object[] { fecha==null ? null : new java.sql.Timestamp( fecha.getTime() ) } );
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Estatus = :estatus'.
	 */
	public VwPedidos[] findWhereEstatusEquals(short estatus) throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Estatus = ? ORDER BY Folio", new Object[] {  new Short(estatus) } );
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Subtotal = :subtotal'.
	 */
	public VwPedidos[] findWhereSubtotalEquals(float subtotal) throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Subtotal = ? ORDER BY Folio", new Object[] {  new Float(subtotal) } );
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'IVA = :iva'.
	 */
	public VwPedidos[] findWhereIvaEquals(float iva) throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE IVA = ? ORDER BY IVA", new Object[] {  new Float(iva) } );
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the criteria 'Total = :total'.
	 */
	public VwPedidos[] findWhereTotalEquals(float total) throws VwPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Total = ? ORDER BY Total", new Object[] {  new Float(total) } );
	}

	/**
	 * Method 'VwPedidosDaoImpl'
	 * 
	 */
	public VwPedidosDaoImpl()
	{
	}

	/**
	 * Method 'VwPedidosDaoImpl'
	 * 
	 * @param userConn
	 */
	public VwPedidosDaoImpl(final Connection userConn)
	{
		this.userConn = userConn;
	}

	/** 
	 * Sets the value of maxRows
	 */
	public void setMaxRows(int maxRows)
	{
		this.maxRows = maxRows;
	}

	/** 
	 * Gets the value of maxRows
	 */
	public int getMaxRows()
	{
		return maxRows;
	}

	/**
	 * Method 'getTableName'
	 * 
	 * @return String
	 */
	public String getTableName()
	{
		return "Inven_E..vwPedidos";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected VwPedidos fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			VwPedidos dto = new VwPedidos();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected VwPedidos[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			VwPedidos dto = new VwPedidos();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		VwPedidos ret[] = new VwPedidos[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(VwPedidos dto, ResultSet rs) throws SQLException
	{
		dto.setIdPedido( rs.getString( COLUMN_ID_PEDIDO ) );
		dto.setIdUsuario( rs.getString( COLUMN_ID_USUARIO ) );
		dto.setFolio( rs.getInt( COLUMN_FOLIO ) );
		if (rs.wasNull()) {
			dto.setFolioNull( true );
		}
		
		dto.setClaveCliente( rs.getString( COLUMN_CLAVE_CLIENTE ) );
		dto.setNombre( rs.getString( COLUMN_NOMBRE ) );
		dto.setFecha( rs.getTimestamp(COLUMN_FECHA ) );
		dto.setEstatus( rs.getShort( COLUMN_ESTATUS ) );
		if (rs.wasNull()) {
			dto.setEstatusNull( true );
		}
		
		dto.setSubtotal( rs.getFloat( COLUMN_SUBTOTAL ) );
		if (rs.wasNull()) {
			dto.setSubtotalNull( true );
		}
		
		dto.setIva( rs.getFloat( COLUMN_IVA ) );
		if (rs.wasNull()) {
			dto.setIvaNull( true );
		}
		
		dto.setTotal( rs.getFloat( COLUMN_TOTAL ) );
		if (rs.wasNull()) {
			dto.setTotalNull( true );
		}
		
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(VwPedidos dto)
	{
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the specified arbitrary SQL statement
	 */
	public VwPedidos[] findByDynamicSelect(String sql, Object[] sqlParams) throws VwPedidosDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new VwPedidosDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	/** 
	 * Returns all rows from the vwPedidos table that match the specified arbitrary SQL statement
	 */
	public VwPedidos[] findByDynamicWhere(String sql, Object[] sqlParams) throws VwPedidosDaoException
	{
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			// construct the SQL statement
			final String SQL = SQL_SELECT + " WHERE " + sql;
		
		
			System.out.println( "Executing " + SQL );
			// prepare statement
			stmt = conn.prepareStatement( SQL );
			stmt.setMaxRows( maxRows );
		
			// bind parameters
			for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}
		
		
			rs = stmt.executeQuery();
		
			// fetch the results
			return fetchMultiResults(rs);
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new VwPedidosDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

}
