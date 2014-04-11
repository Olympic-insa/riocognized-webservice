package fr.olympicinsa.riocognized.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "POSITION")
public class Position implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String descritpion;
    private float latitude;
    private float longitude;
    
    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return descritpion;
    }

    public void setDescription(String descritpion) {
        this.descritpion = descritpion;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setContent(String type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return this.latitude;
    }
}
