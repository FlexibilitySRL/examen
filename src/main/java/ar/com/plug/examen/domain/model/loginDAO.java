/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Declaracion de la clase loginDao
 * @author G&L
 */
public class loginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    /**
     * 
     * @param correo
     * @param pass
     * @return 
     */
    public login log(String correo, String pass){
        login l = new login();
        String sql = "SELECT * FROM usuarios WHERE correo =? AND pass = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs= ps.executeQuery();
            if(rs.next()){
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
            }
                                                                                
                
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return l;
    }
}
