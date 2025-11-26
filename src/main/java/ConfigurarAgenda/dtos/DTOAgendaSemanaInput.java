package ConfigurarAgenda.dtos;

import java.util.ArrayList;
import java.util.List;

public class DTOAgendaSemanaInput {
    private int anio;
    private int semana;
    private List<DTOConsultorAgendaInput> dtoConsultorAgendaInput = new ArrayList<>();

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

    public List<DTOConsultorAgendaInput> getDtoConsultorAgendaInput() {
        return dtoConsultorAgendaInput;
    }

    public void setDtoConsultorAgendaInput(List<DTOConsultorAgendaInput> dtoConsultorAgendaInput) {
        this.dtoConsultorAgendaInput = dtoConsultorAgendaInput;
    }
    
    public void addDtoConsultorAgendaInput(DTOConsultorAgendaInput dtoConsultorAgendaInput) {
        this.dtoConsultorAgendaInput.add(dtoConsultorAgendaInput);
    }
}
