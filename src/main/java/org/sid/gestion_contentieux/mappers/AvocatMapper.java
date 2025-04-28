package org.sid.gestion_contentieux.mappers;

import org.sid.gestion_contentieux.dao.Entity.Avocat;
import org.sid.gestion_contentieux.dto.AvocatDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Static mapper utility for converting between Avocat entities and DTOs
 */
public class AvocatMapper {

    /**
     * Converts an Avocat entity to an AvocatDTO
     *
     * @param avocat the entity to convert
     * @return the converted DTO
     */
    public static AvocatDTO entityToDto(Avocat avocat) {
        if (avocat == null) {
            return null;
        }

        AvocatDTO dto = new AvocatDTO();

        dto.setIdAvocat(avocat.getIdAvocat());
        dto.setNomCabinet(avocat.getNom_cabinet());
        dto.setContact(avocat.getContact());

        return dto;
    }

    /**
     * Converts a list of entities to a list of DTOs
     */
    public static List<AvocatDTO> entitiesToDtos(List<Avocat> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(AvocatMapper::entityToDto)
                .collect(Collectors.toList());
    }
}