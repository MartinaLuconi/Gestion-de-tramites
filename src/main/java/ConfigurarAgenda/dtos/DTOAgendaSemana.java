package ConfigurarAgenda.dtos;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DTOAgendaSemana {
    
    private int anio;
    private int semana;
    private Date fechaDesdeAgenda;
    private Date fechaHastaAgenda;
    private List<DTOConsultorAgenda> dtoConsultorAgenda = new ArrayList<>();

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

    public List<DTOConsultorAgenda> getDtoConsultorAgenda() {
        return dtoConsultorAgenda;
    }

    public void setDtoConsultorAgenda(List<DTOConsultorAgenda> dtoConsultorAgenda) {
        this.dtoConsultorAgenda = dtoConsultorAgenda;
    }
    
    public void addDtoConsultorAgenda(DTOConsultorAgenda dtoConsultorAgenda) {
        this.dtoConsultorAgenda.add(dtoConsultorAgenda);
    }
}
