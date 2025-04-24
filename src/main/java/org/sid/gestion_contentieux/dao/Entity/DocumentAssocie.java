package org.sid.gestion_contentieux.dao.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity


public class DocumentAssocie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idDocumentAssocie;

    private String nomDocumentAssocie;
    private Date dateAjoute;
    private String description;

    private  String documentAssocie;

    @OneToMany(mappedBy = "documentAssocie")
    private List<MesureTribunal> mesures = new ArrayList<>();

    // Constructor(s)
    public DocumentAssocie() {
    }

    public DocumentAssocie(int idDocumentAssocie, String nomDocumentAssocie, Date dateAjoute, String description, String documentAssocie) {
        this.idDocumentAssocie = idDocumentAssocie;
        this.nomDocumentAssocie = nomDocumentAssocie;
        this.dateAjoute = dateAjoute;
        this.description = description;
        this.documentAssocie = documentAssocie;
    }

    // Getters and Setters

    public int getIdDocumentAssocie() {
        return idDocumentAssocie;
    }

    public void setIdDocumentAssocie(int idDocumentAssocie) {
        this.idDocumentAssocie = idDocumentAssocie;
    }

    public String getNomDocumentAssocie() {
        return nomDocumentAssocie;
    }

    public void setNomDocumentAssocie(String nomDocumentAssocie) {
        this.nomDocumentAssocie = nomDocumentAssocie;
    }

    public Date getDateAjoute() {
        return dateAjoute;
    }

    public void setDateAjoute(Date dateAjoute) {
        this.dateAjoute = dateAjoute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentAssocie() {
        return documentAssocie;
    }

    public void setDocumentAssocie(String documentAssocie) {
        this.documentAssocie = documentAssocie;
    }

    public List<MesureTribunal> getMesures() {
        return mesures;
    }

    public void setMesures(List<MesureTribunal> mesures) {
        this.mesures = mesures;
    }
}
