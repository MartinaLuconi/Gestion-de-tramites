package ABMCliente;

import ABMCliente.dtos.DetalleClienteDTO;
import ABMCliente.dtos.DetalleClienteDTOIn;
import ABMCliente.dtos.ListaClienteDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ControladorABMCliente {
    
    private ExpertoABMCliente expertoABMCliente = new ExpertoABMCliente();
      
     public List<ListaClienteDTO> obtenerListaCliente(String cuitCliente,  String nombreApellidoCliente){
        return expertoABMCliente.obtenerListaCliente(cuitCliente,nombreApellidoCliente);
     }
     
     public void modificarCliente(DetalleClienteDTOIn detalleClienteDTOIn) throws ExceptionABMCliente{
         expertoABMCliente.modificarCliente(detalleClienteDTOIn);
     }
     
     public void eliminarCliente(String cuitCliente) throws ExceptionABMCliente{
         expertoABMCliente.eliminarCliente(cuitCliente);
     }
     
     public void agregarCliente (DetalleClienteDTO detalleClienteDTO) throws ExceptionABMCliente{
         expertoABMCliente.agregarCliente(detalleClienteDTO);
     }
     
     
    public DetalleClienteDTO obtenerDetalleCliente (String cuitCliente){
        return expertoABMCliente.obtenerDetalleCliente(cuitCliente);
    }
}

    


