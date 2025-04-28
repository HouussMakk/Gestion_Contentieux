package org.sid.gestion_contentieux.mappers;

import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.dao.Entity.StadeLitige;
import org.sid.gestion_contentieux.dto.StadeLitigedto;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Static mapper utility for converting between StadeLitige entities and DTOs
 */
public class StadeLitigeMapper {

    /**
     * Converts a StadeLitige entity to a StadeLitigedto
     *
     * @param stadeLitige the entity to convert
     * @return the converted DTO
     */
    public static StadeLitigedto entityToDto(StadeLitige stadeLitige) {
        if (stadeLitige == null) {
            return null;
        }

        StadeLitigedto dto = new StadeLitigedto();

        dto.setIdstadelitige(stadeLitige.getIdstadelitige());
        dto.setStedelitige(stadeLitige.getStedelitige());
        dto.setDatChangementStatut(stadeLitige.getDatChangementStatut());

        // Map dossier references if they exist
        if (stadeLitige.getDossiers() != null && !stadeLitige.getDossiers().isEmpty()) {
            List<String> dossierReferences = stadeLitige.getDossiers().stream()
                    .map(Dossier_juridique::getReference_Dossier)
                    .collect(Collectors.toList());
            dto.setDossiersReferences(dossierReferences);
        }

        return dto;
    }

    /**
     * Converts a list of entities to a list of DTOs
     */
    public static List<StadeLitigedto> entitiesToDtos(List<StadeLitige> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(StadeLitigeMapper::entityToDto)
                .collect(Collectors.toList());
    }
}