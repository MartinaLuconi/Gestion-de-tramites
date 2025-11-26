/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMEstadoTramite.beans;

import ABMEstadoTramite.ControladorABMEstadoTramite;
import ABMEstadoTramite.dtos.DTOModificarEstadoTramite;
import ABMEstadoTramite.dtos.DTOModificarEstadoTramiteIn;
import ABMEstadoTramite.dtos.DTONuevoEstadoTramite;
import ABMEstadoTramite.exceptions.EstadoTramiteException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ExternalContext;
import jakarta.servlet.http.HttpServletRequest;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

/**
 *
 * @author vickyvelasco
 */

@Named("uiabmEstadoTramite")  
@ViewScoped 
public class UIABMEstadoTramite implements Serializable{

    private ControladorABMEstadoTramite controladorABMEstadoTramite = new ControladorABMEstadoTramite();
    private boolean insert;
    private int codEstadoTramite;
    private String nombreEstadoTramite;
    private boolean activo; // true if active, false if disabled
    private boolean enUso; // true if used by a version

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }

    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }

    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isEnUso() {
        return enUso;
    }

    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    public UIABMEstadoTramite() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        int codigo = Integer.parseInt(request.getParameter("codEstadoTramite"));
        insert = true;
        if (codigo > 0) {
            insert = false;
            DTOModificarEstadoTramite dtoModificarEstado = controladorABMEstadoTramite.buscarEstadoAModificar(codigo);
            setNombreEstadoTramite(dtoModificarEstado.getNombreEstadoTramite());
            setCodEstadoTramite(dtoModificarEstado.getCodEstadoTramite());
           
        }
    }

    public String agregarEstado() {
        try {
            if (!insert) {
                DTOModificarEstadoTramiteIn dtoModificarEstadoTramiteIn = new DTOModificarEstadoTramiteIn();
                dtoModificarEstadoTramiteIn.setCodEstadoTramite(getCodEstadoTramite());
                dtoModificarEstadoTramiteIn.setNombreEstadoTramite(getNombreEstadoTramite());
                controladorABMEstadoTramite.modificarEstado(dtoModificarEstadoTramiteIn);
                return BeansUtils.redirectToPreviousPage();
            } else {
                DTONuevoEstadoTramite dtoNuevaEstadoTramite = new DTONuevoEstadoTramite();
                dtoNuevaEstadoTramite.setCodEstadoTramite(getCodEstadoTramite());
                dtoNuevaEstadoTramite.setNombreEstadoTramite(getNombreEstadoTramite());
                controladorABMEstadoTramite.agregarEstado(dtoNuevaEstadoTramite);
            }
            return BeansUtils.redirectToPreviousPage();
        } catch (EstadoTramiteException e) {
            Messages.create(e.getMessage()).fatal().add();
            return "";
        }
    }
}
