/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Interfaces.Metodos;
import conexion.Conexion;
import productos.Productos;

/**
 *
 * @author LN710Q
 */
public class ProductosDao implements Metodos <Productos>{
    public static final Conexion con = Conexion.conectar();
    private static final String SQL_INSERT = "INSERT INTO Productos (codigo,nombre,tipo,cantidad,precio) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE Productos SET nombre = ?, tipo = ?, cantidad = ?,precio = ? WHERE codigo=?";
    private static final String SQL_DELETE = "DELETE FROM Productos WHERE codigo=?";
    private static final String SQL_READ = "SELECT * FROM Productos WHERE codigo=?";
    private static final String SQL_READALL = "SELECT * FROM Productos";
    @Override
    public boolean create(Productos g){
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getCodigo());
            ps.setString(2, g.getNombre());
            ps.setString(3, g.getTipo());
            ps.setInt(4, g.getCantidad());
            ps.setInt(5, g.getPrecio());
            ps.setBoolean(6, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally{
            con.cerrarConexion();
        }
        return false;
    }
    @Override
    public boolean delete(Object key){
        PreparedStatement ps;
        try{
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            con.cerrarConexion();
        }
        return false;
    }
    @Override
    public boolean update(Productos c){
        PreparedStatement ps;
        try{
            System.out.println(c.getCodigo());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getCodigo());
            ps.setString(2, c.getNombre());
            ps.setBoolean(3, c.isDisponibiliodad());
            ps.setString(4, c.getCodigo());
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            con.cerrarConexion();
        }
        return false;
    }
    @Override
    public Productos read(Object key){
        Productos f = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            while(rs.next()){
               f = new Productos(rs.getString(1),rs.getString(2),rs.getString(3), rs.getInt(4),rs.getInt(5), rs.getBoolean(6));
            }
            rs.close();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return f;
    }
    @Override
    public ArrayList<Productos> readAll(){
        ArrayList<Productos> all = new ArrayList<>();
        Statement s;
        ResultSet rs;
        try{
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Productos(rs.getString(1),rs.getString(2),rs.getString(3), rs.getInt(4),rs.getInt(5), rs.getBoolean(6)));
            }
            rs.close();
        }catch (SQLException ex){
            Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }
}
