package org.sid.gestion_contentieux.service;

import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;

import java.util.List;
import java.util.Optional;

public interface Dossier_juridiquemanager {
    /**
     * Récupère tous les dossiers juridiques
     * @return liste des dossiers juridiques
     */
    List<Dossier_juridique> getAllDossiers();

    /**
     * Récupère un dossier juridique par sa référence
     *
     * @param reference la référence du dossier
     * @return le dossier juridique correspondant
     */
    Optional<Dossier_juridique> getDossierByReference(String reference_Dossier);

    /**
     * Crée un nouveau dossier juridique
     * @param dossier le dossier à créer
     * @return le dossier créé
     */
    Dossier_juridique createDossier(Dossier_juridique dossier);

    /**
     * Met à jour un dossier juridique existant
     * @param reference la référence du dossier à mettre à jour
     * @param dossier les nouvelles données du dossier
     * @return le dossier mis à jour
     */
    Dossier_juridique updateDossier(String reference_Dossier, Dossier_juridique dossier);

    /**
     * Supprime un dossier juridique
     * @param reference la référence du dossier à supprimer
     * @return true si la suppression a réussi
     */
    boolean deleteDossier(String reference_Dossier);

    /**
     * Recherche des dossiers par nature de litige
     * @param natureLitige la nature du litige à rechercher
     * @return la liste des dossiers correspondants
     */
    List<Dossier_juridique> findByNatureLitige(String natureLitige);
    List<Dossier_juridique> findByInstanceJudiciaire(String instanceJudiciaire);



}
