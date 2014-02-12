
package fr.olympicinsa.riocognized.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import fr.olympicinsa.riocognized.model.Athlete;


public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}