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

public class DetallesPedidosDaoFactory
{
	/**
	 * Method 'create'
	 * 
	 * @return DetallesPedidosDao
	 */
	public static DetallesPedidosDao create()
	{
		return new DetallesPedidosDaoImpl();
	}

	/**
	 * Method 'create'
	 * 
	 * @param conn
	 * @return DetallesPedidosDao
	 */
	public static DetallesPedidosDao create(Connection conn)
	{
		return new DetallesPedidosDaoImpl( conn );
	}

}
