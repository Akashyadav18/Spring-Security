package com.Demo.Security.Application.repository;

import com.Demo.Security.Application.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameAndIsActive(String username, boolean isActive);

    Optional<UserEntity> findByUsername(String username);
}
