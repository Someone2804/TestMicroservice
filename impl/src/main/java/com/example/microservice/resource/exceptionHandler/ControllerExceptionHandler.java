package com.example.microservice.resource.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class,
            NoResultException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFound(Exception e){
        log.warn(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String badRequest(Exception e){
        log.warn(e.getMessage());
        return e.getMessage();
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public String internalError(Exception e){
//        log.error(e.getMessage());
//        return e.getMessage();
//    }
}
