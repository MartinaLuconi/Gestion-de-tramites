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
//import entidades.DetallePrecioTipoTramite;
//import entidades.ListaPrecio;
//import entidades.TipoTramite;
//import jakarta.faces.application.FacesMessage;
//import jakarta.faces.context.FacesContext;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.List;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import utils.DTOCriterio;
//import utils.FachadaPersistencia;
//import utils.HibernateUtil;
//
///**
// *
// * @author marti
// */
//public class ExpertoLP {
//
//    public List<DTOListaPrecioExportar> obtenerListaPrecio(Date fechaActual) {
//
//        List<DTOCriterio> lCriterio = new ArrayList<>();
//
//        if (fechaActual != null) {
//            DTOCriterio unCriterio = new DTOCriterio();
//            unCriterio.setAtributo("fechaHoraHastaListaPrecio");
//            unCriterio.setOperacion(">");
//            unCriterio.setValor(fechaActual);
//            lCriterio.add(unCriterio);
//        }
//        //Obtengo la lista de OBJETOS(listas de precios) desde la fachada de persistencia    
//        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);
//
//        //lista que devuelve el metodo
//        List<DTOListaPrecioExportar> listadelistas = new ArrayList<>();
//
//        for (Object x : objetoList) {
//            ListaPrecio listaPrecio = (ListaPrecio) x;
//            DTOListaPrecioExportar dtoListaPrecio = new DTOListaPrecioExportar();
//            dtoListaPrecio.setCodListaPrecio(listaPrecio.getCodListaPrecio());
//            dtoListaPrecio.setFechaHoraDesdeListaPrecio(listaPrecio.getFechaHoraDesdeListaPrecio());
//            dtoListaPrecio.setFechaHoraHastaListaPrecio(listaPrecio.getFechaHoraHastaListaPrecio());
//            listadelistas.add(dtoListaPrecio);
//        }
//        return listadelistas;
//    }
//
////    public void importarListaPrecio(DTOListaPrecioImportar listaPrecioImportarDTO) {
////
////        List<DTOCriterio> lCriterio = new ArrayList<>();
////
////        try {
////
////            List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);
////
////            ListaPrecio listaPrecioAnterior = objetoList.stream()
////                    .map(x -> (ListaPrecio) x) // Convertir cada objeto a ListaPrecio
////                    .filter(lp -> lp.getFechaHoraDesdeListaPrecio() != null) // Asegurarse de que la fecha "desde" no sea null
////                    .max(Comparator.comparing(ListaPrecio::getFechaHoraDesdeListaPrecio)) // Obtener la lista con la fecha más reciente
////                    .orElse(null); // Devolver null si no hay resultados
////
////            if (listaPrecioAnterior != null) {
////
////                // ver la fecha de la lista anterior por consola
////                System.out.println("Fecha Hora Hasta de la Lista Anterior: " + listaPrecioAnterior.getFechaHoraHastaListaPrecio());
////                System.out.println("Fecha Hora Desde del DTO (Nueva Lista): " + listaPrecioImportarDTO.getFechaHoraDesdeListaPrecio());
////
////                // comparar las fechas
////                if (listaPrecioAnterior.getFechaHoraHastaListaPrecio().before(listaPrecioImportarDTO.getFechaHoraDesdeListaPrecio())) {
////                    System.out.println("La nueva lista de precios es válida y posterior a la anterior.");
////                } else {
////                    System.out.println("Error: La nueva lista de precios tiene una fecha anterior o igual a la lista anterior.");
////                    throw new IllegalArgumentException("La nueva lista tiene una fecha inválida con respecto a la lista anterior.");
////                }
////
////                // Modificar la lista de precios anterior para cerrar su periodo de vigencia
////                listaPrecioAnterior.setFechaHoraHastaListaPrecio(listaPrecioImportarDTO.getFechaHoraDesdeListaPrecio());
////
////                // Guardar los cambios en la lista anterior
////                FachadaPersistencia.getInstance().iniciarTransaccion();
////                FachadaPersistencia.getInstance().guardar(listaPrecioAnterior);
////                FachadaPersistencia.getInstance().finalizarTransaccion();
////            }
////
////            //CREAR NUEVA LISTA PRECIO
////            ListaPrecio nuevaListaPrecio = new ListaPrecio();
////            nuevaListaPrecio.setFechaHoraDesdeListaPrecio(listaPrecioImportarDTO.getFechaHoraDesdeListaPrecio());
////            nuevaListaPrecio.setFechaHoraHastaListaPrecio(listaPrecioImportarDTO.getFechaHoraHastaListaPrecio());
////
////            //BUSCAR TIPOS TRAMITES
////            DTOCriterio unCriterio = new DTOCriterio();
////            unCriterio.setAtributo("fechaHoraBajaTipoTramite");
////            unCriterio.setOperacion("!=");
////            unCriterio.setValor(null);
////            lCriterio.add(unCriterio);
////            List<Object> objetoListTT = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
////
////            for (Object tt : objetoListTT) {
////                TipoTramite tipoTramite = (TipoTramite) tt;
////
////                DetallePrecioTipoTramite nuevodetallePrecioTipoTramite = new DetallePrecioTipoTramite();
////                nuevodetallePrecioTipoTramite.setTipoTramite(tipoTramite);
////                nuevodetallePrecioTipoTramite.setPrecioTipoTramite(0); // Inicializar precio en 0
////
////                //obtengo detalles
////                List<DTODetalleListaPrecioImportar> listaDetalles = listaPrecioImportarDTO.getDetalleListaPrecioImportar();
////                boolean isEncontrado = false;
////
////                for (DTODetalleListaPrecioImportar detImpdto : listaDetalles) {
////                    int codTT = tipoTramite.getCodTipoTramite();
////                    int codTTdto = detImpdto.getCodTipotramite();
////
////                    //Verificar si los códigos de tipo trámite coinciden
////                    if (codTT == codTTdto) {
////                        nuevodetallePrecioTipoTramite.setPrecioTipoTramite(detImpdto.getPrecioTipoTramite());
////                        isEncontrado = true;
////                        break;
////                    }
////                }
////
////                // Si no se encuentra en los detalles del DTO, buscar en los detalles de la lista anterior
////                if (!isEncontrado && listaPrecioAnterior != null) {
////                    List<DetallePrecioTipoTramite> listaDetallesAnt = listaPrecioAnterior.getDetallePrecioTipoTramiteList();
////                    for (DetallePrecioTipoTramite detImp : listaDetallesAnt) {
////                        TipoTramite ttAnterior = detImp.getTipoTramite();
////                        //int codTTA = tipoTramite.getCodTipoTramite();
////
////                        if (ttAnterior.getCodTipoTramite() == tipoTramite.getCodTipoTramite()) {
////                            nuevodetallePrecioTipoTramite.setPrecioTipoTramite(detImp.getPrecioTipoTramite());
////                            break;
////                        }
////                    }
////                }
////                // Añadir el nuevo detalle a la lista de precios nueva
////                nuevaListaPrecio.addDetallePrecioTipoTramiteList(nuevodetallePrecioTipoTramite);
////            }
////            // Guardar la nueva lista de precios
////            FachadaPersistencia.getInstance().iniciarTransaccion();
////            FachadaPersistencia.getInstance().guardar(nuevaListaPrecio);
////            FachadaPersistencia.getInstance().finalizarTransaccion();
////
////        } catch (Exception e) {
////            // Manejo de excepciones
////            System.out.println("Error al procesar la lista de precios: " + e.getMessage());
////            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", e.getMessage()));
////        }
////    }
//    
//    
//    // Método para guardar la configuración junto con los costos
//    public void importarListaPrecio(DTOListaPrecioImportar dtoLPI, List<DTODetalleListaPrecioImportar> dtoDLPI) throws ListaPrecioException {
//        Transaction transaction = null;
//        Session session = null;
//
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//
//            // Crear y persistir la configuración
//            ListaPrecio LP = new ListaPrecio();
//            LP.setFechaHoraDesdeListaPrecio(dtoLPI.getFechaHoraDesdeListaPrecio());
//            LP.setFechaHoraHastaListaPrecio(dtoLPI.getFechaHoraDesdeListaPrecio());
//            LP.setCodListaPrecio(dtoLPI.getCodListaPrecio());
//
//            session.save(LP); // Guardar configuración
//
//            // Procesar los costos desde DTOs
//            for (DTODetalleListaPrecioImportar dtoDetalleLPI : dtoDLPI) {
//                // Crear un nuevo TipoTramite
//                TipoTramite tipoTramite = new TipoTramite();
//                tipoTramite.setCodTipoTramite(dtoDetalleLPI.getCodTipotramite());
//
//                session.save(tipoTramite); // Guardar el nuevo TipoTramite
//
//                // Crear y persistir el costo del tipo trámite
//                DetallePrecioTipoTramite DPTT = new DetallePrecioTipoTramite();
//                DPTT.setPrecioTipoTramite(dtoDetalleLPI.getPrecioTipoTramite());
//                DPTT.setTipoTramite(tipoTramite);
//
//                // Asociar el costo a la configuración
//                LP.getDetallePrecioTipoTramiteList().add(DPTT);
//                session.save(DPTT); // Guardar costo tipo trámite
//            }
//
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }throw new ListaPrecioException("Error al guardar la LP: " + e.getMessage(), e);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }
//
//    public DTOListaPrecioExportar exportarListaPrecio(int codListaPrecio) {
//
//        List<DTOCriterio> lCriterio = new ArrayList<>();
//        DTOCriterio unCriterio = new DTOCriterio();
//        unCriterio.setAtributo("codListaPrecio");
//        unCriterio.setOperacion("=");
//        unCriterio.setValor(codListaPrecio);
//        ListaPrecio lp = (ListaPrecio) FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio).get(0);
//
//        DTOListaPrecioExportar dtoListaPrecioExportar = new DTOListaPrecioExportar();
//        dtoListaPrecioExportar.setCodListaPrecio(lp.getCodListaPrecio());
//        dtoListaPrecioExportar.setFechaHoraDesdeListaPrecio(lp.getFechaHoraDesdeListaPrecio());
//        dtoListaPrecioExportar.setFechaHoraHastaListaPrecio(lp.getFechaHoraHastaListaPrecio());
//        //obtiene detalles
//        List<DetallePrecioTipoTramite> listDet = lp.getDetallePrecioTipoTramiteList();
//
//        for (DetallePrecioTipoTramite detlp : listDet) {
//
//            TipoTramite tt = detlp.getTipoTramite();
//
//            DTODetalleListaPrecioExportar dtoDetalleListaPrecioExportar = new DTODetalleListaPrecioExportar();
//            dtoDetalleListaPrecioExportar.setCodTipoTramite(tt.getCodTipoTramite());
//            dtoDetalleListaPrecioExportar.setNombreTipoTramite(tt.getNombreTipoTramite());
//            dtoDetalleListaPrecioExportar.setPrecioTipoTramite(detlp.getPrecioTipoTramite());
//            dtoListaPrecioExportar.addDetalleListaPrecioExportar(dtoDetalleListaPrecioExportar);
//        }
//        return dtoListaPrecioExportar;
//    }
//
//    public List<DTODetalleListaPrecioExportar> buscarDetallesExportar(int codListaPrecio) {
//        List<DTOCriterio> lCriterio = new ArrayList<>();
//        if (codListaPrecio != 0) {
//            DTOCriterio unCriterio = new DTOCriterio();
//            unCriterio.setAtributo("codListaPrecio");
//            unCriterio.setOperacion("=");
//            unCriterio.setValor(codListaPrecio);
//            lCriterio.add(unCriterio);
//        }
//
//        List objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);
//        System.out.println("Cantidad de objetos encontrados: " + objetoList.size());
//
//        List<DTODetalleListaPrecioExportar> detallesExportar = new ArrayList<>();
//        for (Object x : objetoList) {
//            ListaPrecio listaprecio = (ListaPrecio) x;
//
//            // Iterate through costoTipoTramiteList and populate DTOCostoTipoTramiteImportar
//            for (DetallePrecioTipoTramite precioTT : listaprecio.getDetallePrecioTipoTramiteList()) {
//                DTODetalleListaPrecioExportar dtoDLPE = new DTODetalleListaPrecioExportar();
//                if (precioTT.getTipoTramite() != null) {
//                    dtoDLPE.setCodTipoTramite(precioTT.getTipoTramite().getCodTipoTramite());
//                }
//                dtoDLPE.setPrecioTipoTramite(precioTT.getPrecioTipoTramite());
//                detallesExportar.add(dtoDLPE);
//            }
//        }
//
//        return detallesExportar;
//    }
//}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio;

import ListaPrecio.dtos.DTODetalleListaPrecioExportar;
import ListaPrecio.dtos.DTODetalleListaPrecioImportar;
import ListaPrecio.dtos.DTOListaPrecioExportar;
import ListaPrecio.dtos.DTOListaPrecioImportar;
import ListaPrecio.exceptions.ListaPrecioException;
import entidades.DetallePrecioTipoTramite;
import entidades.ListaPrecio;
import entidades.TipoTramite;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.omnifaces.util.Messages;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author marti
 */
public class ExpertoLP {

    public List<DTOListaPrecioExportar> obtenerListaPrecio(Date fechaActual) {
        //lista que devuelve el metodo
        List<DTOListaPrecioExportar> listadelistas = new ArrayList<>();

        List<DTOCriterio> lCriterio = new ArrayList<>();

        if (fechaActual != null) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("fechaHoraHastaListaPrecio");
            unCriterio.setOperacion(">");
            unCriterio.setValor(fechaActual);
            lCriterio.add(unCriterio);
        }
        //Obtengo la lista de OBJETOS(listas de precios) desde la fachada de persistencia    
        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);

        for (Object x : objetoList) {
            ListaPrecio listaPrecio = (ListaPrecio) x;
            DTOListaPrecioExportar dtoListaPrecio = new DTOListaPrecioExportar();
            dtoListaPrecio.setCodListaPrecio(listaPrecio.getCodListaPrecio());
            dtoListaPrecio.setFechaHoraDesdeListaPrecio(listaPrecio.getFechaHoraDesdeListaPrecio());
            dtoListaPrecio.setFechaHoraHastaListaPrecio(listaPrecio.getFechaHoraHastaListaPrecio());
            dtoListaPrecio.setFechaHoraBajaListaPrecio(listaPrecio.getFechaHoraBajaListaPrecio());
            listadelistas.add(dtoListaPrecio);
        }
        return listadelistas;
    }

//    public void importarListaPrecio(DTOListaPrecioImportar dtoLPI, List<DTODetalleListaPrecioImportar> dtoDetalleLPI) throws ListaPrecioException {
//
//        // Iniciar una única transacción para todo el proceso de importación
//        FachadaPersistencia.getInstance().iniciarTransaccion();
//        try {
//            // Paso 1: Buscar la lista de precios anterior más reciente
//            List<DTOCriterio> lCriterio = new ArrayList<>();
//            List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);
//
//            ListaPrecio listaPrecioAnterior = objetoList.stream()
//                    .map(x -> (ListaPrecio) x)
//                    .filter(lp -> lp.getFechaHoraDesdeListaPrecio() != null)
//                    .max(Comparator.comparing(ListaPrecio::getFechaHoraDesdeListaPrecio))
//                    .orElse(null);
//
//            // Validación de fecha 1: Comprobar que la fechaHoraDesde de la nueva lista no sea igual o anterior a la última lista
//            if (listaPrecioAnterior != null && !dtoLPI.getFechaHoraDesdeListaPrecio().after(listaPrecioAnterior.getFechaHoraDesdeListaPrecio())) {
//                throw new ListaPrecioException("La Fecha Hora Desde de la nueva lista de precios debe ser posterior a la última lista de precios existente.");
//            }
//
//            // Validación de fecha 2: Comprobar que la fechaHoraHasta de la nueva lista sea posterior a su fechaHoraDesde
//            if (dtoLPI.getFechaHoraHastaListaPrecio().before(dtoLPI.getFechaHoraDesdeListaPrecio())) {
//                throw new ListaPrecioException("La Fecha Hora Hasta de la nueva lista de precios debe ser posterior a la Fecha Hora Desde.");
//            }
//
//            if (listaPrecioAnterior != null) {
//                // Actualizar fecha de fin de vigencia de la lista anterior
//                listaPrecioAnterior.setFechaHoraHastaListaPrecio(dtoLPI.getFechaHoraDesdeListaPrecio());
//                FachadaPersistencia.getInstance().guardar(listaPrecioAnterior); // Guardar los cambios en la lista anterior
//            }
//
//            // Paso 2: Mapear los precios más recientes por tipo de trámite en la lista anterior
//            Map<Integer, Double> detPrecioAnteriores = new HashMap<>();
//            if (listaPrecioAnterior != null) {
//                for (DetallePrecioTipoTramite detalle : listaPrecioAnterior.getDetallePrecioTipoTramiteList()) {
//                    detPrecioAnteriores.put(detalle.getTipoTramite().getCodTipoTramite(), detalle.getPrecioTipoTramite());
//                }
//            }
//
//            // Crear nueva lista de precios
//            ListaPrecio nuevaListaPrecio = new ListaPrecio();
//            nuevaListaPrecio.setFechaHoraDesdeListaPrecio(dtoLPI.getFechaHoraDesdeListaPrecio());
//            nuevaListaPrecio.setFechaHoraHastaListaPrecio(dtoLPI.getFechaHoraHastaListaPrecio());
//            nuevaListaPrecio.setCodListaPrecio(dtoLPI.getCodListaPrecio());
//
//            // Guardar la nueva lista de precios (sin finalizar la transacción)
//            FachadaPersistencia.getInstance().guardar(nuevaListaPrecio);
//
//            // Paso 3: Procesar y guardar cada precio de tipoTramite
//            Set<Integer> tiposTramiteNuevos = new HashSet<>(); // Para rastrear trámites ya importados
//            for (DTODetalleListaPrecioImportar dtoDetLPI : dtoDetalleLPI) {
//                int codigoTramite = dtoDetLPI.getCodTipoTramite();
//                tiposTramiteNuevos.add(codigoTramite);
//
//                // Validar que existen los TT que queremos valuar
//                TipoTramite tipoTramite;
//                try {
//                    tipoTramite = obtenerTipoTramite(codigoTramite);
//                } catch (ListaPrecioException e) {
//                    // Lanzar la excepción si no existe el tipo de trámite y cancelar la importación
//                    FachadaPersistencia.getInstance().finalizarTransaccion();
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
//                    throw e;
//                }
//
//                // Si el precio no fue ingresado en el DTO, asignar el precio anterior si existe
//                if (dtoDetLPI.getPrecioTipoTramite() <= 0) {
//                    Double precioAnterior = detPrecioAnteriores.get(codigoTramite);
//                    if (precioAnterior != null) {
//                        dtoDetLPI.setPrecioTipoTramite(precioAnterior);
//                    } else {
//                        throw new ListaPrecioException("Falta el precio para el tipo de trámite con el código: " + codigoTramite);
//                    }
//                }
//
//                // Crear y guardar el nuevo detalle de precio
//                DetallePrecioTipoTramite nuevoPrecioTipoTramite = new DetallePrecioTipoTramite();
//                nuevoPrecioTipoTramite.setPrecioTipoTramite(dtoDetLPI.getPrecioTipoTramite());
//                nuevoPrecioTipoTramite.setTipoTramite(tipoTramite);
//                nuevoPrecioTipoTramite.setListaPrecio(nuevaListaPrecio);
//
//                nuevaListaPrecio.addDetallePrecioTipoTramiteList(nuevoPrecioTipoTramite);
//
//                // Guardar el detalle (sin finalizar la transacción)
//                FachadaPersistencia.getInstance().guardar(nuevoPrecioTipoTramite);
//            }
//
//            // Paso 4: Agregar trámites faltantes de la lista anterior con sus precios
//            for (DetallePrecioTipoTramite detalleAnterior : listaPrecioAnterior.getDetallePrecioTipoTramiteList()) {
//                int codigoTramiteAnterior = detalleAnterior.getTipoTramite().getCodTipoTramite();
//                if (!tiposTramiteNuevos.contains(codigoTramiteAnterior)) {
//                    // Tipo de trámite faltante en la nueva lista, agregar con precio anterior
//                    DetallePrecioTipoTramite detalleFaltante = new DetallePrecioTipoTramite();
//                    detalleFaltante.setPrecioTipoTramite(detalleAnterior.getPrecioTipoTramite());
//                    detalleFaltante.setTipoTramite(detalleAnterior.getTipoTramite());
//                    detalleFaltante.setListaPrecio(nuevaListaPrecio);
//
//                    nuevaListaPrecio.addDetallePrecioTipoTramiteList(detalleFaltante);
//                    FachadaPersistencia.getInstance().guardar(detalleFaltante);
//                }
//            }
//
//            // Finalizar la transacción de todo el proceso
//            FachadaPersistencia.getInstance().finalizarTransaccion();
//            System.out.println("Transaccion completada: Importacion de lista de precios realizada con exito.");
//
//        } catch (ListaPrecioException e) {
//            FachadaPersistencia.getInstance().finalizarTransaccion();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
//            throw e;
//        } catch (Exception e) {
//            FachadaPersistencia.getInstance().finalizarTransaccion();
//            e.printStackTrace();
//            throw new ListaPrecioException("Error durante la importación de la lista de precios: " + e.getMessage());
//        }
//    }
    public void importarListaPrecio(DTOListaPrecioImportar dtoLPI, List<DTODetalleListaPrecioImportar> dtoDetalleLPI) throws ListaPrecioException {

        // Iniciar una única transacción para todo el proceso de importación
        FachadaPersistencia.getInstance().iniciarTransaccion();
        try {
            // Paso 1: Buscar la lista de precios anterior más reciente y activa
            List<DTOCriterio> lCriterio = new ArrayList<>();

            // Añadir criterio para listas activas (fechaHoraBajaListaPrecio == null)
            DTOCriterio criterioActivo = new DTOCriterio();
            criterioActivo.setAtributo("fechaHoraBajaListaPrecio");
            criterioActivo.setOperacion("=");
            criterioActivo.setValor(null); // Solo listas activas
            lCriterio.add(criterioActivo);

            List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);

            ListaPrecio listaPrecioAnterior = objetoList.stream()
                    .map(x -> (ListaPrecio) x)
                    .filter(lp -> lp.getFechaHoraDesdeListaPrecio() != null)
                    .max(Comparator.comparing(ListaPrecio::getFechaHoraDesdeListaPrecio))
                    .orElse(null);

            // Obtener la fecha y hora actual
            Timestamp ahora = new Timestamp(System.currentTimeMillis());

            // Definir el límite de 10 minutos antes de la fecha y hora actual
            Timestamp limitePasado = new Timestamp(ahora.getTime() - (10 * 60 * 1000));

            // Validar y asignar automáticamente la fecha actual si está dentro del rango de 10 minutos en el pasado
            if (dtoLPI.getFechaHoraDesdeListaPrecio().before(limitePasado)) {
                throw new ListaPrecioException("La Fecha Hora Desde de la nueva lista de precios no puede estar en el pasado.");
            } else if (dtoLPI.getFechaHoraDesdeListaPrecio().after(limitePasado) && dtoLPI.getFechaHoraDesdeListaPrecio().before(ahora)) {
                // Si está dentro del rango de 10 minutos en el pasado, ajustar a la hora actual
                dtoLPI.setFechaHoraDesdeListaPrecio(ahora);
            }

            // Validación de fechas
            if (listaPrecioAnterior != null && !dtoLPI.getFechaHoraDesdeListaPrecio().after(listaPrecioAnterior.getFechaHoraDesdeListaPrecio())) {
                throw new ListaPrecioException("La Fecha Hora Desde de la nueva lista de precios debe ser posterior a la Fecha Hora Desde de la última lista de precios activa.");
            }

            if (dtoLPI.getFechaHoraHastaListaPrecio().before(dtoLPI.getFechaHoraDesdeListaPrecio())) {
                throw new ListaPrecioException("La Fecha Hora Hasta de la nueva lista de precios debe ser posterior a la Fecha Hora Desde de la misma.");
            }

            // Paso 2: Verificar existencia de todos los tipos de trámite (que no estén dados de baja) y asignar precios
            Map<Integer, Double> detPrecioAnteriores = new HashMap<>();
            if (listaPrecioAnterior != null) {
                for (DetallePrecioTipoTramite detalle : listaPrecioAnterior.getDetallePrecioTipoTramiteList()) {
                    detPrecioAnteriores.put(detalle.getTipoTramite().getCodTipoTramite(), detalle.getPrecioTipoTramite());
                }
            }

            Set<Integer> tiposTramiteNuevos = new HashSet<>(); // Para rastrear trámites ya importados
            List<DetallePrecioTipoTramite> detallesParaGuardar = new ArrayList<>(); // Lista temporal para almacenar los detalles

            for (DTODetalleListaPrecioImportar dtoDetLPI : dtoDetalleLPI) {
                int codigoTramite = dtoDetLPI.getCodTipoTramite();
                tiposTramiteNuevos.add(codigoTramite);

                // Validar la existencia y el estado del tipo de trámite
                TipoTramite tipoTramite = obtenerTipoTramite(codigoTramite);
                if (tipoTramite.getFechaHoraBajaTipoTramite() != null) {
                    // Si el tipo de trámite está dado de baja, lanzamos una excepción
                    throw new ListaPrecioException("El tipo de trámite con el código " + codigoTramite + " está dado de baja y no puede ser incluido en una nueva lista de precios.");
                }

                // Si el precio no fue ingresado en el DTO, asignar el precio anterior si existe
                if (dtoDetLPI.getPrecioTipoTramite() == -1) { // -1 indica precio no ingresado
                    Double precioAnterior = detPrecioAnteriores.get(codigoTramite);
                    if (precioAnterior != null) {
                        dtoDetLPI.setPrecioTipoTramite(precioAnterior); // Usar precio anterior si existe
                    } else {
                        dtoDetLPI.setPrecioTipoTramite(0); // Asignar 0 si no hay precio anterior
                    }
                }

                //Verificar que el precio no sea negativo, cualquier otro numero que no sea -1 salta esta excepcion, y si es -1 se captura arriba
                if (dtoDetLPI.getPrecioTipoTramite() < 0) {
                    throw new ListaPrecioException("El precio no puede ser negativo para el tipo de trámite con código: " + codigoTramite);
                }

                // Si el precio no fue ingresado en el DTO, asignar el precio anterior si existe
//                if (dtoDetLPI.getPrecioTipoTramite() < 0) {
//                    Double precioAnterior = detPrecioAnteriores.get(codigoTramite);
//                    if (precioAnterior != null) {
//                        dtoDetLPI.setPrecioTipoTramite(precioAnterior);
//                    } else {
//                        throw new ListaPrecioException("Falta el precio para el tipo de trámite con el código: " + codigoTramite);
//                    }
//                }
                // Crear el nuevo detalle de precio (sin persistir aún)
                DetallePrecioTipoTramite nuevoPrecioTipoTramite = new DetallePrecioTipoTramite();
                nuevoPrecioTipoTramite.setPrecioTipoTramite(dtoDetLPI.getPrecioTipoTramite());
                nuevoPrecioTipoTramite.setTipoTramite(tipoTramite);

                detallesParaGuardar.add(nuevoPrecioTipoTramite); // Almacenar en la lista temporal
            }

            // Paso 3: Crear y guardar la nueva lista de precios una vez que todas las validaciones pasaron
            ListaPrecio nuevaListaPrecio = new ListaPrecio();
            nuevaListaPrecio.setFechaHoraDesdeListaPrecio(dtoLPI.getFechaHoraDesdeListaPrecio());
            nuevaListaPrecio.setFechaHoraHastaListaPrecio(dtoLPI.getFechaHoraHastaListaPrecio());
            nuevaListaPrecio.setCodListaPrecio(dtoLPI.getCodListaPrecio());

            // Actualizar vigencia de la lista anterior
            if (listaPrecioAnterior != null) {
                listaPrecioAnterior.setFechaHoraHastaListaPrecio(dtoLPI.getFechaHoraDesdeListaPrecio());
                FachadaPersistencia.getInstance().guardar(listaPrecioAnterior);
            }

            FachadaPersistencia.getInstance().guardar(nuevaListaPrecio);

            // Asignar la nueva lista a cada detalle y guardar los detalles
            for (DetallePrecioTipoTramite detalle : detallesParaGuardar) {
                detalle.setListaPrecio(nuevaListaPrecio);
                FachadaPersistencia.getInstance().guardar(detalle);
                nuevaListaPrecio.addDetallePrecioTipoTramiteList(detalle);
            }

            // Paso 4: Agregar trámites faltantes de la lista anterior con sus precios
            if (listaPrecioAnterior != null) {
                for (DetallePrecioTipoTramite detalleAnterior : listaPrecioAnterior.getDetallePrecioTipoTramiteList()) {
                    int codigoTramiteAnterior = detalleAnterior.getTipoTramite().getCodTipoTramite();
                    if (!tiposTramiteNuevos.contains(codigoTramiteAnterior)) {
                        DetallePrecioTipoTramite detalleFaltante = new DetallePrecioTipoTramite();
                        detalleFaltante.setPrecioTipoTramite(detalleAnterior.getPrecioTipoTramite());
                        detalleFaltante.setTipoTramite(detalleAnterior.getTipoTramite());
                        detalleFaltante.setListaPrecio(nuevaListaPrecio);

                        nuevaListaPrecio.addDetallePrecioTipoTramiteList(detalleFaltante);
                        FachadaPersistencia.getInstance().guardar(detalleFaltante);
                    }
                }
            }
//        } catch (ListaPrecioException e) {
//            // Rollback si ocurre una ListaPrecioException
//            FachadaPersistencia.getInstance().finalizarTransaccion();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
//            throw e;
        } catch (Exception e) {
            // Rollback para cualquier otra excepción inesperada
            FachadaPersistencia.getInstance().finalizarTransaccion();
            e.printStackTrace();
            throw new ListaPrecioException(e.getMessage());
        }

        FachadaPersistencia.getInstance().finalizarTransaccion();
        System.out.println("Transaccion completada: Importacion de lista de precios realizada con exito.");
    }

    // Método auxiliar para obtener un TipoTramite específico desde la base de datos
    private TipoTramite obtenerTipoTramite(int codigoTramite) throws ListaPrecioException {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterio = new DTOCriterio();
        criterio.setAtributo("codTipoTramite");
        criterio.setOperacion("=");
        criterio.setValor(codigoTramite);
        criterioList.add(criterio);

        List<?> resultados = FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);
        if (resultados.isEmpty()) {
            throw new ListaPrecioException("Tipo de tramite no encontrado para el codigo: " + codigoTramite);
        }
        return (TipoTramite) resultados.get(0);
    }

    public DTOListaPrecioExportar exportarListaPrecio(int codListaPrecio) {
        List<DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
        unCriterio.setAtributo("codListaPrecio");
        unCriterio.setOperacion("=");
        unCriterio.setValor(codListaPrecio);
        lCriterio.add(unCriterio);

        ListaPrecio lp = (ListaPrecio) FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio).get(0);

        DTOListaPrecioExportar dtoListaPrecioExportar = new DTOListaPrecioExportar();
        dtoListaPrecioExportar.setCodListaPrecio(lp.getCodListaPrecio());
        dtoListaPrecioExportar.setFechaHoraDesdeListaPrecio(lp.getFechaHoraDesdeListaPrecio());
        dtoListaPrecioExportar.setFechaHoraHastaListaPrecio(lp.getFechaHoraHastaListaPrecio());

        Set<Integer> existingTypeCodes = new HashSet<>();
        for (DetallePrecioTipoTramite det : lp.getDetallePrecioTipoTramiteList()) {
            if (det.getTipoTramite().getFechaHoraBajaTipoTramite() == null) { // Solo incluir si no está dado de baja
                existingTypeCodes.add(det.getTipoTramite().getCodTipoTramite());
                DTODetalleListaPrecioExportar dtoDetalle = new DTODetalleListaPrecioExportar();
                dtoDetalle.setCodTipoTramite(det.getTipoTramite().getCodTipoTramite());
                dtoDetalle.setNombreTipoTramite(det.getTipoTramite().getNombreTipoTramite());
                dtoDetalle.setPrecioTipoTramite(det.getPrecioTipoTramite());
                dtoListaPrecioExportar.addDetalleListaPrecioExportar(dtoDetalle);
            }
        }

        // Búsqueda de todos los tipos de trámite activos (no dados de baja)
        List<DTOCriterio> criteriosTipoTramite = new ArrayList<>();
        DTOCriterio criterioBaja = new DTOCriterio();
        criterioBaja.setAtributo("fechaHoraBajaTipoTramite");
        criterioBaja.setOperacion("=");
        criterioBaja.setValor(null);
        criteriosTipoTramite.add(criterioBaja);

        List<Object> allTiposTramite = FachadaPersistencia.getInstance().buscar("TipoTramite", criteriosTipoTramite);
        for (Object obj : allTiposTramite) {
            TipoTramite tt = (TipoTramite) obj;
            if (!existingTypeCodes.contains(tt.getCodTipoTramite())) {
                DTODetalleListaPrecioExportar dtoDetalleNuevo = new DTODetalleListaPrecioExportar();
                dtoDetalleNuevo.setCodTipoTramite(tt.getCodTipoTramite());
                dtoDetalleNuevo.setNombreTipoTramite(tt.getNombreTipoTramite());
                dtoDetalleNuevo.setPrecioTipoTramite(0); // Precio 0 para tipos no incluidos en la lista actual
                dtoListaPrecioExportar.addDetalleListaPrecioExportar(dtoDetalleNuevo);
            }
        }

        return dtoListaPrecioExportar;
    }

//    public DTOListaPrecioExportar exportarListaPrecio(int codListaPrecio) {
//        List<DTOCriterio> lCriterio = new ArrayList<>();
//        DTOCriterio unCriterio = new DTOCriterio();
//        unCriterio.setAtributo("codListaPrecio");
//        unCriterio.setOperacion("=");
//        unCriterio.setValor(codListaPrecio);
//        lCriterio.add(unCriterio);
//
//        ListaPrecio lp = (ListaPrecio) FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio).get(0);
//
//        DTOListaPrecioExportar dtoListaPrecioExportar = new DTOListaPrecioExportar();
//        dtoListaPrecioExportar.setCodListaPrecio(lp.getCodListaPrecio());
//        dtoListaPrecioExportar.setFechaHoraDesdeListaPrecio(lp.getFechaHoraDesdeListaPrecio());
//        dtoListaPrecioExportar.setFechaHoraHastaListaPrecio(lp.getFechaHoraHastaListaPrecio());
//
//        Set<Integer> existingTypeCodes = lp.getDetallePrecioTipoTramiteList().stream()
//                .map(det -> det.getTipoTramite().getCodTipoTramite())
//                .collect(Collectors.toSet());
//
//        for (DetallePrecioTipoTramite detlp : lp.getDetallePrecioTipoTramiteList()) {
//            TipoTramite tt = detlp.getTipoTramite();
//            DTODetalleListaPrecioExportar dtoDetalle = new DTODetalleListaPrecioExportar();
//            dtoDetalle.setCodTipoTramite(tt.getCodTipoTramite());
//            dtoDetalle.setNombreTipoTramite(tt.getNombreTipoTramite());
//            dtoDetalle.setPrecioTipoTramite(detlp.getPrecioTipoTramite());
//            dtoListaPrecioExportar.addDetalleListaPrecioExportar(dtoDetalle);
//        }
//
//        // Búsqueda de tipos de trámite activos (no dados de baja)
//        List<DTOCriterio> otroCriterio = new ArrayList<>();
//        DTOCriterio dosCriterio = new DTOCriterio();
//        dosCriterio.setAtributo("fechaHoraBajaTipoTramite");
//        dosCriterio.setOperacion("=");
//        dosCriterio.setValor(null);
//        otroCriterio.add(dosCriterio);
//
//        List<Object> allTiposTramite = FachadaPersistencia.getInstance().buscar("TipoTramite", otroCriterio);
//
//        for (Object obj : allTiposTramite) {
//            TipoTramite tt = (TipoTramite) obj;
//            if (!existingTypeCodes.contains(tt.getCodTipoTramite())) {
//                DTODetalleListaPrecioExportar dtoDetalleNuevo = new DTODetalleListaPrecioExportar();
//                dtoDetalleNuevo.setCodTipoTramite(tt.getCodTipoTramite());
//                dtoDetalleNuevo.setNombreTipoTramite(tt.getNombreTipoTramite());
//                dtoDetalleNuevo.setPrecioTipoTramite(0); // Asignar precio 0 a nuevos tipos
//                dtoListaPrecioExportar.addDetalleListaPrecioExportar(dtoDetalleNuevo);
//            }
//        }
//        return dtoListaPrecioExportar;
//    }
    public void darDeBajaListaPrecio(int codListaPrecio) throws ListaPrecioException {
        // Buscar la lista de precios por su código
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterio = new DTOCriterio();
        criterio.setAtributo("codListaPrecio");
        criterio.setOperacion("=");
        criterio.setValor(codListaPrecio);
        criterioList.add(criterio);

        List<Object> resultados = FachadaPersistencia.getInstance().buscar("ListaPrecio", criterioList);

        if (resultados.isEmpty()) {
            throw new ListaPrecioException("No se encontró ninguna lista de precios con el código proporcionado: " + codListaPrecio);
        }

        ListaPrecio listaPrecio = (ListaPrecio) resultados.get(0);

        // Verificar que la fechaHoraDesdeListaPrecio esté en el futuro
        Timestamp ahora = new Timestamp(System.currentTimeMillis());
        if (listaPrecio.getFechaHoraDesdeListaPrecio().before(ahora)) {
            throw new ListaPrecioException("Solo se pueden dar de baja listas que aún no han iniciado.");
        }

        // Dar de baja la lista de precios
        listaPrecio.setFechaHoraBajaListaPrecio(ahora);

        // Buscar la última lista anterior a esta
        DTOCriterio criterioActivo = new DTOCriterio();
        criterioActivo.setAtributo("fechaHoraDesdeListaPrecio");
        criterioActivo.setOperacion("<");
        criterioActivo.setValor(listaPrecio.getFechaHoraDesdeListaPrecio());
        
        List<DTOCriterio> criterioListActiva = new ArrayList<>();
        criterioListActiva.add(criterioActivo);
        
        //Verificar que la última lista esté activa
        DTOCriterio criterioBaja = new DTOCriterio();
        criterioBaja.setAtributo("fechaHoraBajaListaPrecio");
        criterioBaja.setOperacion("=");
        criterioBaja.setValor(null);
        criterioListActiva.add(criterioBaja);
        
        //Buscar la lista que cumpla ambos criterios
        List<Object> listasActivas = FachadaPersistencia.getInstance().buscar("ListaPrecio", criterioListActiva);
        ListaPrecio ultimaListaActiva = listasActivas.stream()
                .map(obj -> (ListaPrecio) obj)
                .max(Comparator.comparing(ListaPrecio::getFechaHoraDesdeListaPrecio))
                .orElse(null);

        if (ultimaListaActiva != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(ultimaListaActiva.getFechaHoraDesdeListaPrecio().getTime());
            cal.add(Calendar.YEAR, 1);  // Sumar un año a la fecha desde
            ultimaListaActiva.setFechaHoraHastaListaPrecio(new Timestamp(cal.getTimeInMillis())); // Y asignarlo como fecha hasta
        }

        // Guardar los cambios
        FachadaPersistencia.getInstance().iniciarTransaccion();
        try {
            FachadaPersistencia.getInstance().guardar(listaPrecio);
            if (ultimaListaActiva != null) {
                FachadaPersistencia.getInstance().guardar(ultimaListaActiva);
            }
            FachadaPersistencia.getInstance().finalizarTransaccion();
        } catch (Exception e) {
            FachadaPersistencia.getInstance().finalizarTransaccion();
            e.printStackTrace();
            throw new ListaPrecioException(e.getMessage());
        }
    }

//    public void darDeBajaListaPrecio(int codListaPrecio) throws ListaPrecioException {
//        // Buscar la lista de precios por su código
//        List<DTOCriterio> criterioList = new ArrayList<>();
//        DTOCriterio criterio = new DTOCriterio();
//        criterio.setAtributo("codListaPrecio");
//        criterio.setOperacion("=");
//        criterio.setValor(codListaPrecio);
//        criterioList.add(criterio);
//
//        List<Object> resultados = FachadaPersistencia.getInstance().buscar("ListaPrecio", criterioList);
//
//        if (resultados.isEmpty()) {
//            throw new ListaPrecioException("No se encontró ninguna lista de precios con el código proporcionado: " + codListaPrecio);
//        }
//
//        ListaPrecio listaPrecio = (ListaPrecio) resultados.get(0);
//
//        // Verificar que la fechaHoraDesdeListaPrecio esté en el futuro
//        Timestamp ahora = new Timestamp(System.currentTimeMillis());
//        if (listaPrecio.getFechaHoraDesdeListaPrecio().before(ahora)) {
//            throw new ListaPrecioException("Solo se pueden dar de baja listas que aun no inician");
//        }
//
//        // Dar de baja la lista de precios
//        listaPrecio.setFechaHoraBajaListaPrecio(ahora);
//
//        // Guardar los cambios
//        FachadaPersistencia.getInstance().iniciarTransaccion();
//        try {
//            FachadaPersistencia.getInstance().guardar(listaPrecio);
//            FachadaPersistencia.getInstance().finalizarTransaccion();
//            System.out.println("La lista de precios con código " + codListaPrecio + " ha sido dada de baja exitosamente.");
//        } catch (Exception e) {
//            FachadaPersistencia.getInstance().finalizarTransaccion();
//            e.printStackTrace();
//            throw new ListaPrecioException("Error al dar de baja la lista de precios: " + e.getMessage());
//        }
//    }
}

// BLOQUE MARTI
//        //BUSCAR TIPOS TRAMITES
//        DTOCriterio unCriterio = new DTOCriterio();
//        unCriterio.setAtributo("fechaHoraBajaTipoTramite");
//        unCriterio.setOperacion("!=");
//        unCriterio.setValor(null);
//        lCriterio.add(unCriterio);
//        List<Object> objetoListTT = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
//
//        for (Object tt : objetoListTT) {
//            TipoTramite tipoTramite = (TipoTramite) tt;
//
//            DetallePrecioTipoTramite nuevodetallePrecioTipoTramite = new DetallePrecioTipoTramite();
//            nuevodetallePrecioTipoTramite.setTipoTramite(tipoTramite);
//            nuevodetallePrecioTipoTramite.setPrecioTipoTramite(0);
//
//            //obtengo detalles
//            List<DTODetalleListaPrecioImportar> listaDetalles = dtoLPI.getDetalleListaPrecioImportar();
//            boolean isEncontrado = false;
//            for (DTODetalleListaPrecioImportar detImpdto : listaDetalles) {
//
//                int codTT = tipoTramite.getCodTipoTramite();
//                int codTTdto = detImpdto.getCodTipotramite();
//
//                //ver que los codigos sean iguales
//                if (codTT == codTTdto) {
//
//                    nuevodetallePrecioTipoTramite.setPrecioTipoTramite(detImpdto.getPrecioTipoTramite());
//                    isEncontrado = true;
//                }
//
//                if (isEncontrado = false) {
//                    //lista de detalles anterior
//                    List<DetallePrecioTipoTramite> listaDetallesAnt = listaPrecioAnterior.getDetallePrecioTipoTramiteList();
//
//                    for (DetallePrecioTipoTramite detImp : listaDetallesAnt) {
//                        TipoTramite ttAnterior = detImp.getTipoTramite();
//                        int codTTA = tipoTramite.getCodTipoTramite();
//                        if (codTTA == codTTdto) {
//
//                            nuevodetallePrecioTipoTramite.setPrecioTipoTramite(detImp.getPrecioTipoTramite());
//
//                        }
//                    }
//                }
//            }
