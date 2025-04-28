package org.sid.gestion_contentieux.service;

import org.sid.gestion_contentieux.dao.Entity.Avocat;
import java.util.List;

public interface AvocatManager {
    /**
     * Récupère tous les avocats
     * @return liste des avocats
     */
    List<Avocat> getAllAvocats();

    /**
     * Récupère un avocat par son ID
     * @param idAvocat l'ID de l'avocat
     * @return l'avocat correspondant
     */
    Avocat getAvocatById(int idAvocat);

    /**
     * Crée un nouvel avocat
     * @param avocat l'avocat à créer
     * @return l'avocat créé
     */
    Avocat createAvocat(Avocat avocat);

    /**
     * Met à jour un avocat existant
     * @param idAvocat l'ID de l'avocat à mettre à jour
     * @param avocatDetails les nouvelles données de l'avocat
     * @return l'avocat mis à jour
     */
    Avocat updateAvocat(int idAvocat, Avocat avocatDetails);

    /**
     * Supprime un avocat
     * @param idAvocat l'ID de l'avocat à supprimer
     * @return true si la suppression a réussi
     */
    boolean deleteAvocat(int idAvocat);
}