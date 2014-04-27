
package fr.olympicinsa.riocognized.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import fr.olympicinsa.riocognized.model.Athlete;
import fr.olympicinsa.riocognized.model.Sport;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SportRepository extends JpaRepository<Sport, Long>, QueryDslPredicateExecutor<Sport>{
    
    @Query("select u from Sport u order by u.id asc")
    List<Athlete> findAllOrderByName();
    
    @Query("SELECT u FROM Sport u WHERE u.id = ?1")
    Sport findOne(String id);

}