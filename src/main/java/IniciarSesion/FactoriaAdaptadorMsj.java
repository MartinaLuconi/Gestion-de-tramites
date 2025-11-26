/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion;

/**
 *
 * @author marti
 */
public class FactoriaAdaptadorMsj {
    private static FactoriaAdaptadorMsj instancia = null;
    public static synchronized FactoriaAdaptadorMsj getInstancia(){
        
        if(instancia== null){
            instancia=new FactoriaAdaptadorMsj();
        }
        
        return instancia;
    }
    public  AdaptadorMsj obtenerAdaptadorMensaje(int tipo)
    {
        if(tipo==1)
        {
            return new AdaptadorMail();
        }
        else
        {
            return new AdaptadorMail3();
        }
    }
    
}
