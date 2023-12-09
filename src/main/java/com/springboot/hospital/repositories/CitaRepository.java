package com.springboot.hospital.repositories;


import com.springboot.hospital.model.Cita;
import com.springboot.hospital.model.StatusCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {

    List<Cita> findByPacienteId(Long idPaciente);

    List<Cita> findByMedicoId(Long idMedico);

    List<Cita> findByStatusCita(StatusCita statusCita);
}
