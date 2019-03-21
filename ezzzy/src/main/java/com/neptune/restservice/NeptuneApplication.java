package com.neptune.restservice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.neptune.restservice.model.Privilege;
import com.neptune.restservice.model.Role;
import com.neptune.restservice.model.User;
import com.neptune.restservice.repository.PrivilegeRepository;
import com.neptune.restservice.repository.RoleRepository;
import com.neptune.restservice.repository.UserRepository;
import com.neptune.restservice.utility.NeptuneConstant;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
public class NeptuneApplication implements CommandLineRunner{

	//@Autowired
	//AdminService adminService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	PrivilegeRepository privilegeRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(NeptuneApplication.class, args);
	}
	
	    @Override
	    public void run(String... args) throws Exception{
	    	
	    	
		 
	 }
}

