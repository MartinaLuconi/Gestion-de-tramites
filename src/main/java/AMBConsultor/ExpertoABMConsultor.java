package AMBConsultor;

import AMBConsultor.dtos.ConsultorDTO;
import AMBConsultor.dtos.ModificarConsultorDTO;
import AMBConsultor.dtos.ModificarConsultorDTOIn;
import AMBConsultor.dtos.NuevoConsultorDTO;
import AMBConsultor.exceptions.ConsultorException;
import entidades.Agenda;
import entidades.Consultor;
import entidades.Usuario;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;


/**
 *
 * @author adrie
 */
public class ExpertoABMConsultor  {
    
    // Método para buscar consultores por legajo o nombre
    public List<ConsultorDTO> buscarConsultores(int legajoConsultor, String nombreApellidoConsultor) {
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if (legajoConsultor > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("legajoConsultor");
            unCriterio.setOperacion("=");
            unCriterio.setValor(legajoConsultor);
            lCriterio.add(unCriterio);
        }

        if(nombreApellidoConsultor.trim().length()>0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreApellidoConsultor");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreApellidoConsultor);
            lCriterio.add(unCriterio);
        }

        List objetoList = FachadaPersistencia.getInstance().buscar("Consultor", lCriterio);
        List<ConsultorDTO> consultoresResultado = new ArrayList<>();

        for (Object x : objetoList) {
            Consultor consultor = (Consultor) x;
            ConsultorDTO consultorDTO = new ConsultorDTO();
            consultorDTO.setLegajoConsultor(consultor.getLegajoConsultor());
            consultorDTO.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
            consultorDTO.setConsultorNroMaxTramite(consultor.getConsultorNroMaxTramite());
            consultorDTO.setFechaHoraBajaConsultor(consultor.getFechaHoraBajaConsultor());
            consultoresResultado.add(consultorDTO);
        }

        return consultoresResultado;
    }
    // Método para agregar un nuevo consultor
    public void agregarConsultor(NuevoConsultorDTO nuevoConsultorDTO) throws ConsultorException {
    FachadaPersistencia.getInstance().iniciarTransaccion();
    
    // Validar si el mail ya esta en uso
        List<DTOCriterio> criterioNombre = new ArrayList<>();
        DTOCriterio criterioNombreUsuario = new DTOCriterio();
        criterioNombreUsuario.setAtributo("usuario");
        criterioNombreUsuario.setOperacion("=");
        criterioNombreUsuario.setValor(nuevoConsultorDTO.getUsuario());
        criterioNombre.add(criterioNombreUsuario);

        List<Object> MailConMismoNombre = FachadaPersistencia.getInstance().buscar("Usuario", criterioNombre);

        if (!MailConMismoNombre.isEmpty()) {
            throw new ConsultorException("El mail del consultor ya existe.");
        }
    // Validación de solo letras y espacios en el campo "Nombre y Apellido"
    if (!nuevoConsultorDTO.getNombreApellidoConsultor().matches("^[\\p{L}\\s]+$")) {
        throw new ConsultorException("El campo 'Nombre y Apellido' solo debe contener letras y espacios.");
    }

    // Validación de campos obligatorios
    if (nuevoConsultorDTO.getNombreApellidoConsultor() == null || nuevoConsultorDTO.getNombreApellidoConsultor().trim().isEmpty()) {
        throw new ConsultorException("El campo 'Nombre y Apellido' es obligatorio y no puede estar vacío.");
    }
    
    if (nuevoConsultorDTO.getUsuario() == null || nuevoConsultorDTO.getUsuario().trim().isEmpty()) {
        throw new ConsultorException("El campo 'Mail' es obligatorio y no puede estar vacío.");
    }
    
    // Validación de la cantidad máxima de trámites
    if (nuevoConsultorDTO.getConsultorNroMaxTramite() <= 0) {
        throw new ConsultorException("La cantidad máxima de trámites debe ser mayor a cero");
    }

    // Obtener el último legajo existente para generar un nuevo legajo
    List<DTOCriterio> criterioList = new ArrayList<>();
    List<Object> consultoresList = FachadaPersistencia.getInstance().buscar("Consultor", criterioList);
    int ultimoLegajo = 0;
    for (Object obj : consultoresList) {
        Consultor consultor = (Consultor) obj;
        if (consultor.getLegajoConsultor() > ultimoLegajo) {
            ultimoLegajo = consultor.getLegajoConsultor();
        }
    }
    int nuevoLegajo = ultimoLegajo + 1;

    // Crear y guardar el usuario asociado al consultor
    Usuario usuario = new Usuario();
    usuario.setCodUsuario(nuevoLegajo);
    usuario.setNombreUsuario(nuevoConsultorDTO.getNombreApellidoConsultor());
    usuario.setPassword("4C2DBF7B32D939FA853464B82F800BC8");  // Configuración inicial de contraseña
    usuario.setUsuario(nuevoConsultorDTO.getUsuario());
    FachadaPersistencia.getInstance().guardar(usuario);

    // Crear el nuevo consultor con el legajo generado y datos del DTO
    Consultor consultor = new Consultor();
    consultor.setLegajoConsultor(nuevoLegajo);  // Asignar el nuevo legajo
    consultor.setNombreApellidoConsultor(nuevoConsultorDTO.getNombreApellidoConsultor());
    consultor.setConsultorNroMaxTramite(nuevoConsultorDTO.getConsultorNroMaxTramite());
    consultor.setUsuario(usuario);

    // Guardar el nuevo consultor en la base de datos
    FachadaPersistencia.getInstance().guardar(consultor);

    // Finalizar la transacción
    FachadaPersistencia.getInstance().finalizarTransaccion();
}



    // Método para buscar un consultor por legajo y obtener el DTO para modificar
    public ModificarConsultorDTO buscarConsultorAModificar(int legajoConsultor) {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("legajoConsultor");
        dto.setOperacion("=");
        dto.setValor(legajoConsultor);

        criterioList.add(dto);

        Consultor consultorEncontrado = (Consultor) FachadaPersistencia.getInstance().buscar("Consultor", criterioList).get(0);

        ModificarConsultorDTO modificarConsultorDTO = new ModificarConsultorDTO();
        modificarConsultorDTO.setLegajoConsultor(consultorEncontrado.getLegajoConsultor());
        modificarConsultorDTO.setNombreApellidoConsultor(consultorEncontrado.getNombreApellidoConsultor());
        modificarConsultorDTO.setConsultorNroMaxTramite(consultorEncontrado.getConsultorNroMaxTramite());
        modificarConsultorDTO.setUsuario(consultorEncontrado.getUsuario().getUsuario());

        return modificarConsultorDTO;
    }

        // Método para modificar solo el nroMaxTramite del consultor
        public void modificarConsultor(ModificarConsultorDTOIn modificarConsultorDTOIn) throws ConsultorException {
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        // Buscar al consultor actual para obtener el usuario asociado
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioLegajo = new DTOCriterio();
        criterioLegajo.setAtributo("legajoConsultor");
        criterioLegajo.setOperacion("=");
        criterioLegajo.setValor(modificarConsultorDTOIn.getLegajoConsultor());
        criterioList.add(criterioLegajo);

        Consultor consultorEncontrado = (Consultor) FachadaPersistencia.getInstance().buscar("Consultor", criterioList).get(0);

        // Validar si el consultor ya ha sido dado de baja
        if (consultorEncontrado.getFechaHoraBajaConsultor() != null) {
            throw new ConsultorException("No se puede modificar un consultor que ha sido dado de baja.");
        }
        
        // Validar si el mail está en uso por otro usuario
        if (!consultorEncontrado.getUsuario().getUsuario().equals(modificarConsultorDTOIn.getUsuario())) {
            List<DTOCriterio> criterioUsuario = new ArrayList<>();
            DTOCriterio criterioNombreUsuario = new DTOCriterio();
            criterioNombreUsuario.setAtributo("usuario");
            criterioNombreUsuario.setOperacion("=");
            criterioNombreUsuario.setValor(modificarConsultorDTOIn.getUsuario());
            criterioUsuario.add(criterioNombreUsuario);

            List<Object> MailConMismoNombre = FachadaPersistencia.getInstance().buscar("Usuario", criterioUsuario);
            
            // Si el mail pertenece a otro usuario, lanza la excepción
            if (!MailConMismoNombre.isEmpty()) {
                throw new ConsultorException("El mail del consultor ya existe.");
            }
        }
        // Validación de solo letras y espacios en el campo "Nombre y Apellido"
        if (!modificarConsultorDTOIn.getNombreApellidoConsultor().matches("^[\\p{L}\\s]+$")) {
            throw new ConsultorException("El campo 'Nombre y Apellido' solo debe contener letras y espacios.");
        }

        // Validación de la cantidad máxima de trámites
        if (modificarConsultorDTOIn.getConsultorNroMaxTramite() <= 0) {
            throw new ConsultorException("La cantidad máxima de trámites debe ser mayor a cero");
        }
        // Validación de campos obligatorios
        if (modificarConsultorDTOIn.getNombreApellidoConsultor() == null || modificarConsultorDTOIn.getNombreApellidoConsultor().trim().isEmpty()) {
            throw new ConsultorException("El campo 'Nombre y Apellido' es obligatorio y no puede estar vacío.");
        }

        if (modificarConsultorDTOIn.getUsuario() == null || modificarConsultorDTOIn.getUsuario().trim().isEmpty()) {
            throw new ConsultorException("El campo 'Mail' es obligatorio y no puede estar vacío.");
        }
        
        // Modificar Consultor 
        consultorEncontrado.setNombreApellidoConsultor(modificarConsultorDTOIn.getNombreApellidoConsultor());
        consultorEncontrado.setConsultorNroMaxTramite(modificarConsultorDTOIn.getConsultorNroMaxTramite());
        consultorEncontrado.getUsuario().setUsuario(modificarConsultorDTOIn.getUsuario());
        
        
        FachadaPersistencia.getInstance().guardar(consultorEncontrado.getUsuario());
        FachadaPersistencia.getInstance().guardar(consultorEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }

    
    public void darDeBajaConsultor(int legajoConsultor) throws ConsultorException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        // Crear el criterio para buscar el consultor por legajo
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("legajoConsultor");
        dto.setOperacion("=");
        dto.setValor(legajoConsultor);
        criterioList.add(dto);

        // Buscar el consultor
        Consultor consultorEncontrado = (Consultor) FachadaPersistencia.getInstance().buscar("Consultor", criterioList).get(0);
        
        // Verificar si el consultor está asignado a algún trámite no finalizado
        criterioList.clear();
        dto = new DTOCriterio();
        dto.setAtributo("consultor");
        dto.setOperacion("=");
        dto.setValor(consultorEncontrado);
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

        List<Object> tramitesRelacionados = FachadaPersistencia.getInstance().buscar("Tramite", criterioList);

        if (!tramitesRelacionados.isEmpty()) {
        // Si hay trámites no finalizados, lanzar excepción
            throw new ConsultorException("El consultor no puede darse de baja porque tiene trámites pendientes.");
        }
        
        // Validar si el consultor ya ha sido dado de baja
        if (consultorEncontrado.getFechaHoraBajaConsultor() != null) {
            throw new ConsultorException("El consultor ya ha sido dado de baja. No es posible realizar la baja nuevamente.");
        }
        // Verificar si el consultor está en alguna agenda activa
        criterioList.clear();
        DTOCriterio dtoFecha = new DTOCriterio();
        dtoFecha.setAtributo("fechaHastaAgenda");
        dtoFecha.setOperacion(">=");
        dtoFecha.setValor(new Date()); // Fecha actual
        criterioList.add(dtoFecha);
        
        
        // Buscar agendas activas relacionadas con el consultor
        List<Object> agendasRelacionadas = FachadaPersistencia.getInstance().buscar("Agenda", criterioList);

        for (Object obj : agendasRelacionadas) {
            Agenda agenda = (Agenda) obj; // Realizar el cast a Agenda
            if (agenda.getConsultores().stream().anyMatch(c -> c.getLegajoConsultor() == consultorEncontrado.getLegajoConsultor())) {
                // Si el consultor está en alguna agenda activa, lanzar excepción
                throw new ConsultorException("El consultor no puede darse de baja porque está agregado a una agenda activa.");
            }
        }

        // Dar de baja al consultor
        consultorEncontrado.setFechaHoraBajaConsultor(new Timestamp(System.currentTimeMillis()));
        consultorEncontrado.getUsuario().setUsuario(null);
        consultorEncontrado.getUsuario().setPassword(null);
        
        FachadaPersistencia.getInstance().guardar(consultorEncontrado.getUsuario());
        FachadaPersistencia.getInstance().guardar(consultorEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
}

