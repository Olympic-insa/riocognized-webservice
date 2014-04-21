
package fr.olympicinsa.riocognized.repository;
 
import fr.olympicinsa.riocognized.model.ImageFace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ImageFaceRepository extends JpaRepository<ImageFace, Long> , QueryDslPredicateExecutor<ImageFace>{
    
}