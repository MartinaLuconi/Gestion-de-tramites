package entidades;

public class DetallePrecioTipoTramite extends Entidad {

    private double precioTipoTramite;
    private TipoTramite tipoTramite;
    private ListaPrecio listaPrecio;

    public DetallePrecioTipoTramite() {
    }

    public double getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    public void setPrecioTipoTramite(double precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }

    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public ListaPrecio getListaPrecio() {
        return listaPrecio;
    }

    public void setListaPrecio(ListaPrecio listaPrecio) {
        this.listaPrecio = listaPrecio;
    }

}
