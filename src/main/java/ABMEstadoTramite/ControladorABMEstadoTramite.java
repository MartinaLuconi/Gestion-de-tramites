/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMEstadoTramite;

import ABMEstadoTramite.dtos.DTOEstadoTramite;
import ABMEstadoTramite.dtos.DTOModificarEstadoTramite;
import ABMEstadoTramite.dtos.DTOModificarEstadoTramiteIn;
import ABMEstadoTramite.dtos.DTONuevoEstadoTramite;
import ABMEstadoTramite.exceptions.EstadoTramiteException;
import java.util.List;

/**
 *
 * @author vickyvelasco
 */
public class ControladorABMEstadoTramite {
    private ExpertoABMEstadoTramite expertoABMEstadoTramite = new ExpertoABMEstadoTramite();

    public List<DTOEstadoTramite> buscarEstados(int codEstadoTramite, String nombreEstadoTramite) {
        return expertoABMEstadoTramite.buscarEstados(codEstadoTramite, nombreEstadoTramite);
    }

    public void agregarEstado(DTONuevoEstadoTramite dtoNuevoEstadoTramite) throws EstadoTramiteException {
        expertoABMEstadoTramite.agregarEstadoTramite(dtoNuevoEstadoTramite);
    }

    public void modificarEstado(DTOModificarEstadoTramiteIn dtoModificarEstadoIn) throws EstadoTramiteException {
        expertoABMEstadoTramite.modificarEstadoTramite(dtoModificarEstadoIn);
    }

    public DTOModificarEstadoTramite buscarEstadoAModificar(int codEstadoTramite) {
        return expertoABMEstadoTramite.buscarEstadoAModificar(codEstadoTramite);
    }

    public void darDeBajaEstadoTramite(int codEstadoTramite) throws EstadoTramiteException {
        expertoABMEstadoTramite.darDeBajaEstadoTramite(codEstadoTramite);
    }
    
}

