package com.user.user_management_svc.persistence;

import com.user.user_management_svc.models.UserBioData;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDao
{
    void saveUserProfile(UserBioData userBioData, String userID);
    UserBioData getUserProfile(String userID);
    void updateFieldValueInUserProfile(String userID, String fieldNameToUpdate, Object newValue);
    void updateUserProfile(String userID, UserBioData newUserBiodata);
    void deleteUserProfile(String userID);
}
