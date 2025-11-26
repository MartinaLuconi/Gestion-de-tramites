package entidades;

import java.sql.Timestamp;

public class Cliente extends Entidad{
    private String cuitCliente;
    private String nombreApellidoCliente;
    private String direccionCliente;
    private String mailCliente;
    private double telefonoCliente;
    private Timestamp fechaHoraAltaCliente;
    private Timestamp fechaHoraBajaCliente;
    
    // Constructor
    public Cliente(){
}
    // Getters y Setters
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

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getMailCliente() {
        return mailCliente;
    }

    public void setMailCliente(String mailCliente) {
        this.mailCliente = mailCliente;
    }

    public double getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(double telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public Timestamp getFechaHoraAltaCliente() {
        return fechaHoraAltaCliente;
    }

    public void setFechaHoraAltaCliente(Timestamp fechaHoraAltaCliente) {
        this.fechaHoraAltaCliente = fechaHoraAltaCliente;
    }

    public Timestamp getFechaHoraBajaCliente() {
        return fechaHoraBajaCliente;
    }

    public void setFechaHoraBajaCliente(Timestamp fechaHoraBajaCliente) {
        this.fechaHoraBajaCliente = fechaHoraBajaCliente;
    }    
}
