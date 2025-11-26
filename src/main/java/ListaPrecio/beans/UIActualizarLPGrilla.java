///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package ListaPrecio.beans;
//
//import java.sql.Timestamp;
//import java.util.Date;
//
///**
// *
// * @author marti
// */
//public class UIActualizarLPGrilla {
//    private int codListaPrecio;
//    private Date fechaHoraDesdeListaPrecio;
//    private Date fechaHoraHastaListaPrecio;
//
//    public UIActualizarLPGrilla() {
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
//}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.beans;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author marti
 */
public class UIActualizarLPGrilla {

    private int codListaPrecio;
    private Date fechaHoraDesdeListaPrecio;
    private Date fechaHoraHastaListaPrecio;
    private Date fechaHoraBajaListaPrecio;

    public UIActualizarLPGrilla() {
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

    public Date getFechaHoraBajaListaPrecio() {
        return fechaHoraBajaListaPrecio;
    }

    public void setFechaHoraBajaListaPrecio(Date fechaHoraBajaListaPrecio) {
        this.fechaHoraBajaListaPrecio = fechaHoraBajaListaPrecio;
    }

}
