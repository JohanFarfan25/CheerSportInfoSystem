/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.ItemsEvaluacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johan.farfan
 */
@Local
public interface ItemsEvaluacionFacadeLocal {

    void create(ItemsEvaluacion itemsEvaluacion);

    void edit(ItemsEvaluacion itemsEvaluacion);

    void remove(ItemsEvaluacion itemsEvaluacion);

    ItemsEvaluacion find(Object id);

    List<ItemsEvaluacion> findAll();

    List<ItemsEvaluacion> findRange(int[] range);

    int count();
    
}
