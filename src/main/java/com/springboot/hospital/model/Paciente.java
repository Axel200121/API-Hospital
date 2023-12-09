package com.springboot.hospital.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Temporal(TemporalType.DATE) //sirve para que se guarde en formato date
    private Date fechaNacimiento;

    private boolean enfermedad;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private Collection<Cita> citas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(boolean enfermedad) {
        this.enfermedad = enfermedad;
    }

    public Collection<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Collection<Cita> citas) {
        this.citas = citas;
    }
}
