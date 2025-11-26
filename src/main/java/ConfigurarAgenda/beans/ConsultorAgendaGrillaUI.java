package ConfigurarAgenda.beans;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;

@Named(value = "consultorAgendaGrillaUI")
@ViewScoped
public class ConsultorAgendaGrillaUI implements Serializable {
    
    private int legajoConsultor;
    private String nombreApellidoConsultor;

    public int getLegajoConsultor() {
        return legajoConsultor;
    }

    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }

    public String getNombreApellidoConsultor() {
        return nombreApellidoConsultor;
    }

    public void setNombreApellidoConsultor(String nombreApellidoConsultor) {
        this.nombreApellidoConsultor = nombreApellidoConsultor;
    }
}

    

