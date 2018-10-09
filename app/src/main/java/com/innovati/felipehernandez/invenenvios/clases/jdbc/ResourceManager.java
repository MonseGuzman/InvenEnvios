package com.innovati.felipehernandez.invenenvios.clases.jdbc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.sql.*;

public class ResourceManager
{
	private static String JDBC_DRIVER   = "net.sourceforge.jtds.jdbc.Driver";
	//private static String JDBC_DRIVER   = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private SharedPreferences preferences;
	public static String addressIp = "innovati.ddns.net";
	public  static  String port= "1433";
	public static  String nameData = "Inven_E";
    public  static String JDBC_USER     = "sa";
    public static String JDBC_PASSWORD = "0f3734m0_%%";
	private static String JDBC_URL      = "jdbc:jtds:sqlserver://" + addressIp + ":" + port + ";databaseName="+ nameData;
    private static Driver driver = null;


	public static synchronized Connection getConnection() throws SQLException
    {
        if (driver == null)
        {
            try
            {
                Class jdbcDriverClass = Class.forName( JDBC_DRIVER );
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver( driver );
            }
            catch (Exception e)
            {
                System.out.println( "Failed to initialise JDBC driver" );
                e.printStackTrace();
            }
        }

        return DriverManager.getConnection(
                JDBC_URL,
                JDBC_USER,
                JDBC_PASSWORD
        );
    }


	public static void close(Connection conn)
	{
		try {
			if (conn != null) conn.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}

	public static void close(PreparedStatement stmt)
	{
		try {
			if (stmt != null) stmt.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}

	public static void close(ResultSet rs)
	{
		try {
			if (rs != null) rs.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}

	}

}
