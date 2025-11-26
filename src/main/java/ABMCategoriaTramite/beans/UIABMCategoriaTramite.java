/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCategoriaTramite.beans;

import ABMCategoriaTramite.ControladorABMCategoriaTramite;
import ABMCategoriaTramite.dtos.DTOCategoriaTramite;
import ABMCategoriaTramite.dtos.DTOModificarCategoriaTramite;
import ABMCategoriaTramite.dtos.DTOModificarCategoriaTramiteIn;
import ABMCategoriaTramite.dtos.DTONuevaCategoriaTramite;
import ABMCategoriaTramite.exceptions.CategoriaTramiteException;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

/**
 *
 * @author vickyvelasco
 */
@Named("uiabmCategoriaTramite")  
@ViewScoped 
public class UIABMCategoriaTramite implements Serializable {
   private ControladorABMCategoriaTramite controladorABMCategoriaTramite = new ControladorABMCategoriaTramite();
   private boolean insert;
   private int codCategoriaTramite;
   private String nombreCategoriaTramite;

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public int getCodCategoriaTramite() {
        return codCategoriaTramite;
    }

    public void setCodCategoriaTramite(int codCategoriaTramite) {
        this.codCategoriaTramite = codCategoriaTramite;
    }

    public String getNombreCategoriaTramite() {
        return nombreCategoriaTramite;
    }

    public void setNombreCategoriaTramite(String nombreCategoriaTramite) {
        this.nombreCategoriaTramite = nombreCategoriaTramite;
    }
   
   public UIABMCategoriaTramite() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        int codigo = Integer.parseInt(request.getParameter("codCategoriaTramite"));
        insert = true;
        if (codigo > 0) {
            insert = false;
            DTOModificarCategoriaTramite dtoModificarCategoria = controladorABMCategoriaTramite.buscarCategoriaAModificar(codigo);
            setNombreCategoriaTramite(dtoModificarCategoria.getNombreCategoriaTramite());
            setCodCategoriaTramite(dtoModificarCategoria.getCodCategoriaTramite());
        }
    }

    public String agregarCategoria() {
        try {
            if (!insert) {
                DTOModificarCategoriaTramiteIn dtoModificarCategoriaIn = new DTOModificarCategoriaTramiteIn();
                dtoModificarCategoriaIn.setCodCategoriaTramite(getCodCategoriaTramite());
                dtoModificarCategoriaIn.setNombreCategoriaTramite(getNombreCategoriaTramite());
                controladorABMCategoriaTramite.modificarCategoria(dtoModificarCategoriaIn);
                return BeansUtils.redirectToPreviousPage();
            } else {
                DTONuevaCategoriaTramite dtoNuevaCategoriaTramite = new DTONuevaCategoriaTramite();
                dtoNuevaCategoriaTramite.setCodCategoriaTramite(getCodCategoriaTramite());
                dtoNuevaCategoriaTramite.setNombreCategoriaTramite(getNombreCategoriaTramite());
                controladorABMCategoriaTramite.agregarCategoria(dtoNuevaCategoriaTramite);
            }
            return BeansUtils.redirectToPreviousPage();
        } catch (CategoriaTramiteException e) {
            Messages.create(e.getMessage()).fatal().add();
            return "";
        }
    }

    
}
