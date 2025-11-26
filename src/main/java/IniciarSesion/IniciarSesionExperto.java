/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion;

import IniciarSesion.bean.UtilPass;
import IniciarSesion.dto.DTOCambioContrasenia;
import IniciarSesion.dto.DTOMensaje;
import IniciarSesion.dto.DtoIniciarSesion;
import IniciarSesion.dto.IniciarSesionExcepcion;
import entidades.CambiarContrasenia;
import entidades.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;





//import org.omnifaces.util.Messages.Message;

import utils.DTOCriterio;
import utils.FachadaPersistencia;

@ApplicationScoped
public class IniciarSesionExperto { //experto
  
     public DtoIniciarSesion findUser(String nombreUsuario, String password) throws IniciarSesionExcepcion {
         
         DtoIniciarSesion dtoIni = new DtoIniciarSesion();
         List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        
            DTOCriterio usuarioCriterio=new DTOCriterio();
            usuarioCriterio.setAtributo("usuario");
            usuarioCriterio.setOperacion("=");
            usuarioCriterio.setValor(nombreUsuario);
            lCriterio.add(usuarioCriterio);
            
            DTOCriterio contraCriterio=new DTOCriterio();
            contraCriterio.setAtributo("password");
            contraCriterio.setOperacion("=");
            String md5Pass=UtilPass.obtieneMD5(password);
            contraCriterio.setValor(md5Pass);
            lCriterio.add(contraCriterio);
//            
            List usuarioEncontrado =  FachadaPersistencia.getInstance().buscar("Usuario", lCriterio);
        
            Usuario usuario=null;
//           List listUsers =  FachadaPersistencia.getInstance().buscar("Usuario", lCriterio);
           if(usuarioEncontrado.size() ==0)
           {
             throw new IniciarSesionExcepcion("Usuario o contraseña incorrecta.");  
           }
           else
           {
             usuario = (Usuario) usuarioEncontrado.get(0);
             dtoIni.setNombreUsuario(usuario.getNombreUsuario());
             dtoIni.setUsuario(usuario.getUsuario());
             dtoIni.setUserCod(usuario.getCodUsuario());
           }
              

                  
           
           
           
           return dtoIni;
            
  }
     
     
     public void CambioContrasenia(String usuario) throws IniciarSesionExcepcion{
    List<DTOCriterio> lCriterio=new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("usuario");
            unCriterio.setOperacion("=");
            unCriterio.setValor(usuario);
            lCriterio.add(unCriterio);
        
//        Me larga error esto, como que ya esta declarado un poco mas arriba
        List<Object> objetoList= FachadaPersistencia.getInstance().buscar("Usuario", lCriterio);
        if(objetoList.size()<=0)
        {
            throw new IniciarSesionExcepcion("Nombre de usuario no existe"); 
        }
        Usuario usuarioEncontrado = (Usuario) objetoList.get(0);
        
        
        FachadaPersistencia.getInstance().iniciarTransaccion();
            List<DTOCriterio> lCriterioCC=new ArrayList<>();
             DTOCriterio unCriterioCC = new DTOCriterio();
            unCriterioCC.setAtributo("activo");
            unCriterioCC.setOperacion("=");
            unCriterioCC.setValor(true);
            lCriterioCC.add(unCriterioCC);
             unCriterioCC = new DTOCriterio();
            unCriterioCC.setAtributo("usuarioObj");
            unCriterioCC.setOperacion("=");
            unCriterioCC.setValor(usuarioEncontrado);
            lCriterioCC.add(unCriterioCC);
        
//        Me larga error esto, como que ya esta declarado un poco mas arriba
        List<Object> objetoListCC= FachadaPersistencia.getInstance().buscar("CambiarContrasenia", lCriterioCC);
       
        for (Object o : objetoListCC){
            CambiarContrasenia cambiarContrasenia = (CambiarContrasenia) o;
            cambiarContrasenia.setActivo(false);
            FachadaPersistencia.getInstance().guardar(cambiarContrasenia);
        }
        
        
        
              CambiarContrasenia cambioCont = new CambiarContrasenia();
                cambioCont.setActivo(true);
               
                cambioCont.setUsuarioObj(usuarioEncontrado);
                cambioCont.setFechaHoraDesdeCodigo(new Timestamp(System.currentTimeMillis()));
                
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(cambioCont.getFechaHoraDesdeCodigo().getTime());
                calendar.add(Calendar.MINUTE,10);
                
                Timestamp  fechaHoraHastaCodigo = new Timestamp (calendar.getTimeInMillis());
                
                cambioCont.setFechaHoraHastaCodigo(fechaHoraHastaCodigo);
                
                //genero codigo
               double codigoVal = 100000 + Math.random() * 900000;
               
               cambioCont.setCodigoValidacion((int)codigoVal);
               
               FachadaPersistencia.getInstance().guardar(cambioCont);
                
               FachadaPersistencia.getInstance().finalizarTransaccion();
      
               DTOMensaje dtocorreo = new DTOMensaje();
                dtocorreo.setUsuario(cambioCont.getUsuarioObj());
                dtocorreo.setAsunto("Código Validación - Gestión de Trámites");
                dtocorreo.setMensaje("Su codigo de validacion es "+cambioCont.getCodigoValidacion());
                //dtocorreo.setMensaje("ingrese al siguiente enlace para cambiar su contraseña: \n http://localhost:8080/admin/public/validacionContrasenia.xhtml?codigoValidacion="+cambioCont.getCodigoValidacion());
                AdaptadorMsj am=FactoriaAdaptadorMsj.getInstancia().obtenerAdaptadorMensaje(1);
                String resultado=am.enviarMensaje(dtocorreo);
               System.out.println(resultado);
                 if(resultado.length() >0 )
                 {
                      throw new IniciarSesionExcepcion("Error en el envio: "+resultado);  
                 }

            

         }
     
     public String confirmarNuevaContrasenia(int codValidacion) throws IniciarSesionExcepcion{
         
       String  usuarioNom;
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List<DTOCriterio> lCriterio=new ArrayList<>();
         DTOCriterio criterioCC = new DTOCriterio();

            criterioCC.setAtributo("activo");
            criterioCC.setOperacion("=");
            criterioCC.setValor(true);
            lCriterio.add(criterioCC);
            
         criterioCC = new DTOCriterio();

            criterioCC.setAtributo("fechaHoraDesdeCodigo");
            criterioCC.setOperacion("<=");
            criterioCC.setValor(new Timestamp(System.currentTimeMillis()));
            lCriterio.add(criterioCC);
            
       criterioCC = new DTOCriterio();

            criterioCC.setAtributo("fechaHoraHastaCodigo");
            criterioCC.setOperacion(">=");
            criterioCC.setValor(new Timestamp(System.currentTimeMillis()));
            lCriterio.add(criterioCC);
            
        criterioCC = new DTOCriterio();

            criterioCC.setAtributo("codigoValidacion");
            criterioCC.setOperacion("=");
            criterioCC.setValor(codValidacion);
            lCriterio.add(criterioCC);
            
            
        List cambContra = FachadaPersistencia.getInstance().buscar("CambiarContrasenia", lCriterio);
        
        if (cambContra.size()==0){
            
             throw new IniciarSesionExcepcion("El código no es válido"); 
            
        }else{
            
          CambiarContrasenia cambCon =(CambiarContrasenia) cambContra.get(0);
         
          
          usuarioNom= cambCon.getUsuarioObj().getNombreUsuario();
          
            }
        
        return  usuarioNom;
        
        
        }
     
     public void crearNuevaContrasenia(DTOCambioContrasenia dtoc) throws IniciarSesionExcepcion{
         
         
         if (!dtoc.getPassword().equals(dtoc.getPassword2())){
             throw new IniciarSesionExcepcion("Las contraseñas deben ser iguales"); 
         }else{
             
            FachadaPersistencia.getInstance().iniciarTransaccion();
               List<DTOCriterio> lCriterio=new ArrayList<>();
                DTOCriterio criterioCC = new DTOCriterio();

                   criterioCC.setAtributo("activo");
                   criterioCC.setOperacion("=");
                   criterioCC.setValor(true);
                   lCriterio.add(criterioCC);

                criterioCC = new DTOCriterio();

                   criterioCC.setAtributo("fechaHoraDesdeCodigo");
                   criterioCC.setOperacion("<=");
                   criterioCC.setValor(new Timestamp(System.currentTimeMillis()));
                   lCriterio.add(criterioCC);

              criterioCC = new DTOCriterio();

                   criterioCC.setAtributo("fechaHoraHastaCodigo");
                   criterioCC.setOperacion(">=");
                   criterioCC.setValor(new Timestamp(System.currentTimeMillis()));
                   lCriterio.add(criterioCC);

               criterioCC = new DTOCriterio();

                   criterioCC.setAtributo("codigoValidacion");
                   criterioCC.setOperacion("=");
                   criterioCC.setValor(dtoc.getCodValidacion());
                   lCriterio.add(criterioCC);


               List cambContra = FachadaPersistencia.getInstance().buscar("CambiarContrasenia", lCriterio);

                CambiarContrasenia cambCon =(CambiarContrasenia) cambContra.get(0);

                   if(cambCon.getCodigoValidacion()== dtoc.getCodValidacion()){
                       //busco el usuario
                       Usuario usuario = cambCon.getUsuarioObj();
                       //le sobreescribo la contraseña
                       usuario.setPassword(UtilPass.obtieneMD5(dtoc.getPassword2()));
                       FachadaPersistencia.getInstance().guardar(usuario);
                       //desactivar cambio
                       cambCon.setActivo(false);
                       FachadaPersistencia.getInstance().guardar(cambCon);
                       
                       FachadaPersistencia.getInstance().finalizarTransaccion();
                   }else {
                       throw new IniciarSesionExcepcion("El código ya no es válido. Pida uno nuevamente."); 

                   }

             
             
         }
        
         
         
         
     }
     
     public boolean validarCodigo(int codigoValidacion,int codValidacionIn) throws IniciarSesionExcepcion{
         boolean valido;
         valido=false;
         if(codigoValidacion==codValidacionIn){
             
             valido=true;
         }else{
             throw new IniciarSesionExcepcion("El código ingresado es incorrecto"); 
         }
         return valido;
     }
        
       
         
     }

