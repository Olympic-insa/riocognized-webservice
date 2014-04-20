package fr.olympicinsa.riocognized.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "LAST_NAME")
    private String name;
    @Column(name = "FIRST_NAME")
    private String surname;
    @Column(name = "CONTENT")
    private String content;
    @ManyToOne
    private Country country;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "client")
    private Set<ImagePub> images = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set getImages() {
        return images;
    }

    public void setImages(Set images) {
        this.images = images;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("image_url")
    public List<String> getURL() {
        Iterator list = this.images.iterator();
        List<String> urlList = new ArrayList<>();
        while (list.hasNext()) {
            ImagePub image = (ImagePub) list.next();
            urlList.add("http://olympic-insa.fr.nf:8083/image/download/" + image.getId());
        }
        return urlList;
    }

    public void addImage(ImagePub image) {
        images.add(image);
        if (image.getClient() != this) {
            image.setClient(this);
        }
    }

    public void deleteImage(ImagePub image) {
        if (image != null) {
            images.remove(image);
        }
    }

}
