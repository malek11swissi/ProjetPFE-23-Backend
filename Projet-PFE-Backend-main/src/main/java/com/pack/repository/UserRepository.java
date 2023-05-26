

	package com.pack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.models.ERole;
import com.pack.models.User;


public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByTelephone(String telephone);

	Optional<User> findByTelephone(String telephone);

	List<User> findAllByRole(ERole role);
	List<User> findAllByRoleNot(ERole role);
}

	


