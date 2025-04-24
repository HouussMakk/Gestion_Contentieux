package org.sid.gestion_contentieux.dao.Repository;

import org.sid.gestion_contentieux.dao.Entity.PartieAdverse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartieAdverseRepo extends JpaRepository<PartieAdverse, Long> {
}
