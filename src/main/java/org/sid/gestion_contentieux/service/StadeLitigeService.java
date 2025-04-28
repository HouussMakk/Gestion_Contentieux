package org.sid.gestion_contentieux.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.sid.gestion_contentieux.dao.Entity.StadeLitige;
import org.sid.gestion_contentieux.dao.Repository.StadeLitigeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StadeLitigeService implements StadeLitigeManager {

    private final StadeLitigeRepo stadeLitigeRepository;

    @Autowired
    public StadeLitigeService(StadeLitigeRepo stadeLitigeRepository) {
        this.stadeLitigeRepository = stadeLitigeRepository;
    }

    @Override
    public List<StadeLitige> getAllStadesLitige() {
        return stadeLitigeRepository.findAll();
    }

    @Override
    public StadeLitige getStadeLitigeById(int idStadeLitige) {
        // Recherche par ID
        Optional<StadeLitige> stadeLitige = stadeLitigeRepository.findAll().stream()
                .filter(s -> s.getIdstadelitige() == idStadeLitige)
                .findFirst();

        return stadeLitige.orElseThrow(() ->
                new ResourceNotFoundException("Stade de litige non trouvé avec l'ID: " + idStadeLitige)
        );
    }

    @Override
    public StadeLitige createStadeLitige(StadeLitige stadeLitige) {
        return stadeLitigeRepository.save(stadeLitige);
    }

    @Override
    public StadeLitige updateStadeLitige(int idStadeLitige, StadeLitige stadeLitigeDetails) {
        StadeLitige stadeLitige = getStadeLitigeById(idStadeLitige);

        // Mettre à jour les propriétés
        stadeLitige.setStedelitige(stadeLitigeDetails.getStedelitige());
        stadeLitige.setDatChangementStatut(stadeLitigeDetails.getDatChangementStatut());

        return stadeLitigeRepository.save(stadeLitige);
    }

    @Override
    public boolean deleteStadeLitige(int idStadeLitige) {
        StadeLitige stadeLitige = getStadeLitigeById(idStadeLitige);
        stadeLitigeRepository.delete(stadeLitige);
        return true;
    }
}