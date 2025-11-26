/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zippo
 */
public class NodoIU {
   /*
    int codigo;
   String nombre="";
   double xpos=0;
   double ypos=0;   List<Integer> destinos= new ArrayList<Integer>();

*/
    @JsonProperty("codigo")
    private int codigo;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("xpos")
    private double xpos;
    @JsonProperty("ypos")
    private double ypos;
    @JsonProperty("destinos")
    private List<Integer> destinos = new ArrayList<>();


    
    //private List<NodoUI> nodos = new ArrayList();

    /*// Constructor con todos los argumentos
    public NodoIU(int codigo, String nombre, double xpos, double ypos, List<Integer> destinos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.xpos = xpos;
        this.ypos = ypos;
        this.destinos = destinos;
    }*/
    public List<Integer> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<Integer> destinos) {
        this.destinos = destinos;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getXpos() {
        return xpos;
    }

    public void setXpos(double xpos) {
        this.xpos = xpos;
    }

    public double getYpos() {
        return ypos;
    }

    public void setYpos(double ypos) {
        this.ypos = ypos;
    }


   public void addDestino(int destino) {
        this.destinos.add(destino);
    }
}
