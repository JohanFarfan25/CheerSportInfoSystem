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
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.Part;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author johan.farfan
 */
@Named(value = "cargarEcxel")
@SessionScoped
public class CargarEcxel implements Serializable {

    @EJB
    EjerciciosFacadeLocal ejerciciosFacadeLocal;
   

    private Ejercicios objEjercicios = new Ejercicios();
    private ArrayList<Ejercicios> listaCategorias = new ArrayList<>();

    private Ejercicios objEjerciciosNew = new Ejercicios();
    private int id_cat_selec = 0;
    private Part archivoImagen;
    private Part archivoExcel;
    private String nombreArchivo;


    public CargarEcxel() {
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
                            filasContador++;
                            break;                 
                    }

                }
            }

        } catch (Exception e) {
            
        }
    }

    public void subeExcel() throws IOException, MessagingException {
        
           
        if (archivoExcel != null) {
            if (archivoExcel.getSize() < 4000000) {
                if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(archivoExcel.getContentType())) {
                    InputStream input = archivoExcel.getInputStream();
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
                    context.redirect("index.xhtml");
                } else {
                    
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Warm!", "El archivo' , ' no es una XLSX"));
                }
            } else {
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Warm!", "El archivo' , ' es muy grande "));
            }
        } else {
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Warm!", "No puede cargar ' , ' EL  archivo "));
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Warm!", "No puede cargar ' , ' EL  archivo"));
    }

    public Ejercicios getObjEjercicios() {
        return objEjercicios;
    }

    public void setObjEjercicios(Ejercicios objEjercicios) {
        this.objEjercicios = objEjercicios;
    }

    public ArrayList<Ejercicios> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(ArrayList<Ejercicios> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public Ejercicios getObjEjerciciosNew() {
        return objEjerciciosNew;
    }

    public void setObjEjerciciosNew(Ejercicios objEjerciciosNew) {
        this.objEjerciciosNew = objEjerciciosNew;
    }

    public int getId_cat_selec() {
        return id_cat_selec;
    }

    public void setId_cat_selec(int id_cat_selec) {
        this.id_cat_selec = id_cat_selec;
    }

    public Part getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(Part archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public Part getArchivoExcel() {
        return archivoExcel;
    }

    public void setArchivoExcel(Part archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    

}
