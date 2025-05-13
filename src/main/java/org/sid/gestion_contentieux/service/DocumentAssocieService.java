package org.sid.gestion_contentieux.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.sid.gestion_contentieux.dao.Entity.DocumentAssocie;
import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.dao.Entity.MesureTribunal;
import org.sid.gestion_contentieux.dao.Repository.DocumentAssocieRepo;
import org.sid.gestion_contentieux.dao.Repository.Dossier_juridiqueRepo;
import org.sid.gestion_contentieux.dao.Repository.MesureTribunalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentAssocieService implements DocumentAssocieManager {

    private final DocumentAssocieRepo documentRepository;
    private final MesureTribunalRepo mesureRepository;
    private final Dossier_juridiqueRepo dossierRepository;

    @Autowired
    public DocumentAssocieService(
            DocumentAssocieRepo documentRepository,
            MesureTribunalRepo mesureRepository,
            Dossier_juridiqueRepo dossierRepository) {
        this.documentRepository = documentRepository;
        this.mesureRepository = mesureRepository;
        this.dossierRepository = dossierRepository;
    }

    @Override
    public List<DocumentAssocie> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public DocumentAssocie getDocumentById(int idDocument) {
        return documentRepository.findById((long) idDocument)
                .orElseThrow(() -> new ResourceNotFoundException("Document non trouvé avec l'ID: " + idDocument));
    }

    @Override
    public DocumentAssocie createDocument(DocumentAssocie document) {
        return documentRepository.save(document);
    }

    @Override
    public DocumentAssocie updateDocument(int idDocument, DocumentAssocie documentDetails) {
        DocumentAssocie document = getDocumentById(idDocument);

        // Mettre à jour les propriétés
        document.setNomDocumentAssocie(documentDetails.getNomDocumentAssocie());
        document.setDateAjoute(documentDetails.getDateAjoute());
        document.setDescription(documentDetails.getDescription());
        document.setDocumentAssocie(documentDetails.getDocumentAssocie());

        return documentRepository.save(document);
    }

    @Override
    public boolean deleteDocument(int idDocument) {
        DocumentAssocie document = getDocumentById(idDocument);
        documentRepository.delete(document);
        return true;
    }

    @Override
    public DocumentAssocie uploadDocument(MultipartFile file, String nomDocument, String description, Integer idMesure) throws IOException {
        DocumentAssocie document = new DocumentAssocie();
        document.setNomDocumentAssocie(nomDocument);
        document.setDateAjoute(new java.util.Date());
        document.setDescription(description);

        // Convertir le fichier en Base64 pour stockage
        String documentContent = Base64.getEncoder().encodeToString(file.getBytes());
        document.setDocumentAssocie(documentContent);

        // Sauvegarder le document
        DocumentAssocie savedDocument = documentRepository.save(document);

        // Si une mesure est spécifiée, associer le document à cette mesure
        if (idMesure != null) {
            Optional<MesureTribunal> mesure = mesureRepository.findById(Long.valueOf(idMesure));
            if (mesure.isPresent()) {
                MesureTribunal mesureTribunal = mesure.get();
                mesureTribunal.setDocumentAssocie(savedDocument);
                mesureRepository.save(mesureTribunal);
            }
        }

        return savedDocument;
    }

    @Override
    public List<DocumentAssocie> getDocumentsByDossierReference(String referenceDossier) {
        // Rechercher le dossier par sa référence
        Optional<Dossier_juridique> dossierOpt = dossierRepository.findByReferenceDossier(referenceDossier);
        if (!dossierOpt.isPresent()) {
            return new ArrayList<>();
        }

        Dossier_juridique dossier = dossierOpt.get();
        List<MesureTribunal> mesures = dossier.getMesures();

        // Si le dossier n'a pas de mesures, retourner une liste vide
        if (mesures == null || mesures.isEmpty()) {
            return new ArrayList<>();
        }

        // Récupérer tous les documents associés aux mesures
        return mesures.stream()
                .map(MesureTribunal::getDocumentAssocie)
                .filter(doc -> doc != null)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Resource downloadDocument(int idDocument) {
        DocumentAssocie document = getDocumentById(idDocument);
        // Convertir le contenu Base64 en bytes
        byte[] documentBytes = Base64.getDecoder().decode(document.getDocumentAssocie());
        return new ByteArrayResource(documentBytes);
    }
}