
package fr.olympicinsa.riocognized.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import fr.olympicinsa.riocognized.model.Athlete;
import fr.olympicinsa.riocognized.model.Country;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AthleteRepository extends JpaRepository<Athlete, Long>, QueryDslPredicateExecutor<Athlete>{
    
    @Query("select u from Athlete u order by u.name asc")
    List<Athlete> findAllOrderByName();
    
    @Query("select u from Athlete u where lower(u.name) like ?1%")
    List<Athlete> findByNameStartingWith(String name);
    
    @Query("select u from Athlete u where lower(u.sport) like ?1%")
    List<Athlete> findBySportStartingWith(String sport);
    
    @Query("select u from Athlete u where lower(u.country.name) like ?1% or lower(u.country.id) like ?1%")
    List<Athlete> findByCountryStartingWith(String sport);
    
    @Query("select u from Athlete u where lower(u.description[?1]) like ?2%")
    List<Athlete> findByDescriptionStartingWith(String key, String value);
    
    @Query("select u.sport.id from Athlete u group by u.sport.id order by u.sport.id asc")
    List<String> findBySport();
    
    @Query("select u.country.id from Athlete u group by u.country.id")
    List<Country> findByCountry();
    
    @Query ("select e from Athlete e join e.description m where KEY(m) = KEY(?1) and VALUE(m) = VALUE(?1)")
    List<Athlete> findByDescription(Map m);
}