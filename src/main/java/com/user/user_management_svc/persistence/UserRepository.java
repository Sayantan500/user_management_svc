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
    public String saveUserProfile(UserBioData userBioData, String userID) {
        return userProfileDao.saveUserProfile(userBioData, userID);
    }

    @Override
    public UserBioData getUserProfile(String userID) {
        return userProfileDao.getUserProfile(userID);
    }

    @Override
    public boolean updateFieldValueInUserProfile(String userID, String fieldNameToUpdate, Object newValue) {
        return userProfileDao
                .updateFieldValueInUserProfile(
                        userID,
                        fieldNameToUpdate,
                        newValue
                );
    }

    @Override
    public boolean updateUserProfile(String userID, UserBioData newUserBiodata) {
        return userProfileDao
                .updateUserProfile(
                        userID,
                        newUserBiodata
                );
    }

    @Override
    public boolean deleteUserProfile(String userID) {
        return userProfileDao.deleteUserProfile(userID);
    }
}
