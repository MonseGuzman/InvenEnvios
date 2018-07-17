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

public class DetallesPedidosDaoImpl extends AbstractDAO implements DetallesPedidosDao
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
	protected final String SQL_SELECT = "SELECT IdDetallePedido, IdPedido, ClaveArticulo, Cantidad, Precio, Subtotal, IVA, Total, UltimaFechaActualizacion, UltimoUsuarioActualizacion FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * SQL INSERT statement for this table
	 */
	protected final String SQL_INSERT = "INSERT INTO " + getTableName() + " ( IdDetallePedido, IdPedido, ClaveArticulo, Cantidad, Precio, Subtotal, IVA, Total, UltimaFechaActualizacion, UltimoUsuarioActualizacion ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	protected final String SQL_UPDATE = "UPDATE " + getTableName() + "SET IdDetallePedido = ?, IdPedido = ?, ClaveArticulo = ?, Cantidad = ?, Precio = ?, Subtotal = ?, IVA = ?, Total = ?, UltimaFechaActualizacion = ?, UltimoUsuarioActualizacion = ?";

	/** 
	 * Index of column IdDetallePedido
	 */
	protected static final int COLUMN_ID_DETALLE_PEDIDO = 1;

	/** 
	 * Index of column IdPedido
	 */
	protected static final int COLUMN_ID_PEDIDO = 2;

	/** 
	 * Index of column ClaveArticulo
	 */
	protected static final int COLUMN_CLAVE_ARTICULO = 3;

	/** 
	 * Index of column Cantidad
	 */
	protected static final int COLUMN_CANTIDAD = 4;

	/** 
	 * Index of column Precio
	 */
	protected static final int COLUMN_PRECIO = 5;

	/** 
	 * Index of column Subtotal
	 */
	protected static final int COLUMN_SUBTOTAL = 6;

	/** 
	 * Index of column IVA
	 */
	protected static final int COLUMN_IVA = 7;

	/** 
	 * Index of column Total
	 */
	protected static final int COLUMN_TOTAL = 8;

	/** 
	 * Index of column UltimaFechaActualizacion
	 */
	protected static final int COLUMN_ULTIMA_FECHA_ACTUALIZACION = 9;

	/** 
	 * Index of column UltimoUsuarioActualizacion
	 */
	protected static final int COLUMN_ULTIMO_USUARIO_ACTUALIZACION = 10;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 10;

	/** 
	 * Inserts a new row in the DetallesPedidos table.
	 */
	public void insert(DetallesPedidos dto) throws DetallesPedidosDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
		
			stmt = conn.prepareStatement( SQL_INSERT );
			stmt.setString( COLUMN_ID_DETALLE_PEDIDO, dto.getIdDetallePedido() );
			stmt.setString( COLUMN_ID_PEDIDO, dto.getIdPedido() );
			stmt.setString( COLUMN_CLAVE_ARTICULO, dto.getClaveArticulo() );
			if (dto.isCantidadNull()) {
				stmt.setNull( COLUMN_CANTIDAD, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( COLUMN_CANTIDAD, dto.getCantidad() );
			}
		
			if (dto.isPrecioNull()) {
				stmt.setNull( COLUMN_PRECIO, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( COLUMN_PRECIO, dto.getPrecio() );
			}
		
			if (dto.isSubtotalNull()) {
				stmt.setNull( COLUMN_SUBTOTAL, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( COLUMN_SUBTOTAL, dto.getSubtotal() );
			}
		
			if (dto.isIvaNull()) {
				stmt.setNull( COLUMN_IVA, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( COLUMN_IVA, dto.getIva() );
			}
		
			if (dto.isTotalNull()) {
				stmt.setNull( COLUMN_TOTAL, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( COLUMN_TOTAL, dto.getTotal() );
			}
		
			stmt.setTimestamp(COLUMN_ULTIMA_FECHA_ACTUALIZACION, dto.getUltimaFechaActualizacion()==null ? null : new java.sql.Timestamp( dto.getUltimaFechaActualizacion().getTime() ) );
			stmt.setString( COLUMN_ULTIMO_USUARIO_ACTUALIZACION, dto.getUltimoUsuarioActualizacion() );
			System.out.println( "Executing " + SQL_INSERT + " with DTO: " + dto );
			stmt.execute();
			int rows = stmt.getUpdateCount();
			System.out.println( rows + " rows affected" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new DetallesPedidosDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}
		
		}
		
	}

	public void update(DetallesPedidos dto, String sql, String[] sqlParams) throws DetallesPedidosDaoException
	{
		long t1 = System.currentTimeMillis();
		// declare variables
		final boolean isConnSupplied = (userConn != null);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// get the user-specified connection or get a connection from the ResourceManager
			conn = isConnSupplied ? userConn : ResourceManager.getConnection();
			final String SQL = SQL_SELECT + " WHERE " + sql;
			stmt = conn.prepareStatement( SQL );
			stmt.setString( COLUMN_ID_DETALLE_PEDIDO, dto.getIdDetallePedido() );
			stmt.setString( COLUMN_ID_PEDIDO, dto.getIdPedido() );
			stmt.setString( COLUMN_CLAVE_ARTICULO, dto.getClaveArticulo() );
			if (dto.isCantidadNull()) {
				stmt.setNull( COLUMN_CANTIDAD, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( COLUMN_CANTIDAD, dto.getCantidad() );
			}

			if (dto.isPrecioNull()) {
				stmt.setNull( COLUMN_PRECIO, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( COLUMN_PRECIO, dto.getPrecio() );
			}

			if (dto.isSubtotalNull()) {
				stmt.setNull( COLUMN_SUBTOTAL, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( COLUMN_SUBTOTAL, dto.getSubtotal() );
			}

			if (dto.isIvaNull()) {
				stmt.setNull( COLUMN_IVA, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( COLUMN_IVA, dto.getIva() );
			}

			if (dto.isTotalNull()) {
				stmt.setNull( COLUMN_TOTAL, java.sql.Types.FLOAT );
			} else {
				stmt.setFloat( COLUMN_TOTAL, dto.getTotal() );
			}

			stmt.setTimestamp(COLUMN_ULTIMA_FECHA_ACTUALIZACION, dto.getUltimaFechaActualizacion()==null ? null : new java.sql.Timestamp( dto.getUltimaFechaActualizacion().getTime() ) );
			stmt.setString( COLUMN_ULTIMO_USUARIO_ACTUALIZACION, dto.getUltimoUsuarioActualizacion() );

			for (int i=12; sqlParams!=null && i<sqlParams.length +12; i++ ) {
				stmt.setObject( i+1, sqlParams[i] );
			}

			System.out.println( "Executing " + SQL);
			stmt.execute();
			int rows = stmt.getUpdateCount();
			System.out.println( rows + " rows affected" );
		}
		catch (Exception _e) {
			_e.printStackTrace();
			throw new DetallesPedidosDaoException( "Exception: " + _e.getMessage(), _e );
		}
		finally {
			ResourceManager.close(stmt);
			if (!isConnSupplied) {
				ResourceManager.close(conn);
			}

		}

	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria ''.
	 */
	public DetallesPedidos[] findAll() throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT, null );
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria 'IdDetallePedido = :idDetallePedido'.
	 */
	public DetallesPedidos[] findWhereIdDetallePedidoEquals(String idDetallePedido) throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE IdDetallePedido = ? ORDER BY IdDetallePedido", new Object[] { idDetallePedido } );
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria 'IdPedido = :idPedido'.
	 */
	public DetallesPedidos[] findWhereIdPedidoEquals(String idPedido) throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE IdPedido = ? ORDER BY IdPedido", new Object[] { idPedido } );
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria 'ClaveArticulo = :claveArticulo'.
	 */
	public DetallesPedidos[] findWhereClaveArticuloEquals(String claveArticulo) throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE ClaveArticulo = ? ORDER BY ClaveArticulo", new Object[] { claveArticulo } );
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria 'Cantidad = :cantidad'.
	 */
	public DetallesPedidos[] findWhereCantidadEquals(double cantidad) throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Cantidad = ? ORDER BY Cantidad", new Object[] {  new Double(cantidad) } );
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria 'Precio = :precio'.
	 */
	public DetallesPedidos[] findWherePrecioEquals(float precio) throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Precio = ? ORDER BY Precio", new Object[] {  new Float(precio) } );
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria 'Subtotal = :subtotal'.
	 */
	public DetallesPedidos[] findWhereSubtotalEquals(float subtotal) throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Subtotal = ? ORDER BY Subtotal", new Object[] {  new Float(subtotal) } );
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria 'IVA = :iva'.
	 */
	public DetallesPedidos[] findWhereIvaEquals(float iva) throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE IVA = ? ORDER BY IVA", new Object[] {  new Float(iva) } );
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria 'Total = :total'.
	 */
	public DetallesPedidos[] findWhereTotalEquals(float total) throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Total = ? ORDER BY Total", new Object[] {  new Float(total) } );
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria 'UltimaFechaActualizacion = :ultimaFechaActualizacion'.
	 */
	public DetallesPedidos[] findWhereUltimaFechaActualizacionEquals(Date ultimaFechaActualizacion) throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE UltimaFechaActualizacion = ? ORDER BY UltimaFechaActualizacion", new Object[] { ultimaFechaActualizacion==null ? null : new java.sql.Timestamp( ultimaFechaActualizacion.getTime() ) } );
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the criteria 'UltimoUsuarioActualizacion = :ultimoUsuarioActualizacion'.
	 */
	public DetallesPedidos[] findWhereUltimoUsuarioActualizacionEquals(String ultimoUsuarioActualizacion) throws DetallesPedidosDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE UltimoUsuarioActualizacion = ? ORDER BY UltimoUsuarioActualizacion", new Object[] { ultimoUsuarioActualizacion } );
	}

	/**
	 * Method 'DetallesPedidosDaoImpl'
	 * 
	 */
	public DetallesPedidosDaoImpl()
	{
	}

	/**
	 * Method 'DetallesPedidosDaoImpl'
	 * 
	 * @param userConn
	 */
	public DetallesPedidosDaoImpl(final Connection userConn)
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
		return "Inven_E..DetallesPedidos";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected DetallesPedidos fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			DetallesPedidos dto = new DetallesPedidos();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected DetallesPedidos[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			DetallesPedidos dto = new DetallesPedidos();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		DetallesPedidos ret[] = new DetallesPedidos[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(DetallesPedidos dto, ResultSet rs) throws SQLException
	{
		dto.setIdDetallePedido( rs.getString( COLUMN_ID_DETALLE_PEDIDO ) );
		dto.setIdPedido( rs.getString( COLUMN_ID_PEDIDO ) );
		dto.setClaveArticulo( rs.getString( COLUMN_CLAVE_ARTICULO ) );
		dto.setCantidad( rs.getFloat( COLUMN_CANTIDAD ) );
		if (rs.wasNull()) {
			dto.setCantidadNull( true );
		}
		
		dto.setPrecio( rs.getFloat( COLUMN_PRECIO ) );
		if (rs.wasNull()) {
			dto.setPrecioNull( true );
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
		
		dto.setUltimaFechaActualizacion( rs.getTimestamp(COLUMN_ULTIMA_FECHA_ACTUALIZACION ) );
		dto.setUltimoUsuarioActualizacion( rs.getString( COLUMN_ULTIMO_USUARIO_ACTUALIZACION ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(DetallesPedidos dto)
	{
	}

	/** 
	 * Returns all rows from the DetallesPedidos table that match the specified arbitrary SQL statement
	 */
	public DetallesPedidos[] findByDynamicSelect(String sql, Object[] sqlParams) throws DetallesPedidosDaoException
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
			throw new DetallesPedidosDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the DetallesPedidos table that match the specified arbitrary SQL statement
	 */
	public DetallesPedidos[] findByDynamicWhere(String sql, Object[] sqlParams) throws DetallesPedidosDaoException
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
			throw new DetallesPedidosDaoException( "Exception: " + _e.getMessage(), _e );
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
