package com.vi5hnu.blogapi.controller;

import com.vi5hnu.blogapi.Dto.RoleDto;
import com.vi5hnu.blogapi.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
    final private RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }
    @GetMapping(path = "")
    public ResponseEntity<List<RoleDto>> getRoles(){
        return new ResponseEntity<>(this.roleService.getRoles(), HttpStatus.OK);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<RoleDto> getRole(@org.hibernate.validator.constraints.UUID(allowNil = false,message = "invalid role id") @PathVariable UUID id){
        return new ResponseEntity<>(this.roleService.getRole(id), HttpStatus.OK);
    }
    @PostMapping(path = "")
    public ResponseEntity<String> addRole(@Valid @RequestBody RoleDto roleDto){
        this.roleService.addRole(roleDto);
        return new ResponseEntity<>("Role Created Successfully", HttpStatus.CREATED);
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteRole(@org.hibernate.validator.constraints.UUID(allowNil = false,message = "invalid role id") @PathVariable UUID id){
        this.roleService.deleteRole(id);
        return new ResponseEntity<>("Role Created Successfully", HttpStatus.CREATED);
    }
}
