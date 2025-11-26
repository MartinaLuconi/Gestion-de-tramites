package ABMCliente.dtos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ListaClienteDTO {
    private String cuitCliente;
    private String nombreApellidoCliente;
    private Timestamp fechaHoraBajaCliente;


    //Getter and Setter (METODOS)
    public String getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    public Timestamp getFechaHoraBajaCliente() {
        return fechaHoraBajaCliente;
    }

    public void setFechaHoraBajaCliente(Timestamp fechaHoraBajaCliente) {
        this.fechaHoraBajaCliente = fechaHoraBajaCliente;
    }


 }    
 
