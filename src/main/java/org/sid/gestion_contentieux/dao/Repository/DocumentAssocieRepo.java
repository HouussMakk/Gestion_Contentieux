package org.sid.gestion_contentieux.dao.Repository;

import org.sid.gestion_contentieux.dao.Entity.DocumentAssocie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentAssocieRepo extends JpaRepository<DocumentAssocie, Long> {
    Optional<DocumentAssocie> findByIdDocumentAssocie(int idDocumentAssocie);
}
