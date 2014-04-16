/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.olympicinsa.riocognized;

import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/**
 *
 * @author Alex
 */
public class MyExceptionHandler {
    /* Error Handling */

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(EmptyResultDataAccessException e, HttpServletRequest req) {
        return new ErrorMessage(e);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest req) {
        return new ErrorMessage(e);
    }

    @ExceptionHandler(NoSuchRequestHandlingMethodException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleNotFoundErrorException(NoSuchRequestHandlingMethodException e, HttpServletRequest req) {
        return new ErrorMessage("INTERNAL_SERVER_ERROR");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorMessage handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest req) {
        return new ErrorMessage("METHOD_NOT_ALLOWED");
    }
    
    @ExceptionHandler(InterruptedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ErrorMessage handleInterruptedException(HttpRequestMethodNotSupportedException e, HttpServletRequest req) {
        return new ErrorMessage("METHOD_NOT_ALLOWED");
    }
    
    @ExceptionHandler(InvalidContent.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorMessage handleInvalidContent(InvalidContent e, HttpServletRequest req) {
        return new ErrorMessage("INVALID_OR_EMPTY_CONTENT");
    }
  
    public class InvalidContent extends RuntimeException {
    }
}
