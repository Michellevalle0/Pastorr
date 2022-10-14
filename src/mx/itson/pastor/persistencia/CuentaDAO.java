/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.pastor.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.pastor.entidades.Cliente;
import mx.itson.pastor.entidades.Cuenta;

/**
 *
 * @author Michelle
 */
public class CuentaDAO {
    
    public static boolean agregar(String numero, Cliente cliente) {
        boolean resultado = false;
        try {
            Connection con = Conexion.obtener();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO cuenta (numero,idCliente) VALUES (?,?)");
            PreparedStatement st = con.prepareCall(sql.toString());
            st.setString(1, numero);
            st.setInt(2, cliente.getId());
            resultado = st.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return resultado;
    }
    
    public static List<Cuenta> obtenerCuentas(int idCliente) {
        List<Cuenta> listadoCuentas = new ArrayList();
        
        try {
            Connection con = Conexion.obtener();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT  distinct numero FROM pastorbd.cliente INNER JOIN cuenta ON cliente.id=cuenta.idCliente WHERE idCliente=?;");
            PreparedStatement st = con.prepareStatement(sql.toString());
         
            st.setInt(1, idCliente);
        
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                
                Cuenta c = new Cuenta();
                c.setNumero(rs.getString("numero"));
             
                
               
                
                listadoCuentas.add(c);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return listadoCuentas;
    }
    
    public static Cuenta obtenerPorID(int id) {
        Cuenta cuenta = new Cuenta();
        
        try {
            
            Connection con = Conexion.obtener();
            String consulta = "SELECT * FROM cuenta Where idCliente=? ";
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, id);
            
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                cuenta.setId(rs.getInt("id"));
                Cliente c = ClienteDAO.obtenerPorID(rs.getInt("idCliente"));
                cuenta.setCliente(c);
                cuenta.setNumero(rs.getString("numero"));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return cuenta;
    }
    
       public static Cuenta obtenerNumero(String numero) {
        Cuenta cuenta = new Cuenta();
        
        try {
            
            Connection con = Conexion.obtener();
            String consulta = "SELECT * FROM cuenta Where numero=? ";
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, numero);
            
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
            cuenta.setId(rs.getInt("id"));
            cuenta.setNumero(rs.getString("numero"));
            cuenta.setCliente(ClienteDAO.obtenerPorID(rs.getInt("idCliente")));
                       
                
               
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return cuenta;
    }
    
    
  
    
}
