package ABMTipoTramite;

import ABMTipoTramite.dtos.DTOCategoriaTramite;
import ABMTipoTramite.dtos.DTODocumentacionTT;
import ABMTipoTramite.dtos.DTOModificarTipoTramite;
import ABMTipoTramite.dtos.DTOModificarTipoTramiteIn; // Asegúrate de que este DTO sea el correcto
import ABMTipoTramite.dtos.DTONuevoTipoTramite;
import ABMTipoTramite.dtos.DTOTipoTramite;
import ABMTipoTramite.dtos.DTOVerTipoTramite;
import ABMTipoTramite.exceptions.ABMTipoTramiteException;
import entidades.CategoriaTramite;
import entidades.Documentacion;
import entidades.TipoTramite;
import entidades.Version;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

public class ExpertoABMTipoTramite {

    public List<DTOCategoriaTramite> buscarCategoriasPosibles() {
        List<DTOCategoriaTramite> resultado = new ArrayList<>();
        List<DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
        unCriterio.setAtributo("fechaHoraBajaCategoriaTramite");
        unCriterio.setOperacion("=");
        unCriterio.setValor(null);
        lCriterio.add(unCriterio);
        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("CategoriaTramite", lCriterio);

        for (Object x : objetoList) {
            CategoriaTramite cat = (CategoriaTramite) x;
            DTOCategoriaTramite d = new DTOCategoriaTramite();
            d.setCodCategoriaTramite(cat.getCodCategoriaTramite());
            d.setNombreCategoriaTramite(cat.getNombreCategoriaTramite());
            resultado.add(d);
        }
        return resultado;
    }
    
    public List<DTODocumentacionTT> buscarDocumentacionesPosibles() {
        List<DTODocumentacionTT> resultado = new ArrayList<>();
        List<DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
        unCriterio.setAtributo("fechaHoraBajaDocumentacion");
        unCriterio.setOperacion("=");
        unCriterio.setValor(null);
        lCriterio.add(unCriterio);
        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("Documentacion", lCriterio);

        for (Object x : objetoList) {
            Documentacion doc = (Documentacion) x; // Cambié `cat` por `doc` para mayor claridad
            DTODocumentacionTT d = new DTODocumentacionTT();
            d.setCodigoDocumentacion(doc.getCodDocumentacion());
            d.setNombreDocumentacion(doc.getNombreDocumentacion());
            resultado.add(d);
        }
        return resultado;
    }

    public List<DTOTipoTramite> buscarTipoTramites(int codigo, String nombre) {
        List<DTOCriterio> lCriterio = new ArrayList<>();
        if (codigo > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codigo);
            lCriterio.add(unCriterio);
        }
        if (nombre.trim().length() > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreTipoTramite");
            unCriterio.setOperacion("like");
            unCriterio.setValor("%" + nombre + "%");
            lCriterio.add(unCriterio);
        }

        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
        List<DTOTipoTramite> tipotramitesResultado = new ArrayList<>();
        
        for (Object x : objetoList) {
            TipoTramite tipotramite = (TipoTramite) x;
            DTOTipoTramite DTOtipotramite = new DTOTipoTramite();
            DTOtipotramite.setCodTipoTramite(tipotramite.getCodTipoTramite());
            DTOtipotramite.setNombreTipoTramite(tipotramite.getNombreTipoTramite());
            DTOtipotramite.setNombreCategoriaTramite(tipotramite.getCategoriaTramite().getNombreCategoriaTramite());
            DTOtipotramite.setFechaHoraBajaTipoTramite(tipotramite.getFechaHoraBajaTipoTramite());
            DTOtipotramite.setMaxDiaEntrega(tipotramite.getMaxDiaEntrega());
            DTOtipotramite.setNombreDocumentacion(tipotramite.toString());
            tipotramitesResultado.add(DTOtipotramite);
        }
        return tipotramitesResultado;
    }

   public DTOVerTipoTramite visualizar(int codtt) throws ABMTipoTramiteException {
    DTOVerTipoTramite tt = new DTOVerTipoTramite();
    List<DTOCriterio> lCriterio = new ArrayList<>();
    DTOCriterio unCriterio = new DTOCriterio();
    unCriterio.setAtributo("codTipoTramite");
    unCriterio.setOperacion("=");
    unCriterio.setValor(codtt);
    lCriterio.add(unCriterio);
    List<Object> objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);

    if (objetoList.isEmpty()) {
        throw new ABMTipoTramiteException("Error no se encuentra TipoTramite");
    }

    TipoTramite Tt = (TipoTramite) objetoList.get(0);
    tt.setCodTipoTramite(Tt.getCodTipoTramite());
    tt.setDescripcionTipoTramite(Tt.getDescripcionTipoTramite());
    tt.setDescripcionTipoTramiteWeb(Tt.getDescripcionTipoTramiteWeb());
    tt.setNombreTipoTramite(Tt.getNombreTipoTramite());
    tt.setMaxDiaEntrega(Tt.getMaxDiaEntrega());
    CategoriaTramite c = Tt.getCategoriaTramite();
    tt.setCodCategoriaTramite(c.getCodCategoriaTramite());
    tt.setNombreCategoriaTramite(c.getNombreCategoriaTramite());

    // Crear la lista de DTODocumentacionTT y llenarla con las documentaciones asociadas
    List<DTODocumentacionTT> listaDocumentaciones = new ArrayList<>();
    for (Documentacion doc : Tt.getDocumentaciones()) {
        DTODocumentacionTT dtoDoc = new DTODocumentacionTT();
        dtoDoc.setCodigoDocumentacion(doc.getCodDocumentacion());
        dtoDoc.setNombreDocumentacion(doc.getNombreDocumentacion());
        listaDocumentaciones.add(dtoDoc);
    }

    // Asignar la lista de documentaciones al DTO
    tt.setListaDocumentacion(listaDocumentaciones);

    return tt;
}


public void agregarABMTipoTramite(DTONuevoTipoTramite DtoN) throws ABMTipoTramiteException { 
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        // Verificar si el tipo de trámite ya existe
        List<DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
        unCriterio.setAtributo("codTipoTramite");
        unCriterio.setOperacion("=");
        unCriterio.setValor(DtoN.getCodTipoTramite());
        lCriterio.add(unCriterio);

        DTOCriterio unCriterio2 = new DTOCriterio();
        unCriterio2.setAtributo("fechaHoraBajaTipoTramite");
        unCriterio2.setOperacion("=");
        unCriterio2.setValor(null);
        lCriterio.add(unCriterio2);

        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
        
        // Verificación si la lista está vacía
        if (!objetoList.isEmpty()) {
            throw new ABMTipoTramiteException("Error: ya existe el TipoTramite");
        }

        // Crear nuevo TipoTramite
        TipoTramite tt = new TipoTramite();
        tt.setCodTipoTramite(DtoN.getCodTipoTramite());
        tt.setDescripcionTipoTramite(DtoN.getDescripcionTipoTramite());
        tt.setDescripcionTipoTramiteWeb(DtoN.getDescripcionTipoTramiteWeb());
        tt.setNombreTipoTramite(DtoN.getNombreTipoTramite());
        tt.setMaxDiaEntrega(DtoN.getMaxDiaEntrega());

        // Asignar la categoría al trámite
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dtoCategoria = new DTOCriterio();
        dtoCategoria.setAtributo("nombreCategoriaTramite");
        dtoCategoria.setOperacion("=");
        dtoCategoria.setValor(DtoN.getNombreCategoriaTramite());
        criterioList.add(dtoCategoria);

        List<Object> objetoList2 = FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList);
        if (objetoList2.isEmpty()) {
            throw new ABMTipoTramiteException("Error: No se encontró la categoría seleccionada.");
        } else {
            CategoriaTramite c = (CategoriaTramite) objetoList2.get(0);
            tt.setCategoriaTramite(c);
        }

        // Asignar la documentación
        List<Documentacion> listaDoc = new ArrayList<>();
        List<String> listDtoDoc = DtoN.getListDocumentacion();
        if (listDtoDoc != null) {
            for (String d: listDtoDoc ) {
                List<DTOCriterio> criterioDocList = new ArrayList<>();
                DTOCriterio dtoDoc = new DTOCriterio();
                dtoDoc.setAtributo("nombreDocumentacion");
                dtoDoc.setOperacion("=");
                dtoDoc.setValor(d);
                criterioDocList.add(dtoDoc);

                List<Object> objetoList3 = FachadaPersistencia.getInstance().buscar("Documentacion", criterioDocList);
                if (!objetoList3.isEmpty()) {
                    Documentacion doc = (Documentacion) objetoList3.get(0);
                    listaDoc.add(doc);
                }
            }
            if(!listaDoc.isEmpty()){
                tt.setDocumentaciones(listaDoc); // Asignar la lista de documentación
            }else{
                throw new ABMTipoTramiteException("Error: No se asignó ninguna documentación.");
            }
        }

        // Guardar el nuevo TipoTramite
        FachadaPersistencia.getInstance().guardar(tt);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }



    public DTOModificarTipoTramite buscarTipoTramiteAModificar(int codigo) {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codTipoTramite");
        dto.setOperacion("=");
        dto.setValor(codigo);
        criterioList.add(dto);

        DTOCriterio dto2 = new DTOCriterio();
        dto2.setAtributo("fechaHoraBajaTipoTramite");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);

        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);
        if (objetoList.isEmpty()) {
            return null; // Si no hay elementos, retornar null.
        }
        TipoTramite tt = (TipoTramite) objetoList.get(0);

        DTOModificarTipoTramite DtoM = new DTOModificarTipoTramite();
        DtoM.setCodTipoTramite(tt.getCodTipoTramite());
        DtoM.setFechaHoraBajaTipoTramite(tt.getFechaHoraBajaTipoTramite());
        DtoM.setMaxDiaEntrega(tt.getMaxDiaEntrega());
        DtoM.setNombreTipoTramite(tt.getNombreTipoTramite());
        DtoM.setDescripcionTipoTramite(tt.getDescripcionTipoTramite());
        DtoM.setDescripcionTipoTramiteWeb(tt.getDescripcionTipoTramiteWeb());
        CategoriaTramite c = tt.getCategoriaTramite();
        DtoM.setNombreCategoriaTramite(c.getNombreCategoriaTramite());

        List<Documentacion> listDoc = tt.getDocumentaciones();
        List<DTODocumentacionTT> listDtoDoc = new ArrayList<>();
        for (Documentacion d : listDoc) {
            DTODocumentacionTT dtoDoc = new DTODocumentacionTT();
            dtoDoc.setCodigoDocumentacion(d.getCodDocumentacion());
            dtoDoc.setNombreDocumentacion(d.getNombreDocumentacion());
            listDtoDoc.add(dtoDoc);
        }
        DtoM.setListDocumentacion(listDtoDoc);
        return DtoM;
    }

    public void modificarTipoTramite(DTOModificarTipoTramiteIn DtoIn) {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codTipoTramite");
        dto.setOperacion("=");
        dto.setValor(DtoIn.getCodTipoTramite());
        criterioList.add(dto);

        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);
        if (objetoList.isEmpty()) {
            return; // Si no hay elementos, no hacer nada.
        }
        TipoTramite tt = (TipoTramite) objetoList.get(0);

        // Aquí no modificamos el código, solo se actualizan los demás campos
        tt.setDescripcionTipoTramite(DtoIn.getDescripcionTipoTramite());
        tt.setDescripcionTipoTramiteWeb(DtoIn.getDescripcionTipoTramiteWeb());
        tt.setNombreTipoTramite(DtoIn.getNombreTipoTramite());
        tt.setMaxDiaEntrega(DtoIn.getMaxDiaEntrega());

        // Asignar la categoría
        criterioList.clear();
        dto.setAtributo("nombreCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(DtoIn.getNombreCategoriaTramite());
        criterioList.add(dto);
        List<Object> objetoList2 = FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList);
        if (!objetoList2.isEmpty()) {
            CategoriaTramite c = (CategoriaTramite) objetoList2.get(0);
            tt.setCategoriaTramite(c);
        }

        // Asignar la documentación
List<Documentacion> nuevasDocumentaciones = new ArrayList<>();
for (String docNombre : DtoIn.getDocumentacionesSeleccionadas()) {
    criterioList.clear();
    DTOCriterio docCriterio = new DTOCriterio();
    docCriterio.setAtributo("nombreDocumentacion");
    docCriterio.setOperacion("=");
    docCriterio.setValor(docNombre);
    criterioList.add(docCriterio);

    List<Object> documentoList = FachadaPersistencia.getInstance().buscar("Documentacion", criterioList);
    if (!documentoList.isEmpty()) {
        Documentacion doc = (Documentacion) documentoList.get(0);
        nuevasDocumentaciones.add(doc);  // Agregar `doc` a la lista de nuevas documentaciones
    }
}

// Asignar la lista completa de documentaciones al trámite
tt.setDocumentaciones(nuevasDocumentaciones);

        // Aquí puedes implementar la lógica correspondiente si decides habilitarlo.

        FachadaPersistencia.getInstance().guardar(tt);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }

//    public void darDeBajaTipoTramite(int codigo) throws ABMTipoTramiteException {
//        FachadaPersistencia.getInstance().iniciarTransaccion();
//
//        List<DTOCriterio> criterioList = new ArrayList<>();
//        DTOCriterio dto = new DTOCriterio();
//        dto.setAtributo("codTipoTramite");
//        dto.setOperacion("=");
//        dto.setValor(codigo);
//        criterioList.add(dto);
//        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);
//        if (objetoList.isEmpty()) {
//            return; // Si no hay elementos, no hacer nada.
//        }
//        TipoTramite tt = (TipoTramite) objetoList.get(0);
//
//        criterioList.clear();
//        dto = new DTOCriterio();
//        dto.setAtributo("tipoTramite");
//        dto.setOperacion("=");
//        dto.setValor(tt);
//        criterioList.add(dto);
//        DTOCriterio dto2 = new DTOCriterio();
//        dto2.setAtributo("fechaHoraFinTramite");
//        dto2.setOperacion("=");
//        dto2.setValor(null);
//        criterioList.add(dto2);
//        List<Object> objetoList2 = FachadaPersistencia.getInstance().buscar("Tramite", criterioList);
//
//        if (!objetoList2.isEmpty()) {
//            throw new ABMTipoTramiteException("TipoTramite no puede darse de baja, porque Existen Tramites Asociados");
//        }
//
//        tt.setFechaHoraBajaTipoTramite(new Timestamp(System.currentTimeMillis()));
//        FachadaPersistencia.getInstance().guardar(tt);
//        FachadaPersistencia.getInstance().finalizarTransaccion();
//    }
     public String darDeBajaTipoTramite(int codigo) throws ABMTipoTramiteException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codTipoTramite");
        dto.setOperacion("=");
        dto.setValor(codigo);
        criterioList.add(dto);
        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);
        if (objetoList.isEmpty()) {
            return "No se encontró elementos"; // Si no hay elementos, no hacer nada.
        }
        TipoTramite tt = (TipoTramite) objetoList.get(0);
        
        criterioList.clear();
        dto.setAtributo("tipoTramite");
        dto.setOperacion("=");
        dto.setValor(tt);
        criterioList.add(dto);
        DTOCriterio dto2 = new DTOCriterio();
        dto2.setAtributo("fechaHoraFinTramite");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);
        DTOCriterio dto3 = new DTOCriterio();
        dto3.setAtributo("fechaHoraBajaTramite");
        dto3.setOperacion("=");
        dto3.setValor(null);
        criterioList.add(dto3);
        List<Object> objetoList2 = FachadaPersistencia.getInstance().buscar("Tramite", criterioList);
        if (!objetoList2.isEmpty()) {
            throw new ABMTipoTramiteException("TipoTramite no puede darse de baja, porque Existen Tramites Asociados");
        }
        
        Timestamp FActual = new Timestamp(System.currentTimeMillis());
        criterioList.clear();
        dto.setAtributo("tipoTramite");
        dto.setOperacion("=");
        dto.setValor(tt);
        criterioList.add(dto);
        dto2.setAtributo("fechaHoraBajaVersion");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);
        dto3.setAtributo("fechaHoraHastaVersion");
        dto3.setOperacion(">=");
        dto3.setValor(FActual);
        criterioList.add(dto3);
        List<Object> objetoList3 = FachadaPersistencia.getInstance().buscar("Version", criterioList);
        

        if (objetoList3.size()<=0) {
        tt.setFechaHoraBajaTipoTramite(new Timestamp(System.currentTimeMillis()));
        FachadaPersistencia.getInstance().guardar(tt);
        FachadaPersistencia.getInstance().finalizarTransaccion(); 
        return "Tipo Tramite anulado con éxito";
        }
        else{
            for(Object m : objetoList3){
                Version v = (Version) m;
                v.setFechaHoraBajaVersion(FActual);
                FachadaPersistencia.getInstance().guardar(v);
            }
        tt.setFechaHoraBajaTipoTramite(new Timestamp(System.currentTimeMillis()));
        FachadaPersistencia.getInstance().guardar(tt);
        FachadaPersistencia.getInstance().finalizarTransaccion(); 
        return "Tipo Tramite y Versiones anuladas con éxito";
        }
       
    }
}
