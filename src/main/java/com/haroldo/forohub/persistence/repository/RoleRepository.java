package com.haroldo.forohub.persistence.repository;

import com.haroldo.forohub.persistence.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findRoleByRoleEnumIn(List<String> roles);
}
