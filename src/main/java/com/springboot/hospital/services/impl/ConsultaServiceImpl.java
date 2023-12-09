package com.springboot.hospital.services.impl;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.ConsultaDTO;
import com.springboot.hospital.mapper.CitaMapper;
import com.springboot.hospital.mapper.ConsultaMapper;
import com.springboot.hospital.model.Cita;
import com.springboot.hospital.model.Consulta;
import com.springboot.hospital.repositories.ConsultaRepository;
import com.springboot.hospital.services.CitaServices;
import com.springboot.hospital.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private ConsultaMapper consultaMapper;

    @Autowired
    private CitaServices citaServices;


    @Override
    public List<ConsultaDTO> getAllConsultas() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream().map(consultaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ConsultaDTO> getConsultaById(Long idConsulta) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(idConsulta);
        return consultaOptional.map(consultaMapper::toDTO);
    }

    @Override
    public ConsultaDTO createConsulta(Long idCIta, ConsultaDTO consultaDTO) throws ParseException {
        CitaDTO citaDTO = citaServices.getCitaById(idCIta).orElseThrow(()-> new EntityNotFoundException("Cita no econtrada"));
        Consulta consulta = new Consulta();
        consulta.setCita(citaMapper.toEntity(citaDTO));
        consulta.setFechaConsulta(new Date());
        consulta.setInforme(consultaDTO.getInforme());
        Consulta saveConsulta = consultaRepository.save(consulta);
        return consultaMapper.toDTO(saveConsulta);

    }

    @Override
    public ConsultaDTO updateConsulta(Long id, ConsultaDTO consultaDTO) throws ParseException {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id);
        if (consultaOptional.isPresent()){
            Consulta consulta = consultaOptional.get();
            consulta.setFechaConsulta(consultaDTO.getFechaConsultaAsDate());
            consulta.setInforme(consultaDTO.getInforme());

            Consulta updateConsulta = consultaRepository.save(consulta);
            Cita cita = consulta.getCita();
            if (cita != null){
                CitaDTO citaDTO = new CitaDTO();
                citaDTO.setFecha(cita.getFecha().toString());
                citaDTO.setCancelado(cita.isCancelado());
                citaDTO.setStatusCita(cita.getStatusCita().toString());
                citaDTO.setIdPaciente(cita.getPaciente().getId());
                citaDTO.setIdMedico(cita.getMedico().getId());

                citaServices.updateCita(cita.getId(),citaDTO);

            }
            return consultaMapper.toDTO(updateConsulta);
        }
        return null;
    }

    @Override
    public void deleteConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

    @Override
    public List<ConsultaDTO> getConsultasByInformeContaining(String searchTerm) {
        List<Consulta> consultas = consultaRepository.findByInformeContainingIgnoreCase(searchTerm);
        return consultas.stream().map(consultaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDTO> getConsultasByCita(Cita cita) {
        List<Consulta> consultas = consultaRepository.findByCita(cita);
        return consultas.stream().map(consultaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDTO> getCosnultasByCita(Long idCIta) throws ParseException {
        CitaDTO citaDTO = citaServices.getCitaById(idCIta).orElseThrow(()->new EntityNotFoundException("cita no encontrada"));

        Cita cita = citaMapper.toEntity(citaDTO);
        List<ConsultaDTO> consultas = getConsultasByCita(cita);
        return consultas;

    }
}
