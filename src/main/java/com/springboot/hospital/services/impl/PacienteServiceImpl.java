package com.springboot.hospital.services.impl;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.PacienteDTO;
import com.springboot.hospital.mapper.CitaMapper;
import com.springboot.hospital.mapper.PacienteMapper;
import com.springboot.hospital.model.Cita;
import com.springboot.hospital.model.Paciente;
import com.springboot.hospital.repositories.PacienteRepository;
import com.springboot.hospital.services.CitaServices;
import com.springboot.hospital.services.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService {


    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CitaServices citaServices;

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private CitaMapper citaMapper;



    @Override
    public List<PacienteDTO> getAllPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(pacienteMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<PacienteDTO> getPacienteById(Long idPaciente) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(idPaciente);
        return pacienteOptional.map(pacienteMapper::toDTO);
    }

    @Override
    public PacienteDTO createPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        paciente = pacienteRepository.save(paciente);
        return pacienteMapper.toDTO(paciente);
    }

    @Override
    public PacienteDTO updatePaciente(Long idPaciente, PacienteDTO pacienteDTO) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(idPaciente);
        if (pacienteOptional.isPresent()){
            Paciente paciente = pacienteOptional.get();
            paciente.setNombre(pacienteDTO.getNombre());
            paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
            paciente.setEnfermedad(pacienteDTO.isEnfermedad());
            paciente = pacienteRepository.save(paciente);
            return pacienteMapper.toDTO(paciente);
        }
        return null;
    }

    @Override
    public void deletePaciente(Long idPaciente) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(idPaciente);
        if (pacienteOptional.isPresent()){
            Paciente paciente = pacienteOptional.get();
            for (Cita cita: paciente.getCitas()){
                citaServices.deleteCita(cita.getId());
            }
            pacienteRepository.deleteById(idPaciente);
        }
    }

    @Override
    public List<CitaDTO> getCitaByPacienteID(Long idPaciente) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(idPaciente);
        return pacienteOptional.map(paciente -> paciente.getCitas().stream()
                .map(citaMapper::toDTO)
                .collect(Collectors.toList()))
                .orElse(null);
    }
}
