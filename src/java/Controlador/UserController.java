package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UserDAO;
import Models.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

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
    
    private void Seleccionar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            String di = request.getParameter("dni");
            
            UserDAO users = new UserDAO();
            List<UserModel> listUser = users.Seleccionar(di);
            
            UserModel user = listUser.get(0);
            
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("estado", "success");
            respuesta.put("usuario", user);

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
            String dni = request.getParameter("dni");
            String nom = request.getParameter("nom");
            String app = request.getParameter("app");
            int gen = Integer.parseInt(request.getParameter("gen"));
            int edad = Integer.parseInt(request.getParameter("edad"));

            UserDAO users = new UserDAO();
            Boolean bandera = users.Editar(dni, nom, app, gen, edad);

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> respuesta = new HashMap<>();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (bandera) {
                respuesta.put("estado", "success");
                respuesta.put("mensaje", "Usuario editado correctamente");
                respuesta.put("redirect", "userView.jsp");
            } else {
                respuesta.put("estado", "error");
                respuesta.put("mensaje", "El usuario no se puede editar.");
                respuesta.put("redirect", "userEditar.jsp");
            }
            
            out.write(mapper.writeValueAsString(respuesta));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    private void Eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dni = request.getParameter("dni");

            UserDAO users = new UserDAO();
            Boolean bandera = users.Eliminar(dni);

            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> respuesta = new HashMap<>();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (bandera) {
                respuesta.put("estado", "success");
                respuesta.put("mensaje", "Usuario eliminado correctamente");
            } else {
                respuesta.put("estado", "error");
                respuesta.put("mensaje", "El usuario no se puede eliminar.");
            }
            respuesta.put("redirect", "userView.jsp");

            out.write(mapper.writeValueAsString(respuesta));

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    private void Registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dni = request.getParameter("dni");
            String nom = request.getParameter("nom");
            String app = request.getParameter("app");
            String gen = request.getParameter("gen");
            String edad = request.getParameter("edad");

            if (dni.length() == 8) {
                UserDAO users = new UserDAO();
                Boolean bandera = users.Registrar(nom, app, gen, edad, dni);

                PrintWriter out = response.getWriter();
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> respuesta = new HashMap<>();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                if (bandera) {
                    respuesta.put("estado", "success");
                    respuesta.put("mensaje", "Usuario creado correctamente");
                    respuesta.put("redirect", "userView.jsp");
                } else {
                    respuesta.put("estado", "error");
                    respuesta.put("mensaje", "El DNI ya existe.");
                    respuesta.put("redirect", "userRegistrar.jsp");
                }

                out.write(mapper.writeValueAsString(respuesta));
            }

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    private void Listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDAO users = new UserDAO();
            List<UserModel> listUser = users.Listar();

            PrintWriter out = response.getWriter();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            out.print(mapper.writeValueAsString(listUser));

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
