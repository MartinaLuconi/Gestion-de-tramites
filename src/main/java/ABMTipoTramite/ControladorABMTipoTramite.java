package ABMTipoTramite;

import ABMTipoTramite.dtos.DTOCategoriaTramite;
import ABMTipoTramite.dtos.DTODocumentacionTT;
import ABMTipoTramite.dtos.DTOModificarTipoTramite;
import ABMTipoTramite.dtos.DTOModificarTipoTramiteIn;
import ABMTipoTramite.dtos.DTONuevoTipoTramite;
import ABMTipoTramite.dtos.DTOTipoTramite;
import ABMTipoTramite.dtos.DTOVerTipoTramite;
import ABMTipoTramite.exceptions.ABMTipoTramiteException;
import java.util.List;

public class ControladorABMTipoTramite {

    private ExpertoABMTipoTramite expertoABMTipoTramite = new ExpertoABMTipoTramite();

    public List<DTOCategoriaTramite> buscarCategoriasPosibles() {
        return expertoABMTipoTramite.buscarCategoriasPosibles();
    }

    public List<DTOTipoTramite> buscarTipoTramites(int codigo, String nombre) {
        return expertoABMTipoTramite.buscarTipoTramites(codigo, nombre);
    }

    public DTOVerTipoTramite visualizarTipoTramite(int codtt) throws ABMTipoTramiteException {
        return expertoABMTipoTramite.visualizar(codtt);
    }

     public void agregarTipoTramite(DTONuevoTipoTramite DTONuevoTipoTramite) throws ABMTipoTramiteException{
        expertoABMTipoTramite.agregarABMTipoTramite(DTONuevoTipoTramite);
    }

    public DTOModificarTipoTramite buscarTipoTramiteAModificar(int codigo) {
        return expertoABMTipoTramite.buscarTipoTramiteAModificar(codigo);
    }

    public void modificarTipoTramite(DTOModificarTipoTramiteIn DTOModificarTipoTramiteIn) {
        expertoABMTipoTramite.modificarTipoTramite(DTOModificarTipoTramiteIn);
    }

    public void darDeBajaTipoTramite(int codigo) throws ABMTipoTramiteException {
        expertoABMTipoTramite.darDeBajaTipoTramite(codigo);
    }
    public List<DTODocumentacionTT> buscarDocumentacionesPosibles() {
        return expertoABMTipoTramite.buscarDocumentacionesPosibles();
    }
}
