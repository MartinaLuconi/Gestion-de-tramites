package ConfigurarAgenda.dtos;

import java.util.ArrayList;
import java.util.List;

public class DTOPantallaAgenda {
    private int anio;
    private int mes;
    private List<DTOAgendaSemana> dtoAgendaSemana = new ArrayList<>();
    private List<DTOConsultor> dtoConsultor = new ArrayList<>();

    
    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public List<DTOAgendaSemana> getDtoAgendaSemana() {
        return dtoAgendaSemana;
    }

    public void setDtoAgendaSemana(List<DTOAgendaSemana> dtoAgendaSemana) {
        this.dtoAgendaSemana = dtoAgendaSemana;
    }
    
    public void addDtoAgendaSemana(DTOAgendaSemana dtoAgendaSemana) {
        this.dtoAgendaSemana.add(dtoAgendaSemana);
    }
    
    public List<DTOConsultor> getDtoConsultor() {
        return dtoConsultor;
    }

    public void setDtoConsultor(List<DTOConsultor> dtoConsultor) {
        this.dtoConsultor = dtoConsultor;
    }
    
    public void addDtoConsultor(DTOConsultor dtoConsultor) {
        this.dtoConsultor.add(dtoConsultor);
    }
}
