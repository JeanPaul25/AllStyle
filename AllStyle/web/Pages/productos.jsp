<%@page import="java.util.ArrayList"%>
<%@page import="beans.ProductoBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Style || 2022</title>
    <link rel="stylesheet" href="css/modal.css">
    <link rel="stylesheet" href="css/estilos.css">
    <link rel="stylesheet" href="css/productos.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
        integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/ec4896fd00.js" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="img/tshirt.png" type="image/x-icon">
</head>

<body>      
    <%
        String usuario = (String)session.getAttribute("usuario");
    %>
    
    <header class="header">
        <div class="header_1">
            <div class="titulo_header">
                <a href="index.jsp">
                    <h1>All Style</h1>
                </a>
            </div>
            <div class="header_1_ul">
                <ul class="header_1_li">
                    <li><a href="servletProductos">Productos</a></li>
                    <li><a href="#">Contacto</a></li>
                    <li><a href="#">Nosotros</a></li>
                </ul>
            </div>
        </div>
        
        <%
            if(usuario == null){
            %>
        
        <div class="header_2">
            <div class="header_2_a_1">
                <a id="Login" style="cursor: pointer;"><i class="fa-regular fa-user"></i> <span> Iniciar Sesion </span></a>
            </div>
            <div class="header_2_a_2">
                <a href="#"> <img src="img/cart.png" alt=""><span>(10)</span></a>
            </div>
        </div>

        <%
            }else{
            %>
        
        <div class="header_2">
            <div class="header_2_a_1" style="margin-top: 13px;">
                <a id="Login" style="cursor: pointer;" href="servletLogin?method=GET"><i class="fa-regular fa-user"></i> <span> <%=usuario%> </span></a>
            </div>
            <div class="header_2_a_2">
                <a href="#" onclick="AbrirNav()"> <img src="img/bars.png" alt=""> </a>
            </div>
        </div>  
            
        <%
            }
            %>

    </header>


    <div class="botones">
        <div class="boton_bar">
            <a href="#"><img src="img/bars.png" alt=""></a>
        </div>
        <div class="boton_cart">
            <a href="#" class="">
                <img src="img/cart2.png" alt="">

            </a>
            <span class="contador">10</span>
        </div>
    </div>

    <div class="contenedor_productos">
        
        <%
            ArrayList<ProductoBeans> arrayProductos = (ArrayList<ProductoBeans>)request.getAttribute("listaProductos");

            for (int i = 0; i < arrayProductos.size(); i++) {    
                ProductoBeans producto = arrayProductos.get(i);
            %>
        
        <div class="productos_fila">    
            <div class="productos">
                <img src="img/tshirt.png"  alt="Avatar" style="">
                <div class="contenido_producto">
                    <p>Producto: <%=producto.getNombre()%> </p>
                    <p>Precio: <%=producto.getPrecio()%> </p>
                    <p>Categoria:</p> 
                   <div class="btn_productos">
                    <a id="añadir" href="#"> <i class="fa-solid fa-cart-shopping"></i> Añadir </a>
                   </div>
                </div>
            </div>
            
        <%            
            }
            %>   
           
        <!-- Login -->
        <div class="contenedor_modal" id="ventana_login" >    
               <form action="servletLogin" class="form" method="POST">
                   <a id="CerrarVentana" class="cerrar_login"><img src="img/close.png" alt=""></a>
                   <h1 class="titulo_login">All Style</h1>
                   <div class="label input">
                       <label>Email</label>
                       <input type="text" name="fEmail">
                   </div>
                   <div class="label input">
                       <label>Contraseña</label>
                       <input type="password" name="fPassword">
                   </div>
                   <div class="btn_login">
                       <button type="submit">Iniciar Sesion</button>
                       <a id="registrarVentana">Registrar</a>
                   </div>
                   <p class="msg">Tu Contraseña Es privada !No lo Compartas¡</p>
               </form>    
       </div>     
            
               
        </div>       
    </div>
            
    <script src="js/modal.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>  
</html>
