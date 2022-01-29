package doc.raf.entities;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Stade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStade;
    private String nomStade;
    private String villeStade;
    //@JsonIgnore
    @OneToMany(mappedBy = "stade",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Match> matches;

    public Stade(Long idStadeo, String nomStade, String villeStade) {
    }

    public Stade(Long idStade, String nomStade, String villeStade, List<Match> matches) {
        this.idStade = idStade;
        this.nomStade = nomStade;
        this.villeStade = villeStade;
        this.matches = matches;
    }

    @Override
    public String toString() {
        return String.format(nomStade);
    }

    public Stade() {
        super();
    }

    public Long getIdStade() {
        return idStade;
    }

    public void setIdStade(Long idStade) {
        this.idStade = idStade;
    }

    public String getNomStade() {
        return nomStade;
    }

    public void setNomStade(String nomStade) {
        this.nomStade = nomStade;
    }

    public String getVilleStade() {
        return villeStade;
    }

    public void setVilleStade(String villeStade) {
        this.villeStade = villeStade;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

}
