package org.sid.gestion_contentieux.dao.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class StadeLitige {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int idstadelitige ;
    public  String stedelitige ;
    public Date DatChangementStatut ;
    @OneToMany(mappedBy = "stadeLitige")
    private List<Dossier_juridique> dossiers;

}
