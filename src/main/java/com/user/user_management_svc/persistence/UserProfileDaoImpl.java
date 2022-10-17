package com.user.user_management_svc.persistence;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.user.user_management_svc.models.UserBioData;
import org.springframework.stereotype.Repository;

@Repository
class UserProfileDaoImpl implements UserProfileDao
{
    private final String COLLECTION_NAME = "USER_PROFILES";
    private final CollectionReference firestoreCollectionReference
            = FirestoreClient.getFirestore().collection(COLLECTION_NAME);
    @Override
    public void saveUserProfile(UserBioData userBioData, String userID) {
        try{
            firestoreCollectionReference
                    .document(userID)
                    .set(userBioData, SetOptions.merge());
            System.out.println(">> [ UserProfileDaoImpl.saveUserProfile ] user profile saved...");
        }catch(Exception e){
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public UserBioData getUserProfile(String userID) {
        try{
            return firestoreCollectionReference
                    .document(userID)
                    .get()
                    .get()
                    .toObject(UserBioData.class);
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
        }
        return null;
    }

    @Override
    public void updateFieldValueInUserProfile(String userID, String fieldNameToUpdate, Object newValue) {

    }

    @Override
    public void updateUserProfile(String userID, UserBioData newUserBiodata) {

    }

    @Override
    public void deleteUserProfile(String userID) {

    }
}
