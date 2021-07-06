/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.model;

import com.sun.istack.logging.Logger;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sun.util.logging.PlatformLogger;

	
/**
 *Declaracion de la clase ClienteDAO
 * @author G&L
 */
public class ClienteDao {
	Conexion cn = new Conexion();
	Connection con;
	PreparedStatement ps;
        ResultSet rs;
	/**
         * Declaracion del metodo Registar Cliente
         * @param cl
         * @return 
         */
	public boolean RegistrarCliente(Cliente cl) {
		String sql = "INSERT INTO cliente (cliente,nombre,telefono,direccion,razon) VALUES (?,?,?,?,?)";
		
		try {
			con = cn.getConnection();
                        ps = con.prepareStatement(sql);
			ps.setInt(1,cl.getDNI());
                        ps.setString(2, cl.getNombre());
			ps.setInt(3,cl.getTelefono());
                        ps.setString(4, cl.getDireccion());
                        ps.setString(5, cl.getRazon());
                        ps.execute();
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,e.toString());
                        return false;
		}finally{
                    
                }try{
                    con.close();
                    
                }catch(SQLException e){
                    System.out.println(e.toString());
                }
            return false;
          
    }   
       /**
        * Declaracion del metodo Listar Cliente
        * @return 
        */
        public ArrayList<Cliente> ListarCliente(){
            ArrayList<Cliente> ListaCl = new ArrayList();
            String sql = "SELECT * FROM clientes";
            try{
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Cliente cl = new Cliente();
                    cl.setId(rs.getInt("id"));
                    cl.setDNI(rs.getInt("dni"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setTelefono(rs.getInt("telefono"));
                    cl.setDireccion(rs.getString("direccion"));
                    cl.setRazon(rs.getString("razon"));
                    ListaCl.add(cl);
                }
            }catch (SQLException e){
                System.out.println(e.toString());
            }
            return ListaCl;
        }
       /**
        * Declaracion del metodo Eliminar Cliente
        * @param id
        * @return 
        */
        public boolean EliminarCliente(int id){
            String sql = "DELETE FROM cliente WHERE  id =?";
            try{
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.execute();
                return true;
            }catch (SQLException e ){
                System.out.println(e.toString());
                return false;
            }finally{
                try{
                con.close();
            }catch(Exception ex){
                   System.out.println(ex.toString());
                    }
        }
        }
        /**
         * Declaracion del metodo Modificar Cliente
         * @param cl
         * @return 
         */
        public boolean ModificarCliente(Cliente cl){
            String sql = "UPDATE clientes SET dni=?, nombre=?, telefono=?, direccion=?,razon=? WHERE id=?";
            try{
                ps = con.prepareStatement(sql);
                ps.setInt(1, cl.getDNI());
                ps.setString(2,cl.getNombre());
                ps.setInt(3, cl.getTelefono());
                ps.setString(4, cl.getDireccion());
                ps.setString(5, cl.getRazon());
                ps.setInt(6, cl.getId());
                ps.execute();
                return true;
            }catch(SQLException e){
                System.out.println(e.toString());
                return false;
            }finally{
                try{
                    con.close();
                }catch(SQLException e){
                    System.out.println(e.toString());
                }
            }
        }
}
