package org.sid.gestion_contentieux.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.sid.gestion_contentieux.dao.Entity.Avocat;
import org.sid.gestion_contentieux.dao.Repository.AvocatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvocatService implements AvocatManager {

    private final AvocatRepo avocatRepository;

    @Autowired
    public AvocatService(AvocatRepo avocatRepository) {
        this.avocatRepository = avocatRepository;
    }

    @Override
    public List<Avocat> getAllAvocats() {
        return avocatRepository.findAll();
    }

    @Override
    public Avocat getAvocatById(int idAvocat) {
        return avocatRepository.findById((long) idAvocat)
                .orElseThrow(() -> new ResourceNotFoundException("Avocat non trouvé avec l'ID: " + idAvocat));
    }

    @Override
    public Avocat createAvocat(Avocat avocat) {
        return avocatRepository.save(avocat);
    }

    @Override
    public Avocat updateAvocat(int idAvocat, Avocat avocatDetails) {
        Avocat avocat = getAvocatById(idAvocat);

        // Mettre à jour les propriétés
        avocat.setNom_cabinet(avocatDetails.getNom_cabinet());
        avocat.setContact(avocatDetails.getContact());

        return avocatRepository.save(avocat);
    }

    @Override
    public boolean deleteAvocat(int idAvocat) {
        Avocat avocat = getAvocatById(idAvocat);
        avocatRepository.delete(avocat);
        return true;
    }
}