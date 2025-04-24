package org.sid.gestion_contentieux.mappers;

import org.sid.gestion_contentieux.dao.Entity.Avocat;
import org.sid.gestion_contentieux.dto.AvocatDTO;

public class AvocatMapper {

    public static AvocatDTO toDto(Avocat avocat) {
        if (avocat == null) return null;

        AvocatDTO dto = new AvocatDTO();
        dto.setIdAvocat(avocat.getIdAvocat());
        dto.setNomCabinet(avocat.getNom_cabinet());
        dto.setContact(avocat.getContact());
        return dto;
    }

    public static Avocat toEntity(AvocatDTO dto) {
        if (dto == null) return null;

        Avocat avocat = new Avocat();
        avocat.setIdAvocat(Long.valueOf(dto.getIdAvocat()));
        avocat.setNom_cabinet(dto.getNomCabinet());
        avocat.setContact(dto.getContact());
        return avocat;
    }
}
