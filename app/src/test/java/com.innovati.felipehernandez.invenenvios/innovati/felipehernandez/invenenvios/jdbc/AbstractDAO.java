package com.innovati.felipehernandez.invenenvios.innovati.felipehernandez.invenenvios.jdbc;

import java.io.*;
import java.sql.*;

/**
 * Generic Base class for DAO classes.
 *
 * This is a customizable template within FireStorm/DAO.
 */
public class AbstractDAO
{

    public byte[] getBlobColumn(ResultSet rs, int columnIndex)
            throws SQLException
    {
        return rs.getBytes( columnIndex );
    }

    public void setBlobColumn(PreparedStatement stmt, int parameterIndex, byte[] value)
            throws SQLException
    {
        if (value == null) {
            stmt.setNull( parameterIndex, Types.BINARY );
        }
        else {
            stmt.setBytes( parameterIndex, value );
        }
    }

    public String getClobColumn(ResultSet rs, int columnIndex)
        throws SQLException
    {
        try {
            Clob clob = rs.getClob( columnIndex );
            if (clob == null) {
                return null;
            }

            StringBuffer ret = new StringBuffer();
            InputStream is = clob.getAsciiStream();

            if (is == null) {
                return null;
            }
            else {
                byte buffer[] = new byte[ 64 ];
                int c = is.read( buffer );
                while (c>0) {
                    ret.append( new String(buffer, 0, c) );
                    c = is.read( buffer );
                }
                return ret.toString();
            }
        }
        catch (IOException e) {
            throw new SQLException( "Failed to read CLOB column due to IOException: " + e.getMessage() );
        }
    }

    public void setClobColumn(PreparedStatement stmt, int parameterIndex, String value)
        throws SQLException
    {
        if (value == null) {
            stmt.setNull( parameterIndex, Types.CLOB );
        }
        else {
            stmt.setAsciiStream( parameterIndex, new ByteArrayInputStream(value.getBytes()), value.length() );
        }
    }
}
