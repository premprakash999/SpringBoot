package com.neptune.restservice.dto;

import java.util.List;

import javax.validation.constraints.Size;

import com.neptune.restservice.model.Privilege;

import io.swagger.annotations.ApiModelProperty;

public class RoleDTO {
	  @ApiModelProperty(position = 1)
	  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
	  private String roleName;
	
	  @ApiModelProperty(position = 2)
	  private List<Privilege> lstPrivileges;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Privilege> getLstPrivileges() {
		return lstPrivileges;
	}

	public void setLstPrivileges(List<Privilege> lstPrivileges) {
		this.lstPrivileges = lstPrivileges;
	}
	  
	  
	  
}
