package com.user.profile.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.user.profile.model.UserProfile;
import com.user.profile.repository.UserProfileRepository;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile createUserProfile(UserProfile userProfile) {
        userProfile.setCreatedAt(LocalDateTime.now());
        return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfile getUserProfileById(Integer id) {
        return userProfileRepository.findById(id).orElse(null);
    }

    public UserProfile updateUserProfile(Integer id, UserProfile userProfile) {
        UserProfile existingProfile = userProfileRepository.findById(id).orElse(null);
        if (existingProfile != null) {
            existingProfile.setUsername(userProfile.getUsername());
            existingProfile.setEmail(userProfile.getEmail());
            return userProfileRepository.save(existingProfile);
        }
        return null;
    }

    public boolean deleteUserProfile(Integer id) {
        UserProfile existingProfile = userProfileRepository.findById(id).orElse(null);
        if (existingProfile != null) {
            userProfileRepository.delete(existingProfile);
            return true;
        }
        return false;
    }
}

