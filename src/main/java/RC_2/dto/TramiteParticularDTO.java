package RC_2.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TramiteParticularDTO {
    private int codTipoTramite;
    private Timestamp fechaHoraFinEntregaDoc;
    private String nombreApellidoCliente;
    private float precioTipoTramite;
    private String observaciones;
    private Timestamp fechaHoraFinTramite;
    private List<HistoricoDTO> historicoDTOList=new ArrayList<>();
    private List<TransicionDTO> TransicionDTOList=new ArrayList<>();
    private EstadoActualDTO  estadoActualDTO;
   
//    private String estadoActual;
//
//    public String getEstadoActual() {
//        return estadoActual;
//    }
//
//    public void setEstadoActual(String estadoActual) {
//        this.estadoActual = estadoActual;
//    }
    
    public EstadoActualDTO getEstadoActualDTO() {
        return estadoActualDTO;
    }

    public void setEstadoActualDTO(EstadoActualDTO estadoActualDTO) {
        this.estadoActualDTO = estadoActualDTO;
    }
    
    
    
    public Timestamp getFechaHoraFinTramite() {
        return fechaHoraFinTramite;
    }

    //METODOS GET
    public void setFechaHoraFinTramite(Timestamp fechaHoraFinTramite) {
        this.fechaHoraFinTramite = fechaHoraFinTramite;
    }

    public int getCodTipoTramite() {
        return codTipoTramite;
    }
    public Timestamp getFechaHoraFinEntregaDoc() {
        return fechaHoraFinEntregaDoc;
    }
    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }
    public float getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    //METODOS SET
    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }
    public void setFechaHoraFinEntregaDoc(Timestamp fechaHoraFinEntregaDoc) {
        this.fechaHoraFinEntregaDoc = fechaHoraFinEntregaDoc;
    }
    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }
    public void setPrecioTipoTramite(float precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
    public List<HistoricoDTO> getHistoricoDTOList() {
        return historicoDTOList;
    }

    public void setHistoricoDTOList(List<HistoricoDTO> historicoDTOList) {
        this.historicoDTOList = historicoDTOList;
    }

    public void addHistoricoDTOList(HistoricoDTO historicoDTO) {
        this.historicoDTOList.add(historicoDTO);
    }
    public List<TransicionDTO> getTransicionDTOList() {
        return TransicionDTOList;
    }

    public void setTransicionDTOList(List<TransicionDTO> TransicionDTOList) {
        this.TransicionDTOList = TransicionDTOList;
    }

    
    

}
