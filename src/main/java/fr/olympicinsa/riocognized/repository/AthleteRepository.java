
package fr.olympicinsa.riocognized.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import fr.olympicinsa.riocognized.model.Athlete;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    @Query("select u from Athlete u where lower(u.name) like ?1%")
    List<Athlete> findByNameStartingWith(String name);
    
    @Query("select u from Athlete u where lower(u.sport) like ?1%")
    List<Athlete> findBySportStartingWith(String sport);
    
    @Query("select u from Athlete u where lower(u.country) like ?1%")
    List<Athlete> findByCountryStartingWith(String sport);
    
    Athlete find(long id);
}