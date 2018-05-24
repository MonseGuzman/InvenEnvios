/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.innovati.felipehernandez.invenenvios.innovati.felipehernandez.invenenvios.jdbc;

import com.innovati.felipehernandez.invenenvios.dao.*;
import com.innovati.felipehernandez.invenenvios.factory.*;
import com.innovati.felipehernandez.invenenvios.dto.*;
import com.innovati.felipehernandez.invenenvios.exceptions.*;
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

public class VwAgenteDaoImpl extends AbstractDAO implements VwAgenteDao
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
	protected final String SQL_SELECT = "SELECT Clave, Nombre, Telefono, Celular, Comision, Email, Sucursal FROM " + getTableName() + "";

	/** 
	 * Finder methods will pass this value to the JDBC setMaxRows method
	 */
	protected int maxRows;

	/** 
	 * Index of column Clave
	 */
	protected static final int COLUMN_CLAVE = 1;

	/** 
	 * Index of column Nombre
	 */
	protected static final int COLUMN_NOMBRE = 2;

	/** 
	 * Index of column Telefono
	 */
	protected static final int COLUMN_TELEFONO = 3;

	/** 
	 * Index of column Celular
	 */
	protected static final int COLUMN_CELULAR = 4;

	/** 
	 * Index of column Comision
	 */
	protected static final int COLUMN_COMISION = 5;

	/** 
	 * Index of column Email
	 */
	protected static final int COLUMN_EMAIL = 6;

	/** 
	 * Index of column Sucursal
	 */
	protected static final int COLUMN_SUCURSAL = 7;

	/** 
	 * Number of columns
	 */
	protected static final int NUMBER_OF_COLUMNS = 7;

	/** 
	 * Returns all rows from the vwAgente table that match the criteria ''.
	 */
	public VwAgente[] findAll() throws VwAgenteDaoException
	{
		return findByDynamicSelect( SQL_SELECT, null );
	}

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Clave = :clave'.
	 */
	public VwAgente[] findWhereClaveEquals(String clave) throws VwAgenteDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Clave = ? ORDER BY Clave", new Object[] { clave } );
	}

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Nombre = :nombre'.
	 */
	public VwAgente[] findWhereNombreEquals(String nombre) throws VwAgenteDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Nombre = ? ORDER BY Nombre", new Object[] { nombre } );
	}

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Telefono = :telefono'.
	 */
	public VwAgente[] findWhereTelefonoEquals(String telefono) throws VwAgenteDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Telefono = ? ORDER BY Telefono", new Object[] { telefono } );
	}

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Celular = :celular'.
	 */
	public VwAgente[] findWhereCelularEquals(String celular) throws VwAgenteDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Celular = ? ORDER BY Celular", new Object[] { celular } );
	}

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Comision = :comision'.
	 */
	public VwAgente[] findWhereComisionEquals(double comision) throws VwAgenteDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Comision = ? ORDER BY Comision", new Object[] {  new Double(comision) } );
	}

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Email = :email'.
	 */
	public VwAgente[] findWhereEmailEquals(String email) throws VwAgenteDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Email = ? ORDER BY Email", new Object[] { email } );
	}

	/** 
	 * Returns all rows from the vwAgente table that match the criteria 'Sucursal = :sucursal'.
	 */
	public VwAgente[] findWhereSucursalEquals(String sucursal) throws VwAgenteDaoException
	{
		return findByDynamicSelect( SQL_SELECT + " WHERE Sucursal = ? ORDER BY Sucursal", new Object[] { sucursal } );
	}

	/**
	 * Method 'VwAgenteDaoImpl'
	 * 
	 */
	public VwAgenteDaoImpl()
	{
	}

	/**
	 * Method 'VwAgenteDaoImpl'
	 * 
	 * @param userConn
	 */
	public VwAgenteDaoImpl(final Connection userConn)
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
		return "Inven_E..vwAgente";
	}

	/** 
	 * Fetches a single row from the result set
	 */
	protected VwAgente fetchSingleResult(ResultSet rs) throws SQLException
	{
		if (rs.next()) {
			VwAgente dto = new VwAgente();
			populateDto( dto, rs);
			return dto;
		} else {
			return null;
		}
		
	}

	/** 
	 * Fetches multiple rows from the result set
	 */
	protected VwAgente[] fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			VwAgente dto = new VwAgente();
			populateDto( dto, rs);
			resultList.add( dto );
		}
		
		VwAgente ret[] = new VwAgente[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

	/** 
	 * Populates a DTO with data from a ResultSet
	 */
	protected void populateDto(VwAgente dto, ResultSet rs) throws SQLException
	{
		dto.setClave( rs.getString( COLUMN_CLAVE ) );
		dto.setNombre( rs.getString( COLUMN_NOMBRE ) );
		dto.setTelefono( rs.getString( COLUMN_TELEFONO ) );
		dto.setCelular( rs.getString( COLUMN_CELULAR ) );
		dto.setComision( rs.getDouble( COLUMN_COMISION ) );
		if (rs.wasNull()) {
			dto.setComisionNull( true );
		}
		
		dto.setEmail( rs.getString( COLUMN_EMAIL ) );
		dto.setSucursal( rs.getString( COLUMN_SUCURSAL ) );
	}

	/** 
	 * Resets the modified attributes in the DTO
	 */
	protected void reset(VwAgente dto)
	{
	}

	/** 
	 * Returns all rows from the vwAgente table that match the specified arbitrary SQL statement
	 */
	public VwAgente[] findByDynamicSelect(String sql, Object[] sqlParams) throws VwAgenteDaoException
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
			throw new VwAgenteDaoException( "Exception: " + _e.getMessage(), _e );
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
	 * Returns all rows from the vwAgente table that match the specified arbitrary SQL statement
	 */
	public VwAgente[] findByDynamicWhere(String sql, Object[] sqlParams) throws VwAgenteDaoException
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
			throw new VwAgenteDaoException( "Exception: " + _e.getMessage(), _e );
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
