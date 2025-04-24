package org.sid.gestion_contentieux.web;

import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.service.Dossier_juridiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dossiers")
public class Dossier_juridiqueController {
    @RestController
    @RequestMapping("/api/dossiers")
    @CrossOrigin("*")
    public class DossierJuridiqueController {

        private final Dossier_juridiqueService dossierService;

        @Autowired
        public DossierJuridiqueController(Dossier_juridiqueService dossierService) {
            this.dossierService = dossierService;
        }

        @GetMapping
        public ResponseEntity<List<Dossier_juridique>> getAllDossiers() {
            List<Dossier_juridique> dossiers = dossierService.getAllDossiers();
            return new ResponseEntity<>(dossiers, HttpStatus.OK);
        }

        @GetMapping("/{reference}")
        public ResponseEntity<Dossier_juridique> getDossierByReference(@PathVariable String reference_Dossier) {
            Dossier_juridique dossier = dossierService.getDossierByReference(reference_Dossier);
            return new ResponseEntity<>(dossier, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Dossier_juridique> createDossier(@RequestBody Dossier_juridique dossier) {
            Dossier_juridique createdDossier = dossierService.createDossier(dossier);
            return new ResponseEntity<>(createdDossier, HttpStatus.CREATED);
        }

        @PutMapping("/{reference}")
        public ResponseEntity<Dossier_juridique> updateDossier(
                @PathVariable String reference_Dossier,
                @RequestBody Dossier_juridique dossierDetails) {
            Dossier_juridique updatedDossier = dossierService.updateDossier(reference_Dossier, dossierDetails);
            return new ResponseEntity<>(updatedDossier, HttpStatus.OK);
        }

        @DeleteMapping("/{reference}")
        public ResponseEntity<Void> deleteDossier(@PathVariable String reference_Dossier) {
            boolean deleted = dossierService.deleteDossier(reference_Dossier);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @GetMapping("/nature/{natureLitige}")
        public ResponseEntity<List<Dossier_juridique>> getDossiersByNatureLitige(@PathVariable String natureLitige) {
            List<Dossier_juridique> dossiers = dossierService.findByNatureLitige(natureLitige);
            return new ResponseEntity<>(dossiers, HttpStatus.OK);
        }
        @GetMapping("/instance/{instanceJudiciaire}")
        public ResponseEntity<List<Dossier_juridique>> getDossiersByInstanceJudiciaire(@PathVariable String instanceJudiciaire) {
            List<Dossier_juridique> dossiers = dossierService.findByInstanceJudiciaire(instanceJudiciaire);
            return new ResponseEntity<>(dossiers, HttpStatus.OK);
        }
    }}
