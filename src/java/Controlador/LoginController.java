package Controllers;

import Dao.UserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        if ("login".equals(accion)) {
            Login(request, response);
        }
    }

    private void Login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dni = request.getParameter("dni");
            String rol = request.getParameter("rol");

            UserDAO users = new UserDAO();
            boolean exists = users.Login(dni, rol);

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> respuesta = new HashMap<>();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (exists) {
                respuesta.put("estado", "success");
                respuesta.put("mensaje", "Usuario logeado correctamente");
                respuesta.put("redirect", "indicadoresView.jsp");
            } else {
                respuesta.put("estado", "error");
                respuesta.put("mensaje", "El usuario no existe.");
                respuesta.put("redirect", "login.jsp");
            }

            out.write(mapper.writeValueAsString(respuesta));

            throw new Exception("Credenciales inválidas");

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
