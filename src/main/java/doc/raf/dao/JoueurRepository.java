package doc.raf.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import doc.raf.entities.Joueur;

import java.util.List;

public interface JoueurRepository extends JpaRepository<Joueur,Long> {
    @Query(value = "SELECT * FROM joueur j inner join equipe e on j.equipe_id = e.id_equipe WHERE nom_equipe = ?" ,nativeQuery = true)
    List<Joueur> getJoueurByEquipes(String nom_equipe);

    @Query(value = "SELECT * FROM joueur j INNER JOIN equipe e ON j.equipe_id = e.id_equipe WHERE poste_joueur=? AND nom_equipe = ?" ,nativeQuery = true)
    List<Joueur> getJoueurByEquipesAndPoste(String poste_joueur,String nom_equipe);

    public Page<Joueur> findByNomJoueurContains(String mc, Pageable pageable);

}
