/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCategoriaTramite;

import ABMCategoriaTramite.dtos.DTOCategoriaTramite;
import ABMCategoriaTramite.dtos.DTOModificarCategoriaTramite;
import ABMCategoriaTramite.dtos.DTOModificarCategoriaTramiteIn;
import ABMCategoriaTramite.dtos.DTONuevaCategoriaTramite;
import ABMCategoriaTramite.exceptions.CategoriaTramiteException;
import entidades.CategoriaTramite;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author vickyvelasco
 */
public class ExpertoABMCategoriaTramite {
    public List<DTOCategoriaTramite> buscarCategorias(int codCategoriaTramite, String nombreCategoriaTramite) {
        List<DTOCriterio> lCriterio = new ArrayList<>();

        if (codCategoriaTramite > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("codCategoriaTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codCategoriaTramite);
            lCriterio.add(unCriterio);
        }

        if (nombreCategoriaTramite.trim().length() > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreCategoriaTramite");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreCategoriaTramite);
            lCriterio.add(unCriterio);
        }

        List objetoList = FachadaPersistencia.getInstance().buscar("CategoriaTramite", lCriterio);
        List<DTOCategoriaTramite> categoriasResultado = new ArrayList<>();

        for (Object x : objetoList) {
            CategoriaTramite categoria = (CategoriaTramite) x;
            DTOCategoriaTramite categoriaDTO = new DTOCategoriaTramite();
            categoriaDTO.setCodCategoriaTramite(categoria.getCodCategoriaTramite());
            categoriaDTO.setNombreCategoriaTramite(categoria.getNombreCategoriaTramite());
            categoriaDTO.setFechaHoraBajaCategoriaTramite(categoria.getFechaHoraBajaCategoriaTramite());
            categoriasResultado.add(categoriaDTO);
        }

        return categoriasResultado;
    }

    // Método para agregar una nueva categoría
    public void agregarCategoria(DTONuevaCategoriaTramite dtoNuevaCategoriaTramite) throws CategoriaTramiteException {
    
        // Validar que el código sea un número positivo
        if (dtoNuevaCategoriaTramite.getCodCategoriaTramite() <= 0) {
            throw new CategoriaTramiteException("El código debe ser un número positivo.");
        }

        // Validar que el nombre no esté vacío
        if (dtoNuevaCategoriaTramite.getNombreCategoriaTramite() == null || dtoNuevaCategoriaTramite.getNombreCategoriaTramite().trim().isEmpty()) {
            throw new CategoriaTramiteException("El nombre de la categoría no puede estar vacío.");
        }

        FachadaPersistencia.getInstance().iniciarTransaccion();

        // Validar que el nombre no exista en una categoría activa
        List<DTOCriterio> criterioNombre = new ArrayList<>();
        DTOCriterio nombreCriterio = new DTOCriterio();
        nombreCriterio.setAtributo("nombreCategoriaTramite");
        nombreCriterio.setOperacion("=");
        nombreCriterio.setValor(dtoNuevaCategoriaTramite.getNombreCategoriaTramite());
        criterioNombre.add(nombreCriterio);

        DTOCriterio fechaBajaCriterio = new DTOCriterio();
        fechaBajaCriterio.setAtributo("fechaHoraBajaCategoriaTramite");
        fechaBajaCriterio.setOperacion("=");
        fechaBajaCriterio.setValor(null); // Solo categorías activas
        criterioNombre.add(fechaBajaCriterio);

        List<Object> lCategoriasActivas = FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioNombre);

        if (!lCategoriasActivas.isEmpty()) {
            throw new CategoriaTramiteException("Ya existe una categoría activa con ese nombre.");
        }

        // Validar que el código no exista
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(dtoNuevaCategoriaTramite.getCodCategoriaTramite());
        criterioList.add(dto);

        List<Object> lCategoria = FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList);

        if (!lCategoria.isEmpty()) {
            throw new CategoriaTramiteException("El código de la categoría ya existe.");
        } else {
            CategoriaTramite categoria = new CategoriaTramite();
            categoria.setCodCategoriaTramite(dtoNuevaCategoriaTramite.getCodCategoriaTramite());
            categoria.setNombreCategoriaTramite(dtoNuevaCategoriaTramite.getNombreCategoriaTramite());

            FachadaPersistencia.getInstance().guardar(categoria);
            FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    }


    // Método para buscar una categoría por código para modificar
    public DTOModificarCategoriaTramite buscarCategoriaAModificar(int codCategoriaTramite) {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(codCategoriaTramite);

        criterioList.add(dto);

        CategoriaTramite categoriaEncontrada = (CategoriaTramite) FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList).get(0);

        
        DTOModificarCategoriaTramite dtoModificarCategoria = new DTOModificarCategoriaTramite();
        dtoModificarCategoria.setCodCategoriaTramite(categoriaEncontrada.getCodCategoriaTramite());
        dtoModificarCategoria.setNombreCategoriaTramite(categoriaEncontrada.getNombreCategoriaTramite());

        return dtoModificarCategoria;
    }

    // Método para modificar una categoría
    public void modificarCategoria(DTOModificarCategoriaTramiteIn dtoModificarCategoriaIn) throws CategoriaTramiteException {
        // Validar que el código de la categoría sea un número positivo
        if (dtoModificarCategoriaIn.getCodCategoriaTramite() <= 0) {
            throw new CategoriaTramiteException("El código debe ser un número positivo.");
        }

        // Validar que el nombre de la categoría no esté vacío
        if (dtoModificarCategoriaIn.getNombreCategoriaTramite() == null || dtoModificarCategoriaIn.getNombreCategoriaTramite().trim().isEmpty()) {
            throw new CategoriaTramiteException("El nombre de la categoría no puede estar vacío.");
        }

        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(dtoModificarCategoriaIn.getCodCategoriaTramite());

        criterioList.add(dto);

        List<Object> resultados = FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList);
        if (resultados.isEmpty()) {
            throw new CategoriaTramiteException("La categoría no fue encontrada.");
        }

        CategoriaTramite categoriaEncontrada = (CategoriaTramite) resultados.get(0);

        // Verificar si la categoría está asociada a algún tipo de trámite activo
        criterioList.clear();
        dto = new DTOCriterio();
        dto.setAtributo("categoriaTramite");
        dto.setOperacion("=");
        dto.setValor(categoriaEncontrada);
        criterioList.add(dto);

        DTOCriterio dtoFecha = new DTOCriterio();
        dtoFecha.setAtributo("fechaHoraBajaTipoTramite");
        dtoFecha.setOperacion("=");
        dtoFecha.setValor(null); // Solo tipos trámites no finalizados
        criterioList.add(dtoFecha);

        List<Object> tiposTramitesRelacionados = FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);

        if (!tiposTramitesRelacionados.isEmpty()) {
            throw new CategoriaTramiteException("La categoría no puede ser modificada porque está asociada a tipos de trámites activos.");
        }

        // Solo se modifica el nombre de la categoría
        categoriaEncontrada.setNombreCategoriaTramite(dtoModificarCategoriaIn.getNombreCategoriaTramite());

        FachadaPersistencia.getInstance().guardar(categoriaEncontrada);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }

    // Método para dar de baja una categoría
    public void darDeBajaCategoria(int codCategoriaTramite) throws CategoriaTramiteException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        // Crear el criterio para buscar la categoría de trámite por código
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(codCategoriaTramite);
        criterioList.add(dto);

        // Buscar la categoría de trámite
        List<Object> categorias = FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList);
        if (categorias.isEmpty()) {
            throw new CategoriaTramiteException("Categoría no encontrada.");
        }
        CategoriaTramite categoriaEncontrada = (CategoriaTramite) categorias.get(0);

        // Verificar si la categoría está asociada a algún tipo de trámite activo
        criterioList.clear();
        dto = new DTOCriterio();
        dto.setAtributo("categoriaTramite");
        dto.setOperacion("=");
        dto.setValor(categoriaEncontrada);
        criterioList.add(dto);

        DTOCriterio dtoFecha = new DTOCriterio();
        dtoFecha.setAtributo("fechaHoraBajaTipoTramite");
        dtoFecha.setOperacion("=");
        dtoFecha.setValor(null); // Solo tipos trámites no finalizados
        criterioList.add(dtoFecha);

        List<Object> tiposTramitesRelacionados = FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);

        if (!tiposTramitesRelacionados.isEmpty()) {
            throw new CategoriaTramiteException("La categoría no puede darse de baja porque está asociada a tipos de trámites activos.");
        }

        // Dar de baja la categoría
        categoriaEncontrada.setFechaHoraBajaCategoriaTramite(new Timestamp(System.currentTimeMillis()));
        FachadaPersistencia.getInstance().guardar(categoriaEncontrada);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }

    
    

}
