package Dao;

import Models.Conexion;
import Models.cartillaModel;
import Models.indicadoresModel;
import Models.totalEstadoEscolaresModel;
import java.sql.*;
import java.util.ArrayList;

public class indicadorDAO {

    /*Listar: */
    public ArrayList<indicadoresModel> Listar() throws SQLException, ClassNotFoundException {
        ArrayList<indicadoresModel> lista = new ArrayList();
        String sql = "SELECT med.Medicionid, CONCAT_WS(' ', us.nombres, us.apellidos) AS [Nombres y apellidos], us.dni FROM dbo.Mediciones med \n"
                + "INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk\n"
                + "INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                indicadoresModel data = new indicadoresModel();
                data.setId(rs.getInt("Medicionid"));
                data.setNom(rs.getString("Nombres y apellidos"));
                data.setDni(rs.getString("dni"));
                lista.add(data);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public ArrayList<totalEstadoEscolaresModel> ListarSanos() throws SQLException, ClassNotFoundException {
        ArrayList<totalEstadoEscolaresModel> lista = new ArrayList();
        String sql = "SELECT COUNT(med.Medicionid) AS [Alumnos] FROM dbo.Mediciones med \n"
                + "INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk\n"
                + "INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid\n"
                + "WHERE med.imc BETWEEN (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 4) AND (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 4) AND\n"
                + "med.glucosa BETWEEN (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 1) AND (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 1) AND\n"
                + "((us.genero = 1 AND med.hemoglobina BETWEEN (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 2) AND (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 2)) OR\n"
                + "(us.genero = 0 AND med.hemoglobina BETWEEN (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 3) AND (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 3)))";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                totalEstadoEscolaresModel data = new totalEstadoEscolaresModel();
                data.setCantSaludables(rs.getInt("Alumnos"));
                lista.add(data);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public ArrayList<totalEstadoEscolaresModel> ListarNoSanos() throws SQLException, ClassNotFoundException {
        ArrayList<totalEstadoEscolaresModel> lista = new ArrayList();
        String sql = "SELECT COUNT(med.Medicionid) AS [Alumnos] FROM dbo.Mediciones med \n"
                + "INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk\n"
                + "INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid\n"
                + "WHERE med.imc < (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 4) OR med.imc > (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 4) AND\n"
                + "med.glucosa < (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 1) OR med.glucosa > (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 1) AND\n"
                + "((us.genero = 1 AND med.hemoglobina < (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 2) OR med.hemoglobina > (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 2)) OR\n"
                + "(us.genero = 0 AND med.hemoglobina < (SELECT Valmin FROM dbo.Parametros WHERE Parametroid = 3) OR med.hemoglobina > (SELECT Valmax FROM dbo.Parametros WHERE Parametroid = 3)))";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                totalEstadoEscolaresModel data = new totalEstadoEscolaresModel();
                data.setCantNoSaludables(rs.getInt("Alumnos"));
                lista.add(data);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
}
