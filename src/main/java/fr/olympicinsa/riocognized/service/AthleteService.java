package fr.olympicinsa.riocognized.service;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import fr.olympicinsa.riocognized.exception.MyExceptionHandler.TooManyResultException;
import fr.olympicinsa.riocognized.model.Athlete;
import fr.olympicinsa.riocognized.model.Country;
import fr.olympicinsa.riocognized.model.QAthlete;
import fr.olympicinsa.riocognized.repository.AthleteRepository;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        Athlete result = athleteRepository.findOne(id);
        if (result == null) 
            throw new EmptyResultDataAccessException(1) ;
        return result;
    }

    public List<Athlete> findByNameStartingWith(String name) {
        List<Athlete> result = athleteRepository.findByNameStartingWith(name);
        if (result.isEmpty()) 
            throw new EmptyResultDataAccessException(1) ;
        return result;
    }

    public List<Athlete> findBySportStartingWith(String sport) {
        return athleteRepository.findBySportStartingWith(sport);
    }

    public List<Athlete> findByCountryStartingWith(String country) {
        return athleteRepository.findByCountryStartingWith(country);
    }

    public List<Athlete> findByDescriptionStartingWith(String key, String value) {
        return athleteRepository.findByDescriptionStartingWith(key, value);
    }

    public List<Athlete> findByDescription(Map m) {
        OrderSpecifier<String> sortOrder = QAthlete.athlete.name.asc();
		Predicate predicate = toPredicate(m);
                List<Athlete> result = (List<Athlete>) athleteRepository.findAll(predicate, sortOrder);
                if ( m.containsKey("racing") && ( m.get("racing").equals("false") | m.get("racing").equals("true") )) {
                    Boolean bool =  Boolean.valueOf(m.get("racing").toString());
                    Iterator newResult = result.iterator();
                    while (newResult.hasNext()) {
                        Athlete athlete = (Athlete) newResult.next();
                        if (athlete.isRacing() != bool)
                            newResult.remove();
                    }
                }
                if (result.isEmpty()) 
                    throw new EmptyResultDataAccessException(5) ;
                if (result.size() > 5) 
                    throw new TooManyResultException() ;
		return result;
	}
    
    private Predicate toPredicate(Map m) {
        QAthlete athlete = QAthlete.athlete;
        Map<String, String> queryMap = m;
        BooleanExpression result = null;

        try {
            for (Entry<String, String> entry : queryMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                
                BooleanExpression expression = null;
 
                switch (key) {
                    case "country" :
                         if (value != null && !"".equals(value)) {
                             expression = athlete.country.id.like("%"
                                 + value + "%");
                         }
                         break;
                    case "sport" :
                         if (value != null && !"".equals(value)) {
                             expression = athlete.sport.id.like("%"
                                 + value + "%");
                         }
                         break;

                   case "color" :
                   case "gender" :
                   case "other" :
                   case "bib" :
                   case "race_suit" :
                   case "hair" :
                   case "fit" :
                         if (value != null && !"".equals(value)) {
                             expression = athlete.description.get(key).like("%"
                                 + value + "%");
                         }
                         break;

                }
                if (expression != null) {
                    if (result != null) {
                        result = result.and(expression);
                    } else {
                        result = expression;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
      return result;
    }

    public List<String> findBySport() {
        return athleteRepository.findBySport();
    }

    public List<Country> findByCountry() {
        return athleteRepository.findByCountry();
    }

    public void save(Athlete athlete) {
        athleteRepository.save(athlete);
    }

    public void delete(Athlete athlete) {
        athleteRepository.delete(athlete);
    }
}
