package com.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
