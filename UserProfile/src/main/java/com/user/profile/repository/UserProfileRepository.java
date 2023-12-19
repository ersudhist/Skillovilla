package com.user.profile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.user.profile.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{
	 Optional<UserProfile> findByUsername(String username);

}
