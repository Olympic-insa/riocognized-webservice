package fr.olympicinsa.riocognized.service;
 
import fr.olympicinsa.riocognized.model.Country;
import fr.olympicinsa.riocognized.repository.CountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryService {
    
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> findById(List<Country> id) {
        return countryRepository.findById(id);
    };
}