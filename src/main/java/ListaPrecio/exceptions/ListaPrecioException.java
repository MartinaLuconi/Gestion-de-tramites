/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.exceptions;

/**
 *
 * @author arode
 */
public class ListaPrecioException extends Exception{
    
    public ListaPrecioException(String message){
        super(message);
    }
    
    public ListaPrecioException(String message, Throwable cause){
        super(message, cause);
    }  
}
