package com.springboot.hospital.controller;

import com.springboot.hospital.dto.CitaDTO;
import com.springboot.hospital.dto.MedicoDTO;
import com.springboot.hospital.model.Medico;
import com.springboot.hospital.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;


    @GetMapping
    public ResponseEntity<List<MedicoDTO>> getAllMedicos(){
        List<MedicoDTO> medicos = medicoService.getAllMedicos();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> getMadicoById(@PathVariable Long id){
        return medicoService.getMedicoById(id)
                .map(medico -> new ResponseEntity<>(medico,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> saveMedico(@RequestBody MedicoDTO medicoDTO){
        MedicoDTO saveMedico = medicoService.createMedico(medicoDTO);
        return new ResponseEntity<>(saveMedico,HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> updatemEDICO(@PathVariable Long id, @RequestBody MedicoDTO medicoDTO){
        MedicoDTO editMedico = medicoService.updateMedico(id,medicoDTO);
        if (editMedico != null){
            return new ResponseEntity<>(editMedico,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id){
        medicoService.deleteMedico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/citas")
    public ResponseEntity<Collection<CitaDTO>> getAllCitasByIdMedico(@PathVariable Long id){
        Collection<CitaDTO> citas = medicoService.getCitaByMedicoID(id);
        if (citas != null){
            return new ResponseEntity<>(citas,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<MedicoDTO>> getFindByEspecialidad(@PathVariable String especialidad){
        List<MedicoDTO> medicos = medicoService.getMedicosByEspecialidad(especialidad);
        return new ResponseEntity<>(medicos,HttpStatus.OK);
    }

}
