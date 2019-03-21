package com.neptune.restservice.serviceimpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.neptune.restservice.domain.response.CreateUserResponse;
import com.neptune.restservice.domain.response.ResponseHeader;
import com.neptune.restservice.domain.response.TokenResponse;
import com.neptune.restservice.dto.AdminDataDTO;
import com.neptune.restservice.exception.NeptuneCustomException;
import com.neptune.restservice.model.Role;
import com.neptune.restservice.model.User;
import com.neptune.restservice.repository.RoleRepository;
import com.neptune.restservice.repository.UserRepository;
import com.neptune.restservice.security.JwtTokenProvider;
import com.neptune.restservice.service.AdminService;
import com.neptune.restservice.service.UserSecurityService;
import com.neptune.restservice.utility.NeptuneConstant;
import com.neptune.restservice.utility.NeptuneUtil;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository adminRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserSecurityService adminService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;



	

	public TokenResponse getAdminToken(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			final UserDetails userDetails = adminService.loadUserByUsername(username);
			boolean test=userDetails.getAuthorities().stream().filter(r->r.getAuthority().equalsIgnoreCase(NeptuneConstant.PERM_TOKEN_CREATE))
			.findAny().isPresent();
			if(!userDetails.isEnabled() || !userDetails.getAuthorities().stream().filter(r->r.getAuthority().equalsIgnoreCase(NeptuneConstant.PERM_TOKEN_CREATE))
					.findAny().isPresent())
			{
				throw new NeptuneCustomException(HttpStatus.UNAUTHORIZED, NeptuneConstant.TokenError,
						NeptuneConstant.TokenErrorMessage, "/token", NeptuneConstant.v1ApiVersion);
			}
			String token = jwtTokenProvider.createToken(userDetails.getUsername(), userDetails.getAuthorities());
			TokenResponse tokenResponse = new TokenResponse();
			tokenResponse.setResponseHeader(NeptuneUtil
					.getV1ResponseHeader(new ResponseHeader("/token", NeptuneConstant.v1ApiVersion, true, null)));
			tokenResponse.setAuth_token(token);
			return tokenResponse;
		} catch (Exception e) {
			throw new NeptuneCustomException(HttpStatus.UNAUTHORIZED, NeptuneConstant.TokenError,
					NeptuneConstant.TokenErrorMessage, "/token", NeptuneConstant.v1ApiVersion);
		}
	}

	public TokenResponse getToken(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			final UserDetails userDetails = adminService.loadUserByUsername(username);
			boolean test=userDetails.getAuthorities().stream().filter(r->r.getAuthority().equalsIgnoreCase(NeptuneConstant.PERM_TOKEN_CREATE))
			.findAny().isPresent();
			if(!userDetails.isEnabled() || !userDetails.getAuthorities().stream().filter(r->r.getAuthority().equalsIgnoreCase(NeptuneConstant.PERM_TOKEN_CREATE))
					.findAny().isPresent())
			{
				throw new NeptuneCustomException(HttpStatus.UNAUTHORIZED, NeptuneConstant.TokenError,
						NeptuneConstant.TokenErrorMessage, "/token", NeptuneConstant.v1ApiVersion);
			}
			String token = jwtTokenProvider.createToken(userDetails.getUsername(), userDetails.getAuthorities());
			TokenResponse tokenResponse = new TokenResponse();
			tokenResponse.setResponseHeader(NeptuneUtil
					.getV1ResponseHeader(new ResponseHeader("/token", NeptuneConstant.v1ApiVersion, true, null)));
			tokenResponse.setAuth_token(token);
			return tokenResponse;
		} catch (Exception e) {
			throw new NeptuneCustomException(HttpStatus.UNAUTHORIZED, NeptuneConstant.TokenError,
					NeptuneConstant.TokenErrorMessage, "/token", NeptuneConstant.v1ApiVersion);
		}
	}

	
	@Override
	public User findByUsername(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateUserResponse createAppAdminUser(AdminDataDTO adminDTO) {
		try {
			 if (adminRepository.findByEmail(adminDTO.getEmail()) != null) {
				throw new NeptuneCustomException(HttpStatus.CONFLICT, NeptuneConstant.USEREMAIL_EXIST_ERROR,
						NeptuneConstant.USEREMAIL_EXIST_ERROR, "/appadmin", NeptuneConstant.v1ApiVersion);
			} else {
				CreateUserResponse createUserResponse = new CreateUserResponse();
				User adminUser = new User();
				Role appAdminRole=new Role(NeptuneConstant.ROLE_APP_ADMIN);
				adminUser.setRoles(Arrays.asList(appAdminRole));
				adminUser.setEnabled(true);
				adminUser.setFirstName(adminDTO.getFirstname());
				adminUser.setLastName(adminDTO.getLastname());
				adminUser.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
				adminRepository.save(adminUser);
				createUserResponse.setMessage(NeptuneConstant.Create_APP_ADMIN_SUCCESS);
				createUserResponse.setResponseHeader(NeptuneUtil.getV1ResponseHeader(
						new ResponseHeader("/appadmin", NeptuneConstant.v1ApiVersion, true, null)));
				return createUserResponse;
			}

		}
		catch(NeptuneCustomException ne)
		{
			throw ne;
		}
		catch (Exception e) {
			throw new NeptuneCustomException(HttpStatus.INTERNAL_SERVER_ERROR, NeptuneConstant.GENERIC_ERROR,
					NeptuneConstant.GENERIC_ERROR, "/appadmin", NeptuneConstant.v1ApiVersion);
		}

	}
}