package CargarTramite;


import CargarTramite.dtos.DTOClienteV;
import CargarTramite.dtos.DTODocumentacion;
import CargarTramite.dtos.DTOTipoTramiteV;
import CargarTramite.dtos.DTOTramiteV;
import CargarTramite.dtos.DTOVisualizarTramite;
import CargarTramite.exceptions.TramiteException;
import ObtenerConsultor.ExpertoObtenerConsultor;
import entidades.Cliente;
import entidades.Consultor;
import entidades.DetallePrecioTipoTramite;
import entidades.Documentacion;
import entidades.EstadoTramite;
import entidades.ListaPrecio;
import entidades.TipoTramite;
import entidades.Tramite;
import entidades.TramiteDocumentacion;
import entidades.TramiteEstado;
import entidades.Version;
import java.util.Date;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpertoCargarTramite {
    
    
    
public List<DTOTramiteV> mostrarTramites(Date fechaDesde, String cuitFiltro,
                int nroTramiteFiltro, String nombreEstadoTramiteFiltro, 
                int legajoConsultorFiltro, int codTipoTramiteFiltro){
        
    List<DTOCriterio> lCriterio = new ArrayList<DTOCriterio>(); 
    
    try {
        if (fechaDesde != null) {
            DTOCriterio unCriterio = crearCriterio("fechaHoraCargaTramite",">=",fechaDesde);
            lCriterio.add(unCriterio);
        }
        
        if ( cuitFiltro != null && !cuitFiltro.isEmpty()) {
            DTOCriterio unCriterio = crearCriterio("cliente.cuitCliente","like",cuitFiltro);
            lCriterio.add(unCriterio);
        }

        if(nombreEstadoTramiteFiltro.trim().length() > 0) {
            DTOCriterio unCriterio = crearCriterio("estadoTramite.nombreEstadoTramite","like",nombreEstadoTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        
        if(legajoConsultorFiltro > 0) {
            DTOCriterio unCriterio = crearCriterio("consultor.legajoConsultor","=",legajoConsultorFiltro);
            lCriterio.add(unCriterio);
        }
        
        if(codTipoTramiteFiltro > 0) {
            
            DTOCriterio unCriterio = crearCriterio("tipoTramite.codTipoTramite","=",codTipoTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        
        if(nroTramiteFiltro > 0) {
            DTOCriterio unCriterio = crearCriterio("nroTramite","=",nroTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        
        List objetoList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        lCriterio.clear();
        List<DTOTramiteV> tramitesResultado = new ArrayList<>();

        for (Object x : objetoList) {
            Tramite tramite = (Tramite) x;
            DTOTramiteV tramiteDTO = new DTOTramiteV();
            tramiteDTO.setNroTramite(tramite.getNroTramite());
            tramiteDTO.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
            tramiteDTO.setNombreEstadoTramite(tramite.getEstadoTramite().getNombreEstadoTramite());
            tramiteDTO.setNombreConsultor(tramite.getConsultor().getNombreApellidoConsultor());
            tramiteDTO.setNombreCliente(tramite.getCliente().getNombreApellidoCliente());
            tramiteDTO.setCuitCliente(tramite.getCliente().getCuitCliente());
            tramiteDTO.setFechaHoraCargaTramite(tramite.getFechaHoraCargaTramite());
            tramiteDTO.setFechaHoraFinTramite(tramite.getFechaHoraFinTramite());
            tramiteDTO.setFechaHoraFinEntregaDoc(tramite.getFechaHoraFinEntregaDocumentacion());
            tramiteDTO.setFechaHoraBajaTramite(tramite.getFechaHoraBajaTramite());
            tramitesResultado.add(tramiteDTO);
        }
        
        return tramitesResultado;

    } catch (Exception e) {
        System.err.println("Ocurrió un error: " + e.getMessage());
        e.printStackTrace();
        return new ArrayList<>();  // Devolvemos una lista vacía si ocurre un error
    }
}



    
     public DTOClienteV obtenerCliente(String cuit) throws TramiteException{
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
            if(cuit != null && !cuit.isEmpty())
            {
                DTOCriterio unCriterio = crearCriterio("cuitCliente", "=", cuit);
                lCriterio.add(unCriterio);
            }else{
                throw new TramiteException("Error: Deberá dar de alta al cliente, si desea continuar.");
            }
            
            List objetoList = FachadaPersistencia.getInstance().buscar("Cliente", lCriterio);
            Cliente clienteRecuperado = null;
            if (!objetoList.isEmpty()) {
                clienteRecuperado = (Cliente)objetoList.get(0); // Obtener el primer cliente
                if(clienteRecuperado.getFechaHoraBajaCliente() == null){
                    DTOClienteV clienteDTO = new DTOClienteV();
                    clienteDTO.setCUIT(clienteRecuperado.getCuitCliente());
                    clienteDTO.setNombreApellidoCliente(clienteRecuperado.getNombreApellidoCliente());
                    clienteDTO.setDireccion(clienteRecuperado.getDireccionCliente());
                return clienteDTO;
                }else{
                    throw new TramiteException("Error: Deberá dar de alta al cliente, si desea continuar.");
                }
            } else {
                throw new TramiteException("Error: Deberá dar de alta al cliente, si desea continuar.");
            }
        }
     
    
     
     public DTOTipoTramiteV obtenerTipoTramite(int codigoTT) throws TramiteException{
         List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
         
            if(codigoTT>0){
                DTOCriterio dto = crearCriterio("codTipoTramite", "=", codigoTT);
                lCriterio.add(dto);
            }else{
                throw new TramiteException("El codigo de tipo de tramite debe ser mayor a 0 ");
            }

            List<Object> objectList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);

            if(!objectList.isEmpty()){
                TipoTramite tt = (TipoTramite)objectList.get(0);
                if(tt.getFechaHoraBajaTipoTramite()== null){
                    DTOTipoTramiteV ttDTO = new DTOTipoTramiteV();
                    ttDTO.setCodigoTT(tt.getCodTipoTramite());
                    ttDTO.setNombreTipoTramite(tt.getNombreTipoTramite());
                return ttDTO;
                }else{
                    throw new TramiteException("Error: Deberá dar de alta al tipo de trámite, si desea continuar.");
                }
            }else{
                throw new TramiteException("Error: Deberá dar de alta al tipo de trámite, si desea continuar.");
            }
        }
     
     
     
     
    public void botonCargar(String cuit, int codigoTT) throws TramiteException, Exception {
    if (codigoTT > 0 && !cuit.isEmpty()) {
        List<DTOCriterio> lCriterio = new ArrayList<>();
        FachadaPersistencia fachada = FachadaPersistencia.getInstance();

        // Revisamos que no hayan tramites vigentes con ese cliente y codigoTT
        lCriterio.add(crearCriterio("cliente.cuitCliente", "like", cuit));
        lCriterio.add(crearCriterio("tipoTramite.codTipoTramite", "=", codigoTT));
        List<Object> objectList = fachada.buscar("Tramite", lCriterio);
        if (!objectList.isEmpty()) { 
            int mayorTramite = 0;
            for (Object o : objectList) {
                Tramite tramiteExistente = (Tramite) o;
                if(tramiteExistente.getNroTramite() > mayorTramite){
                    mayorTramite = tramiteExistente.getNroTramite();
                }
            }
            lCriterio.clear();
            objectList.clear();
            lCriterio.add(crearCriterio("nroTramite", "=", mayorTramite));
            objectList = fachada.buscar("Tramite", lCriterio);
                Tramite tramiteMayor = (Tramite)objectList.get(0);
                System.out.println("nroTramite " +tramiteMayor.getNroTramite());
                if (tramiteMayor.getFechaHoraFinTramite() != null && tramiteMayor.getFechaHoraBajaTramite() == null) {
                    crearTramite(cuit, codigoTT);
                }

                if (tramiteMayor.getFechaHoraBajaTramite() == null) {
                    throw new TramiteException("El cliente ya solicitó el tipo de trámite código:  " +codigoTT + ", el cual no ha sido anulado.");
                }else{
                    crearTramite(cuit, codigoTT);
                }    
        } else{
            crearTramite(cuit, codigoTT);
        }
            
        } else{
            throw new TramiteException("Datos de entrada inválidos, no es posible cargar el trámite.");
        }
        
    }


   public void crearTramite(String cuit, int codigoTT) throws TramiteException, Exception {
    if (codigoTT <= 0 || cuit.isEmpty()) {
        throw new TramiteException("Datos inválidos, no es posible cargar el trámite.");
    }
    
    FachadaPersistencia.getInstance().iniciarTransaccion();
    
    // Variables auxiliares
    List<DTOCriterio> lCriterio = new ArrayList<>();
    FachadaPersistencia fachada = FachadaPersistencia.getInstance();
    Timestamp fechaHoraActual = new Timestamp(System.currentTimeMillis());
    List<Object> objectList = new ArrayList<>();
    Date fechaDesde = new Date(0), mayorFechaDesde = new Date(0);
    int contador = 0;

    // Crear el Tramite
    Tramite tramite = new Tramite();
    tramite.setFechaHoraCargaTramite(fechaHoraActual);

    // Asignar TipoTramite
    lCriterio.add(crearCriterio("codTipoTramite", "=", codigoTT));
    objectList = fachada.buscar("TipoTramite", lCriterio);
    if (!objectList.isEmpty()) {
        TipoTramite tt = (TipoTramite) objectList.get(0);
        tramite.setTipoTramite(tt);

        // Asignar fecha vencimiento documentacion
        int maxDiaEntrega = tt.getMaxDiaEntrega();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaHoraActual);
        calendar.add(Calendar.DAY_OF_MONTH, maxDiaEntrega);
        tramite.setFechaHoraVencimientoDocumentacion(new Timestamp(calendar.getTimeInMillis()));

        // Crear TramiteDocumentacion
        List<Documentacion> documentaciones = tt.getDocumentaciones();
        if (!documentaciones.isEmpty()) {
            contador = 0;
            for (Documentacion doc : documentaciones) {
                TramiteDocumentacion td = new TramiteDocumentacion();
                contador++;
                td.setCodTramiteDocumentacion(contador);
                td.setDocumentacion(doc);
                tramite.addTramiteDocumentacionList(td);
            }
        }else{
            throw new TramiteException("El Tipo de Trámite solicitado no presenta documentación asociada.");
        }

        // Asignar versión
        lCriterio.clear();
        lCriterio.add(crearCriterio("fechaHoraDesdeVersion", "<=", fechaHoraActual));
        lCriterio.add(crearCriterio("fechaHoraHastaVersion", ">=", fechaHoraActual));
        lCriterio.add(crearCriterio("fechaHoraBajaVersion", "=", null));
        objectList = fachada.buscar("Version", lCriterio);
        if (!objectList.isEmpty()) {
            Version mayorVersion=null;
            Date mayorFecha = new Date(0);
            boolean banderaVersion = false;
            for(Object o : objectList){
                Version version = (Version)o;
                if (codigoTT == version.getTipoTramite().getCodTipoTramite()){
                    if(version.getFechaHoraDesdeVersion().after(mayorFecha)){
                        mayorFecha = version.getFechaHoraDesdeVersion();
                        mayorVersion = version;
                        tramite.setVersion(mayorVersion);
                        banderaVersion = true;
                    }
                }
            }
            if(banderaVersion == false){
                throw new TramiteException("El Tipo de Trámite solicitado no está relacionado a ninguna versión.");
            }
        } else {
            throw new TramiteException("No se encontró una lista de versiones");
        }
    } else {
        throw new TramiteException("No se encontró ningún Tipo de Trámite con código = " + codigoTT);
    }

    // Asignar Cliente
    lCriterio.clear();
    lCriterio.add(crearCriterio("cuitCliente", "=", cuit));
    objectList = fachada.buscar("Cliente", lCriterio);
    if (!objectList.isEmpty()) {
        Cliente cliente = (Cliente) objectList.get(0);
        tramite.setCliente(cliente);
    } else {
        throw new TramiteException("No se encontró ningún cliente con el cuit: " + cuit);
    }

    // Asignar nroTramite
    objectList = fachada.buscar("Tramite", null);
    tramite.setNroTramite(objectList.size() + 1);

    // Asignar Estado Tramite
    lCriterio.clear();
    lCriterio.add(crearCriterio("codEstadoTramite", "=", 1));
    objectList = fachada.buscar("EstadoTramite", lCriterio);
    if (!objectList.isEmpty()) {
        EstadoTramite estado = (EstadoTramite) objectList.get(0);
        System.out.println(estado.getNombreEstadoTramite());
        tramite.setEstadoTramite(estado);
        TramiteEstado tramiteE = new TramiteEstado();
        tramiteE.setNroLineaTramiteEstado(1);
        tramiteE.setFechaHoraDesdeTramiteEstado(fechaHoraActual);
        tramiteE.setEstadoTramite(estado);
        tramite.addTramiteEstadoList(tramiteE);
    } else {
        throw new TramiteException("No se encontró ningún estado de trámite con nombre 'Iniciado'");
    }

    // Buscar lista precio y asignar precio al tramite
    lCriterio.clear();
    lCriterio.add(crearCriterio("fechaHoraDesdeListaPrecio", "<=", fechaHoraActual));
    lCriterio.add(crearCriterio("fechaHoraHastaListaPrecio", ">=", fechaHoraActual));
    lCriterio.add(crearCriterio("fechaHoraBajaListaPrecio", "=", null));

    objectList = fachada.buscar("ListaPrecio", lCriterio);

    if (!objectList.isEmpty()) {
        ListaPrecio mayorLista = null;
        int mayorCodigo = 0;

        // Selección de la lista con el mayor código
        for (Object o : objectList) {
            ListaPrecio lista = (ListaPrecio) o;
            int codigoLista = lista.getCodListaPrecio();
            if (codigoLista > mayorCodigo) {
                mayorCodigo = codigoLista;
                mayorLista = lista;
            }
        }

        // Verificar si se encontró la lista mayor
        if (mayorLista == null) {
            throw new TramiteException("No se encontró una lista de precios válida.");
        }

        // Buscar el precio del tipo de trámite en la lista seleccionada
        for (DetallePrecioTipoTramite d : mayorLista.getDetallePrecioTipoTramiteList()) {
            if (codigoTT == d.getTipoTramite().getCodTipoTramite()) {
                tramite.setPrecioTramite((float) d.getPrecioTipoTramite());
                break;
            }else{
                throw new TramiteException("El Tipo de Trámite no está vinculado a la lista de precios vigente.");
            }
        }
        
    } else {
        throw new TramiteException("No se encontró ninguna lista de precios vigente.");
    }


    // Asignar Consultor
    ExpertoObtenerConsultor experto = new ExpertoObtenerConsultor();
    Consultor consultor = experto.obtenerConsultor();
    if(consultor != null){
        tramite.setConsultor(consultor);
    }else{
        throw new TramiteException("No se pudo asignar un consultor");
    }
    // Guardar Tramite
    fachada.guardar(tramite);
    fachada.finalizarTransaccion();
    
}
      
   
     
     public DTOVisualizarTramite visualizarTramite(int nroTramite) throws Exception{
         
         DTOVisualizarTramite dtoV = new DTOVisualizarTramite();
         DTOCriterio criterio = new DTOCriterio();  
         List<DTOCriterio> lCriterio = new ArrayList<>();
         
         if(nroTramite>0){
             criterio.setAtributo("nroTramite");
             criterio.setOperacion("=");
             criterio.setValor(nroTramite);
             lCriterio.add(criterio);
         }
         
         List<Object> objectList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        
         try{
         if(!objectList.isEmpty()){
            Tramite tramite = (Tramite)objectList.get(0);
            
            dtoV.setNroTramite(tramite.getNroTramite());
            dtoV.setFechaHoraCargaTramite(tramite.getFechaHoraCargaTramite());
            dtoV.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
            dtoV.setNombreCliente(tramite.getCliente().getNombreApellidoCliente());
            dtoV.setCuitCliente(tramite.getCliente().getCuitCliente());
            dtoV.setDireccion(tramite.getCliente().getDireccionCliente());
            dtoV.setNombreApellidoConsultor(tramite.getConsultor().getNombreApellidoConsultor());
            dtoV.setPrecioTramite(tramite.getPrecioTramite());
            dtoV.setNombreEstadoTramite(tramite.getEstadoTramite().getNombreEstadoTramite());
            //para anular el boton completar documentacion
            dtoV.setFechaHoraBajaTramite(tramite.getFechaHoraBajaTramite());
            dtoV.setFechaHoraFinTramite(tramite.getFechaHoraFinTramite());
            
            dtoV.setFechaHoraFinEntregaDoc(tramite.getFechaHoraFinEntregaDocumentacion());
            
            List<TramiteDocumentacion> presentarDoc = tramite.getTramiteDocumentacionList();
            
            if(!presentarDoc.isEmpty()){
                for(TramiteDocumentacion t : presentarDoc){
                    DTODocumentacion dtoDoc = new DTODocumentacion();
                    Documentacion recuperada = t.getDocumentacion();
                    dtoDoc.setNombreDocumentacion(recuperada.getNombreDocumentacion());
                    dtoDoc.setFechaHoraEntrega(t.getFechaHoraEntrega());
                    dtoV.addDTODocumentacion(dtoDoc);
                    }
                }   
            }
        }catch(Exception e){
                throw new Exception("El tramite no presenta ninguna documentacion asociada",e);
            }
         return dtoV;
    }
     
     

    public void anularTramite(int nroTramite) throws TramiteException {
    FachadaPersistencia.getInstance().iniciarTransaccion(); // Iniciar transacción
    try {
        List<DTOCriterio> criterioList = new ArrayList<>();
        
        if (nroTramite > 0) {
            
            DTOCriterio dto = crearCriterio("nroTramite", "=", nroTramite);
            criterioList.add(dto);
            
            DTOCriterio dto2 = crearCriterio("fechaHoraBajaTramite", "=", null);
            criterioList.add(dto2);

          
            List<Tramite> resultado = (List) FachadaPersistencia.getInstance().buscar("Tramite", criterioList);
            
            if (!resultado.isEmpty()) {
                Tramite tramiteEncontrado = (Tramite) resultado.get(0);
                int codigoEstado = tramiteEncontrado.getEstadoTramite().getCodEstadoTramite();
                
                if (codigoEstado == 1) {
                    // Dar de baja el trámite
                    tramiteEncontrado.setFechaHoraBajaTramite(new Timestamp(System.currentTimeMillis()));
                    
                    DTOCriterio dto3 = crearCriterio("codEstadoTramite", "=", 2);
                    criterioList.clear();
                    criterioList.add(dto3);
                    List<Object> objectList = FachadaPersistencia.getInstance().buscar("EstadoTramite", criterioList);
                    if(!objectList.isEmpty()){
                        EstadoTramite estadoRecu = (EstadoTramite)objectList.get(0);
                        tramiteEncontrado.setEstadoTramite(estadoRecu);
                    }else{
                        throw new TramiteException("No se pudo encontrar el estado Anulado. ");
                    }
                    FachadaPersistencia.getInstance().guardar(tramiteEncontrado);
                } else {
                    throw new TramiteException("No es posible anular el trámite n°:" + nroTramite + " ya que no está en estado 'Iniciado'.");
                }
            } else {
                throw new TramiteException("El trámite n°:" + nroTramite + " ya fue dado de baja.");
            }
        } else {
            throw new TramiteException("El número de trámite debe ser mayor que 0.");
        }
    } catch (TramiteException e) {
        System.err.println("Error en la anulación del trámite: " + e.getMessage());
        throw e;
    } catch (Exception e) {
        System.err.println("Error inesperado: " + e.getMessage());
        throw new TramiteException("Ocurrió un error inesperado al anular el trámite.");
    } finally {
            FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    }
    
    
    public DTOCriterio crearCriterio(String atributo, String operacion, Object valor){
        
        DTOCriterio dto = new DTOCriterio();
            dto.setAtributo(atributo);
            dto.setOperacion(operacion);
            dto.setValor(valor);
        return dto;
        
    }
}
