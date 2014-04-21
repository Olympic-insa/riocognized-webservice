package fr.olympicinsa.riocognized;

import fr.olympicinsa.riocognized.exception.MyExceptionHandler;
import fr.olympicinsa.riocognized.facedetector.FaceDetector;

import fr.olympicinsa.riocognized.model.*;
import fr.olympicinsa.riocognized.repository.*;
import fr.olympicinsa.riocognized.service.AthleteService;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.beans.PropertyEditorSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.opencv.core.CvType;
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
    private ImageRepository imageRepository;
    @Autowired
    private ImageFaceRepository imageFaceRepository;
    
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
    
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Athlete.class, "athlete", new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) {
            Athlete ath = athleteService.findOne(Long.parseLong(text));
            setValue(ath);
        }
        });
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

    @RequestMapping("/remove/{imageId}")
    public String remove(@PathVariable("imageId") Long imageId) {

        imageFaceRepository.delete(imageId);

        return "redirect:/recognition";
    }

    /* API POST Method*/
    @RequestMapping(value = "/api/detect/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String detectFaces() {

        //System.load("/opt/openCV/libopencv_java248.so");
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String haar = "/opt/openCV/haarcascade_frontalface_alt.xml";
        String image = "/opt/openCV/image.jpg";
        String dest = "/opt/openCV/imagedetect.jpg";
        FaceDetector detector = new FaceDetector();
        int detected = detector.detectFaces(image, dest);

        JSONArray faceArray = new JSONArray();
        JSONObject faceJSON = new JSONObject();
        faceJSON.put("image", "/opt/openCV/image.png");
        faceJSON.put("classifier", haar);
        faceJSON.put("detected", detected);
        faceArray.put(faceJSON);

        return faceArray.toString();
    }

    @RequestMapping(value = "/api/detect", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String detectFacesURL(@RequestParam("url") String url) {

        String haar = "/opt/openCV/haarcascade_frontalface_alt.xml";
        String dest = "/var/www/opencv/result.jpg";
        FaceDetector detector = new FaceDetector();
        BufferedImage imageBuffered;
        ByteArrayOutputStream bais = new ByteArrayOutputStream();
        Mat newMat;

        try {
            URL u = new URL(url);
            int contentLength = u.openConnection().getContentLength();
            imageBuffered = ImageIO.read(u);
            int rows = imageBuffered.getWidth();
            int cols = imageBuffered.getHeight();
            byte[] data = ((DataBufferByte) imageBuffered.getRaster().getDataBuffer()).getData();
            Mat mat = new Mat(cols, rows, CvType.CV_8UC3);
            mat.put(0, 0, data);

            //System.load("/opt/openCV/libopencv_java248.so");
            //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            int detected = detector.detectFaces(mat, dest);

            JSONArray faceArray = new JSONArray();
            JSONObject faceJSON = new JSONObject();
            faceJSON.put("image", url);
            faceJSON.put("classifier", haar);
            faceJSON.put("detected", detected);
            faceJSON.put("result", "http://lynxlabs.fr.nf/opencv/result.jpg");
            faceArray.put(faceJSON);

            return faceArray.toString();

        } catch (IOException e) {
            System.err.printf("Failed while reading bytes from %s: ", e.getMessage());
            e.printStackTrace();
            //Content is null
            throw new InvalidContent();
        } catch (NullPointerException e) {
            //Content is not an image
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

}
