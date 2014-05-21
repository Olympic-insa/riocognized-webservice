package fr.olympicinsa.riocognized.service;

import com.mysema.query.types.expr.BooleanExpression;
import fr.olympicinsa.riocognized.model.Athlete;
import fr.olympicinsa.riocognized.model.Image;
import fr.olympicinsa.riocognized.model.ImageFace;
import fr.olympicinsa.riocognized.model.ImagePub;
import fr.olympicinsa.riocognized.model.QAthlete;
import fr.olympicinsa.riocognized.model.QImage;
import fr.olympicinsa.riocognized.repository.*;
import java.util.Map;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;
    
    private static final int PAGE_SIZE = 15;

    public Page<Image> getImageFace(Integer pageNumber) {
        PageRequest request =
            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
        return imageRepository.findAll(request);
    }
}