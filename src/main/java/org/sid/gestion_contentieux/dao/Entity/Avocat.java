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

public class Avocat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int idavocat ;
    public String Nom_cabinet ;
    public String Contact ;
    @OneToMany(mappedBy = "avocat")
    private List<Dossier_juridique> dossiers = new ArrayList<>();
    public int getIdAvocat() { return idavocat; }
    public void setIdAvocat(Long idAvocat) { this.idavocat = idavocat; }


}
