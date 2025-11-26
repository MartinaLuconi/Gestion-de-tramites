/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.dtos;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti
 */
public class DTOListaPrecioExportar {

    private int codListaPrecio;
    private Date fechaHoraDesdeListaPrecio;
    private Date fechaHoraHastaListaPrecio;
    private Date fechaHoraBajaListaPrecio;
    private List<DTODetalleListaPrecioExportar> detalleListaPrecioExportar = new ArrayList<>();

    public List<DTODetalleListaPrecioExportar> getDetalleListaPrecioExportar() {
        return detalleListaPrecioExportar;
    }

    public void setDetalleListaPrecioExportar(List<DTODetalleListaPrecioExportar> detalleListaPrecioExportar) {
        this.detalleListaPrecioExportar = detalleListaPrecioExportar;
    }

    public void addDetalleListaPrecioExportar(DTODetalleListaPrecioExportar detalleListaPrecioExportar) {
        this.detalleListaPrecioExportar.add(detalleListaPrecioExportar);
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
