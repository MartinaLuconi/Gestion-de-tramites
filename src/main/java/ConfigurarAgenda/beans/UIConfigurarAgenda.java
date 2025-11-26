package ConfigurarAgenda.beans;

import ConfigurarAgenda.ControladorConfigurarAgenda;
import ConfigurarAgenda.dtos.DTOAgendaSemana;
import ConfigurarAgenda.dtos.DTOConsultorAgenda;
import ConfigurarAgenda.dtos.DTOPantallaAgenda;
import ConfigurarAgenda.exceptions.ConfigurarAgendaException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import utils.BeansUtils;

@Named("uiConfigurarAgenda")
@ViewScoped
public class UIConfigurarAgenda implements Serializable {

    private ControladorConfigurarAgenda controladorConfigurarAgenda = new ControladorConfigurarAgenda();
    private int anio;
    private int mes;
    private int semana;
    private List<AgendaSemanaGrillaUI> agendasSemanaGrilla;
    private List<Integer> listaAnios;

    public List<Integer> getListaAnios() {
        if (listaAnios == null) {
            listaAnios = new ArrayList<>();
            for (int i = 2000; i <= 2050; i++) {
                listaAnios.add(i);
            }
        }
        return listaAnios;
    }
    
    public String getMesNombre() {
        Map<Integer, String> meses = new HashMap<>();
        meses.put(1, "Enero");
        meses.put(2, "Febrero");
        meses.put(3, "Marzo");
        meses.put(4, "Abril");
        meses.put(5, "Mayo");
        meses.put(6, "Junio");
        meses.put(7, "Julio");
        meses.put(8, "Agosto");
        meses.put(9, "Septiembre");
        meses.put(10, "Octubre");
        meses.put(11, "Noviembre");
        meses.put(12, "Diciembre");

        return meses.get(mes); // Devuelve el nombre del mes correspondiente
    }
    
    public UIConfigurarAgenda() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        // Obtener los parámetros de anio y mes de la URL
        String anioParam = request.getParameter("anio");
        String mesParam = request.getParameter("mes");

        try {
            // Verificar que los parámetros no sean nulos y parsear
            if (anioParam != null && mesParam != null) {
                int anio = Integer.parseInt(anioParam);
                int mes = Integer.parseInt(mesParam);

                // Validar los valores de anio y mes
                if (anio >= 2000 && anio <= 2050) {
                    if (mes >= 1 && mes <= 12) {
                        this.anio = anio;
                        this.mes = mes;
                    } else {
                        // Mostrar mensaje de error para mes inválido
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Mes inválido", "El mes debe estar entre 1 y 12."));
                        setDefaultDate(); // Usar la fecha actual como predeterminada
                    }
                } else {
                    // Mostrar mensaje de error para año inválido
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Año inválido", "El año debe estar entre 2000 y 2050."));
                    setDefaultDate(); // Usar la fecha actual como predeterminada
                }
            } else {
                // Si no se reciben parámetros, usar la fecha actual
                setDefaultDate();
            }
        } catch (NumberFormatException e) {
            // Manejo de excepción para entradas inválidas
            System.out.println("Error al parsear anio o mes: " + e.getMessage());
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Entrada inválida", "Año o mes no son números válidos."));
            setDefaultDate(); // Usar la fecha actual como predeterminada
        }
    }


    // Método para establecer la fecha por defecto
    private void setDefaultDate() {
        LocalDate today = LocalDate.now();
        this.anio = today.getYear();
        this.mes = today.getMonthValue(); // Mes entre 1 y 12    
    }

    public ControladorConfigurarAgenda getControladorConfigurarAgenda() {
        return controladorConfigurarAgenda;
    }

    public void setControladorConfigurarAgenda(ControladorConfigurarAgenda controladorConfigurarAgenda) {
        this.controladorConfigurarAgenda = controladorConfigurarAgenda;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }
    
    public List<AgendaSemanaGrillaUI> buscarAgendas() {
        System.out.println("Año: " + anio);
        System.out.println("Mes: " + mes);

        agendasSemanaGrilla = new ArrayList<>(); // Inicializar la lista
        DTOPantallaAgenda dtoPantallaAgenda = controladorConfigurarAgenda.mostrarAgenda(anio, mes);

        // Iterar sobre las agendas y crear los DTOs correspondientes
        for (DTOAgendaSemana dtoAgendaSemana : dtoPantallaAgenda.getDtoAgendaSemana()) {
            AgendaSemanaGrillaUI agendasSemanaGrillaUI = new AgendaSemanaGrillaUI();

            // Asignar los atributos de la agenda semanal
            agendasSemanaGrillaUI.setAnio(dtoAgendaSemana.getAnio());
            agendasSemanaGrillaUI.setSemana(dtoAgendaSemana.getSemana());
            agendasSemanaGrillaUI.setFechaDesdeAgenda(dtoAgendaSemana.getFechaDesdeAgenda());
            agendasSemanaGrillaUI.setFechaHastaAgenda(dtoAgendaSemana.getFechaHastaAgenda());

            // Imprimir los atributos de la agenda semanal
            System.out.println("Año: " + dtoAgendaSemana.getAnio());
            System.out.println("Semana: " + dtoAgendaSemana.getSemana());
            System.out.println("Fecha Desde: " + dtoAgendaSemana.getFechaDesdeAgenda());
            System.out.println("Fecha Hasta: " + dtoAgendaSemana.getFechaHastaAgenda());

            // Agregar consultores a la agenda
            for (DTOConsultorAgenda dtoConsultorAgenda : dtoAgendaSemana.getDtoConsultorAgenda()) {
                agendasSemanaGrillaUI.addDtoConsultorAgenda(dtoConsultorAgenda);

                // Imprimir los atributos de cada consultor
                System.out.println("Consultor - Legajo: " + dtoConsultorAgenda.getLegajoConsultor());
                System.out.println("Consultor - Nombre y Apellido: " + dtoConsultorAgenda.getNombreApellidoConsultor());
            }

            // Añadir la agenda de la semana a la lista final
            agendasSemanaGrilla.add(agendasSemanaGrillaUI);
        }

        return agendasSemanaGrilla; // Asegúrate de que esto se retorne correctamente
    }

    public List<AgendaSemanaGrillaUI> getAgendasSemanaGrilla() {
    if (agendasSemanaGrilla == null) {
        agendasSemanaGrilla = new ArrayList<>(); // Asegurarse de que no sea null
    }
    return agendasSemanaGrilla;
}
    
    public void bajaConsultorDeAgenda(int anio, int semana, int legajoConsultor) {
        try {
            // Verificar si el legajo del consultor es válido (mayor que cero)
            if (legajoConsultor > 0) {
                // Llamar al controlador para eliminar la relación en la agenda
                controladorConfigurarAgenda.darDeBajaConsultorDeAgenda(semana, anio, legajoConsultor);

                // Agregar un mensaje de éxito en la interfaz
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Consultor dado de baja de la agenda (año: " + anio + ", semana: " + semana + ")"));

                // Actualizar la lista de agendas si es necesario
                buscarAgendas();  // Vuelve a buscar las agendas actualizadas
            } else {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El legajo del consultor es inválido", ""));
            }
        } catch (ConfigurarAgendaException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de baja consultor", e.getMessage()));
        }
    }
 
    public String irAgregarConsultorAgenda(int anio, int semana) {
        try {
            LocalDate fechaActual = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int semanaActual = fechaActual.get(weekFields.weekOfYear());
            int anioActual = fechaActual.getYear();

            // Validar si la agenda que se intenta modificar pertenece a una semana pasada o la semana actual
            if (anio < anioActual || (anio == anioActual && semana < semanaActual)) { //if (anio < anioActual || (anio == anioActual && semana <= semanaActual)) {
                // Mostrar mensaje de error si la agenda es actual o pasada
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: No puede agregar Consultores en Agendas actuales o pasadas", ""));
                return null; // No redirigir a la nueva página
            } else {
                // Redirigir a la página para agregar un nuevo consultor
                BeansUtils.guardarUrlAnterior();
                return "agregarConsultorAgenda.xhtml?faces-redirect=true&anio=" + anio + "&semana=" + semana;
            }
        } catch (Exception e) {
            // Manejo de excepciones genéricas
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado: " + e.getMessage(), ""));
            return null; // No redirigir a la nueva página
        }
    }
    
    public boolean esAgendaPasada(int anio, int semana) {
        LocalDate fechaActual = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int semanaActual = fechaActual.get(weekFields.weekOfYear());
        int anioActual = fechaActual.getYear();

        // Validar si la agenda es actual o pasada
        return anio < anioActual || (anio == anioActual && semana < semanaActual); // return anio < anioActual || (anio == anioActual && semana <= semanaActual);
    }
    
}
