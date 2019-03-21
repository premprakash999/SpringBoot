package com.neptune.restservice.utility;

import java.time.Instant;

import com.neptune.restservice.domain.response.ResponseHeader;

public class NeptuneUtil {

	public static String getCurrentDateUTC()
	{
		Instant instant = Instant.now();
		return instant.toString();
	}
	
	public static ResponseHeader getV1ResponseHeader(ResponseHeader responseHeader)
	{
		if(responseHeader.getIsSuccess()==null)
			responseHeader.setIsSuccess(false);
		if(responseHeader.getApiName()==null)
			responseHeader.setApiName(NeptuneConstant.dummyApiName);
		if(responseHeader.getApiVersion()==null)
			responseHeader.setApiVersion(NeptuneConstant.v1ApiVersion);
		if(responseHeader.getCreated_at()==null)
			responseHeader.setCreated_at(NeptuneUtil.getCurrentDateUTC());
		return responseHeader;
	}
}
