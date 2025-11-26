/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMEstadoTramite.beans;

import ABMEstadoTramite.ControladorABMEstadoTramite;
import ABMEstadoTramite.dtos.DTOEstadoTramite;
import ABMEstadoTramite.exceptions.EstadoTramiteException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

/**
 *
 * @author vickyvelasco
 */

@Named("uiabmEstadoTramiteLista")
@ViewScoped
public class UIABMEstadoTramiteLista implements Serializable{
    private ControladorABMEstadoTramite controladorABMEstadoTramite = new ControladorABMEstadoTramite();
    private int codEstadoTramiteFiltro = 0;
    private String nombreEstadoTramiteFiltro = "";

    public int getCodEstadoTramiteFiltro() {
        return codEstadoTramiteFiltro;
    }

    public void setCodEstadoTramiteFiltro(int codEstadoTramite) {
        this.codEstadoTramiteFiltro = codEstadoTramite;
    }

    public String getNombreEstadoTramiteFiltro() {
        return nombreEstadoTramiteFiltro;
    }

    public void setNombreEstadoTramiteFiltro(String nombreEstadoTramite) {
        this.nombreEstadoTramiteFiltro = nombreEstadoTramite;
    }
    
    public void filtrar() {
        
    }
    
    public List<UIABMEstadoTramiteGrilla> buscarEstados() {
        List<UIABMEstadoTramiteGrilla> estadosGrilla = new ArrayList<>();
        List<DTOEstadoTramite> dtoEstados = controladorABMEstadoTramite.buscarEstados(codEstadoTramiteFiltro, nombreEstadoTramiteFiltro);

        if (dtoEstados != null) {
            for (DTOEstadoTramite estadoDTO : dtoEstados) {
                UIABMEstadoTramiteGrilla uiEstadoTramiteGrilla = new UIABMEstadoTramiteGrilla();
                uiEstadoTramiteGrilla.setCodEstadoTramite(estadoDTO.getCodEstadoTramite());
                uiEstadoTramiteGrilla.setNombreEstadoTramite(estadoDTO.getNombreEstadoTramite());
                uiEstadoTramiteGrilla.setFechaHoraBajaEstadoTramite(estadoDTO.getFechaHoraBajaEstadoTramite());
                estadosGrilla.add(uiEstadoTramiteGrilla);
            }
        }

        return estadosGrilla;
    }

    // Redirigir a la página para agregar un nuevo estado
    public String irAgregarEstado() {
        BeansUtils.guardarUrlAnterior();
        return "abmEstadoTramite.xhtml?faces-redirect=true&codEstadoTramite=0";
    }

    // Redirigir a la página para modificar un estado existente
    public String irModificarEstado(int codEstadoTramite) {
        BeansUtils.guardarUrlAnterior();
        return "abmEstadoTramite?faces-redirect=true&codEstadoTramite=" + codEstadoTramite;
    }

    // Método para dar de baja un estado
    public void darDeBajaEstado(int codEstadoTramite) {
        try {
            controladorABMEstadoTramite.darDeBajaEstadoTramite(codEstadoTramite);
            Messages.create("Estado anulado").detail("Anulado").add();
        } catch (EstadoTramiteException e) {
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }

    
}
