/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImportExport;

import com.club.model.Ejercicios;
import com.club.utils.ConexionMySQL;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author johan.farfan
 */
@Named(value = "fileUploadControl")
@SessionScoped
public class FileUploadControl implements Serializable {

    private List<Ejercicios> Ejer = new ArrayList<Ejercicios>();

    private String destination = "C:\\Users\\Johan.farfan\\Documents\\NetBeansProjects\\AppCheerGaes9\\web\\WEB-INF\\uploaded";
    private UploadedFile file;

    public FileUploadControl() {
    }

    public void TransferFile(String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(destination + fileName));
            int reader = 0;
            byte[] bytes = new byte[(int) getFile().getSize()];
            while ((reader = in.read(bytes)) != -1) {
                out.write(bytes, 0, reader);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wrong", "Error Transfer file..."));
        }
    }

    public void upload() {
        String extValidate;
        if (getFile() != null) {
            String ext = getFile().getFileName();
            if (ext != null) {
                extValidate = ext.substring(ext.indexOf(".") + 1);
            } else {
                extValidate = "null";

                if (extValidate.equals("csv")) {
                    try {
                        TransferFile(getFile().getFileName(), getFile().getInputstream());
                        insertarMySQL(Ejer);
                    } catch (IOException ex) {
                        Logger.getLogger(FileUploadControl.class.getName()).log(Level.SEVERE, null, ex);
                        FacesContext context = FacesContext.getCurrentInstance();
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Wrong", "Error Uploading file..."));
                    }
                    FacesContext context = FacesContext.getCurrentInstance();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getFile().getFileName(), "Created Correctly"));
                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Wrong_ext", "only extension .csv"));
                }
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Wrong", "Select File!"));
        }
    }

    public void insertarMySQL(List<Ejercicios> Ejer) {
        System.out.println("\nSE VAN A INSERTA: " + Ejer.size() + " REGISTROS\n");

        ConexionMySQL sql = new ConexionMySQL();
        java.sql.Connection con = sql.conectarMySQL();

        try {

            for (int i = 0; i < Ejer.size(); i++) {
                String query = "INSERT INTO ejercicios(nombreEjercicio, descripcion, categoriaEjercicio) VALUES(?,?,?)";
                PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
                ps.setString(1, Ejer.get(i).getNombreEjercicio());
                ps.setString(2, Ejer.get(i).getDescripcion());
                ps.setString(3, Ejer.get(i).getCategoriaEjercicio());

                ps.executeUpdate();
                ps.close();

                System.out.println("Se inserto el elemento: " + (i + 1) + "/" + Ejer.size());
            }

            con.close();
        } catch (SQLException e) {

        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Ejercicios> getEjer() {
        return Ejer;
    }

    public void setEjer(List<Ejercicios> Ejer) {
        this.Ejer = Ejer;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

}
