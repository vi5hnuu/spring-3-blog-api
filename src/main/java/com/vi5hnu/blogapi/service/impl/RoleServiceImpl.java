package com.vi5hnu.blogapi.service.impl;

import com.vi5hnu.blogapi.Dto.RoleDto;
import com.vi5hnu.blogapi.exception.ResourceNotFoundException;
import com.vi5hnu.blogapi.model.Role;
import com.vi5hnu.blogapi.repository.RoleRepository;
import com.vi5hnu.blogapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    final private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }
    @Override
    public RoleDto getRole(UUID id) {
        final Role role= this.roleRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("role not found for id %s", id)));
        return new RoleDto(role.getId(),role.getName());
    }

    @Override
    public List<RoleDto> getRoles() {
        return this.roleRepository.findAll().stream().map(role->new RoleDto(role.getId(),role.getName())).toList();
    }

    @Override
    public RoleDto addRole(RoleDto roleDto) {
        final Role role=new Role(null,roleDto.getName());
        final Role savedRole=this.roleRepository.save(role);
        roleDto.setId(savedRole.getId());
        return roleDto;
    }

    @Override
    public void deleteRole(UUID id) {
        if(!this.roleRepository.existsById(id)){
            throw new ResourceNotFoundException(String.format("Role with id %s does not exists.",id));
        }
        this.roleRepository.deleteById(id);
    }
}
