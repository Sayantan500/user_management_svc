package com.user.user_management_svc.controllers;

import com.user.user_management_svc.models.UserBioData;
import com.user.user_management_svc.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserProfileAPIs
{
    private final UserRepository userRepository;

    @GetMapping("/users/{id}/profile")
    public ResponseEntity<UserBioData> getUserProfileByID(@PathVariable(name = "id") String userID)
    {
        UserBioData userProfileFromDB = userRepository.getUserProfile(userID);
        return userProfileFromDB!=null ?
                new ResponseEntity<>(userProfileFromDB, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public HttpStatus deleteUserByID(@PathVariable(name = "id") String userID)
    {
        //Todo: check if the user profile is correctly deleted and return appropriate response
        userRepository.deleteUserProfile(userID);
        return HttpStatus.NO_CONTENT;
    }

    @PostMapping("/user/{id}/profile")
    public ResponseEntity<UserBioData> updateEntireUserProfile(
            @PathVariable(name = "id") String userID,
            @RequestBody UserBioData updatedUserProfile
    )
    {
        //Todo: update the profile and return the updated profile with userID and appropriate http status code
        userRepository.updateUserProfile(userID, updatedUserProfile);
        return null;
    }
}
