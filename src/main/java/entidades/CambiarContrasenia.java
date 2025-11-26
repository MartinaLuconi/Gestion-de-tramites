/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import IniciarSesion.IniciarSesionControlador;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 *
 * @author marti
 */
public class CambiarContrasenia extends Entidad{
    

    private int codigoValidacion;
    private boolean activo;
    private Timestamp fechaHoraDesdeCodigo;
    private Timestamp fechaHoraHastaCodigo;
    private Usuario usuarioObj;
    
    

    public CambiarContrasenia() {
      
    }

   
    
    public int getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(int codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Timestamp getFechaHoraDesdeCodigo() {
        return fechaHoraDesdeCodigo;
    }

    public void setFechaHoraDesdeCodigo(Timestamp fechaHoraDesdeCodigo) {
        this.fechaHoraDesdeCodigo = fechaHoraDesdeCodigo;
    }

    public Timestamp getFechaHoraHastaCodigo() {
        return fechaHoraHastaCodigo;
    }

    public void setFechaHoraHastaCodigo(Timestamp fechaHoraHastaCodigo) {
        this.fechaHoraHastaCodigo = fechaHoraHastaCodigo;
    }

    public Usuario getUsuarioObj() {
        return usuarioObj;
    }

    public void setUsuarioObj(Usuario usuarioObj) {
        this.usuarioObj = usuarioObj;
    }
    
    
    
    
    
   
    
}
