/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julie
 */
public class DTOEstadoActualOutput {
    private int codEstadoActual;
    private int nroTransicion;
    private String nombreEstadoTramite;
    private List<DTOEstadoDestinoOutput> estadosDestino = new ArrayList();

    public int getCodEstadoActual() {
        return codEstadoActual;
    }

    public void setCodEstadoActual(int codEstadoActual) {
        this.codEstadoActual = codEstadoActual;
    }

    public int getNroTransicion() {
        return nroTransicion;
    }

    public void setNroTransicion(int nroTransicion) {
        this.nroTransicion = nroTransicion;
    }

    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }

    public List<DTOEstadoDestinoOutput> getEstadosDestino() {
        return estadosDestino;
    }

    public void setEstadosDestino(List<DTOEstadoDestinoOutput> estadosDestino) {
        this.estadosDestino = estadosDestino;
    }
    
    public void addEstadosDestino (DTOEstadoDestinoOutput estadoDestino){
        estadosDestino.add(estadoDestino);
    }
}
