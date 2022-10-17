package com.user.user_management_svc.persistence;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.user.user_management_svc.configurations.FirebaseConfiguration;
import com.user.user_management_svc.models.UserCredentials;
import org.springframework.stereotype.Repository;

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
        final CreateRequest newUserCreationRequest = new CreateRequest();
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
            System.out.println(">> [ UserCredentialsDaoImpl.saveUserCredentials() ] Successfully created new user: " + newUserRecord.getUid());
            return newUserRecord.getUid();
        }
        else
            System.out.println(">> [ UserCredentialsDaoImpl.saveUserCredentials() ] New user creation failed...!");
        return null;
    }

    @Override
    public void removeUserCredentials(String userID)
    {
        // Todo: remove the user credentials from firebase auth database.
        firebaseAuth.deleteUserAsync(userID).addListener(
                () -> System.out.println("User with id " + userID + " is deleted"),
                Runnable::run
        );
    }
}
