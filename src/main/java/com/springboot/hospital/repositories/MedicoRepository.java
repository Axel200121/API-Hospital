package com.springboot.hospital.repositories;

import com.springboot.hospital.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
    Medico findByNombre(String nombre);

    List<Medico> findByEspecialidad(String especialidad);
}
