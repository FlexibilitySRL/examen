/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.model;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 * Declaracion del la clase ProductoDao
 * @author G&L
 */
public class ProductosDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
   /**
    * Declaracion del metodo Registar productos
    * @param pro
    * @return 
    */
    public boolean RegistrarProductos(Productos pro){
        String sql = "INSERT INTO productos(codigo,nombre,proveedor,stock,precio) VALUES (?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.execute();
            return true;
            
        }catch (SQLException e){
            System.out.println(e.toString());
            return false;
            
        }
    }
   /**
    * Declaracion del metodo Consultar Proveedor
    * @param proveedor 
    */
    public void ConsultarProveedor(JComboBox proveedor){
        String sql = "SELECT nombre FROM  proveedor";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                proveedor.addItem(rs.getString("nombre"));
            }
            }catch(SQLException e){
                System.out.println(e.toString());
        }
        
        
    }
    
    /**
     * Declaracion del metodo listar Productos
     * @return 
     */
    
    
    public ArrayList<Productos> ListarProductos(){
        ArrayList<Productos> Listapro = new ArrayList();
        String sql = "SELECT * FROM productos";
        try{
             con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    Productos pro = new Productos();
                    pro.setId(rs.getInt("id"));
                    pro.setCodigo(rs.getString("codigo"));
                    pro.setNombre(rs.getString("nombre"));
                    pro.setProveedor(rs.getString("proveedor"));
                    pro.setStock(rs.getInt("stock"));
                    pro.setPrecio(rs.getDouble("precio"));
                    Listapro.add(pro);
        }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return Listapro;
    }
    /**
     * Declaracion del metodo Eliminar Producto
     * @param id
     * @return 
     */
    public boolean EliminarProductos(int id){
            String sql = "DELETE FROM productos WHERE  id =?";
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
     * Declaracion del metodo Modificiar Productos
     * @param pro
     * @return 
     */
     public boolean ModificarProductos(Productos pro){
            String sql = "UPDATE productos SET codigo=?, nombre=?, proveedor=?, stock=?,precio=? WHERE id=?";
            try{
                ps = con.prepareStatement(sql);
                ps.setString(1, pro.getCodigo());
                ps.setString(2,pro.getNombre());
                ps.setString(3, pro.getProveedor());
                ps.setInt(4, pro.getStock());
                ps.setDouble(5, pro.getPrecio());
                ps.setInt(6, pro.getId());
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
