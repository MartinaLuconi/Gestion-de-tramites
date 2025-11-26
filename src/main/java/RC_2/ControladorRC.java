/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2;

import RC_2.dto.EstadoActualDTO;
import RC_2.dto.HistoricoDTO;
import RC_2.dto.NuevoEstadoDTO;
import RC_2.dto.TramiteParticularDTO;
import RC_2.dto.TramitesConsultorDTO;
import RC_2.dto.TransicionDTO;
import RC_2.dto.datosConsultorDTO;
import entidades.TramiteEstado;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
/**
 *
 * @author marti
 */
public class ControladorRC {
    
    private ExpertoRC expertoRC = new ExpertoRC();
    
    
    
     public void anularUltimo(int nroTramite) throws exceptionRC{
        expertoRC.anularUltimo(nroTramite);
    }
            
     
    public datosConsultorDTO obtenerConsultor(int usuario){
        return expertoRC.obtenerConsultor(usuario);
    }
    
    public List<TramitesConsultorDTO> obtenerTramitesInactivos(int usuario,Date fechaFiltro,String nombreTipoTramiteFiltro, String estadoFiltro) throws exceptionRC{
      return expertoRC.obtenerTramitesInactivos(usuario, fechaFiltro, nombreTipoTramiteFiltro, estadoFiltro);
    }
     
     
    public void cambiarEstadoTramite (TransicionDTO tdto, int nroTramite) throws exceptionRC{
         expertoRC.cambiarEstadoTramite(tdto, nroTramite);
    }
    
   
    
    
    public List<HistoricoDTO> etapas(int nroTramite){
      
        return new ArrayList<HistoricoDTO>();
    }
    
    
            
    public NuevoEstadoDTO nuevoEstado(int legajoConsultor,int nroTramite) throws exceptionRC{
        return expertoRC.nuevoEstado(legajoConsultor,nroTramite);
    }
            
    
    
    public TramiteParticularDTO obtenerTramiteParticular(int legajoConsultor, int nroTramite) throws exceptionRC{
        return expertoRC.obtenerTramiteParticular(legajoConsultor, nroTramite);
    }
    
    
            
    public List<TramitesConsultorDTO> obtenerTramitesConsultor( int usuario, Date fechaFiltro,String nombreTipoTramiteFiltro, String estadoFiltro) throws exceptionRC{
       
        return expertoRC.obtenerTramitesConsultor( usuario,fechaFiltro, nombreTipoTramiteFiltro, estadoFiltro);
    }
    
     public TramitesConsultorDTO buscarTramiteConsultor(int nroTramiteFiltro){
         return expertoRC.buscarTramiteConsultor(nroTramiteFiltro);
     }
     
      public NuevoEstadoDTO nuevoEstadoCP(int nroTramite) throws exceptionRC{
           return expertoRC.nuevoEstadoCP(nroTramite);
      }
    
}
