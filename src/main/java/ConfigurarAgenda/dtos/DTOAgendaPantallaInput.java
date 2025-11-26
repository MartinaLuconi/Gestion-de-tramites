package ConfigurarAgenda.dtos;

import java.util.ArrayList;
import java.util.List;


public class DTOAgendaPantallaInput {
    private List<DTOAgendaSemanaInput> dtoAgendaSemanaInput = new ArrayList<>();

    public List<DTOAgendaSemanaInput> getDtoAgendaSemanaInput() {
        return dtoAgendaSemanaInput;
    }

    public void setDtoAgendaSemanaInput(List<DTOAgendaSemanaInput> dtoAgendaSemanaInput) {
        this.dtoAgendaSemanaInput = dtoAgendaSemanaInput;
    }
    
    public void addDtoAgendaSemanaInput(DTOAgendaSemanaInput dtoAgendaSemanaInput) {
        this.dtoAgendaSemanaInput.add(dtoAgendaSemanaInput);
    }
}
