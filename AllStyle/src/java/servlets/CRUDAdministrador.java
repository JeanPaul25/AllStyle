/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.AdminDAO;
import beans.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.conexionDB;

/**
 *
 * @author sebas
 */
@WebServlet(name = "CRUDAdministrador", urlPatterns = {"/CRUDAdministrador"})
public class CRUDAdministrador extends HttpServlet {
    List usuario = new ArrayList();
    List cantC = new ArrayList();
     List cantA = new ArrayList();
    
    Usuarios user= new Usuarios();
    AdminDAO adao = new AdminDAO();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PreparedStatement ps ;
        ResultSet rs;
        String Accion = request.getParameter("Accion");
        switch(Accion){
            case "ListarUsuario":
                usuario = adao.ListarUsuario();
                cantA = adao.CantidadAdmin();
                cantC = adao.CantidadCliente();
                request.setAttribute("cantidadC", cantC.size());
                request.setAttribute("cantA", cantA.size());
                request.setAttribute("cantidad", usuario.size());
                request.setAttribute("ListUser", usuario);
                request.getRequestDispatcher("Administrador/AdministracionUsuarios.jsp").forward(request, response);
                break;
            case "BuscarUsuario":
               String id = request.getParameter("dni");
                usuario = adao.BuscarUsuario(id);
                request.setAttribute("Buscar", usuario);
                 request.getRequestDispatcher("Administrador/EditarUsuario.jsp").forward(request, response);
                break;
            case "Eliminar":
                String DNI = request.getParameter("DNI");
                try {
                    ps = conexionDB.getConexion().prepareStatement("delete  from usuarios where dni=?");
                    ps.setString(1, DNI);
                    ps.executeUpdate();

                } catch (Exception e) {
                }
                request.getRequestDispatcher("CRUDAdministrador?Accion=ListarUsuario").forward(request, response);
                break;
            case "Editar":
                String dni = request.getParameter("dni");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String telefono = request.getParameter("telefono");
                String pass = request.getParameter("pass");
                String correo = request.getParameter("correo");
                String rol = request.getParameter("rol");
                String fnacimiento = request.getParameter("fnacimiento");
                try {
                    ps = conexionDB.getConexion().prepareStatement("update usuarios(dni=?,nombre=?,apellido=?,telefono=?,correo=?,contraseña=?,rol=?,nacimiento=?)");
                    ps.setString(1, dni);
                    ps.setString(2, nombre);
                    ps.setString(3, apellido);
                    ps.setString(4, telefono);
                    ps.setString(5, correo);
                    ps.setString(6, pass);
                    ps.setString(7, rol);
                    ps.setString(8, fnacimiento);
                    ps.executeUpdate();
                    request.getRequestDispatcher("CRUDAdministrador?Accion=ListarUsuario").forward(request, response);
                } catch (Exception e) {
                }
                break;
            default:
                 request.getRequestDispatcher("Administrador/DashBoard.jsp").forward(request, response);
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
        
        PreparedStatement ps;
        ResultSet rs;
                String dni = request.getParameter("dni");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String telefono = request.getParameter("telefono");
                String pass = request.getParameter("pass");
                String correo = request.getParameter("correo");
                String rol = request.getParameter("rol");
                String fnacimiento = request.getParameter("fnacimiento");
                try {
                ps = conexionDB.getConexion().prepareStatement("insert into usuarios values(?,?,?,?,?,?,?,?)");
                ps.setString(1, dni);
                ps.setString(2, nombre);
                ps.setString(3, apellido);
                ps.setString(4, telefono);
                ps.setString(5, correo);
                ps.setString(6, pass);
                ps.setString(7, rol);
                ps.setString(8, fnacimiento);
                ps.executeUpdate();
                request.getRequestDispatcher("CRUDAdministrador?Accion=ListarUsuario").forward(request, response);
                } catch (Exception e) {
                    
                }
        
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
