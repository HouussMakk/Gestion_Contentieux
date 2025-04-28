package org.sid.gestion_contentieux.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.dao.Repository.Dossier_juridiqueRepo;
import org.sid.gestion_contentieux.dao.Repository.MesureTribunalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Dossier_juridiqueService implements Dossier_juridiquemanager {
    private  Dossier_juridiqueRepo dossierRepository;
    private MesureTribunalRepo mesureRepository;

    @Autowired
    public Dossier_juridiqueService(Dossier_juridiqueRepo dossierRepository,
                                       MesureTribunalRepo mesureRepository) {
        this.dossierRepository = dossierRepository;
        this.mesureRepository = mesureRepository;
    }

    @Override
    public List<Dossier_juridique> getAllDossiers() {
        return dossierRepository.findAll();
    }

    @Override
    public Dossier_juridique getDossierByReference(String reference_Dossier) {
        return dossierRepository.findById(Long.valueOf(reference_Dossier))
                .orElseThrow(() -> new ResourceNotFoundException("Dossier juridique non trouvé avec la référence: " + reference_Dossier));
    }

    @Override
    public Dossier_juridique createDossier(Dossier_juridique dossier) {
        return dossierRepository.save(dossier);
    }

    @Override
    public Dossier_juridique updateDossier(String reference_Dossier, Dossier_juridique dossierDetails) {
        Dossier_juridique dossier = getDossierByReference(reference_Dossier);

        // Mettre à jour les propriétés du dossier
        dossier.setQualiteagence(dossierDetails.getQualiteagence());
        dossier.setNatureLitige(dossierDetails.getNatureLitige());
        dossier.setObjetLitige(dossierDetails.getObjetLitige());
        dossier.setInstancejudiciaire(dossierDetails.getInstancejudiciaire());
        dossier.setPort(dossierDetails.getPort());
        dossier.setPartieAdverse(dossierDetails.getPartieAdverse());
        dossier.setStadeLitige(dossierDetails.getStadeLitige());
        dossier.setAvocat(dossierDetails.getAvocat());

        return dossierRepository.save(dossier);
    }
    @Autowired
    private MesureTribunalRepo mesureTribunalRepo;

    @Override
    public boolean deleteDossier(String reference_Dossier) {
        Dossier_juridique dossier = getDossierByReference(reference_Dossier);

        // Supprimer d'abord les mesures associées au dossier
        MesureTribunalRepo.deleteByDossierJuridique(dossier);

        // Puis supprimer le dossier lui-même
        dossierRepository.delete(dossier);
        return true;
    }

    @Override
    public List<Dossier_juridique> findByNatureLitige(String natureLitige) {
        return dossierRepository.findByNatureLitige(natureLitige);
    }
    @Override
    public List<Dossier_juridique> findByInstanceJudiciaire(String instanceJudiciaire) {
        return dossierRepository.findByInstancejudiciaire(instanceJudiciaire);
    }





}

