package servlets;

import beans.ProductoBeans;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.conexionDB;

@WebServlet(name = "servletProductos", urlPatterns = {"/servletProductos"})
public class servletProductos extends HttpServlet {

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
        try {
            PreparedStatement pstaProductos = conexionDB.getConexion().prepareStatement("select * from productos");     
            ResultSet rsProductos = pstaProductos.executeQuery(); 
            
            ArrayList<ProductoBeans> arrayProductos = new ArrayList<>();
            
            while(rsProductos.next()){
                
                ProductoBeans producto = new ProductoBeans(rsProductos.getString(1),
                    rsProductos.getString(2), rsProductos.getString(3), rsProductos.getInt(4),
                    rsProductos.getDouble(5), rsProductos.getString(6));
                
                arrayProductos.add(producto);
            }            
            
            conexionDB.getConexion().close();
            
            request.setAttribute("listaProductos", arrayProductos);
                    
            request.getRequestDispatcher("Pages/productos.jsp").forward(request, response);                            
        } catch (IOException | SQLException | ServletException e) {
            System.out.println("Error Productos: " + e);
        }        
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