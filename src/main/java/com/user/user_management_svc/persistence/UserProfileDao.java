package com.user.user_management_svc.persistence;

import com.user.user_management_svc.models.UserBioData;
import org.springframework.stereotype.Repository;

@Repository
interface UserProfileDao
{
    String saveUserProfile(UserBioData userBioData, String userID);
    UserBioData getUserProfile(String userID);
    UserBioData updateFieldValueInUserProfile(String userID, String fieldNameToUpdate, Object newValue);
    UserBioData updateUserProfile(String userID, UserBioData newUserBiodata);
    boolean deleteUserProfile(String userID);
}
