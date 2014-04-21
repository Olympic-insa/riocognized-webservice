/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.olympicinsa.riocognized.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

    public ImageFace() {
        super();
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
   }
}
