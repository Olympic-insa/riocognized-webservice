
package fr.olympicinsa.riocognized.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import fr.olympicinsa.riocognized.model.ImagePub;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ImagePubRepository extends JpaRepository<ImagePub, Long> , QueryDslPredicateExecutor<ImagePub>{
    
}