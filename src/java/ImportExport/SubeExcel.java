/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImportExport;

import com.club.ejb.EjerciciosFacadeLocal;
import com.club.model.Ejercicios;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author johan.farfan
 */
@Named(value = "subeExcel")
@SessionScoped
public class SubeExcel implements Serializable {

    @EJB
    EjerciciosFacadeLocal objEjercicios;

    private Part archivoExcel;

    public SubeExcel() {
    }

    public void insertarXLS(List cellDataList) {
        try {
            int filasContador = 0;
            for (int i = 0; i < cellDataList.size(); i++) {
                List cellTemp = (List) cellDataList.get(i);
                Ejercicios newP = new Ejercicios();
                for (int j = 0; j < cellTemp.size(); j++) {
                    XSSFCell hssfCell = (XSSFCell) cellTemp.get(j);
                    switch (filasContador) {
                        case 0:
                            newP.setNombreEjercicio(hssfCell.toString());
                            filasContador++;
                            break;
                        case 1:
                            newP.setDescripcion(hssfCell.toString());
                            filasContador++;
                            break;
                        case 2:
                            newP.setCategoriaEjercicio(hssfCell.toString());
                            filasContador = 0;
                            objEjercicios.create(newP);
                            break;

                    }

                }
            }

        } catch (Exception e) {

        }
    }

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {

        if (file != null) {
            if (file.getSize() < 4000000) {
                if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType())) {
                    InputStream input = null;
                    try {
                        input = file.getInputstream();
                        List cellData = new ArrayList();
                        try {
                            XSSFWorkbook workBook = new XSSFWorkbook(input);
                            XSSFSheet hssfSheet = workBook.getSheetAt(0);
                            Iterator rowIterator = hssfSheet.rowIterator();
                            rowIterator.next();

                            while (rowIterator.hasNext()) {
                                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                                Iterator iterator = hssfRow.cellIterator();
                                List cellTemp = new ArrayList();
                                while (iterator.hasNext()) {
                                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                                    cellTemp.add(hssfCell);
                                }
                                cellData.add(cellTemp);
                            }
                            insertarXLS(cellData);
                            

                        } catch (Exception e) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Warm!", "The Exerssice is assigned in a training plan"));
                        }
                        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                        try {
                            
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡The file!", "Got up correctly"));
                            
                        } catch (Exception ex) {
                            Logger.getLogger(SubeExcel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(SubeExcel.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            input.close();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(SubeExcel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Warm!", "The file ,  !must be XLSX format¡"));
                }
            } else {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Warm!", "The file' , ! it is very big¡ "));
            }
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Can't Upload!", "The file"));
        }

        

    }

    public void handleFileUpload(FileUploadEvent event) {
        //System.out.println("DesintationPDF : " + destinationPDF);
        System.out.println("called handle file");
        //   System.out.println("Destination is : " + destination);

        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded."); //Displays to user on the webpage using AJAX
        FacesContext.getCurrentInstance().addMessage(null, msg);

        try {
            //   System.out.println("Upload complete value before copy file " + uploadComplete);
            //  copyFile(event.getFile().getFileName(), event.getFile().getInputstream());

        } catch (Exception e) {
//handle the exception
            e.printStackTrace();
        }

    }

    public Part getArchivoExcel() {
        return archivoExcel;
    }

    public void setArchivoExcel(Part archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

}
