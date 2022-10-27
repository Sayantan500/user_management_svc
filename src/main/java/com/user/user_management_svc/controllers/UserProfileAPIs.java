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
                new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUserByID(@PathVariable(name = "id") String userID)
    {
        return userRepository.deleteUserProfile(userID) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/users/{id}/profile")
    public ResponseEntity<UserBioData> updateEntireUserProfile(
            @PathVariable(name = "id") String userID,
            @RequestBody UserBioData updatedUserProfile
    )
    {
        UserBioData updateUserProfile =
                userRepository.updateUserProfile(
                        userID,
                        updatedUserProfile
                );
        return updateUserProfile!=null ?
                new ResponseEntity<>(updatedUserProfile,HttpStatus.OK) :
                new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/users/{id}/profile/{field}")
    public ResponseEntity<UserBioData> updateFieldValueOfUserProfile(
            @PathVariable(name = "id") String userID,
            @PathVariable(name = "field") String fieldNameToUpdate,
            @RequestBody UpdateAFieldInUserProfile updateObject
    )
    {
        UserBioData updatedUserProfile =
                userRepository.updateFieldValueInUserProfile(
                        userID,
                        fieldNameToUpdate,
                        updateObject.getNewValue()
                );

        return updatedUserProfile!=null ?
                new ResponseEntity<>(updatedUserProfile,HttpStatus.OK) :
                new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
    }
}
