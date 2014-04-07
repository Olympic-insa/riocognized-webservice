package fr.olympicinsa.riocognized;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONException;

import java.io.IOException;

import fr.olympicinsa.riocognized.model.*;
import fr.olympicinsa.riocognized.facedetector.FaceDetector;
import fr.olympicinsa.riocognized.repository.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.imageio.ImageIO;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
@ComponentScan("fr.olympicinsa.riocognized.repository")
@RequestMapping("/recognition")
public class RecognitionController extends MyExceptionHandler {

    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = "/api/athletes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Athlete athleteByIdJson(ModelMap model, @PathVariable("id") long id) throws JSONException {
        return athleteRepository.findOne(id);
    }

    @RequestMapping("/download/{imageId}")
    public String download(@PathVariable("imageId") Long imageId, HttpServletResponse response) {

        Image doc = imageRepository.findOne(imageId);
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
        FaceDetector detector = new FaceDetector(haar);
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
        FaceDetector detector = new FaceDetector(haar);
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

}
