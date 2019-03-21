package com.neptune.restservice.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.neptune.restservice.domain.response.CreateRoleResponse;
import com.neptune.restservice.dto.RoleDTO;
import com.neptune.restservice.exception.NeptuneCustomException;
import com.neptune.restservice.model.Role;
import com.neptune.restservice.repository.RoleRepository;
import com.neptune.restservice.service.RoleService;
import com.neptune.restservice.utility.NeptuneConstant;

public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public CreateRoleResponse createRole(RoleDTO roleDTO) {
		try {
			if(roleExists(roleDTO.getRoleName()))
			{
				throw new NeptuneCustomException(HttpStatus.CONFLICT, NeptuneConstant.ROLE_EXIST_ERROR,
						NeptuneConstant.ROLE_EXIST_ERROR, "craete /role", NeptuneConstant.v1ApiVersion);
			}
			CreateRoleResponse createRoleResponse=new CreateRoleResponse();
			Role role=new Role();
			role.setName(roleDTO.getRoleName());
			role.setPrivileges(roleDTO.getLstPrivileges());
			roleRepository.save(role);
			return createRoleResponse;
		}
		catch(NeptuneCustomException ne)
		{
			throw ne;
		}
		catch (Exception e) {
			throw new NeptuneCustomException(HttpStatus.INTERNAL_SERVER_ERROR, NeptuneConstant.GENERIC_ERROR,
					NeptuneConstant.GENERIC_ERROR, "craete /role", NeptuneConstant.v1ApiVersion);
		}

	}
	
	private boolean roleExists(String roleName)
	{
		boolean isExists=false;
		Role role=roleRepository.findByName(roleName);
		if(role!=null)
			isExists=true;
		return isExists;
	}

}
