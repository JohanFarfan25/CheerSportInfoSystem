/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImportExport;

import com.club.ejb.EjerciciosFacadeLocal;
import com.club.model.Ejercicios;
import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author johan.farfan
 */
@Named(value = "cSVaMySQL")
@RequestScoped
public class CSVaMySQL implements Serializable{

    private String destination = "C:\\uploaded\\";
    private UploadedFile file;

    @EJB
    EjerciciosFacadeLocal ejbEjer;
    private List<Ejercicios> Ejer = new ArrayList<Ejercicios>();

    //* Subir el archivo*//
    
    
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
            System.out.println(e.getMessage());
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
                        insertarEcxel();
                    } catch (IOException ex) {
                        Logger.getLogger(CSVaMySQL.class.getName()).log(Level.SEVERE, null, ex);
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage(null, new FacesMessage("Wrong", "Error Uploading file..."));
                    }
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Succesful", getFile().getFileName() + "is uploaded."));
                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage("Wrong_ext", "only extension .csv"));
                }
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Wrong", "Select File!"));
        }
    }

    
    
    public static List<Ejercicios> importarCSV() {
        List<Ejercicios> Ejer = new ArrayList<Ejercicios>();

        try {
            CsvReader leerEjercicios = new CsvReader("Ejercicios.csv");
            leerEjercicios.readHeaders();

            while (leerEjercicios.readRecord()) {
                String nombreEjercicio = leerEjercicios.get(1);
                String descripcion = leerEjercicios.get(2);
                String categoriaEjercicio = leerEjercicios.get(3);

                Ejercicios.add(new Ejercicios(nombreEjercicio, descripcion, categoriaEjercicio));
            }

            leerEjercicios.close();

            System.out.println("LISTA DE EJERCICIOS DEL CSV\n");
            for (Ejercicios user : Ejer) {
                System.out.println(
                        user.getIdEjercicios() + ", "
                        + user.getNombreEjercicio() + ", "
                        + user.getDescripcion() + ", "
                        + user.getCategoriaEjercicio()
                );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Ejer;

    }

        //* Insertar  el archivo en la BDD*//
    public void insertarEcxel() {

        try {

            ejbEjer.insertarMySQL(Ejer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡The File!", " Was Inserted Correctly"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡The File!", " Could not insert"));
        }
    }

    public List<Ejercicios> getEjer() {
        return Ejer;
    }

    public void setEjer(List<Ejercicios> Ejer) {
        this.Ejer = Ejer;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
