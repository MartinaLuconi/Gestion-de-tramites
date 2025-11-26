package entidades;

import java.util.ArrayList;
import java.util.List;

public class TransicionEstado extends Entidad {
    private int nroTransicionEstado;
    private EstadoTramite estadoActual;
    private List<EstadoTramite> estadosDestino = new ArrayList<>();
    
    // Constructor
    public TransicionEstado() {
    }
    
    // Getters y Setters
    public int getNroTransicionEstado() {
        return nroTransicionEstado;
    }

    public void setNroTransicionEstado(int nroTransicionEstado) {
        this.nroTransicionEstado = nroTransicionEstado;
    }
    
    public EstadoTramite getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoTramite estadoActual) {
        this.estadoActual = estadoActual;
    }

    public List<EstadoTramite> getEstadosDestino() {
        return estadosDestino;
    }

    public void setEstadosDestino(List<EstadoTramite> estadosDestino) {
        this.estadosDestino = estadosDestino;
    }

    // Métodos para agregar y quitar estados destino
    public void addEstadoDestino(EstadoTramite estado) {
        this.estadosDestino.add(estado);
    }

    public void removeEstadoDestino(EstadoTramite estado) {
        this.estadosDestino.remove(estado);
    }
}
