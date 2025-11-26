package ObtenerConsultor.beans;

import ObtenerConsultor.exceptions.ControladorObtenerConsultor;
import entidades.Consultor;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;

@Named(value = "uiObtenerConsultor")
@ViewScoped
public class UIObtenerConsultor implements Serializable {
    
    private ControladorObtenerConsultor controladorObtenerConsultor = new ControladorObtenerConsultor();
    private Consultor consultor;

    // Atributos para mostrar en la interfaz
    private int legajoConsultor;
    private String nombreApellidoConsultor;

    public void init() {
        // Llama al método para obtener el consultor al inicializar la vista
        consultor = controladorObtenerConsultor.obtenerConsultor();
        
        // Guarda el legajo y el nombre del consultor
        if (consultor != null) {
            guardarConsultor(consultor);
        }
    }

    public Consultor getConsultor() {
        return consultor;
    }
    
    // Método para guardar el legajo y el nombre del consultor
    public void guardarConsultor(Consultor consultor) {
        this.legajoConsultor = consultor.getLegajoConsultor();
        this.nombreApellidoConsultor = consultor.getNombreApellidoConsultor();
    }

    // Getters para mostrar en la vista
    public int getLegajoConsultor() {
        return legajoConsultor;
    }

    public String getNombreApellidoConsultor() {
        return nombreApellidoConsultor;
    }
}
