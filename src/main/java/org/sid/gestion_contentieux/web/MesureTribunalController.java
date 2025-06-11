package org.sid.gestion_contentieux.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang.NullArgumentException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.sid.gestion_contentieux.dao.Entity.DocumentAssocie;
import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.dao.Entity.MesureTribunal;
import org.sid.gestion_contentieux.dto.MesureTribunaldto;
import org.sid.gestion_contentieux.mappers.MesureTribunalMapper;
import org.sid.gestion_contentieux.service.DocumentAssocieService;
import org.sid.gestion_contentieux.service.Dossier_juridiqueService;
import org.sid.gestion_contentieux.service.MesureTribunalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mesure")
@CrossOrigin("*")
public class MesureTribunalController {
    private final MesureTribunalService mesureService;
    private final Dossier_juridiqueService dossierService;
    private final DocumentAssocieService documentService;

    public MesureTribunalController(MesureTribunalService mesureService, Dossier_juridiqueService dossierService, DocumentAssocieService documentService) {
        this.mesureService = mesureService;
        this.dossierService = dossierService;
        this.documentService = documentService;
    }

    /**
     * Récupère toutes les mesures tribunal
     */
    @GetMapping
    public ResponseEntity<List<MesureTribunaldto>> getAllMesures() {
        List<MesureTribunaldto> mesures = MesureTribunalMapper.entitiesToDtos(mesureService.getAllMesures());
        mesureService.getAllMesures().forEach((MesureTribunal mesure) -> {
            System.out.println(mesure.getId_Mesure());
        });
        return new ResponseEntity<>(mesures, HttpStatus.OK);
    }

    /**
     * Récupère une mesure tribunal par son ID
     */
    @GetMapping("/{idMesure}")
    @Tag(name = "get", description = "GET methods of Employee APIs")
    public ResponseEntity<MesureTribunaldto> getMesureById(@PathVariable Long idMesure) {
        MesureTribunal mesure = mesureService.getMesureById(idMesure);
        if (mesure == null)
            return new ResponseEntity<>(MesureTribunalMapper.entityToDto(mesure), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(MesureTribunalMapper.entityToDto(mesure), HttpStatus.OK);
    }

    /**
     * Crée une nouvelle mesure tribunal
     */
    @PostMapping
    public ResponseEntity<MesureTribunaldto> createMesure(@RequestBody MesureTribunaldto mesureDto) {
        try {
            // Convertir DTO en entité
            MesureTribunal mesure = new MesureTribunal();
            mesure.settypeMesure(mesureDto.getTypeMesure());
            mesure.setDateMesure(mesureDto.getDateMesure());

            // Récupérer les entités référencées par IDs
            if (!mesureDto.getReferenceDossier().isEmpty()) {
                //setting dosier in mesure
                Optional<Dossier_juridique> dossierJuridique = dossierService.getDossierByReference(mesureDto.getReferenceDossier());
                dossierService.getAllDossiers().forEach(dossier -> {
                    System.out.println(dossier.getReferenceDossier());
                });
                if (dossierJuridique == null)
                    throw new ResourceNotFoundException("Dossier juridique non trouve avec reference dossier:" + mesureDto.getReferenceDossier());
                mesure.setDossierJuridique(dossierJuridique.get());
            }


            if (mesureDto.getDocumentAssocieId() > 0) {
                DocumentAssocie document = documentService.getDocumentById(mesureDto.getDocumentAssocieId());
                if (document == null) {
                    throw new ResourceNotFoundException("Document associé non trouvé avec l'ID: " + mesureDto.getDocumentAssocieId());
                }
                mesure.setDocumentAssocie(document);
            }

            // Persister l'entité
            MesureTribunal createdMesure = mesureService.createMesure(mesureDto);

            // Convertir l'entité créée en DTO pour la réponse
            MesureTribunaldto createdDto = MesureTribunalMapper.entityToDto(createdMesure);

            return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
        } catch (ResourceNotFoundException | IllegalArgumentException e) {
            System.err.println("Erreur lors de la création de la mesure: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.err.println("Erreur lors de la création de la mesure: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{idMesure}")
    public ResponseEntity<MesureTribunaldto> updateMesure(
            @PathVariable Long idMesure,
            @RequestBody MesureTribunaldto mesureDto
    ){
        try {
            MesureTribunal existingMesure = mesureService.getMesureById(idMesure);
            existingMesure.settypeMesure(mesureDto.getTypeMesure());
            existingMesure.setDateMesure(mesureDto.getDateMesure());
            DocumentAssocie document = documentService.getDocumentById(mesureDto.getDocumentAssocieId());
            existingMesure.setDocumentAssocie(document);
            Optional<Dossier_juridique> dossierJuridique = dossierService.getDossierByReference(mesureDto.getReferenceDossier());
            if (!dossierJuridique.isPresent())
                throw new NullArgumentException("DOssier Jurique Not FOund");

            MesureTribunal mesureTribunal = mesureService.updateMesure(idMesure,existingMesure);
            MesureTribunaldto mesureTribunaldto  = MesureTribunalMapper.entityToDto(mesureTribunal);
            return new ResponseEntity<>(mesureTribunaldto, HttpStatus.OK);
        }
        catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du dossier: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Supprime une mesure tribunal
     */
    @DeleteMapping("/{idMesure}")
    public ResponseEntity<Void> deleteMesure(@PathVariable Long idMesure) {
        boolean deleted = mesureService.deleteMesure(idMesure);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Recherche des mesures par type
     */
    @GetMapping("/type/{typeMesure}")
    public ResponseEntity<List<MesureTribunaldto>> getMesuresByType(@PathVariable String typeMesure) {
        List<MesureTribunaldto> mesures = MesureTribunalMapper.entitiesToDtos(mesureService.findByTypeMesure(typeMesure));
        return new ResponseEntity<>(mesures, HttpStatus.OK);
    }

    /**
     * Recherche des mesures par dossier juridique
     */
    @GetMapping("/dossier/{referenceDossier}")
    public ResponseEntity<List<MesureTribunaldto>> getMesuresByDossier(@PathVariable String referenceDossier) {
        List<MesureTribunaldto> mesures = MesureTribunalMapper.entitiesToDtos(mesureService.findByDossierJuridiqueReference(referenceDossier));
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
