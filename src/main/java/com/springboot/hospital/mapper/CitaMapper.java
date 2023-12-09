package com.springboot.hospital.mapper;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.model.Cita;
import com.springboot.hospital.model.Medico;
import com.springboot.hospital.model.Paciente;
import com.springboot.hospital.model.StatusCita;
import com.springboot.hospital.repositories.MedicoRepository;
import com.springboot.hospital.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component //para usar las clases mapperss deben llevar esta anotación
public class CitaMapper {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    //transformamos una entidad a un dto
    public CitaDTO toDTO(Cita cita){
        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setId(cita.getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate = simpleDateFormat.format(cita.getFecha());
        citaDTO.setFecha(formateDate);
        citaDTO.setCancelado(cita.isCancelado());
        citaDTO.setStatusCita(cita.getStatusCita().name());
        citaDTO.setIdPaciente(cita.getPaciente().getId());
        citaDTO.setIdMedico(cita.getMedico().getId());

        return citaDTO;
    }

    //transformamos un dto a una entidad
    public Cita toEntity(CitaDTO citaDTO, Paciente paciente, Medico medico) throws ParseException {
        Cita cita = new Cita();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(citaDTO.getFecha());

        cita.setId(citaDTO.getId());
        cita.setFecha(date);
        cita.setCancelado(citaDTO.isCancelado());
        cita.setStatusCita(StatusCita.valueOf(citaDTO.getStatusCita()));
        cita.setPaciente(paciente);
        cita.setMedico(medico);

        return  cita;
    }

    //transformamos un dto a una entidad esta es un segundo metodo
    public Cita toEntity(CitaDTO citaDTO) throws ParseException {
        Cita cita = new Cita();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(citaDTO.getFecha());

        cita.setFecha(date);
        cita.setCancelado(citaDTO.isCancelado());
        cita.setStatusCita(StatusCita.valueOf(citaDTO.getStatusCita()));

        Optional<Paciente> paciente = pacienteRepository.findById(citaDTO.getIdPaciente());
        Paciente pacienteBBDD = paciente.get();
        cita.setPaciente(pacienteBBDD);

        Optional<Medico> medico = medicoRepository.findById(citaDTO.getIdMedico());
        Medico medicoBBDD = medico.get();
        cita.setMedico(medicoBBDD);

        return  cita;
    }


}

