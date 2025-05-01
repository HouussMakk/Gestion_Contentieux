package org.sid.gestion_contentieux.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Dossier_juridiquedto {
    private String referenceDossier;
    private String qualiteAgence;
    private String natureLitige;
    private String objetLitige;
    private String instanceJudiciaire;

    private String codePort;
    private Long partieAdverseId;
    private int stadeLitigeId;
    private int avocatId;

//    private List<MesureTribunaldto> mesures;

    // Getters
    public String getReferenceDossier() {
        return referenceDossier;
    }

    public String getQualiteAgence() {
        return qualiteAgence;
    }

    public String getNatureLitige() {
        return natureLitige;
    }

    public String getObjetLitige() {
        return objetLitige;
    }

    public String getInstanceJudiciaire() {
        return instanceJudiciaire;
    }

    public String getCodePort() {
        return codePort;
    }

    public Long getPartieAdverseId() {
        return partieAdverseId;
    }

    public int getStadeLitigeId() {
        return stadeLitigeId;
    }

    public int getAvocatId() {
        return avocatId;
    }

//    public List<MesureTribunaldto> getMesures() {
//        return mesures;
//    }

    // Setters
    public void setReferenceDossier(String referenceDossier) {
        this.referenceDossier = referenceDossier;
    }

    public void setQualiteAgence(String qualiteAgence) {
        this.qualiteAgence = qualiteAgence;
    }

    public void setNatureLitige(String natureLitige) {
        this.natureLitige = natureLitige;
    }

    public void setObjetLitige(String objetLitige) {
        this.objetLitige = objetLitige;
    }

    public void setInstanceJudiciaire(String instanceJudiciaire) {
        this.instanceJudiciaire = instanceJudiciaire;
    }

    public void setCodePort(String codePort) {
        this.codePort = codePort;
    }

    public void setPartieAdverseId(Long partieAdverseId) {
        this.partieAdverseId = partieAdverseId;
    }

    public void setStadeLitigeId(int stadeLitigeId) {
        this.stadeLitigeId = stadeLitigeId;
    }

    public void setAvocatId(int avocatId) {
        this.avocatId = avocatId;
    }

//    public void setMesures(List<MesureTribunaldto> mesures) {
//        this.mesures = mesures;
//    }


}
