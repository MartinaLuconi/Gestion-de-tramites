package SolicitarTramiteWeb.exceptions;

import ObtenerConsultor.exceptions.ExpertoObtenerConsultor;
import SolicitarTramiteWeb.dtos.DTOCategoriaTramite;
import SolicitarTramiteWeb.dtos.DTOCliente;
import SolicitarTramiteWeb.dtos.DTOConfirmacion;
import SolicitarTramiteWeb.dtos.DTOConfirmacion2;
import SolicitarTramiteWeb.dtos.DTODocumentacion;
import SolicitarTramiteWeb.dtos.DTOTipoTramiteWeb;
import SolicitarTramiteWeb.dtos.DTOTramiteWeb;
import entidades.CategoriaTramite;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

public class ExpertoSolicitarTramiteWeb {
    
    Tramite tramite = null; // lo que veiamos en secuencia linea recta, linea punteada
     
    // Método para obtener un cliente por su CUIT
    public DTOCliente obtenerCliente(String cuitCliente) {
        
        FachadaPersistencia.getInstance().iniciarTransaccion();
        DTOCliente dtoc=new DTOCliente();
        //buscar('Estado','codigo='+nuevoEstadoDTO.getCodigo() and fechaBaja=null' )
        
        List<DTOCriterio> criterioList = new ArrayList<>();
        
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("cuitCliente");
        dto.setOperacion("like");
        dto.setValor(cuitCliente);
        criterioList.add(dto);
        
        DTOCriterio dto2  = new DTOCriterio();

        dto2.setAtributo("fechaHoraBajaCliente");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);
        
        List lCliente=FachadaPersistencia.getInstance().buscar("Cliente", criterioList);
        System.out.println("Tamaño:"+lCliente.size());  
        if(lCliente.size()> 0)
        {
            
            Cliente c=(Cliente) lCliente.get(0);
            dtoc.setCuitCliente(cuitCliente);
            dtoc.setNombreApellidoCliente(c.getNombreApellidoCliente());
            dtoc.setDireccionCliente(c.getDireccionCliente());
            dtoc.setMailCliente(c.getMailCliente());
        }
        /*
        FachadaPersistencia.getInstance().guardar(cliente);
        FachadaPersistencia.getInstance().finalizarTransaccion();
        }*/
        return dtoc;
       
    }
    
        public List<DTOCategoriaTramite> obtenerCategoriaTramite() {
            
                   
        List<DTOCriterio> criterio = new ArrayList<>();
        
        DTOCriterio dto = new DTOCriterio();
        
        dto.setAtributo("fechaHoraBajaCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(null);
        criterio.add(dto);
            
            
        
        List objetoList = FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterio);
        
        List<DTOCategoriaTramite> categoria = new ArrayList<>();
        for (Object x : objetoList) {
            CategoriaTramite categ = (CategoriaTramite) x;
            DTOCategoriaTramite DTOcategoriatramite = new DTOCategoriaTramite();
            DTOcategoriatramite.setCodCategoriaTramite(categ.getCodCategoriaTramite());
            DTOcategoriatramite.setNombreCategoriaTramite(categ.getNombreCategoriaTramite());
            categoria.add(DTOcategoriatramite);
        
        }
        return categoria;
    }
        
        public List<DTOTipoTramiteWeb> elegirCategoriaTramite(int codTipoTramite) {
        
        List<DTOTipoTramiteWeb> tiposDeTramiteList = new ArrayList<>();

    try {
        // Iniciar transacción
        FachadaPersistencia.getInstance().iniciarTransaccion();

        // Crear criterio para buscar la categoría de trámite por nombre
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dtoCategoria = new DTOCriterio();
        dtoCategoria.setAtributo("codCategoriaTramite");
        dtoCategoria.setOperacion("=");
        dtoCategoria.setValor(codTipoTramite);
        criterioList.add(dtoCategoria);

        // Buscar la categoría de trámite
        List categoriaList = FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList);
        if (!categoriaList.isEmpty()) {
            CategoriaTramite CategoriaTramite = (CategoriaTramite) categoriaList.get(0);

            // Crear criterio para buscar tipos de trámite asociados a la categoría y sin fecha de baja
            List<DTOCriterio> TipoTramite = new ArrayList<>();
            DTOCriterio dtoCriterioFechaBaja = new DTOCriterio();
            dtoCriterioFechaBaja.setAtributo("fechaHoraBajaTipoTramite");
            dtoCriterioFechaBaja.setOperacion("=");
            dtoCriterioFechaBaja.setValor(null);
            TipoTramite.add(dtoCriterioFechaBaja);

            DTOCriterio dtoCriterioCategoriaTramite = new DTOCriterio();
            dtoCriterioCategoriaTramite.setAtributo("categoriaTramite");
            dtoCriterioCategoriaTramite.setOperacion("=");
            dtoCriterioCategoriaTramite.setValor(CategoriaTramite);
            TipoTramite.add(dtoCriterioCategoriaTramite);

            // Buscar los tipos de trámite asociados
            List tipoTramiteList = FachadaPersistencia.getInstance().buscar("TipoTramite", TipoTramite);
            for (Object obj : tipoTramiteList) {
                TipoTramite tipoTramite = (TipoTramite) obj;
                DTOTipoTramiteWeb dtoTipoTramite = new DTOTipoTramiteWeb();
                dtoTipoTramite.setNombreTipoTramite(tipoTramite.getNombreTipoTramite());
                dtoTipoTramite.setCodTipoTramite(tipoTramite.getCodTipoTramite());
                dtoTipoTramite.setDescripcionTipoTramiteWeb(tipoTramite.getDescripcionTipoTramite());
                
                tiposDeTramiteList.add(dtoTipoTramite);
            }
        }


    } catch (Exception e) {
        System.err.println("Error al obtener tipos de trámite: " + e.getMessage());
        // Revertir transacción en caso de error
    }

    return tiposDeTramiteList;
}

    public DTOTramiteWeb elegirTipoTramite(int codTipoTramite, String cuitCliente) throws SolicitarTramiteWebException {

        TipoTramite tipoTramiteRelacionado = new TipoTramite();

        //creamos nuevo tramite
        Tramite nuevoTramite = new Tramite();
        nuevoTramite.setFechaHoraCargaTramite(new Timestamp(System.currentTimeMillis()));
        try {
            // Iniciar transacción
            FachadaPersistencia.getInstance().iniciarTransaccion();

            // Buscar el tipo de trámite por nombre
            List<DTOCriterio> criterioListTipoTramite = new ArrayList<>();
            DTOCriterio dtoCriterioTipoTramite = new DTOCriterio();
            dtoCriterioTipoTramite.setAtributo("codTipoTramite");
            dtoCriterioTipoTramite.setOperacion("=");
            dtoCriterioTipoTramite.setValor(codTipoTramite);
            criterioListTipoTramite.add(dtoCriterioTipoTramite);

            // Asignar TipoTramite
            List tipoTramiteList = FachadaPersistencia.getInstance().buscar("TipoTramite", criterioListTipoTramite);
            if (tipoTramiteList != null && !tipoTramiteList.isEmpty()) {
                tipoTramiteRelacionado = (TipoTramite) tipoTramiteList.get(0);
                nuevoTramite.setTipoTramite(tipoTramiteRelacionado);

            }
        } catch (Exception e) {
            System.out.println("Error: No se encontró un Tipo de Tramite con los criterios especificados." + codTipoTramite);

        }

        // Obtener el número máximo de días de entrega
        if (tipoTramiteRelacionado != null) {
            int maxDiaEntrega = tipoTramiteRelacionado.getMaxDiaEntrega();

            // Asignar fecha vencimiento documentacion    
            Calendar fechaVencimiento = Calendar.getInstance();
            fechaVencimiento.add(Calendar.DATE, maxDiaEntrega);
            Timestamp fechaHoraVencimientoDocumentacion = new Timestamp(fechaVencimiento.getTimeInMillis());
            nuevoTramite.setFechaHoraVencimientoDocumentacion(fechaHoraVencimientoDocumentacion);
        }
        // Buscar la versión activa
        List<DTOCriterio> criterioListVersion = new ArrayList<>();
        DTOCriterio dtoCriterioFechaVersion = new DTOCriterio();
        dtoCriterioFechaVersion.setAtributo("fechaHoraDesdeVersion");
        dtoCriterioFechaVersion.setOperacion("<=");
        dtoCriterioFechaVersion.setValor(new Timestamp(System.currentTimeMillis())); // Fecha actual
        criterioListVersion.add(dtoCriterioFechaVersion);

        DTOCriterio dtoCriterioHastaVersion = new DTOCriterio();
        dtoCriterioHastaVersion.setAtributo("fechaHoraHastaVersion");
        dtoCriterioHastaVersion.setOperacion(">=");
        dtoCriterioHastaVersion.setValor(new Timestamp(System.currentTimeMillis())); // Fecha actual
        criterioListVersion.add(dtoCriterioHastaVersion);

        DTOCriterio dtoCriterioBajaVersion = new DTOCriterio();
        dtoCriterioBajaVersion.setAtributo("fechaHoraBajaVersion");
        dtoCriterioBajaVersion.setOperacion("=");
        dtoCriterioBajaVersion.setValor(null); // Versiones activas
        criterioListVersion.add(dtoCriterioBajaVersion);

        // Asignar versión
        List versionList = FachadaPersistencia.getInstance().buscar("Version", criterioListVersion);
        if (!versionList.isEmpty()) {
            Version version = (Version) versionList.get(0);
            nuevoTramite.setVersion(version);
        } else {
            System.out.println("No se encontró versión activa.");
        }

        // Buscar el cliente por CUIT   
        List<DTOCriterio> criterioListCliente = new ArrayList<>();
        DTOCriterio dtoCriterioCliente = new DTOCriterio();
        dtoCriterioCliente.setAtributo("cuitCliente");
        dtoCriterioCliente.setOperacion("=");
        dtoCriterioCliente.setValor(cuitCliente);
        criterioListCliente.add(dtoCriterioCliente);

        DTOCriterio dtoCriterioBajaCliente = new DTOCriterio();
        dtoCriterioBajaCliente.setAtributo("fechaHoraBajaCliente");
        dtoCriterioBajaCliente.setOperacion("=");
        dtoCriterioBajaCliente.setValor(null); // Cliente activo
        criterioListCliente.add(dtoCriterioBajaCliente);

        List clienteList = FachadaPersistencia.getInstance().buscar("Cliente", criterioListCliente);
        if (!clienteList.isEmpty()) {
            Cliente cliente = (Cliente) clienteList.get(0);
            nuevoTramite.setCliente(cliente);
        } else {
            System.out.println("No se encontró cliente.");
        }

        // Buscar el Tramite
        List<DTOCriterio> criteriotramite = new ArrayList<>();
        
        // Buscar los trámite asociados
        List TramiteList = FachadaPersistencia.getInstance().buscar("Tramite", criteriotramite);
        int nro = 0;
        nro = TramiteList.size() + 1;
        
        
        nuevoTramite.setNroTramite(nro);
        
        // Buscar el Estado por iniciado
        List<DTOCriterio> criterioestado = new ArrayList<>();

        DTOCriterio dtoestado = new DTOCriterio();

        dtoestado.setAtributo("codEstadoTramite");
        dtoestado.setOperacion("=");
        dtoestado.setValor(1);
        criterioestado.add(dtoestado);

        List estadotramiteList = FachadaPersistencia.getInstance().buscar("EstadoTramite", criterioestado);
        if (!estadotramiteList.isEmpty()) {
            EstadoTramite estadotramite = (EstadoTramite) estadotramiteList.get(0);
            nuevoTramite.setEstadoTramite(estadotramite);
            TramiteEstado nuevoestadotramite = new TramiteEstado();
            nuevoestadotramite.setNroLineaTramiteEstado(1);
            nuevoestadotramite.setFechaHoraDesdeTramiteEstado(new Timestamp(System.currentTimeMillis()));
            nuevoestadotramite.setEstadoTramite(estadotramite);
       
            nuevoTramite.addTramiteEstadoList(nuevoestadotramite);
            
        } else{
            throw new SolicitarTramiteWebException("no se recupero ningun estado tramite");
        }
        //documentacion
        int contador = 0;
        //Creamos instancia de TramiteDocumentacion y seteamos la Documentacion correspondiete al tipoTramite relacionado
        try {
            List<Documentacion> documentaciones = tipoTramiteRelacionado.getDocumentaciones();

            System.out.println("RECUPERE LA LISTA DE DOCUMENTACIONES");
            if (!documentaciones.isEmpty()) {
                contador = 0;
                //    System.out.println("cantidad de elementos en documnetaciones: "+documentaciones.size());
                for (Documentacion doc : documentaciones) {
                    TramiteDocumentacion td = new TramiteDocumentacion();
                    contador++;
                    td.setCodTramiteDocumentacion(contador);
                    System.out.println("contador= " + contador + " y codDocu= " + td.getCodTramiteDocumentacion());
                    td.setDocumentacion(doc);
                    nuevoTramite.addTramiteDocumentacionList(td);
                    System.out.println("tramitedocumentacion agregada ");
                }
            } else {
                throw new SolicitarTramiteWebException("El tipo de tramite no tiene documentos asignados");
            }

        } catch (Exception e) {
            System.out.println("se rompe en tramiteDocumentacion");

        }
        
        // Buscar la lista precio activa
        
        List<DTOCriterio> criterioListPrecio = new ArrayList<>();
        DTOCriterio dtoCriterioDesdeprecio = new DTOCriterio();
        dtoCriterioDesdeprecio.setAtributo("fechaHoraDesdeListaPrecio");
        dtoCriterioDesdeprecio.setOperacion("<=");
        dtoCriterioDesdeprecio.setValor(new Date()); // Fecha actual
        criterioListPrecio.add(dtoCriterioDesdeprecio);

        DTOCriterio dtoCriterioHastaPrecio = new DTOCriterio();
        dtoCriterioHastaPrecio.setAtributo("fechaHoraHastaListaPrecio");
        dtoCriterioHastaPrecio.setOperacion(">=");
        dtoCriterioHastaPrecio.setValor(new Date()); // Fecha actual
        criterioListPrecio.add(dtoCriterioHastaPrecio);
        
        List precioList = FachadaPersistencia.getInstance().buscar("ListaPrecio", criterioListPrecio);
        ListaPrecio listaprecio=null;
        if (!precioList.isEmpty()) {
            listaprecio = (ListaPrecio) precioList.get(0);
            
        } else {
            System.out.println("No se encontró lista de precios activa.");
        }
        
        for (DetallePrecioTipoTramite preciotiptra : listaprecio.getDetallePrecioTipoTramiteList()) {
               
                preciotiptra.getTipoTramite();
                if(codTipoTramite == preciotiptra.getTipoTramite().getCodTipoTramite())
                {
                    nuevoTramite.setPrecioTramite((float) preciotiptra.getPrecioTipoTramite());
                }
               }
        
        
//creamos un DTOTramiteWeb y le seteamos los datos
        DTOTramiteWeb  traweb = new DTOTramiteWeb();
                    

              traweb.setCodTipoTramite(nuevoTramite.getTipoTramite().getCodTipoTramite());
              traweb.setPrecioTramite(nuevoTramite.getPrecioTramite());
              traweb.setDescripcionTipoTramiteWeb(nuevoTramite.getTipoTramite().getDescripcionTipoTramiteWeb());
              traweb.setNombreTipoTramite(nuevoTramite.getTipoTramite().getNombreTipoTramite());
              
        
         //lista TramiteDocumentacion para traer el nombre
        
              List<TramiteDocumentacion> presentarDoc = nuevoTramite.getTramiteDocumentacionList();

              if(!presentarDoc.isEmpty()){
                for(TramiteDocumentacion t : presentarDoc){
                    DTODocumentacion dtoDoc = new DTODocumentacion();
                    Documentacion recuperada = t.getDocumentacion();
                    dtoDoc.setNombreDocumentacion(recuperada.getNombreDocumentacion());
                    System.out.println("nombre documentacion: " + dtoDoc.getNombreDocumentacion());
                    traweb.addDocumentacionList(dtoDoc);
                }
              }
              
              //Include CU - ObtenerConsultor
                ExpertoObtenerConsultor experto = new ExpertoObtenerConsultor();
                Consultor consultor = experto.obtenerConsultor();
                 if(consultor != null){
                     nuevoTramite.setConsultor(consultor);  // Asigna un consultor completo al trámite
                       FachadaPersistencia.getInstance().guardar(nuevoTramite); 
                        
                   System.out.println("consultor oid = " +consultor.getOID());    
                   
                    }else{
                        System.out.println("No se pudo asignar un consultor");
                    }
              
                    
             
                       
             
                    //TENES QUE GUARDAR EL TRAMITE
            
            this.tramite = nuevoTramite;    //Para no crear un tramite nuevo en el método confirmar
            System.out.println("TRAMITE GUARDADO ");
            System.out.println("tramite con oid " +nuevoTramite.getOID());
                 
            
        
            
            
            
       return traweb;
       
        
   }
 
 
          public DTOConfirmacion confirmar() {  
              
              
               TipoTramite tipoTramite = this.tramite.getTipoTramite(); //agarro tipo tramite con el get
            
                
            
                
            DTOConfirmacion confirmacion = new DTOConfirmacion();
            
            confirmacion.setNombreApellidoCliente(tramite.getCliente().getNombreApellidoCliente());
            confirmacion.setCuitCliente(tramite.getCliente().getCuitCliente());
            confirmacion.setDireccionCliente(tramite.getCliente().getDireccionCliente());
            confirmacion.setPrecioTramite(tramite.getPrecioTramite());
            confirmacion.setNombreTipoTramite(tipoTramite.getNombreTipoTramite());
            
            
            
                
            
                
               
        return confirmacion;
          }
 
          public DTOConfirmacion2 confirmar2() {
              
              DTOConfirmacion2 confirmacion2 = new DTOConfirmacion2();
              // Verificar si el consultor está asignado para evitar NullPointerException
            if (tramite.getConsultor() != null) {
                confirmacion2.setNombreApellidoConsultor(tramite.getConsultor().getNombreApellidoConsultor());
            } else {
                confirmacion2.setNombreApellidoConsultor("Consultor no asignado");
            }
              confirmacion2.setNroTramite(tramite.getNroTramite());
              confirmacion2.setFechaHoraVencimientoDocumentacion(tramite.getFechaHoraVencimientoDocumentacion());
       
              FachadaPersistencia.getInstance().finalizarTransaccion();
             
              return confirmacion2;
              
          }
 
      
        }
          
         
        
          
          


        
     
        
    

   
    




