package fr.olympicinsa.riocognized;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.olympicinsa.riocognized.exception.MyExceptionHandler;
import fr.olympicinsa.riocognized.facedetector.detection.FaceDetector;
import fr.olympicinsa.riocognized.facedetector.tools.ImageConvertor;
import fr.olympicinsa.riocognized.facedetector.db.FaceDBReader;
import fr.olympicinsa.riocognized.facedetector.exception.FaceDBException;
import fr.olympicinsa.riocognized.facedetector.recognition.RioRecognizer;
import static fr.olympicinsa.riocognized.facedetector.tools.ImageConvertor.bufferedImagetoMat;

import fr.olympicinsa.riocognized.model.*;
import fr.olympicinsa.riocognized.repository.*;
import fr.olympicinsa.riocognized.service.AthleteService;
import fr.olympicinsa.riocognized.service.RecognitionService;
import java.awt.image.BufferedImage;
import java.beans.PropertyEditorSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.opencv.core.Mat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller
@ComponentScan("fr.olympicinsa.riocognized.repository")
@RequestMapping("/recognition")
public class RecognitionController extends MyExceptionHandler {

    @Autowired
    private AthleteService athleteService;
    @Autowired
    private ImageFaceRepository imageFaceRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    RecognitionService recognitionService;

    public static String HAAR = "/opt/openCV/haarcascade_frontalface_alt.xml";
    public static String DEST = "/var/www/opencv/result.jpg";
    public static String DB_PATH = "/opt/openCV/athleteDB";
    public static String RECO = "/opt/openCV/face.yml";

    private static final Logger logger = Logger.getLogger(RecognitionController.class.getName());

    @RequestMapping("")
    public String index(Map<String, Object> map) {
        try {
            map.put("image", new ImageFace());
            map.put("imageList", imageFaceRepository.findAll());
            map.put("athleteList", athleteService.findAllOrderByName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "imagedb";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Validated
        @ModelAttribute("image") ImageFace image,
        @RequestParam("file") MultipartFile file) {

        System.out.println("Name:" + image.getName());
        System.out.println("Desc:" + image.getDescription());
        System.out.println("File:" + file.getName());
        System.out.println("ContentType:" + file.getContentType());

        try {
            byte[] blob = IOUtils.toByteArray(file.getInputStream());

            image.setFilename(file.getOriginalFilename());
            image.setContent(blob);
            image.setContentType(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            imageFaceRepository.save(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/recognition";
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        File dbDir = new File(DB_PATH);
        if (!dbDir.exists() && !dbDir.isDirectory()) {
            dbDir.mkdirs();
        }
        try {
            FaceDBReader faces = new FaceDBReader(DB_PATH + "/faces.csv");
            FaceDetector facedetector = new FaceDetector();
            List<ImageFace> imageList = imageFaceRepository.findAll();
            int i = 0;
            long id = 0;
            int y = 0;
            for (ImageFace image : imageList) {
                if (y < 15) {
                    i = (id == image.getAthlete().getId()) ? i + 1 : 0;
                    id = image.getAthlete().getId();
                    File dir = new File(DB_PATH + "/" + id);
                    if (!dir.exists() && !dir.isDirectory()) {
                        dir.mkdirs();
                    }
                    if (image.getFaceContent() == null) {
                        try {
                            Mat mat = ImageConvertor.byteArrayToMat(image.getContent());
                            BufferedImage crop = facedetector.cropFaceToBufferedImage(mat);
                            if (crop != null) {
                                String file = dir + "/face" + image.getId().toString() + ".jpg";
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                ImageIO.write(crop, "jpg", baos);
                                baos.flush();
                                byte[] blob = baos.toByteArray();
                                image.setFaceContent(blob);
                                image.setFaceUrl(file);
                                imageFaceRepository.save(image);
                                baos.close();
                                try {
                                    File outputfile = new File(file);
                                    ImageIO.write(crop, "jpg", outputfile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    System.err.println("Cant't write image cropped");
                                }

                                faces.addFace(new String[]{file, String.valueOf(id)});
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        y++;
                    }
                }
            }
            faces.writeFile();
        } catch (FaceDBException e) {
            e.printStackTrace();
        }

        return "redirect:/recognition";
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clear() {
        List<ImageFace> imageList = imageFaceRepository.findAll();
        for (ImageFace image : imageList) {
            if (image.getFaceContent() != null) {
                image.setFaceContent(null);
                image.setFaceUrl(null);
                imageFaceRepository.save(image);
            }
            File dir = new File(DB_PATH);
            if (dir.exists() && dir.isDirectory()) {
                dir.delete();
            }
        }

        return "redirect:/recognition";
    }

    @InitBinder
    protected
        void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Athlete.class, "athlete", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Athlete ath = athleteService.findOne(Long.parseLong(text));
                setValue(ath);
            }
        }
        );
    }

    @RequestMapping("/download/{imageId}")
    public String download(@PathVariable("imageId") Long imageId, HttpServletResponse response) {

        ImageFace doc = imageFaceRepository.findOne(imageId);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(doc.getContent());
            response.setHeader("Content-Disposition", "inline;filename=\"" + doc.getFilename() + "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(doc.getContentType());
            IOUtils.copy(bis, out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/downloadFace/{imageId}")
    public String downloadFace(@PathVariable("imageId") Long imageId, HttpServletResponse response) {

        ImageFace doc = imageFaceRepository.findOne(imageId);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(doc.getFaceContent());
            response.setHeader("Content-Disposition", "inline;filename=\"" + doc.getFilename() + "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(doc.getContentType());
            IOUtils.copy(bis, out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/remove/{imageId}")
    public String remove(@PathVariable("imageId") Long imageId) {
        ImageFace image = imageFaceRepository.findOne(imageId);
        try {
            FaceDBReader faces = new FaceDBReader(DB_PATH + "/faces.csv");
            ArrayList<String[]> list = (ArrayList) faces.readFile(DB_PATH + "/faces.csv");
            String id = image.getAthlete().getId().toString();
            String file = image.getFaceUrl();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String[] i = (String[]) it.next();
                if (i[0].equals(file)) {
                    it.remove();
                }
            }
            list.trimToSize();
            logger.info(list.toString());
            faces.setList(list);
            faces.writeFile();
        } catch (FaceDBException e) {
            e.printStackTrace();
        }
        imageFaceRepository.delete(imageId);
        return "redirect:/recognition";
    }

    @RequestMapping("/removeFace/{imageId}")
    public String removeFace(@PathVariable("imageId") Long imageId) {
        ImageFace image = imageFaceRepository.findOne(imageId);
        try {
            FaceDBReader faces = new FaceDBReader(DB_PATH + "/faces.csv");
            ArrayList<String[]> list = (ArrayList) faces.readFile(DB_PATH + "/faces.csv");
            String id = image.getAthlete().getId().toString();
            String file = image.getFaceUrl();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String[] i = (String[]) it.next();
                if (i[0].equals(file)) {
                    it.remove();
                }
            }
            list.trimToSize();
            logger.info(list.toString());
            faces.setList(list);
            faces.writeFile();
        } catch (FaceDBException e) {
            e.printStackTrace();
        }
        image.setFaceContent(null);
        image.setFaceUrl(null);
        imageFaceRepository.save(image);
        return "redirect:/recognition";
    }


    /* API POST Method*/
    @RequestMapping(value = "/api/detect", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String detectFacesURL(@RequestParam("url") String url) {

        FaceDetector detector = new FaceDetector();
        BufferedImage imageBuffered;
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        Mat newMat;

        try {
            URL u = new URL(url);
            int contentLength = u.openConnection().getContentLength();
            imageBuffered = ImageIO.read(u);
            Mat mat = bufferedImagetoMat(imageBuffered);

            int detected = detector.detectFaces(mat, DEST);

            JSONArray faceArray = new JSONArray();
            JSONObject faceJSON = new JSONObject();
            faceJSON.put("image", url);
            faceJSON.put("classifier", HAAR);
            faceJSON.put("detected", detected);
            faceJSON.put("result", "http://lynxlabs.fr.nf/opencv/result.jpg");
            faceArray.put(faceJSON);

            return faceArray.toString();

        } catch (IOException e) {
            System.err.printf("Failed while reading bytes from %s: ", e.getMessage());
            throw new InvalidContent();
        } catch (NullPointerException e) {
            throw new InvalidContent();
        }
    }

    @RequestMapping(value = "/api/recognize", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String recognizeFacesURL(@RequestParam("url") String url) {

        BufferedImage imageBuffered;
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        try {
            URL u = new URL(url);
            int contentLength = u.openConnection().getContentLength();
            imageBuffered = ImageIO.read(u);
            RioRecognizer recognize = recognitionService.recognizeAthlete(imageBuffered);

            Athlete athleteDetected = athleteService.findOne((long) recognize.getResult()[0]);

            ObjectMapper mapper = new ObjectMapper();
            JSONArray faceArray = new JSONArray();
            JSONObject faceJSON = new JSONObject();
            JSONObject athleteJSON = new JSONObject(mapper.writeValueAsString(athleteDetected));
            faceJSON.put("precision", recognize.getPrecision()[0]);
            faceJSON.put("athlete", athleteJSON);
            faceArray.put(faceJSON);
            return faceArray.toString();

        } catch (IOException e) {
            System.err.printf("Failed while reading bytes from %s: ", e.getMessage());
            throw new InvalidContent();
        } catch (NullPointerException e) {
            throw new InvalidContent();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public @ResponseBody
    String getAllSeries() throws InterruptedException {
        Thread.sleep(9000);	//pause to better show sync/async RestTemplate behavior
        JSONArray faceArray = new JSONArray();
        JSONObject faceJSON = new JSONObject();
        faceJSON.put("image", "/opt/openCV/image.png");
        faceJSON.put("result", "Athlete found !");
        faceJSON.put("detected", "1");
        faceArray.put(faceJSON);
        return faceArray.toString();
    }

    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String handleFileUpload(@RequestBody
        final Image image) {

        if (image.getContent().length < 1 || !image.getContentType().startsWith("image")) {
            throw new InvalidContent();
        }
        try {
            BufferedImage imageB = ImageIO.read(new ByteArrayInputStream(image.getContent()));
            RioRecognizer recognize = recognitionService.recognizeAthlete(imageB);

            Athlete athleteDetected = athleteService.findOne((long) recognize.getResult()[0]);

            ObjectMapper mapper = new ObjectMapper();
            JSONArray faceArray = new JSONArray();
            JSONObject faceJSON = new JSONObject();
            JSONObject athleteJSON = new JSONObject(mapper.writeValueAsString(athleteDetected));
            faceJSON.put("precision", recognize.getPrecision()[0]);
            faceJSON.put("athlete", athleteJSON);
            faceArray.put(faceJSON);
            return faceArray.toString();

        } catch (IOException e) {
            System.err.printf("Failed while reading bytes from %s: ", e.getMessage());
            throw new InvalidContent();
        } catch (NullPointerException e) {
            throw new InvalidContent();
        }
    }

}
