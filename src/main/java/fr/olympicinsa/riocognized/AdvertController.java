/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.olympicinsa.riocognized;

import fr.olympicinsa.riocognized.exception.MyExceptionHandler;
import fr.olympicinsa.riocognized.model.Image;
import fr.olympicinsa.riocognized.model.ImagePub;
import fr.olympicinsa.riocognized.repository.ImagePubRepository;
import fr.olympicinsa.riocognized.repository.ImageRepository;
import fr.olympicinsa.riocognized.service.ImageService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *
 * @author alex
 */

@Controller
@RequestMapping("/ad")
@ComponentScan("fr.olympicinsa.riocognized.repository")
public class AdvertController extends MyExceptionHandler{

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImagePubRepository imagePubRepository;
    @Autowired
    private ImageService imageService;
    
    @RequestMapping("")
    public String getAdvert(HttpServletResponse response) {
        
        ImagePub ad = imageService.findOneRandom();
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(ad.getContent());
            response.setHeader("Content-Disposition", "inline;filename=\"" + ad.getFilename() + "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(ad.getContentType());
            IOUtils.copy(bis, out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping("/manage")
    public String index(Map<String, Object> map) {
        try {
            map.put("image", new ImagePub());
            map.put("imageList", imagePubRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "advert";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("image") ImagePub image,
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
            imagePubRepository.save(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/ad/manage";
    }

    @RequestMapping("/download/{imageId}")
    public String download(@PathVariable("imageId") Long imageId, HttpServletResponse response) {

        ImagePub doc = imagePubRepository.findOne(imageId);
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

        imagePubRepository.delete(imageId);

        return "redirect:/ad/manage";
    }
    
}

