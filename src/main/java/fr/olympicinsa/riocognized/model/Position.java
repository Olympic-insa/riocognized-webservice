package fr.olympicinsa.riocognized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map; 
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import org.json.JSONException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import javax.servlet.ServletContext;
import javax.xml.bind.annotation.XmlRootElement;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.engine.profile.Fetch;

import org.springframework.util.Assert;

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
