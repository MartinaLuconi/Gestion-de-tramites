package ConfigurarAgenda;

import ConfigurarAgenda.dtos.DTOConsultor;
import ConfigurarAgenda.dtos.DTOConsultorAgenda;
import ConfigurarAgenda.dtos.DTOPantallaAgenda;
import ConfigurarAgenda.exceptions.ConfigurarAgendaException;
import java.util.List;

public class ControladorConfigurarAgenda {
    private ExpertoConfigurarAgenda expertoConfigurarAgenda = new ExpertoConfigurarAgenda();
    
    public DTOPantallaAgenda mostrarAgenda(int anio, int mes){
        return expertoConfigurarAgenda.mostrarAgenda(anio, mes);
    }
    
    public List<DTOConsultorAgenda> buscarConsultoresAsignados(int anio, int semana) throws ConfigurarAgendaException {
        return expertoConfigurarAgenda.buscarConsultoresAsignados(anio, semana);
    }
    
    public List<DTOConsultor> buscarConsultoresNoAsignados(int anio, int semana) throws ConfigurarAgendaException {
        return expertoConfigurarAgenda.buscarConsultoresNoAsignados(anio, semana);
    }
    
    public void agregarConsultor(int semana,int anio,int legajoConsultor) throws ConfigurarAgendaException{
        expertoConfigurarAgenda.agregarConsultor(semana, anio, legajoConsultor);
    }
    
    public void darDeBajaConsultorDeAgenda(int semana, int anio, int legajoConsultor) throws ConfigurarAgendaException {
        expertoConfigurarAgenda.darDeBajaConsultorDeAgenda(semana, anio, legajoConsultor);
    }
    
    public List<DTOConsultor> buscarConsultores(){
        return expertoConfigurarAgenda.buscarConsultores();
    }
}
