package org.sid.gestion_contentieux.web;

import org.sid.gestion_contentieux.dao.Entity.StadeLitige;
import org.sid.gestion_contentieux.dto.StadeLitigedto;
import org.sid.gestion_contentieux.mappers.StadeLitigeMapper;
import org.sid.gestion_contentieux.service.StadeLitigeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stadeslitige")
@CrossOrigin("*")
public class StadeLitigeController {

    private final StadeLitigeService stadeLitigeService;

    @Autowired
    public StadeLitigeController(StadeLitigeService stadeLitigeService) {
        this.stadeLitigeService = stadeLitigeService;
    }

    @GetMapping
    public ResponseEntity<List<StadeLitigedto>> getAllStadesLitige() {
        List<StadeLitige> stadesLitige = stadeLitigeService.getAllStadesLitige();
        List<StadeLitigedto> stadeLitigeDtos = stadesLitige.stream()
                .map(StadeLitigeMapper::entityToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(stadeLitigeDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StadeLitigedto> getStadeLitigeById(@PathVariable int id) {
        StadeLitige stadeLitige = stadeLitigeService.getStadeLitigeById(id);
        StadeLitigedto stadeLitigeDto = StadeLitigeMapper.entityToDto(stadeLitige);
        return new ResponseEntity<>(stadeLitigeDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StadeLitigedto> createStadeLitige(@RequestBody StadeLitigedto stadeLitigeDto) {
        // Conversion simplifiée de DTO vers entité (sans relations)
        StadeLitige stadeLitige = new StadeLitige();
        stadeLitige.setStedelitige(stadeLitigeDto.getStedelitige());
        stadeLitige.setDatChangementStatut(stadeLitigeDto.getDatChangementStatut());

        // Créer le stade de litige
        StadeLitige createdStadeLitige = stadeLitigeService.createStadeLitige(stadeLitige);

        // Conversion de l'entité vers DTO pour la réponse
        StadeLitigedto createdStadeLitigeDto = StadeLitigeMapper.entityToDto(createdStadeLitige);

        return new ResponseEntity<>(createdStadeLitigeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StadeLitigedto> updateStadeLitige(@PathVariable int id, @RequestBody StadeLitigedto stadeLitigeDto) {
        // Conversion simplifiée de DTO vers entité (sans relations)
        StadeLitige stadeLitige = new StadeLitige();
        stadeLitige.setStedelitige(stadeLitigeDto.getStedelitige());
        stadeLitige.setDatChangementStatut(stadeLitigeDto.getDatChangementStatut());

        // Mettre à jour le stade de litige
        StadeLitige updatedStadeLitige = stadeLitigeService.updateStadeLitige(id, stadeLitige);

        // Conversion de l'entité vers DTO pour la réponse
        StadeLitigedto updatedStadeLitigeDto = StadeLitigeMapper.entityToDto(updatedStadeLitige);

        return new ResponseEntity<>(updatedStadeLitigeDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStadeLitige(@PathVariable int id) {
        boolean deleted = stadeLitigeService.deleteStadeLitige(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}