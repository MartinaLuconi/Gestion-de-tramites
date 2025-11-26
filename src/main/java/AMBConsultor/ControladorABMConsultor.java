package AMBConsultor;

import AMBConsultor.dtos.ConsultorDTO;
import AMBConsultor.dtos.ModificarConsultorDTO;
import AMBConsultor.dtos.ModificarConsultorDTOIn;
import AMBConsultor.dtos.NuevoConsultorDTO;
import AMBConsultor.exceptions.ConsultorException;
import java.util.List;


/**
 *
 * @author adrie
 */
public class ControladorABMConsultor {

   private ExpertoABMConsultor expertoABMConsultor = new ExpertoABMConsultor();
    public List<ConsultorDTO> buscarConsultores(int legajoConsultor, String nombreApellidoConsultor){
        return expertoABMConsultor.buscarConsultores (legajoConsultor,nombreApellidoConsultor);
    }
    
    public void agregarConsultor(NuevoConsultorDTO nuevoConsultorDTO) throws ConsultorException{
        expertoABMConsultor.agregarConsultor (nuevoConsultorDTO);
    }

    public void modificarConsultor(ModificarConsultorDTOIn modificarConsultorDTOIn)throws ConsultorException {
        expertoABMConsultor.modificarConsultor(modificarConsultorDTOIn);
    }

    public ModificarConsultorDTO buscarConsultorAModificar(int legajoConsultor){
        return expertoABMConsultor.buscarConsultorAModificar(legajoConsultor);
    }

    public void darDeBajaConsultor(int legajoConsultor) throws ConsultorException {
      expertoABMConsultor.darDeBajaConsultor(legajoConsultor);
    }

}

