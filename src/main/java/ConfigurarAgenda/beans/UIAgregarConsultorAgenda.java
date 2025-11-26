package ConfigurarAgenda.beans;

import ConfigurarAgenda.ControladorConfigurarAgenda;
import ConfigurarAgenda.dtos.DTOConsultor;
import ConfigurarAgenda.dtos.DTOConsultorAgenda;
import ConfigurarAgenda.exceptions.ConfigurarAgendaException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import utils.BeansUtils;


@Named("uiAgregarConsultorAgenda")
@ViewScoped
public class UIAgregarConsultorAgenda implements Serializable {

    private ControladorConfigurarAgenda controladorConfigurarAgenda = new ControladorConfigurarAgenda();
    private List<ConsultorGrillaUI> consultoresNoAsignados = new ArrayList<>();
    private List<ConsultorAgendaGrillaUI> consultoresAsignados = new ArrayList<>();
    private int semana;
    private int mes;
    private int anio;
    
    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        int anioTemp = 0; // Variable temporal para el año
        int semanaTemp = 0; // Variable temporal para la semana

        String previousUrl = request.getHeader("referer"); // Obtener la URL anterior

        try {
            // Verificar y obtener los parámetros de la URL
            String anioParam = request.getParameter("anio");
            String semanaParam = request.getParameter("semana");

            // Si los parámetros existen, parsearlos y asignarlos a las variables temporales
            if (anioParam != null) {
                anioTemp = Integer.parseInt(anioParam);
                // Validar el año
                if (anioTemp < 2000 || anioTemp > 2050) {
                    throw new NumberFormatException("El año debe estar entre 2000 y 2050.");
                }
            }

            if (semanaParam != null) {
                semanaTemp = Integer.parseInt(semanaParam);
                // Validar la semana
                if (semanaTemp < 1 || semanaTemp > 53) {
                    throw new NumberFormatException("La semana debe estar entre 1 y 53.");
                }
            }

            // Si las validaciones pasaron, asignar a las variables de instancia
            this.anio = anioTemp;
            this.semana = semanaTemp;

            // Calcular el mes a partir de la semana y año
            this.mes = calcularMesDesdeSemana(anio, semana);

            // Validación adicional: si es semana 1 y el mes calculado es diciembre (12), ajustar a semana 53
            if (this.semana == 1 && this.mes == 12) {
                this.semana = 53;
            }

            // Llamar al método para cargar los consultores no asignados a la agenda
            this.consultoresNoAsignados = cargarConsultoresNoAsignados(anio, semana);
            // Llamar al método para cargar los consultores asignados a la agenda
            this.consultoresAsignados = cargarConsultoresAsignados(anio, semana);

        } catch (ConfigurarAgendaException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar los consultores", e.getMessage()));
            // Redirigir a la URL anterior
            redirectToUrl(previousUrl);
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en los parámetros de la URL", e.getMessage()));
            // Redirigir a la URL anterior
            redirectToUrl(previousUrl);
        }
    }

    // Método para redirigir a la URL proporcionada
    private void redirectToUrl(String url) {
        try {
            if (url != null && !url.isEmpty()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            } else {
                // Redirigir a una página predeterminada si no hay URL anterior
                FacesContext.getCurrentInstance().getExternalContext().redirect("paginaDeInicio.xhtml"); // Cambia por tu página deseada
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejar la excepción de redirección
        }
    }



    // Método para calcular el mes a partir de la semana y el año
    private int calcularMesDesdeSemana(int anio, int semana) {
        Calendar calendar = Calendar.getInstance();
        calendar.setWeekDate(anio, semana, Calendar.MONDAY); // Establecer a lunes de la semana
        return calendar.get(Calendar.MONTH) + 1; // Mes es 0-indexed, sumar 1
    }


    // Getters y setters para los atributos
    public ControladorConfigurarAgenda getControladorConfigurarAgenda() {
        return controladorConfigurarAgenda;
    }

    public void setControladorConfigurarAgenda(ControladorConfigurarAgenda controladorConfigurarAgenda) {
        this.controladorConfigurarAgenda = controladorConfigurarAgenda;
    }

    public List<ConsultorAgendaGrillaUI> getConsultoresAsignados() {
        return consultoresAsignados;
    }

    public void setConsultoresAsignados(List<ConsultorAgendaGrillaUI> consultoresAsignados) {
        this.consultoresAsignados = consultoresAsignados;
    }

    public List<ConsultorGrillaUI> getConsultoresNoAsignados() {
        return consultoresNoAsignados;
    }

    public void setConsultoresNoAsignados(List<ConsultorGrillaUI> consultoresNoAsignados) {
        this.consultoresNoAsignados = consultoresNoAsignados;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    
    // Método para buscar los consultores
    public List<ConsultorGrillaUI> cargarConsultoresNoAsignados(int anio, int semana) throws ConfigurarAgendaException{
        
        List<DTOConsultor> dtoConsultores = controladorConfigurarAgenda.buscarConsultoresNoAsignados(anio, semana); // Método existente para buscar consultores
        
        List<ConsultorGrillaUI> consultoresGrilla = new ArrayList<>();
        
        // Iterar sobre los DTOs de consultores y crear los UI correspondientes
        for (DTOConsultor dto : dtoConsultores) {
            ConsultorGrillaUI consultorNoAsignado = new ConsultorGrillaUI();
            consultorNoAsignado.setLegajoConsultor(dto.getLegajoConsultor());
            consultorNoAsignado.setNombreApellidoConsultor(dto.getNombreApellidoConsultor());
            consultoresGrilla.add(consultorNoAsignado); // Agregar a la lista de consultores de la UI            
       }        
        consultoresNoAsignados = consultoresGrilla;
                
        return consultoresGrilla;
    }
    
    public List<ConsultorAgendaGrillaUI> cargarConsultoresAsignados(int anio, int semana) throws ConfigurarAgendaException {
        // Obtener la lista de consultores asignados de la agenda
        List<DTOConsultorAgenda> dtoConsultoresAsignados = controladorConfigurarAgenda.buscarConsultoresAsignados(anio, semana);

        List<ConsultorAgendaGrillaUI> consultoresAgendaGrilla = new ArrayList<>();

        // Recorrer la lista de DTOConsultorAgenda y convertir a ConsultorAgendaGrillaUI
        for (DTOConsultorAgenda dto : dtoConsultoresAsignados) {
            ConsultorAgendaGrillaUI consultorUI = new ConsultorAgendaGrillaUI();
            consultorUI.setLegajoConsultor(dto.getLegajoConsultor());
            consultorUI.setNombreApellidoConsultor(dto.getNombreApellidoConsultor());
            consultoresAgendaGrilla.add(consultorUI);
        }

        // Asignar la lista convertida a consultoresAsignados
        consultoresAsignados = consultoresAgendaGrilla;

        // Retornar la lista de consultores convertidos
        return consultoresAgendaGrilla;
    }
    
    public List<ConsultorAgendaGrillaUI> cargarConsultoresEliminarPrueba() throws ConfigurarAgendaException {
        // Obtener la lista de consultores asignados de la agenda
        List<DTOConsultor> dtoConsultoresPrueba = controladorConfigurarAgenda.buscarConsultores();

        List<ConsultorAgendaGrillaUI> consultoresAgendaGrilla = new ArrayList<>();

        // Recorrer la lista de DTOConsultorAgenda y convertir a ConsultorAgendaGrillaUI
        for (DTOConsultor dto : dtoConsultoresPrueba) {
            ConsultorAgendaGrillaUI consultorUI = new ConsultorAgendaGrillaUI();
            consultorUI.setLegajoConsultor(dto.getLegajoConsultor());
            consultorUI.setNombreApellidoConsultor(dto.getNombreApellidoConsultor());
            consultoresAgendaGrilla.add(consultorUI);
        }

        // Asignar la lista convertida a consultoresAsignados
        consultoresAsignados = consultoresAgendaGrilla;

        // Retornar la lista de consultores convertidos
        return consultoresAgendaGrilla;
    }
    public List<ConsultorGrillaUI> cargarConsultoresAgregarPrueba() throws ConfigurarAgendaException {
        // Obtener la lista de consultores asignados de la agenda
        List<DTOConsultor> dtoConsultoresPrueba = controladorConfigurarAgenda.buscarConsultores();

        List<ConsultorGrillaUI> consultoresGrilla = new ArrayList<>();

        // Recorrer la lista de DTOConsultorAgenda y convertir a ConsultorGrillaUI
        for (DTOConsultor dto : dtoConsultoresPrueba) {
            ConsultorGrillaUI consultorUI = new ConsultorGrillaUI();
            consultorUI.setLegajoConsultor(dto.getLegajoConsultor());
            consultorUI.setNombreApellidoConsultor(dto.getNombreApellidoConsultor());
            consultoresGrilla.add(consultorUI);
        }

        // Asignar la lista convertida a consultoresAsignados
        consultoresNoAsignados = consultoresGrilla;

        // Retornar la lista de consultores convertidos
        return consultoresGrilla;
    }
    
    // Nuevo método para dar de baja un consultor de la agenda
    public void bajaConsultorDeAgenda(int semana, int anio, ConsultorAgendaGrillaUI consultor) {
        try {
            // Verificamos que el consultor no sea nulo
            if (consultor != null) {
                
                // Llama al controlador para eliminar la relación en la agenda
                controladorConfigurarAgenda.darDeBajaConsultorDeAgenda(semana, anio, consultor.getLegajoConsultor());

                // Agregamos un mensaje de éxito a la interfaz
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Consultor dado de baja de la agenda: " + consultor.getNombreApellidoConsultor()));

                // **NO** eliminamos el consultor de la lista de UI
            } else {
                System.out.println("Error: El consultor es nulo");
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El consultor es nulo", ""));
            }
        } catch (ConfigurarAgendaException e) {
            System.out.println("Error al dar de baja consultor: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de baja consultor", e.getMessage()));
        }
    }
    
    // Acción para agregar un consultor a la agenda
    public void agregarAAgenda(int anio, int semana, ConsultorGrillaUI consultor) {
        try {
            controladorConfigurarAgenda.agregarConsultor(semana, anio, consultor.getLegajoConsultor());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Consultor agregado a la agenda: " + consultor.getNombreApellidoConsultor()));
        } catch (ConfigurarAgendaException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al agregar consultor", e.getMessage()));
        }
    }
    
    // Volver a la lista de agendas
    public String volverAgenda(int anio, int mes) {
        // Redirigir a la página ConfigurarAgenda con los parámetros de anio y mes
        return "configurarAgenda.xhtml?faces-redirect=true&anio=" + anio + "&mes=" + mes;
    }

    public boolean esAgendaPasada(int anio, int semana) {
        LocalDate fechaActual = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int semanaActual = fechaActual.get(weekFields.weekOfYear());
        int anioActual = fechaActual.getYear();

        // Validar si la agenda es actual o pasada
        return anio < anioActual || (anio == anioActual && semana <= semanaActual);
    }
    
    public String irPrueba(int anio, int semana) {
        try {
                // Redirigir a la página para agregar un nuevo consultor
                BeansUtils.guardarUrlAnterior();
                return "agregarConsultorAgendaPRUEBA.xhtml?faces-redirect=true&anio=" + anio + "&semana=" + semana;

        } catch (Exception e) {
            // Manejo de excepciones genéricas
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado: " + e.getMessage(), ""));
            return null; // No redirigir a la nueva página
        }
    }
    
    public String volverDePrueba(int anio, int semana) {
        try {
                // Redirigir a la página para agregar un nuevo consultor
                BeansUtils.guardarUrlAnterior();
                return "agregarConsultorAgenda.xhtml?faces-redirect=true&anio=" + anio + "&semana=" + semana;

        } catch (Exception e) {
            // Manejo de excepciones genéricas
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado: " + e.getMessage(), ""));
            return null; // No redirigir a la nueva página
        }
    }
}
