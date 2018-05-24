package com.innovati.felipehernandez.invenenvios.innovati.felipehernandez.invenenvios.jdbc;

import java.sql.*;

public class ResourceManager
{
    private static String JDBC_DRIVER   = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String JDBC_URL      = "jdbc:sqlserver://SERVERNET\PDPSERVER:1433;databaseName=Inven_E";

    private static String JDBC_USER     = "sa";
    private static String JDBC_PASSWORD = "0f3734m0_%%";

    private static Driver driver = null;

    public static synchronized Connection getConnection()
	throws SQLException
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
