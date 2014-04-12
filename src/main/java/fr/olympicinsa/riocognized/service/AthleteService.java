
package fr.olympicinsa.riocognized.service;
 

import fr.olympicinsa.riocognized.model.Athlete;
import fr.olympicinsa.riocognized.model.Country;
import fr.olympicinsa.riocognized.model.Sport;
import fr.olympicinsa.riocognized.repository.AthleteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AthleteService {
    
    @Autowired
    private AthleteRepository athleteRepository;
    
    public List<Athlete> findAllOrderByName() {
        return athleteRepository.findAllOrderByName();
    }

    public Athlete findOne(long id) {
        return athleteRepository.findOne(id);
    }
    
    public List<Athlete> findByNameStartingWith(String name){
        return athleteRepository.findByNameStartingWith(name);
    }
    
    public List<Athlete> findBySportStartingWith(String sport){
        return athleteRepository.findBySportStartingWith(sport);
    }
    
     public List<Athlete> findByCountryStartingWith(String country){
        return athleteRepository.findByCountryStartingWith(country);
    }
    
     public List<Athlete> findByDescriptionStartingWith(String key, String value){
        return athleteRepository.findByDescriptionStartingWith(key, value);
    }
    
     public List<String> findBySport() {
        return athleteRepository.findBySport();
    }
    
     public List<Country> findByCountry(){
        return athleteRepository.findByCountry();
    }
     
    public void save(Athlete athlete){
        athleteRepository.save(athlete);
    }
    
    public void delete(Athlete athlete){
        athleteRepository.delete(athlete);
    }
}