/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.olympicinsa.riocognized.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "IMAGE_COMMON")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ImageCommon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "filename")
    private String filename;

    @Column(name = "content", length = 1000000, nullable = false)
    @Lob
    private byte[] content;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "created")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;

    public ImageCommon() {
        this.created = new Date();
    }

    public String getExtension() {
        String mimeType = contentType.split("/")[1];
        return mimeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImageCommon)) {
            return false;
        }
        ImageCommon other = (ImageCommon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.olympicinsa.riocognized.model.Image[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFilename() {
        if (filename != null) {
            return filename;
        } else {
            return "riocognized-" + id + "." + this.getExtension();
        }
    }

    @JsonIgnore
    public byte[] getContent() {
        return content;
    }

    public String getCreated() {
        DateFormat dateFormat
            = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return dateFormat.format(created);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @JsonProperty("content")
    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
