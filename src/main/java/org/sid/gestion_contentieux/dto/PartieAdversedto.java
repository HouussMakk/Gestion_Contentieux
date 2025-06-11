package org.sid.gestion_contentieux.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PartieAdversedto {

    private String nom;
    private String adresee;
    private String contact;


    // Getters


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