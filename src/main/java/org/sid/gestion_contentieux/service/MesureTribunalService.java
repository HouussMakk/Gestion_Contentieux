package org.sid.gestion_contentieux.service;

import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.dao.Entity.MesureTribunal;
import org.sid.gestion_contentieux.dao.Repository.DocumentAssocieRepo;
import org.sid.gestion_contentieux.dao.Repository.Dossier_juridiqueRepo;
import org.sid.gestion_contentieux.dao.Repository.MesureTribunalRepo;
import org.sid.gestion_contentieux.dto.MesureTribunaldto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MesureTribunalService implements MesureTribunalManager{
    @Autowired
    private  MesureTribunalRepo mesureRepository;
    @Autowired
    private  Dossier_juridiqueRepo dossierRepository;;
    @Autowired
    private DocumentAssocieRepo documentAssocieRepository;
    @Autowired
    private DocumentAssocieService documentAssocieService;
    @Autowired
    private Dossier_juridiqueService dossier_juridiqueService;


    @Override
    public List<MesureTribunal> getAllMesures() {
        return mesureRepository.findAll();
    }

    @Override
    public MesureTribunal getMesureById(Long idMesure) {
        return mesureRepository.findById(idMesure)
                .orElse(null);
    }

    @Override
    public MesureTribunal createMesure(MesureTribunaldto mesureDto) {
        // Vérifier si le dossier existe
        Optional<Dossier_juridique> dossierOpt = dossierRepository.findByReferenceDossier(mesureDto.getReferenceDossier());
        if (!dossierOpt.isPresent()) {
            throw new RuntimeException("Dossier juridique non trouvé avec la référence: " + mesureDto.getReferenceDossier());
        }

        // Créer une nouvelle mesure
        MesureTribunal mesure = new MesureTribunal();
        mesure.settypeMesure(mesureDto.getTypeMesure());
        mesure.setDateMesure(mesureDto.getDateMesure());
        mesure.setDossierJuridique(dossierOpt.get());

        // Enregistrer et retourner la mesure
        return mesureRepository.save(mesure);
    }



    @Override
    public MesureTribunal updateMesure(Long idMesure, MesureTribunal mesureDetails) {
        MesureTribunal mesure = getMesureById(idMesure);

        // Mettre à jour les propriétés de la mesure
        mesure.settypeMesure(mesureDetails.gettypeMesure());
        mesure.setDateMesure(mesureDetails.getDateMesure());
        mesure.setDocumentAssocie(mesureDetails.getDocumentAssocie());
        mesure.setDossierJuridique(mesureDetails.getDossierjuridique());

        return mesureRepository.save(mesure);
    }

    @Override
    public boolean deleteMesure(Long idMesure) {
        MesureTribunal mesure = getMesureById(idMesure);
        mesureRepository.delete(mesure);
        return true;
    }

    @Override
    public List<MesureTribunal> findByTypeMesure(String typeMesure) {
        return mesureRepository.findByTypeMesure(typeMesure);
    }

    @Override
    public List<MesureTribunal> findByDossierJuridiqueReference(String reference_Dossier) {
        List<MesureTribunal> resultList = new ArrayList<>();
        for (MesureTribunal ms : mesureRepository.findAll()){
            if(ms.getDossierjuridique().referenceDossier.equals(reference_Dossier))
                resultList.add(ms);
        }
        return resultList;
    }

    @Override
    public List<MesureTribunal> findByDateMesure(Date date) {
        return mesureRepository.findByDateMesure(date);
    }


    }



