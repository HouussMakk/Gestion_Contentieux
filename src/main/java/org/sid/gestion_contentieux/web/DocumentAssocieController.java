package org.sid.gestion_contentieux.web;

import org.sid.gestion_contentieux.dao.Entity.DocumentAssocie;
import org.sid.gestion_contentieux.dto.DocumentAssociedto;
import org.sid.gestion_contentieux.mappers.DocumentAssocieMapper;
import org.sid.gestion_contentieux.service.DocumentAssocieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin("*")
public class DocumentAssocieController {

    private final DocumentAssocieService documentService;

    @Autowired
    public DocumentAssocieController(DocumentAssocieService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public ResponseEntity<List<DocumentAssociedto>> getAllDocuments() {
        List<DocumentAssocie> documents = documentService.getAllDocuments();
        List<DocumentAssociedto> documentDtos = documents.stream()
                .map(DocumentAssocieMapper::entityToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(documentDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentAssociedto> getDocumentById(@PathVariable int id) {
        DocumentAssocie document = documentService.getDocumentById(id);
        DocumentAssociedto documentDto = DocumentAssocieMapper.entityToDto(document);
        return new ResponseEntity<>(documentDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DocumentAssociedto> createDocument(@RequestBody DocumentAssociedto documentDto) {
        // Conversion simplifiée de DTO vers entité (sans relations)
        DocumentAssocie document = new DocumentAssocie();
        document.setNomDocumentAssocie(documentDto.getNomDocumentAssocie());
        document.setDateAjoute(documentDto.getDateAjoute());
        document.setDescription(documentDto.getDescription());
        document.setDocumentAssocie(documentDto.getDocumentAssocie());

        // Créer le document
        DocumentAssocie createdDocument = documentService.createDocument(document);

        // Conversion de l'entité vers DTO pour la réponse
        DocumentAssociedto createdDocumentDto = DocumentAssocieMapper.entityToDto(createdDocument);

        return new ResponseEntity<>(createdDocumentDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentAssociedto> updateDocument(@PathVariable int id, @RequestBody DocumentAssociedto documentDto) {
        // Conversion simplifiée de DTO vers entité (sans relations)
        DocumentAssocie document = new DocumentAssocie();
        document.setNomDocumentAssocie(documentDto.getNomDocumentAssocie());
        document.setDateAjoute(documentDto.getDateAjoute());
        document.setDescription(documentDto.getDescription());
        document.setDocumentAssocie(documentDto.getDocumentAssocie());

        // Mettre à jour le document
        DocumentAssocie updatedDocument = documentService.updateDocument(id, document);

        // Conversion de l'entité vers DTO pour la réponse
        DocumentAssociedto updatedDocumentDto = DocumentAssocieMapper.entityToDto(updatedDocument);

        return new ResponseEntity<>(updatedDocumentDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable int id) {
        boolean deleted = documentService.deleteDocument(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Télécharger un fichier et l'associer à une mesure
     */
    @PostMapping("/upload")
    public ResponseEntity<DocumentAssociedto> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("nomDocument") String nomDocument,
            @RequestParam("description") String description,
            @RequestParam(value = "idMesure", required = false) Integer idMesure) {

        try {
            DocumentAssocie uploadedDocument = documentService.uploadDocument(file, nomDocument, description, idMesure);
            DocumentAssociedto documentDto = DocumentAssocieMapper.entityToDto(uploadedDocument);
            return new ResponseEntity<>(documentDto, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Récupérer tous les documents associés à un dossier juridique
     */
    @GetMapping("/dossier/{referenceDossier}")
    public ResponseEntity<List<DocumentAssociedto>> getDocumentsByDossier(@PathVariable String referenceDossier) {
        List<DocumentAssocie> documents = documentService.getDocumentsByDossierReference(referenceDossier);
        List<DocumentAssociedto> documentDtos = documents.stream()
                .map(DocumentAssocieMapper::entityToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(documentDtos, HttpStatus.OK);
    }

    /**
     * Télécharger un document par son ID
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable int id) {
        DocumentAssocie document = documentService.getDocumentById(id);
        Resource fileResource = documentService.downloadDocument(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getNomDocumentAssocie() + "\"")
                .body(fileResource);
    }
}