package com.springboot.hospital.dto;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ConsultaDTO {

    private Long id;
    private String fechaConsulta;
    private String informe;
    private CitaDTO citaDTO;


    //formato de tipo de fecha
    public Date getFechaConsultaAsDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  simpleDateFormat.parse(this.fechaConsulta);
    }

    public void SetFechaConsultaFromDate(Date fechaConsulta){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.fechaConsulta = simpleDateFormat.format(fechaConsulta);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public CitaDTO getCitaDTO() {
        return citaDTO;
    }

    public void setCitaDTO(CitaDTO citaDTO) {
        this.citaDTO = citaDTO;
    }
}
