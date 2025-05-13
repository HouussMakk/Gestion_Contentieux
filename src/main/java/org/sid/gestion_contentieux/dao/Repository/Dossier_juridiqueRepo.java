package org.sid.gestion_contentieux.dao.Repository;

import org.sid.gestion_contentieux.dao.Entity.Dossier_juridique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Dossier_juridiqueRepo extends JpaRepository<Dossier_juridique, Long> {
    List<Dossier_juridique> findByNatureLitige(String natureLitige);
    List<Dossier_juridique> findByInstancejudiciaire(String instanceJudiciaire);

     Optional<Dossier_juridique> findByReferenceDossier(String referenceDossier);
    //Dossier_juridique getDossierByReference(String referenceDossier);

    //Optional<Dossier_juridique> findByReference_Dossier(String reference_Dossier);
}
