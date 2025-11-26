///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package ListaPrecio.beans;
//
//import ListaPrecio.ControladorLP;
//import ListaPrecio.dtos.DTODetalleListaPrecioImportar;
//import ListaPrecio.dtos.DTOListaPrecioImportar;
//import ListaPrecio.exceptions.ListaPrecioException;
//import jakarta.faces.application.FacesMessage;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.view.ViewScoped;
//import jakarta.inject.Named;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.poi.ss.usermodel.*;
//import org.omnifaces.util.Messages;
//import org.primefaces.event.FileUploadEvent;
//import utils.BeansUtils;
//
//@Named("uiImportatLP")
//@ViewScoped
///**
// *
// * @author marti
// */
//public class UIImportarLP implements Serializable {
//
//    private int codListaPrecio;
//    private Date fechaHoraDesdeListaPrecio;
//    private Date fechaHoraHastaListaPrecio;
//    private List<DTODetalleListaPrecioImportar> detallesImportar = new ArrayList<>();
//    private ControladorLP controladorLP = new ControladorLP();
//
//    public UIImportarLP() {
//    }
//
//    public int getCodListaPrecio() {
//        return codListaPrecio;
//    }
//
//    public void setCodListaPrecio(int codListaPrecio) {
//        this.codListaPrecio = codListaPrecio;
//    }
//
//    public Date getFechaHoraDesdeListaPrecio() {
//        return fechaHoraDesdeListaPrecio;
//    }
//
//    public void setFechaHoraDesdeListaPrecio(Date fechaHoraDesdeListaPrecio) {
//        this.fechaHoraDesdeListaPrecio = fechaHoraDesdeListaPrecio;
//    }
//
//    public Date getFechaHoraHastaListaPrecio() {
//        return fechaHoraHastaListaPrecio;
//    }
//
//    public void setFechaHoraHastaListaPrecio(Date fechaHoraHastaListaPrecio) {
//        this.fechaHoraHastaListaPrecio = fechaHoraHastaListaPrecio;
//    }
//
//    public String irAMenuPrincipal() {
//        BeansUtils.guardarUrlAnterior();
//        return "actualizarLP.xhtml?faces-redirect=true"; // Usa '?faces-redirect=true' para hacer una redirección
//    }
//
//    // METODO IMPORTAR EXCEL
//    public void handleFileUpload(FileUploadEvent event) {
//        try (InputStream inp = event.getFile().getInputStream()) {
//            Workbook wb = WorkbookFactory.create(inp);
//            Sheet sheet = wb.getSheetAt(0);
//            int iRow = 1; // Cambia a 0 si quieres leer desde la primera fila
//
//            // Limpiar la lista antes de importar nuevos datos
//            detallesImportar.clear();
//
//            // Leer las filas del archivo Excel
//            while (true) {
//                Row row = sheet.getRow(iRow);
//                if (row == null) {
//                    break; // Salir si no hay más filas
//                }
//                DTODetalleListaPrecioImportar dtoDLPI = new DTODetalleListaPrecioImportar();
//
//                // Leer el código del trámite
//                Cell codTTCell = row.getCell(0);
//                if (codTTCell != null && codTTCell.getCellType() == CellType.NUMERIC) {
//                    dtoDLPI.setCodTipoTramite((int) codTTCell.getNumericCellValue());
//                } else {
//                    System.out.println("Error: Código no válido en la fila " + (iRow + 1));
//                }
//
//                // Leer el importe
//                Cell precioTT = row.getCell(2);
//                if (precioTT != null && precioTT.getCellType() == CellType.NUMERIC) {
//                    dtoDLPI.setPrecioTipoTramite((int) precioTT.getNumericCellValue());
//                } else {
//                    System.out.println("Error: Precio no válido en la fila " + (iRow + 1));
//                }
//                detallesImportar.add(dtoDLPI); // Agregar el dto a la lista
//                iRow++;
//            }
//
//            // Comprobar si se han cargado trámites
//            if (detallesImportar.isEmpty()) {
//                throw new ListaPrecioException("No se han cargado detalles desde el archivo Excel.");
//            }
//
//        } catch (Exception ex) {
//            Logger.getLogger(UIImportarLP.class.getName()).log(Level.SEVERE, null, ex);
//            Messages.create(ex.getMessage()).error().add();
//        }
//    }
//
//    public void importar() {
//        try {
//            // Primero, verificar qué se ha subido al archivo
//            if (detallesImportar.isEmpty()) {
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se han cargado detalles desde el archivo Excel.", null));
//                return;
//            }
//
//            // Crear la lista de precios
//            DTOListaPrecioImportar listaPrecioImportar = new DTOListaPrecioImportar();
//            listaPrecioImportar.setCodListaPrecio(getCodListaPrecio());
//            listaPrecioImportar.setFechaHoraDesdeListaPrecio(getFechaHoraDesdeListaPrecio());
//            listaPrecioImportar.setFechaHoraHastaListaPrecio(getFechaHoraHastaListaPrecio());
//
//            // Llamar al método del controlador con ambos parámetros
//            controladorLP.importarListaPrecio(listaPrecioImportar, detallesImportar);
//
//            // Mensaje de éxito
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Lista y Detalles guardados exitosamente.", null));
//
//        }catch (ListaPrecioException e){
//            Messages.create("Error al guardar la Lista de Precios: " + e.getMessage()).error().add();
//        } catch (Exception e) {
//            Messages.create("Error inesperado: " + e.getMessage()).error().add();
//
//        }
//    }
//}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.beans;

import ListaPrecio.ControladorLP;
import ListaPrecio.dtos.DTODetalleListaPrecioImportar;
import ListaPrecio.dtos.DTOListaPrecioImportar;
import ListaPrecio.exceptions.ListaPrecioException;
import entidades.TipoTramite;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import utils.BeansUtils;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

@Named("uiImportatLP")
@ViewScoped
/**
 *
 * @author marti
 */
public class UIImportarLP implements Serializable {

    private int codListaPrecio;
    private Date fechaHoraDesdeListaPrecio;
    private Date fechaHoraHastaListaPrecio;
    private ControladorLP controladorLP = new ControladorLP();
    private List<DTODetalleListaPrecioImportar> dtoDetalleLPI = new ArrayList<>();
    private String uploadStatus;

    public UIImportarLP() {
        inicializarFechasPorDefecto();
        generarCodListaPrecio(); // Generar el código al inicializar el bean
    }

    public int getCodListaPrecio() {
        return codListaPrecio;
    }

    public void setCodListaPrecio(int codListaPrecio) {
        this.codListaPrecio = codListaPrecio;
    }

    public Date getFechaHoraDesdeListaPrecio() {
        return fechaHoraDesdeListaPrecio;
    }

    public void setFechaHoraDesdeListaPrecio(Date fechaHoraDesdeListaPrecio) {
        this.fechaHoraDesdeListaPrecio = fechaHoraDesdeListaPrecio;
    }

    public Date getFechaHoraHastaListaPrecio() {
        return fechaHoraHastaListaPrecio;
    }

    public void setFechaHoraHastaListaPrecio(Date fechaHoraHastaListaPrecio) {
        this.fechaHoraHastaListaPrecio = fechaHoraHastaListaPrecio;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    // Método para generar automáticamente el siguiente codListaPrecio
    private void generarCodListaPrecio() {
        Integer ultimoCod = controladorLP.obtenerUltimoCodListaPrecio();
        this.codListaPrecio = (ultimoCod != null) ? ultimoCod + 1 : 1;
    }

    // Método para inicializar las fechas por defecto
    private void inicializarFechasPorDefecto() {
        // Fecha y hora actuales
        fechaHoraDesdeListaPrecio = new Date();

        // Fecha dentro de un mes
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaHoraDesdeListaPrecio);
        calendar.add(Calendar.YEAR, 1);
        fechaHoraHastaListaPrecio = calendar.getTime();
    }

    // Método para importar la lista de precios
    public String importar() {
        try {
            // Primero, verificar que se ha subido el archivo
            if (dtoDetalleLPI.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se han cargado trámites desde el archivo Excel.", null));
                return null;
//            } else {
//                System.out.println("Se ha cargado el dtoDetalleLPI desde el Excel (boton importar)");
            }

            //Crear la lista
            DTOListaPrecioImportar dtoLPI = new DTOListaPrecioImportar();

            dtoLPI.setCodListaPrecio(codListaPrecio);
            System.out.println(codListaPrecio);

            dtoLPI.setFechaHoraDesdeListaPrecio(fechaHoraDesdeListaPrecio);
            System.out.println(fechaHoraDesdeListaPrecio);

            dtoLPI.setFechaHoraHastaListaPrecio(fechaHoraHastaListaPrecio);
            System.out.println(fechaHoraHastaListaPrecio);

            // Validación de campos obligatorios
            if (dtoLPI.getCodListaPrecio() <= 0) {
                throw new ListaPrecioException("El código de la lista de precios no puede ser negativo ni 0 ");
            }
            if (dtoLPI.getFechaHoraDesdeListaPrecio() == null) {
                throw new ListaPrecioException("La fecha 'Desde' es obligatoria.");
            }
            if (dtoLPI.getFechaHoraHastaListaPrecio() == null) {
                throw new ListaPrecioException("La fecha 'Hasta' es obligatoria.");
            }

            //Verificar si el codListaPrecio ya existe en la base de datos
            if (!verificarCodListaPrecioUnico()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El código de la lista de precios ya está en uso."));
                return null;
            }

            //Llamar al método en el controlador pasando los datos de la lista y los detalles
            controladorLP.importarListaPrecio(dtoLPI, dtoDetalleLPI);

            // Añadir mensaje al Flash scope para que sobreviva a la redirección
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito:", "La nueva lista de precios fue importada correctamente"));

            return "actualizarLP.xhtml?faces-redirect=true"; // Asegúrate de redirigir correctamente
        } catch (ListaPrecioException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    public boolean verificarCodListaPrecioUnico() {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioCodUnico = new DTOCriterio();
        criterioCodUnico.setAtributo("codListaPrecio");
        criterioCodUnico.setOperacion("=");
        criterioCodUnico.setValor(codListaPrecio);
        criterioList.add(criterioCodUnico);

        List<Object> resultados = FachadaPersistencia.getInstance().buscar("ListaPrecio", criterioList);
        return resultados.isEmpty(); // Retorna true si no se encontraron resultados, lo que indica que es único
    }

    //Método para manejar la subida del Excel
    public void handleFileUpload(FileUploadEvent event) {
        try {
            InputStream inp = event.getFile().getInputStream();
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);//obtiene la primer hoja
            int iRow = 2; //Empieza a leer desde la segunda fila (cabeceras en la primera y segunda)

            dtoDetalleLPI.clear();  // Limpiar lista anterior si existía    

            while (true) {
                Row row = sheet.getRow(iRow);
                if (row == null) {
                    break; //salir si las filas ya estan en blanco
                }
                DTODetalleListaPrecioImportar dtoDLPI = new DTODetalleListaPrecioImportar();

                // Leer el código del TipoTrámite
                Cell codTipoTramiteCell = row.getCell(0);
                if (codTipoTramiteCell != null && codTipoTramiteCell.getCellType() == CellType.NUMERIC) {
                    dtoDLPI.setCodTipoTramite((int) codTipoTramiteCell.getNumericCellValue());
                    System.out.println("Exito: CodTT valido en la fila " + (iRow + 1));
                } else {
                    System.out.println("Error: CodTT no válido en la fila " + (iRow + 1));
                }

                // Leer el nombre del TipoTramite
                TipoTramite tt = new TipoTramite();
                Cell nombreCell = row.getCell(1);
                if (nombreCell != null && nombreCell.getCellType() == CellType.STRING) {
                    tt.setNombreTipoTramite((String) nombreCell.getStringCellValue());
                    System.out.println("Exito: NombreTT valido en la fila " + (iRow + 1));
                } else {
                    System.out.println("Error: NombreTT no válido en la fila " + (iRow + 1));
                }

                // Leer el "Precio Actual" y posiblemente reemplazarlo por el "Nuevo Precio"
                Cell precioActualCell = row.getCell(2);
                Cell nuevoPrecioCell = row.getCell(3);

                if (nuevoPrecioCell != null && nuevoPrecioCell.getCellType() == CellType.NUMERIC) {
                    int nuevoPrecio = (int) nuevoPrecioCell.getNumericCellValue();
                    if (nuevoPrecio < 0) {
                        throw new ListaPrecioException("El precio no puede ser negativo. Fila: " + (iRow + 1));
                    }
                    dtoDLPI.setPrecioTipoTramite(nuevoPrecio);
                } else if (precioActualCell != null && precioActualCell.getCellType() == CellType.NUMERIC) {
                    int precioActual = (int) precioActualCell.getNumericCellValue();
                    if (precioActual < 0) {
                        throw new ListaPrecioException("El precio no puede ser negativo. Fila: " + (iRow + 1));
                    }
                    dtoDLPI.setPrecioTipoTramite(precioActual);
                } else {
                    dtoDLPI.setPrecioTipoTramite(-1); // Indicador de precio no ingresado
                }
//                if (nuevoPrecioCell != null && nuevoPrecioCell.getCellType() == CellType.NUMERIC) {
//                    dtoDLPI.setPrecioTipoTramite((int) nuevoPrecioCell.getNumericCellValue()); // Usar Nuevo Precio si está disponible
//                } else if (precioActualCell != null && precioActualCell.getCellType() == CellType.NUMERIC) {
//                    dtoDLPI.setPrecioTipoTramite((int) precioActualCell.getNumericCellValue()); // Mantener Precio Actual si Nuevo Precio está vacío
//                } else if (precioActualCell == null || precioActualCell.getCellType() != CellType.NUMERIC) {
//                    // Si el precio actual también está vacío, usa -1 como indicador de "precio no ingresado"
//                    dtoDLPI.setPrecioTipoTramite(-1);
//                }

                dtoDetalleLPI.add(dtoDLPI); // Agregar el detalle a la lista
                iRow++;
            }

            // Comprobar si se han cargado trámites
            if (dtoDetalleLPI.isEmpty()) {
                uploadStatus = "No se han cargado trámites desde el archivo Excel.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", uploadStatus));
            } else {
                uploadStatus = "Archivo subido: " + event.getFile().getFileName();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", uploadStatus));
            }
        } catch (Exception ex) {
            Logger.getLogger(UIImportarLP.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(ex.getMessage()).error().add();
        }
    }

    public String irAMenuPrincipal() {
        BeansUtils.guardarUrlAnterior();
        return "actualizarLP.xhtml?faces-redirect=true"; // Usa '?faces-redirect=true' para hacer una redirección
    }
}

//    COMBINADO NICO Y YO public void handleFileUpload(FileUploadEvent event) {
//
////        //Creo el dtoLPI y se lo paso al controlador
////        DTOListaPrecioImportar dtoLPI = new DTOListaPrecioImportar();
////        controladorLP.importarListaPrecio(dtoLPI);
////        listaPrecioImportarDTO.setCodListaPrecio(getCodListaPrecio());
////        listaPrecioImportarDTO.setFechaHoraDesdeListaPrecio(getFechaHoraDesdeListaPrecio());
////        listaPrecioImportarDTO.setFechaHoraHastaListaPrecio(getFechaHoraHastaListaPrecio());
////        controladorLP.importarListaPrecio(listaPrecioImportarDTO);
////        
////        List<DTODetalleListaPrecioImportar> detallesImportar = new ArrayList<>();
//        try {
//            InputStream inp = event.getFile().getInputStream();
//            Workbook wb = WorkbookFactory.create(inp);
//            Sheet sheet = wb.getSheetAt(0);//obtiene la primer hoja
//            int iRow = 1; //antes era 0 
//            while (true) {
//                Row row = sheet.getRow(iRow);
//                if (row == null) {
//                    break; // Salir si no hay más filas
//                }
//                DTODetalleListaPrecioImportar dtoDLPI = new DTODetalleListaPrecioImportar();
//
//                // Leer el código del trámite
//                Cell codTipoTramiteCell = row.getCell(0);
//                if (codTipoTramiteCell != null && codTipoTramiteCell.getCellType() == CellType.NUMERIC) {
//                    dtoDLPI.setCodTipoTramite((int) codTipoTramiteCell.getNumericCellValue());
//                } else {
//                    System.out.println("Error: Código de trámite no válido en la fila " + (iRow + 1));
//                }
//
//                // Leer el código de configuración
//                Cell nombreCell = row.getCell(1);
//                if (nombreCell != null && nombreCell.getCellType() == CellType.STRING) {
//                    dtoDLPI.setNombreTipoTramite((String) nombreCell.getNumericCellValue());
//                } else {
//                    System.out.println("Error: Código de configuración no válido en la fila " + (iRow + 1));
//                }
//
//                // Leer el importe
//                Cell precioCell = row.getCell(2);
//                if (precioCell != null && precioCell.getCellType() == CellType.NUMERIC) {
//                    dtoDLPI.setPrecioTipoTramite((int) precioCell.getNumericCellValue());
//                } else {
//                    System.out.println("Error: Importe no válido en la fila " + (iRow + 1));
//                }
//
//                dtoDetalleLPI.add(dtoDLPI); // Agregar el trámite a la lista
//                iRow++;
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(UIImportarLP.class.getName()).log(Level.SEVERE, null, ex);
//            Messages.create(ex.getMessage()).error().add();
//        }
//    }
//    SOLO YO public void handleFileUpload(FileUploadEvent event) {
//
////        //Creo el dtoLPI y se lo paso al controlador
//        DTOListaPrecioImportar listaPrecioImportarDTO = new DTOListaPrecioImportar();
////        listaPrecioImportarDTO.setCodListaPrecio(getCodListaPrecio());
////        listaPrecioImportarDTO.setFechaHoraDesdeListaPrecio(getFechaHoraDesdeListaPrecio());
////        listaPrecioImportarDTO.setFechaHoraHastaListaPrecio(getFechaHoraHastaListaPrecio());
////        controladorLP.importarListaPrecio(listaPrecioImportarDTO);
////        
//        List<DTODetalleListaPrecioImportar> detallesImportar = new ArrayList<>();
//
//        try {
//
//            InputStream inp = event.getFile().getInputStream();
//
//            Workbook wb = WorkbookFactory.create(inp);
//            Sheet sheet = wb.getSheetAt(0);//obtiene la primer hoja
//            int iRow = 6; //antes era 0 
//            Row row = sheet.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
//            while (row != null) {
//                //crea 1 detalle
//                DTODetalleListaPrecioImportar detalle = new DTODetalleListaPrecioImportar();
//
//                Cell cellCodTramite = row.getCell(0);
//                Cell cellPrecio = row.getCell(3);
//
//                if (cellCodTramite != null && cellCodTramite.getCellType() == CellType.NUMERIC) {
//                    if (cellCodTramite.getNumericCellValue() > 0) {
//                        detalle.setCodTipoTramite((int) cellCodTramite.getNumericCellValue());
//                        System.out.println(detalle.getCodTipotramite());
//                    } else {
//                        break;
//                    }
//
//                }
//
//                if (cellPrecio != null && (cellPrecio.getCellType() == CellType.NUMERIC || cellPrecio.getCellType() == CellType.FORMULA)) {
//                    detalle.setPrecioTipoTramite(cellPrecio.getNumericCellValue());
//                    System.out.println(detalle.getPrecioTipoTramite());
//                }
//
////                  if (cellNombreTT!=null && cellNombreTT.getCellType()==CellType.STRING){
////                    String value = cellNombreTT.getStringCellValue();
////                    Messages.create(iRow +"String").detail(value).add();
////                    System.out.println(iRow + "Valor de la celda es " + value);
//////                  }
////                    //double value = cell.getNumericCellValue();
////                    //cast para q acepte float
////                    double value = cellPrecio.getNumericCellValue();
////                    detalle.setPrecioTipoTramite(value);
////                    Messages.create(iRow +"Precio").detail(Double(value).toString()).add();
////                    
////                  }
//                //PARA CODIGO
////                  if (cellCodTramite!=null && cellCodTramite.getCellType()==CellType.NUMERIC ){
////                   int value = (int) Math.round(cellCodTramite.getNumericCellValue());
////                    //cast para q acepte INT
////                    
////                    detalle.setCodTipoTramite(value);
////                   // Messages.create(iRow +"Numero").detail(Double(cellCodTramite.getNumericCellValue()).toString()).add();
////                  }
//                listaPrecioImportarDTO.addDetalleListaPrecioImportar(detalle);
//
//                iRow++;
//                row = sheet.getRow(iRow);
//            }
////            listaPrecioImportarDTO.setCodListaPrecio();
////            listaPrecioImportarDTO.setFechaHoraDesdeListaPrecio(new Timestamp(System.currentTimeMillis()));
////            listaPrecioImportarDTO.setFechaHoraDesdeListaPrecio(new Timestamp(System.currentTimeMillis()));
//            controladorLP.importarListaPrecio(listaPrecioImportarDTO);
//
//        } catch (Exception ex) {
//            Logger.getLogger(UIImportarLP.class.getName()).log(Level.SEVERE, null, ex);
//            Messages.create(ex.getMessage()).error().add();
//        }
//
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded. Size (KB): " + event.getFile().getSize() / 1024f);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
