package org.sid.gestion_contentieux.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class DocumentAssociedto {

    private int idDocumentAssocie;
    private String nomDocumentAssocie;
    private Date dateAjoute;
    private String description;
    private String documentAssocie;

    private List<MesureTribunaldto> mesures = new ArrayList<>();

    // Getters
    public int getIdDocumentAssocie() {
        return idDocumentAssocie;
    }

    public String getNomDocumentAssocie() {
        return nomDocumentAssocie;
    }

    public Date getDateAjoute() {
        return dateAjoute;
    }

    public String getDescription() {
        return description;
    }

    public String getDocumentAssocie() {
        return documentAssocie;
    }

    public List<MesureTribunaldto> getMesures() {
        return mesures;
    }

    // Setters
    public void setIdDocumentAssocie(int idDocumentAssocie) {
        this.idDocumentAssocie = idDocumentAssocie;
    }

    public void setNomDocumentAssocie(String nomDocumentAssocie) {
        this.nomDocumentAssocie = nomDocumentAssocie;
    }

    public void setDateAjoute(Date dateAjoute) {
        this.dateAjoute = dateAjoute;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDocumentAssocie(String documentAssocie) {
        this.documentAssocie = documentAssocie;
    }

    public void setMesures(List<MesureTribunaldto> mesures) {
        this.mesures = mesures;
    }
}