package doc.raf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import doc.raf.entities.Equipe;

import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe,Long> {
    List<Equipe> findByPaysEquipe(String paysEquipe);

    @Query(value = "select nomEquipe from equipe e inner join match_equipe m on e.id_equipe = m.equipe_id where match_id=?;" ,nativeQuery = true)
    List<Equipe> getAllEquipeByIdMatch(Long match_id);
}
