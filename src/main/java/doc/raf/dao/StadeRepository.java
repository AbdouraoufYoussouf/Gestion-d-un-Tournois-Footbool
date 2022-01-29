package doc.raf.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import doc.raf.entities.Stade;

public interface StadeRepository extends JpaRepository<Stade,Long> {
    
   @Query(value = "SELECT * FROM stade s inner join match_table m on s.id_stade = m.stade_id WHERE id_match = ?" , nativeQuery = true)
    Stade getByMatchId(Long idMatch);
}
