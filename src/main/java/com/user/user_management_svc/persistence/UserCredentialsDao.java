package com.user.user_management_svc.persistence;

import com.user.user_management_svc.models.UserCredentials;
import org.springframework.stereotype.Repository;

@Repository
interface UserCredentialsDao
{
    String saveUserCredentials(UserCredentials userCredentials);
    void removeUserCredentials(String userID);
}
