package org.sid.gestion_contentieux.mappers;

import org.sid.gestion_contentieux.dao.Entity.MesureTribunal;
import org.sid.gestion_contentieux.dao.Repository.DocumentAssocieRepo;
import org.sid.gestion_contentieux.dao.Repository.Dossier_juridiqueRepo;
import org.sid.gestion_contentieux.dto.MesureTribunaldto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MesureTribunalMapper {
    @Autowired
    private DocumentAssocieRepo documentAssocieRepository;

    @Autowired
    private Dossier_juridiqueRepo dossierJuridiqueRepository;

    /**
     * Converts a MesureTribunal entity to a MesureTribunaldto
     *
     * @param mesure the entity to convert
     * @return the converted DTO
     */
    public MesureTribunaldto entityToDto(MesureTribunal mesure) {
        if (mesure == null) {
            return null;
        }

        MesureTribunaldto dto = new MesureTribunaldto();

        dto.setIdMesure(mesure.getId_Mesure());
        dto.setTypeMesure(mesure.gettypeMesure());
        dto.setDateMesure(mesure.getDateMesure());

        // Set document ID if document exists
        if (mesure.getDocumentAssocie() != null) {
            dto.setDocumentAssocieId(mesure.getDocumentAssocie().getId());
        }

        // Set dossier reference if dossier exists
        if (mesure.getDossierJuridique() != null) {
            dto.setReferenceDossier(mesure.getDossierJuridique().getReferenceDossier());
        }

        return dto;
    }

    /**
     * Converts a MesureTribunaldto to a MesureTribunal entity
     *
     * @param dto the DTO to convert
     * @param existingEntity optional existing entity to update (can be null for new entity)
     * @return the converted entity
     */
    public MesureTribunal dtoToEntity(MesureTribunaldto dto, MesureTribunal existingEntity) {
        if (dto == null) {
            return null;
        }

        MesureTribunal entity = (existingEntity != null) ? existingEntity : new MesureTribunal();

        // Don't set ID for new entities (it's typically auto-generated)
        if (existingEntity != null || dto.getIdMesure() != 0) {
            entity.setIdMesure(dto.getIdMesure());
        }

        entity.setTypeMesure(dto.getTypeMesure());
        entity.setDateMesure(dto.getDateMesure());

        // Set related document if ID is provided
        if (dto.getDocumentAssocieId() != 0) {
            Optional<DocumentAssocie> document = documentAssocieRepository.findById(dto.getDocumentAssocieId());
            document.ifPresent(entity::setDocumentAssocie);
        }

        // Set related dossier if reference is provided
        if (dto.getReferenceDossier() != null && !dto.getReferenceDossier().isEmpty()) {
            Optional<Dossier_juridique> dossier = dossierJuridiqueRepository.findByReferenceDossier(dto.getReferenceDossier());
            dossier.ifPresent(entity::setDossierJuridique);
        }

        return entity;
    }

    /**
     * Convenience method for creating a new entity from a DTO
     */
    public MesureTribunal dtoToEntity(MesureTribunaldto dto) {
        return dtoToEntity(dto, null);
    }

    /**
     * Converts a list of entities to a list of DTOs
     */
    public List<MesureTribunaldto> entitiesToDtos(List<MesureTribunal> entities) {
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
    public List<MesureTribunal> dtosToEntities(List<MesureTribunaldto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
