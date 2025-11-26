
package hola;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;


@Named(value = "holaUI")
@ViewScoped
public class holaUI implements Serializable {

    public holaUI() {
    }
    
    public String irListaClientes() {
        return "/ABMCliente/listaClientes.xhtml?faces-redirect=true";
    }

    public String irABMConsultorLista() {
        return "/ABMConsultor/abmConsultorLista.xhtml?faces-redirect=true";
    }

    public String irConfigurarAgenda() {
        return "/ConfigurarAgenda/configurarAgenda.xhtml?faces-redirect=true";
    }

    public String irABMDocumentacionLista() {
        return "/ABMDocumentacion/abmDocumentacionLista.xhtml?faces-redirect=true";
    }

    public String irMostrarTramitesLista() {
        return "/CargarTramite/mostrarTramitesLista.xhtml?faces-redirect=true";
    }

    public String irSolicitarTramiteWeb() {
        return "/SolicitarTramiteWEB/solicitarTramiteWeb.xhtml?faces-redirect=true";
    }

    public String irABMCategoriaTramiteLista() {
        return "/ABMCategoriaTramite/abmCategoriaTramiteLista.xhtml?faces-redirect=true";
    }

    public String irABMTipoTramiteLista() {
        return "/ABMTipoTramite/abmTipoTramiteLista.xhtml?faces-redirect=true";
    }

    public String irTramitesConsultor() {
        return "/RC_2_wp/tramitesConsultor.xhtml?faces-redirect=true";
    }

    public String irActualizarLP() {
        return "/ListaPrecio/actualizarLP.xhtml?faces-redirect=true";
    }

    public String irConfigurarVersiones() {
        return "/ABMConfigurarVersiones/VersionGrillaUI.xhtml?faces-redirect=true";
    }

    public String irABMEstadoTramiteLista() {
        return "/ABMEstadoTramite/abmEstadoTramiteLista.xhtml?faces-redirect=true";
    }
    
}
