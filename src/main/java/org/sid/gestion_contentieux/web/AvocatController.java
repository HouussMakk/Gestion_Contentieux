package org.sid.gestion_contentieux.web;

import org.sid.gestion_contentieux.dao.Entity.Avocat;
import org.sid.gestion_contentieux.dto.AvocatDTO;
import org.sid.gestion_contentieux.mappers.AvocatMapper;
import org.sid.gestion_contentieux.service.AvocatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/avocats")
@CrossOrigin("*")
public class AvocatController {

    private final AvocatService avocatService;

    @Autowired
    public AvocatController(AvocatService avocatService) {
        this.avocatService = avocatService;
    }

    @GetMapping
    public ResponseEntity<List<AvocatDTO>> getAllAvocats() {
        List<Avocat> avocats = avocatService.getAllAvocats();
        List<AvocatDTO> avocatDTOs = avocats.stream()
                .map(AvocatMapper::entityToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(avocatDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvocatDTO> getAvocatById(@PathVariable int id) {
        Avocat avocat = avocatService.getAvocatById(id);
        AvocatDTO avocatDTO = AvocatMapper.entityToDto(avocat);
        return new ResponseEntity<>(avocatDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AvocatDTO> createAvocat(@RequestBody AvocatDTO avocatDTO) {
        // Conversion simplifiée de DTO vers entité (sans relations)
        Avocat avocat = new Avocat();
        avocat.setNom_cabinet(avocatDTO.getNomCabinet());
        avocat.setContact(avocatDTO.getContact());

        // Créer l'avocat
        Avocat createdAvocat = avocatService.createAvocat(avocat);

        // Conversion de l'entité vers DTO pour la réponse
        AvocatDTO createdAvocatDTO = AvocatMapper.entityToDto(createdAvocat);

        return new ResponseEntity<>(createdAvocatDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvocatDTO> updateAvocat(@PathVariable int id, @RequestBody AvocatDTO avocatDTO) {
        // Conversion simplifiée de DTO vers entité (sans relations)
        Avocat avocat = new Avocat();
        avocat.setNom_cabinet(avocatDTO.getNomCabinet());
        avocat.setContact(avocatDTO.getContact());

        // Mettre à jour l'avocat
        Avocat updatedAvocat = avocatService.updateAvocat(id, avocat);

        // Conversion de l'entité vers DTO pour la réponse
        AvocatDTO updatedAvocatDTO = AvocatMapper.entityToDto(updatedAvocat);

        return new ResponseEntity<>(updatedAvocatDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvocat(@PathVariable int id) {
        boolean deleted = avocatService.deleteAvocat(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}