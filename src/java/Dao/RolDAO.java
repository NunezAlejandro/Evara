package Dao;

import Models.Conexion;
import Models.RolModel;
import Models.UserModel;

import java.sql.*;
import java.util.ArrayList;

public class RolDAO {

    /*Listar: */
    public ArrayList<RolModel> Listar() throws SQLException, ClassNotFoundException {
        ArrayList<RolModel> lista = new ArrayList();
        String sql = "SELECT Rolid, CONCAT_WS(' ', us.nombres, us.apellidos) AS [Nombres y apellidos], us.dni, trl.TipRol FROM dbo.Roles rl \n"
                + "INNER JOIN dbo.Usuarios us ON us.Userid = rl.Userid_fk\n"
                + "INNER JOIN dbo.TipRol trl ON trl.TipRol = rl.tipRolid_fk";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RolModel rol = new RolModel();
                rol.setId(rs.getInt("Rolid"));
                rol.setRol(rs.getInt("TipRol"));
                rol.setNombres(rs.getString("Nombres y apellidos"));
                rol.setDni(rs.getString("dni"));
                lista.add(rol);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    /*Registrar: */
    public Boolean Registrar(String dni, int rol) throws SQLException, ClassNotFoundException {
        int id = 0;
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{ call roles_crud(?,?,?,?) }");

            ps.setInt(1, 1);
            ps.setInt(2, rol);
            ps.setString(3, dni);
            ps.setInt(4, id);

            ps.execute();

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
        return true;
    }

    /*Seleccionar: */
    public ArrayList<UserModel> DNI() throws SQLException, ClassNotFoundException {
        ArrayList<UserModel> lista = new ArrayList();
        String sql = "SELECT dni, CONCAT_WS(' ', nombres, apellidos) AS [Nombres y apellidos] FROM dbo.Usuarios";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserModel users = new UserModel();
                users.setDni(rs.getString("dni"));
                users.setNom(rs.getString("Nombres y apellidos"));
                lista.add(users);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public ArrayList<RolModel> Seleccionar(int id) throws SQLException, ClassNotFoundException {
        ArrayList<RolModel> lista = new ArrayList();
        String sql = "SELECT Rolid, us.dni, trl.Tiprol FROM dbo.Roles rl \n"
                + "INNER JOIN dbo.Usuarios us ON us.Userid = rl.Userid_fk\n"
                + "INNER JOIN dbo.TipRol trl ON trl.TipRol = rl.tipRolid_fk\n"
                + "WHERE Rolid = " + id + "";

        try {
            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RolModel rol = new RolModel();
                rol.setId(rs.getInt("Rolid"));
                rol.setRol(rs.getInt("Tiprol"));
                rol.setDni(rs.getString("dni"));
                lista.add(rol);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    /*Editar: */
    public Boolean Editar(String di, int rol, int id) throws SQLException, ClassNotFoundException {
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{call roles_crud(?,?,?,?) }");

            ps.setInt(1, 2);
            ps.setInt(2, rol);
            ps.setString(3, di);
            ps.setInt(4, id);

            int row = ps.executeUpdate();
            Boolean result = row > 0;
            return result;

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    /*Eliminar: */
    public Boolean Eliminar(int id) throws SQLException, ClassNotFoundException {
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{call roles_crud(?,?,?,?) }");

            ps.setInt(1, 3);
            ps.setInt(2, 0);
            ps.setString(3, "");
            ps.setInt(4, id);

            int row = ps.executeUpdate();
            Boolean result = row > 0;
            return result;

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }
}
