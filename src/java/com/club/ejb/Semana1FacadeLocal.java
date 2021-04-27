/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.Semana1;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johan.farfan
 */
@Local
public interface Semana1FacadeLocal {

    void create(Semana1 semana1);

    void edit(Semana1 semana1);

    void remove(Semana1 semana1);

    Semana1 find(Object id);

    List<Semana1> findAll();

    List<Semana1> findRange(int[] range);

    int count();
    
}
