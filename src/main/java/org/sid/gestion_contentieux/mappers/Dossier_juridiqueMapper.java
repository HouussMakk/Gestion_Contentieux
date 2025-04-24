package org.sid.gestion_contentieux.mappers;

import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.dao.Entity.MesureTribunal;
import org.sid.gestion_contentieux.dto.Dossier_juridiquedto;
import org.sid.gestion_contentieux.dto.MesureTribunaldto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Dossier_juridiqueMapper {
    @Autowired
    private MesureTribunalMapper mesureTribunalMapper;

    /**
     * Converts a Dossier_juridique entity to a Dossier_juridiquedto
     *
     * @param dossier the entity to convert
     * @return the converted DTO
     */
    public Dossier_juridiquedto entityToDto(Dossier_juridique dossier) {
        if (dossier == null) {
            return null;
        }

        Dossier_juridiquedto dto = new Dossier_juridiquedto();

        dto.setReferenceDossier(dossier.getReference_Dossier());
        dto.setQualiteAgence(dossier.getQualiteagence());
        dto.setNatureLitige(dossier.getNatureLitige());
        dto.setObjetLitige(dossier.getObjetLitige());
        dto.setInstanceJudiciaire(dossier.getInstancejudiciaire());

        // Assuming these are IDs from related entities
        if (dossier.getPort() != null) {
            dto.setPortId(dossier.getPort().getI());
        }

        if (dossier.getPartieAdverse() != null) {
            dto.setPartieAdverseId(dossier.getPartieAdverse().getIdPartieAdverse());
        }

        if (dossier.getStadeLitige() != null) {
            dto.setStadeLitigeId(dossier.getStadeLitige().getId());
        }

        if (dossier.getAvocat() != null) {
            dto.setAvocatId(dossier.getAvocat().getId());
        }

        // Map related mesures if they exist
        if (dossier.getMesures() != null) {
            List<MesureTribunaldto> mesureDtos = dossier.getMesures().stream()
                    .map(mesureTribunalMapper::entityToDto)
                    .collect(Collectors.toList());
            dto.setMesures(mesureDtos);
        }

        return dto;
    }

    /**
     * Converts a Dossier_juridiquedto to a Dossier_juridique entity
     *
     * @param dto the DTO to convert
     * @param existingEntity optional existing entity to update (can be null for new entity)
     * @return the converted entity
     */
    public Dossier_juridique dtoToEntity(Dossier_juridiquedto dto, Dossier_juridique existingEntity) {
        if (dto == null) {
            return null;
        }

        Dossier_juridique entity = (existingEntity != null) ? existingEntity : new Dossier_juridique();

        entity.setReference_Dossier(dto.getReferenceDossier());
        entity.setQualiteagence(dto.getQualiteAgence());
        entity.setNatureLitige(dto.getNatureLitige());
        entity.setObjetLitige(dto.getObjetLitige());
        entity.setInstancejudiciaire(dto.getInstanceJudiciaire());

        // Note: Related entities (Port, PartieAdverse, StadeLitige, Avocat)
        // should be set by the service layer, as we only have IDs here

        // For mesures, if provided in the DTO, map them
        if (dto.getMesures() != null) {
            List<MesureTribunal> mesures = dto.getMesures().stream()
                    .map(mesureDto -> mesureTribunalMapper.dtoToEntity(mesureDto, null))
                    .collect(Collectors.toList());
            entity.setMesures(mesures);
        }

        return entity;
    }

    /**
     * Convenience method for creating a new entity from a DTO
     */
    public Dossier_juridique dtoToEntity(Dossier_juridiquedto dto) {
        return dtoToEntity(dto, null);
    }

    /**
     * Converts a list of entities to a list of DTOs
     */
    public List<Dossier_juridiquedto> entitiesToDtos(List<Dossier_juridique> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of DTOs to a list of entities
     */
    public List<Dossier_juridique> dtosToEntities(List<Dossier_juridiquedto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
