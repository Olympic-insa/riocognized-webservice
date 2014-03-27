
package fr.olympicinsa.riocognized.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import fr.olympicinsa.riocognized.model.Athlete;
import fr.olympicinsa.riocognized.model.Sport;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    @Query("select u from Athlete u where lower(u.name) like ?1%")
    List<Athlete> findByNameStartingWith(String name);
    
    @Query("select u from Athlete u where lower(u.sport) like ?1%")
    List<Athlete> findBySportStartingWith(String sport);
    
    @Query("select u from Athlete u where lower(u.country) like ?1%")
    List<Athlete> findByCountryStartingWith(String sport);
    
    @Query("select u from Athlete u where lower(u.description[?1]) like ?2%")
    List<Athlete> findByDescriptionStartingWith(String key, String value);
    
    @Query("select u.sport from Athlete u group by u.sport")
    List<String> findBySport();
}