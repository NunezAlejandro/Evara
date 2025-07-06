package Dao;

import Models.Conexion;
import Models.UserModel;
import Models.alumnosModel;
import Models.institucionesModel;

import java.sql.*;
import java.util.ArrayList;

public class alumnoDAO {

    /*Listar: */
    public ArrayList<alumnosModel> Listar() throws SQLException, ClassNotFoundException {
        ArrayList<alumnosModel> lista = new ArrayList();
        String sql = "SELECT al.Alumnosid, CONCAT_WS(' ', us.nombres, us.apellidos) AS [Nombres y apellidos], ins.institucion FROM dbo.Alumnos al \n"
                + "INNER JOIN dbo.Instituciones ins ON ins.Institucionid = al.Institucionid\n"
                + "INNER JOIN dbo.Usuarios us ON us.Userid = al.Userid_fk";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                alumnosModel data = new alumnosModel();
                data.setId(rs.getInt("Alumnosid"));
                data.setDescripcion(rs.getString("Nombres y apellidos"));
                data.setInstitucion(rs.getString("institucion"));
                lista.add(data);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    /*Registrar: */
    public Boolean Registrar(String dni, int ins) throws SQLException, ClassNotFoundException {
        int id = 0;
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{ call alumnos_crud(?,?,?,?) }");

            ps.setInt(1, 1);
            ps.setString(2, dni);
            ps.setInt(3, ins);
            ps.setInt(4, id);

            ps.execute();

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
        return true;
    }

    /*Seleccionar: */
    public ArrayList<institucionesModel> Instituciones() throws SQLException, ClassNotFoundException {
        ArrayList<institucionesModel> lista = new ArrayList();
        String sql = "SELECT * FROM dbo.Instituciones";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                institucionesModel users = new institucionesModel();
                users.setId(rs.getInt("InstitucionId"));
                users.setInstitucion(rs.getString("Institucion"));
                lista.add(users);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    public ArrayList<alumnosModel> Seleccionar(int id) throws SQLException, ClassNotFoundException {
        ArrayList<alumnosModel> lista = new ArrayList();
        String sql = "SELECT Alumnosid, us.dni, ins.Institucionid FROM dbo.Alumnos al \n"
                + "INNER JOIN dbo.Instituciones ins ON ins.Institucionid = al.Institucionid\n"
                + "INNER JOIN dbo.Usuarios us ON us.Userid = al.Userid_fk WHERE Alumnosid = " + id + "";

        try {
            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                alumnosModel rol = new alumnosModel();
                rol.setId(rs.getInt("Alumnosid"));
                rol.setIdInstitucion(rs.getInt("Institucionid"));
                rol.setIdUsuario(rs.getString("dni"));
                lista.add(rol);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    /*Editar: */
    public Boolean Editar(String dni, int ins, int id) throws SQLException, ClassNotFoundException {
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{call alumnos_crud(?,?,?,?) }");

            ps.setInt(1, 2);
            ps.setString(2, dni);
            ps.setInt(3, ins);
            ps.setInt(4, id);

            int row = ps.executeUpdate();
            Boolean result = row > 0;
            return result;

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        alumnoDAO al = new alumnoDAO();
        System.out.println(al.Editar("21345325", 4, 2));
    }

    /*Eliminar: */
    public Boolean Eliminar(int id) throws SQLException, ClassNotFoundException {
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{call alumnos_crud(?,?,?,?) }");

            ps.setInt(1, 3);
            ps.setString(2, "");
            ps.setInt(3, 0);
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
