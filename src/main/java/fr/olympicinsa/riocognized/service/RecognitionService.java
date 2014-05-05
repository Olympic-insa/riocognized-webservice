package fr.olympicinsa.riocognized.service;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import fr.olympicinsa.riocognized.exception.MyExceptionHandler;
import fr.olympicinsa.riocognized.exception.MyExceptionHandler.NoFaceDetectedException;
import fr.olympicinsa.riocognized.exception.MyExceptionHandler.NotRecognizedException;
import fr.olympicinsa.riocognized.exception.MyExceptionHandler.ProcessingError;
import static fr.olympicinsa.riocognized.facedetector.Riocognized.log;
import fr.olympicinsa.riocognized.facedetector.db.FaceDBReader;
import fr.olympicinsa.riocognized.facedetector.detection.FaceDetector;
import fr.olympicinsa.riocognized.facedetector.exception.FaceDBException;
import fr.olympicinsa.riocognized.facedetector.recognition.RioRecognizer;
import fr.olympicinsa.riocognized.facedetector.tools.ImageConvertor;
import static fr.olympicinsa.riocognized.facedetector.tools.ImageConvertor.bufferedImagetoMat;
import java.awt.image.BufferedImage;
import org.opencv.core.Mat;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@Service
public class RecognitionService {

    public static String HAAR = "/opt/openCV/haarcascade_frontalface_alt.xml";
    public static String DEST = "/var/www/opencv/result.jpg";
    public static String DB_PATH = "/opt/openCV/athleteDB";
    public static String RECO = "/opt/openCV/face.yml";

    public FaceDetector detectFace(BufferedImage imageBuffered) {
        Mat mat = bufferedImagetoMat(imageBuffered);
        FaceDetector detector = new FaceDetector();

        log.info("Start Detection");
        Mat crop = detector.cropFaceToMat(mat);
        log.info("Detected " + detector.getFacesDetected() + " athletes !");

        if (detector.getFacesDetected() < 0) {
            throw new NoFaceDetectedException();
        }
        return detector;
    }

    public RioRecognizer recognizeAthlete(BufferedImage imageBuffered) {
        try {
            int athlete;
            FaceDetector detector = new FaceDetector();
            FaceDBReader faceDB = new FaceDBReader(DB_PATH + "/faces.csv");
            Mat mat = bufferedImagetoMat(imageBuffered);

            log.info("Create Recognizer");
            RioRecognizer recognizor = new RioRecognizer(faceDB, RECO);
            //Store faces
            log.info("Read CSV and load images");
            recognizor.init();
            log.info("Train Recognizer");
            recognizor.train();
            log.info("Save Recognizer");
            recognizor.save();
            log.info("Start Detection");
            Mat crop = detector.cropFaceToMat(mat);
            log.info("Detected " + detector.getFacesDetected() + " athletes !");

            if (detector.getFacesDetected() < 0) {
                throw new NoFaceDetectedException();
            }

            IplImage face = ImageConvertor.matToIplImage(crop);
            //recognizor.changeRecognizer(1);
            athlete = recognizor.predictedLabel(face);
            if (athlete < 1) {
                throw new NotRecognizedException();
            }
            return recognizor;

        } catch (FaceDBException e) {
            throw new ProcessingError();
        }
    }
}
