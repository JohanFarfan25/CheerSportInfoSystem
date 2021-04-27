/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.Semana3;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johan.farfan
 */
@Local
public interface Semana3FacadeLocal {

    void create(Semana3 semana3);

    void edit(Semana3 semana3);

    void remove(Semana3 semana3);

    Semana3 find(Object id);

    List<Semana3> findAll();

    List<Semana3> findRange(int[] range);

    int count();
    
}
