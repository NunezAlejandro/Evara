
package Controllers;

import Dao.parametroDAO;
import Models.parametrosModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "parametroController", urlPatterns = {"/parametroController"})
public class parametroController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                Listar(request, response);
                break;
            default:
                break;
        }
    }
    
    private void Listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            parametroDAO objeto = new parametroDAO();
            List<parametrosModel> lista = objeto.Listar();

            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(lista));

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
