package org.sid.gestion_contentieux.service;

import org.sid.gestion_contentieux.dao.Entity.PartieAdverse;
import java.util.List;

public interface PartieAdverseManager {
    /**
     * Récupère toutes les parties adverses
     * @return liste des parties adverses
     */
    List<PartieAdverse> getAllPartiesAdverses();

    /**
     * Récupère une partie adverse par son ID
     * @param idPartieAdverse l'ID de la partie adverse
     * @return la partie adverse correspondante
     */
    PartieAdverse getPartieAdverseById(String idPartieAdverse);

    /**
     * Crée une nouvelle partie adverse
     * @param partieAdverse la partie adverse à créer
     * @return la partie adverse créée
     */
    PartieAdverse createPartieAdverse(PartieAdverse partieAdverse);

    /**
     * Met à jour une partie adverse existante
     * @param idPartieAdverse l'ID de la partie adverse à mettre à jour
     * @param partieAdverseDetails les nouvelles données de la partie adverse
     * @return la partie adverse mise à jour
     */
    PartieAdverse updatePartieAdverse(String idPartieAdverse, PartieAdverse partieAdverseDetails);

    /**
     * Supprime une partie adverse
     * @param idPartieAdverse l'ID de la partie adverse à supprimer
     * @return true si la suppression a réussi
     */
    boolean deletePartieAdverse(String idPartieAdverse);
}