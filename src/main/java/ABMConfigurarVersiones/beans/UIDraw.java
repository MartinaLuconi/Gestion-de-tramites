package ABMConfigurarVersiones.beans;

import ABMConfigurarVersiones.dtos.DTOEstadoActualInput;
import ABMConfigurarVersiones.dtos.DTOEstadoDestinoInput;
import ABMConfigurarVersiones.dtos.DTOEstadoPosible;
import ABMConfigurarVersiones.dtos.DTOVersionInput;
import ABMConfigurarVersiones.dtos.DTOVersionOutput;
import ABMConfigurarVersiones.dtos.DTOVersionyEstados;
import ABMConfigurarVersiones.exceptions.ControladorABMVersion;
import ABMConfigurarVersiones.exceptions.VersionException;
import utils.BeansUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omnifaces.util.Messages;

@Named("uiDraw")
@ViewScoped

public class UIDraw implements Serializable {

    private String guardarJSON = "";
    private ControladorABMVersion controlador = new ControladorABMVersion();
    private String cargarJSON = "";
    private String titulo = "";
    private boolean editable;
    private String nodosPosibles = "";
    private boolean insert;
    private int nroVersion;
    private int codigoTipoTramite;
    private String fechaHoraDesdeVersion;
    private String fechaHoraHastaVersion;

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public int getCodigoTipoTramite() {
        return codigoTipoTramite;
    }

    public void setCodigoTipoTramite(int codigoTipoTramite) {
        this.codigoTipoTramite = codigoTipoTramite;
    }

    public String getFechaHoraDesdeVersion() {
        return fechaHoraDesdeVersion;
    }

    public void setFechaHoraDesdeVersion(String fechaHoraDesdeVersion) {
        this.fechaHoraDesdeVersion = fechaHoraDesdeVersion;
    }

    public String getFechaHoraHastaVersion() {
        return fechaHoraHastaVersion;
    }

    public void setFechaHoraHastaVersion(String fechaHoraHastaVersion) {
        this.fechaHoraHastaVersion = fechaHoraHastaVersion;
    }
    
    public void setNroVersion(int nroVersion) {
        this.nroVersion = nroVersion;
    }

    public int getNroVersion() {
        return nroVersion;
    }
    public String getNodosPosibles() {
        return nodosPosibles;
    }

    public void setNodosPosibles(String nodosPosibles) {
        this.nodosPosibles = nodosPosibles;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public UIDraw() throws VersionException  {
        //Diagrama
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int codigo = Integer.parseInt(request.getParameter("codigoTipoTramite"));
        codigoTipoTramite=codigo;
        boolean editaOve = Boolean.parseBoolean(request.getParameter("editable"));
        editable = editaOve;
       
        
        DTOVersionyEstados versionMostrar=null;
        DTOVersionOutput versionoutput =null;
            insert=true;
        try {
            versionMostrar = controlador.buscarVersionEditar(codigo);
           // versionoutput = versionMostrar.getVersion();
        } catch (VersionException ex) {
            Logger.getLogger(UIDraw.class.getName()).log(Level.SEVERE, null, ex);   
        }
        versionoutput = versionMostrar.getVersion();
        setNroVersion(versionoutput.getCodVersion());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Timestamp fechaDesde = versionoutput.getFechaHoraDesdeVersion();
        
        fechaHoraDesdeVersion = sdf.format(fechaDesde);
        
        Timestamp fechaHasta = versionoutput.getFechaHoraHastaVersion();
        
        fechaHoraHastaVersion = sdf.format(fechaHasta);

        List<NodoMenuIU> lestadosP = new ArrayList<NodoMenuIU>();
        
            List<DTOEstadoPosible> estados = versionMostrar.getEstadosPosibles();
                for(DTOEstadoPosible x: estados){
                 NodoMenuIU unEP = new NodoMenuIU();
                 unEP.setCodigo(x.getCodEstadoTramite());
                 unEP.setNombre(x.getNombreEstadoTramite());
                 lestadosP.add(unEP);
                }    
            Gson gson = new Gson();
            nodosPosibles = gson.toJson(lestadosP);
        
        List<NodoIU> lestados = new ArrayList<NodoIU>();
        String aa ="no hay";
        //versionoutput==null ||
            if( versionoutput.getDraw()== aa)
            {
                
                for(DTOEstadoPosible x: estados){
                    if(x.getCodEstadoTramite()==1){
                        List<Integer> a = new ArrayList();
                        NodoIU unE = new NodoIU();
                        
                        unE.setCodigo(x.getCodEstadoTramite());
                        unE.setNombre(x.getNombreEstadoTramite());
                        unE.setXpos(80);
                        unE.setYpos(80);
                        lestados.add(unE);  
                    }
                } 
                Gson gson1 = new Gson();
                cargarJSON = gson1.toJson(lestados);
              //cargarJSON= versionMostrar.getVersion().getDraw();
            }
            else
            {
                cargarJSON=versionMostrar.getVersion().getDraw();
            }
        editable = true;
       
       titulo = "Versión";

    }

    public String getCargarJSON() {
        return cargarJSON;
    }

    public void setCargarJSON(String cargarJSON) {
        this.cargarJSON = cargarJSON;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getGuardarJSON() {
        return guardarJSON;
    }

    public void setGuardarJSON(String guardarJSON) {
        this.guardarJSON = guardarJSON;
    }

    public String guardar() throws InterruptedException {
        
        DTOVersionInput vi = new DTOVersionInput();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // Convertir el string a java.util.Date
            java.util.Date parsedDate = dateFormat.parse(fechaHoraDesdeVersion);
            // Convertir java.util.Date a java.sql.Timestamp
            Timestamp fd = new Timestamp(parsedDate.getTime());
            //lo asigno 
            vi.setFechaHoraDesdeVersion(fd);
            //Los mismo para la hasta
            java.util.Date parsedDate2 = dateFormat.parse(fechaHoraHastaVersion);
            Timestamp fh = new Timestamp(parsedDate2.getTime()); 
            vi.setFechaHoraHastaVersion(fh);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
              vi.setCodTipoTramite(codigoTipoTramite);
              vi.setCodVersion(getNroVersion());
            
        //System.out.println(this.guardarJSON);
        //Messages.create("nroVersion").detail(""+this.nroVersion).add();
        //Messages.create("Guardar").detail(this.guardarJSON).add();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       List<NodoIU> listaNodo = new ArrayList();
        try {
            listaNodo = objectMapper.readValue(this.guardarJSON, typeFactory.constructCollectionType(List.class, NodoIU.class));
           // System.out.println(listaNodo.toString());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UIDraw.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(ex.getMessage()).error().add();
        } 
        /*
        Gson gson = new Gson();
        String prueba = gson.toJson(listaNodo);
        System.out.println(prueba + "cristian");
        Messages.create("Guardar 2").detail(prueba+"cristian").add();*/

            // Procesar la lista de nodos*/
         for (NodoIU nodo : listaNodo) {
              //System.out.println("Nodo: " + nodo.getNombre() + ", Destinos: " + nodo.getDestinos());
              DTOEstadoActualInput ei=new DTOEstadoActualInput();
            ei.setCodEstadoActual(nodo.getCodigo());
            for(int cod : nodo.getDestinos())
            {
                DTOEstadoDestinoInput ed = new DTOEstadoDestinoInput();
                ed.setCodEstadoDestino(cod);
                ei.addEstadosDestino(ed);
            }
            vi.addEstadosActuales(ei);
            }
         
            Gson gson = new Gson();
        //String prueba = gson.toJson(vi);
        //System.out.println(prueba + "cristian");
        //Messages.create("Guardar 2").detail(prueba+"cristian").add();     

        vi.setDraw(this.guardarJSON);
        try {
            controlador.nuevaVersion(vi);
            System.out.println("Guardado con Éxito");
        } catch (VersionException ex) {
            //Logger.getLogger(UIDraw.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            Messages.create("Error!").error().detail(ex.getMessage()).add();
            return ex.getMessage();
        }
        //listaNodo tiene los nodos
        //para comprobar
        //String jsonArray = "";

        
        cargarJSON = gson.toJson(listaNodo);
        //System.out.println(jsonArray);
        //Messages.create("Guardar").detail(this.guardarJSON).add();
        //System.out.println("Guardado con Éxito");
        //Thread.sleep(7000);
        return BeansUtils.redirectToPreviousPage();
    }
    public String volver(){
            return BeansUtils.redirectToPreviousPage();
    }    

}
