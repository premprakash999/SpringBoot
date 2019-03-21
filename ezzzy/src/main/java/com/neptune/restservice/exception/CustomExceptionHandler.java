package com.neptune.restservice.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice 
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	 @ExceptionHandler(Exception.class)
	  public final ResponseEntity<NeptuneCustomException> handleAllExceptions(Exception ex, WebRequest request) {
	    NeptuneCustomException exceptionResponse = new NeptuneCustomException(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),
	        request.getDescription(false),null,null);
	    return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	  } 
	 /*
	 @ExceptionHandler(NeptuneCustomException.class)
	  public void handleCustomException(HttpServletResponse res, NeptuneCustomException ex) throws IOException {
	    res.sendError(ex.getHttpStatusCode().value(), ex.getMessage());
	  } */
	 @ExceptionHandler(NeptuneCustomException.class) 
	  public final ResponseEntity<NeptuneCustomException> handleUserNotFoundException(NeptuneCustomException ex, WebRequest request) {
		 
	    return new ResponseEntity<>(ex, ex.getHttpStatusCode());
	  } 
	 
}
