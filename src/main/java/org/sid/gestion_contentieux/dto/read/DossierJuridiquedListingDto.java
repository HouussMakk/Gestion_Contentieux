package org.sid.gestion_contentieux.dto.read;

public class DossierJuridiquedListingDto {
    private String referenceDossier;
    private String qualiteAgence;
    private String natureLitige;
    private String objetLitige;
    private String instanceJudiciaire;

    private String codePort;
    private String partieAdverse;
    private int stadeLitigeId;
    private String avocat;

    public String getReferenceDossier() {
        return referenceDossier;
    }

    public void setReferenceDossier(String referenceDossier) {
        this.referenceDossier = referenceDossier;
    }

    public String getQualiteAgence() {
        return qualiteAgence;
    }

    public void setQualiteAgence(String qualiteAgence) {
        this.qualiteAgence = qualiteAgence;
    }

    public String getNatureLitige() {
        return natureLitige;
    }

    public void setNatureLitige(String natureLitige) {
        this.natureLitige = natureLitige;
    }

    public String getObjetLitige() {
        return objetLitige;
    }

    public void setObjetLitige(String objetLitige) {
        this.objetLitige = objetLitige;
    }

    public String getInstanceJudiciaire() {
        return instanceJudiciaire;
    }

    public void setInstanceJudiciaire(String instanceJudiciaire) {
        this.instanceJudiciaire = instanceJudiciaire;
    }

    public String getCodePort() {
        return codePort;
    }

    public void setCodePort(String codePort) {
        this.codePort = codePort;
    }

    public String getPartieAdverse() {
        return partieAdverse;
    }

    public void setPartieAdverse(String partieAdverse) {
        this.partieAdverse = partieAdverse;
    }

    public int getStadeLitigeId() {
        return stadeLitigeId;
    }

    public void setStadeLitigeId(int stadeLitigeId) {
        this.stadeLitigeId = stadeLitigeId;
    }

    public String getAvocat() {
        return avocat;
    }

    public void setAvocat(String avocat) {
        this.avocat = avocat;
    }
}
