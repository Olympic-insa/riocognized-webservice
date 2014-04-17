/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.olympicinsa.riocognized.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author alex
 */

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
