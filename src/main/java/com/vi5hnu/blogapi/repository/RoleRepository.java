package com.vi5hnu.blogapi.repository;

import com.vi5hnu.blogapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface RoleRepository extends JpaRepository<Role,UUID> {
    Optional<Role> findByName(String name);
}
