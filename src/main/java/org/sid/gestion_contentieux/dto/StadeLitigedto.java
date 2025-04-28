package org.sid.gestion_contentieux.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class StadeLitigedto {

    private int idstadelitige;
    private String stedelitige;
    private Date datChangementStatut;
    private List<String> dossiersReferences = new ArrayList<>();

    // Getters
    public int getIdstadelitige() {
        return idstadelitige;
    }

    public String getStedelitige() {
        return stedelitige;
    }

    public Date getDatChangementStatut() {
        return datChangementStatut;
    }

    public List<String> getDossiersReferences() {
        return dossiersReferences;
    }

    // Setters
    public void setIdstadelitige(int idstadelitige) {
        this.idstadelitige = idstadelitige;
    }

    public void setStedelitige(String stedelitige) {
        this.stedelitige = stedelitige;
    }

    public void setDatChangementStatut(Date datChangementStatut) {
        this.datChangementStatut = datChangementStatut;
    }

    public void setDossiersReferences(List<String> dossiersReferences) {
        this.dossiersReferences = dossiersReferences;
    }
}