package com.springboot.hospital.mapper;

import com.springboot.hospital.dto.MedicoDTO;
import com.springboot.hospital.model.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {


    //convertimos de entidad a DTO
    public MedicoDTO toDTO(Medico medico){
        MedicoDTO medicoDTO = new MedicoDTO();

        medicoDTO.setId(medico.getId());
        medicoDTO.setNombre(medico.getNombre());
        medicoDTO.setEmail(medico.getEmail());
        medicoDTO.setEspecialidad(medico.getEspecialidad());
        return medicoDTO;
    }

    //convertimos de DTO a entidad
    public Medico toEntity(MedicoDTO medicoDTO){
        Medico medico = new Medico();

        medico.setId(medicoDTO.getId());
        medico.setNombre(medicoDTO.getNombre());
        medico.setEmail(medico.getEmail());
        medico.setEspecialidad(medicoDTO.getEspecialidad());
        return medico;
    }
}
