/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.olympicinsa.riocognized.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToOne;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "IMAGE_CLIENT")
public class ImagePub extends ImageCommon implements Serializable {

    @ManyToOne
    private Client client;
    
    public ImagePub() {
        super();
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    	if (!client.getImages().contains(this)) {
    		client.getImages().add(this);
        }
    }
    
}
