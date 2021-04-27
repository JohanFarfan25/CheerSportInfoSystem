/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.Semana4;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johan.farfan
 */
@Local
public interface Semana4FacadeLocal {

    void create(Semana4 semana4);

    void edit(Semana4 semana4);

    void remove(Semana4 semana4);

    Semana4 find(Object id);

    List<Semana4> findAll();

    List<Semana4> findRange(int[] range);

    int count();
    
}
