package org.sid.gestion_contentieux.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PartieAdversedto {

    private Long idPartieadverse;
    private String nom;
    private String adresee;
    private String contact;


    // Getters
    public Long getIdPartieadverse() {
        return idPartieadverse;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresee() {
        return adresee;
    }

    public String getContact() {
        return contact;
    }


    // Setters
    public void setIdPartieadverse(Long idPartieadverse) {
        this.idPartieadverse = idPartieadverse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresee(String adresee) {
        this.adresee = adresee;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

 }
