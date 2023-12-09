package com.springboot.hospital.repositories;

import com.springboot.hospital.model.Cita;
import com.springboot.hospital.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

    List<Consulta> findByCita(Cita cita);

    List<Consulta> findByInformeContainingIgnoreCase(String searchTerm);
}
