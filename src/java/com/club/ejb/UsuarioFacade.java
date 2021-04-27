/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import com.club.model.Usuario;
import com.club.model.Usuario_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author johan.farfan
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public List<Usuario> listarDeportistas() {

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);
        Root<Usuario> entidad = query.from(Usuario.class);
        query.select(entidad).where(qb.equal(entidad.get(Usuario_.tipo), "Deportista"));
        List<Usuario> result = em.createQuery(query).getResultList();
        return result;

    }

    @Override
    public List<Usuario> listarEntrenador() {

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);
        Root<Usuario> entidad = query.from(Usuario.class);
        query.select(entidad).where(qb.equal(entidad.get(Usuario_.tipo), "Entrenador"));
        List<Usuario> result = em.createQuery(query).getResultList();
        return result;

    }

    @Override
    public List<Usuario> listarAdministrador() {

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);
        Root<Usuario> entidad = query.from(Usuario.class);
        query.select(entidad).where(qb.equal(entidad.get(Usuario_.tipo), "Administrador"));
        List<Usuario> result = em.createQuery(query).getResultList();
        return result;

    }

}
