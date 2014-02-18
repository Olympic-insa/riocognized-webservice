/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.olympicinsa.riocognized;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author alex
 */

@XmlRootElement(name = "error")
public class ErrorMessage {

    private Throwable exception;
    private String message;

    public ErrorMessage() {
        this.message = "";
    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(Throwable exception) {
        this.exception = exception;
        this.message = exception.getLocalizedMessage();
    }

    @XmlTransient
    @JsonIgnore
    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    @XmlElement(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
