package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.enums.Role;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, UUID> {
    Optional<UserRole> findByRole(UserRole roleEnum);

    Optional<UserRole> findUserRoleByRole(Role role);

}
