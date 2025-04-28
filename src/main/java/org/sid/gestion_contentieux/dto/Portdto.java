package org.sid.gestion_contentieux.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Portdto {

    private String codePort;
    private String nomPort;

    // Getters
    public String getCodePort() {
        return codePort;
    }

    public String getNomPort() {
        return nomPort;
    }


    // Setters
    public void setCodePort(String codePort) {
        this.codePort = codePort;
    }

    public void setNomPort(String nomPort) {
        this.nomPort = nomPort;
    }

}