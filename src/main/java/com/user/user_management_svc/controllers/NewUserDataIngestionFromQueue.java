package com.user.user_management_svc.controllers;

import com.user.user_management_svc.models.User;
import com.user.user_management_svc.models.UserBioData;
import com.user.user_management_svc.models.UserCredentials;
import com.user.user_management_svc.persistence.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewUserDataIngestionFromQueue
{
    private final UserRepository userRepository;

    @RabbitListener(queues = "SAVE_VERIFIED_USERS_QUEUE")
    public void getUsersFromQueueAndSave(User newVerifiedUser)
    {
        System.out.println("newVerifiedUser = " + newVerifiedUser);

        UserBioData userBioDataOfNewUser = newVerifiedUser.getUserBioData();
        UserCredentials userCredentialsOfNewUser = newVerifiedUser.getUserCredentials();

        String newUsersID = userRepository.saveUserCredentials(userCredentialsOfNewUser);
        if(newUsersID!=null)
            userRepository.saveUserProfile(userBioDataOfNewUser,newUsersID);
    }
}
