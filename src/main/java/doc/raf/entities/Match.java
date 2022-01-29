package doc.raf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "match_table")
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatch ;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dateMatch;
    private String heureMatch;

   @ToString.Exclude
   @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "stade_id")
    private Stade stade;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "arbitre_id",referencedColumnName = "idArbitre")
    private Arbitre arbitre;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "match_equipe",
            joinColumns = { @JoinColumn(name = "match_id", referencedColumnName = "idMatch") },
            inverseJoinColumns = { @JoinColumn(name = "equipe_id", referencedColumnName = "idEquipe") })
    private List<Equipe> equipes = new java.util.ArrayList<>();

    public Match(Long idMatch, Date dateMatch, String heureMatch, Stade stade, Arbitre arbitre, List<Equipe> equipes) {
        this.idMatch = idMatch;
        this.dateMatch = dateMatch;
        this.heureMatch = heureMatch;
        this.stade = stade;
        this.arbitre = arbitre;
        this.equipes = equipes;
    }

    public Match() {
        super();
    }

    @Override
    public String toString() {
        return String.format(equipes + " le " + dateMatch +" à "+ heureMatch + " au stade " + stade +", arbitre : "+ arbitre);
    }

    public Long getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    public String getHeureMatch() {
        return heureMatch;
    }

    public void setHeureMatch(String heureMatch) {
        this.heureMatch = heureMatch;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public Arbitre getArbitre() {
        return arbitre;
    }

    public void setArbitre(Arbitre arbitre) {
        this.arbitre = arbitre;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    

}
