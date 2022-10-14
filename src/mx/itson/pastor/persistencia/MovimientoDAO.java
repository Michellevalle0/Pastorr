/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.pastor.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.itson.pastor.entidades.Cuenta;
import mx.itson.pastor.entidades.Movimiento;
import mx.itson.pastor.enumeradores.TipoMovimiento;

/**
 *
 * @author Michelle
 */
public class MovimientoDAO {

    public static boolean agregar(Movimiento movimiento) {
        boolean resultado = false;

        try {
            Connection con = Conexion.obtener();

            String sql = "INSERT INTO `pastorbd`.`movimiento` (`concepto`, `fecha`, `importe`, `tipo`, `cuenta`) VALUES (?,?,?,?,?) ";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, movimiento.getDescripcion());
            st.setString(2, String.valueOf(movimiento.getFecha()));

            switch (movimiento.getTipo()) {
                case ABONO:
                    st.setInt(4, 1);
                    st.setDouble(3, -movimiento.getImporte());
                    break;
                case CARGO:
                    st.setInt(4, 2);
                    st.setDouble(3, movimiento.getImporte());
                    break;

            }

            st.setInt(5, movimiento.getCuenta().getId());
            resultado = st.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

        return resultado;

    }

    public static List<Movimiento> listadoMovimiento(String numero) {
        List<Movimiento> listado = new ArrayList();

        try {
            Connection con = Conexion.obtener();
            String sql = "SELECT  distinct concepto,fecha,importe,tipo,numero FROM pastorbd.movimiento INNER JOIN cuenta ON movimiento.cuenta=cuenta.id WHERE numero=? order by fecha asc;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, numero);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Movimiento m = new Movimiento();
                m.setDescripcion(rs.getString("concepto"));
                m.setFecha(Date.valueOf(rs.getString("fecha")));

                m.setImporte(rs.getDouble("importe"));

                if (rs.getInt("tipo") == 1) {
                    m.setTipo(m.getTipo().ABONO);

                } else {
                    m.setTipo(m.getTipo().CARGO);

                }
                Cuenta c = new Cuenta();
                c.setNumero(rs.getString("numero"));
                m.setCuenta(c);
                listado.add(m);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return listado;
    }


}
