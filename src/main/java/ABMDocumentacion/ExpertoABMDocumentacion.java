package ABMDocumentacion;

import ABMDocumentacion.dtos.DocumentacionDTO;
import ABMDocumentacion.dtos.ModificarDocumentacionDTO;
import ABMDocumentacion.dtos.ModificarDocumentacionDTOIn;
import ABMDocumentacion.dtos.NuevaDocumentacionDTO;
import ABMDocumentacion.exceptions.ABMDocumentacionException;
import entidades.Documentacion;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;


public class ExpertoABMDocumentacion {

    // Método para buscar documentación por código o nombre
    public List<DocumentacionDTO> buscarDocumentacion(int codDocumentacion, String nombreDocumentacion) {
        List<DTOCriterio> lCriterio = new ArrayList<>();

        if (codDocumentacion > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("codDocumentacion");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codDocumentacion);
            lCriterio.add(unCriterio);
        }

        if(nombreDocumentacion != null && !nombreDocumentacion.trim().isEmpty()) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreDocumentacion");
            unCriterio.setOperacion("like");
            unCriterio.setValor("%" + nombreDocumentacion + "%");
            lCriterio.add(unCriterio);
        }

        List<Object> listaDocumentacion = FachadaPersistencia.getInstance().buscar("Documentacion", lCriterio);
        List<DocumentacionDTO> documentacionResultado = new ArrayList<>();

        for (Object obj : listaDocumentacion) {
            Documentacion documentacion = (Documentacion) obj;
            DocumentacionDTO documentacionDTO = new DocumentacionDTO();
            documentacionDTO.setCodDocumentacion(documentacion.getCodDocumentacion());
            documentacionDTO.setNombreDocumentacion(documentacion.getNombreDocumentacion());
            documentacionDTO.setFechaHoraBajaDocumentacion(documentacion.getFechaHoraBajaDocumentacion());
            documentacionResultado.add(documentacionDTO);
        }

        return documentacionResultado;
    }

    // Método para agregar una nueva documentación
public void agregarDocumentacion(NuevaDocumentacionDTO nuevaDocumentacionDTO) throws ABMDocumentacionException {
    FachadaPersistencia.getInstance().iniciarTransaccion();

    // Validar si el nombre ya está en uso
    List<DTOCriterio> criterioNombre = new ArrayList<>();
    DTOCriterio criterioNombreDoc = new DTOCriterio();
    criterioNombreDoc.setAtributo("nombreDocumentacion");
    criterioNombreDoc.setOperacion("=");
    criterioNombreDoc.setValor(nuevaDocumentacionDTO.getNombreDocumentacion());
    criterioNombre.add(criterioNombreDoc);

    List<Object> lDocumentacionConMismoNombre = FachadaPersistencia.getInstance().buscar("Documentacion", criterioNombre);

    if (!lDocumentacionConMismoNombre.isEmpty()) {
        throw new ABMDocumentacionException("El nombre de la documentación ya existe.");
    }
    // Validación de campos obligatorios
    if (nuevaDocumentacionDTO.getNombreDocumentacion()== null || nuevaDocumentacionDTO.getNombreDocumentacion().trim().isEmpty()) {
        throw new ABMDocumentacionException("El campo 'Nombre de la Documentación' es obligatorio y no puede estar vacío.");
    }
    // Validar si el código ya está en uso
     List<DTOCriterio> criterioList = new ArrayList<>();
     List<Object> lDocumentacion = FachadaPersistencia.getInstance().buscar("Documentacion", criterioList);

   
    int ultimoCodigo = 0;
    for (Object obj : lDocumentacion) {
        Documentacion documentacion = (Documentacion) obj;
        if (documentacion.getCodDocumentacion()> ultimoCodigo) {
            ultimoCodigo = documentacion.getCodDocumentacion();
        }
    }
    int nuevoCodigo = ultimoCodigo + 1;

    // Si no hay conflictos, agregar la nueva documentación
    Documentacion documentacion = new Documentacion();
    documentacion.setCodDocumentacion(nuevoCodigo);
    documentacion.setNombreDocumentacion(nuevaDocumentacionDTO.getNombreDocumentacion());

    FachadaPersistencia.getInstance().guardar(documentacion);
    FachadaPersistencia.getInstance().finalizarTransaccion();
}

    // Método para buscar una documentación por código para modificar
    public ModificarDocumentacionDTO buscarDocumentacionAModificar(int codDocumentacion) {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioCod = new DTOCriterio();
        criterioCod.setAtributo("codDocumentacion");
        criterioCod.setOperacion("=");
        criterioCod.setValor(codDocumentacion);

        criterioList.add(criterioCod);

        Documentacion documentacion = (Documentacion) FachadaPersistencia.getInstance().buscar("Documentacion", criterioList).get(0);

        ModificarDocumentacionDTO modificarDocumentacionDTO = new ModificarDocumentacionDTO();
        modificarDocumentacionDTO.setCodDocumentacion(documentacion.getCodDocumentacion());
        modificarDocumentacionDTO.setNombreDocumentacion(documentacion.getNombreDocumentacion());

        return modificarDocumentacionDTO;
    }

    // Método para modificar la documentación
    public void modificarDocumentacion(ModificarDocumentacionDTOIn modificarDocumentacionDTOIn) throws ABMDocumentacionException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        // Buscar la documentación actual por código para verificar su existencia y obtener datos
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioCod = new DTOCriterio();
        criterioCod.setAtributo("codDocumentacion");
        criterioCod.setOperacion("=");
        criterioCod.setValor(modificarDocumentacionDTOIn.getCodDocumentacion());
        criterioList.add(criterioCod);

        Documentacion documentacion = (Documentacion) FachadaPersistencia.getInstance().buscar("Documentacion", criterioList).get(0);

        // Validación de campos obligatorios
        if (modificarDocumentacionDTOIn.getNombreDocumentacion() == null || modificarDocumentacionDTOIn.getNombreDocumentacion().trim().isEmpty()) {
            throw new ABMDocumentacionException("El campo 'Nombre de la Documentación' es obligatorio y no puede estar vacío.");
        }

        // Validar si la documentación ya ha sido dada de baja
        if (documentacion.getFechaHoraBajaDocumentacion() != null) {
            throw new ABMDocumentacionException("No se puede modificar un documento que ha sido dado de baja.");
        }

        // Validar si el nombre de la documentación ya está en uso por otro registro
        if (!documentacion.getNombreDocumentacion().equals(modificarDocumentacionDTOIn.getNombreDocumentacion())) {
            List<DTOCriterio> criterioNombre = new ArrayList<>();
            DTOCriterio criterioNombreDoc = new DTOCriterio();
            criterioNombreDoc.setAtributo("nombreDocumentacion");
            criterioNombreDoc.setOperacion("=");
            criterioNombreDoc.setValor(modificarDocumentacionDTOIn.getNombreDocumentacion());
            criterioNombre.add(criterioNombreDoc);

            List<Object> lDocumentacionConMismoNombre = FachadaPersistencia.getInstance().buscar("Documentacion", criterioNombre);

            // Si el nombre ya está en uso, lanzar la excepción
            if (!lDocumentacionConMismoNombre.isEmpty()) {
                throw new ABMDocumentacionException("El nombre de la documentación ya existe.");
            }
        }

        // Proceder con la modificación del nombre de la documentación
        documentacion.setNombreDocumentacion(modificarDocumentacionDTOIn.getNombreDocumentacion());

        // Guardar los cambios y finalizar la transacción
        FachadaPersistencia.getInstance().guardar(documentacion);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }



    // Método para dar de baja una documentación
    public void darDeBajaDocumentacion(int codDocumentacion) throws ABMDocumentacionException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioCod = new DTOCriterio();
        criterioCod.setAtributo("codDocumentacion");
        criterioCod.setOperacion("=");
        criterioCod.setValor(codDocumentacion);
        criterioList.add(criterioCod);

        Documentacion documentacion = (Documentacion) FachadaPersistencia.getInstance().buscar("Documentacion", criterioList).get(0);
        
        // Validar si la documentacion ya ha sido dado de baja
        if (documentacion.getFechaHoraBajaDocumentacion() != null) {
            throw new ABMDocumentacionException("La documentación ya ha sido dada de baja. No es posible realizar la baja nuevamente.");
        }
        //Validar si la documentacion esta asignada a un TipoTramite
        criterioList.clear();
        criterioCod = new DTOCriterio();
        criterioCod.setAtributo("documentaciones");
        criterioCod.setOperacion("contains");
        criterioCod.setValor(documentacion);
        criterioList.add(criterioCod);

        DTOCriterio dto2 = new DTOCriterio();
        dto2.setAtributo("fechaHoraBajaTipoTramite");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);
        
        List<Object> tiposTramiteRelacionados = FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);

        if (!tiposTramiteRelacionados.isEmpty()) {
            throw new ABMDocumentacionException("La documentación no puede darse de baja porque está asignada a un TipoTramite activo.");
        }

        // Marcar la documentación como dada de baja
        documentacion.setFechaHoraBajaDocumentacion(new Timestamp(System.currentTimeMillis()));
        
        FachadaPersistencia.getInstance().guardar(documentacion);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
}

