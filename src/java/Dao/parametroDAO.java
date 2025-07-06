package Dao;

import Models.Conexion;
import Models.parametrosModel;
import java.sql.*;
import java.util.ArrayList;

public class parametroDAO {

    /*Listar: */
    public ArrayList<parametrosModel> Listar() throws SQLException, ClassNotFoundException {
        ArrayList<parametrosModel> lista = new ArrayList();
        String sql = "SELECT Parametroid, para.parametro, para.Valmin, para.Valmax, und.metrica FROM dbo.Parametros para \n"
                + "INNER JOIN dbo.Unidadesmedida und ON und.Unidadid = para.Unidadid_fk";

        try {

            Connection con = Conexion.Conectado();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                parametrosModel data = new parametrosModel();
                data.setId(rs.getInt("Parametroid"));
                data.setDescripcion(rs.getString("parametro"));
                data.setMin(rs.getInt("Valmin"));
                data.setMax(rs.getInt("Valmax"));
                data.setUnidad(rs.getString("metrica"));
                lista.add(data);
            }

        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    /*Registrar: */
 /*Seleccionar: */
 /*Editar: */
 /*Eliminar: */
}
