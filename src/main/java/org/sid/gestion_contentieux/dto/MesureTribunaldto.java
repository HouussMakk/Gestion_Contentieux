package org.sid.gestion_contentieux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data@NoArgsConstructor@AllArgsConstructor
public class MesureTribunaldto {

    private int idMesure;
    private String typeMesure;
    private Date dateMesure;

    private int documentAssocieId;
    private String referenceDossier;
    // Getters
    public int getIdMesure() {
        return idMesure;
    }

    public String getTypeMesure() {
        return typeMesure;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    public int getDocumentAssocieId() {
        return documentAssocieId;
    }

    public String getReferenceDossier() {
        return referenceDossier;
    }

    // Setters
    public void setIdMesure(int idMesure) {
        this.idMesure = idMesure;
    }

    public void setTypeMesure(String typeMesure) {
        this.typeMesure = typeMesure;
    }

    public void setDateMesure(Date dateMesure) {
        this.dateMesure = dateMesure;
    }

    public void setDocumentAssocieId(int documentAssocieId) {
        this.documentAssocieId = documentAssocieId;
    }

    public void setReferenceDossier(String referenceDossier) {
        this.referenceDossier = referenceDossier;
    }

    /**
     * Crée et retourne un objet DocumentAssociedto avec l'ID du document associé
     * Note: Cette méthode ne récupère que l'ID du document, pas le document complet
     * Pour un document complet, il faut utiliser le service approprié
     *
     * @return DocumentAssociedto contenant l'ID du document associé
     */


    /**
     * Crée et retourne un objet Dossier_juridique avec la référence du dossier
     * Note: Cette méthode ne récupère que la référence du dossier, pas le dossier complet
     * Pour un dossier complet, il faut utiliser le service approprié
     *
     * @return Dossier_juridique contenant la référence du dossier
     */



}
