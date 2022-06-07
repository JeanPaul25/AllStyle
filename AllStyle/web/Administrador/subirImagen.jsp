<%@page import="beans.ProductoBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.*" %>
<%@ page import="java.io.*" %>

<%
    String img="";
    try{
        /*FileItemFactory es una interfaz para crear FileItem*/
        FileItemFactory file_factory = new DiskFileItemFactory();
		
	/*ServletFileUpload esta clase convierte los input file a FileItem*/
        ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
        
	/*sacando los FileItem del ServletFileUpload en una lista */
        List items = servlet_up.parseRequest(request);
     
        for(int i=0;i<items.size();i++){
	/*FileItem representa un archivo en memoria que puede ser pasado al disco duro*/
            FileItem item = (FileItem) items.get(i);
        /*item.isFormField() false=input file; true=text field*/
            if (!item.isFormField()){
                /*cual sera la ruta al archivo en el servidor*/
                File archivo_server = new File("F:\\2022 - 1\\Análisis y Diseño de Sistemas de Información\\AllStyle\\AllStyle\\web\\imgProductos\\"+item.getName());
                /*y lo escribimos en el servidor*/
                item.write(archivo_server);
                img=item.getName();
            }
        }
            System.out.println("222222222");
            
                            String idProducto;
                   String nombre;
                   String descripcion;
                   int stock;
                   double precio;
                   String imagen;
        ProductoBeans producto = new ProductoBeans(request.getParameter("fId"), request.getParameter("fNombre"),
            request.getParameter("fDescripcion"), Integer.parseInt(request.getParameter("fStock")), 
            Double.parseDouble(request.getParameter("fPrecio")), img);
        
        ArrayList<ProductoBeans> listaProducto = new ArrayList<ProductoBeans>();
        listaProducto.add(producto);
            
            System.out.println("333333333333");
        
        request.setAttribute("producto", listaProducto);
        request.getRequestDispatcher("servletAgregarProducto").forward(request, response);    
    }catch(Exception e){
        System.out.println("Error Subir Imagen: "+e);
    }        
        
%>