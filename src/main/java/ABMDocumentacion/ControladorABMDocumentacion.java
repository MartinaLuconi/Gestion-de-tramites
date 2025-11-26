package ABMDocumentacion;

import ABMDocumentacion.dtos.DocumentacionDTO;
import ABMDocumentacion.dtos.ModificarDocumentacionDTO;
import ABMDocumentacion.dtos.ModificarDocumentacionDTOIn;
import ABMDocumentacion.dtos.NuevaDocumentacionDTO;
import ABMDocumentacion.exceptions.ABMDocumentacionException;
import java.util.List;


public class ControladorABMDocumentacion {

   private ExpertoABMDocumentacion expertoABMDocumentacion = new ExpertoABMDocumentacion();
   
   public List<DocumentacionDTO> buscarDocumentacion(int codDocumentacion, String nombreDocumentacion){
       return expertoABMDocumentacion.buscarDocumentacion(codDocumentacion, nombreDocumentacion);
   }

   public void agregarDocumentacion(NuevaDocumentacionDTO nuevaDocumentacionDTO) throws ABMDocumentacionException{
       expertoABMDocumentacion.agregarDocumentacion(nuevaDocumentacionDTO);
   }

   public void modificarDocumentacion(ModificarDocumentacionDTOIn modificarDocumentacionDTOIn)throws ABMDocumentacionException {
       expertoABMDocumentacion.modificarDocumentacion(modificarDocumentacionDTOIn);
   }

   public ModificarDocumentacionDTO buscarDocumentacionAModificar(int codDocumentacion){
       return expertoABMDocumentacion.buscarDocumentacionAModificar(codDocumentacion);
   }

   public void darDeBajaDocumentacion(int codDocumentacion) throws ABMDocumentacionException {
       expertoABMDocumentacion.darDeBajaDocumentacion(codDocumentacion);
   }
}
