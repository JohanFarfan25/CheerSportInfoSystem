/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.club.model.Group;
import com.club.model.User;
import com.club.utils.AuthenticationUtils;
import javax.persistence.Query;

/**
 *
 * @author johan.farfan
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User createUser(User user, String roll) {
        try {
            user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        Group group = new Group();
        group.setEmail(user.getEmail());
        group.setGroupname(roll);
        em.persist(user);
        em.persist(group);

        return user;
    }

    public void desencripContraseñaPassword(User user) {
        try {
            user.setPassword(AuthenticationUtils.decryptSHA256(user.getPassword()));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    ;
    
    
    
    

    @Override
    public User findUserById(String id) {
        TypedQuery<User> query = em.createNamedQuery("findUserById", User.class);
        query.setParameter("email", id);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        try {
            TypedQuery<User> qt = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            qt.setParameter("email", email);
            return qt.getSingleResult();
        } catch (Exception e) {
            return new User();
        }
    }

    @Override
    public User login(String email, String clave) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :clv");
            qt.setParameter("email", email);
            qt.setParameter("clv", AuthenticationUtils.encodeSHA256(clave));
            return (User) qt.getSingleResult();
        } catch (Exception e) {
            return new User();
        }
    }

    public User editarContraseña(String password) {
        try {
            Query qt = em.createQuery("SELECT u FROM User u WHERE u.password = :password");
            qt.setParameter("password", password);
            return (User) qt.getSingleResult();
        } catch (Exception e) {
            return new User();
        }
    }

    
    @Override
    public boolean actualizarEmail(String emailAnterior, String emailNuevo) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createNativeQuery("UPDATE `users` SET `email` = ? WHERE `users`.`email` = ?");
            qt.setParameter(1, emailNuevo);
            qt.setParameter(2, emailAnterior);
            qt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
