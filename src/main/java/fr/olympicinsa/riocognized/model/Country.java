package fr.olympicinsa.riocognized.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import javax.persistence.Column;

@Entity
@Table(name="COUNTRY")
public class Country implements Serializable{

    @Id
    private String id;
    @Column(name="NAME")
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
