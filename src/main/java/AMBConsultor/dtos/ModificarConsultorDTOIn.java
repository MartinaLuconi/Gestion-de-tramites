package AMBConsultor.dtos;

/**
 *
 * @author adrie
 */
public class ModificarConsultorDTOIn  {

    private int legajoConsultor;
    private int consultorNroMaxTramite;
    private String nombreApellidoConsultor;
    private String usuario;

    public int getLegajoConsultor() {
        return legajoConsultor; 
    }
    public void setLegajoConsultor(int legajoConsultor){
        this.legajoConsultor = legajoConsultor;
    } 
    public int getConsultorNroMaxTramite() {
        return consultorNroMaxTramite;
    }

    public void setConsultorNroMaxTramite(int consultorNroMaxTramite) {
        this.consultorNroMaxTramite = consultorNroMaxTramite;
    }
    
    public String getNombreApellidoConsultor() {
        return nombreApellidoConsultor;
    }
    public void setNombreApellidoConsultor(String nombreApellidoConsultor) {
        this.nombreApellidoConsultor = nombreApellidoConsultor;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}