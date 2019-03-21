package com.neptune.restservice.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseHeader {
	private String apiName;
	private String apiVersion;
	private Boolean isSuccess;
	private String created_at;
	private ResponseHeader responseHeader;
	
	public ResponseHeader(String apiName,String apiVersion,Boolean isSuccess,String created_at)
	{
		this.apiName=apiName;
		this.apiVersion=apiVersion;
		this.isSuccess=isSuccess;
		this.created_at=created_at;
	}
	
	public ResponseHeader()
	{
		
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}
	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
	
}
