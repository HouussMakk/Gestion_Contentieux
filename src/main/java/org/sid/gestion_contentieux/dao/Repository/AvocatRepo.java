package org.sid.gestion_contentieux.dao.Repository;

import org.sid.gestion_contentieux.dao.Entity.Avocat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvocatRepo extends JpaRepository<Avocat, Long> {
}
