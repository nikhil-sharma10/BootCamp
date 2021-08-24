package com.bootcampProject.BootcampProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileUploadException.class)
    public final ResponseEntity<Object> handleFileUploadException(Exception ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
