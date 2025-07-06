package Dao;

import Models.Conexion;
import Models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    /*Login: */
    public boolean Login(String dni, String rol) throws SQLException, ClassNotFoundException {
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{ call usuario_sl(?,?) }");

            ps.setString(1, dni);
            ps.setString(2, rol);

            ResultSet rs = ps.executeQuery();
            boolean exist = false;

            while (rs.next()) {
                exist = true;
            }

            return exist;

        } catch (SQLException e) {
            throw e;
        }
    }

    /*Registrar: */
    public Boolean Registrar(String nom, String app, String genero, String edad, String dni) throws SQLException, ClassNotFoundException {
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{ call usuarios_crud(?,?,?,?,?,?) }");

            ps.setInt(1, 1);
            ps.setString(2, nom);
            ps.setString(3, app);
            ps.setString(4, genero);
            ps.setString(5, edad);
            ps.setString(6, dni);

            ps.execute();

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<UserModel> Logeado(String dni, String rol) {
        ArrayList<UserModel> lista = new ArrayList();
        String sql = "SELECT us.nombres FROM dbo.Usuarios us \n"
                + "	INNER JOIN dbo.Roles rl ON us.Userid = rl.Userid_fk \n"
                + "	INNER JOIN dbo.TipRol tprl ON tprl.TipRol = rl.tipRolid_fk \n"
                + "	WHERE us.dni = '" + dni + "' AND tprl.TipRol = " + rol + "";

        try {
            Connection con = Conexion.Conectado();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserModel users = new UserModel();
                users.setDni(dni);
                users.setNom(rs.getString("nombres"));
                users.setApp(rs.getString("TipRol"));
                lista.add(users);
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

        return lista;
    }

    /*Listar: */
    public ArrayList<UserModel> Listar() throws SQLException, ClassNotFoundException {
        ArrayList<UserModel> lista = new ArrayList();
        String sql = "SELECT Userid, CONCAT_WS(' ', nombres, apellidos) AS [Nombres y apellidos], genero, edad, dni FROM dbo.Usuarios";

        try {
            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserModel users = new UserModel();
                users.setId(rs.getInt("Userid"));
                users.setNom(rs.getString("Nombres y apellidos"));
                users.setGen(rs.getInt("genero") == 0 ? "Masculino" : "Femenino");
                users.setEdad(rs.getInt("edad"));
                users.setDni(rs.getString("dni"));
                lista.add(users);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    /*Seleccionar: */
    public ArrayList<UserModel> Seleccionar(String di) throws SQLException, ClassNotFoundException {
        ArrayList<UserModel> lista = new ArrayList();
        String sql = "SELECT * FROM dbo.Usuarios WHERE dni = '" + di + "'";

        try {
            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserModel users = new UserModel();
                users.setId(rs.getInt("Userid"));
                users.setNom(rs.getString("nombres"));
                users.setApp(rs.getString("apellidos"));
                users.setGen(rs.getInt("genero") + "");
                users.setEdad(rs.getInt("edad"));
                users.setDni(rs.getString("dni"));
                lista.add(users);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    /*Editar: */
    public Boolean Editar(String di, String nom, String app, int gen, int edad) throws SQLException, ClassNotFoundException {
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{ call usuarios_crud(?,?,?,?,?,?) }");

            ps.setInt(1, 2);
            ps.setString(2, nom);
            ps.setString(3, app);
            ps.setInt(4, gen);
            ps.setInt(5, edad);
            ps.setString(6, di);

            int row = ps.executeUpdate();
            Boolean result = row > 0;
            return result;

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    /*Eliminar: */
    public Boolean Eliminar(String di) throws SQLException, ClassNotFoundException {
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{ call usuarios_crud(?,?,?,?,?,?) }");

            ps.setInt(1, 3);
            ps.setString(2, "");
            ps.setString(3, "");
            ps.setString(4, "");
            ps.setString(5, "");
            ps.setString(6, di);

            int row = ps.executeUpdate();
            Boolean result = row > 0;
            return result;

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }
}
