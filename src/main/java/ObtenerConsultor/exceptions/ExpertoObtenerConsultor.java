
package ObtenerConsultor.exceptions;

import ObtenerConsultor.dtos.DTOConsultorDisponible;
import entidades.Agenda;
import entidades.Consultor;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

public class ExpertoObtenerConsultor {
    
    public Consultor obtenerConsultor() {      
        List<DTOCriterio> lcriterio = new ArrayList<>();

        // Obtener el año y la semana actual
        LocalDate fechaActual = LocalDate.now();
        int anioActual = fechaActual.getYear();
        int semanaActual = fechaActual.get(WeekFields.of(Locale.getDefault()).weekOfYear());

        System.out.println("Año actual: " + anioActual);
        System.out.println("Semana actual: " + semanaActual);

        // Crear criterios de búsqueda
        DTOCriterio criterio1 = new DTOCriterio();
        criterio1.setAtributo("anio");
        criterio1.setOperacion("=");
        criterio1.setValor(anioActual);
        lcriterio.add(criterio1);

        DTOCriterio criterio2 = new DTOCriterio();
        criterio2.setAtributo("semana");
        criterio2.setOperacion("=");
        criterio2.setValor(semanaActual);
        lcriterio.add(criterio2);


        // Obtener la lista de agendas
        // Buscar la agenda en la base de datos
        List agendasEncontradas = FachadaPersistencia.getInstance().buscar("Agenda", lcriterio);

        // Si no se encuentran agendas, retornar una lista vacía
        if (agendasEncontradas.isEmpty()) {
            System.out.println("No se encontró la agenda");
            return null; // Retornar lista vacía
        }

        Agenda agenda = (Agenda) agendasEncontradas.get(0);
        System.out.println("Agenda encontrada: Año " + agenda.getAnio() + ", Semana: " + agenda.getSemana());

        List<DTOConsultorDisponible> consultoresDisponibles = new ArrayList<>();

        for (Consultor consultor : agenda.getConsultores()) {
            System.out.println("Procesando consultor: " + consultor.getNombreApellidoConsultor() + " (Legajo: " + consultor.getLegajoConsultor() + ")");

            List<DTOCriterio> lcriterio2 = new ArrayList<>();
            DTOCriterio criterio3 = new DTOCriterio();
            criterio3.setAtributo("consultor"); // Asegúrate de usar el nombre correcto
            criterio3.setOperacion("=");
            criterio3.setValor(consultor); // Aquí puedes usar el objeto consultor o su legajo según cómo esté mapeado
            lcriterio2.add(criterio3);

            DTOCriterio criterio4 = new DTOCriterio();
            criterio4.setAtributo("fechaHoraFinTramite");
            criterio4.setOperacion("=");
            criterio4.setValor(null);
            lcriterio2.add(criterio4);

            // Obtener los trámites asociados al consultor donde no se ha terminado
            List<Object> tramites = FachadaPersistencia.getInstance().buscar("Tramite", lcriterio2);

            // Contar los trámites
            int tramitesAsignados = tramites.size();
            System.out.println("Consultor: " + consultor.getNombreApellidoConsultor() + " - Trámites asignados: " + tramitesAsignados);
            System.out.println("Nro Max Trámite: " + consultor.getConsultorNroMaxTramite());

            // Verificar si el consultor tiene menos trámites asignados que el actual consultor disponible
            if (consultor.getConsultorNroMaxTramite() > tramitesAsignados) {
                DTOConsultorDisponible dtoConsultorDisponible = new DTOConsultorDisponible();
                dtoConsultorDisponible.setLegajoConsultor(consultor.getLegajoConsultor());
                dtoConsultorDisponible.setConsultorNroMaxTramite(consultor.getConsultorNroMaxTramite());
                dtoConsultorDisponible.setTramitesConsultorDisponible(tramitesAsignados);
                consultoresDisponibles.add(dtoConsultorDisponible);
                System.out.println("Consultor disponible agregado: " + consultor.getNombreApellidoConsultor());
            } else {
                System.out.println("Consultor " + consultor.getNombreApellidoConsultor() + " no es disponible.");
            }
        }

        // Si no se encuentra un consultor disponible, retorna null (indica un día no laboral)
        if (consultoresDisponibles.isEmpty()) {
            System.out.println("No se encontró un consultor disponible, puede ser un día no laboral.");
            return null;
        }

        // Lógica para seleccionar al consultor con menos trámites asignados.
        DTOConsultorDisponible consultorDisponibleConMenosTramites = consultoresDisponibles.get(0);
        System.out.println("Consultor inicial seleccionado: " + consultorDisponibleConMenosTramites.getLegajoConsultor() + " - Trámites: " + consultorDisponibleConMenosTramites.getTramitesConsultorDisponible());

        for (DTOConsultorDisponible dtoConsultor : consultoresDisponibles) {
            System.out.println("Comparando con consultor disponible: " + dtoConsultor.getLegajoConsultor() + " - Trámites: " + dtoConsultor.getTramitesConsultorDisponible());

            if (dtoConsultor.getTramitesConsultorDisponible() < consultorDisponibleConMenosTramites.getTramitesConsultorDisponible()) {
                consultorDisponibleConMenosTramites = dtoConsultor;
                System.out.println("Consultor seleccionado actualizado: " + consultorDisponibleConMenosTramites.getLegajoConsultor());
            } else if (dtoConsultor.getTramitesConsultorDisponible() == consultorDisponibleConMenosTramites.getTramitesConsultorDisponible()) {
                // Si la cantidad de trámites es igual, seleccionamos el que tenga consultorNroMaxTramite mayor
                if (dtoConsultor.getConsultorNroMaxTramite() > consultorDisponibleConMenosTramites.getConsultorNroMaxTramite()) {
                    consultorDisponibleConMenosTramites = dtoConsultor;
                    System.out.println("Consultor seleccionado actualizado por nroMaxTramite: " + consultorDisponibleConMenosTramites.getLegajoConsultor());
                }
            }
        }

        // Buscar el consultor con legajo seleccionado
        List<DTOCriterio> lcriterio3 = new ArrayList<>();
        DTOCriterio criterio5 = new DTOCriterio();
        criterio5.setAtributo("legajoConsultor");
        criterio5.setOperacion("=");
        criterio5.setValor(consultorDisponibleConMenosTramites.getLegajoConsultor());
        lcriterio3.add(criterio5);

        Consultor consultorSeleccionado = (Consultor) FachadaPersistencia.getInstance().buscar("Consultor", lcriterio3).get(0); 

        System.out.println("Consultor seleccionado final: " + consultorSeleccionado.getNombreApellidoConsultor() + " (Legajo: " + consultorSeleccionado.getLegajoConsultor() + ")");

        return consultorSeleccionado;  // Devuelve el consultor disponible con menos trámites
    }
}
