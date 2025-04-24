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

public class Port {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public String codePort ;
    public String NomPort ;
    @OneToMany(mappedBy = "port")
    private List<Dossier_juridique> dossiers = new ArrayList<>();


}
