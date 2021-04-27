/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.club.controller;

import com.club.model.Persona;
import com.club.model.Usuario;
import com.club.ejb.PersonaFacadeLocal;
import com.club.ejb.UserFacadeLocal;
import com.club.ejb.UsuarioFacadeLocal;
import com.club.model.User;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author johan.farfan
 */
@Named(value = "administradorView")
@ViewScoped
public class AdministradorView implements Serializable {

    @EJB
    UserFacadeLocal UserFacadeLocal;
    private ArrayList<User> listaUser = new ArrayList<>();
    private User userReg = new User();

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    private List<Usuario> lista;

    @EJB
    PersonaFacadeLocal PersonaFacadeLocal;
    private ArrayList<Persona> listaPersona = new ArrayList<>();
    private Persona usReg = new Persona();

    @Inject
    LoginView loginView;

    /**
     * Creates a new instance of AdministradorView
     */
    @PostConstruct
    public void leerListaUsuarios() {

        usuario = new Usuario();
        usReg = new Persona();

        lista = usuarioEJB.findAll();
        listaPersona.addAll(PersonaFacadeLocal.findAll());
    }



    public void cargaUsuarioEditar(Persona usuEditar) {
        this.usReg = usuEditar;
    }

    public void editarUsuario() {

        try {
            PersonaFacadeLocal.edit(usReg);
            listaPersona.clear();
            listaPersona.addAll(PersonaFacadeLocal.findAll());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Usuario!", "Editado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Usuario No se pudo editar"));
        }

    }

    public void editarMiPerfil(Persona persoRem) {

        try {

            PersonaFacadeLocal.edit(persoRem);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Usuario!", "Editado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Alerta!", "Usuario No se pudo editar"));
        }

    }

    @SuppressWarnings("unchecked")
    public void descargaReporte(String nombreReporte) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("personaReporte", loginView.getAuthenticatedUser().getName());
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gargolasproject", "Johan", "12345");
            System.out.println("Catalogo : " + conec.getCatalog());

            File jasper = new File(context.getRealPath("/WEB-INF/reportes/" + nombreReporte + ".jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=" + nombreReporte + ".pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }

    }

    public void descargaCertificado(String codigo) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("codigo", codigo);
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gargolasproject", "Johan", "12345");
            System.out.println("Catalogo : " + conec.getCatalog());

            File jasper = new File(context.getRealPath("/WEB-INF/reportes/certificados/certificado.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=Certificado.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }

    }

    public void descargaCertificadoAdmin(String codigo) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("codigo", codigo);
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gargolasproject", "Johan", "12345");
            System.out.println("Catalogo : " + conec.getCatalog());

            File jasper = new File(context.getRealPath("/WEB-INF/reportes/Certificados/certificadoAdmin.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=certificadoAdmin.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }

    }

    public void descargaCertificadoEntre(String codigo) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");

        try {
            Map parametro = new HashMap();
            parametro.put("codigo", codigo);
            Connection conec = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/gargolasproject", "Johan", "12345");
            System.out.println("Catalogo : " + conec.getCatalog());

            File jasper = new File(context.getRealPath("/WEB-INF/reportes/Certificados/certificadoEntre.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), parametro, conec);

            HttpServletResponse hsr = (HttpServletResponse) context.getResponse();
            hsr.addHeader("Content-disposition", "attachment; filename=certificadoEntre.pdf");
            OutputStream os = hsr.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jp, os);
            os.flush();
            os.close();
            facesContext.responseComplete();

        } catch (JRException e) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + e.getMessage());
        } catch (IOException i) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + i.getMessage());
        } catch (SQLException q) {
            System.out.println("com.club.controlador.AdministradorView.descargaReporte() " + q.getMessage());
        }

    }

    public AdministradorView() {
    }

    public ArrayList<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(ArrayList<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public Persona getUsReg() {
        return usReg;
    }

    public void setUsReg(Persona usReg) {
        this.usReg = usReg;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

}
