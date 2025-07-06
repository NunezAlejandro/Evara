package Models;

import java.sql.*;

public class Conexion {

    private Conexion(){}
    private static Connection conectar = null;

    public static Connection Conectado() throws SQLException, ClassNotFoundException {

        if (conectar == null) {
            try {
                String usuario = "JeanDev";
                String pass = "190919";
                String bd = "bdEvara";
                String host = "localhost";
                String puerto = "1433";
                
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                String url = "jdbc:sqlserver://" + host + ":" + puerto
                        + ";databaseName=" + bd
                        + ";user=" + usuario
                        + ";password=" + pass
                        + ";encrypt=true"
                        + ";TrustServerCertificate=True"
                        + ";loginTimeout=30;";
                
                conectar = DriverManager.getConnection(url);
                
                System.out.println("Conexión exitosa");
            } catch (SQLException e) {
                throw e;
            }
        }
        return conectar;
    }

    public static void Desconectado() throws SQLException {
        try {
            if (conectar != null && !conectar.isClosed()) {
                conectar.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}