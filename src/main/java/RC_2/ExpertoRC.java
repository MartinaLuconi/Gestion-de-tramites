/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2;

import RC_2.dto.*;
import entidades.Consultor;
import entidades.DetallePrecioTipoTramite;
import entidades.EstadoTramite;
import entidades.ListaPrecio;
import entidades.TipoTramite;
import entidades.Tramite;
import entidades.TramiteEstado;
import entidades.TransicionEstado;
import entidades.Usuario;
import entidades.Version;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author marti
 */
public class ExpertoRC {
    
    
            
    public List<TramitesConsultorDTO> obtenerTramitesConsultor(int usuario,Date fechaFiltro,String nombreTipoTramiteFiltro, String estadoFiltro) throws exceptionRC{
        
   
         
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
                
            DTOCriterio UsuarioCriterio=new DTOCriterio();
            UsuarioCriterio.setAtributo("codUsuario");
            UsuarioCriterio.setOperacion("=");
            UsuarioCriterio.setValor(usuario);
            lCriterio.add(UsuarioCriterio);
           List usuarioEnc = FachadaPersistencia.getInstance().buscar("Usuario", lCriterio);  
           Usuario usr=(Usuario) usuarioEnc.get(0);
            
        // List<DTOCriterio> cCriterio=new ArrayList<DTOCriterio>();   
           lCriterio=new ArrayList<DTOCriterio>();    
            DTOCriterio cCriterio=new DTOCriterio();
            cCriterio.setAtributo("usuario");
            cCriterio.setOperacion("contains");
            cCriterio.setValor(usr);
            lCriterio.add(cCriterio);
            
            List consultorEnc = FachadaPersistencia.getInstance().buscar("Consultor", lCriterio);  
            Consultor consultor=(Consultor) consultorEnc.get(0);
            System.out.println("Consultor:" +consultor.getLegajoConsultor());
            datosConsultorDTO consultorAsignado = new datosConsultorDTO();
                consultorAsignado.setLegajoConsultor(consultor.getLegajoConsultor());
                consultorAsignado.setNombreConsultor(consultor.getNombreApellidoConsultor());
           
            
       lCriterio=new ArrayList<DTOCriterio>();
        if(fechaFiltro != null){
            DTOCriterio fechaCriterio=new DTOCriterio();
            fechaCriterio.setAtributo("fechaHoraCargaTramite");
            fechaCriterio.setOperacion("<");
            fechaCriterio.setValor(fechaFiltro);
            lCriterio.add(fechaCriterio);
            
        }
        
        DTOCriterio DocCompletaCriterio=new DTOCriterio();
        Timestamp fechaActual= new Timestamp(System.currentTimeMillis());
      
            DocCompletaCriterio.setAtributo("fechaHoraFinEntregaDocumentacion");
            DocCompletaCriterio.setOperacion( "<");
            DocCompletaCriterio.setValor(fechaActual);
            lCriterio.add(DocCompletaCriterio);
            
        DTOCriterio fechaFinCriterio=new DTOCriterio();
            fechaFinCriterio.setAtributo("fechaHoraFinTramite");
            fechaFinCriterio.setOperacion( "=");
            fechaFinCriterio.setValor(null);
            lCriterio.add(fechaFinCriterio);
            
        DTOCriterio fechaBajaCriterio=new DTOCriterio();
            fechaBajaCriterio.setAtributo("fechaHoraBajaTramite");
            fechaBajaCriterio.setOperacion("=");
            fechaBajaCriterio.setValor(null);
            lCriterio.add(fechaBajaCriterio);
            
        
            
         if(nombreTipoTramiteFiltro.trim().length()>0)
        {
            lCriterio=new ArrayList<DTOCriterio>();
            
            DTOCriterio criterioTT = new DTOCriterio();

            criterioTT.setAtributo("nombreTipoTramite");
            criterioTT.setOperacion("like");
            criterioTT.setValor(nombreTipoTramiteFiltro);
            lCriterio.add(criterioTT);

            TipoTramite tipoTramiteEncontrado = null;

            List ltipoTramites = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
            if (!ltipoTramites.isEmpty()) {
                tipoTramiteEncontrado = (TipoTramite) ltipoTramites.get(0);
            }

            lCriterio.clear();
            criterioTT.setAtributo("tipoTramite"); 
            criterioTT.setOperacion("=");
            criterioTT.setValor(tipoTramiteEncontrado);

            lCriterio.add(criterioTT);
           
            } 
  
        
        if(estadoFiltro.trim().length()>0)
        {   
            lCriterio=new ArrayList<DTOCriterio>();
           DTOCriterio criterioET = new DTOCriterio();

            criterioET.setAtributo("nombreEstadoTramite");
            criterioET.setOperacion("like");
            criterioET.setValor(estadoFiltro);
            lCriterio.add(criterioET);

            EstadoTramite estadoTramiteEncontrado = null;

            List lestTramites = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio);
            if (!lestTramites.isEmpty()) {
                estadoTramiteEncontrado = (EstadoTramite) lestTramites.get(0);
            }

            lCriterio.clear();
            criterioET.setAtributo("estadoTramite"); 
            criterioET.setOperacion("=");
            criterioET.setValor(estadoTramiteEncontrado);

            lCriterio.add(criterioET);
            
        }
        
        DTOCriterio conCriterio=new DTOCriterio();
            conCriterio.setAtributo("consultor");
            conCriterio.setOperacion("contains");
            conCriterio.setValor(consultor);
            lCriterio.add(conCriterio);

        List objetoList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        
        if(objetoList.isEmpty()){
            throw new exceptionRC("No hay tramites asignados de momento");
        }
        
        List<TramitesConsultorDTO> tramitesConsultorResultado = new ArrayList<>();
         
        for (Object x : objetoList) {
            Tramite tramite = (Tramite) x;
            
            TramitesConsultorDTO tramitesConsultorDTO = new TramitesConsultorDTO();
            tramitesConsultorDTO.setNroTramite(tramite.getNroTramite());
            tramitesConsultorDTO.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
            tramitesConsultorDTO.setFechaHoraCargaTramite(tramite.getFechaHoraCargaTramite());
            tramitesConsultorDTO.setNombreEstadoTramite(tramite.getEstadoTramite().getNombreEstadoTramite());
            tramitesConsultorDTO.setCuitCliente(tramite.getCliente().getCuitCliente());
            tramitesConsultorDTO.setConsultorAsignado(consultorAsignado);
            tramitesConsultorResultado.add(tramitesConsultorDTO);
            
        }
        return tramitesConsultorResultado;
        
       
        
        
    }
    
    public List<TramitesConsultorDTO> obtenerTramitesInactivos(int usuario,Date fechaFiltro,String nombreTipoTramiteFiltro, String estadoFiltro) throws exceptionRC{
        
   
         
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
                
            DTOCriterio UsuarioCriterio=new DTOCriterio();
            UsuarioCriterio.setAtributo("codUsuario");
            UsuarioCriterio.setOperacion("=");
            UsuarioCriterio.setValor(usuario);
            lCriterio.add(UsuarioCriterio);
           List usuarioEnc = FachadaPersistencia.getInstance().buscar("Usuario", lCriterio);  
           Usuario usr=(Usuario) usuarioEnc.get(0);
            
        
           lCriterio=new ArrayList<DTOCriterio>();    
            DTOCriterio cCriterio=new DTOCriterio();
            cCriterio.setAtributo("usuario");
            cCriterio.setOperacion("contains");
            cCriterio.setValor(usr);
            lCriterio.add(cCriterio);
            
            List consultorEnc = FachadaPersistencia.getInstance().buscar("Consultor", lCriterio);  
           Consultor consultor=(Consultor) consultorEnc.get(0);
            System.out.println("Consultor:" +consultor.getLegajoConsultor());
        
          lCriterio=new ArrayList<DTOCriterio>();  
          Timestamp fechaActual= new Timestamp(System.currentTimeMillis());
      
        DTOCriterio fechaFinCriterio=new DTOCriterio();
            fechaFinCriterio.setAtributo("fechaHoraFinTramite");
            fechaFinCriterio.setOperacion("<");
            fechaFinCriterio.setValor(fechaActual);
            lCriterio.add(fechaFinCriterio);
            
        DTOCriterio fechaBajaCriterio=new DTOCriterio();
            fechaBajaCriterio.setAtributo("fechaHoraBajaTramite");
            fechaBajaCriterio.setOperacion("=");
            fechaBajaCriterio.setValor(null);
            lCriterio.add(fechaBajaCriterio);
           
        DTOCriterio conCriterio=new DTOCriterio();
            conCriterio.setAtributo("consultor");
            conCriterio.setOperacion("contains");
            conCriterio.setValor(consultor);
            lCriterio.add(conCriterio); 

        List objetoList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        
        List<TramitesConsultorDTO> tramitesConsultorResultado = new ArrayList<>();
         
        for (Object x : objetoList) {
            Tramite tramite = (Tramite) x;
            
            
                
            datosConsultorDTO consultorAsignado = new datosConsultorDTO();
                consultorAsignado.setLegajoConsultor(consultor.getLegajoConsultor());
                consultorAsignado.setNombreConsultor(consultor.getNombreApellidoConsultor());
           
            TramitesConsultorDTO tramitesConsultorDTO = new TramitesConsultorDTO();
            tramitesConsultorDTO.setNroTramite(tramite.getNroTramite());
            tramitesConsultorDTO.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
            tramitesConsultorDTO.setFechaHoraCargaTramite(tramite.getFechaHoraCargaTramite());
            tramitesConsultorDTO.setNombreEstadoTramite(tramite.getEstadoTramite().getNombreEstadoTramite());
            tramitesConsultorDTO.setCuitCliente(tramite.getCliente().getCuitCliente());
            tramitesConsultorDTO.setConsultorAsignado(consultorAsignado);
            tramitesConsultorResultado.add(tramitesConsultorDTO);
            
        }
        return tramitesConsultorResultado;
        
       
        
        
    }
    
    public datosConsultorDTO obtenerConsultor(int usuario){
        datosConsultorDTO datosC = new datosConsultorDTO();
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
                
            DTOCriterio UsuarioCriterio=new DTOCriterio();
            UsuarioCriterio.setAtributo("codUsuario");
            UsuarioCriterio.setOperacion("=");
            UsuarioCriterio.setValor(usuario);
            lCriterio.add(UsuarioCriterio);
           List usuarioEnc = FachadaPersistencia.getInstance().buscar("Usuario", lCriterio);  
           Usuario usr=(Usuario) usuarioEnc.get(0);
            
        // List<DTOCriterio> cCriterio=new ArrayList<DTOCriterio>();   
           lCriterio=new ArrayList<DTOCriterio>();    
            DTOCriterio cCriterio=new DTOCriterio();
            cCriterio.setAtributo("usuario");
            cCriterio.setOperacion("contains");
            cCriterio.setValor(usr);
            lCriterio.add(cCriterio);
            
            List consultorEnc = FachadaPersistencia.getInstance().buscar("Consultor", lCriterio);  
           Consultor consultor=(Consultor) consultorEnc.get(0);
           datosC.setLegajoConsultor(consultor.getLegajoConsultor());
           datosC.setNombreConsultor(consultor.getNombreApellidoConsultor());
           
        return datosC;
    }
    

    
    public TramiteParticularDTO obtenerTramiteParticular(int legajoConsultor,int nroTramiteFiltro) throws exceptionRC{
        
        
 
        
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        //lCriterio=new ArrayList<DTOCriterio>();
        if(nroTramiteFiltro>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        Tramite tramiteEncontrado = (Tramite) FachadaPersistencia.getInstance().buscar("Tramite", lCriterio).get(0);
        
        int legajoCA = tramiteEncontrado.getConsultor().getLegajoConsultor();
        
        if (legajoCA != legajoConsultor){
            throw new exceptionRC("El trámite al que desea acceder no pertenece al consultor");
        }
        

        TramiteParticularDTO tramiteParticularDTO = new TramiteParticularDTO();
            tramiteParticularDTO.setCodTipoTramite(tramiteEncontrado.getTipoTramite().getCodTipoTramite());
            tramiteParticularDTO.setFechaHoraFinEntregaDoc(tramiteEncontrado.getFechaHoraFinEntregaDocumentacion());
            tramiteParticularDTO.setNombreApellidoCliente(tramiteEncontrado.getCliente().getNombreApellidoCliente());
            tramiteParticularDTO.setFechaHoraFinTramite(tramiteEncontrado.getFechaHoraFinTramite());//encontrar ultimo TE
            List <TramiteEstado> tramEstados = tramiteEncontrado.getTramiteEstadoList();
            TramiteEstado tramiteEstadoMasReciente = tramEstados.stream()
                .max(Comparator.comparing(TramiteEstado::getFechaHoraDesdeTramiteEstado))
                .orElse(null);
            
            tramiteParticularDTO.setObservaciones(tramiteEstadoMasReciente.getObservacionTramiteEstado());
            //setear precio
            List<DTOCriterio> lCriterioLP=new ArrayList<DTOCriterio>();
            List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterioLP);

            ListaPrecio listaPrecioActual = objetoList.stream()
                    .map(x -> (ListaPrecio) x) // Convertir cada objeto a ListaPrecio     
                    .max(Comparator.comparing(ListaPrecio::getFechaHoraDesdeListaPrecio)) // Obtener la lista con la fecha más reciente
                    .orElse(null); 
            List<DetallePrecioTipoTramite> detallesActuales = listaPrecioActual.getDetallePrecioTipoTramiteList();
            for(DetallePrecioTipoTramite det : detallesActuales){
                det.getTipoTramite();
                int codTTdet = det.getTipoTramite().getCodTipoTramite();
                int codTT = tramiteEncontrado.getTipoTramite().getCodTipoTramite();
                
                if(codTT == codTTdet){
                    tramiteParticularDTO.setPrecioTipoTramite((float) det.getPrecioTipoTramite());
                     
                }
                
            }
            
        DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramiteFiltro);
            lCriterio.add(unCriterio);
        
        Tramite tramiteEnc = (Tramite) FachadaPersistencia.getInstance().buscar("Tramite", lCriterio).get(0);
        
        List<TramiteEstado> tramitesEst =tramiteEnc.getTramiteEstadoList();
         
        for(TramiteEstado tramiteEstado : tramitesEst){
            
            HistoricoDTO historicoDTO = new HistoricoDTO();
            historicoDTO.setNroLineaTramiteEstado(tramiteEstado.getNroLineaTramiteEstado());
            historicoDTO.setCodEstadoTramite(tramiteEstado.getEstadoTramite().getCodEstadoTramite());
            historicoDTO.setFechaHoraBajaTramiteEstado(tramiteEstado.getFechaHoraBajaTramiteEstado());
            historicoDTO.setFechaHastaTramiteEstado(tramiteEstado.getFechaHoraHastaTramiteEstado());
            historicoDTO.setObservaciones(tramiteEstado.getObservacionTramiteEstado());
            historicoDTO.setNombreEstadoTramite(tramiteEstado.getEstadoTramite().getNombreEstadoTramite());
            
            tramiteParticularDTO.addHistoricoDTOList(historicoDTO);        
        }
        
        if(tramiteEnc.getFechaHoraBajaTramite()!=null){
             throw new exceptionRC("El trámite al que desea acceder se encuentra anulado.");
        }
//        if(tramiteEnc.getFechaHoraFinTramite()!=null){
//             throw new exceptionRC("El trámite al que desea acceder se encuentra finalizado.");
//        }
        
        return tramiteParticularDTO;
    }
    
  
    public void anularUltimo(int nroTramite) throws exceptionRC{
        
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        List <DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        
        dto.setAtributo("nroTramite");
        dto.setOperacion("=");
        dto.setValor(nroTramite);
        
        criterioList.add(dto);
        
        Tramite tramiteEncontrado =(Tramite) FachadaPersistencia.getInstance().buscar("Tramite", criterioList).get(0);
        Timestamp fechaFinT = tramiteEncontrado.getFechaHoraFinTramite();
        List <TramiteEstado> tramitesEstados=tramiteEncontrado.getTramiteEstadoList();
        //busca el ultimo para anular
        TramiteEstado tramiteEstadoMasReciente = tramitesEstados.stream()
                .filter(tramiteEstado -> tramiteEstado.getFechaHoraBajaTramiteEstado() == null)
                .max(Comparator.comparing(TramiteEstado::getFechaHoraDesdeTramiteEstado))
                .orElse(null);
        
        //verificacion que no sea el primero
        int nroLineaTEA=tramiteEstadoMasReciente.getNroLineaTramiteEstado();
        if(nroLineaTEA==1){
           throw new exceptionRC("No se puede anular el estado Iniciado");
        } else{
            
         //BUSCAR ET DEL TRAMITE Y VER Q CODIGOS SEAN =
       EstadoTramite estadoActual = tramiteEncontrado.getEstadoTramite();
      int codTE= estadoActual.getCodEstadoTramite();
      if(tramiteEncontrado.getEstadoTramite().getCodEstadoTramite()  == codTE) {
        
        //lo anula poniendole la fecha de baja 
        tramiteEstadoMasReciente.setFechaHoraBajaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        //tramiteEstadoMasReciente.setFechaHoraHastaTramiteEstado(new Timestamp(System.currentTimeMillis()));
      
       
        //busca el anterior y no anulado
        List<TramiteEstado> tramEstados = tramiteEncontrado.getTramiteEstadoList();
        
        TramiteEstado tramiteEstadoAnterior = tramEstados.stream()
                .filter(tramiteEstado -> tramiteEstado.getFechaHoraBajaTramiteEstado() == null)
                .max(Comparator.comparing(TramiteEstado::getFechaHoraDesdeTramiteEstado))
                .orElse(null);
        //if(tramiteEstadoAnterior.getFechaHoraBajaTramiteEstado()==null){
        tramiteEstadoAnterior.setFechaHoraHastaTramiteEstado(null);
        //}
        EstadoTramite estadoAnterior = tramiteEstadoAnterior.getEstadoTramite();
        tramiteEncontrado.setEstadoTramite(estadoAnterior);
         //Tramite tramiteE =(Tramite) FachadaPersistencia.getInstance().buscar("Tramite", criterioList).get(0);
        
        if(fechaFinT!=null){
            tramiteEncontrado.setFechaHoraFinTramite(null);
            
            tramiteEncontrado.setEstadoTramite(estadoAnterior);
            System.out.println("El tramite se ha reactivado");
           // FachadaPersistencia.getInstance().guardar(tramiteEncontrado);
        }
      
        FachadaPersistencia.getInstance().guardar(tramiteEncontrado);
        FachadaPersistencia.getInstance().guardar(tramiteEstadoMasReciente);
        FachadaPersistencia.getInstance().guardar(tramiteEstadoAnterior);
        FachadaPersistencia.getInstance().finalizarTransaccion();
      }
        }
    }
            
    public void cambiarEstadoTramite (TransicionDTO tdto, int nroTramite) throws exceptionRC{
        //buscar el estado ingresado en el dto
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        
          DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codEstadoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(tdto.getCodEstadoTramite());
            lCriterio.add(unCriterio);
            
          DTOCriterio dosCriterio=new DTOCriterio();
            dosCriterio.setAtributo("fechaHoraBajaEstadoTramite");
            dosCriterio.setOperacion("=");
            dosCriterio.setValor(null);
            lCriterio.add(dosCriterio);
          
         // List<Objact> EstadoTramite estadoTramiteEncontrado =  FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio).get(0);
       
       EstadoTramite estadoTramiteEncontrado = (EstadoTramite) FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio).get(0);
       
        if(estadoTramiteEncontrado==null){
            throw new exceptionRC("No se pudo registrar el cambio de estado");
        }
        if(estadoTramiteEncontrado.getFechaHoraBajaEstadoTramite() != null){
            throw new exceptionRC("Error. El estado al que desea transitar no se encuentra disponible.");
        }else{
            
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List <DTOCriterio> criterioList = new ArrayList<>();
        //busco tramite x nro
        DTOCriterio dto = new DTOCriterio();
        
        dto.setAtributo("nroTramite");
        dto.setOperacion("=");
        dto.setValor(nroTramite);
        
        criterioList.add(dto);
        
        Tramite tramiteEncontrado =(Tramite) FachadaPersistencia.getInstance().buscar("Tramite", criterioList).get(0);
        
        //le pido el estado tramite
        EstadoTramite estadoTramite=tramiteEncontrado.getEstadoTramite();
        int codET = estadoTramite.getCodEstadoTramite();
        
        List <TramiteEstado> tramitesEstados=tramiteEncontrado.getTramiteEstadoList();
        TramiteEstado tramiteEstadoMasReciente = tramitesEstados.stream()
                .max(Comparator.comparing(TramiteEstado::getFechaHoraDesdeTramiteEstado))
                .orElse(null);
        tramiteEstadoMasReciente.getFechaHoraHastaTramiteEstado();
        tramiteEstadoMasReciente.setFechaHoraHastaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        
        TramiteEstado tramiteEstadoNuevo = new TramiteEstado();
            tramiteEstadoNuevo.setNroLineaTramiteEstado((tramiteEstadoMasReciente.getNroLineaTramiteEstado())+1);
            tramiteEstadoNuevo.setFechaHoraDesdeTramiteEstado(tramiteEstadoMasReciente.getFechaHoraHastaTramiteEstado());
            tramiteEstadoNuevo.setFechaHoraHastaTramiteEstado(null);
            tramiteEstadoNuevo.setFechaHoraBajaTramiteEstado(null);
            tramiteEstadoNuevo.setObservacionTramiteEstado(tdto.getObservacion());
            tramiteEncontrado.addTramiteEstadoList(tramiteEstadoNuevo);
            

            
            tramiteEstadoNuevo.setEstadoTramite(estadoTramiteEncontrado);
            tramiteEncontrado.setEstadoTramite(estadoTramiteEncontrado);
            FachadaPersistencia.getInstance().guardar(tramiteEstadoNuevo);
            FachadaPersistencia.getInstance().guardar(tramiteEncontrado);
            
        Version versionAct = tramiteEncontrado.getVersion();
        List<TransicionEstado> lTransiciones =  versionAct.getTransicionEstadoList();
        for( TransicionEstado transicion : lTransiciones){
            int codTran = transicion.getEstadoActual().getCodEstadoTramite();
            int codETA = tramiteEncontrado.getEstadoTramite().getCodEstadoTramite();
            if ( codETA == codTran){
                List <EstadoTramite> destinosPosibles = transicion.getEstadosDestino();
                if(destinosPosibles.isEmpty()){
                    tramiteEncontrado.setFechaHoraFinTramite(new Timestamp(System.currentTimeMillis()));
                    System.out.println("El tramite ha finalizado");
                    FachadaPersistencia.getInstance().guardar(tramiteEncontrado);
                }
            }
            
            
        }
         
       
        FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    }
    
    
    
            
    public NuevoEstadoDTO nuevoEstado(int legajoConsultor,int nroTramite) throws exceptionRC{
        List<EstadoActualDTO> dtoEA=new ArrayList<>();
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramite);
            lCriterio.add(unCriterio);
        
        Tramite tramite = (Tramite) FachadaPersistencia.getInstance().buscar("Tramite", lCriterio).get(0);
        int legajoCA = tramite.getConsultor().getLegajoConsultor();
        
        if (legajoCA != legajoConsultor){
            throw new exceptionRC("El trámite al que desea acceder no pertenece al consultor");
            
        } else {
        
        EstadoTramite estadoTramiteActual=tramite.getEstadoTramite();
        int codEstadoTramite = estadoTramiteActual.getCodEstadoTramite();
        
        Version version = tramite.getVersion();
        List<TransicionEstado> transicionesEstado = version.getTransicionEstadoList();
        
        NuevoEstadoDTO estadoActualDTO = new NuevoEstadoDTO();
        estadoActualDTO.setNombreApellidoCliente(tramite.getCliente().getNombreApellidoCliente());
        estadoActualDTO.setNroTramite(tramite.getNroTramite());
        estadoActualDTO.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
        
        
        for(TransicionEstado transicionEstado : transicionesEstado){
            if(codEstadoTramite == transicionEstado.getEstadoActual().getCodEstadoTramite())
            {
            
                estadoActualDTO.setNombreEstadoTramiteActual(estadoTramiteActual.getNombreEstadoTramite());
                List<EstadoTramite> etDestinos = transicionEstado.getEstadosDestino();
                //CASO QUE LA LISTA ESTE VACIA
                if(etDestinos.isEmpty()){
                    throw new exceptionRC("No hay probles transiciones. El Trámite se encuentra finalizado.");
                }
                for (EstadoTramite etDestino : etDestinos) {
                    if(etDestino.getFechaHoraBajaEstadoTramite() == null){
                        
                    
                        // Crear DTODestinosPosibles con nombreEstadoTramiteDestino y codEstadoTramite
                        DestinosPosiblesDTO destinosDTO = new DestinosPosiblesDTO();
                        destinosDTO.setNombreEstadoTramiteDestino(etDestino.getNombreEstadoTramite());
                        destinosDTO.setCodEstadoTramite(etDestino.getCodEstadoTramite());

                        // Añadir DTODestinosPosibles a DTOEstadoActual

                        estadoActualDTO.addDestinosPosiblesList(destinosDTO);
                    }
                }
            }

        }
        
        return estadoActualDTO;}
               
    }
    
        public TramitesConsultorDTO buscarTramiteConsultor(int nroTramiteFiltro){
         List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(nroTramiteFiltro>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        
        //List objetoList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        Tramite tramite = (Tramite) FachadaPersistencia.getInstance().buscar("Tramite", lCriterio).get(0);
        
        TramitesConsultorDTO tramitesConsultorResultado = new TramitesConsultorDTO();
        
            tramitesConsultorResultado.setNroTramite(tramite.getNroTramite());
            tramitesConsultorResultado.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
            tramitesConsultorResultado.setFechaHoraCargaTramite(tramite.getFechaHoraCargaTramite());
            tramitesConsultorResultado.setNombreEstadoTramite(tramite.getEstadoTramite().getNombreEstadoTramite());
            tramitesConsultorResultado.setCuitCliente(tramite.getCliente().getCuitCliente());
    
            
        
        return tramitesConsultorResultado;
    }
           
    
        
        
        
    //PARA DE CASO PRUEBA
        
        public NuevoEstadoDTO nuevoEstadoCP(int nroTramite) throws exceptionRC{
        List<EstadoActualDTO> dtoEA=new ArrayList<>();
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(nroTramite>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramite);
            lCriterio.add(unCriterio);
        }
        Tramite tramite = (Tramite) FachadaPersistencia.getInstance().buscar("Tramite", lCriterio).get(0);
        
        EstadoTramite estadoTramiteActual=tramite.getEstadoTramite();
        int codEstadoTramite = estadoTramiteActual.getCodEstadoTramite();
        
        Version version = tramite.getVersion();
        List<TransicionEstado> transicionesEstado = version.getTransicionEstadoList();
        
        NuevoEstadoDTO estadoActualDTO = new NuevoEstadoDTO();
        estadoActualDTO.setNombreApellidoCliente(tramite.getCliente().getNombreApellidoCliente());
        estadoActualDTO.setNroTramite(tramite.getNroTramite());
        estadoActualDTO.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
        //EstadoTramite estadoTramiteActual = tramite.getEstadoTramite();
        
        for(TransicionEstado transicionEstado : transicionesEstado){
            if(codEstadoTramite == transicionEstado.getEstadoActual().getCodEstadoTramite())
            {
            
                estadoActualDTO.setNombreEstadoTramiteActual(estadoTramiteActual.getNombreEstadoTramite());
                List<EstadoTramite> etDestinos = transicionEstado.getEstadosDestino();
                //CASO QUE LA LISTA ESTE VACIA
                if(etDestinos.size()<=0){
                    throw new exceptionRC("No hay probles transiciones");
                }
                for (EstadoTramite etDestino : etDestinos) {
                        // Crear DTODestinosPosibles con nombreEstadoTramiteDestino y codEstadoTramite
                        DestinosPosiblesDTO destinosDTO = new DestinosPosiblesDTO();
                        destinosDTO.setNombreEstadoTramiteDestino(etDestino.getNombreEstadoTramite());
                        destinosDTO.setCodEstadoTramite(etDestino.getCodEstadoTramite());

                        // Añadir DTODestinosPosibles a DTOEstadoActual

                        estadoActualDTO.addDestinosPosiblesList(destinosDTO);
                }
            }

        }
        
        return estadoActualDTO;
    }
        
        
        

    
}
