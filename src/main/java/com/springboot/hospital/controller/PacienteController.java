package com.springboot.hospital.controller;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.PacienteDTO;
import com.springboot.hospital.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;


    @PostMapping
    public ResponseEntity<PacienteDTO> savePaciente(@RequestBody PacienteDTO pacienteDTO){
        PacienteDTO savePaciente = pacienteService.createPaciente(pacienteDTO);
        return new ResponseEntity<>(savePaciente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes(){
        List<PacienteDTO> pacienteDTOS = pacienteService.getAllPacientes();
        return new ResponseEntity<>(pacienteDTOS,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id){
        return pacienteService.getPacienteById(id)
                .map(pacienteDTO -> new ResponseEntity<>(pacienteDTO,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO){
        PacienteDTO editPaciente = pacienteService.updatePaciente(id,pacienteDTO);
        if (editPaciente != null){
            return new ResponseEntity<>(editPaciente,HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deletePaciente(@PathVariable Long id){
        pacienteService.deletePaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/citas")
    public  ResponseEntity<Collection<CitaDTO>> getAllCitasByIdPaciente(@PathVariable Long id){
        Collection<CitaDTO> citas = pacienteService.getCitaByPacienteID(id);
        return new ResponseEntity<>(citas,HttpStatus.OK);
    }


}
