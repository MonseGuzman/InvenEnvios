/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.innovati.felipehernandez.invenenvios.clases.example;

import java.math.*;
import java.util.Date;
import java.util.Collection;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwUsuariosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwUsuarios;
import com.innovati.felipehernandez.invenenvios.clases.exceptions.VwUsuariosDaoException;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwUsuariosDaoFactory;

public class VwUsuariosDaoSample
{
	/**
	 * Method 'main'
	 * 
	 * @param arg
	 * @throws Exception
	 */
	public static void main(String[] arg) throws Exception
	{
		// Uncomment one of the lines below to test the generated code
		
		// findAll();
		// findWhereIdUsuarioEquals("");
		// findWhereClaveEquals("");
		// findWhereNickNameEquals("");
		// findWherePasswordEquals("");
		// findWhereStatusEquals(0);
		// findWhereFechaActualizacionEquals(null);
		// findWhereIdUsuarioActualizacionEquals("");
		// findWhereSucursalEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 */
	public static void findAll()
	{
		try {
			VwUsuariosDao _dao = getVwUsuariosDao();
			VwUsuarios _result[] = _dao.findAll();
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereIdUsuarioEquals'
	 * 
	 * @param idUsuario
	 */
	public static void findWhereIdUsuarioEquals(String idUsuario)
	{
		try {
			VwUsuariosDao _dao = getVwUsuariosDao();
			VwUsuarios _result[] = _dao.findWhereIdUsuarioEquals(idUsuario);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereClaveEquals'
	 * 
	 * @param clave
	 */
	public static void findWhereClaveEquals(String clave)
	{
		try {
			VwUsuariosDao _dao = getVwUsuariosDao();
			VwUsuarios _result[] = _dao.findWhereClaveEquals(clave);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereNickNameEquals'
	 * 
	 * @param nickName
	 */
	public static void findWhereNickNameEquals(String nickName)
	{
		try {
			VwUsuariosDao _dao = getVwUsuariosDao();
			VwUsuarios _result[] = _dao.findWhereNickNameEquals(nickName);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWherePasswordEquals'
	 * 
	 * @param password
	 */
	public static void findWherePasswordEquals(String password)
	{
		try {
			VwUsuariosDao _dao = getVwUsuariosDao();
			VwUsuarios _result[] = _dao.findWherePasswordEquals(password);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereStatusEquals'
	 * 
	 * @param status
	 */
	public static void findWhereStatusEquals(short status)
	{
		try {
			VwUsuariosDao _dao = getVwUsuariosDao();
			VwUsuarios _result[] = _dao.findWhereStatusEquals(status);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereFechaActualizacionEquals'
	 * 
	 * @param fechaActualizacion
	 */
	public static void findWhereFechaActualizacionEquals(Date fechaActualizacion)
	{
		try {
			VwUsuariosDao _dao = getVwUsuariosDao();
			VwUsuarios _result[] = _dao.findWhereFechaActualizacionEquals(fechaActualizacion);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereIdUsuarioActualizacionEquals'
	 * 
	 * @param idUsuarioActualizacion
	 */
	public static void findWhereIdUsuarioActualizacionEquals(String idUsuarioActualizacion)
	{
		try {
			VwUsuariosDao _dao = getVwUsuariosDao();
			VwUsuarios _result[] = _dao.findWhereIdUsuarioActualizacionEquals(idUsuarioActualizacion);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereSucursalEquals'
	 * 
	 * @param sucursal
	 */
	public static void findWhereSucursalEquals(String sucursal)
	{
		try {
			VwUsuariosDao _dao = getVwUsuariosDao();
			VwUsuarios _result[] = _dao.findWhereSucursalEquals(sucursal);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'getVwUsuariosDao'
	 * 
	 * @return VwUsuariosDao
	 */
	public static VwUsuariosDao getVwUsuariosDao()
	{
		return VwUsuariosDaoFactory.create();
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(VwUsuarios dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getIdUsuario() );
		buf.append( ", " );
		buf.append( dto.getClave() );
		buf.append( ", " );
		buf.append( dto.getNickName() );
		buf.append( ", " );
		buf.append( dto.getPassword() );
		buf.append( ", " );
		buf.append( dto.getStatus() );
		buf.append( ", " );
		buf.append( dto.getFechaActualizacion() );
		buf.append( ", " );
		buf.append( dto.getIdUsuarioActualizacion() );
		buf.append( ", " );
		buf.append( dto.getSucursal() );
		System.out.println( buf.toString() );
	}

}
