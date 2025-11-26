
package ABMCliente;

import ABMCliente.dtos.DetalleClienteDTO;
import ABMCliente.dtos.DetalleClienteDTOIn;
import ABMCliente.dtos.ListaClienteDTO;
import entidades.Cliente;
import entidades.Tramite;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author Dell
 */
public class ExpertoABMCliente {
     
    //Metodo para buscar Clientes
    public List<ListaClienteDTO> obtenerListaCliente(String cuitCliente, String nombreApellidoCliente){
        //lista que devuelve el metodo
        //List<ListaClienteDTO> listaClienteDTOs = new ArrayList<>();
        
        //busqueda buscar("Cliente", "") trae todos los clientes
        List<DTOCriterio> lCriterio=new ArrayList<>();
        //DTOCriterio unCriterio=new DTOCriterio(); Esto se pone asi si necesito utilizar criterio para buscar
         //Obtengo la lista de OBJETOS(clientes) desde la fachada de persistencia    
        //List<Object> objetoList= FachadaPersistencia.getInstance().buscar("Cliente", lCriterio);
        
        if(!cuitCliente.isEmpty()) {
            DTOCriterio unCriterio= new DTOCriterio();
            unCriterio.setAtributo("cuitCliente");
            unCriterio.setOperacion("like");
            unCriterio.setValor(cuitCliente);
            lCriterio.add(unCriterio);
        }
        
        if (nombreApellidoCliente.trim().length()>0){
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreApellidoCliente");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreApellidoCliente);
            lCriterio.add(unCriterio);
        }

        List<Object> objetoList= FachadaPersistencia.getInstance().buscar("Cliente", lCriterio);
        List<ListaClienteDTO> listaClienteDTOs = new ArrayList<>();

         for(Object x : objetoList){
             Cliente cliente = (Cliente) x;
             ListaClienteDTO listaClienteDTO = new ListaClienteDTO();
                listaClienteDTO.setCuitCliente(cliente.getCuitCliente());
                listaClienteDTO.setNombreApellidoCliente(cliente.getNombreApellidoCliente());
                listaClienteDTO.setFechaHoraBajaCliente(cliente.getFechaHoraBajaCliente());
                listaClienteDTOs.add(listaClienteDTO);
         }
         
         
        return listaClienteDTOs;
    }

     //Metodo para eliminar cliente
    public void eliminarCliente(String cuitCliente) throws ExceptionABMCliente {
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        List <DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
            dto.setAtributo("cuitCliente");
            dto.setOperacion("like");
            dto.setValor(cuitCliente);
            lCriterio.add(dto);
            
        Cliente clienteEncontrado =(Cliente) FachadaPersistencia.getInstance().buscar("Cliente", lCriterio).get(0);
        
        //Compruebo que cliente no este ocupado con tramites
        lCriterio.clear();
        dto = new DTOCriterio();
        dto.setAtributo("cliente");
        dto.setOperacion("=");
        dto.setValor(clienteEncontrado);
        lCriterio.add(dto);
        
//        dto2 para buscar si hay tramites activos
        DTOCriterio dto2 = new DTOCriterio();
        dto2 = new DTOCriterio();
        dto2.setAtributo("fechaHoraBajaTramite"); //No esta este atributo en entidad Tramite
        dto2.setOperacion("=");
        dto2.setValor(null);
        lCriterio.add(dto2);
        
        DTOCriterio dto3 = new DTOCriterio();
        dto3 = new DTOCriterio();
        dto3.setAtributo("fechaHoraFinTramite");
        dto3.setOperacion("=");
        dto3.setValor(null);
        lCriterio.add(dto3);
        
        List <Object> tramitesCliente = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        Timestamp fechaBaja = clienteEncontrado.getFechaHoraBajaCliente();  //Traigo fechaBaja para ver si tiene o no
       
        if (!tramitesCliente.isEmpty()){
            throw new ExceptionABMCliente("El cliente tiene trámites activos!!");
        }
        if (fechaBaja != null){
            throw new ExceptionABMCliente("El cliente ya está eliminado");
        
        }else{ //if(fechaBaja == null)
        clienteEncontrado.setFechaHoraBajaCliente(new Timestamp(System.currentTimeMillis()));
        }
        FachadaPersistencia.getInstance().guardar(clienteEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
        
    }
    
//    /**
//     *
//     * @param cuitCliente
//     * @param nombreApellidoCliente
//     * @return
//     */
public DetalleClienteDTO obtenerDetalleCliente(String cuitCliente) {
        //muestra detalles
        List<DTOCriterio> lCriterio=new ArrayList<>();
        //if(!cuitCliente.isEmpty()){
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("cuitCliente");
            unCriterio.setOperacion("=");
            unCriterio.setValor(cuitCliente);
            lCriterio.add(unCriterio);
        //}

        //Nos de el primer cliente según lo marcado arriba 
        Cliente clienteEncontrado =(Cliente) FachadaPersistencia.getInstance().buscar("Cliente", lCriterio).get(0);
        
        //Encontro cliente 
        DetalleClienteDTO detalleClienteDTO = new DetalleClienteDTO();
        detalleClienteDTO.setCuitCliente(cuitCliente);
        detalleClienteDTO.setNombreApellidoCliente(clienteEncontrado.getNombreApellidoCliente());
        detalleClienteDTO.setDireccion(clienteEncontrado.getDireccionCliente());
        detalleClienteDTO.setMailCliente(clienteEncontrado.getMailCliente());
        detalleClienteDTO.setTelefonoCliente(clienteEncontrado.getTelefonoCliente());
      
        return detalleClienteDTO;
    }
    //Metodo para modificar todo EXCEPTO CUIT
    public void modificarCliente(DetalleClienteDTOIn detalleClienteDTOIn)throws ExceptionABMCliente {
        //literalmente modifica la instancia cliente
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        List <DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("cuitCliente");
        dto.setOperacion("=");
        dto.setValor(detalleClienteDTOIn.getCuitCliente());
        lCriterio.add(dto);
        
        
        Cliente clienteEncontrado =(Cliente) FachadaPersistencia.getInstance().buscar("Cliente", lCriterio).get(0);
        
        if(clienteEncontrado.getFechaHoraBajaCliente()!= null){
            throw new ExceptionABMCliente("No se puede modificar un cliente que está eliminado");
        }  //if(fechaBaja == null)
        
        if (!detalleClienteDTOIn.getNombreApellidoCliente().matches("^[\\p{L}\\s]+$")) {
            throw new ExceptionABMCliente("El campo 'Nombre y Apellido' solo debe contener letras y espacios.");
        }
        //Solo tendria que modificar todos los detalles MENOS CUIT
        clienteEncontrado.setNombreApellidoCliente(detalleClienteDTOIn.getNombreApellidoCliente());
        clienteEncontrado.setDireccionCliente(detalleClienteDTOIn.getDireccion());
        clienteEncontrado.setMailCliente(detalleClienteDTOIn.getMailCliente());
        clienteEncontrado.setTelefonoCliente(detalleClienteDTOIn.getTelefonoCliente());
        
        FachadaPersistencia.getInstance().guardar(clienteEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
    

    //Metodo para agregar Cliente Nuevo
    public void agregarCliente (DetalleClienteDTO detalleClienteDTO) throws ExceptionABMCliente{
    FachadaPersistencia.getInstance().iniciarTransaccion();
        
        
        
         //Verificación: existencia de CUIT en un Cliente ACTIVO
         
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("cuitCliente");
        dto.setOperacion("=");
        dto.setValor(detalleClienteDTO.getCuitCliente());
        criterioList.add(dto);
        
               
        DTOCriterio dto2  = new DTOCriterio();
        dto2.setAtributo("fechaHoraBajaCliente");
        dto2.setOperacion("=");
        dto2.setValor(null); //Cliente activo
        criterioList.add(dto2);
        
        List lCliente = FachadaPersistencia.getInstance().buscar("Cliente", criterioList);
        if (!detalleClienteDTO.getNombreApellidoCliente().matches("^[\\p{L}\\s]+$")) {
            throw new ExceptionABMCliente("El campo 'Nombre y Apellido' solo debe contener letras y espacios.");
        }
        if (!lCliente.isEmpty()) {
            throw new ExceptionABMCliente ("El CUIT es de un cliente existente");
        } 
        
//         if (detalleClienteDTO.getCuitCliente() == null || detalleClienteDTO.getCuitCliente().isEmpty()) {
//            throw new ExceptionABMCliente("El CUIT no puede estar vacío.");
//        }
        
         if(detalleClienteDTO.getCuitCliente() == null || detalleClienteDTO.getCuitCliente().isEmpty()){
            throw new ExceptionABMCliente("El CUIT no puede ser cero o negativo");
        }
        if (!detalleClienteDTO.getCuitCliente().matches("\\d{11}")) {
        throw new ExceptionABMCliente("El CUIT debe contener exactamente 11 dígitos numéricos.");
        }else{
        
        Cliente cliente = new Cliente();
  
            cliente.setCuitCliente(detalleClienteDTO.getCuitCliente()); // Asignar el CUIT al cliente      
            cliente.setNombreApellidoCliente(detalleClienteDTO.getNombreApellidoCliente());
            cliente.setDireccionCliente(detalleClienteDTO.getDireccion());
            cliente.setMailCliente(detalleClienteDTO.getMailCliente());
            cliente.setTelefonoCliente(detalleClienteDTO.getTelefonoCliente());
            cliente.setFechaHoraAltaCliente(new Timestamp(System.currentTimeMillis()));
            
            FachadaPersistencia.getInstance().guardar(cliente);
            FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    
}
}

   

