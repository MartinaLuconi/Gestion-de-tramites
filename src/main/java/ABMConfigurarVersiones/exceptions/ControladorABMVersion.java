/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.exceptions;

import ABMConfigurarVersiones.dtos.DTOVersionHistorial;
import ABMConfigurarVersiones.dtos.DTOVersionInput;
import ABMConfigurarVersiones.dtos.DTOVersionOutput;
import ABMConfigurarVersiones.dtos.DTOVersionesDeTipo;
import ABMConfigurarVersiones.dtos.DTOVersionyEstados;
import java.util.List;

/**
 *
 * @author julie
 */
public class ControladorABMVersion {
    private ExpertoABMVersion expertoABMVersion = new ExpertoABMVersion();
    
    
    public List<DTOVersionesDeTipo> listarTablaVersionesDeTipos(int codtt, String nombrett){
        return expertoABMVersion.listarTablaVersionesDeTipos(codtt, nombrett);
    }
            
    public DTOVersionOutput verVersion (int codTipoTramite, int codVersion) throws VersionException{
        return expertoABMVersion.verVersion(codTipoTramite, codVersion);
    }
   
    public DTOVersionyEstados buscarVersionEditar (int codTipoTramite) throws VersionException{
        return expertoABMVersion.buscarVersionEditar(codTipoTramite);
    }
    
    public void nuevaVersion (DTOVersionInput datosingresados)throws VersionException{
        expertoABMVersion.nuevaVersion(datosingresados);
    }
    
    public List<DTOVersionHistorial> historialVersiones(int codigoTT, int codv){
        return expertoABMVersion.historialVersiones(codigoTT, codv);
    }
    
    public void anularVersion(int codVersion, int codTT) throws VersionException{
        expertoABMVersion.anularVersion(codVersion, codTT);
    }
    
}
