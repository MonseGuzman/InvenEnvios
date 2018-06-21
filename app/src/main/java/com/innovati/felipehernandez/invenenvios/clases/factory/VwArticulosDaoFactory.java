/*
 * This source file was generated by FireStorm/DAO.
 * 
 * If you purchase a full license for FireStorm/DAO you can customize this header file.
 * 
 * For more information please visit http://www.codefutures.com/products/firestorm
 */

package com.innovati.felipehernandez.invenenvios.clases.factory;

import java.sql.Connection;
import com.innovati.felipehernandez.invenenvios.clases.dao.*;
import com.innovati.felipehernandez.invenenvios.clases.jdbc.*;

public class VwArticulosDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return VwArticulosDao
	 */
	public static VwArticulosDao create()
	{
		return new VwArticulosDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return VwArticulosDao
	 */
	public static VwArticulosDao create(Connection conn)
	{
		return new VwArticulosDaoImpl( conn );
	}

}