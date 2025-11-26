/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMEstadoTramite;

import ABMEstadoTramite.dtos.DTOEstadoTramite;
import ABMEstadoTramite.dtos.DTOModificarEstadoTramite;
import ABMEstadoTramite.dtos.DTOModificarEstadoTramiteIn;
import ABMEstadoTramite.dtos.DTONuevoEstadoTramite;
import ABMEstadoTramite.exceptions.EstadoTramiteException;
import entidades.EstadoTramite;
import entidades.Tramite;
import entidades.TransicionEstado;
import entidades.Version;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author vickyvelasco
 */
public class ExpertoABMEstadoTramite {
    // Método para buscar estados de trámite
    public List<DTOEstadoTramite> buscarEstados(int codEstadoTramite, String nombreEstadoTramite) {
        List<DTOCriterio> lCriterio = new ArrayList<>();

        if (codEstadoTramite > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("codEstadoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codEstadoTramite);
            lCriterio.add(unCriterio);
        }

        if (nombreEstadoTramite != null && !nombreEstadoTramite.trim().isEmpty()) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreEstadoTramite");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreEstadoTramite);
            lCriterio.add(unCriterio);
        }

        List<Object> estados = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio);
        List<DTOEstadoTramite> resultado = new ArrayList<>();

        for (Object x : estados) {
            EstadoTramite estado = (EstadoTramite) x;
            DTOEstadoTramite dto = new DTOEstadoTramite();
            dto.setCodEstadoTramite(estado.getCodEstadoTramite());
            dto.setNombreEstadoTramite(estado.getNombreEstadoTramite());
            dto.setFechaHoraBajaEstadoTramite(estado.getFechaHoraBajaEstadoTramite());
            resultado.add(dto);
        }

        return resultado;
    }

    
    public void agregarEstadoTramite(DTONuevoEstadoTramite nuevoEstadoTramite) throws EstadoTramiteException {
    // Validar que el código sea positivo
        if (nuevoEstadoTramite.getCodEstadoTramite() <= 0) {
            throw new EstadoTramiteException("El código del Estado de Trámite debe ser un número positivo.");
        }

        // Validar que el nombre no esté vacío
        if (nuevoEstadoTramite.getNombreEstadoTramite() == null || nuevoEstadoTramite.getNombreEstadoTramite().trim().isEmpty()) {
            throw new EstadoTramiteException("El nombre del Estado de Trámite no puede estar vacío.");
        }

        FachadaPersistencia.getInstance().iniciarTransaccion();

        // Validar que el código no exista
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioCodigo = new DTOCriterio();
        criterioCodigo.setAtributo("codEstadoTramite");
        criterioCodigo.setOperacion("=");
        criterioCodigo.setValor(nuevoEstadoTramite.getCodEstadoTramite());
        criterioList.add(criterioCodigo);

        List<Object> estadoExistente = FachadaPersistencia.getInstance().buscar("EstadoTramite", criterioList);

        if (!estadoExistente.isEmpty()) {
            throw new EstadoTramiteException("El código del Estado de Trámite ya existe.");
        }

        // Validar que no exista otro estado vigente con el mismo nombre
        List<DTOCriterio> criterioNombre = new ArrayList<>();
        DTOCriterio criterioNombreEstado = new DTOCriterio();
        criterioNombreEstado.setAtributo("nombreEstadoTramite");
        criterioNombreEstado.setOperacion("=");
        criterioNombreEstado.setValor(nuevoEstadoTramite.getNombreEstadoTramite());
        criterioNombre.add(criterioNombreEstado);

        DTOCriterio criterioFechaBaja = new DTOCriterio();
        criterioFechaBaja.setAtributo("fechaHoraBajaEstadoTramite");
        criterioFechaBaja.setOperacion("=");
        criterioFechaBaja.setValor(null); // Estado vigente
        criterioNombre.add(criterioFechaBaja);

        List<Object> estadosExistentes = FachadaPersistencia.getInstance().buscar("EstadoTramite", criterioNombre);

        if (!estadosExistentes.isEmpty()) {
            throw new EstadoTramiteException("Ya existe un Estado de Trámite vigente con el mismo nombre.");
        }

        // Crear el nuevo EstadoTramite
        EstadoTramite estadoTramite = new EstadoTramite();
        estadoTramite.setCodEstadoTramite(nuevoEstadoTramite.getCodEstadoTramite());
        estadoTramite.setNombreEstadoTramite(nuevoEstadoTramite.getNombreEstadoTramite());

        FachadaPersistencia.getInstance().guardar(estadoTramite);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }

    
    public DTOModificarEstadoTramite buscarEstadoAModificar(int codEstadoTramite) {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codEstadoTramite");
        dto.setOperacion("=");
        dto.setValor(codEstadoTramite);

        criterioList.add(dto);

        EstadoTramite estadoEncontrado = (EstadoTramite) FachadaPersistencia.getInstance().buscar("EstadoTramite", criterioList).get(0);

        DTOModificarEstadoTramite dtoModificarEstado = new DTOModificarEstadoTramite();
        dtoModificarEstado.setCodEstadoTramite(estadoEncontrado.getCodEstadoTramite());
        dtoModificarEstado.setNombreEstadoTramite(estadoEncontrado.getNombreEstadoTramite());

        return dtoModificarEstado;
    }

    
    public void modificarEstadoTramite(DTOModificarEstadoTramiteIn estadoModificado) throws EstadoTramiteException {
        // Validar que el código sea positivo
        if (estadoModificado.getCodEstadoTramite() <= 0) {
            throw new EstadoTramiteException("El código del Estado de Trámite debe ser un número positivo.");
        }

        // Validar que el nombre no esté vacío
        if (estadoModificado.getNombreEstadoTramite() == null || estadoModificado.getNombreEstadoTramite().trim().isEmpty()) {
            throw new EstadoTramiteException("El nombre del Estado de Trámite no puede estar vacío.");
        }

        FachadaPersistencia.getInstance().iniciarTransaccion();

        // Buscar el EstadoTramite por código
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioCodigo = new DTOCriterio();
        criterioCodigo.setAtributo("codEstadoTramite");
        criterioCodigo.setOperacion("=");
        criterioCodigo.setValor(estadoModificado.getCodEstadoTramite());
        criterioList.add(criterioCodigo);

        List<Object> estados = FachadaPersistencia.getInstance().buscar("EstadoTramite", criterioList);

        if (estados.isEmpty()) {
            throw new EstadoTramiteException("El Estado de Trámite no fue encontrado.");
        }

        // Validar que no exista otro estado vigente con el mismo nombre
        List<DTOCriterio> criterioNombre = new ArrayList<>();
        DTOCriterio criterioNombreEstado = new DTOCriterio();
        criterioNombreEstado.setAtributo("nombreEstadoTramite");
        criterioNombreEstado.setOperacion("=");
        criterioNombreEstado.setValor(estadoModificado.getNombreEstadoTramite());
        criterioNombre.add(criterioNombreEstado);

        DTOCriterio criterioFechaBaja = new DTOCriterio();
        criterioFechaBaja.setAtributo("fechaHoraBajaEstadoTramite");
        criterioFechaBaja.setOperacion("=");
        criterioFechaBaja.setValor(null); // Estado vigente
        criterioNombre.add(criterioFechaBaja);

        List<Object> estadosExistentes = FachadaPersistencia.getInstance().buscar("EstadoTramite", criterioNombre);

        if (!estadosExistentes.isEmpty()) {
            throw new EstadoTramiteException("Ya existe un Estado de Trámite vigente con el mismo nombre.");
        }

        EstadoTramite estadoEncontrado = (EstadoTramite) estados.get(0);

        // Verificar si el estado está asociado a alguna versión activa
        if (verificarEstadoEnVersionesActivas(estadoEncontrado.getCodEstadoTramite())) {
            throw new EstadoTramiteException("No se pudo eliminar el Estado Tramite porque está asociado a VERSIONES ACTIVAS.");
        }
        // Verificar si el estado está asociado a trámites activos
        criterioList.clear();
        DTOCriterio dtoEstado = new DTOCriterio();
        dtoEstado.setAtributo("estadoTramite");
        dtoEstado.setOperacion("=");
        dtoEstado.setValor(estadoEncontrado);
        criterioList.add(dtoEstado);

        DTOCriterio dtoFechaFin = new DTOCriterio();
        dtoFechaFin.setAtributo("fechaHoraFinTramite");
        dtoFechaFin.setOperacion("="); // Solo trámites que aún no han finalizado
        dtoFechaFin.setValor(null);
        criterioList.add(dtoFechaFin);

        List<Object> tramitesActivos = FachadaPersistencia.getInstance().buscar("Tramite", criterioList);
        if (!tramitesActivos.isEmpty()) {
            throw new EstadoTramiteException("No se puede modificar el Estado de Trámite porque está asociado a TRAMITES ACTIVOS.");
        }
        
        // Modificar el nombre del EstadoTramite
        estadoEncontrado.setNombreEstadoTramite(estadoModificado.getNombreEstadoTramite());

        FachadaPersistencia.getInstance().guardar(estadoEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }

    
    /*private List<Version> obtenerVersionesDesdeEstadoTramite(EstadoTramite estadoTramite) {
       // Obtener las transiciones asociadas al estado actual
       List<DTOCriterio> criterioTransicion = new ArrayList<>();
       DTOCriterio dtoEstado = new DTOCriterio();
       dtoEstado.setAtributo("estadoActual");
       dtoEstado.setOperacion("=");
       dtoEstado.setValor(estadoTramite);
       criterioTransicion.add(dtoEstado);

       List<Object> transiciones = FachadaPersistencia.getInstance().buscar("TransicionEstado", criterioTransicion);

       // Obtener las versiones activas y futuras
       List<DTOCriterio> criterioVersion = new ArrayList<>();
       DTOCriterio dtoBaja = new DTOCriterio();
       dtoBaja.setAtributo("fechaHoraBajaVersion");
       dtoBaja.setOperacion("=");
       dtoBaja.setValor(null);
       criterioVersion.add(dtoBaja);

       // Criterio para verificar si la fecha de hasta versión es futura
       DTOCriterio dtoFechaHasta = new DTOCriterio();
       dtoFechaHasta.setAtributo("fechaHoraHastaVersion");
       dtoFechaHasta.setOperacion(">=");
       dtoFechaHasta.setValor(new Timestamp(System.currentTimeMillis())); // Fecha actual
       criterioVersion.add(dtoFechaHasta);

       // Obtener todas las versiones activas y futuras
       List<Object> versiones = FachadaPersistencia.getInstance().buscar("Version", criterioVersion);

       List<Version> versionesActivasOFuturas = new ArrayList<>();
       for (Object objVersion : versiones) {
           Version version = (Version) objVersion;

           // Comprobar si la versión pertenece a alguna de las transiciones
           for (Object objTransicion : transiciones) {
               TransicionEstado transicion = (TransicionEstado) objTransicion;
               // Verificar si la versión está asociada a la transición
               if (version.getTransicionEstadoList().contains(transicion)) {
                   if (!versionesActivasOFuturas.contains(version)) {
                       versionesActivasOFuturas.add(version);
                   }
                   break; // Salir del bucle al encontrar una transición asociada
               }
           }
       }

       return versionesActivasOFuturas;
    }*/

    private boolean verificarEstadoEnVersionesActivas(int codEstadoTramite) {
        List<DTOCriterio> criterioList = new ArrayList<>();

        DTOCriterio dtoFechaDesde = new DTOCriterio();
        dtoFechaDesde.setAtributo("fechaHoraDesdeVersion");
        dtoFechaDesde.setOperacion("<");
        dtoFechaDesde.setValor(new Timestamp(System.currentTimeMillis()));
        criterioList.add(dtoFechaDesde);

        DTOCriterio dtoFechaBaja = new DTOCriterio();
        dtoFechaBaja.setAtributo("fechaHoraBajaVersion");
        dtoFechaBaja.setOperacion("=");
        dtoFechaBaja.setValor(null);
        criterioList.add(dtoFechaBaja);

        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("Version", criterioList);

        for (Object o : objetoList) {
            Version version = (Version) o;
            for (TransicionEstado tEstado : version.getTransicionEstadoList()) {
                if (tEstado.getEstadoActual().getCodEstadoTramite() == codEstadoTramite) {
                    return true; // Está asociado a una versión activa
                }
            }
        }
        return false; // No está asociado
    }
    
    
    public void darDeBajaEstadoTramite(int codEstadoTramite) throws EstadoTramiteException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        // Crear el criterio para buscar el estado de trámite por código
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codEstadoTramite");
        dto.setOperacion("=");
        dto.setValor(codEstadoTramite);
        criterioList.add(dto);

        // Buscar el estado de trámite
        List<Object> estados = FachadaPersistencia.getInstance().buscar("EstadoTramite", criterioList);
        if (estados.isEmpty()) {
            throw new EstadoTramiteException("Estado de trámite no encontrado.");
        }
        EstadoTramite estadoEncontrado = (EstadoTramite) estados.get(0);

        // Verificar si el estado está asociado a alguna versión activa
        if (verificarEstadoEnVersionesActivas(codEstadoTramite)) {
            throw new EstadoTramiteException("No se pudo eliminar el Estado Tramite porque está asociado a VERSIONES ACTIVAS.");
        }

        // Verificar si el estado está asociado a trámites activos
        criterioList.clear();
        DTOCriterio dtoEstado = new DTOCriterio();
        dtoEstado.setAtributo("estadoTramite");
        dtoEstado.setOperacion("=");
        dtoEstado.setValor(estadoEncontrado);
        criterioList.add(dtoEstado);

        DTOCriterio dtoFechaFin = new DTOCriterio();
        dtoFechaFin.setAtributo("fechaHoraFinTramite");
        dtoFechaFin.setOperacion("="); // Solo trámites que aún no han finalizado
        dtoFechaFin.setValor(null);
        criterioList.add(dtoFechaFin);

        List<Object> tramitesActivos = FachadaPersistencia.getInstance().buscar("Tramite", criterioList);
        if (!tramitesActivos.isEmpty()) {
            throw new EstadoTramiteException("No se puede dar de baja el Estado de Trámite porque está asociado a TRAMITES ACTIVOS.");
        }

        // Dar de baja el estado de trámite
        estadoEncontrado.setFechaHoraBajaEstadoTramite(new Timestamp(System.currentTimeMillis()));
        FachadaPersistencia.getInstance().guardar(estadoEncontrado);

        FachadaPersistencia.getInstance().finalizarTransaccion();
    
    }
}

