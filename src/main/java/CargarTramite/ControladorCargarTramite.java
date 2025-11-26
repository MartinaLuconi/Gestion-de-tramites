package CargarTramite;

import CargarTramite.dtos.DTOClienteV;
import CargarTramite.dtos.DTOTipoTramiteV;
import CargarTramite.dtos.DTOTramiteV;
import CargarTramite.dtos.DTOVisualizarTramite;
import java.util.Date;
import CargarTramite.exceptions.TramiteException;

import java.util.List;

public class ControladorCargarTramite {
    
    
    private ExpertoCargarTramite expertoCargarTramite = new ExpertoCargarTramite();
    

    public List<DTOTramiteV> mostrarTramites(Date fechaDesdeFiltro, String cuitFiltro,
                int nroTramiteFiltro, String estadoFiltro, 
                int legajoConsultorFiltro, int codigoTTFiltro){
        
        return expertoCargarTramite.mostrarTramites(fechaDesdeFiltro, cuitFiltro,
                nroTramiteFiltro,estadoFiltro,legajoConsultorFiltro,codigoTTFiltro);
    }
    
    
    public void botonCargar(String cuit, int codigoTT) throws TramiteException, Exception{
        expertoCargarTramite.botonCargar(cuit, codigoTT);
    }
    
    public DTOVisualizarTramite visualizarTramite(int nroTramite) throws Exception{
        return expertoCargarTramite.visualizarTramite(nroTramite);
    }
    
    public DTOClienteV obtenerCliente(String cuit) throws TramiteException {
        return expertoCargarTramite.obtenerCliente(cuit);
    }
    
    public DTOTipoTramiteV obtenerTipoTramite(int codigoTT) throws TramiteException {
        return expertoCargarTramite.obtenerTipoTramite(codigoTT);
    }
    
    public void anularTramite(int nroTramite) throws TramiteException {
        expertoCargarTramite.anularTramite(nroTramite);
    }
    


}
