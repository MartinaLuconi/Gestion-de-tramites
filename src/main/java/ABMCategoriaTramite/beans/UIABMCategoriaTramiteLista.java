/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCategoriaTramite.beans;

import ABMCategoriaTramite.ControladorABMCategoriaTramite;
import ABMCategoriaTramite.dtos.DTOCategoriaTramite;
import ABMCategoriaTramite.exceptions.CategoriaTramiteException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

/**
 *
 * @author vickyvelasco
 */

@Named("uiabmCategoriaTramiteLista")  // Este nombre debe coincidir con el que usas en el archivo XHTML
@ViewScoped
public class UIABMCategoriaTramiteLista implements Serializable {
    private ControladorABMCategoriaTramite controladorABMCategoriaTramite = new ControladorABMCategoriaTramite();
    private int codCategoriaTramiteFiltro = 0;
    private String nombreCategoriaTramiteFiltro = "";

    public ControladorABMCategoriaTramite getControladorABMCategoriaTramite() {
        return controladorABMCategoriaTramite;
    }

    public void setControladorABMCategoriaTramite(ControladorABMCategoriaTramite controladorABMCategoriaTramite) {
        this.controladorABMCategoriaTramite = controladorABMCategoriaTramite;
    }

    public int getCodCategoriaTramiteFiltro() {
        return codCategoriaTramiteFiltro;
    }

    public void setCodCategoriaTramiteFiltro(int codCategoriaTramiteFiltro) {
        this.codCategoriaTramiteFiltro = codCategoriaTramiteFiltro;
    }

    public String getNombreCategoriaTramiteFiltro() {
        return nombreCategoriaTramiteFiltro;
    }

    public void setNombreCategoriaTramiteFiltro(String nombreCategoriaTramiteFiltro) {
        this.nombreCategoriaTramiteFiltro = nombreCategoriaTramiteFiltro;
    }
    
    public void filtrar() {
        
    }
    
    public List<UIABMCategoriaTramiteGrilla> buscarCategorias() {
        List<UIABMCategoriaTramiteGrilla> categoriasGrilla = new ArrayList<>(); // Siempre inicializar una lista vacía
        List<DTOCategoriaTramite> dtoCategorias = controladorABMCategoriaTramite.buscarCategorias(codCategoriaTramiteFiltro, nombreCategoriaTramiteFiltro); // Método para buscar categorías

        if (dtoCategorias != null) {
            for (DTOCategoriaTramite categoriaDTO : dtoCategorias) {
                UIABMCategoriaTramiteGrilla uiCategoriaTramiteGrilla = new UIABMCategoriaTramiteGrilla();
                uiCategoriaTramiteGrilla.setCodCategoriaTramite(categoriaDTO.getCodCategoriaTramite());
                uiCategoriaTramiteGrilla.setNombreCategoriaTramite(categoriaDTO.getNombreCategoriaTramite());
                uiCategoriaTramiteGrilla.setFechaHoraBajaCategoriaTramite(categoriaDTO.getFechaHoraBajaCategoriaTramite());
                categoriasGrilla.add(uiCategoriaTramiteGrilla);
            }
        }

        return categoriasGrilla;
    }

    // Redirigir a la página para agregar una nueva categoría
    public String irAgregarCategoria() {
        BeansUtils.guardarUrlAnterior();
        return "abmCategoriaTramite.xhtml?faces-redirect=true&codCategoriaTramite=0";
    }

    // Redirigir a la página para modificar una categoría existente
    public String irModificarCategoria(int codCategoriaTramite) {
        BeansUtils.guardarUrlAnterior();
        return "abmCategoriaTramite?faces-redirect=true&codCategoriaTramite=" + codCategoriaTramite;
    }

    // Método para dar de baja una categoría
    public void darDeBajaCategoria(int codCategoriaTramite) {
        try {
            controladorABMCategoriaTramite.darDeBajaCategoriaTramite(codCategoriaTramite);
            Messages.create("Categoría anulada").detail("Anulada").add();
        } catch (CategoriaTramiteException e) {
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }

}


