///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package ListaPrecio;
//
//import ListaPrecio.dtos.DTODetalleListaPrecioExportar;
//import ListaPrecio.dtos.DTODetalleListaPrecioImportar;
//import ListaPrecio.dtos.DTOListaPrecioExportar;
//import ListaPrecio.dtos.DTOListaPrecioImportar;
//import ListaPrecio.exceptions.ListaPrecioException;
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.List;
//
///**
// *
// * @author marti
// */
//public class ControladorLP {
//
//    private ExpertoLP expertoActualizarListaPrecio = new ExpertoLP();
//
//    public List<DTOListaPrecioExportar> obtenerListaPrecio(Date fechaActual) {
//        return expertoActualizarListaPrecio.obtenerListaPrecio(fechaActual);
//    }
//
//    public void importarListaPrecio(DTOListaPrecioImportar dtoLPI, List<DTODetalleListaPrecioImportar> dtoDLPI) throws ListaPrecioException {
//        try {
//            expertoActualizarListaPrecio.importarListaPrecio(dtoLPI, dtoDLPI);
//        } catch (Exception e) {
//            throw new ListaPrecioException("Error al guardar la Lista de Precios: " + e.getMessage());
//        }
//    }
//
//    public DTOListaPrecioExportar exportarListaPrecio(int codListaPrecio) {
//        return expertoActualizarListaPrecio.exportarListaPrecio(codListaPrecio);
//    }
//
//    public List<DTODetalleListaPrecioExportar> buscarDetallesExportar(int codListaPrecio) {
//        return expertoActualizarListaPrecio.buscarDetallesExportar(codListaPrecio);
//    }
//}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio;

import ListaPrecio.dtos.DTODetalleListaPrecioImportar;
import ListaPrecio.dtos.DTOListaPrecioExportar;
import ListaPrecio.dtos.DTOListaPrecioImportar;
import ListaPrecio.exceptions.ListaPrecioException;
import entidades.ListaPrecio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author marti
 */
public class ControladorLP {

    private ExpertoLP expertoActualizarListaPrecio = new ExpertoLP();

    public List<DTOListaPrecioExportar> obtenerListaPrecio(Date fechaActual) {
        return expertoActualizarListaPrecio.obtenerListaPrecio(fechaActual);
    }

    public void importarListaPrecio(DTOListaPrecioImportar dtoLPI, List<DTODetalleListaPrecioImportar> dtoDetalleLPI) throws ListaPrecioException {
            expertoActualizarListaPrecio.importarListaPrecio(dtoLPI, dtoDetalleLPI);
    }

    public DTOListaPrecioExportar exportarListaPrecio(int codListaPrecio) {
        return expertoActualizarListaPrecio.exportarListaPrecio(codListaPrecio);
    }

    public void darDeBajaListaPrecio(int codListaPrecio) throws ListaPrecioException {
        expertoActualizarListaPrecio.darDeBajaListaPrecio(codListaPrecio);
    }

    public Integer obtenerUltimoCodListaPrecio() {
        List<DTOCriterio> criterios = new ArrayList<>();
        List<Object> listaPrecios = FachadaPersistencia.getInstance().buscar("ListaPrecio", criterios);

        return listaPrecios.stream()
                .map(lp -> (ListaPrecio) lp)
                .mapToInt(ListaPrecio::getCodListaPrecio)
                .max()
                .orElse(0); // Retorna 0 si no hay listas en la base de datos
    }

}
