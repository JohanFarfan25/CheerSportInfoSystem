/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.PlanDeEntrenamiento;
import com.club.model.Resultados1;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johan.farfan
 */
@Stateless
public class Resultados1Facade extends AbstractFacade<Resultados1> implements Resultados1FacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Resultados1Facade() {
        super(Resultados1.class);
    }

    @Override
    public void resultadosFinal(Integer plan, Integer resultado1, Integer resultado2, Integer resultado3,
        Integer resultado4) {
        Connection conn = null;
        try {
            conn = ConnectionMySQL.conectar("localhost", "Johan", "12345", "gargolasproject");
            conn.setAutoCommit(true);

            CallableStatement cStmt = conn.prepareCall("{call sp_insertarnotas(?, ?, ?, ?, ?)}");
            cStmt.setInt(1, plan);
            cStmt.setInt(2, resultado1);
            cStmt.setInt(3, resultado2);
            cStmt.setInt(4, resultado3);
            cStmt.setInt(5, resultado4);
            //    cStmt.registerOutParameter("inOutParam", Types.INTEGER);

            cStmt.execute();
            //final ResultSet rs = cStmt.getResultSet();

            //  int outputValue = cStmt.getInt("inOutParam");
            //  System.out.println("Parametro de salida incrementado=" + outputValue);
        } catch (Exception e) {
            
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Resultados1Facade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
