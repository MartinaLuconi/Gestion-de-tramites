package ConfigurarAgenda;

import ConfigurarAgenda.dtos.DTOAgendaSemana;
import ConfigurarAgenda.dtos.DTOConsultor;
import ConfigurarAgenda.dtos.DTOConsultorAgenda;
import ConfigurarAgenda.dtos.DTOPantallaAgenda;
import ConfigurarAgenda.exceptions.ConfigurarAgendaException;
import entidades.Agenda;
import entidades.Consultor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.hibernate.Hibernate;

public class ExpertoConfigurarAgenda {

    public List<Integer> obtenerSemanasDelMes(int anio, int mes) {
        List<Integer> semanas = new ArrayList<>();
        // Obtener la fecha del primer día del mes
        LocalDate firstDayOfMonth = LocalDate.of(anio, mes, 1);
        // Obtener la fecha del primer lunes del mes
        LocalDate firstMonday = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        // Asegúrate de que las semanas comiencen desde la primera semana del mes
        if (firstMonday.getMonthValue() != mes) {
            firstMonday = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        }
        // Iterar a través de las semanas del mes
        LocalDate currentMonday = firstMonday;
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        while (currentMonday.getMonthValue() == mes) {
            int weekNumber = currentMonday.get(weekFields.weekOfYear());
            if (!semanas.contains(weekNumber)) { // Evitar duplicados
                semanas.add(weekNumber);
            }
            // Pasar al siguiente lunes
            currentMonday = currentMonday.plusWeeks(1);
        }
        return semanas;
    }

    public Map<String, Date> obtenerFechasDeSemana(int anio, int semana) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        // Obtener el primer día del año
        LocalDate firstDayOfYear = LocalDate.of(anio, 1, 1);

        // Obtener el primer día de la semana indicada (por defecto, lunes)
        LocalDate fechaDesde = firstDayOfYear
                .with(weekFields.weekOfYear(), semana)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        // Obtener el último día de la semana indicada (por defecto, domingo)
        LocalDate fechaHasta = fechaDesde.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        // Convertir LocalDate a java.util.Date
        Map<String, Date> fechas = new HashMap<>();
        fechas.put("fechaDesdeAgenda", Date.from(fechaDesde.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        fechas.put("fechaHastaAgenda", Date.from(fechaHasta.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return fechas;
    }
    
    public DTOPantallaAgenda mostrarAgenda(int anio, int mes) {
        DTOPantallaAgenda dtoPantallaAgenda = new DTOPantallaAgenda();

        dtoPantallaAgenda.setAnio(anio);
        dtoPantallaAgenda.setMes(mes);

        // Obtener el primer lunes del mes
        LocalDate firstDayOfMonth = LocalDate.of(anio, mes, 1);
        LocalDate firstMonday = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        if (firstMonday.getMonthValue() != mes) {
            firstMonday = firstDayOfMonth.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }

        // Obtener las semanas del mes
        List<Integer> semanas = obtenerSemanasDelMes(anio, mes);
        
        // Iterar sobre las semanas y crear un DTOAgendaSemana para cada semana
        for (int i = 0; i < semanas.size(); i++) {
            DTOAgendaSemana dtoAgendaSemana = new DTOAgendaSemana();
            dtoAgendaSemana.setAnio(anio);
            dtoAgendaSemana.setSemana(semanas.get(i));

            // Calcular las fechas desde y hasta para la semana actual
            LocalDate fechaDesdeAgenda = firstMonday.plusWeeks(i); // Lunes de la semana i
            LocalDate fechaHastaAgenda = fechaDesdeAgenda.plusDays(6); // Domingo de la semana i

            // Asignar las fechas al DTO
            dtoAgendaSemana.setFechaDesdeAgenda(convertirLocalDateADate(fechaDesdeAgenda));
            dtoAgendaSemana.setFechaHastaAgenda(convertirLocalDateADate(fechaHastaAgenda));

            List<DTOCriterio> lcriterio = new ArrayList<>();
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("anio");
            unCriterio.setOperacion("=");
            unCriterio.setValor(anio);
            lcriterio.add(unCriterio);

            DTOCriterio dosCriterio = new DTOCriterio();
            dosCriterio.setAtributo("semana");
            dosCriterio.setOperacion("=");
            dosCriterio.setValor(semanas.get(i));
            lcriterio.add(dosCriterio);

            // Verificar si se obtuvieron resultados de la búsqueda
            List<Object> agendas = FachadaPersistencia.getInstance().buscar("Agenda", lcriterio);
            if (agendas != null && !agendas.isEmpty()) { // Verificación agregada
                Agenda agenda = (Agenda) agendas.get(0); // Solo accede si la lista no está vacía

                for (Consultor consultor : agenda.getConsultores()) {
                    DTOConsultorAgenda dtoConsultorAgenda = new DTOConsultorAgenda();
                    dtoConsultorAgenda.setLegajoConsultor(consultor.getLegajoConsultor());
                    dtoConsultorAgenda.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
                    // Agrega el consultor al dtoAgendaSemana
                    dtoAgendaSemana.addDtoConsultorAgenda(dtoConsultorAgenda);
                }
            } else {
            }

            // Agrega la agenda al dtoPantallaAgenda
            dtoPantallaAgenda.addDtoAgendaSemana(dtoAgendaSemana);
        }

        List<DTOCriterio> lcriterio = new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
        unCriterio.setAtributo("fechaHoraBajaConsultor");
        unCriterio.setOperacion("=");
        unCriterio.setValor(null);
        lcriterio.add(unCriterio);

        List lconsultor = FachadaPersistencia.getInstance().buscar("Consultor", lcriterio);

        for (Object x : lconsultor) {
            Consultor consultor = (Consultor) x;
            DTOConsultor dtoConsultor = new DTOConsultor();
            dtoConsultor.setLegajoConsultor(consultor.getLegajoConsultor());
            dtoConsultor.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
            dtoPantallaAgenda.addDtoConsultor(dtoConsultor);
        }

        return dtoPantallaAgenda;
    }
    
    private Date convertirLocalDateADate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public List<DTOConsultor> buscarConsultores() {

        List<DTOCriterio> lcriterio = new ArrayList<>();
        List lconsultor = FachadaPersistencia.getInstance().buscar("Consultor", lcriterio);

        List<DTOConsultor> consultores = new ArrayList<>();
        for (Object x : lconsultor) {
            Consultor consultor = (Consultor) x;
            DTOConsultor dtoConsultor = new DTOConsultor();
            dtoConsultor.setLegajoConsultor(consultor.getLegajoConsultor());
            dtoConsultor.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
            consultores.add(dtoConsultor);
        }

        return consultores;
    }

    public List<DTOConsultorAgenda> buscarConsultoresAsignados(int anio, int semana) throws ConfigurarAgendaException {

        // Criterios para buscar la agenda según el año y la semana
        List<DTOCriterio> lcriterio = new ArrayList<>();
        DTOCriterio criterioAnio = new DTOCriterio();
        criterioAnio.setAtributo("anio");
        criterioAnio.setOperacion("=");
        criterioAnio.setValor(anio);
        lcriterio.add(criterioAnio);

        DTOCriterio criterioSemana = new DTOCriterio();
        criterioSemana.setAtributo("semana");
        criterioSemana.setOperacion("=");
        criterioSemana.setValor(semana);
        lcriterio.add(criterioSemana);

        // Buscar la agenda en la base de datos
        List agendasEncontradas = FachadaPersistencia.getInstance().buscar("Agenda", lcriterio);

        // Si no se encuentran agendas, retornar una lista vacía
        if (agendasEncontradas.isEmpty()) {
            return new ArrayList<>(); // Retornar lista vacía
        }

        Agenda agenda = (Agenda) agendasEncontradas.get(0);

        // Lista para almacenar los DTOConsultorAgenda
        List<DTOConsultorAgenda> dtoConsultoresAgenda = new ArrayList<>();

        // Iterar sobre los consultores de la agenda
        for (Consultor consultor : agenda.getConsultores()) {
            DTOConsultorAgenda dtoConsultorAgenda = new DTOConsultorAgenda();
            dtoConsultorAgenda.setLegajoConsultor(consultor.getLegajoConsultor());
            dtoConsultorAgenda.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
            dtoConsultoresAgenda.add(dtoConsultorAgenda);
        }

        return dtoConsultoresAgenda;
    }


    public List<DTOConsultor> buscarConsultoresNoAsignados(int anio, int semana) throws ConfigurarAgendaException {
        System.out.println("Iniciando método buscarConsultoresNoAsignados con año: " + anio + ", semana: " + semana);

        // Criterios para buscar la agenda según el año y la semana
        List<DTOCriterio> lcriterio = new ArrayList<>();
        DTOCriterio criterioAnio = new DTOCriterio();
        criterioAnio.setAtributo("anio");
        criterioAnio.setOperacion("=");
        criterioAnio.setValor(anio);
        lcriterio.add(criterioAnio);

        DTOCriterio criterioSemana = new DTOCriterio();
        criterioSemana.setAtributo("semana");
        criterioSemana.setOperacion("=");
        criterioSemana.setValor(semana);
        lcriterio.add(criterioSemana);

        // Buscar la agenda en la base de datos
        System.out.println("Buscando agenda con los criterios: Año = " + anio + ", Semana = " + semana);
        List agendasEncontradas = FachadaPersistencia.getInstance().buscar("Agenda", lcriterio);

        List<DTOConsultor> consultoresNoAsignados = new ArrayList<>();

        if (agendasEncontradas.isEmpty()) {
            // Si no se encuentran agendas, devolvemos todos los consultores activos
            System.out.println("No se encontraron agendas para la semana y año especificados.");

            // Criterio para buscar consultores activos (no dados de baja)
            List<DTOCriterio> lcriterioConsultores = new ArrayList<>();
            DTOCriterio criterioConsultores = new DTOCriterio();
            criterioConsultores.setAtributo("fechaHoraBajaConsultor");
            criterioConsultores.setOperacion("=");
            criterioConsultores.setValor(null);
            lcriterioConsultores.add(criterioConsultores);

            System.out.println("Buscando consultores activos (no dados de baja)");
            List consultoresActivos = FachadaPersistencia.getInstance().buscar("Consultor", lcriterioConsultores);

            System.out.println("Cantidad de consultores activos encontrados: " + consultoresActivos.size());

            // Agregar todos los consultores activos a la lista de no asignados
            for (Object objConsultor : consultoresActivos) {
                Consultor consultor = (Consultor) objConsultor;
                System.out.println("Consultor activo encontrado: " + consultor.getNombreApellidoConsultor());
                DTOConsultor dtoConsultor = new DTOConsultor();
                dtoConsultor.setLegajoConsultor(consultor.getLegajoConsultor());
                dtoConsultor.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
                consultoresNoAsignados.add(dtoConsultor);
            }
        } else {
            // Si existe una agenda para el año y semana especificados
            System.out.println("Agenda encontrada para la semana y año especificados.");
            Agenda agenda = (Agenda) agendasEncontradas.get(0);

            // Criterio para buscar consultores activos (no dados de baja)
            List<DTOCriterio> lcriterioConsultores = new ArrayList<>();
            DTOCriterio criterioConsultores = new DTOCriterio();
            criterioConsultores.setAtributo("fechaHoraBajaConsultor");
            criterioConsultores.setOperacion("=");
            criterioConsultores.setValor(null);
            lcriterioConsultores.add(criterioConsultores);

            System.out.println("Buscando consultores activos (no dados de baja)");
            List consultoresActivos = FachadaPersistencia.getInstance().buscar("Consultor", lcriterioConsultores);

            System.out.println("Cantidad de consultores activos encontrados: " + consultoresActivos.size());

            // Filtrar los consultores activos no asignados a la agenda
            for (Object objConsultor : consultoresActivos) {
                Consultor consultor = (Consultor) objConsultor;
                // Verificar si el consultor no está asignado a la agenda
                if (!agenda.getConsultores().stream().anyMatch(c -> c.getLegajoConsultor() == consultor.getLegajoConsultor())) {
                    System.out.println("Consultor no asignado en la agenda: " + consultor.getNombreApellidoConsultor());
                    DTOConsultor dtoConsultor = new DTOConsultor();
                    dtoConsultor.setLegajoConsultor(consultor.getLegajoConsultor());
                    dtoConsultor.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
                    consultoresNoAsignados.add(dtoConsultor);
                } else {
                    System.out.println("Consultor ya asignado en la agenda: " + consultor.getNombreApellidoConsultor());
                }
            }
        }

        System.out.println("Total de consultores no asignados encontrados: " + consultoresNoAsignados.size());
        return consultoresNoAsignados;
    }

    
    public void agregarConsultor(int semana, int anio, int legajoConsultor) throws ConfigurarAgendaException {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int semanaActual = fechaActual.get(weekFields.weekOfYear());
        int anioActual = fechaActual.getYear();

        // Validar si la agenda que se intenta modificar pertenece a una semana pasada o la semana actual
        if (anio < anioActual || (anio == anioActual && semana < semanaActual)) { //if (anio < anioActual || (anio == anioActual && semana <= semanaActual)) {
            throw new ConfigurarAgendaException("No se pueden modificar agendas de semanas pasadas.");
        }
        
        // Iniciar la transacción
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        // Criterios para buscar la agenda según el año y la semana
        List<DTOCriterio> lcriterio = new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
        unCriterio.setAtributo("anio");
        unCriterio.setOperacion("=");
        unCriterio.setValor(anio);
        lcriterio.add(unCriterio);

        DTOCriterio sdoCriterio = new DTOCriterio();
        sdoCriterio.setAtributo("semana");
        sdoCriterio.setOperacion("=");
        sdoCriterio.setValor(semana);
        lcriterio.add(sdoCriterio);

        // Buscar la agenda en la base de datos
        List agendaEncontrada = FachadaPersistencia.getInstance().buscar("Agenda", lcriterio);

        Agenda agenda;

        // Si no se encuentra una agenda, se crea una nueva
        if (agendaEncontrada == null || agendaEncontrada.isEmpty()) {
            Map<String, Date> fechas = obtenerFechasDeSemana(anio, semana);
            agenda = new Agenda();
            agenda.setAnio(anio);
            agenda.setSemana(semana);
            agenda.setFechaDesdeAgenda(fechas.get("fechaDesdeAgenda"));
            agenda.setFechaHastaAgenda(fechas.get("fechaHastaAgenda"));
        } else {
            // Usar la agenda existente
            agenda = (Agenda) agendaEncontrada.get(0);

        }
         // Validar si el consultor ya está en la agenda
        for (Consultor consultor : agenda.getConsultores()) {
            if (consultor.getLegajoConsultor() == legajoConsultor) {
                // Si el consultor ya está en la agenda, lanzamos una excepción
                throw new ConfigurarAgendaException("El consultor ya está asignado a esta agenda.");
            }
        }
        
        // Buscar el consultor elegido
        List<DTOCriterio> lcriterio1 = new ArrayList<>();
        DTOCriterio criterio1 = new DTOCriterio();
        criterio1.setAtributo("legajoConsultor");
        criterio1.setOperacion("=");
        criterio1.setValor(legajoConsultor);
        lcriterio1.add(criterio1);

        Consultor consultorElegido =  (Consultor) FachadaPersistencia.getInstance().buscar("Consultor", lcriterio1).get(0);
        
        if (consultorElegido.getFechaHoraBajaConsultor() != null) {
            throw new ConfigurarAgendaException("El consultor esta dado de baja");
        }
        if (anio == anioActual && semana == semanaActual) {
            // Crear criterios para buscar trámites activos del consultor en la agenda actual
            List<DTOCriterio> lcriterio2 = new ArrayList<>();

            DTOCriterio criterioTramite = new DTOCriterio();
            criterioTramite.setAtributo("consultor");
            criterioTramite.setOperacion("=");
            criterioTramite.setValor(consultorElegido);
            lcriterio2.add(criterioTramite);

            DTOCriterio criterioTramite1 = new DTOCriterio();
            criterioTramite1.setAtributo("fechaHoraFinTramite");
            criterioTramite1.setOperacion("=");
            criterioTramite1.setValor(null);
            lcriterio2.add(criterioTramite1);

            DTOCriterio criterioTramite2 = new DTOCriterio();
            criterioTramite2.setAtributo("fechaHoraBajaTramite");
            criterioTramite2.setOperacion("=");
            criterioTramite2.setValor(null);
            lcriterio2.add(criterioTramite2);

            List<Object> tramitesRelacionados = FachadaPersistencia.getInstance().buscar("Tramite", lcriterio2);

            // Verificar si la cantidad de trámites activos alcanza o supera el máximo permitido
            if (tramitesRelacionados.size() >= consultorElegido.getConsultorNroMaxTramite()) {
                throw new ConfigurarAgendaException("No se puede agregar al consultor porque ya tiene el número máximo de trámites activos permitidos.");
            }
        }
        // Agregar el consultor a la agenda
        agenda.addConsultor(consultorElegido);

        // Guardar la agenda (nueva o modificada)
        FachadaPersistencia.getInstance().guardar(agenda);

        // Finalizar la transacción
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
    
    // Eliminar Consultor de la Agenda
    public void darDeBajaConsultorDeAgenda(int semana, int anio, int legajoConsultor) throws ConfigurarAgendaException {
        FachadaPersistencia.getInstance().iniciarTransaccion();
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int semanaActual = fechaActual.get(weekFields.weekOfYear());
            int anioActual = fechaActual.getYear();

            // Validar si la agenda que se intenta modificar pertenece a una semana pasada o la semana actual
            if (anio < anioActual || (anio == anioActual && semana < semanaActual)) { //if (anio < anioActual || (anio == anioActual && semana <= semanaActual)) {
                throw new ConfigurarAgendaException("No se pueden eliminar consultores de agendas pasadas.");
            }
        // Buscar la agenda correspondiente al año y semana
        List<DTOCriterio> lcriterioAgenda = new ArrayList<>();
        DTOCriterio criterioAnio = new DTOCriterio();
        criterioAnio.setAtributo("anio");
        criterioAnio.setOperacion("=");
        criterioAnio.setValor(anio);
        lcriterioAgenda.add(criterioAnio);

        DTOCriterio criterioSemana = new DTOCriterio();
        criterioSemana.setAtributo("semana");
        criterioSemana.setOperacion("=");
        criterioSemana.setValor(semana);
        lcriterioAgenda.add(criterioSemana);

        List agendasEncontradas = FachadaPersistencia.getInstance().buscar("Agenda", lcriterioAgenda);
        if (agendasEncontradas.isEmpty()) {
            throw new ConfigurarAgendaException("No se encontró la agenda para la semana y año especificados.");
        }

        Agenda agenda = (Agenda) agendasEncontradas.get(0);

        // Buscar el consultor por legajo
        List<DTOCriterio> lcriterioConsultor = new ArrayList<>();
        DTOCriterio criterioLegajo = new DTOCriterio();
        criterioLegajo.setAtributo("legajoConsultor");
        criterioLegajo.setOperacion("=");
        criterioLegajo.setValor(legajoConsultor);
        lcriterioConsultor.add(criterioLegajo);

        List<Object> consultoresEncontrados = FachadaPersistencia.getInstance().buscar("Consultor", lcriterioConsultor);
        if (consultoresEncontrados.isEmpty()) {
            throw new ConfigurarAgendaException("No se encontró el consultor con el legajo especificado.");
        }

        Consultor consultorElegido = (Consultor) consultoresEncontrados.get(0);
        
        if ((anio == anioActual && semana == semanaActual)){
            // Verificar si el consultor está asignado a algún trámite no finalizado
            List<DTOCriterio> lcriterio2 = new ArrayList<>();
            DTOCriterio criterioTramite = new DTOCriterio();
            criterioTramite.setAtributo("consultor");
            criterioTramite.setOperacion("=");
            criterioTramite.setValor(consultorElegido);
            lcriterio2.add(criterioTramite);

            DTOCriterio criterioTramite1 = new DTOCriterio();
            criterioTramite1.setAtributo("fechaHoraFinTramite");
            criterioTramite1.setOperacion("=");
            criterioTramite1.setValor(null);
            lcriterio2.add(criterioTramite1);

            DTOCriterio criterioTramite2 = new DTOCriterio();
            criterioTramite2.setAtributo("fechaHoraBajaTramite");
            criterioTramite2.setOperacion("=");
            criterioTramite2.setValor(null);
            lcriterio2.add(criterioTramite2);

            List<Object> tramitesRelacionados = FachadaPersistencia.getInstance().buscar("Tramite", lcriterio2);

            if (!tramitesRelacionados.isEmpty()) {
                // Si hay trámites no finalizados, lanzar excepción
                throw new ConfigurarAgendaException("No se puede eliminar porque está asignado a un trámite activo.");
            }
        }
        
        // Eliminar el consultor de la agenda
        Consultor consultorAEliminar = new Consultor();
        if (agenda != null && consultorElegido != null) {
            // Eliminar la relación consultor-agenda
            for (Consultor cons : agenda.getConsultores()) {
                if (consultorElegido.getLegajoConsultor() == cons.getLegajoConsultor()){
                    consultorAEliminar = cons;
                }
            }
            if (consultorAEliminar.getLegajoConsultor() != 0){
                agenda.getConsultores().remove(consultorAEliminar);  
            } else {
                throw new ConfigurarAgendaException("El consultor no se encuentra en la agenda");
            }

            // Guardar la agenda
            FachadaPersistencia.getInstance().guardar(agenda);
        } else {
        }
        
        // Finalizar la transacción
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
    
}
