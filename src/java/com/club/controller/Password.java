/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.UserFacadeLocal;
import com.club.model.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;

/**
 *
 * @author johan.farfan
 */
@Named(value = "password")
@SessionScoped
public class Password implements Serializable {

    @EJB
    private UserFacadeLocal userFacade;
    private User objUser;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String Roll;
    private String correoRecuperar = "";
    private ArrayList<User> listaUser = new ArrayList<>();

    public Password() {
    }

    public User getObjUser() {
        return objUser;
    }

    public void setObjUser(User objUser) {
        this.objUser = objUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRoll() {
        return Roll;
    }

    public void setRoll(String Roll) {
        this.Roll = Roll;
    }

    public String getCorreoRecuperar() {
        return correoRecuperar;
    }

    public void setCorreoRecuperar(String correoRecuperar) {
        this.correoRecuperar = correoRecuperar;
    }

    public ArrayList<User> getListaUser() {
        return listaUser;
    }

    public void setListaUser(ArrayList<User> listaUser) {
        this.listaUser = listaUser;
    }
    
    
    

   

}
