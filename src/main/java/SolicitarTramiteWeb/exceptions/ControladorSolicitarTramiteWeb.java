package SolicitarTramiteWeb.exceptions;

import SolicitarTramiteWeb.dtos.DTOCategoriaTramite;
import SolicitarTramiteWeb.dtos.DTOCliente;
import SolicitarTramiteWeb.dtos.DTOConfirmacion;
import SolicitarTramiteWeb.dtos.DTOConfirmacion2;
import SolicitarTramiteWeb.dtos.DTOTipoTramiteWeb;
import SolicitarTramiteWeb.dtos.DTOTramiteWeb;
import entidades.Documentacion;
import java.sql.Timestamp;
import java.util.List;

public class ControladorSolicitarTramiteWeb {
    private ExpertoSolicitarTramiteWeb expertoSolicitarTramiteWeb = new ExpertoSolicitarTramiteWeb();
    
  
    public List<DTOCategoriaTramite> obtenerCategoriaTramite(){
       return expertoSolicitarTramiteWeb.obtenerCategoriaTramite();
   }
    
    public List<DTOTipoTramiteWeb> elegirCategoriaTramite(int codTipoTramite) {
      return expertoSolicitarTramiteWeb.elegirCategoriaTramite(codTipoTramite);  
   }
  
    
   public DTOTramiteWeb elegirTipoTramite(int codTipoTramite, String cuitCliente) throws SolicitarTramiteWebException {
      return expertoSolicitarTramiteWeb.elegirTipoTramite(codTipoTramite, cuitCliente);
   }

    public ExpertoSolicitarTramiteWeb getExpertoSolicitarTramiteWeb() {
        return expertoSolicitarTramiteWeb;
    }

    public void setExpertoSolicitarTramiteWeb(ExpertoSolicitarTramiteWeb expertoSolicitarTramiteWeb) {
        this.expertoSolicitarTramiteWeb = expertoSolicitarTramiteWeb;
    }
  
    public DTOCliente obtenerCliente(String cuitCliente) {
      return expertoSolicitarTramiteWeb.obtenerCliente(cuitCliente);
   }
 


public DTOConfirmacion2 confirmar2() {
       return expertoSolicitarTramiteWeb.confirmar2();
    }


}