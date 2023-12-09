package com.springboot.hospital.services.impl;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.MedicoDTO;
import com.springboot.hospital.mapper.CitaMapper;
import com.springboot.hospital.mapper.MedicoMapper;
import com.springboot.hospital.model.Cita;
import com.springboot.hospital.model.Medico;
import com.springboot.hospital.repositories.MedicoRepository;
import com.springboot.hospital.services.CitaServices;
import com.springboot.hospital.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private CitaServices citaServices;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private MedicoMapper medicoMapper;



    @Override
    public List<MedicoDTO> getAllMedicos() {
       List<Medico> medicos = medicoRepository.findAll();
       return medicos.stream().map(medicoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<MedicoDTO> getMedicoById(Long idMedico) {
        Optional<Medico> medicoOptional = medicoRepository.findById(idMedico);
        return medicoOptional.map(medicoMapper::toDTO);
    }

    @Override
    public MedicoDTO createMedico(MedicoDTO medicoDTO) {
        Medico medico = medicoMapper.toEntity(medicoDTO);
        medico = medicoRepository.save(medico);
        return medicoMapper.toDTO(medico);
    }

    @Override
    public MedicoDTO updateMedico(Long idMedico, MedicoDTO medicoDTO) {
        Optional<Medico> medicoOptional = medicoRepository.findById(idMedico);
        if (medicoOptional.isPresent()){
            Medico medico = medicoOptional.get();
            medico.setNombre(medicoDTO.getNombre());
            medico.setEmail(medicoDTO.getEmail());
            medico.setEspecialidad(medicoDTO.getEspecialidad());

            medico = medicoRepository.save(medico);
            return medicoMapper.toDTO(medico);
        }
        return null;
    }

    @Override
    public void deleteMedico(Long idMedico) {
        Optional<Medico> medicoOptional = medicoRepository.findById(idMedico);
        if (medicoOptional.isPresent()){
            Medico medico = medicoOptional.get();
            for (Cita cita : medico.getCitas()){
                citaServices.deleteCita(cita.getId());
            }
            medicoRepository.deleteById(idMedico);
        }
    }

    @Override
    public Collection<CitaDTO> getCitaByMedicoID(Long idMedico) {
        Optional<Medico> medicoOptional = medicoRepository.findById(idMedico);
        return medicoOptional.map(medico -> medico.getCitas().stream()
                .map(citaMapper::toDTO)
                .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public List<MedicoDTO> getMedicosByEspecialidad(String especialidad) {
        List<Medico> medicoOptional = medicoRepository.findByEspecialidad(especialidad);
        return medicoOptional.stream().map(medicoMapper::toDTO).collect(Collectors.toList());
    }

}
