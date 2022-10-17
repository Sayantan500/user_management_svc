package com.user.user_management_svc.persistence;

import com.user.user_management_svc.models.UserBioData;
import com.user.user_management_svc.models.UserCredentials;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository implements UserCredentialsDao, UserProfileDao
{
    private final UserCredentialsDao userCredentialsDao;
    private final UserProfileDao userProfileDao;
    @Override
    public String saveUserCredentials(UserCredentials userCredentials) {
        return userCredentialsDao.saveUserCredentials(userCredentials);
    }

    @Override
    public void removeUserCredentials(String userID) {
        userCredentialsDao.removeUserCredentials(userID);
    }

    @Override
    public void saveUserProfile(UserBioData userBioData, String userID) {
        userProfileDao.saveUserProfile(userBioData, userID);
    }

    @Override
    public UserBioData getUserProfile(String userID) {
        return userProfileDao.getUserProfile(userID);
    }

    @Override
    public void updateFieldValueInUserProfile(String userID, String fieldNameToUpdate, Object newValue) {
        userProfileDao.updateFieldValueInUserProfile(userID,fieldNameToUpdate,newValue);
    }

    @Override
    public void updateUserProfile(String userID, UserBioData newUserBiodata) {
        userProfileDao.updateUserProfile(userID,newUserBiodata);
    }

    @Override
    public void deleteUserProfile(String userID) {
        userProfileDao.deleteUserProfile(userID);
    }
}
