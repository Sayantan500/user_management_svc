package com.user.user_management_svc.persistence;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.user.user_management_svc.configurations.FirebaseConfiguration;
import com.user.user_management_svc.models.UserCredentials;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
class UserCredentialsDaoImpl implements UserCredentialsDao
{
    private final FirebaseAuth firebaseAuth;

    public UserCredentialsDaoImpl(){
        FirebaseConfiguration.initFirebase();
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public String saveUserCredentials(UserCredentials userCredentials)
    {
        CreateRequest newUserCreationRequest = new CreateRequest();
        Map<String,Object> customClaims = new HashMap<>();

        newUserCreationRequest
                .setEmail(userCredentials.getEmail())
                .setPassword(userCredentials.getPassword())
                .setEmailVerified(true);

        UserRecord newUserRecord = null;
        try{
            newUserRecord = firebaseAuth.createUser(newUserCreationRequest);
        }catch(FirebaseAuthException firebaseAuthException){
            System.out.println(">> [ UserCredentialsDaoImpl.saveUserCredentials() ] " + firebaseAuthException);
        }

        if( newUserRecord!=null)
        {
            try {
                customClaims.put("role","student");
                firebaseAuth.createCustomToken(newUserRecord.getUid(),customClaims);
            } catch (FirebaseAuthException e) {
                customClaims = null;
                newUserCreationRequest = null;
                throw new RuntimeException(e);
            }

            System.out.println(
                    ">> [ UserCredentialsDaoImpl.saveUserCredentials() ] Successfully created new user: " +
                            newUserRecord.getUid()
            );

            customClaims = null;
            newUserCreationRequest = null;

            return newUserRecord.getUid();
        }
        else
            System.out.println(">> [ UserCredentialsDaoImpl.saveUserCredentials() ] New user creation failed...!");

        customClaims = null;
        newUserCreationRequest = null;
        return null;
    }

    @Override
    public void removeUserCredentials(String userID)
    {
        firebaseAuth.deleteUserAsync(userID).addListener(
                () -> System.out.println("User with id " + userID + " is deleted"),
                Runnable::run
        );
    }
}
