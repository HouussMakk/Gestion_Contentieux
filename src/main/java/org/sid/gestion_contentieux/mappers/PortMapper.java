package org.sid.gestion_contentieux.mappers;

import org.sid.gestion_contentieux.dao.Entity.Port;
import org.sid.gestion_contentieux.dto.Portdto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Static mapper utility for converting between Port entities and DTOs
 */
public class PortMapper {

    /**
     * Converts a Port entity to a Portdto
     *
     * @param port the entity to convert
     * @return the converted DTO
     */
    public static Portdto entityToDto(Port port) {
        if (port == null) {
            return null;
        }

        Portdto dto = new Portdto();

        dto.setCodePort(port.getCodePort());
        dto.setNomPort(port.getNomPort());


        return dto;
    }

    /**
     * Converts a list of entities to a list of DTOs
     */
    public static List<Portdto> entitiesToDtos(List<Port> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(PortMapper::entityToDto)
                .collect(Collectors.toList());
    }
}