package org.sid.gestion_contentieux.web;

import org.sid.gestion_contentieux.dao.Entity.PartieAdverse;
import org.sid.gestion_contentieux.dto.PartieAdversedto;
import org.sid.gestion_contentieux.mappers.PartieAdverseMapper;
import org.sid.gestion_contentieux.service.PartieAdverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/partiesadverses")
@CrossOrigin("*")
public class PartieAdverseController {

    private final PartieAdverseService partieAdverseService;

    @Autowired
    public PartieAdverseController(PartieAdverseService partieAdverseService) {
        this.partieAdverseService = partieAdverseService;
    }

    @GetMapping
    public ResponseEntity<List<PartieAdversedto>> getAllPartiesAdverses() {
        List<PartieAdverse> partiesAdverses = partieAdverseService.getAllPartiesAdverses();
        List<PartieAdversedto> partieAdverseDtos = partiesAdverses.stream()
                .map(PartieAdverseMapper::entityToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(partieAdverseDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartieAdversedto> getPartieAdverseById(@PathVariable String id) {
        PartieAdverse partieAdverse = partieAdverseService.getPartieAdverseById(id);
        PartieAdversedto partieAdverseDto = PartieAdverseMapper.entityToDto(partieAdverse);
        return new ResponseEntity<>(partieAdverseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PartieAdversedto> createPartieAdverse(@RequestBody PartieAdversedto partieAdverseDto) {
        try {
            // Convertir DTO en entité en utilisant le mapper
            PartieAdverse partieAdverse = PartieAdverseMapper.dtoToEntity(partieAdverseDto);

            // S'assurer que l'ID est NULL pour une nouvelle entité
            partieAdverse.setIdPartieAdverse(null);

            // Créer la partie adverse
            PartieAdverse createdPartieAdverse = partieAdverseService.createPartieAdverse(partieAdverse);

            // Convertir l'entité créée en DTO pour la réponse
            PartieAdversedto createdDto = PartieAdverseMapper.entityToDto(createdPartieAdverse);

            return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la partie adverse: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartieAdversedto> updatePartieAdverse(@PathVariable String id, @RequestBody PartieAdversedto partieAdverseDto) {
        // Conversion simplifiée de DTO vers entité (sans relations)
        PartieAdverse partieAdverse = new PartieAdverse();
        partieAdverse.setNom(partieAdverseDto.getNom());
        partieAdverse.setAdresee(partieAdverseDto.getAdresee());
        partieAdverse.setContact(partieAdverseDto.getContact());

        // Mettre à jour la partie adverse
        PartieAdverse updatedPartieAdverse = partieAdverseService.updatePartieAdverse(id, partieAdverse);

        // Conversion de l'entité vers DTO pour la réponse
        PartieAdversedto updatedPartieAdverseDto = PartieAdverseMapper.entityToDto(updatedPartieAdverse);

        return new ResponseEntity<>(updatedPartieAdverseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartieAdverse(@PathVariable String id) {
        boolean deleted = partieAdverseService.deletePartieAdverse(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}