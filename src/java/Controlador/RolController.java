package Controllers;

import Dao.RolDAO;
import Dao.UserDAO;
import Models.RolModel;
import Models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RolController", urlPatterns = {"/RolController"})
public class RolController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                Listar(request, response);
                break;
            case "registrar":
                Registrar(request, response);
                break;
            case "dni":
                DNI(request, response);
                break;
            case "editar":
                Editar(request, response);
                break;
            case "eliminar":
                Eliminar(request, response);
                break;
            case "seleccionar":
                Seleccionar(request, response);
                break;
            default:
                break;
        }
    }

    private void Listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RolDAO objeto = new RolDAO();
            List<RolModel> lista = objeto.Listar();

            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(lista));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    
    private void Seleccionar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            int id = Integer.parseInt(request.getParameter("id"));
            
            RolDAO objeto = new RolDAO();
            List<RolModel> lista = objeto.Seleccionar(id);
            
            RolModel mostrar = lista.get(0);
            
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("estado", "success");
            respuesta.put("usar", mostrar);

            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();

            String resultado = mapper.writeValueAsString(respuesta);
            out.print(resultado);
                    
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    
    private void DNI(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RolDAO objeto = new RolDAO();
            List<UserModel> lista = objeto.DNI();

            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(lista));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    private void Registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dni = request.getParameter("dni");
            int rol = Integer.parseInt(request.getParameter("rol"));
            
            RolDAO objeto = new RolDAO();
            Boolean bandera = objeto.Registrar(dni, rol);

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> respuesta = new HashMap<>();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (bandera) {
                respuesta.put("estado", "success");
                respuesta.put("mensaje", "Creado correctamente");
                respuesta.put("redirect", "rolesView.jsp");
            } else {
                respuesta.put("estado", "error");
                respuesta.put("mensaje", "No se puede crear.");
                respuesta.put("redirect", "rolRegistrar.jsp");
            }

            out.write(mapper.writeValueAsString(respuesta));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    
    private void Editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dni = request.getParameter("dni");
            int rol = Integer.parseInt(request.getParameter("rol"));
            int id = Integer.parseInt(request.getParameter("id"));

            RolDAO objeto = new RolDAO();
            Boolean bandera = objeto.Editar(dni, rol, id);

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> respuesta = new HashMap<>();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (bandera) {
                respuesta.put("estado", "success");
                respuesta.put("mensaje", "Editado correctamente");
                respuesta.put("redirect", "rolesView.jsp");
            } else {
                respuesta.put("estado", "error");
                respuesta.put("mensaje", "No se puede editar.");
                respuesta.put("redirect", "rolEditar.jsp");
            }
            
            out.write(mapper.writeValueAsString(respuesta));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    
    private void Eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            RolDAO objeto = new RolDAO();
            Boolean bandera = objeto.Eliminar(id);

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> respuesta = new HashMap<>();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (bandera) {
                respuesta.put("estado", "success");
                respuesta.put("mensaje", "Eliminado correctamente");
            } else {
                respuesta.put("estado", "error");
                respuesta.put("mensaje", "No se puede eliminar.");
            }
            respuesta.put("redirect", "rolesView.jsp");

            out.write(mapper.writeValueAsString(respuesta));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
