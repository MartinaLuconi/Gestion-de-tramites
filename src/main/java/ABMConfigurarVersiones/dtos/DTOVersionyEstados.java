/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julie
 */
public class DTOVersionyEstados {
    private List<DTOEstadoPosible> estadosPosibleslist = new ArrayList<DTOEstadoPosible>();
    private DTOVersionOutput version;

    public List<DTOEstadoPosible> getEstadosPosibles() {
        return estadosPosibleslist;
    }

    public void setEstadosPosibles(List<DTOEstadoPosible> estadosPosibles) {
        this.estadosPosibleslist = estadosPosibles;
    }

    public DTOVersionOutput getVersion() {
        return version;
    }

    public void setVersion(DTOVersionOutput version) {
        this.version = version;
    }
    public void addEstadosPosibles(DTOEstadoPosible estadosPosibles) {
        estadosPosibleslist.add(estadosPosibles);
    }
}
