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
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.exceptions.VwClientesDaoException;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwClientesDaoFactory;

public class VwClientesDaoSample
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
		// findWhereClaveEquals("");
		// findWhereNombreEquals("");
		// findWhereRfcEquals("");
		// findWhereCalleEquals("");
		// findWhereNumeroExteriorEquals("");
		// findWhereNumeroInteriorEquals("");
		// findWhereColoniaEquals("");
		// findWhereTelefonoEquals("");
	}

	/**
	 * Method 'findAll'
	 * 
	 */
	public static void findAll()
	{
		try {
			VwClientesDao _dao = getVwClientesDao();
			VwClientes _result[] = _dao.findAll();
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
			VwClientesDao _dao = getVwClientesDao();
			VwClientes _result[] = _dao.findWhereClaveEquals(clave);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereNombreEquals'
	 * 
	 * @param nombre
	 */
	public static void findWhereNombreEquals(String nombre)
	{
		try {
			VwClientesDao _dao = getVwClientesDao();
			VwClientes _result[] = _dao.findWhereNombreEquals(nombre);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereRfcEquals'
	 * 
	 * @param rfc
	 */
	public static void findWhereRfcEquals(String rfc)
	{
		try {
			VwClientesDao _dao = getVwClientesDao();
			VwClientes _result[] = _dao.findWhereRfcEquals(rfc);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereCalleEquals'
	 * 
	 * @param calle
	 */
	public static void findWhereCalleEquals(String calle)
	{
		try {
			VwClientesDao _dao = getVwClientesDao();
			VwClientes _result[] = _dao.findWhereCalleEquals(calle);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereNumeroExteriorEquals'
	 * 
	 * @param numeroExterior
	 */
	public static void findWhereNumeroExteriorEquals(String numeroExterior)
	{
		try {
			VwClientesDao _dao = getVwClientesDao();
			VwClientes _result[] = _dao.findWhereNumeroExteriorEquals(numeroExterior);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereNumeroInteriorEquals'
	 * 
	 * @param numeroInterior
	 */
	public static void findWhereNumeroInteriorEquals(String numeroInterior)
	{
		try {
			VwClientesDao _dao = getVwClientesDao();
			VwClientes _result[] = _dao.findWhereNumeroInteriorEquals(numeroInterior);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereColoniaEquals'
	 * 
	 * @param colonia
	 */
	public static void findWhereColoniaEquals(String colonia)
	{
		try {
			VwClientesDao _dao = getVwClientesDao();
			VwClientes _result[] = _dao.findWhereColoniaEquals(colonia);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'findWhereTelefonoEquals'
	 * 
	 * @param telefono
	 */
	public static void findWhereTelefonoEquals(String telefono)
	{
		try {
			VwClientesDao _dao = getVwClientesDao();
			VwClientes _result[] = _dao.findWhereTelefonoEquals(telefono);
			for (int i=0; i<_result.length; i++ ) {
				display( _result[i] );
			}
		
		}
		catch (Exception _e) {
			_e.printStackTrace();
		}
		
	}

	/**
	 * Method 'getVwClientesDaoI'
	 * 
	 * @return VwClientesDao_I
	 */
	public static VwClientesDao getVwClientesDao()
	{
		return VwClientesDaoFactory.create();
	}

	/**
	 * Method 'display'
	 * 
	 * @param dto
	 */
	public static void display(VwClientes dto)
	{
		StringBuffer buf = new StringBuffer();
		buf.append( dto.getClave() );
		buf.append( ", " );
		buf.append( dto.getNombre() );
		buf.append( ", " );
		buf.append( dto.getRfc() );
		buf.append( ", " );
		buf.append( dto.getCalle() );
		buf.append( ", " );
		buf.append( dto.getNumeroExterior() );
		buf.append( ", " );
		buf.append( dto.getNumeroInterior() );
		buf.append( ", " );
		buf.append( dto.getColonia() );
		buf.append( ", " );
		buf.append( dto.getTelefono() );
		System.out.println( buf.toString() );
	}

}
