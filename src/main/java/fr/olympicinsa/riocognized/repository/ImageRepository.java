
package fr.olympicinsa.riocognized.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import fr.olympicinsa.riocognized.model.Image;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
    
    
}