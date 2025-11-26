/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion;

import IniciarSesion.dto.DTOCambioContrasenia;
import IniciarSesion.dto.DtoIniciarSesion;
import IniciarSesion.dto.IniciarSesionExcepcion;
import entidades.Usuario;
import jakarta.persistence.TypedQuery;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author marti
 */
public class IniciarSesionControlador { 
  private IniciarSesionExperto userS = new IniciarSesionExperto() ;

     public DtoIniciarSesion findUser(String nombreUsuario, String password) throws IniciarSesionExcepcion {
         return userS.findUser(nombreUsuario, password);
     }
    public void CambioContrasenia(String usuario) throws IniciarSesionExcepcion {
        userS.CambioContrasenia(usuario);
    }
      
    public String confirmarNuevaContrasenia(int CodValidacion) throws IniciarSesionExcepcion{
        return userS.confirmarNuevaContrasenia(CodValidacion);
    }
    
     public void crearNuevaContrasenia(DTOCambioContrasenia dtoc) throws IniciarSesionExcepcion{
         userS.crearNuevaContrasenia( dtoc);
     }
     
     public boolean validarCodigo(int codigoValidacion,int codValidacionIn) throws IniciarSesionExcepcion{
        return userS.validarCodigo(codigoValidacion, codValidacionIn);
     }
    
    
}
