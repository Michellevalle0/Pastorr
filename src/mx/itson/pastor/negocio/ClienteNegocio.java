/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.pastor.negocio;

import mx.itson.pastor.entidades.Cliente;
import mx.itson.pastor.persistencia.ClienteDAO;
import mx.itson.pastor.persistencia.CuentaDAO;

/**
 *
 * @author Michelle
 */
public class ClienteNegocio {

    public static boolean guardar(Cliente c) {
        boolean resultado = true;
        try {
            if (ClienteDAO.obtenerPorEmail(c.getEmail()) == false) {
                resultado = ClienteDAO.guardar(c);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;

    }

}
