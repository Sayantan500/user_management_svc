package com.user.user_management_svc.controllers;

import com.user.user_management_svc.models.UpdateAFieldInUserProfile;
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
    public ResponseEntity<Object> deleteUserByID(@PathVariable(name = "id") String userID)
    {
        //Todo: check if the user profile is correctly deleted and return appropriate response
        return
                userRepository.deleteUserProfile(userID) ?
                        new ResponseEntity<>(HttpStatus.OK) :
                        new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/users/{id}/profile")
    public ResponseEntity<String> updateEntireUserProfile(
            @PathVariable(name = "id") String userID,
            @RequestBody UserBioData updatedUserProfile
    )
    {
        //Todo: return the updated profile with userID and appropriate http status code
        return userRepository.updateUserProfile(
                userID,
                updatedUserProfile
        ) ?
                new ResponseEntity<>("updated",HttpStatus.OK) :
                new ResponseEntity<>("update failed",HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/users/{id}/profile/{field}")
    public ResponseEntity<String> updateFieldValueOfUserProfile(
            @PathVariable(name = "id") String userID,
            @PathVariable(name = "field") String fieldNameToUpdate,
            @RequestBody UpdateAFieldInUserProfile updateObject
            )
    {
        //Todo: return the updated profile with userID and appropriate http status code
        return userRepository.updateFieldValueInUserProfile(
                userID,
                fieldNameToUpdate,
                updateObject.getNewValue()
        ) ?
                new ResponseEntity<>("updated",HttpStatus.OK) :
                new ResponseEntity<>("update failed",HttpStatus.NOT_MODIFIED);
    }

    /// Development and testing only
    @PostMapping("/users/new")
    public ResponseEntity<String> saveNewUser(@RequestBody UserBioData newUserProfile)
    {
        String userID = userRepository.saveUserProfile(newUserProfile,null);
        return new ResponseEntity<>(userID,HttpStatus.CREATED);
    }
}
