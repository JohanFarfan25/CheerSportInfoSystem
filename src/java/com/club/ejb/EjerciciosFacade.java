/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.Ejercicios;
import com.club.utils.ConexionMySQL;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author johan.farfan
 */
@Stateless
public class EjerciciosFacade extends AbstractFacade<Ejercicios> implements EjerciciosFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EjerciciosFacade() {
        super(Ejercicios.class);
    }

    @Override
    public void insertarMySQL(List<Ejercicios> Ejer) {
        System.out.println("\nSE VAN A INSERTA: " + Ejer.size() + " REGISTROS\n");

        ConexionMySQL sql = new ConexionMySQL();
        java.sql.Connection con = sql.conectarMySQL();

        try {

            for (int i = 0; i < Ejer.size(); i++) {
                String query = "INSERT INTO ejercicios(nombreEjercicio, descripcion, categoriaEjercicio) VALUES(?,?,?)";
                PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
                ps.setString(1, Ejer.get(i).getNombreEjercicio());
                ps.setString(2, Ejer.get(i).getDescripcion());
                ps.setString(3, Ejer.get(i).getCategoriaEjercicio());

                ps.executeUpdate();
                ps.close();

                System.out.println("Se inserto el elemento: " + (i + 1) + "/" + Ejer.size());
            }

            con.close();
        } catch (SQLException e) {

        }
    }

}
