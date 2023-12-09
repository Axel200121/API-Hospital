package com.springboot.hospital.dto;

import lombok.Data;

@Data
public class CitaDTO {

    private Long id;
    private String fecha;
    private  String statusCita;
    private boolean cancelado;
    private  Long idPaciente;
    private  Long idMedico;

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getStatusCita() {
        return statusCita;
    }

    public void setStatusCita(String statusCita) {
        this.statusCita = statusCita;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }
}
