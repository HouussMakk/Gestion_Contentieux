package org.sid.gestion_contentieux.service;

import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.dao.Entity.MesureTribunal;
import org.sid.gestion_contentieux.dao.Repository.Dossier_juridiqueRepo;
import org.sid.gestion_contentieux.dao.Repository.MesureTribunalRepo;
import org.sid.gestion_contentieux.dto.MesureTribunaldto;
import org.sid.gestion_contentieux.mappers.MesureTribunalMapper;
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


    @Override
    public List<MesureTribunal> getAllMesures() {
        return mesureRepository.findAll();
    }

    @Override
    public MesureTribunal getMesureById(int idMesure) {
        return mesureRepository.findById(Long.valueOf(idMesure))
                .orElse(null);
    }

    @Override
    public MesureTribunal createMesure(MesureTribunaldto mesureDto) {
        Optional<Dossier_juridique> dossierJuridique =  dossierRepository.findByReference_Dossier(mesureDto.getReferenceDossier());
        if(dossierJuridique == null) {
            throw new NullPointerException("Dossier juridique");
        }
        MesureTribunal mesure = MesureTribunalMapper.dtoToEntity(mesureDto);

        return mesureRepository.save(mesure);
    }

    @Override
    public MesureTribunal updateMesure(int idMesure, MesureTribunal mesureDetails) {
        MesureTribunal mesure = getMesureById(idMesure);

        // Mettre à jour les propriétés de la mesure
        mesure.settypeMesure(mesureDetails.gettypeMesure());
        mesure.setDateMesure(mesureDetails.getDateMesure());
        mesure.setDocumentAssocie(mesureDetails.getDocumentAssocie());
        mesure.setDossierJuridique(mesureDetails.getDossierjuridique());

        return mesureRepository.save(mesure);
    }

    @Override
    public boolean deleteMesure(int idMesure) {
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
            if(ms.getDossierjuridique().reference_Dossier.equals(reference_Dossier))
                resultList.add(ms);
        }
        return resultList;
    }

    @Override
    public List<MesureTribunal> findByDateMesure(Date date) {
        return mesureRepository.findByDateMesure(date);
    }


    }



