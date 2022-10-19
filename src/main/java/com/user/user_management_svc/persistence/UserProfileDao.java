package com.user.user_management_svc.persistence;

import com.user.user_management_svc.models.UserBioData;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDao
{
    String saveUserProfile(UserBioData userBioData, String userID);
    UserBioData getUserProfile(String userID);
    boolean updateFieldValueInUserProfile(String userID, String fieldNameToUpdate, Object newValue);
    boolean updateUserProfile(String userID, UserBioData newUserBiodata);
    boolean deleteUserProfile(String userID);
}
