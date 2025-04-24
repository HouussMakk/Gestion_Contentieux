package org.sid.gestion_contentieux.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.sid.gestion_contentieux.dao.Entity.MesureTribunal;
import org.sid.gestion_contentieux.service.MesureTribunalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/mesure")
public class MesureTribunalController {
    private final MesureTribunalService mesureService;

    @Autowired
    public MesureTribunalController(MesureTribunalService mesureService) {
        this.mesureService = mesureService;
    }

    /**
     * Récupère toutes les mesures tribunal
     */
    @GetMapping
    public ResponseEntity<List<MesureTribunal>> getAllMesures() {
        List<MesureTribunal> mesures = mesureService.getAllMesures();
        return new ResponseEntity<>(mesures, HttpStatus.OK);
    }

    /**
     * Récupère une mesure tribunal par son ID
     */
    @GetMapping("/{id_Mesure}")
    @Tag(name = "get", description = "GET methods of Employee APIs")
    public ResponseEntity<MesureTribunal> getMesureById(@PathVariable int id_Mesure) {
        MesureTribunal mesure = mesureService.getMesureById(id_Mesure);
        if(mesure == null)
            return new ResponseEntity<>(mesure,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mesure, HttpStatus.OK);
    }

    /**
     * Crée une nouvelle mesure tribunal
     */
    @PostMapping
    public ResponseEntity<MesureTribunal> createMesure(@RequestBody MesureTribunal mesure) {
        MesureTribunal createdMesure = mesureService.createMesure(mesure);
        return new ResponseEntity<>(createdMesure, HttpStatus.CREATED);
    }

    /**
     * Met à jour une mesure tribunal existante
     */
    @PutMapping("/{idMesure}")
    public ResponseEntity<MesureTribunal> updateMesure(
            @PathVariable int id_Mesure,
            @RequestBody MesureTribunal mesureDetails) {
        MesureTribunal updatedMesure = mesureService.updateMesure(id_Mesure, mesureDetails);
        return new ResponseEntity<>(updatedMesure, HttpStatus.OK);
    }

    /**
     * Supprime une mesure tribunal
     */
    @DeleteMapping("/{idMesure}")
    public ResponseEntity<Void> deleteMesure(@PathVariable int id_Mesure) {
        boolean deleted = mesureService.deleteMesure(id_Mesure);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Recherche des mesures par type
     */
    @GetMapping("/type/{typeMesure}")
    public ResponseEntity<List<MesureTribunal>> getMesuresByType(@PathVariable String typeMesure) {
        List<MesureTribunal> mesures = mesureService.findByTypeMesure(typeMesure);
        return new ResponseEntity<>(mesures, HttpStatus.OK);
    }

    /**
     * Recherche des mesures par dossier juridique
     */
    @GetMapping("/dossier/{referenceDossier}")
    public ResponseEntity<List<MesureTribunal>> getMesuresByDossier(@PathVariable String reference_Dossier) {
        List<MesureTribunal> mesures = mesureService.findByDossierJuridiqueReference(reference_Dossier);
        return new ResponseEntity<>(mesures, HttpStatus.OK);
    }

    /**
     * Recherche des mesures par date
     */
    @GetMapping("/date/{date}")
    public ResponseEntity<List<MesureTribunal>> getMesuresByDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<MesureTribunal> mesures = mesureService.findByDateMesure(date);
        return new ResponseEntity<>(mesures, HttpStatus.OK);
    }
}
