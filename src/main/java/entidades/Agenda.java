package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Agenda extends Entidad {
    private int anio;
    private int semana;
    private Date fechaDesdeAgenda;
    private Date fechaHastaAgenda;
    private List<Consultor> consultores = new ArrayList<>();

    // Constructor por defecto
    public Agenda() {
    }

    // Nuevo constructor con parámetros
    public Agenda(int anio, int semana, Date fechaDesdeAgenda, Date fechaHastaAgenda) {
        this.anio = anio;
        this.semana = semana;
        this.fechaDesdeAgenda = fechaDesdeAgenda;
        this.fechaHastaAgenda = fechaHastaAgenda;
    }

    // Getters y Setters
    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public Date getFechaDesdeAgenda() {
        return fechaDesdeAgenda;
    }

    public void setFechaDesdeAgenda(Date fechaDesdeAgenda) {
        this.fechaDesdeAgenda = fechaDesdeAgenda;
    }

    public Date getFechaHastaAgenda() {
        return fechaHastaAgenda;
    }

    public void setFechaHastaAgenda(Date fechaHastaAgenda) {
        this.fechaHastaAgenda = fechaHastaAgenda;
    }

    public List<Consultor> getConsultores() {
        return consultores;
    }

    public void setConsultores(List<Consultor> consultores) {
        this.consultores = consultores;
    }

    public void addConsultor(Consultor consultor) {
        consultores.add(consultor);
    }

    public void removeConsultor(Consultor consultor) {
        consultores.remove(consultor);
    }
}
