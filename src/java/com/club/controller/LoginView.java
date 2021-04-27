/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.PersonaFacadeLocal;
import com.club.ejb.UserFacadeLocal;
import com.club.model.Persona;
import com.club.model.User;
import com.club.utils.AuthenticationUtils;
import com.club.utils.Email;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.club.utils.GeneratePassword;

/**
 *
 * @author johan.farfan
 */
@Named(value = "loginView")
@SessionScoped
public class LoginView implements Serializable {

    private static final long serialVersionUID = 3254181235309041386L;
    private static Logger log = Logger.getLogger(LoginView.class.getName());

    @EJB
    private PersonaFacadeLocal ejbFacade;
    private List<Persona> items = null;
    private Persona selected;

    @EJB
    private UserFacadeLocal userFacade;
    private String email;
    private String password;
    private User user;
    private List<User> itemsUser = null;

    private String correoRecuperar;

    private String CorreoMasivo;
    private String Sujeto = "";
    private String Mensaje = "";

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(email, password);
        } catch (ServletException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Alerta", "El usuario no existe en el sistema"));
            return "signin";
        }
        Principal principal = request.getUserPrincipal();
        this.user = userFacade.findUserById(principal.getName());
        log.info("Authentication done for user: " + principal.getName());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("User", user);

        if (request.isUserInRole("users")) {
            return "/user/privatepage?faces-redirect=true";

        } else if (request.isUserInRole("administrador")) {
            return "/administrador/privatepage?faces-redirect=true";

        } else if (request.isUserInRole("entrenador")) {
            return "/entrenador/privatepage?faces-redirect=true";

        } else {
            return "signin";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            this.user = null;
            request.logout();
            // clear the session
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "No se pudo cerrar la sesión del usuario!", e);
        }
        return "/signin?faces-redirect=true";
    }

    public void restaurarClave() {
        User usuRecuperar = new User();

        try {
            usuRecuperar = userFacade.findByEmail(correoRecuperar);
            if (usuRecuperar.getName() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Alerta", "El usuario no existe en el sistema"));
            } else {
                String pwd = GeneratePassword.generate();
                usuRecuperar.setPassword(AuthenticationUtils.encodeSHA256(pwd));
                userFacade.edit(usuRecuperar);
                Email.sendClaves(usuRecuperar.getEmail(),
                        usuRecuperar.getName(),
                        usuRecuperar.getEmail(),
                        pwd);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tu nueva clave", "fue enviada a correo electrónico"));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "El usuario no existe en el sistema"));
        }

    }

    public void correoMasivo() {

        itemsUser = userFacade.findAll();
        for (User lUsuario : itemsUser) {
            try {
                Email.send(lUsuario.getEmail(), Sujeto, Mensaje);

            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Alerta ", "El mensaje no se pudo enviar "));

            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El mensaje ", "Fue enviado a todos los usuarios"));

    }

    public void leer(User Seleccion) {

        user = Seleccion;
    }

    public User getAuthenticatedUser() {
        return user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger aLog) {
        log = aLog;
    }

    public List<Persona> getItems() {
        return items;
    }

    public void setItems(List<Persona> items) {
        this.items = items;
    }

    public Persona getSelected() {
        return selected;
    }

    public void setSelected(Persona selected) {
        this.selected = selected;
    }

    public List<User> getItemsUser() {
        return itemsUser;
    }

    public void setItemsUser(List<User> itemsUser) {
        this.itemsUser = itemsUser;
    }

    public String getCorreoRecuperar() {
        return correoRecuperar;
    }

    public void setCorreoRecuperar(String correoRecuperar) {
        this.correoRecuperar = correoRecuperar;
    }

    public String getSujeto() {
        return Sujeto;
    }

    public void setSujeto(String Sujeto) {
        this.Sujeto = Sujeto;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

}
