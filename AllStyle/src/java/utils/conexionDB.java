package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexionDB {
    public static Connection getConexion(){
        Connection con = null;
        String cadena = "jdbc:mysql://localhost/°°?user=root&password=";
        try{
            Class.forName("com.mysql.jdbc.Driver");            
            con = DriverManager.getConnection(cadena);            
            
            System.out.println("Conexión Satisfactoria");
        }catch(Exception e){
            System.out.println("Error de conexión " + e);
        }
        
        return con;
    }
    
    public static void main(String[] args) {
        getConexion();        
    }
}
