package entidades;

import jakarta.persistence.Column;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Version extends Entidad {
    private int codVersion;
    private Timestamp fechaHoraDesdeVersion;
    private Timestamp fechaHoraHastaVersion;
    private Timestamp fechaHoraBajaVersion;
    private TipoTramite tipoTramite;
    private List<TransicionEstado> transicionEstadoList = new ArrayList<>();
    @Column(name = "draw", length = 1000)
    private String draw;

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }
    // Constructor
    public Version() {
    }
    
    // Getters y Setters
    public int getCodVersion() {
        return codVersion;
    }

    public void setCodVersion(int codVersion) {
        this.codVersion = codVersion;
    }

    public Timestamp getFechaHoraDesdeVersion() {
        return fechaHoraDesdeVersion;
    }

    public void setFechaHoraDesdeVersion(Timestamp fechaHoraDesdeVersion) {
        this.fechaHoraDesdeVersion = fechaHoraDesdeVersion;
    }

    public Timestamp getFechaHoraHastaVersion() {
        return fechaHoraHastaVersion;
    }

    public void setFechaHoraHastaVersion(Timestamp fechaHoraHastaVersion) {
        this.fechaHoraHastaVersion = fechaHoraHastaVersion;
    }

    public Timestamp getFechaHoraBajaVersion() {
        return fechaHoraBajaVersion;
    }

    public void setFechaHoraBajaVersion(Timestamp fechaHoraBajaVersion) {
        this.fechaHoraBajaVersion = fechaHoraBajaVersion;
    }
    
    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public List<TransicionEstado> getTransicionEstadoList() {
        return transicionEstadoList;
    }

    public void setTransicionEstadoList(List<TransicionEstado> transicionEstadoList) {
        this.transicionEstadoList = transicionEstadoList;
    }
    
    public void addTransicionEstadoList(TransicionEstado transicionEstado) {
        transicionEstadoList.add(transicionEstado);
    }
}