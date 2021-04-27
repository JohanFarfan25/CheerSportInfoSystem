/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.ejb.GroupFacadeLocal;
import com.club.ejb.PersonaFacadeLocal;
import com.club.ejb.UserFacadeLocal;
import com.club.ejb.UsuarioFacadeLocal;
import com.club.model.Group;
import com.club.model.Persona;
import com.club.model.User;
import com.club.model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;

/**
 *
 * @author johan.farfan
 */
@Named(value = "registerView")
@SessionScoped
public class RegisterView implements Serializable {

    private static Logger log = Logger.getLogger(RegisterView.class.getName());

    @Inject
    LoginView loginvew;
    
    @EJB
    PersonaFacadeLocal personaEJB;
    private Persona objPersona = new Persona();
    private ArrayList<Persona> listaPersona = new ArrayList<>();

    @EJB
    UsuarioFacadeLocal usuarioEJB;
    private Usuario objUsuario = new Usuario();
    private ArrayList<Usuario> listaUsuario = new ArrayList<>();

    @EJB
    GroupFacadeLocal groupEJB;
    private Group objGroup = new Group();
    private ArrayList<Group> listaGroup = new ArrayList<>();

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
    
    private String emailNuevo;
    private String emailNuevo2;
    private int codigo;
    
    public RegisterView() {

    }

    @PostConstruct
    public void init() {

        objUsuario = new Usuario();
        objPersona = new Persona();
        objUser = new User();
        objGroup = new Group();

        listaUsuario.addAll(usuarioEJB.findAll());
        listaPersona.addAll(personaEJB.findAll());
        listaUser.addAll(userFacade.findAll());
        listaGroup.addAll(groupEJB.findAll());

    }

    
    
    public void validatePassword(ComponentSystemEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        // get password
        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();
        // get confirm password
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmpassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();
        // Let required="true" do its job.
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }
        if (!password.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage("Confirm password does not match password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(passwordId, msg);
            facesContext.renderResponse();
        }
        if (userFacade.findUserById(email) != null) {
            FacesMessage msg = new FacesMessage("User with this e-mail already exists");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(passwordId, msg);
            facesContext.renderResponse();
        }
    }

    
    
    public void register() {
        try {

            User user = new User(email, password, name);
            userFacade.createUser(user, Roll);

            this.objUsuario.setCodigo(objPersona);
            usuarioEJB.create(objUsuario);
            listaUsuario.add(objUsuario);

            log.info("New user created with e-mail: " + email + " and name: " + name);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, this.getName() + " " + this.getEmail(), "Creado Correctamente"));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, this.getEmail(), "Ya esta en el sistema"));
        }
        objPersona = new Persona();
        objUsuario = new Usuario();
        objUser = new User();

    }
    
    
        public void modificar() {
        
        try {
             usuarioEJB.edit(objUsuario);
      
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Usuario!", "  Editado Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "No se pudo editar"));
        }
        
    }

    
    
    public void cargaUsPersonaEditar(Persona usuEditar) {
        this.objPersona = usuEditar;
    }

    
    
    public void cargarFormulario(Persona OjbCargar) {

        objPersona = OjbCargar;

    }

    
    public void cargarUser(User user) {

        objUser = user;

    }
    
    
    
    public void editarUser(){
    
         try {
           
            groupEJB.actualizarEmail2(loginvew.getAuthenticatedUser().getEmail(), emailNuevo2);
            userFacade.edit(objUser);
            userFacade.actualizarEmail(loginvew.getAuthenticatedUser().getEmail(), emailNuevo);
            
            

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Correo editado correctamente!", "¡Recuerde que debe cerrar sessión!"));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "No se pudo editar"));
        }
        objUser = new User();
    
    }
    
    
        public void editarGroup(Group group){
    
         try {
                       
            

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Grupo!", "de Autenticación editado correctamente"));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "No se pudo editar"));
        }
        objGroup = new Group();
    
    }

    
    
    public void removerUsuario(Usuario usu) {

        try {

            usuarioEJB.remove(usu); 

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Usuario!", "Eliminado correctamente ¡Vaya al paso 2!"));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡El Usuario!", "Tiene un plan de entrenamiento asignado, primero debe eliminar el plan de entrenamiento"));
        }
        objUsuario = new Usuario();

    }

    public void removerPersona( Persona perso) {

        try {

            
            personaEJB.remove(perso);    

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Persona!", "Eliminada correctamente ¡Vaya al paso 3!"));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Debe seguir el orden de eliminación paso 1> paso 2> paso 3> paso 4"));
        }
        objPersona = new Persona();
    }

    
    
    public void removerUsers(User user) {

        try {

            userFacade.remove(user);           

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Nombre de Autenticación!", " Eliminada correctamente ¡Vaya al paso 4!"));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Debe seguir el orden de eliminación paso 1> paso 2> paso 3> paso 4"));
        }
        
        objUser = new User();
    }
    
        public void removerGroup(Group group) {

        try {

            groupEJB.remove(group);
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Finalizado!", "Eliminación completa"));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Debe seguir el orden de eliminación paso 1> paso 2> paso 3> paso 4"));
        }
        
        objGroup = new Group();
    }

    
    
    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger aLog) {
        log = aLog;
    }

    public String getName() {
        //   name="asdasdasdasd";
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

    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
        this.objPersona = objPersona;
    }

    public ArrayList<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(ArrayList<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public ArrayList<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getCorreoRecuperar() {
        return correoRecuperar;
    }

    public void setCorreoRecuperar(String correoRecuperar) {
        this.correoRecuperar = correoRecuperar;
    }

    public User getObjUser() {
        return objUser;
    }

    public void setObjUser(User objUser) {
        this.objUser = objUser;
    }

    public ArrayList<User> getListaUser() {
        return listaUser;
    }

    public void setListaUser(ArrayList<User> listaUser) {
        this.listaUser = listaUser;
    }

    public Group getObjGroup() {
        return objGroup;
    }

    public void setObjGroup(Group objGroup) {
        this.objGroup = objGroup;
    }

    public ArrayList<Group> getListaGroup() {
        return listaGroup;
    }

    public void setListaGroup(ArrayList<Group> listaGroup) {
        this.listaGroup = listaGroup;
    }

    public String getEmailNuevo() {
        return emailNuevo;
    }

    public void setEmailNuevo(String emailNuevo) {
        this.emailNuevo = emailNuevo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEmailNuevo2() {
        return emailNuevo2;
    }

    public void setEmailNuevo2(String emailNuevo2) {
        this.emailNuevo2 = emailNuevo2;
    }

    
}
