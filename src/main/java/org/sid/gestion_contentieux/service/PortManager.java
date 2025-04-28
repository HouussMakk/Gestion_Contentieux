package org.sid.gestion_contentieux.service;

import org.sid.gestion_contentieux.dao.Entity.Port;
import java.util.List;

public interface PortManager {
    /**
     * Récupère tous les ports
     * @return liste des ports
     */
    List<Port> getAllPorts();

    /**
     * Récupère un port par son code
     * @param codePort le code du port
     * @return le port correspondant
     */
    Port getPortByCode(String codePort);

    /**
     * Crée un nouveau port
     * @param port le port à créer
     * @return le port créé
     */
    Port createPort(Port port);

    /**
     * Met à jour un port existant
     * @param codePort le code du port à mettre à jour
     * @param portDetails les nouvelles données du port
     * @return le port mis à jour
     */
    Port updatePort(String codePort, Port portDetails);

    /**
     * Supprime un port
     * @param codePort le code du port à supprimer
     * @return true si la suppression a réussi
     */
    boolean deletePort(String codePort);
}