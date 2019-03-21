package com.neptune.restservice.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;


public class AdminDataDTO {
 
  @ApiModelProperty(position = 1)
  @NotNull
  @Size(min=4, message="Minimum length for Firstname : 4 ")
  private String firstname;
  
  @ApiModelProperty(position = 2)
  @NotNull
  @Size(min=2,message="Minimum length lastName: 2 ")
  private String lastname;

  @ApiModelProperty(position = 3)
  @NotNull
  @Email(message="Invalid Email Address")
  private String email;

  @ApiModelProperty(position = 4)
  @NotNull
  @Size(min = 8, message = "Minimum password length: 8 characters")
  private String password;

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
}
