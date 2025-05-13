    package org.sid.gestion_contentieux.dao.Entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.*;

    import java.util.List;

    @Entity

    @NoArgsConstructor
    @AllArgsConstructor


    public class Dossier_juridique {
        @Id
        public String referenceDossier;
        public String qualiteagence ;
        public String natureLitige ;
        public String objetLitige ;
        public String instancejudiciaire ;
        @ManyToOne
        @JoinColumn(name = "codePort", referencedColumnName = "codePort")
        public Port port;

        @ManyToOne
        @JoinColumn(name = "idPartieAdverse")
        public PartieAdverse partieAdverse;

        @ManyToOne
        @JoinColumn(name = "idstadelitige")
        public StadeLitige stadeLitige;


        @ManyToOne
        @JoinColumn(name = "idavocat")
        public Avocat avocat;



        @OneToMany(mappedBy = "dossierJuridique")
        @JsonIgnore
        private List<MesureTribunal> mesures ;


        public String getReferenceDossier() {
            return referenceDossier;
        }

        public void setReferenceDossier(String reference_Dossier) {
            this.referenceDossier = reference_Dossier;
        }

        public String getQualiteagence() {
            return qualiteagence;
        }

        public void setQualiteagence(String qualiteagence) {
            this.qualiteagence = qualiteagence;
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

        public String getInstancejudiciaire() {
            return instancejudiciaire;
        }

        public void setInstancejudiciaire(String instancejudiciaire) {
            this.instancejudiciaire = instancejudiciaire;
        }

        public Port getPort() {
            return port;
        }

        public void setPort(Port port) {
            this.port = port;
        }

        public PartieAdverse getPartieAdverse() {
            return partieAdverse;
        }

        public void setPartieAdverse(PartieAdverse partieAdverse) {
            this.partieAdverse = partieAdverse;
        }

        public StadeLitige getStadeLitige() {
            return stadeLitige;
        }

        public void setStadeLitige(StadeLitige stadeLitige) {
            this.stadeLitige = stadeLitige;
        }

        public Avocat getAvocat() {
            return avocat;
        }

        public void setAvocat(Avocat avocat) {
            this.avocat = avocat;
        }

        public List<MesureTribunal> getMesures() {
            return mesures;
        }

        public void setMesures(List<MesureTribunal> mesures) {
            this.mesures = mesures;
        }



    }
