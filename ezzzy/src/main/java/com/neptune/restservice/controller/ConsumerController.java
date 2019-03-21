package com.neptune.restservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/consumer") 
@Api(value="Ezzzy Consumer Management System", description="Operations pertaining to consumer in Consumer Management System")
public class ConsumerController {

	@ApiOperation(value = "Add an consumer") 
	@PostMapping("/consumer") 
	public ResponseEntity<String> createEmployee( 
			) {
		return ResponseEntity.ok().body("Consumer Added");
	} 
	
	
	@ApiOperation(value = "Get an consumer by Id") 
	@GetMapping("/consumer/{id}") 
	public ResponseEntity<String> getEmployeeById( 
			@ApiParam(value = "Consumer id from which consumer object will retrieve", required = true) 
			@PathVariable(value = "id") Long employeeId)
			throws ResourceAccessException { 
		
		return ResponseEntity.ok().body("Consumer Returned");
	} 
}
