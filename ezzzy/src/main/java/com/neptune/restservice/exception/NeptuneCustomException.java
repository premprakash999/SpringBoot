package com.neptune.restservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.neptune.restservice.utility.NeptuneUtil;

public class NeptuneCustomException extends RuntimeException{
	  private HttpStatus httpStatusCode;
	  private String timestamp;
	  private String message;
	  private String details;
	  private String apiName;
	  private String apiVserion;
	 
	public NeptuneCustomException(HttpStatus httpStatusCode,String message, String details,String apiName,String apiVersion) {
	    super(); 
	    this.httpStatusCode=httpStatusCode;
	    this.timestamp = NeptuneUtil.getCurrentDateUTC();
	    this.message = message;
	    this.details = details;
	    this.apiName=apiName;
	    this.apiVserion=apiVersion;
	  } 
	 
	  public String getTimestamp() {
	    return timestamp;
	  } 
	 
	  public String getMessage() {
	    return message;
	  } 
	 
	  public String getDetails() {
	    return details;
	  } 
	  public String getApiName() {
			return apiName;
		}

		public String getApiVserion() {
			return apiVserion;
		}

		public HttpStatus getHttpStatusCode() {
			return httpStatusCode;
		}

}
