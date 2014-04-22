/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.olympicinsa.riocognized.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "IMAGE_FACE")
public class ImageFace extends ImageCommon implements Serializable {

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
    private Athlete athlete;
    
    @JsonIgnore
    @Column(name = "face_content", length = 5000, nullable = true)
    @Lob
    private byte[] faceContent;
    
    @Column(name = "face_url")
    private String faceUrl;
    
    public ImageFace() {
        super();
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
   }
    
    public byte[] getFaceContent() {
        return faceContent;
    }
    
    public void setFaceContent(byte[] content) {
        this.faceContent = content;
    }
    
    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
   }
}
