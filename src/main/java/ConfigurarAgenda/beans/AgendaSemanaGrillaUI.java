package ConfigurarAgenda.beans;


import ConfigurarAgenda.dtos.DTOConsultorAgenda;
import entidades.Consultor;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "agendaSemanaGrillaUI")
@ViewScoped
public class AgendaSemanaGrillaUI implements Serializable {

    private int anio;
    private int semana;
    private Date fechaDesdeAgenda;
    private Date fechaHastaAgenda;
    private List<DTOConsultorAgenda> dtoConsultorAgenda = new ArrayList<>();
    private List<Consultor> consultores = new ArrayList<>();
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        // Obtener los parámetros de la URL
        semana = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("semana"));
        anio = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("anio"));
    }
    
    public List<DTOConsultorAgenda> getDtoConsultorAgenda() {
        return dtoConsultorAgenda;
    }

    public void setDtoConsultorAgenda(List<DTOConsultorAgenda> dtoConsultorAgenda) {
        this.dtoConsultorAgenda = dtoConsultorAgenda;
    }

    public List<Consultor> getConsultores() {
        return consultores;
    }

    public void setConsultores(List<Consultor> consultores) {
        this.consultores = consultores;
    }

    public void addConsultores(Consultor consultores) {
        this.consultores.add(consultores);
    }
    
    public List<DTOConsultorAgenda> getDtoConsultor() {
        return dtoConsultorAgenda;
    }

    public void setDtoConsultor(List<DTOConsultorAgenda> dtoConsultorAgenda) {
        this.dtoConsultorAgenda = dtoConsultorAgenda;
    }

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
    
    public void addDtoConsultorAgenda(DTOConsultorAgenda dtoConsultorAgenda) {
        this.dtoConsultorAgenda.add(dtoConsultorAgenda);
    }
}
