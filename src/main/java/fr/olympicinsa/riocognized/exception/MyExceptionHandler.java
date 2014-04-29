/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.olympicinsa.riocognized.exception;

import fr.olympicinsa.riocognized.exception.ErrorMessage;
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
        return new ErrorMessage("ATHLETE_NOT_FOUND");
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
    
    @ExceptionHandler(TooManyResultException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.REQUEST_ENTITY_TOO_LARGE)
    public ErrorMessage handleTooManyResultException(TooManyResultException e, HttpServletRequest req) {
        return new ErrorMessage("TOO_MANY_RESULTS");
    }
    
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    public ErrorMessage handleNullPointerException(NullPointerException e, HttpServletRequest req) {
        return new ErrorMessage("ENTITY_HAS_UNALLOWED_NULL_VALUE");
    }
    
    @ExceptionHandler(NoFaceDetectedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ErrorMessage handleNoFaceDetectedException(NoFaceDetectedException e, HttpServletRequest req) {
        return new ErrorMessage("NO_FACE_DETECTED");
    }
    
    @ExceptionHandler(NotRecognizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorMessage handleNotRecognizedException(NotRecognizedException e, HttpServletRequest req) {
        return new ErrorMessage("NOT_RECOGNIZED");
    }
    
    public static class InvalidContent extends RuntimeException {
    }
    
    public static class TooManyResultException extends RuntimeException {
    }
    
    public static class NoFaceDetectedException extends RuntimeException {
    }
    
    public static class NotRecognizedException extends RuntimeException {
    }
}
