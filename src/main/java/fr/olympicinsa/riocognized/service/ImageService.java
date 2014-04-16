
package fr.olympicinsa.riocognized.service;
 
import fr.olympicinsa.riocognized.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
}