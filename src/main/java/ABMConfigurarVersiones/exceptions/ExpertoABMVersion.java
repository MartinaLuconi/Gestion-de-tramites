 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.exceptions;

import ABMConfigurarVersiones.dtos.DTOCodDestino;
import ABMConfigurarVersiones.dtos.DTOEstadoActualInput;
import ABMConfigurarVersiones.dtos.DTOEstadoActualOutput;
import ABMConfigurarVersiones.dtos.DTOEstadoDestinoInput;
import ABMConfigurarVersiones.dtos.DTOEstadoDestinoOutput;
import ABMConfigurarVersiones.dtos.DTOEstadoPosible;
import ABMConfigurarVersiones.dtos.DTOVersionHistorial;
import ABMConfigurarVersiones.dtos.DTOVersionInput;
import ABMConfigurarVersiones.dtos.DTOVersionOutput;
import ABMConfigurarVersiones.dtos.DTOVersionesDeTipo;
import ABMConfigurarVersiones.dtos.DTOVersionyEstados;
import entidades.EstadoTramite;
import entidades.TipoTramite;
import entidades.TransicionEstado;
import entidades.Version;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author julie
 */
public class ExpertoABMVersion {
    
    
    public List<DTOVersionesDeTipo> listarTablaVersionesDeTipos(int codtt, String nombrett){
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(codtt>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codtt);
            lCriterio.add(unCriterio);
        }
        if(nombrett != null && nombrett.trim().isEmpty())
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nombreTipoTramite");
            unCriterio.setOperacion("like");
            unCriterio.setValor("%"+nombrett+"%");
            lCriterio.add(unCriterio);
        }
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);

        List<DTOVersionesDeTipo> dtoLista = new ArrayList();
        
        if (objetoList.size()>0){
            for (Object x : objetoList) {
                    TipoTramite tt = (TipoTramite) x;
                    DTOVersionesDeTipo tipotramiteyV = new DTOVersionesDeTipo();
                    tipotramiteyV.setCodTipoTramite(tt.getCodTipoTramite());
                    tipotramiteyV.setNombreTipoTramite(tt.getNombreTipoTramite());
                    tipotramiteyV.setFechaHoraBajaTipoTramite(tt.getFechaHoraBajaTipoTramite());

                    List<DTOCriterio> lCriterio2 =new ArrayList<DTOCriterio>();
                    DTOCriterio unCriterio=new DTOCriterio();
                    unCriterio.setAtributo("tipoTramite");
                    unCriterio.setOperacion("=");
                    unCriterio.setValor(tt);
                    lCriterio2.add(unCriterio);
                    DTOCriterio unCriterio2=new DTOCriterio();
                    unCriterio2.setAtributo("fechaHoraBajaVersion");
                    unCriterio2.setOperacion("=");
                    unCriterio2.setValor(null);
                    lCriterio2.add(unCriterio2);
                    List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio2);

                    //Selecciono la de mayor fecha desde
                    List<Version> listversion = (List<Version>) objetoList2;
                    Version ultVersion=null;
                    for(int i=0;i<listversion.size();i++)
                    {
                        if(ultVersion==null)
                        {
                            ultVersion=listversion.get(i);
                        }
                        else
                        {
                            if(ultVersion.getFechaHoraDesdeVersion().compareTo(listversion.get(i).getFechaHoraDesdeVersion())==0){
                                if(ultVersion.getCodVersion()< listversion.get(i).getCodVersion()){
                                    ultVersion= listversion.get(i);
                                }
                            }
                            if(ultVersion.getFechaHoraDesdeVersion().compareTo(listversion.get(i).getFechaHoraDesdeVersion())<0)
                            {
                                ultVersion=listversion.get(i);
                            }
                            
                        }
                    }
                   // Optional<Version> ultimaVersion = listversion.stream().max(Comparator.comparing(Version::getFechaHoraDesdeVersion)); 
                   if(ultVersion==null)
                   {
                        tipotramiteyV.setCodVersion(0);
                        tipotramiteyV.setFechaHoraDesdeVersion(null);
                   }
                   else
                   {
                    tipotramiteyV.setCodVersion(ultVersion.getCodVersion());
                    tipotramiteyV.setFechaHoraDesdeVersion(ultVersion.getFechaHoraDesdeVersion());
                   }
                   dtoLista.add(tipotramiteyV);
            }
          }
        dtoLista.sort(Comparator.comparingInt(DTOVersionesDeTipo :: getCodTipoTramite).reversed());
        return dtoLista;
    }
    
    public DTOVersionOutput verVersion (int codTipoTramite, int codVersion) throws VersionException{ 
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>(); 
        DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codTipoTramite);
            lCriterio.add(unCriterio);
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio); 
        if(objetoList.size()<= 0)
        {
                throw new VersionException("El TipoTramite NO existe");
        }
        TipoTramite tt = (TipoTramite) objetoList.get(0);
        
        lCriterio.clear();
        DTOCriterio Criterio1=new DTOCriterio();
            Criterio1.setAtributo("tipoTramite");
            Criterio1.setOperacion("=");
            Criterio1.setValor(tt);
            lCriterio.add(Criterio1);
        DTOCriterio Criterio2=new DTOCriterio();
            Criterio2.setAtributo("codVersion");
            Criterio2.setOperacion("=");
            Criterio2.setValor(codVersion);
            lCriterio.add(Criterio2);
        List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio);
         if(objetoList2.size()<= 0)
        {
                throw new VersionException("La Version NO existe");
        }
        
        Version v = (Version) objetoList2.get(0);
        
        DTOVersionOutput dtoVersion = new DTOVersionOutput ();
        
        dtoVersion.setCodTipoTramite(tt.getCodTipoTramite());
        dtoVersion.setCodVersion(v.getCodVersion());
        dtoVersion.setFechaHoraDesdeVersion(v.getFechaHoraDesdeVersion());
        dtoVersion.setFechaHoraHastaVersion(v.getFechaHoraHastaVersion());
        dtoVersion.setDraw(v.getDraw());
        /*
        List<TransicionEstado> transiciones = v.getTransicionEstadoList();
        List<DTOEstadoActualOutput> listEA = new ArrayList();
        
        for(TransicionEstado x : transiciones){
        DTOEstadoActualOutput estadoActual = new DTOEstadoActualOutput();
        estadoActual.setNroTransicion(x.getNroTransicionEstado());
        EstadoTramite eA = x.getEstadoActual();
        estadoActual.setCodEstadoActual(eA.getCodEstadoTramite());
        estadoActual.setNombreEstadoTramite(eA.getNombreEstadoTramite());
        
        List<EstadoTramite> eDestinos = x.getEstadosDestino();
        for (EstadoTramite eD : eDestinos){
        DTOEstadoDestinoOutput estadoDestino = new DTOEstadoDestinoOutput();
        estadoDestino.setCodEstadoDestino(eD.getCodEstadoTramite());
        estadoDestino.setNombreEstadoTramite(eD.getNombreEstadoTramite());
        estadoActual.addEstadosDestino(estadoDestino);
        }
        listEA.add(estadoActual);
        }
        
        dtoVersion.setEstadosActuales(listEA);
        */
        return dtoVersion;
    }
    
    public DTOVersionyEstados buscarVersionEditar (int codTipoTramite) throws VersionException {
        
        FachadaPersistencia.getInstance().iniciarTransaccion();
        DTOVersionyEstados dtofinal = new DTOVersionyEstados();
        dtofinal.setVersion(null);
        
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>(); 
        DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codTipoTramite);
            lCriterio.add(unCriterio);
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio); 
        if(objetoList.size()<= 0)
        {
                throw new VersionException("El TipoTramite NO existe");
        }
        TipoTramite tt = (TipoTramite) objetoList.get(0);
        
        
        List objetoList2 = null;
        lCriterio.clear();
        DTOCriterio Criterio1=new DTOCriterio();
            Criterio1.setAtributo("tipoTramite");
            Criterio1.setOperacion("=");
            Criterio1.setValor(tt);
            lCriterio.add(Criterio1);
        DTOCriterio Criterio2=new DTOCriterio();
            Criterio2.setAtributo("fechaHoraBajaVersion");
            Criterio2.setOperacion("=");
            Criterio2.setValor(null);
            lCriterio.add(Criterio2);
        objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio);
        
        DTOVersionOutput dtoVersion = new DTOVersionOutput ();
         
         
         List<DTOEstadoActualOutput> listEA = new ArrayList(); 
         
        //Selecciono la de mayor fecha desde
        List<Version> listversion = (List<Version>) objetoList2;
        Version ultVersion=null;
            for(int i=0;i<listversion.size();i++){
                if(ultVersion==null){ ultVersion=listversion.get(i);}
                else{
                    if(ultVersion.getFechaHoraDesdeVersion().compareTo(listversion.get(i).getFechaHoraDesdeVersion())==0){
                                if(ultVersion.getCodVersion()< listversion.get(i).getCodVersion()){
                                    ultVersion= listversion.get(i);
                                }
                    }
                    if(ultVersion.getFechaHoraDesdeVersion().compareTo(listversion.get(i).getFechaHoraDesdeVersion())<0){
                        ultVersion=listversion.get(i);
                        }
                    }
                }
                if(ultVersion==null)
                   {
                    dtoVersion.setCodTipoTramite(tt.getCodTipoTramite());
                    dtoVersion.setCodVersion(0);
                    dtoVersion.setFechaHoraDesdeVersion(new Timestamp(System.currentTimeMillis()));
                    Timestamp fh = Timestamp.valueOf("2070-09-15 22:06:45.0");
                    dtoVersion.setFechaHoraHastaVersion(fh);
                    /*
                    DTOEstadoActualOutput estadoActual = new DTOEstadoActualOutput();
                      lCriterio.clear();
                        DTOCriterio Criterio3=new DTOCriterio();
                            Criterio3.setAtributo("nombreEstadoTramite");
                            Criterio3.setOperacion("=");
                            Criterio3.setValor("Iniciado");
                            lCriterio.add(Criterio3);       
                      List objetoList3 = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio);
                      DTOEstadoActualOutput estadoA = new DTOEstadoActualOutput();
                      EstadoTramite estadoA1 = (EstadoTramite) objetoList3;
                      estadoA.setCodEstadoActual(estadoA1.getCodEstadoTramite());
                      estadoA.setNombreEstadoTramite(estadoA1.getNombreEstadoTramite());
                      estadoA.setNroTransicion(1);
                      estadoA.setEstadosDestino(null);
                      listEA.add(estadoA);
                      dtoVersion.setEstadosActuales(listEA);
                    */
                      String draw = "no hay";//"[{\"codigo\":1,\"nombre\":\"Iniciado\",\"xpos\":80,\"ypos\":80s,\"destinos\":[]}]";
                      dtoVersion.setDraw(draw);
                      dtofinal.setVersion(dtoVersion);
                      
                   }
                else
                   { Version v = ultVersion;
      
                    dtoVersion.setCodTipoTramite(tt.getCodTipoTramite());
                    dtoVersion.setCodVersion(v.getCodVersion());
                    dtoVersion.setFechaHoraDesdeVersion(v.getFechaHoraDesdeVersion());
                    dtoVersion.setFechaHoraHastaVersion(v.getFechaHoraHastaVersion());
                    dtoVersion.setDraw(v.getDraw());
                    /*
                    List<TransicionEstado> transiciones = v.getTransicionEstadoList();        
                    for(TransicionEstado x : transiciones){
                    DTOEstadoActualOutput estadoActual = new DTOEstadoActualOutput();
                    estadoActual.setNroTransicion(x.getNroTransicionEstado());
                    EstadoTramite eA = x.getEstadoActual();
                    estadoActual.setCodEstadoActual(eA.getCodEstadoTramite());
                    estadoActual.setNombreEstadoTramite(eA.getNombreEstadoTramite());
        
                    List<EstadoTramite> eDestinos = x.getEstadosDestino();
                    for (EstadoTramite eD : eDestinos){
                    DTOEstadoDestinoOutput estadoDestino = new DTOEstadoDestinoOutput();
                    estadoDestino.setCodEstadoDestino(eD.getCodEstadoTramite());
                    estadoDestino.setNombreEstadoTramite(eD.getNombreEstadoTramite());
                    estadoActual.addEstadosDestino(estadoDestino);
                    }
                    listEA.add(estadoActual);
                    }
        
                    dtoVersion.setEstadosActuales(listEA);
                    */
                    dtofinal.setVersion(dtoVersion);
                }
         
        lCriterio=new ArrayList<DTOCriterio>();
        DTOCriterio Criterio=new DTOCriterio();
            Criterio.setAtributo("fechaHoraBajaEstadoTramite");
            Criterio.setOperacion("=");
            Criterio.setValor(null);
            lCriterio.add(Criterio);
        List objetoList3 = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio);
        if(objetoList3.size()<= 0)
        {
                throw new VersionException("No esxisten EstadosTramites vigentes");
        }
        
        for(Object x : objetoList3){
            EstadoTramite et = (EstadoTramite) x;
            DTOEstadoPosible ep = new DTOEstadoPosible();
            if(!(et.getCodEstadoTramite()== 2)){
                ep.setCodEstadoTramite(et.getCodEstadoTramite());
                ep.setNombreEstadoTramite(et.getNombreEstadoTramite());
                dtofinal.addEstadosPosibles(ep);
            }  
        }                   
        return dtofinal;
    }
    
    public void nuevaVersion (DTOVersionInput datosingresados)throws VersionException{
    
    FachadaPersistencia.getInstance().iniciarTransaccion();
        
    //Creo nueva version
        Version nuevaV = new Version();
        //cambiar
        nuevaV.setFechaHoraDesdeVersion(datosingresados.getFechaHoraDesdeVersion());
        nuevaV.setFechaHoraHastaVersion(datosingresados.getFechaHoraHastaVersion());
        nuevaV.setFechaHoraBajaVersion(null);
        nuevaV.setDraw(datosingresados.getDraw());
        //busco el TT
        int codTT = datosingresados.getCodTipoTramite();
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>(); 
        DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codTT);
            lCriterio.add(unCriterio);
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio); 
        if(objetoList.size()<= 0)
        {
                throw new VersionException("El TipoTramite NO existe");
        }
        TipoTramite tt = (TipoTramite) objetoList.get(0);
        nuevaV.setTipoTramite(tt);
        
        //codigo
        List<DTOCriterio> lCriterio2=new ArrayList<DTOCriterio>(); 
        DTOCriterio unCriterio4=new DTOCriterio();
            unCriterio4.setAtributo("tipoTramite");
            unCriterio4.setOperacion("=");
            unCriterio4.setValor(tt);
            lCriterio2.add(unCriterio4);
        List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio2);
        //verrrrr
        List<Version> listversion1 = (List<Version>) objetoList2;
        
                    int codigo;
                    if(listversion1.size()>0){
                        codigo=((Version)listversion1.get(0)).getCodVersion();
                        for(Object v : listversion1){
                                    if(codigo < ((Version)v).getCodVersion())
                                    {
                                         codigo = ((Version)v).getCodVersion();
                                    }
                        }
                        codigo= codigo;
                    }
                    else{
                        codigo = 0;
                    }
                    
        nuevaV.setCodVersion(codigo+1);
        
        //Estados y Transiciones
        List<DTOEstadoActualInput> EAingresados = datosingresados.getEstadosActuales();
        int k=0;
        List<EstadoTramite> l= new ArrayList<>();
        EstadoTramite eA=null;
        for (DTOEstadoActualInput x :EAingresados){
            k++;
            TransicionEstado Transicion = new TransicionEstado();
            Transicion.setNroTransicionEstado(k);
            //busco es estado actual 
            boolean encontrado=false;
            for(EstadoTramite unE : l)
            {
                if(unE.getCodEstadoTramite()==x.getCodEstadoActual())
                {
                    encontrado=true;
                    eA=unE;
                }
            }
            if(!encontrado)
            {
                lCriterio.clear();
                DTOCriterio Criterio=new DTOCriterio();
                Criterio.setAtributo("codEstadoTramite");
                Criterio.setOperacion("=");
                Criterio.setValor(x.getCodEstadoActual());
                lCriterio.add(Criterio);
                List objetoList3 = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio); 
                if(objetoList3.size()<= 0)
                {
                    throw new VersionException("El Estado Actual NO existe");
                }
                eA = (EstadoTramite) objetoList3.get(0);
                l.add(eA);
            }
            
            Transicion.setEstadoActual(eA);
            //busco los estados destino
            List<DTOEstadoDestinoInput> EDingresados = x.getEstadosDestino();
            
            EstadoTramite eD=null;
            for(DTOEstadoDestinoInput e : EDingresados){
                encontrado=false;
                for(EstadoTramite unE : l)
                {
                    if(unE.getCodEstadoTramite()==e.getCodEstadoDestino())
                    {
                        encontrado=true;
                        eD=unE;
                    }
                }
                if(!encontrado)
                {
                    lCriterio.clear();
                    DTOCriterio Criterio2=new DTOCriterio();
                    Criterio2.setAtributo("codEstadoTramite");
                    Criterio2.setOperacion("=");
                    Criterio2.setValor(e.getCodEstadoDestino());
                    lCriterio.add(Criterio2);
                    List objetoList4 = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio); 
                    if(objetoList4.size()<= 0)
                    {
                        throw new VersionException("El Estado Actual NO existe");
                    }
                    eD = (EstadoTramite) objetoList4.get(0);
                    l.add(eD);
                }
                
                Transicion.addEstadoDestino(eD);
            }
           
            nuevaV.addTransicionEstadoList(Transicion);
        }
//Epuezo con las validaciones de fechas
    
        //buscar ultima version de ese TipoTramite
          
        lCriterio.clear();
        DTOCriterio Criterio1=new DTOCriterio();
            Criterio1.setAtributo("tipoTramite");
            Criterio1.setOperacion("=");
            Criterio1.setValor(tt);
            lCriterio.add(Criterio1);
        DTOCriterio Criterio2=new DTOCriterio();
            Criterio2.setAtributo("fechaHoraBajaVersion");
            Criterio2.setOperacion("=");
            Criterio2.setValor(null);
            lCriterio.add(Criterio2);
        List objetoList5 = FachadaPersistencia.getInstance().buscar("Version", lCriterio); 
        //Selecciono la de mayor fecha desde
        List<Version> listversion = (List<Version>) objetoList5;
        Version ultVersion=null;
        for(int i=0;i<listversion.size();i++){
            if(ultVersion==null){ ultVersion=listversion.get(i);}
            else{
                if(ultVersion.getFechaHoraDesdeVersion().compareTo(listversion.get(i).getFechaHoraDesdeVersion())<0)
                {
                                ultVersion=listversion.get(i);
                }
            }
        }
               
        Version vultima = (Version)ultVersion;
       
        //Voy comparardo y tiro error fatal
        Timestamp FDNv = nuevaV.getFechaHoraDesdeVersion();
        
        if (vultima!=null){
        Timestamp FDUv =vultima.getFechaHoraDesdeVersion();
            if(FDNv.before(FDUv)){
                throw new VersionException("Erro la version nueva esta antes que la última version. Existen versiones futuras");
                //FachadaPersistencia.getInstance().finalizarTransaccion();            
            }
            Timestamp FHUv = vultima.getFechaHoraHastaVersion();
            if(FDNv.after(FHUv)){
                throw new VersionException("Erro la version nueva esta despues de la última version. No puede haber huecos entre versiones");
                //FachadaPersistencia.getInstance().finalizarTransaccion();            
            }
            //Significa que fechaHoraDesdeVersion(ÚltimaVersion)< fechaHoraDesdeVersion(NuevaVersion)<=fechaHoraHastaVersion(ÚltimaVersion)        
            if(FDNv == vultima.getFechaHoraDesdeVersion()){
                throw new VersionException("Erro la version tiene misma fecha desde que la ultima, Debe anularla antes.");
            }
        }
        
        Timestamp FHNv = nuevaV.getFechaHoraHastaVersion();
        if(FHNv.equals(FDNv)){
            throw new VersionException("Erro la version nueva debe tener distinta fecha desde y fehca hasta.");
            //FachadaPersistencia.getInstance().finalizarTransaccion(); 
        }
        if(FHNv.before(FDNv)){
            throw new VersionException("Erro la version nueva no puede tener una fecha desde > fehca hasta.");
            //FachadaPersistencia.getInstance().finalizarTransaccion(); 
        }
//cambio la ultima version:
        if (vultima!=null){
        vultima.setFechaHoraHastaVersion(FDNv);}
        
//Epuezo con las validaciones de las Tranciciones

        //Creo el dto para validar los destinos es la lista l
        List<Integer> codDestinos = new ArrayList();
        List<TransicionEstado> transiciones = nuevaV.getTransicionEstadoList();
        for(TransicionEstado trans : transiciones){
        List<EstadoTramite> EDs = trans.getEstadosDestino();
        for( EstadoTramite ed : EDs){
            //DTOCodDestino ced = new DTOCodDestino();
            //ced.setCodDestino(ed.getCodEstadoTramite());
            codDestinos.add(ed.getCodEstadoTramite());
        }   
        }
        
        //Valido la primer transicion
        for(TransicionEstado transicion : transiciones){
            if(transicion.getNroTransicionEstado() == 1){
                eA = transicion.getEstadoActual();
                int estadoInicial = eA.getCodEstadoTramite() ;
                if(estadoInicial != 1){
                    throw new VersionException("Error, la vercion debe tener como primer estado actual el Iniciado");
                //FachadaPersistencia.getInstance().finalizarTransaccion(); 
                }
                List<EstadoTramite> eDs = transicion.getEstadosDestino();
                for(EstadoTramite estado : eDs){
                    if(estadoInicial == estado.getCodEstadoTramite()){
                        throw new VersionException("Error, la vercion no puede tener estados recursivos");
                    //FachadaPersistencia.getInstance().finalizarTransaccion();     
                    }
                }
            }
            else{
                eA = transicion.getEstadoActual();
                int codigoEA = eA.getCodEstadoTramite();
                boolean codEncontrado = false;

                //DTOCodDestino codeA = new DTOCodDestino();
                //codeA.setCodDestino(eA.getCodEstadoTramite());
                for (int m=0;m<codDestinos.size(); m++){
                    if(codigoEA == codDestinos.get(m)){
                        codEncontrado = true;
                    }
                }
                if(!codEncontrado){
                    throw new VersionException("Error, la vercion debe tener estados actuales posibles de alcanzar");
                //FachadaPersistencia.getInstance().finalizarTransaccion(); 
                }
                
                List<EstadoTramite> eDs = transicion.getEstadosDestino();
                for(EstadoTramite ed : eDs){
                    if(codigoEA == ed.getCodEstadoTramite()){
                        throw new VersionException("Error, la vercion no puede tener estados recursivos");
                    //FachadaPersistencia.getInstance().finalizarTransaccion();     
                    }
                }
                //Valido estados recurcivos de un ciclo
                if(transicion.getNroTransicionEstado() > 2){
                    List<TransicionEstado> listaTrans2=new ArrayList();
                    for(TransicionEstado transicion2 : transiciones){
                        List<EstadoTramite> eDsL2 = transicion2.getEstadosDestino();
                        for(EstadoTramite j : eDsL2){
                           if(j.getCodEstadoTramite() == transicion.getEstadoActual().getCodEstadoTramite()) {
                               listaTrans2.add(transicion2);
                           }
                        }
                    }
                    for(TransicionEstado transicion3 : listaTrans2){
                        for(EstadoTramite j : eDs){
                           if(transicion3.getEstadoActual().getCodEstadoTramite() == j.getCodEstadoTramite()) {
                              throw new VersionException("Error, la vercion no puede tener estados recursivos");
                           }
                        }
                    }
                }
            }
            
        }
        
        //transiciones
        for(TransicionEstado transicion : nuevaV.getTransicionEstadoList()){
        FachadaPersistencia.getInstance().guardar(transicion);            
        }
        if (vultima!=null){
        FachadaPersistencia.getInstance().guardar(vultima);  }      
        FachadaPersistencia.getInstance().guardar(nuevaV);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    
    }  
   
    public List<DTOVersionHistorial> historialVersiones(int codigoTT, int codigoV){
        
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List<DTOVersionHistorial> versionesResultado = new ArrayList<>();
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codigoTT);
            lCriterio.add(unCriterio);
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
        for (Object y : objetoList){
        TipoTramite tt = (TipoTramite) y;            
        
        lCriterio.clear();
            DTOCriterio Criterio=new DTOCriterio();
            Criterio.setAtributo("tipoTramite");
            Criterio.setOperacion("=");
            Criterio.setValor(tt);
            lCriterio.add(Criterio);
            
        if(codigoV>0)
        {
            
            DTOCriterio unCriterio2=new DTOCriterio();
            unCriterio2.setAtributo("codVersion");
            unCriterio2.setOperacion("=");
            unCriterio2.setValor(codigoV);
            lCriterio.add(unCriterio2);
        }
        List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio);
        
            for (Object x : objetoList2){
            Version version = (Version) x;
            DTOVersionHistorial versionDTO = new DTOVersionHistorial();
            versionDTO.setCodVersion(version.getCodVersion());
            versionDTO.setFechaBajaVersion(version.getFechaHoraBajaVersion());
            versionDTO.setFechaDesdeVersion(version.getFechaHoraDesdeVersion());
            versionDTO.setFechaHastaVersion(version.getFechaHoraHastaVersion());            
            TipoTramite tipoTramiteDTO =version.getTipoTramite();
            versionDTO.setCodTipoTramite(tipoTramiteDTO.getCodTipoTramite());
            versionDTO.setNombreTipoTramite(tipoTramiteDTO.getNombreTipoTramite());
            versionesResultado.add(versionDTO);
            }            
        }
       // List<DTOVersionHistorial> versionROrden = new ArrayList();
        //versionROrden =null;
        versionesResultado.sort(Comparator.comparingInt(DTOVersionHistorial :: getCodVersion).reversed());
        //Ordeno la lista
       /* for(int i=0; i<versionesResultado.size(); i++){
          if(versionROrden==null){ versionROrden.add(versionesResultado.get(i));}
            else{
                if(versionROrden.get(i-1).getCodVersion().compare(versionROrden.get(i).getCodVersion())<0)
                {
                                ultVersion=listversion.get(i);
                }
            }  
        }
        Collections.sort(versionesResultado, new Comparator<Version>(){
            @Override
            public int compare(Version v1, Version v2) {
                // Comparar por código en orden descendente
                return Integer.compare(v2.getCodVersion(), v1.getCodVersion());
            }
        });*/
        return versionesResultado;
    }
    
    public void anularVersion(int codVersion, int codTT) throws VersionException {
        
        FachadaPersistencia.getInstance().iniciarTransaccion();

       List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codTT);
            lCriterio.add(unCriterio);
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
        TipoTramite tt = (TipoTramite)objetoList.get(0);
        if(objetoList.size()<= 0)
        {
                throw new VersionException("El TipoTramite NO existe");
        }
        
        lCriterio.clear();
         DTOCriterio Criterio1=new DTOCriterio();
            Criterio1.setAtributo("tipoTramite");
            Criterio1.setOperacion("=");
            Criterio1.setValor(tt);
            lCriterio.add(Criterio1);
        DTOCriterio Criterio2=new DTOCriterio();
            Criterio2.setAtributo("fechaHoraBajaVersion");
            Criterio2.setOperacion("=");
            Criterio2.setValor(null);
            lCriterio.add(Criterio2);
        DTOCriterio Criterio3=new DTOCriterio();
            Criterio3.setAtributo("codVersion");
            Criterio3.setOperacion("=");
            Criterio3.setValor(codVersion);
            lCriterio.add(Criterio3);
        List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio); 
        Version versionanula = (Version) objetoList2.get(0);
        if(objetoList.size()<= 0)
        {
                throw new VersionException("La version no se puede anular");
        }
        Timestamp FActual = new Timestamp(System.currentTimeMillis());
        if(versionanula.getFechaHoraHastaVersion().after(FActual) && versionanula.getFechaHoraDesdeVersion().before(FActual))
        {
                throw new VersionException("La version no se puede anular. ES LA ACTUAL");
        }  
       //buscar ultima version de ese TipoTramite
        
        lCriterio.clear();
        DTOCriterio Cri=new DTOCriterio();
            Cri.setAtributo("tipoTramite");
            Cri.setOperacion("=");
            Cri.setValor(tt);
            lCriterio.add(Cri);
        DTOCriterio Cri2=new DTOCriterio();
            Cri2.setAtributo("fechaHoraBajaVersion");
            Cri2.setOperacion("=");
            Cri2.setValor(null);
            lCriterio.add(Cri2);
        List objetoList3 = FachadaPersistencia.getInstance().buscar("Version", lCriterio); 
        //Selecciono la de mayor fecha desde
        List<Version> listversion = (List<Version>) objetoList3;
        
        int i;
        int p;
        List<Integer> ordenFd = new ArrayList();
        Version ultVersion=null;
            for(i=0;i<listversion.size();i++){
                if(ultVersion==null){ ultVersion=listversion.get(i);}
                else{
                    if(ultVersion.getFechaHoraDesdeVersion().compareTo(listversion.get(i).getFechaHoraDesdeVersion())<0){
                        ultVersion=listversion.get(i);
                        p= i;
                        ordenFd.add(ultVersion.getCodVersion());
                        }
                    }
                }          
        Version vultima = (Version)ultVersion;
        Timestamp FHUV = vultima.getFechaHoraHastaVersion();
        int codvu = vultima.getCodVersion();
        int codva = versionanula.getCodVersion();
        if(codvu != codva){
            throw new VersionException("Existen versiones futuras. Solo se puede anular la última futura.");
        } 
        if(listversion.size()<=1){ 
            throw new VersionException("La version no se puede anular porque es la única");
        }
        vultima.setFechaHoraBajaVersion(new Timestamp(System.currentTimeMillis()));
        listversion.remove(vultima);
        Version penultima=null;
            for(int j=0;j<(listversion.size());j++){
                if(penultima==null){ penultima=listversion.get(j);}
                else{
                    if(penultima.getFechaHoraDesdeVersion().compareTo(listversion.get(j).getFechaHoraDesdeVersion())<0){
                        penultima=listversion.get(j);
                        }
                    }
                } 
        penultima.setFechaHoraHastaVersion(vultima.getFechaHoraHastaVersion());
        
        
        FachadaPersistencia.getInstance().guardar(vultima);
        FachadaPersistencia.getInstance().guardar(penultima);
        FachadaPersistencia.getInstance().finalizarTransaccion();
        // la guardo antes para poder despues buscar la penultima como ultima
        FachadaPersistencia.getInstance().iniciarTransaccion();
        //Cambio la antepenúltima version
        /*
        lCriterio.clear();
        DTOCriterio Cri1=new DTOCriterio();
            Cri1.setAtributo("tipoTramite");
            Cri1.setOperacion("=");
            Cri1.setValor(tt);
            lCriterio.add(Cri1);
        DTOCriterio Cri3=new DTOCriterio();
            Cri3.setAtributo("fechaHoraBajaVersion");
            Cri3.setOperacion("=");
            Cri3.setValor(null);
            lCriterio.add(Cri3);
        List objetoList4 = FachadaPersistencia.getInstance().buscar("Version", lCriterio); 
        //Selecciono la de mayor fecha desde
        List<Version> listversion2 = (List<Version>) objetoList4;
        Version penultVersion=null;
            for(int j=0;j<listversion.size();j++){
                if(penultVersion==null){ penultVersion=listversion.get(j);}
                else{
                    if(penultVersion.getFechaHoraDesdeVersion().compareTo(listversion.get(j).getFechaHoraDesdeVersion())<0){
                        penultVersion=listversion.get(j);
                        }
                    }
                }
        Version penulVer = penultVersion;
        
        penulVer.setFechaHoraHastaVersion(FHUV);
        
        FachadaPersistencia.getInstance().guardar(penulVer);
        FachadaPersistencia.getInstance().finalizarTransaccion();
*/
    }
    
}
