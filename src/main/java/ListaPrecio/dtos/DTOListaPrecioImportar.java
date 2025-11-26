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
public class DTOListaPrecioImportar {

    private int codListaPrecio;
    private Date fechaHoraDesdeListaPrecio;
    private Date fechaHoraHastaListaPrecio;
    private List<DTODetalleListaPrecioImportar> detalleListaPrecioImportar = new ArrayList<>();

    public List<DTODetalleListaPrecioImportar> getDetalleListaPrecioImportar() {
        return detalleListaPrecioImportar;
    }

    public void setDetalleListaPrecioImportar(List<DTODetalleListaPrecioImportar> detalleListaPrecioImportar) {
        this.detalleListaPrecioImportar = detalleListaPrecioImportar;
    }

    public void addDetalleListaPrecioImportar(DTODetalleListaPrecioImportar detalleListaPrecioImportar) {
        this.detalleListaPrecioImportar.add(detalleListaPrecioImportar);
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

}
