
package Controllers;


import Dao.cartillaDAO;
import Dao.indicadorDAO;
import Models.RolModel;
import Models.cartillaModel;
import java.util.*;
import Models.indicadoresModel;
import Models.totalEstadoEscolaresModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "indicadorController", urlPatterns = {"/indicadorController"})
public class indicadorController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "listar":
                Listar(request, response);
                break;
            case "listarFecha":
                ListarFecha(request, response);
                break;
            case "listarCartilla":
                ListarCartilla(request, response);
                break;
            case "listarSanos":
                ListarSanos(request, response);
                break;
            case "listarNoSanos":
                ListarNoSanos(request, response);
                break;
            case "registrar":
                Registrar(request, response);
                break;
            case "seleccionar":
                Seleccionar(request, response);
                break;
            case "editar":
                Editar(request, response);
                break;
        }
    }
    
    private void Listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            indicadorDAO objeto = new indicadorDAO();
            List<indicadoresModel> lista = objeto.Listar();
            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(lista));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    
    private void ListarSanos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            indicadorDAO objeto = new indicadorDAO();
            List<totalEstadoEscolaresModel> lista = objeto.ListarSanos();
            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(lista));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    
    private void ListarNoSanos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            indicadorDAO objeto = new indicadorDAO();
            List<totalEstadoEscolaresModel> lista = objeto.ListarNoSanos();
            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(lista));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    
    private void ListarFecha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String di = request.getParameter("di");
        
        try {
            cartillaDAO objeto = new cartillaDAO();
            List<cartillaModel> lista = objeto.ListarFecha(di);
            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(lista));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    
    private void ListarCartilla(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String di = request.getParameter("di");
        String fecha = request.getParameter("fecha");
        
        try {
            cartillaDAO objeto = new cartillaDAO();
            List<cartillaModel> lista = objeto.ListarCartilla(di, fecha);
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
            double peso = Double.parseDouble(request.getParameter("peso"));
            double talla = Double.parseDouble(request.getParameter("talla"));
            double hemoglobina = Double.parseDouble(request.getParameter("hemoglobina"));
            double glucosa = Double.parseDouble(request.getParameter("glucosa"));
            
            cartillaDAO objeto = new cartillaDAO();
            Boolean bandera = objeto.Registrar(peso, talla, hemoglobina, glucosa, dni);

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> respuesta = new HashMap<>();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (bandera) {
                respuesta.put("estado", "success");
                respuesta.put("mensaje", "Creado correctamente");
                respuesta.put("redirect", "indicadoresView.jsp");
            } else {
                respuesta.put("estado", "error");
                respuesta.put("mensaje", "No se puede crear.");
                respuesta.put("redirect", "cartillaRegistrar.jsp");
            }

            out.write(mapper.writeValueAsString(respuesta));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    
    private void Seleccionar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            int id = Integer.parseInt(request.getParameter("id"));
            
            cartillaDAO objeto = new cartillaDAO();
            List<cartillaModel> lista = objeto.Seleccionar(id);
            
            cartillaModel mostrar = lista.get(0);
            
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
    
    private void Editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String dni = request.getParameter("dni");
            double peso = Double.parseDouble(request.getParameter("peso"));
            double talla = Double.parseDouble(request.getParameter("talla"));
            double hemoglobina = Double.parseDouble(request.getParameter("hemoglobina"));
            double glucosa = Double.parseDouble(request.getParameter("glucosa"));

            cartillaDAO objeto = new cartillaDAO();
            Boolean bandera = objeto.Editar(peso, talla, hemoglobina, glucosa, dni, id);

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> respuesta = new HashMap<>();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (bandera) {
                respuesta.put("estado", "success");
                respuesta.put("mensaje", "Editado correctamente");
                respuesta.put("redirect", "indicadoresView.jsp");
            } else {
                respuesta.put("estado", "error");
                respuesta.put("mensaje", "No se puede editar.");
                respuesta.put("redirect", "cartillaEditar.jsp");
            }
            
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
