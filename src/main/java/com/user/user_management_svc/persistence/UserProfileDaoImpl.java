package com.user.user_management_svc.persistence;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.user.user_management_svc.models.UserBioData;
import org.springframework.stereotype.Repository;

// Todo : remove sout and use logs
@Repository
class UserProfileDaoImpl implements UserProfileDao
{
    private final String COLLECTION_NAME = "USER_PROFILES";
    private final CollectionReference firestoreCollectionReference
            = FirestoreClient.getFirestore().collection(COLLECTION_NAME);
    @Override
    public String saveUserProfile(UserBioData userBioData, String userID) {
        DocumentReference newDocRef = null;
        try{

            if(userID == null)
                newDocRef = firestoreCollectionReference.document();
            else
                newDocRef = firestoreCollectionReference.document(userID);

            newDocRef.set(userBioData, SetOptions.merge());
            System.out.println(">> [ UserProfileDaoImpl.saveUserProfile ] user profile saved...");
        }catch(Exception e){
            System.out.println("e = " + e.getMessage());
        }

        return newDocRef!=null ? newDocRef.getId() : "";
    }

    @Override
    public UserBioData getUserProfile(String userID) {
        try{
            DocumentSnapshot documentSnapshot = firestoreCollectionReference
                    .document(userID)
                    .get()
                    .get();
            if(documentSnapshot.exists())
                    return documentSnapshot.toObject(UserBioData.class);
        }catch(Exception exception){
            System.out.println(">> [ UserProfileDaoImpl.getUserProfile ] " + exception.getMessage());
            throw  new RuntimeException(exception.getMessage());
        }
        return null;
    }

    @Override
    public UserBioData updateFieldValueInUserProfile(String userID, String fieldNameToUpdate, Object newValue) {
        if(isUserExisting(userID))
        {
            try {
                firestoreCollectionReference
                        .document(userID)
                        .update(fieldNameToUpdate,
                                newValue
                        ).get();
            } catch (Exception exception) {
                System.out.println(">> [ UserProfileDaoImpl.updateFieldValueInUserProfile ] " + exception.getMessage());
                throw new RuntimeException(exception.getMessage());
            }
            return getUserProfile(userID);
        }
        else
            System.out.println(
                    ">> [ UserProfileDaoImpl.updateFieldValueInUserProfile ] User with userID " +
                            userID +
                            " is does not exist."
            );
        return null;
    }

    @Override
    public UserBioData updateUserProfile(String userID, UserBioData newUserBiodata) {
        if(isUserExisting(userID))
        {
            try{
                firestoreCollectionReference
                        .document(userID)
                        .set(newUserBiodata,
                                SetOptions.merge()
                        );
            }catch (Exception exception){
                System.out.println(">> [ UserProfileDaoImpl.updateUserProfile ] " + exception.getMessage());
                throw new RuntimeException(exception.getMessage());
            }

            System.out.println(
                    ">> [ UserProfileDaoImpl.updateUserProfile ] User with userID " +
                            userID +
                            " is updated."
            );
            return getUserProfile(userID);
        }
        else
            System.out.println(
                    ">> [ UserProfileDaoImpl.updateUserProfile ] User with userID " +
                            userID +
                            " does not exists."
            );

        return null;
    }

    @Override
    public boolean deleteUserProfile(String userID) {
        try{
            if(isUserExisting(userID))
            {
                System.out.println("[ UserProfileDaoImpl.deleteUserProfile ] Document with userID " +
                        userID +
                        " does exist."
                );
                firestoreCollectionReference.document(userID).delete();
            }
            else
            {
                System.out.println("[ UserProfileDaoImpl.deleteUserProfile ] Document with userID " +
                        userID +
                        " does not exist."
                );
                return false;
            }
        }catch (Exception e){
            System.out.println("[ UserProfileDaoImpl.deleteUserProfile ] " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return !isUserExisting(userID);
    }

    private boolean isUserExisting(String userID)
    {
        boolean isUserPresentInDB;
        try {
            isUserPresentInDB = firestoreCollectionReference.document(userID).get().get().exists();
        }catch(Exception exception){
            System.out.println("[ UserProfileDaoImpl.isUserExisting ] " + exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
        return isUserPresentInDB;
    }
}
