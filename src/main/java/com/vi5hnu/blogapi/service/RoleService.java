package com.vi5hnu.blogapi.service;

import com.vi5hnu.blogapi.Dto.RoleDto;
import com.vi5hnu.blogapi.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    RoleDto getRole(UUID id);
    List<RoleDto> getRoles();

    RoleDto addRole(RoleDto roleDto);
    void deleteRole(UUID id);
}
