package Controllers;

import Dao.alumnoDAO;
import Dao.institucionDAO;
import Models.alumnosModel;
import Models.institucionesModel;

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

@WebServlet(name = "alumnoController", urlPatterns = {"/alumnoController"})
public class alumnoController extends HttpServlet {

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
            case "institucion":
                Institucion(request, response);
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
    
    private void Institucion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            alumnoDAO objeto = new alumnoDAO();
            List<institucionesModel> lista = objeto.Instituciones();

            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(lista));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    private void Listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            alumnoDAO objeto = new alumnoDAO();
            List<alumnosModel> lista = objeto.Listar();

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
            
            alumnoDAO objeto = new alumnoDAO();
            List<alumnosModel> lista = objeto.Seleccionar(id);
            
            alumnosModel mostrar = lista.get(0);
            
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
    
    private void Registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dni = request.getParameter("dni");
            int ins = Integer.parseInt(request.getParameter("ins"));
            
            alumnoDAO objeto = new alumnoDAO();
            Boolean bandera = objeto.Registrar(dni, ins);

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> respuesta = new HashMap<>();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (bandera) {
                respuesta.put("estado", "success");
                respuesta.put("mensaje", "Creado correctamente");
                respuesta.put("redirect", "alumnosView.jsp");
            } else {
                respuesta.put("estado", "error");
                respuesta.put("mensaje", "No se puede crear.");
                respuesta.put("redirect", "alumnoRegistrar.jsp");
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
            int ins = Integer.parseInt(request.getParameter("ins"));
            int id = Integer.parseInt(request.getParameter("id"));

            alumnoDAO objeto = new alumnoDAO();
            Boolean bandera = objeto.Editar(dni, ins, id);

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> respuesta = new HashMap<>();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (bandera) {
                respuesta.put("estado", "success");
                respuesta.put("mensaje", "Editado correctamente");
                respuesta.put("redirect", "alumnosView.jsp");
            } else {
                respuesta.put("estado", "error");
                respuesta.put("mensaje", "No se puede editar.");
                respuesta.put("redirect", "alumnoEditar.jsp");
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

            alumnoDAO objeto = new alumnoDAO();
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
            respuesta.put("redirect", "alumnosView.jsp");

            out.write(mapper.writeValueAsString(respuesta));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
