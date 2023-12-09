package com.springboot.hospital.controller;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.mapper.CitaMapper;
import com.springboot.hospital.mapper.MedicoMapper;
import com.springboot.hospital.mapper.PacienteMapper;
import com.springboot.hospital.model.Cita;
import com.springboot.hospital.model.StatusCita;
import com.springboot.hospital.services.CitaServices;
import com.springboot.hospital.services.MedicoService;
import com.springboot.hospital.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaServices citaServices;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private MedicoMapper medicoMapper;

    @Autowired
    private PacienteMapper pacienteMapper;

    @GetMapping
    public ResponseEntity<List<CitaDTO>> getAllCitas(){
        List<CitaDTO> citas = citaServices.getAllCitas();
        return  new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> getCitaById(@PathVariable Long id){
        Optional<CitaDTO> citaDTO = citaServices.getCitaById(id);
        return citaDTO.map(cita-> new ResponseEntity<>(cita,HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{idPaciente}/{idMedico}")
    public ResponseEntity<CitaDTO> saveCita(@RequestBody CitaDTO citaDTO, @PathVariable Long idPaciente, @PathVariable Long idMedico) throws ParseException {
        Cita newCita = citaServices.createCita(citaDTO,idPaciente,idMedico);
        if (newCita == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CitaDTO newCitaDto = citaMapper.toDTO(newCita);
        return new ResponseEntity<>(citaDTO,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> updateCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) throws ParseException {
        CitaDTO citaUpdate = citaServices.updateCita(id, citaDTO);
        if (citaUpdate != null){
            return ResponseEntity.of(Optional.of(citaUpdate));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id){
        citaServices.deleteCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/paciente/{idPaciente}")
    public List<CitaDTO> getCitasByIdPaciente(@PathVariable Long idPaciente){
        return citaServices.getCitasByPacienteId(idPaciente);
    }

    @GetMapping("/medico/{idMedico}")
    public List<CitaDTO> getCitasByIdMedico(@PathVariable Long idMedico){
        return citaServices.getCitasByMedicoId(idMedico);
    }

    @GetMapping("/status/{statusCita}")
    public List<CitaDTO> getCitasByStatus(@PathVariable StatusCita statusCita){
        return citaServices.getCitasByStatusCita(statusCita);
    }
}
