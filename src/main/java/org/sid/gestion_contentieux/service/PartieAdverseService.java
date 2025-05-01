package org.sid.gestion_contentieux.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.sid.gestion_contentieux.dao.Entity.PartieAdverse;
import org.sid.gestion_contentieux.dao.Repository.PartieAdverseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartieAdverseService implements PartieAdverseManager {

    private final PartieAdverseRepo partieAdverseRepository;

    @Autowired
    public PartieAdverseService(PartieAdverseRepo partieAdverseRepository) {
        this.partieAdverseRepository = partieAdverseRepository;
    }

    @Override
    public List<PartieAdverse> getAllPartiesAdverses() {
        return partieAdverseRepository.findAll();
    }

    @Override
    public PartieAdverse getPartieAdverseById(String idPartieAdverse) {
        List<PartieAdverse> partieAdverses = partieAdverseRepository.findAll();
        return partieAdverseRepository.findById(Long.valueOf(idPartieAdverse))
                .orElseThrow(() -> new ResourceNotFoundException("Partie adverse non trouvée avec l'ID: " + idPartieAdverse));
    }

    @Override
    public PartieAdverse createPartieAdverse(PartieAdverse partieAdverse) {
        return partieAdverseRepository.save(partieAdverse);
    }

    @Override
    public PartieAdverse updatePartieAdverse(String idPartieAdverse, PartieAdverse partieAdverseDetails) {
        PartieAdverse partieAdverse = getPartieAdverseById(idPartieAdverse);

        // Mettre à jour les propriétés
        partieAdverse.setNom(partieAdverseDetails.getNom());
        partieAdverse.setAdresee(partieAdverseDetails.getAdresee());
        partieAdverse.setContact(partieAdverseDetails.getContact());

        return partieAdverseRepository.save(partieAdverse);
    }

    @Override
    public boolean deletePartieAdverse(String idPartieAdverse) {
        PartieAdverse partieAdverse = getPartieAdverseById(idPartieAdverse);
        partieAdverseRepository.delete(partieAdverse);
        return true;
    }
}