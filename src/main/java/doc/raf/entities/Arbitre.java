package doc.raf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Data
//@JsonIgnoreProperties("match")
public class Arbitre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArbitre;
    private String nomArbitre;
    private String nationaliteArbitre;
    @JsonIgnore
    @OneToMany(mappedBy = "arbitre",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Match> matches;

    public Arbitre(Long idArbitre, String nomArbitre, String nationaliteArbitre, List<Match> matches) {
        this.idArbitre = idArbitre;
        this.nomArbitre = nomArbitre;
        this.nationaliteArbitre = nationaliteArbitre;
        this.matches = matches;
    }

    public Arbitre() {
        super();
    }

    @Override
    public String toString() {
        return String.format(nomArbitre);
    }

    public Long getIdArbitre() {
        return idArbitre;
    }

    public void setIdArbitre(Long idArbitre) {
        this.idArbitre = idArbitre;
    }

    public String getNomArbitre() {
        return nomArbitre;
    }

    public void setNomArbitre(String nomArbitre) {
        this.nomArbitre = nomArbitre;
    }

    public String getNationaliteArbitre() {
        return nationaliteArbitre;
    }

    public void setNationaliteArbitre(String nationaliteArbitre) {
        this.nationaliteArbitre = nationaliteArbitre;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
