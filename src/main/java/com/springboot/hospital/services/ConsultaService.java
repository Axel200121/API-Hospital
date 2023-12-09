package com.springboot.hospital.services;

import com.springboot.hospital.dto.ConsultaDTO;
import com.springboot.hospital.model.Cita;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public interface ConsultaService {

    List<ConsultaDTO> getAllConsultas();

    Optional<ConsultaDTO> getConsultaById(Long idConsulta);

    ConsultaDTO createConsulta(Long idCIta, ConsultaDTO consultaDTO) throws ParseException;

    ConsultaDTO updateConsulta(Long id, ConsultaDTO consultaDTO) throws ParseException;

    void deleteConsulta(Long id);

    List<ConsultaDTO> getConsultasByInformeContaining(String searchTerm);

    List<ConsultaDTO> getConsultasByCita(Cita cita);

    List<ConsultaDTO> getCosnultasByCita(Long idCIta) throws ParseException;


}
