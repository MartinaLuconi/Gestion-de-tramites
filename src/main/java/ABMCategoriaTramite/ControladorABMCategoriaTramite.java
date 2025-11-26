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
import java.util.List;

/**
 *
 * @author vickyvelasco
 */
public class ControladorABMCategoriaTramite {
    private ExpertoABMCategoriaTramite expertoABMCategoriaTramite = new ExpertoABMCategoriaTramite();
    public List<DTOCategoriaTramite> buscarCategorias (int codCategoriaTramite, String nombreCategoriaTramite){
        return expertoABMCategoriaTramite.buscarCategorias(codCategoriaTramite, nombreCategoriaTramite);
    }
    public void agregarCategoria(DTONuevaCategoriaTramite dtoNuevaCategoriaTramite) throws CategoriaTramiteException{
     expertoABMCategoriaTramite.agregarCategoria(dtoNuevaCategoriaTramite);
    }
    public void modificarCategoria (DTOModificarCategoriaTramiteIn dtoModificarCategoriaTramiteIn)throws CategoriaTramiteException{
        expertoABMCategoriaTramite.modificarCategoria(dtoModificarCategoriaTramiteIn);
    }
    public DTOModificarCategoriaTramite buscarCategoriaAModificar(int codCategoriaTramite){
        return expertoABMCategoriaTramite.buscarCategoriaAModificar(codCategoriaTramite);
    }
    public void darDeBajaCategoriaTramite(int codCategoriaTramite) throws CategoriaTramiteException{
        expertoABMCategoriaTramite.darDeBajaCategoria(codCategoriaTramite);
    }
}
    
