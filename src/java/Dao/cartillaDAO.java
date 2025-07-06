package Dao;

import Models.Conexion;
import Models.cartillaModel;
import java.sql.*;
import java.util.ArrayList;

public class cartillaDAO {

    /*Listar: */
    public ArrayList<cartillaModel> ListarFecha(String di) throws SQLException, ClassNotFoundException {
        ArrayList<cartillaModel> lista = new ArrayList();
        String sql = "SELECT med.Medicionid, med.fecha, us.dni FROM dbo.Mediciones med \n"
                + "INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk\n"
                + "INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid\n"
                + "WHERE al.Userid_fk = (SELECT Userid FROM dbo.Usuarios WHERE dni = '" + di + "')";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cartillaModel data = new cartillaModel();
                data.setId(rs.getInt("Medicionid"));
                data.setDni(rs.getString("dni"));
                data.setFecha(rs.getString("fecha"));
                lista.add(data);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public ArrayList<cartillaModel> ListarCartilla(String di, String fecha) throws SQLException, ClassNotFoundException {
        ArrayList<cartillaModel> lista = new ArrayList();
        String sql = "SELECT med.Medicionid, us.dni, CONCAT_WS(' ', us.nombres, us.apellidos) AS [Nombres y apellidos], med.imc, med.peso, med.talla, med.hemoglobina, med.glucosa, med.fecha FROM dbo.Mediciones med \n"
                + "INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk\n"
                + "INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid\n"
                + "WHERE al.Userid_fk = (SELECT Userid FROM dbo.Usuarios WHERE dni = '" + di + "') AND med.fecha = '" + fecha + "'";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cartillaModel data = new cartillaModel();
                data.setId(rs.getInt("Medicionid"));
                data.setDni(rs.getString("dni"));
                data.setNombres(rs.getString("Nombres y apellidos"));
                data.setImc(rs.getFloat("imc"));
                data.setPeso(rs.getFloat("peso"));
                data.setTalla(rs.getFloat("talla"));
                data.setHemoglobina(rs.getFloat("hemoglobina"));
                data.setGlucosa(rs.getFloat("glucosa"));
                data.setFecha(rs.getString("fecha"));
                lista.add(data);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    /*Registrar: */
    public Boolean Registrar(double peso, double talla, double hemoglobina, double glucosa, String dni) throws SQLException, ClassNotFoundException {
        int id = 0;
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{ call mediciones_crud(?,?,?,?,?,?,?) }");

            ps.setInt(1, 1);
            ps.setDouble(2, peso);
            ps.setDouble(3, talla);
            ps.setDouble(4, hemoglobina);
            ps.setDouble(5, glucosa);
            ps.setString(6, dni);
            ps.setInt(7, id);

            ps.execute();

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
        return true;
    }

    /*Seleccionar: */
    public ArrayList<cartillaModel> Seleccionar(int id) throws SQLException, ClassNotFoundException {
        ArrayList<cartillaModel> lista = new ArrayList();
        String sql = "SELECT med.Medicionid, us.dni, CONCAT_WS(' ', us.nombres, us.apellidos) AS [Nombres y apellidos], med.imc, med.peso, med.talla, med.hemoglobina, med.glucosa, med.fecha FROM dbo.Mediciones med \n"
                + "INNER JOIN dbo.Usuarios us ON us.Userid = med.Userid_fk\n"
                + "INNER JOIN dbo.Alumnos al ON al.Userid_fk = us.Userid\n"
                + "WHERE med.Medicionid = " + id + "";

        try {
            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cartillaModel data = new cartillaModel();
                data.setId(rs.getInt("Medicionid"));
                data.setDni(rs.getString("dni"));
                data.setNombres(rs.getString("Nombres y apellidos"));
                data.setImc(rs.getFloat("imc"));
                data.setPeso(rs.getFloat("peso"));
                data.setTalla(rs.getFloat("talla"));
                data.setHemoglobina(rs.getFloat("hemoglobina"));
                data.setGlucosa(rs.getFloat("glucosa"));
                data.setFecha(rs.getString("fecha"));
                lista.add(data);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    /*Editar: */
    public Boolean Editar(double peso, double talla, double hemoglobina, double glucosa, String dni, int id) throws SQLException, ClassNotFoundException {
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{call mediciones_crud(?,?,?,?,?,?,?) }");

            ps.setInt(1, 2);
            ps.setDouble(2, peso);
            ps.setDouble(3, talla);
            ps.setDouble(4, hemoglobina);
            ps.setDouble(5, glucosa);
            ps.setString(6, dni);
            ps.setInt(7, id);

            int row = ps.executeUpdate();
            Boolean result = row > 0;
            return result;

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }
}
