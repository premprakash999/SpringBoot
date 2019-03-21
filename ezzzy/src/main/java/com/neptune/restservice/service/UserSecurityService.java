package com.neptune.restservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neptune.restservice.model.Privilege;
import com.neptune.restservice.model.Role;
import com.neptune.restservice.model.User;
import com.neptune.restservice.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	  public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
	    final User user = userRepository.findByEmail(userEmail);

	    if (user == null) {
	      throw new UsernameNotFoundException("User '" + userEmail + "' not found");
	    }
	    return org.springframework.security.core.userdetails.User
	    		.withUsername(userEmail)//
	            .password(user.getPassword())//
	            .authorities(getAuthorities(user.getRoles()))//
	            .accountExpired(false)//
	            .accountLocked(false)//
	            .credentialsExpired(false)//
	            .disabled(!user.isEnabled())//
	            .build();
	   // return org.springframework.security.core.userdetails.
       // return org.springframework.security.core.userdetails.(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
	   // return org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
	  }

	private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }
	private final List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<String>();
        final List<Privilege> collection = new ArrayList<Privilege>();
        for (final Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }

    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
