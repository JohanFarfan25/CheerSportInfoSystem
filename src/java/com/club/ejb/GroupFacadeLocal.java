/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.Group;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johan.farfan
 */
@Local
public interface GroupFacadeLocal {

    void create(Group group);

    void edit(Group group);

    void remove(Group group);

    Group find(Object id);

    List<Group> findAll();

    List<Group> findRange(int[] range);

    int count();

    public boolean actualizarEmail2(String emailAnterior, String emailNuevo2);
    
}
