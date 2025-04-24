package org.sid.gestion_contentieux.dao.Repository;

import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.sid.gestion_contentieux.dao.Entity.MesureTribunal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MesureTribunalRepo extends JpaRepository<MesureTribunal, Long> {


    // Méthodes de base comme findById, delete, save sont héritées de JpaRepository

    // Méthodes de recherche personnalisées
    List<MesureTribunal> findByTypeMesure(String typeMesure);
    List<MesureTribunal> findByDateMesure(Date dateMesure);

    //List<MesureTribunal> findByDocumentAssocie_Id(Long idDocumentAssocie);


    // Méthode pour supprimer toutes les mesures d'un dossier
    static long deleteByDossierJuridique(Dossier_juridique dossierJuridique) {
        return 0;
    }

     //List<MesureTribunal> findByDossierJuridique(String reference_Dossier);
}
