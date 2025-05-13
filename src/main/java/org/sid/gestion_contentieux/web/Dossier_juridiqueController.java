package org.sid.gestion_contentieux.web;

import org.sid.gestion_contentieux.dao.Entity.*;
import org.sid.gestion_contentieux.dto.Dossier_juridiquedto;
import org.sid.gestion_contentieux.mappers.Dossier_juridiqueMapper;
import org.sid.gestion_contentieux.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dossiers")
@CrossOrigin("*")
public class Dossier_juridiqueController {

    private final Dossier_juridiqueService dossierService;
    private final PortService portService;
    private final PartieAdverseService partieAdverseService;
    private final StadeLitigeService stadeLitigeService;
    private final AvocatService avocatService;

    @Autowired
    public Dossier_juridiqueController(
            Dossier_juridiqueService dossierService,
            PortService portService,
            PartieAdverseService partieAdverseService,
            StadeLitigeService stadeLitigeService,
            AvocatService avocatService) {
        this.dossierService = dossierService;
        this.portService = portService;
        this.partieAdverseService = partieAdverseService;
        this.stadeLitigeService = stadeLitigeService;
        this.avocatService = avocatService;
    }

    @GetMapping
    public ResponseEntity<List<Dossier_juridiquedto>> getAllDossiers() {
        List<Dossier_juridique> dossiers = dossierService.getAllDossiers();
        List<Dossier_juridiquedto> dossierDtos = dossiers.stream()
                .map(Dossier_juridiqueMapper::entityToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dossierDtos, HttpStatus.OK);
    }

    @GetMapping("/{reference}")
    public ResponseEntity<Dossier_juridiquedto> getDossierByReference(@PathVariable String reference) {
        Optional<Dossier_juridique> dossier = dossierService.getDossierByReference(reference);
        Dossier_juridiquedto dossierDto = Dossier_juridiqueMapper.entityToDto(dossier.get());
        return new ResponseEntity<>(dossierDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dossier_juridiquedto> createDossier(@RequestBody Dossier_juridiquedto dossierDto) {
        try {
            // Convertir DTO en entité
            Dossier_juridique dossier = new Dossier_juridique();
            dossier.setReferenceDossier(dossierDto.getReferenceDossier());
            dossier.setQualiteagence(dossierDto.getQualiteAgence());
            dossier.setNatureLitige(dossierDto.getNatureLitige());
            dossier.setObjetLitige(dossierDto.getObjetLitige());
            dossier.setInstancejudiciaire(dossierDto.getInstanceJudiciaire());

            // Récupérer les entités référencées par IDs
            if (dossierDto.getCodePort() != null && !dossierDto.getCodePort().isEmpty()) {
                Port port = portService.getPortByCode(dossierDto.getCodePort());
                port.setDossiers(new ArrayList<>());
                dossier.setPort(port);
            }

            if (dossierDto.getPartieAdverseId() != null) {
                PartieAdverse partieAdverse = partieAdverseService.getPartieAdverseById(dossierDto.getPartieAdverseId().toString());
                dossier.setPartieAdverse(partieAdverse);
            }

            if (dossierDto.getStadeLitigeId() > 0) {
                StadeLitige stadeLitige = stadeLitigeService.getStadeLitigeById(dossierDto.getStadeLitigeId());
                dossier.setStadeLitige(stadeLitige);
            }

            if (dossierDto.getAvocatId() > 0) {
                Avocat avocat = avocatService.getAvocatById(dossierDto.getAvocatId());
                dossier.setAvocat(avocat);
            }

            // Persister l'entité
            dossier.setMesures(new ArrayList<>());
            Dossier_juridique createdDossier = dossierService.createDossier(dossier);
            // Convertir l'entité créée en DTO pour la réponse
            Dossier_juridiquedto createdDto = Dossier_juridiqueMapper.entityToDto(createdDossier);

            return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du dossier: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{reference}")
    public ResponseEntity<Dossier_juridiquedto> updateDossier(
            @PathVariable String reference,
            @RequestBody Dossier_juridiquedto dossierDto) {
        try {
            // Récupérer le dossier existant
            Dossier_juridique existingDossier = dossierService.getDossierByReference(reference).get();

            // Mettre à jour les propriétés de base
            existingDossier.setQualiteagence(dossierDto.getQualiteAgence());
            existingDossier.setNatureLitige(dossierDto.getNatureLitige());
            existingDossier.setObjetLitige(dossierDto.getObjetLitige());
            existingDossier.setInstancejudiciaire(dossierDto.getInstanceJudiciaire());

            // Mettre à jour les références
            if (dossierDto.getCodePort() != null && !dossierDto.getCodePort().isEmpty()) {
                Port port = portService.getPortByCode(dossierDto.getCodePort());
                existingDossier.setPort(port);
            }

            if (dossierDto.getPartieAdverseId() != null) {
                PartieAdverse partieAdverse = partieAdverseService.getPartieAdverseById(dossierDto.getPartieAdverseId().toString());
                existingDossier.setPartieAdverse(partieAdverse);
            }

            if (dossierDto.getStadeLitigeId() > 0) {
                StadeLitige stadeLitige = stadeLitigeService.getStadeLitigeById(dossierDto.getStadeLitigeId());
                existingDossier.setStadeLitige(stadeLitige);
            }

            if (dossierDto.getAvocatId() > 0) {
                Avocat avocat = avocatService.getAvocatById(dossierDto.getAvocatId());
                existingDossier.setAvocat(avocat);
            }

            // Persister les modifications
            Dossier_juridique updatedDossier = dossierService.updateDossier(reference, existingDossier);

            // Convertir en DTO pour la réponse
            Dossier_juridiquedto updatedDto = Dossier_juridiqueMapper.entityToDto(updatedDossier);

            return new ResponseEntity<>(updatedDto, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du dossier: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{reference}")
    public ResponseEntity<Void> deleteDossier(@PathVariable String reference) {
        boolean deleted = dossierService.deleteDossier(reference);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/nature/{natureLitige}")
    public ResponseEntity<List<Dossier_juridiquedto>> getDossiersByNatureLitige(@PathVariable String natureLitige) {
        List<Dossier_juridique> dossiers = dossierService.findByNatureLitige(natureLitige);
        List<Dossier_juridiquedto> dossierDtos = dossiers.stream()
                .map(Dossier_juridiqueMapper::entityToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dossierDtos, HttpStatus.OK);
    }

    @GetMapping("/instance/{instanceJudiciaire}")
    public ResponseEntity<List<Dossier_juridiquedto>> getDossiersByInstanceJudiciaire(@PathVariable String instanceJudiciaire) {
        List<Dossier_juridique> dossiers = dossierService.findByInstanceJudiciaire(instanceJudiciaire);
        List<Dossier_juridiquedto> dossierDtos = dossiers.stream()
                .map(Dossier_juridiqueMapper::entityToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dossierDtos, HttpStatus.OK);
    }
}