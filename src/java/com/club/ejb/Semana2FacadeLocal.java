/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.Semana2;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johan.farfan
 */
@Local
public interface Semana2FacadeLocal {

    void create(Semana2 semana2);

    void edit(Semana2 semana2);

    void remove(Semana2 semana2);

    Semana2 find(Object id);

    List<Semana2> findAll();

    List<Semana2> findRange(int[] range);

    int count();
    
}
