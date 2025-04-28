package org.sid.gestion_contentieux.service;

import org.sid.gestion_contentieux.dao.Entity.StadeLitige;
import java.util.List;

public interface StadeLitigeManager {
    /**
     * Récupère tous les stades de litige
     * @return liste des stades de litige
     */
    List<StadeLitige> getAllStadesLitige();

    /**
     * Récupère un stade de litige par son ID
     * @param idStadeLitige l'ID du stade de litige
     * @return le stade de litige correspondant
     */
    StadeLitige getStadeLitigeById(int idStadeLitige);

    /**
     * Crée un nouveau stade de litige
     * @param stadeLitige le stade de litige à créer
     * @return le stade de litige créé
     */
    StadeLitige createStadeLitige(StadeLitige stadeLitige);

    /**
     * Met à jour un stade de litige existant
     * @param idStadeLitige l'ID du stade de litige à mettre à jour
     * @param stadeLitigeDetails les nouvelles données du stade de litige
     * @return le stade de litige mis à jour
     */
    StadeLitige updateStadeLitige(int idStadeLitige, StadeLitige stadeLitigeDetails);

    /**
     * Supprime un stade de litige
     * @param idStadeLitige l'ID du stade de litige à supprimer
     * @return true si la suppression a réussi
     */
    boolean deleteStadeLitige(int idStadeLitige);
}