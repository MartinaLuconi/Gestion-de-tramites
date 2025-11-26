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
public class DTOEstadoActualInput {
    private int codEstadoActual;
    private int nroTransicion;
    private List<DTOEstadoDestinoInput> estadosDestino = new ArrayList();

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

    public List<DTOEstadoDestinoInput> getEstadosDestino() {
        return estadosDestino;
    }

    public void setEstadosDestino(List<DTOEstadoDestinoInput> estadosDestino) {
        this.estadosDestino = estadosDestino;
    }
    
    public void addEstadosDestino (DTOEstadoDestinoInput estadoDestino){
        estadosDestino.add(estadoDestino);
    }
    
}
