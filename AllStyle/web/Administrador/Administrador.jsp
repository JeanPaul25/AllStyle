<%-- 
    Document   : Administrador
    Created on : 02/06/2022, 12:59:40 AM
    Author     : sebas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Style || 2022</title>
    <link rel="stylesheet" href="../css/modal.css">
    <link rel="stylesheet" href="../css/adminitrador.css">
    <link rel="stylesheet" href="../css/productos.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
        integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/ec4896fd00.js" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="img/tshirt.png" type="image/x-icon">
</head>

<body>
    <header class="header" id="main">
        <div class="header_1">
            <div class="titulo_header">
                <a href="#">
                    <h1>All Style</h1>
                </a>
            </div>
            <div class="header_1_ul">
                <ul class="header_1_li">
                    <li><a href="#">Estadistica</a></li>
                    <li><a href="#">Productos</a></li>
                    <li><a href="#">Soporte</a></li>
                </ul>
            </div>
        </div>

        <div class="header_2">
            <div class="header_2_a_1" style="margin-top: 13px;">
                <a id="Login" style="cursor: pointer;"><i class="fa-regular fa-user"></i> <span> ANGEL SEBASTIAN BARRIENTOS GOMEZ</span></a>
            </div>
            <div class="header_2_a_2">
                <a href="#" onclick="AbrirNav()"> <img src="../img/bars.png" alt=""> </a>
            </div>
        </div>


    </header>

    <div id="barraLateral" class=" barraLateral">
        <a href="javascript:void(0)" class="closebtn" onclick="cerrarNav()">&times;</a>
        <a href="#">OPCION 1</a>
        <a href="#">OPCION 2</a>
        <a href="#">OPCION 3</a>
        <a href="#">OPCION 4</a>
      </div>
      
      
      
      <script>
      function AbrirNav() {
        document.getElementById("barraLateral").style.width = "250px";
        document.getElementById("main").style.marginRight = "250px";
      }
      
      function cerrarNav() {
        document.getElementById("barraLateral").style.width = "0";
        document.getElementById("main").style.marginRight= "0";
      }
      </script>

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

   






<script>
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementById("main").style.marginLeft= "0";
}
</script>
   
<script src="js/modal.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>
