/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.ConexionSQL;
import javax.servlet.http.HttpSession;  
import DAO.AdminDAO;

@WebServlet(name = "servletLogin", urlPatterns = {"/servletLogin"})
public class servletLogin extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
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

        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String email = request.getParameter("fEmail");
        System.out.println(email);
        String password = request.getParameter("fPassword");
        System.out.println(password);
        String rol = request.getParameter("fRol");
        String usuario = null;
        HttpSession session = request.getSession();         
        try {
            PreparedStatement pstaLogin = ConexionSQL.getConexion().prepareStatement("select * from usuarios where correo=? and contraseña=?");                                           
            pstaLogin.setString(1, email);
            pstaLogin.setString(2, password);
            ResultSet rsLogin = pstaLogin.executeQuery(); 
            
            while(rsLogin.next()){
                if(rsLogin.getString(2) != null){                    
                    String[] nombres = rsLogin.getString(2).split(" ");
                    String[] apellidos = rsLogin.getString(3).split(" ");
                    usuario = nombres[0] + " " + apellidos[0];                  
                }else{
                    System.out.println("No hay usuario");
                }
                
                session.setAttribute("usuario", usuario);
                
                if(rsLogin.getString(7).equals("Cliente")){
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }else if(rsLogin.getString(7).equals("admin")){
                   int cantidad2 = adao.CantidadusuarioCliente();
                   int cantidad = adao.CantUsuarioAdmin();
                   int TotalUsuario = adao.TotalUsuario();
                   int TotalProductos = adao.TotalProductos();
                   int TotalBoletas = adao.TotalBoletas();
                   request.setAttribute("TotalBoletas", TotalBoletas);
                   request.setAttribute("TotalProductos", TotalProductos);
                   request.setAttribute("TotalUsuario",TotalUsuario);
                    request.setAttribute("usuario", cantidad);
                    request.setAttribute("admin", cantidad2);
                    request.getRequestDispatcher("Administrador/DashBoard.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }  
            }            
        } catch (IOException | SQLException | ServletException e) {
            System.out.println("Error Login: " + e);
        }        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
