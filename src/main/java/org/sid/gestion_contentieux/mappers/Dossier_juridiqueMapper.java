package org.sid.gestion_contentieux.mappers;

import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.dto.Dossier_juridiquedto;
import org.sid.gestion_contentieux.dto.MesureTribunaldto;
import org.sid.gestion_contentieux.dto.read.DossierJuridiquedListingDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Static mapper utility for converting between Dossier_juridique entities and DTOs
 */
public class Dossier_juridiqueMapper {
    public static DossierJuridiquedListingDto entityToListingDto(Dossier_juridique dossier) {
        if (dossier == null) {
            return null;
        }

        DossierJuridiquedListingDto dto = new DossierJuridiquedListingDto();

        dto.setReferenceDossier(dossier.getReferenceDossier());
        dto.setQualiteAgence(dossier.getQualiteagence());
        dto.setNatureLitige(dossier.getNatureLitige());
        dto.setObjetLitige(dossier.getObjetLitige());
        dto.setInstanceJudiciaire(dossier.getInstancejudiciaire());

        // Assuming these are IDs from related entities
        if (dossier.getPort() != null) {
            dto.setCodePort(dossier.getPort().getCodePort());
        }

        if (dossier.getPartieAdverse() != null) {
            dto.setPartieAdverse(dossier.getPartieAdverse().getNom());
        }

        if (dossier.getStadeLitige() != null) {
            dto.setStadeLitigeId(dossier.getStadeLitige().getIdstadelitige());
        }

        if (dossier.getAvocat() != null) {
            dto.setAvocat(dossier.getAvocat().getNom_cabinet());
        }

        // Map related mesures if they exist
        if (dossier.getMesures() != null) {
            List<MesureTribunaldto> mesureDtos = dossier.getMesures().stream()
                    .map(MesureTribunalMapper::entityToDto)
                    .collect(Collectors.toList());
//            dto.setMesures(mesureDtos);
        }

        return dto;
    }
    /**
     * Converts a Dossier_juridique entity to a Dossier_juridiquedto
     *
     * @param dossier the entity to convert
     * @return the converted DTO
     */
    public static Dossier_juridiquedto entityToDto(Dossier_juridique dossier) {
        if (dossier == null) {
            return null;
        }

        Dossier_juridiquedto dto = new Dossier_juridiquedto();

        dto.setReferenceDossier(dossier.getReferenceDossier());
        dto.setQualiteAgence(dossier.getQualiteagence());
        dto.setNatureLitige(dossier.getNatureLitige());
        dto.setObjetLitige(dossier.getObjetLitige());
        dto.setInstanceJudiciaire(dossier.getInstancejudiciaire());

        // Assuming these are IDs from related entities
        if (dossier.getPort() != null) {
            dto.setCodePort(dossier.getPort().getCodePort());
        }

        if (dossier.getPartieAdverse() != null) {
            dto.setPartieAdverseId(dossier.getPartieAdverse().getIdPartieAdverse());
        }

        if (dossier.getStadeLitige() != null) {
            dto.setStadeLitigeId(dossier.getStadeLitige().getIdstadelitige());
        }

        if (dossier.getAvocat() != null) {
            dto.setAvocatId(dossier.getAvocat().getIdAvocat());
        }

        // Map related mesures if they exist
        if (dossier.getMesures() != null) {
            List<MesureTribunaldto> mesureDtos = dossier.getMesures().stream()
                    .map(MesureTribunalMapper::entityToDto)
                    .collect(Collectors.toList());
//            dto.setMesures(mesureDtos);
        }

        return dto;
    }

    /**
     * Converts a list of entities to a list of DTOs
     */
    public static List<Dossier_juridiquedto> entitiesToDtos(List<Dossier_juridique> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(Dossier_juridiqueMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
