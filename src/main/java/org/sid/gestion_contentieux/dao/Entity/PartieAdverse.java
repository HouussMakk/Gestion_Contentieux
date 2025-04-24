package org.sid.gestion_contentieux.dao.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PartieAdverse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String idPartieadverse ;
    public  String nom ;
    public  String adresee ;
    public  String contact ;
    @OneToMany(mappedBy = "partieAdverse")
    private List<Dossier_juridique> dossiers = new ArrayList<>();
    // getter
    public int getIdPartieAdverse() {
        return idPartieadverse;
    }

    // setter
    public void setIdPartieAdverse(Long idPartieAdverse) {
        this.idPartieadverse = idPartieadverse;
    }
}
