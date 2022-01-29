package doc.raf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import doc.raf.entities.Equipe;
import doc.raf.entities.Match;

import java.util.Date;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match,Long> {
    @Query(value = "select * from equipe e inner join match_equipe m on e.id_equipe = m.equipe_id where match_id=?;" ,nativeQuery = true)
    List<Equipe> findEquipeByIdMatch(Integer id_match);

    //@Query("SELECT b FROM Book b INNER JOIN b.categories c WHERE c IN (:categories)")


    List<Match> getAllByDateMatchEquals(Date dateMatch);

    void deleteMatchesByDateMatchBefore(Date date);
}
