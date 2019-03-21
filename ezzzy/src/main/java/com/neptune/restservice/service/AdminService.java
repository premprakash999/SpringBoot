package com.neptune.restservice.service;

import java.util.Set;

import com.neptune.restservice.domain.response.CreateUserResponse;
import com.neptune.restservice.domain.response.TokenResponse;
import com.neptune.restservice.dto.AdminDataDTO;
import com.neptune.restservice.model.Role;
import com.neptune.restservice.model.User;

public interface AdminService {
	
	//User createSuperAdminUser(User admin, Set<Role> adminRoles);
	
	CreateUserResponse createAppAdminUser(AdminDataDTO admin);

	User findByUsername(String email);
		
   TokenResponse getAdminToken(String username, String password);
   
   TokenResponse getToken(String username, String password);
   
   
}
