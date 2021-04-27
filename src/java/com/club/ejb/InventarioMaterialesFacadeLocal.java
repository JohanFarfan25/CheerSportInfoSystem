/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.InventarioMateriales;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author johan.farfan
 */
@Local
public interface InventarioMaterialesFacadeLocal {

    void create(InventarioMateriales inventarioMateriales);

    void edit(InventarioMateriales inventarioMateriales);

    void remove(InventarioMateriales inventarioMateriales);

    InventarioMateriales find(Object id);

    List<InventarioMateriales> findAll();

    List<InventarioMateriales> findRange(int[] range);

    int count();

    public int contarPorCategoria(int fk_categoria);

    public List<InventarioMateriales> listaPorCategoria(int fk_categoria);

}
