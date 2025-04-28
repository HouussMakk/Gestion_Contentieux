package org.sid.gestion_contentieux.service;

import org.sid.gestion_contentieux.dao.Entity.DocumentAssocie;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentAssocieManager {
    /**
     * Récupère tous les documents associés
     * @return liste des documents associés
     */
    List<DocumentAssocie> getAllDocuments();

    /**
     * Récupère un document par son ID
     * @param idDocument l'ID du document
     * @return le document correspondant
     */
    DocumentAssocie getDocumentById(int idDocument);

    /**
     * Crée un nouveau document
     * @param document le document à créer
     * @return le document créé
     */
    DocumentAssocie createDocument(DocumentAssocie document);

    /**
     * Met à jour un document existant
     * @param idDocument l'ID du document à mettre à jour
     * @param documentDetails les nouvelles données du document
     * @return le document mis à jour
     */
    DocumentAssocie updateDocument(int idDocument, DocumentAssocie documentDetails);

    /**
     * Supprime un document
     * @param idDocument l'ID du document à supprimer
     * @return true si la suppression a réussi
     */
    boolean deleteDocument(int idDocument);
    DocumentAssocie uploadDocument(MultipartFile file, String nomDocument, String description, Integer idMesure) throws IOException, IOException;

    /**
     * Récupérer tous les documents associés à un dossier
     * @param referenceDossier La référence du dossier
     * @return La liste des documents associés
     */
    List<DocumentAssocie> getDocumentsByDossierReference(String referenceDossier);

    /**
     * Télécharger un document
     *
     * @param idDocument L'ID du document
     * @return La ressource contenant le document
     */
    Resource downloadDocument(int idDocument);
}