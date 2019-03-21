package com.neptune.restservice.service;

import com.neptune.restservice.domain.response.CreateRoleResponse;
import com.neptune.restservice.dto.RoleDTO;

public interface RoleService {

	CreateRoleResponse createRole(RoleDTO roleDTO);
}
