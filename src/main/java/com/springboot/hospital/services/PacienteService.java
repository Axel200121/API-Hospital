package com.springboot.hospital.services;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.PacienteDTO;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    List<PacienteDTO> getAllPacientes();

    Optional<PacienteDTO> getPacienteById(Long idPaciente);

    PacienteDTO createPaciente(PacienteDTO pacienteDTO);

    PacienteDTO updatePaciente(Long idPaciente, PacienteDTO pacienteDTO);

    void deletePaciente(Long idPaciente);

    List<CitaDTO> getCitaByPacienteID(Long idPaciente);


}
