///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package ListaPrecio.beans;
//
//import ListaPrecio.ControladorLP;
//import ListaPrecio.dtos.DTODetalleListaPrecioExportar;
//import ListaPrecio.dtos.DTOListaPrecioExportar;
//import jakarta.faces.view.ViewScoped;
//import jakarta.inject.Named;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.omnifaces.util.Messages;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
//import utils.BeansUtils;
//
///**
// *
// * @author marti
// */
//@Named("uiActualizarLP")
//@ViewScoped
//
//public class UIActualizarLP implements Serializable {
//
//    private Date fechaDesde;
//    private ControladorLP controladorLP = new ControladorLP();
//    private StreamedContent fileD;
//    private List<DTODetalleListaPrecioExportar> detallesExportarData = new ArrayList<>();
//    private int codListaPrecioFiltro;
//
//    public UIActualizarLP() {
//    }
//
//    public Date getFechaDesde() {
//        return fechaDesde;
//    }
//
//    public void setFechaDesde(Date fechaDesde) {
//        this.fechaDesde = fechaDesde;
//    }
//
//    public List<UIActualizarLPGrilla> obtenerListaPrecio() {
//
//        List<UIActualizarLPGrilla> listaPreciogrilla = new ArrayList<>();
//        List<DTOListaPrecioExportar> dtoListasPreciosExportar = controladorLP.obtenerListaPrecio(fechaDesde);
//        for (DTOListaPrecioExportar dtoListaPrecioExportar : dtoListasPreciosExportar) {
//            UIActualizarLPGrilla uiActualizarLPGrilla = new UIActualizarLPGrilla();
//            uiActualizarLPGrilla.setCodListaPrecio(dtoListaPrecioExportar.getCodListaPrecio());
//            uiActualizarLPGrilla.setFechaHoraDesdeListaPrecio(dtoListaPrecioExportar.getFechaHoraDesdeListaPrecio());
//            uiActualizarLPGrilla.setFechaHoraHastaListaPrecio(dtoListaPrecioExportar.getFechaHoraHastaListaPrecio());
//            listaPreciogrilla.add(uiActualizarLPGrilla);
//        }
//
//        return listaPreciogrilla;
//    }
//
//    public void filtrar() {
//
//    }
//
//    public String irAImportar() {
//        BeansUtils.guardarUrlAnterior();
//        return "importarLP.xhtml?faces-redirect=true"; // Usa '?faces-redirect=true' para hacer una redirección
//    }
//
//    public StreamedContent getFileD() {
//        populateDetallesExportarData(); // Populate the detallesListaPrecio before creating the Excel file
//
//        try {
//            Workbook workbook = new XSSFWorkbook();
//            Sheet sheet = workbook.createSheet("Hoja 1");
//
//            // Create header row
//            Row headerRow = sheet.createRow(0);
//            headerRow.createCell(0).setCellValue("Codigo");
//            headerRow.createCell(1).setCellValue("Nombre");
//            headerRow.createCell(2).setCellValue("Precio");
//
//            // Fill data rows
//            int rowIndex = 1;
//            for (DTODetalleListaPrecioExportar dtoDLPE : detallesExportarData) {
//                Row row = sheet.createRow(rowIndex++);
//                row.createCell(0).setCellValue(dtoDLPE.getCodTipoTramite());
//                row.createCell(1).setCellValue(dtoDLPE.getNombreTipoTramite());
//                row.createCell(2).setCellValue(dtoDLPE.getPrecioTipoTramite());
//            }
//
//            // Create a temporary file and write to it
//            File tempFile = File.createTempFile("listaprecio_", ".xlsx");
//            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
//                workbook.write(outputStream);
//            }
//            workbook.close();
//
//            // Prepare the StreamedContent for download
//            InputStream stream = new FileInputStream(tempFile);
//            fileD = DefaultStreamedContent.builder()
//                    .name("Lista de Precios.xlsx")
//                    .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
//                    .stream(() -> stream)
//                    .build();
//
//        } catch (IOException ex) {
//            Logger.getLogger(UIActualizarLP.class.getName()).log(Level.SEVERE, null, ex);
//            Messages.create("Error generating the file: " + ex.getMessage()).error().add();
//        }
//        return fileD;
//    }
//
//    private void populateDetallesExportarData() {
//       detallesExportarData = controladorLP.buscarDetallesExportar(codListaPrecioFiltro);
//    }
//
//}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.beans;

import ListaPrecio.ControladorLP;
import ListaPrecio.dtos.DTODetalleListaPrecioExportar;
import ListaPrecio.dtos.DTOListaPrecioExportar;
import ListaPrecio.exceptions.ListaPrecioException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omnifaces.util.Messages;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import utils.BeansUtils;

/**
 *
 * @author marti
 */
@Named("uiActualizarLP")
@ViewScoped

public class UIActualizarLP implements Serializable {

    private Date fechaActual;
    private ControladorLP controladorLP = new ControladorLP();
    private StreamedContent fileD;
    private int codListaPrecio;

    public int getCodListaPrecio() {
        return codListaPrecio;
    }

    public void setCodListaPrecio(int codListaPrecio) {
        this.codListaPrecio = codListaPrecio;
    }

    public UIActualizarLP() {
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public List<UIActualizarLPGrilla> obtenerListaPrecio() {

        // Obtener la lista de precios filtrada por la fecha seleccionada
        List<DTOListaPrecioExportar> dtoListasPreciosExportar = controladorLP.obtenerListaPrecio(fechaActual);

        // Crear una nueva lista para la grilla de UI
        List<UIActualizarLPGrilla> listaPreciogrilla = new ArrayList<>();

        for (DTOListaPrecioExportar dtoListaPrecioExportar : dtoListasPreciosExportar) {
            UIActualizarLPGrilla uiActualizarLPGrilla = new UIActualizarLPGrilla();
            uiActualizarLPGrilla.setCodListaPrecio(dtoListaPrecioExportar.getCodListaPrecio());
            uiActualizarLPGrilla.setFechaHoraDesdeListaPrecio(dtoListaPrecioExportar.getFechaHoraDesdeListaPrecio());
            uiActualizarLPGrilla.setFechaHoraHastaListaPrecio(dtoListaPrecioExportar.getFechaHoraHastaListaPrecio());
            uiActualizarLPGrilla.setFechaHoraBajaListaPrecio(dtoListaPrecioExportar.getFechaHoraBajaListaPrecio());
            listaPreciogrilla.add(uiActualizarLPGrilla);
        }

        // Ordenar la lista por código en orden descendente
        listaPreciogrilla.sort(Comparator.comparingInt(UIActualizarLPGrilla::getCodListaPrecio).reversed());

        return listaPreciogrilla;
    }

    public void filtrar() {
    }

    public String irAImportar() {
        BeansUtils.guardarUrlAnterior();
        return "importarLP.xhtml?faces-redirect=true"; // Usa '?faces-redirect=true' para hacer una redirección
    }

//    public StreamedContent getFileD() {
//        DTOListaPrecioExportar dtoListaPrecioExportar = controladorLP.exportarListaPrecio(codListaPrecio);
//        List<DTODetalleListaPrecioExportar> detallesListaPrecio = dtoListaPrecioExportar.getDetalleListaPrecioExportar();
//
//        try {
//            Workbook libro = new XSSFWorkbook();
//
//            final String nombreArchivo = "./tmp.xlsx";
//            Sheet hoja = libro.createSheet("Hoja 1");
//
//            int nFila = 0;
//            int nColumna = 0;
//            Row fila;
//            Cell celda;
//            fila = hoja.createRow(nFila);
//            celda = fila.createCell(nColumna); //crea celda en columna
//            celda.setCellValue("Cod");//setea valor
//            nColumna++;//pasa a la siguiente columna
//            celda = fila.createCell(nColumna);
//            celda.setCellValue("nombre");
//            nColumna++;
//            celda = fila.createCell(nColumna);
//            celda.setCellValue("precio");
//
//            int j = 0;
//            for (DTODetalleListaPrecioExportar detalleLPE : detallesListaPrecio) {
//                nColumna = 0;
//                nFila++;
//                fila = hoja.createRow(nFila);
//                celda = fila.createCell(nColumna);
//                celda.setCellValue(detalleLPE.getCodTipoTramite());
//                nColumna++;
//                celda = fila.createCell(nColumna);
//                celda.setCellValue(detalleLPE.getNombreTipoTramite());
//                nColumna++;
//                celda = fila.createCell(nColumna);
//                celda.setCellValue(detalleLPE.getPrecioTipoTramite());
//
//            }
//
//            FileOutputStream outputStream;
//
//            outputStream = new FileOutputStream(nombreArchivo);
//            libro.write(outputStream);
//            libro.close();
//            InputStream ie = new FileInputStream(nombreArchivo);
//            fileD = DefaultStreamedContent.builder()
//                    .name("ListaPrecio.xlsx")
//                    .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
//                    .stream(() -> ie)
//                    .build();
//        } catch (IOException ex) {
//            Logger.getLogger(UIActualizarLP.class.getName()).log(Level.SEVERE, null, ex);
//            Messages.create(ex.getMessage()).error().add();
//        }
//        return fileD;
//    }
    public StreamedContent getFileD() {
        DTOListaPrecioExportar dtoListaPrecioExportar = controladorLP.exportarListaPrecio(codListaPrecio);
        List<DTODetalleListaPrecioExportar> detallesListaPrecio = dtoListaPrecioExportar.getDetalleListaPrecioExportar();

        // Ordenar detallesListaPrecio por codTipoTramite de forma ascendente
        detallesListaPrecio.sort(Comparator.comparingInt(DTODetalleListaPrecioExportar::getCodTipoTramite));

        try {
            Workbook libro = new XSSFWorkbook();
            final String nombreArchivo = "ListaPrecio_" + codListaPrecio + ".xlsx";
            Sheet hoja = libro.createSheet("Hoja 1");

            // Creación de la fila de información general de la lista de precios
            Row infoRow = hoja.createRow(0);
            infoRow.createCell(0).setCellValue("Lista Nº: " + dtoListaPrecioExportar.getCodListaPrecio());
            infoRow.createCell(1).setCellValue("Desde: " + dtoListaPrecioExportar.getFechaHoraDesdeListaPrecio());
            infoRow.createCell(2).setCellValue("Hasta: " + dtoListaPrecioExportar.getFechaHoraHastaListaPrecio());

            // Definición de los encabezados para los detalles de la lista de precios
            String[] headers = {"Código del Tipo de Trámite", "Nombre del Tipo de Trámite", "Precio Actual", "Nuevo Precio"};
            Row headerRow = hoja.createRow(1); // Los encabezados comienzan en la segunda fila
            for (int i = 0; i < headers.length; i++) {
                Cell headerCell = headerRow.createCell(i);
                headerCell.setCellValue(headers[i]);
            }

            // Llenar datos de las filas comenzando después de los encabezados
            int filaNum = 2; // Comienza a llenar detalles desde la tercera fila
            for (DTODetalleListaPrecioExportar detalleLPE : detallesListaPrecio) {
                Row fila = hoja.createRow(filaNum++);
                fila.createCell(0).setCellValue(detalleLPE.getCodTipoTramite());
                fila.createCell(1).setCellValue(detalleLPE.getNombreTipoTramite());
                fila.createCell(2).setCellValue(detalleLPE.getPrecioTipoTramite());
                fila.createCell(3).setCellValue(""); // Columna "Nuevo Precio" vacía para entrada manual
            }

            // Ajustar el tamaño de las columnas
            for (int i = 0; i < headers.length + 1; i++) { // Ajustar también las columnas de la fila de información general
                hoja.autoSizeColumn(i);
            }

            // Escritura del libro a un archivo temporal
            FileOutputStream outputStream = new FileOutputStream(nombreArchivo);
            libro.write(outputStream);
            libro.close();
            InputStream inputStream = new FileInputStream(nombreArchivo);
            fileD = DefaultStreamedContent.builder()
                    .name(nombreArchivo) // Usa el nombre personalizado con código
                    .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    .stream(() -> inputStream)
                    .build();
        } catch (IOException ex) {
            Logger.getLogger(UIActualizarLP.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(ex.getMessage()).error().add();
        }
        return fileD;
    }
    // Método que se activa desde el botón en la interfaz

    public void darDeBajaListaPrecio() {
        try {
            controladorLP.darDeBajaListaPrecio(codListaPrecio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito:", "La lista de precios con código " + codListaPrecio + " ha sido dada de baja correctamente."));
        } catch (ListaPrecioException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", e.getMessage()));
        }
    }
}
