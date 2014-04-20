package fr.olympicinsa.riocognized.service;

import com.mysema.query.types.expr.BooleanExpression;
import fr.olympicinsa.riocognized.model.Athlete;
import fr.olympicinsa.riocognized.model.Image;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageService {

    @Autowired
    private ImagePubRepository imageRepository;
    @PersistenceContext
    private EntityManager em;
    
    public ImagePub findOneRandom() {
        Criterion restriction;
        Session session = em.unwrap(Session.class);
        ImagePub result = null;  // will later contain a random entity
        Criteria crit = session.createCriteria(ImagePub.class);
        //crit.add(restriction);
        crit.setProjection(Projections.rowCount());
        int count = ((Number) crit.uniqueResult()).intValue();
        if (0 != count) {
            int index = new Random().nextInt(count);
            crit = session.createCriteria(ImagePub.class);
            //crit.add(restriction);
            result = (ImagePub) crit.setFirstResult(index).setMaxResults(1).uniqueResult();
        }
        if (result == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return result;
    }
}