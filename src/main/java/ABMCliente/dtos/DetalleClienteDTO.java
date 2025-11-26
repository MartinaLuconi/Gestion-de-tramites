package ABMCliente.dtos;

import java.sql.Timestamp;

public class DetalleClienteDTO {
    private String cuitCliente;
    private String nombreApellidoCliente;
    private String direccion;
    private String mailCliente;
    private double telefonoCliente;

//constructor
//    public DTODetalleCliente(int cuitCliente, String nombreApellidoCliente, String direccion, String mailCliente, int telefonoCliente) {
//        this.cuitCliente = cuitCliente;
//        this.nombreApellidoCliente = nombreApellidoCliente;
//        this.direccion = direccion;
//        this.mailCliente = mailCliente;
//        this.telefonoCliente = telefonoCliente;
//    }
       
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
        
}