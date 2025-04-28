package org.sid.gestion_contentieux.mappers;

import org.sid.gestion_contentieux.dao.Entity.PartieAdverse;
import org.sid.gestion_contentieux.dto.PartieAdversedto;

/**
 * Static mapper utility for converting between PartieAdverse entities and DTOs
 */
public class PartieAdverseMapper {

    /**
     * Converts a PartieAdverse entity to a PartieAdversedto
     *
     * @param partieAdverse the entity to convert
     * @return the converted DTO
     */
    public static PartieAdversedto entityToDto(PartieAdverse partieAdverse) {
        if (partieAdverse == null) {
            return null;
        }

        PartieAdversedto dto = new PartieAdversedto();

        dto.setNom(partieAdverse.getNom());
        dto.setAdresee(partieAdverse.getAdresee());
        dto.setContact(partieAdverse.getContact());

        // Map dossier references if they exist


        return dto;
    }}