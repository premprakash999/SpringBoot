package com.neptune.restservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neptune.restservice.domain.response.CreateRoleResponse;
import com.neptune.restservice.domain.response.CreateUserResponse;
import com.neptune.restservice.domain.response.TokenResponse;
import com.neptune.restservice.dto.AdminDataDTO;
import com.neptune.restservice.dto.RoleDTO;
import com.neptune.restservice.service.AdminService;
import com.neptune.restservice.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/admin")
@Api(tags = "admin")
public class AdminController {


	  @Autowired
	  private AdminService adminService;
	  
	  @Autowired
	  private RoleService roleService;
	
	  @GetMapping("/adminToken")
	  @ApiOperation(value = "get Admin Token")
	  public ResponseEntity<TokenResponse> adminToken(//
	      @ApiParam("Username") @RequestHeader String username,
	      @ApiParam("Password") @RequestHeader String password) {
		  return new ResponseEntity<>(adminService.getAdminToken(username, password),HttpStatus.OK);
	  }
	  
	  @GetMapping("/token")
	  @PreAuthorize("hasAuthority('PERM_TOKEN_CREATE')")
	  @ApiOperation(value = "get Token")
	  public ResponseEntity<TokenResponse> token(//
		  @ApiParam("Authorization") @RequestHeader String Authorization,
	      @ApiParam("Username") @RequestHeader String username,
	      @ApiParam("Password") @RequestHeader String password) {
		  return new ResponseEntity<>(adminService.getToken(username, password),HttpStatus.OK);
	  }
	  
	  @PostMapping("/appadmin")
	  @PreAuthorize("hasAuthority('PERM_APP_ADMIN_CREATE')")
	  @ApiOperation(value = "Create App Admin")
	  public ResponseEntity<CreateUserResponse> appAdmin(//
	      @ApiParam("Authorization") @RequestHeader String Authorization, //
	      @ApiParam("Create App Admin") @Valid @RequestBody AdminDataDTO adminData) {
		  return new ResponseEntity<>(adminService.createAppAdminUser(adminData),HttpStatus.OK);
	  }
	  
	  @PostMapping("/appadmin")
	  @PreAuthorize("hasAuthority('PERM_ROLE_CREATE')")
	  @ApiOperation(value = "Create Role")
	  public ResponseEntity<CreateRoleResponse> createRole(//
	      @ApiParam("Authorization") @RequestHeader String Authorization, //
	      @ApiParam("Create Role") @Valid @RequestBody RoleDTO roleDTO) {
		  return new ResponseEntity<>(roleService.createRole(roleDTO),HttpStatus.OK);
	  }
}
