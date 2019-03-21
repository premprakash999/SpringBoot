package com.neptune.restservice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



@Component
@EnableAutoConfiguration
public class RequestInterceptor extends HandlerInterceptorAdapter{
	
	private static Logger logger = LogManager.getLogger(RequestInterceptor.class);
	
	@Autowired
	BuildProperties buildProperties;
	
	@Override 
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object object) throws Exception {
		return true; 
	} 
 
	@Override 
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object object, ModelAndView model)
			throws Exception {
	} 
 
	@Override 
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object object, Exception arg3)
			throws Exception {
	} 

}
