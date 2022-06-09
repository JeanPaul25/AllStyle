/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import utils.conexionDB;
import beans.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    ResultSet rs;
    PreparedStatement ps;
    public List BuscarUsuario(String DNI){
            ArrayList<Usuarios> listU = new ArrayList<>();
                try {
                    ps = conexionDB.getConexion().prepareStatement("select * from usuarios where dni=?");
                    ps.setString(1, DNI);
                    rs= ps.executeQuery();
                   
                    while (rs.next()) {
                       Usuarios u = new Usuarios();
                        u.setDNI(rs.getString(1));
                        u.setNombre(rs.getString(2));
                        u.setApellido(rs.getString(3));
                        u.setTelefono(rs.getString(4));
                        u.setCorreo(rs.getString(5));
                        u.setContraseña(rs.getString(6));
                        u.setRol(rs.getString(7));
                        u.setFnacimiento(rs.getString(8));
                        listU.add(u);
                      
                    }
                   
                } catch (Exception e) {
                }
        return listU;
    }
    public List ListarUsuario(){
         ArrayList<Usuarios> ListarUsuario = new ArrayList<>();
        
        try {
            ps = conexionDB.getConexion().prepareStatement("select * from usuarios");
            rs = ps.executeQuery();
            while (rs.next()) {
            Usuarios user = new Usuarios();
            user.setDNI(rs.getString(1));
            user.setNombre(rs.getString(2));
            user.setApellido(rs.getString(3));
            user.setTelefono(rs.getString(4));
            user.setCorreo(rs.getString(5));
            user.setContraseña(rs.getString(6));
            user.setRol(rs.getString(7));
            user.setFnacimiento(rs.getString(8));
            ListarUsuario.add(user);
            }
             
        } catch (Exception e) {
            
        }
         return ListarUsuario;
    }
    public List CantidadAdmin(){
         ArrayList<Usuarios> ListarUsuario = new ArrayList<>();
        
        try {
            ps = conexionDB.getConexion().prepareStatement("select * from usuarios where rol='admin'");
            rs = ps.executeQuery();
            while (rs.next()) {
            Usuarios user = new Usuarios();
            user.setDNI(rs.getString(1));
            user.setNombre(rs.getString(2));
            user.setApellido(rs.getString(3));
            user.setTelefono(rs.getString(4));
            user.setCorreo(rs.getString(5));
            user.setContraseña(rs.getString(6));
            user.setRol(rs.getString(7));
            user.setFnacimiento(rs.getString(8));
            ListarUsuario.add(user);
            }
             
        } catch (Exception e) {
            
        }
         return ListarUsuario;
    }
     public List CantidadCliente(){
         ArrayList<Usuarios> ListarUsuario = new ArrayList<>();
        
        try {
            ps = conexionDB.getConexion().prepareStatement("select * from usuarios where rol='cliente'");
            rs = ps.executeQuery();
            while (rs.next()) {
            Usuarios user = new Usuarios();
            user.setDNI(rs.getString(1));
            user.setNombre(rs.getString(2));
            user.setApellido(rs.getString(3));
            user.setTelefono(rs.getString(4));
            user.setCorreo(rs.getString(5));
            user.setContraseña(rs.getString(6));
            user.setRol(rs.getString(7));
            user.setFnacimiento(rs.getString(8));
            ListarUsuario.add(user);
            }
             
        } catch (Exception e) {
            
        }
         return ListarUsuario;
    }
    public int EliminarUsuario(String DNI){
        try {
            ps = conexionDB.getConexion().prepareStatement("delete  from usuarios where dni=?");
            ps.setString(1, DNI);
            ps.executeUpdate();
            
        } catch (Exception e) {
        }
            
        return 1;
    }
}
