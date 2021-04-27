/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.PlanDeEntrenamiento;
import com.club.model.Resultados1;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johan.farfan
 */
@Local
public interface Resultados1FacadeLocal {

    void create(Resultados1 resultados1);

    void edit(Resultados1 resultados1);

    void remove(Resultados1 resultados1);

    Resultados1 find(Object id);

    List<Resultados1> findAll();

    List<Resultados1> findRange(int[] range);

    int count();

   void resultadosFinal(Integer plan, Integer resultado1, Integer resultado2, Integer resultado3,Integer resultado4);

  
}
