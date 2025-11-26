package CompletarDocumentacion;

import CompletarDocumentacion.dtos.DTOArchivo;
import CompletarDocumentacion.dtos.DTOCompletarDoc;
import CargarTramite.exceptions.TramiteException;


public class ControladorCompletarDocumentacion {
    
    private ExpertoCompletarDocumentacion expertoCompletarDocumentacion = new ExpertoCompletarDocumentacion();

    
    
    
    public DTOCompletarDoc seleccionarTramite(int nroTramite) throws TramiteException{
        return expertoCompletarDocumentacion.seleccionarTramite(nroTramite);
    }

    
    public void guardarArchivo(int nroTramite, int codTramiteDocumentacion, DTOArchivo archivo) throws TramiteException{
        expertoCompletarDocumentacion.guardarArchivo(nroTramite, codTramiteDocumentacion, archivo);
    }
    
      public DTOArchivo descargarArchivo(int nroTramite, int codTramiteDocumentacion) throws Exception{
        return expertoCompletarDocumentacion.descargarArchivo(nroTramite, codTramiteDocumentacion);
    }
    
    public void eliminarArchivo(int nroTramite, int codTramiteDocumentacion) throws TramiteException{
        expertoCompletarDocumentacion.eliminarArchivo(nroTramite,codTramiteDocumentacion);
    }
}
