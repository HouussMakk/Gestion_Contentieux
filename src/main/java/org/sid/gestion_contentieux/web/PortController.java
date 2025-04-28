package org.sid.gestion_contentieux.web;

import org.sid.gestion_contentieux.dao.Entity.Port;
import org.sid.gestion_contentieux.dto.Portdto;
import org.sid.gestion_contentieux.mappers.PortMapper;
import org.sid.gestion_contentieux.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ports")
@CrossOrigin("*")
public class PortController {

    private final PortService portService;

    @Autowired
    public PortController(PortService portService) {
        this.portService = portService;
    }

    @GetMapping
    public ResponseEntity<List<Portdto>> getAllPorts() {
        List<Port> ports = portService.getAllPorts();
        List<Portdto> portDtos = ports.stream()
                .map(PortMapper::entityToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(portDtos, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Portdto> getPortByCode(@PathVariable String code) {
        Port port = portService.getPortByCode(code);
        Portdto portDto = PortMapper.entityToDto(port);
        return new ResponseEntity<>(portDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Portdto> createPort(@RequestBody Portdto portDto) {
        // Conversion simplifiée de DTO vers entité (sans relations)
        Port port = new Port();
        port.setCodePort(portDto.getCodePort());
        port.setNomPort(portDto.getNomPort());
        port.setDossiers(new ArrayList<>());
        // Créer le port
        Port createdPort = portService.createPort(port);

        // Conversion de l'entité vers DTO pour la réponse
        Portdto createdPortDto = PortMapper.entityToDto(createdPort);

        return new ResponseEntity<>(createdPortDto, HttpStatus.CREATED);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Portdto> updatePort(@PathVariable String code, @RequestBody Portdto portDto) {
        // Conversion simplifiée de DTO vers entité (sans relations)
        Port port = new Port();
        port.setNomPort(portDto.getNomPort());

        // Mettre à jour le port
        Port updatedPort = portService.updatePort(code, port);

        // Conversion de l'entité vers DTO pour la réponse
        Portdto updatedPortDto = PortMapper.entityToDto(updatedPort);

        return new ResponseEntity<>(updatedPortDto, HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deletePort(@PathVariable String code) {
        boolean deleted = portService.deletePort(code);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}