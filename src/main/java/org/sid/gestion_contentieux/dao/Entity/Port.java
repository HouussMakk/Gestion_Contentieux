package org.sid.gestion_contentieux.dao.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Port {
    @Id
    public String codePort ;
    public String NomPort ;
    @JsonIgnore
    @OneToMany(mappedBy = "port")
    private List<Dossier_juridique> dossiers = new ArrayList<>();


    public String getCodePort() {


        return this.codePort;
    }
}
