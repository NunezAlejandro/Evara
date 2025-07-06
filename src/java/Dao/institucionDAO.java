package Dao;

import Models.Conexion;
import Models.institucionesModel;
import java.sql.*;
import java.util.ArrayList;

public class institucionDAO {

    /*Listar: */
    public ArrayList<institucionesModel> Listar() throws SQLException, ClassNotFoundException {
        ArrayList<institucionesModel> lista = new ArrayList();
        String sql = "SELECT * FROM dbo.Instituciones";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                institucionesModel data = new institucionesModel();
                data.setId(rs.getInt("Institucionid"));
                data.setInstitucion(rs.getString("institucion"));
                lista.add(data);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

    /*Registrar: */
    public Boolean Registrar(String ins) throws SQLException, ClassNotFoundException {
        int id = 0;
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{ call instituciones_crud(?,?,?) }");

            ps.setInt(1, 1);
            ps.setString(2, ins);
            ps.setInt(3, 0);

            ps.execute();

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
        return true;
    }

    /*Editar: */
    public Boolean Editar(String ins, int id) throws SQLException, ClassNotFoundException {
        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement("{call instituciones_crud(?,?,?) }");

            ps.setInt(1, 2);
            ps.setString(2, ins);
            ps.setInt(3, id);

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
            PreparedStatement ps = con.prepareStatement("{call instituciones_crud(?,?,?) }");

            ps.setInt(1, 3);
            ps.setString(2, "");
            ps.setInt(3, id);

            int row = ps.executeUpdate();
            Boolean result = row > 0;
            return result;

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    /*Seleccionar*/
    public ArrayList<institucionesModel> Seleccionar(int id) throws SQLException, ClassNotFoundException {
        ArrayList<institucionesModel> lista = new ArrayList();
        String sql = "SELECT * FROM dbo.Instituciones\n"
                + "WHERE institucionid = '" + id + "'";

        try {
            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                institucionesModel data = new institucionesModel();
                data.setId(rs.getInt("institucionid"));
                data.setInstitucion(rs.getString("institucion"));
                lista.add(data);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }
}
