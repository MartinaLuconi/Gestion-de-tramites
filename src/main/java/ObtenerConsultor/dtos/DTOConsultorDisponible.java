
package ObtenerConsultor.dtos;


public class DTOConsultorDisponible {
    private int legajoConsultor;
    private int tramitesConsultorDisponible;
    private int consultorNroMaxTramite;

    public int getConsultorNroMaxTramite() {
        return consultorNroMaxTramite;
    }

    public void setConsultorNroMaxTramite(int consultorNroMaxTramite) {
        this.consultorNroMaxTramite = consultorNroMaxTramite;
    }

    public int getLegajoConsultor() {
        return legajoConsultor;
    }

    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }

    public int getTramitesConsultorDisponible() {
        return tramitesConsultorDisponible;
    }

    public void setTramitesConsultorDisponible(int tramitesConsultorDisponible) {
        this.tramitesConsultorDisponible = tramitesConsultorDisponible;
    }
    
}
