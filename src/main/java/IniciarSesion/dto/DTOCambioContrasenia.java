/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion.dto;

/**
 *
 * @author marti
 */
public class DTOCambioContrasenia {
    
    private String usuario; 
    private String password;
    private String password2;
    private int codValidacion;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public int getCodValidacion() {
        return codValidacion;
    }

    public void setCodValidacion(int codValidacion) {
        this.codValidacion = codValidacion;
    }
    
    
    
    
}
