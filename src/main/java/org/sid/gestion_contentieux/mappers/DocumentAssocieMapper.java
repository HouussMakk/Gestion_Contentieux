package org.sid.gestion_contentieux.mappers;

import org.sid.gestion_contentieux.dao.Entity.DocumentAssocie;
import org.sid.gestion_contentieux.dto.DocumentAssociedto;
import org.sid.gestion_contentieux.dto.MesureTribunaldto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Static mapper utility for converting between DocumentAssocie entities and DTOs
 */
public class DocumentAssocieMapper {

    /**
     * Converts a DocumentAssocie entity to a DocumentAssociedto
     *
     * @param document the entity to convert
     * @return the converted DTO
     */
    public static DocumentAssociedto entityToDto(DocumentAssocie document) {
        if (document == null) {
            return null;
        }

        DocumentAssociedto dto = new DocumentAssociedto();

        dto.setIdDocumentAssocie(document.getIdDocumentAssocie());
        dto.setNomDocumentAssocie(document.getNomDocumentAssocie());
        dto.setDateAjoute(document.getDateAjoute());
        dto.setDescription(document.getDescription());
        dto.setDocumentAssocie(document.getDocumentAssocie());

        // Map related mesures if they exist
        if (document.getMesures() != null && !document.getMesures().isEmpty()) {
            List<MesureTribunaldto> mesureDtos = document.getMesures().stream()
                    .map(MesureTribunalMapper::entityToDto)
                    .collect(Collectors.toList());
            dto.setMesures(mesureDtos);
        }

        return dto;
    }

    /**
     * Converts a list of entities to a list of DTOs
     */
    public static List<DocumentAssociedto> entitiesToDtos(List<DocumentAssocie> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(DocumentAssocieMapper::entityToDto)
                .collect(Collectors.toList());
    }
}