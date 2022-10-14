/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.pastor.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.itson.pastor.entidades.Cliente;

/**
 *
 * @author Michelle
 */
public class ClienteDAO {

    public static List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList();

        try {
            Connection con = Conexion.obtener();
            Statement statment = con.createStatement();
            ResultSet resulSet = statment.executeQuery("Select * from cliente");
            while (resulSet.next()) {
                Cliente c = new Cliente();
                c.setId(resulSet.getInt(1));
                c.setNombre(resulSet.getString(2));
                c.setDireccion(resulSet.getString(3));
                c.setTelefono(resulSet.getString(4));
                c.setEmail(resulSet.getString(5));
                clientes.add(c);
            }
        } catch (Exception e) {
            System.err.print(e);
        }

        return clientes;
    }

    public static Cliente obtenerPorNombre(String nombre) {

        Cliente cliente = new Cliente();
        try {

            Connection con = Conexion.obtener();
            String consulta = "SELECT * FROM cliente WHERE nombre=? ";
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, nombre);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setDireccion(rs.getString(3));
                cliente.setTelefono(rs.getString(4));
                cliente.setEmail(rs.getString(5));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return cliente;
    }
    public static Cliente obtenerPorID(int id) {

        Cliente cliente = new Cliente();
        try {

            Connection con = Conexion.obtener();
            String consulta = "SELECT * FROM cliente WHERE id=? ";
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setDireccion(rs.getString(3));
                cliente.setTelefono(rs.getString(4));
                cliente.setEmail(rs.getString(5));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return cliente;
    }

    public static Cliente obtenerCliente(String busqueda) {

        Cliente cliente = new Cliente();
        try {

            Connection con = Conexion.obtener();
            String consulta = "SELECT * FROM cliente WHERE id=? OR nombre=? OR direccion=? OR telefono=? OR email=?";
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, busqueda);
            st.setString(2, busqueda);
            st.setString(3, busqueda);
            st.setString(4, busqueda);
            st.setString(5, busqueda);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setDireccion(rs.getString(3));
                cliente.setTelefono(rs.getString(4));
                cliente.setEmail(rs.getString(5));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return cliente;
    }

    public static boolean guardar(Cliente c) {
        boolean resultado = false;

        try {
            Connection con = Conexion.obtener();
            String consulta = "INSERT INTO cliente (nombre,direccion,telefono,email) VALUES (?,?,?,?) ";
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, c.getNombre());
            st.setString(2, c.getDireccion());
            st.setString(3, c.getTelefono());
            st.setString(4, c.getEmail());
            resultado = st.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

        return resultado;
    }

    public static boolean obtenerPorEmail(String email) {
        boolean resultado = false;
        try {

            Connection con = Conexion.obtener();
            String consulta = "SELECT * FROM cliente WHERE email=?";
            PreparedStatement statement = con.prepareStatement(consulta);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            resultado = rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }

        return resultado;
    }

}
