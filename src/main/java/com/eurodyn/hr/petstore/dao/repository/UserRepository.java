package com.eurodyn.hr.petstore.dao.repository;

import com.eurodyn.hr.petstore.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

    User findByEmail(String email);

    User findByExternalId(UUID externalId);
}