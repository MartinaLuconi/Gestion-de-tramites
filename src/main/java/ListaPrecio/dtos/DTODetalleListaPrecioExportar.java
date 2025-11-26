/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.dtos;

/**
 *
 * @author marti
 */
public class DTODetalleListaPrecioExportar {

    private int codTipoTramite;
    private String nombreTipoTramite;
    private double precioTipoTramite;

    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public double getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    public void setPrecioTipoTramite(double precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }
}
