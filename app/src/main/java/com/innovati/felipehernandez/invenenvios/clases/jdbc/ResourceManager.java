package com.innovati.felipehernandez.invenenvios.clases.jdbc;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.sql.*;

public class ResourceManager
{
	private static String JDBC_DRIVER   = "net.sourceforge.jtds.jdbc.Driver";
	//private static String JDBC_DRIVER   = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String JDBC_URL      = "jdbc:jtds:sqlserver://192.168.0.34:1433;databaseName=Inven_E";

    private static String JDBC_USER     = "sa";
    private static String JDBC_PASSWORD = "0f3734m0_%%";

    private static Driver driver = null;

    public static synchronized Connection getConnection() throws SQLException
    {
        if (driver == null)
        {
            try
            {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
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
