package org.sid.gestion_contentieux.dao.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class MesureTribunal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id_Mesure ;
    public String typeMesure ;
    public Date dateMesure ;


    @ManyToOne
    @JoinColumn(name = "document_associe_id")
    private DocumentAssocie documentAssocie;

    @ManyToOne
    @JoinColumn(name = "reference_Dossier")
    private Dossier_juridique dossierJuridique;

    public Dossier_juridique getDossierjuridique() {
        return dossierJuridique;
    }

    public int getId_Mesure() {
        return id_Mesure;
    }

    public void setId_Mesure(int id_Mesure) {
        this.id_Mesure = id_Mesure;
    }

    public String gettypeMesure() {
        return typeMesure;
    }

    public void settypeMesure(String type_Mesure) {
        typeMesure = type_Mesure;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(Date dateMesure) {
        this.dateMesure = dateMesure;
    }

    public DocumentAssocie getDocumentAssocie() {
        return documentAssocie;
    }

    public void setDocumentAssocie(DocumentAssocie documentAssocie) {
        this.documentAssocie = documentAssocie;
    }

    public Dossier_juridique getDossierJuridique() {
        return dossierJuridique;
    }

    public void setDossierJuridique(Dossier_juridique dossierJuridique) {
        this.dossierJuridique = dossierJuridique;
    }








}

