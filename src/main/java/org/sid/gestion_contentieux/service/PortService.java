package org.sid.gestion_contentieux.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.sid.gestion_contentieux.dao.Entity.Port;
import org.sid.gestion_contentieux.dao.Repository.PortRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortService implements PortManager {

    private final PortRepo portRepository;

    @Autowired
    public PortService(PortRepo portRepository) {
        this.portRepository = portRepository;
    }

    @Override
    public List<Port> getAllPorts() {
        return portRepository.findAll();
    }

    @Override
    public Port getPortByCode(String codePort) {
        // Puisqu'il n'y a pas de méthode findByCodePort dans le repository,
        // nous faisons un filtrage manuel après avoir récupéré tous les ports
        Optional<Port> port = portRepository.findAll().stream()
                .filter(p -> p.getCodePort().equals(codePort))
                .findFirst();

        return port.orElseThrow(() -> new ResourceNotFoundException("Port non trouvé avec le code: " + codePort));
    }

    @Override
    public Port createPort(Port port) {
        return portRepository.save(port);
    }

    @Override
    public Port updatePort(String codePort, Port portDetails) {
        Port port = getPortByCode(codePort);

        // Mettre à jour les propriétés
        port.setNomPort(portDetails.getNomPort());

        return portRepository.save(port);
    }

    @Override
    public boolean deletePort(String codePort) {
        Port port = getPortByCode(codePort);
        portRepository.delete(port);
        return true;
    }
}