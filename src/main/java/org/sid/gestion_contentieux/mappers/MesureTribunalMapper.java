package org.sid.gestion_contentieux.mappers;

import org.sid.gestion_contentieux.dao.Entity.MesureTribunal;
import org.sid.gestion_contentieux.dto.MesureTribunaldto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Static mapper utility for converting between MesureTribunal entities and DTOs
 */
public class MesureTribunalMapper {

    /**
     * Converts a MesureTribunal entity to a MesureTribunaldto
     *
     * param  the entity to convert
     * @return the converted DTO
     */
    /*public static MesureTribunaldto entityToDto(MesureTribunaldto mesure) {
        if (mesure == null) {
            return null;
        }

        MesureTribunaldto dto = new MesureTribunaldto();

        dto.setIdMesure(mesure.getId_Mesure());
        dto.setTypeMesure(mesure.getTypeMesure());
        dto.setDateMesure(mesure.getDateMesure());

        // Set document ID if document exists
        if (mesure.getDocumentAssocie() != null) {
            dto.setDocumentAssocieId(mesure.getDocumentAssocie().getIdDocumentAssocie());
        }

        // Set dossier reference if dossier exists
        if (mesure.getDossierJuridique() != null) {
            dto.setReferenceDossier(mesure.getDossierJuridique().getReference_Dossier());
        }

        return dto;
    }

    /**
     * Converts a list of entities to a list of DTOs
     */
    public static List<MesureTribunaldto> entitiesToDtos(List<MesureTribunal> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(MesureTribunalMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public static MesureTribunaldto entityToDto(MesureTribunal mesure) {
        MesureTribunaldto mesureTribunaldto = new MesureTribunaldto();
        if (mesure != null) {
            mesureTribunaldto.setIdMesure(mesure.getId_Mesure());
            mesureTribunaldto.setTypeMesure(mesure.gettypeMesure());
            mesureTribunaldto.setDateMesure(mesure.getDateMesure());
            //TODO referecne
        }
        return mesureTribunaldto;
    }

    /**
     * Converts a MesureTribunaldto to a MesureTribunal entity
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    public static MesureTribunal dtoToEntity(MesureTribunaldto dto) {
        if (dto == null) {
            return null;
        }

        MesureTribunal entity = new MesureTribunal();

        // Ne pas définir l'ID pour les nouvelles entités (auto-généré)
        if (dto.getIdMesure() != 0) {
            entity.setId_Mesure(dto.getIdMesure());
        }

        entity.settypeMesure(dto.getTypeMesure());
        entity.setDateMesure(dto.getDateMesure());
//        entity.setDossierJuridique(dto.getDossierJuridique());
//        entity.setDocumentAssocie(dto.getDocumentAssocie());
        // Remarque: Les relations (document, dossier) doivent être gérées séparément
        // car elles nécessitent des accès aux repositories

        return entity;
    }

}