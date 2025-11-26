package ObtenerConsultor;

import entidades.Consultor;

public class ControladorObtenerConsultor {
    private ExpertoObtenerConsultor expertoObtenerConsultor = new ExpertoObtenerConsultor();
    
    public Consultor obtenerConsultor(){
        return expertoObtenerConsultor.obtenerConsultor();
    }
}
