package org.sid.gestion_contentieux.service;

import org.sid.gestion_contentieux.dao.Entity.MesureTribunal;
import org.sid.gestion_contentieux.dto.MesureTribunaldto;

import java.util.Date;
import java.util.List;

public interface MesureTribunalManager {


     /**
      * Récupère toutes les mesures tribunal
      * @return liste des mesures tribunal
      */
     List<MesureTribunal> getAllMesures();

     /**
      * Récupère une mesure tribunal par son ID
      * @return la mesure tribunal correspondante
      */
     MesureTribunal getMesureById(int id_Mesure);

     /**
      * Crée une nouvelle mesure tribunal
      * @param mesure la mesure à créer
      * @return la mesure créée
      */
     MesureTribunal createMesure(MesureTribunaldto mesure);

     /**
      * Met à jour une mesure tribunal existante
      * @param id_Mesure l'ID de la mesure à mettre à jour
      * @param mesureDetails les nouvelles données de la mesure
      * @return la mesure mise à jour
      */
     MesureTribunal updateMesure(int id_Mesure, MesureTribunal mesureDetails);

     /**
      * Supprime une mesure tribunal
      * @param id_Mesure l'ID de la mesure à supprimer
      * @return true si la suppression a réussi
      */
     boolean deleteMesure(int id_Mesure);

     /**
      * Recherche des mesures par type de mesure
      * @param typeMesure le type de mesure à rechercher
      * @return la liste des mesures correspondantes
      */
     List<MesureTribunal> findByTypeMesure(String typeMesure);

     /**
      * Recherche des mesures par référence de dossier juridique
      * @param reference_Dossier la référence du dossier
      * @return la liste des mesures correspondantes
      */
     List<MesureTribunal> findByDossierJuridiqueReference(String reference_Dossier);

     /**
      * Recherche des mesures par date
      * @param date la date des mesures à rechercher
      * @return la liste des mesures correspondantes
      */
     List<MesureTribunal> findByDateMesure(Date date);
}





