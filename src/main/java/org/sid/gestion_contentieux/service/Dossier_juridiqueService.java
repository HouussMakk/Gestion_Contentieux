package org.sid.gestion_contentieux.service;

import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.dao.Repository.Dossier_juridiqueRepo;
import org.sid.gestion_contentieux.dao.Repository.MesureTribunalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Dossier_juridique> getDossierByReference(String reference_Dossier) {
        Optional<Dossier_juridique> dossierJuridique = dossierRepository.findByReferenceDossier(reference_Dossier);

        return dossierJuridique;
    }

    @Override
    public Dossier_juridique createDossier(Dossier_juridique dossier) {
        return dossierRepository.save(dossier);
    }

    @Override
    public Dossier_juridique updateDossier(String reference_Dossier, Dossier_juridique dossierDetails) {
        Optional<Dossier_juridique> dossier = getDossierByReference(reference_Dossier);

        // Mettre à jour les propriétés du dossier
        dossier.get().setQualiteagence(dossierDetails.getQualiteagence());
        dossier.get().setNatureLitige(dossierDetails.getNatureLitige());
        dossier.get().setObjetLitige(dossierDetails.getObjetLitige());
        dossier.get().setInstancejudiciaire(dossierDetails.getInstancejudiciaire());
        dossier.get().setPort(dossierDetails.getPort());
        dossier.get().setPartieAdverse(dossierDetails.getPartieAdverse());
        dossier.get().setStadeLitige(dossierDetails.getStadeLitige());
        dossier.get().setAvocat(dossierDetails.getAvocat());

        return dossierRepository.save(dossier.get());
    }
    @Autowired
    private MesureTribunalRepo mesureTribunalRepo;

    @Override
    public boolean deleteDossier(String reference_Dossier) {
        Optional<Dossier_juridique> dossier = getDossierByReference(reference_Dossier);

        // Supprimer d'abord les mesures associées au dossier
        MesureTribunalRepo.deleteByDossierJuridique(dossier.orElse(null));

        // Puis supprimer le dossier lui-même
        dossierRepository.delete(dossier.get());
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

