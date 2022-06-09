/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.AdminDAO;
import beans.Boletas;
import beans.DetallesBoleta;
import beans.Productos;
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
    List p = new ArrayList();
    List Bol = new ArrayList();
    List detalles = new ArrayList();
    DetallesBoleta detB  = new DetallesBoleta();
    Boletas boletas = new Boletas();
    Productos productos = new Productos();
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
            case "ListarBoleta":
                Bol = adao.ListarBoleta();
                 request.setAttribute("ListarBoletas", Bol);
                  request.getRequestDispatcher("Administrador/AdministracionBoletas.jsp").forward(request, response);
                break;
            case "BuscarBoleta":
                String IDboleta = request.getParameter("IDboleta");
                Bol = adao.BuscarBoleta(IDboleta);
                detalles = adao.DetallesBol(IDboleta);
                request.setAttribute("detalles", detalles);
                request.setAttribute("BuscarBoleta", Bol);
                 request.getRequestDispatcher("Administrador/EditarBoleta.jsp").forward(request, response);
                break;
            case "ListarProducto":
                 p = adao.ListarProducto();
                 request.setAttribute("cantidadP", p.size());
                 request.setAttribute("ListProductos", p);
                 request.getRequestDispatcher("Administrador/AdministracionProducto.jsp").forward(request, response);
                break;
            case "BuscarProducto":
                String idp = request.getParameter("idp");
                p = adao.BuscarP(idp);
                request.setAttribute("Buscar", p);
                 request.getRequestDispatcher("Administrador/EditarProducto.jsp").forward(request, response);
                break;
            case "EliminarProducto":
                 String idProducto = request.getParameter("idp");
                try {
                    ps = conexionDB.getConexion().prepareStatement("delete  from productos where idproducto=?");
                    ps.setString(1, idProducto);
                    ps.executeUpdate();

                } catch (Exception e) {
                }
                request.getRequestDispatcher("CRUDAdministrador?Accion=ListarProducto").forward(request, response);
                break;
            case "":
              
                break;
                
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
        String Accionar = request.getParameter("accion");               
                if(Accionar.equals("Registrar")){
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
                }  else if(Accionar.equals("InsertProducto")){
                  String idproductoinsert = request.getParameter("idproducto");
                String nombrep = request.getParameter("nombrep");
                String descp = request.getParameter("apellido");
                int stock = Integer.parseInt(request.getParameter("stock"));
                float precio = Float.parseFloat(request.getParameter("precio"));
                String genero = request.getParameter("genero");
                String categoria = request.getParameter("categoria");
                String imagen = request.getParameter("imagen");
                try {
                    ps = conexionDB.getConexion().prepareStatement("insert into productos values(?,?,?,?,?,?,?,?)");
                    ps.setString(1, idproductoinsert);
                    ps.setString(2, nombrep);
                    ps.setString(3, descp);
                    ps.setInt(4, stock);
                    ps.setFloat(5, precio);
                    ps.setString(6, genero);
                    ps.setString(7, categoria);
                    ps.setString(8, imagen);
                    ps.executeUpdate();
                    request.getRequestDispatcher("/CRUDAdministrador?Accion=ListarProducto").forward(request, response);
                    
                } catch (Exception e) {
                }
                 
                } else if(Accionar.equals("EditarUsuario")){
                     String dni = request.getParameter("dni");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String telefono = request.getParameter("telefono");
                String pass = request.getParameter("pass");
                String correo = request.getParameter("correo");
                String rol = request.getParameter("rol");
                String fnacimiento = request.getParameter("fnacimiento");
                try {
                    ps = conexionDB.getConexion().prepareStatement("update usuarios set dni=?,nombre=?,apellido=?,telefono=?,correo=?,contraseña=?,rol=?,nacimiento=?");
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
