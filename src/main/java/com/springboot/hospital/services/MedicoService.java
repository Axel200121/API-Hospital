package com.springboot.hospital.services;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.MedicoDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MedicoService {

    List<MedicoDTO> getAllMedicos();

    Optional<MedicoDTO> getMedicoById(Long idMedico);

    MedicoDTO createMedico(MedicoDTO medicoDTO);

    MedicoDTO updateMedico(Long idMedico, MedicoDTO medicoDTO);

    void deleteMedico(Long idMedico);

    Collection<CitaDTO> getCitaByMedicoID(Long idMedico);

    List<MedicoDTO> getMedicosByEspecialidad(String especialidad);

}
