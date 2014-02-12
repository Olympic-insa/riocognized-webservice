
package fr.olympicinsa.riocognized.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import fr.olympicinsa.riocognized.model.Athlete;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}