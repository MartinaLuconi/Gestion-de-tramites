package AMBConsultor.dtos;

import entidades.Usuario;

/**
 *
 * @author adrie
 */

public class ModificarConsultorDTO  {

    private int consultorNroMaxTramite;
    private int legajoConsultor;
    private String nombreApellidoConsultor;
    private String usuario;

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
    
    public String getNombreApellidoConsultor(){
        return nombreApellidoConsultor;
    }
    
    public void setNombreApellidoConsultor(String nombreApellidoConsultor){
        this.nombreApellidoConsultor = nombreApellidoConsultor;
    }  
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}