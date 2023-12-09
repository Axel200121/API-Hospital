package com.springboot.hospital.controller;

import com.springboot.hospital.dto.ConsultaDTO;
import com.springboot.hospital.mapper.CitaMapper;
import com.springboot.hospital.services.CitaServices;
import com.springboot.hospital.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private CitaMapper citaMapper;

    @Autowired
    private CitaServices citaServices;


    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> getAllConsultas(){
        List<ConsultaDTO> consultaDTOS = consultaService.getAllConsultas();
        return new ResponseEntity<>(consultaDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> getConulta(@PathVariable Long id){
        Optional<ConsultaDTO> consultaDTO = consultaService.getConsultaById(id);
        return consultaDTO.map(dto -> new ResponseEntity<>(dto,HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> saveConsulta(@RequestParam Long idCita, @RequestBody ConsultaDTO consultaDTO) throws ParseException {
        ConsultaDTO saveCosnulta = consultaService.createConsulta(idCita,consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCosnulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> updateConsulta(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) throws ParseException {
        ConsultaDTO editConsulta = consultaService.updateConsulta(id,consultaDTO);
        return editConsulta != null ? new ResponseEntity<>(editConsulta,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCosnulta(@PathVariable Long id) {
        consultaService.deleteConsulta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ConsultaDTO>> getAllConsultaByInforme(@RequestParam String seacrhTerm){
        List<ConsultaDTO> consultas = consultaService.getConsultasByInformeContaining(seacrhTerm);
        return new ResponseEntity<>(consultas,HttpStatus.OK);
    }
}
