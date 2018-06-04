package com.innovati.felipehernandez.invenenvios;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MetodosInternos
{
    private Context context;

    public MetodosInternos(Context context) {
        this.context = context;
    }

    public boolean conexionRed()
    {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo tipoRed = connectivity.getActiveNetworkInfo();

        if(tipoRed != null)
        {
            if (tipoRed.getType() == ConnectivityManager.TYPE_MOBILE || tipoRed.getType() == ConnectivityManager.TYPE_WIFI)
                return true;
        }
        return false;
    }

    public ArrayList consultaDeTodos()
    {
        ArrayList lista = new ArrayList();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection;
        String url;

        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //192.168.0.3
            url = "jdbc:jtds:sqlserver://192.168.0.34;databaseName=Inven_E;user=sa;password=0f3734m0_%%";
            connection = DriverManager.getConnection(url);
            Statement estatuto = connection.createStatement();
            String query = "SELECT * FROM vwClientes";
            ResultSet resultado = estatuto.executeQuery(query);

            while (resultado.next())
            {
                //datos
                Clientes clientes = new Clientes();
                clientes.setNombre(resultado.getString("Nombre"));
                clientes.setRFC(resultado.getString("RFC"));
                clientes.setTelefono(resultado.getString("telefono"));
                lista.add(clientes);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean login(String user, String pass)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection;
        String url;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //192.168.0.3
            url = "jdbc:jtds:sqlserver://192.168.0.34;databaseName=Inven_E;user=sa;password=0f3734m0_%%";
            connection = DriverManager.getConnection(url);
            Statement estatuto = connection.createStatement();
            String query = "SELECT * FROM vwUsuarios WHERE NickName = "+ user + " AND Password = " + pass;
            ResultSet resultado = estatuto.executeQuery(query);

            if (resultado.next())
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validacion(String user, String pass)
    {
        if (user.isEmpty() && pass.isEmpty())
            return false;
        else if (user.isEmpty())
            return false;
        else if (pass.isEmpty())
            return false;

        return true;
    }
}
