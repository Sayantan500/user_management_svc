package com.user.user_management_svc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    @JsonProperty("biodata")
    private UserBioData userBioData;

    @JsonProperty("credentials")
    private UserCredentials userCredentials;

    public UserBioData getUserBioData(){
        return new UserBioData(
                userBioData.getName(),
                userBioData.getRoll_Number(),
                userBioData.getRegistration_Number(),
                userBioData.getEmail()
        );
    }

    public UserCredentials getUserCredentials(){
        return new UserCredentials(
                userCredentials.getEmail(),
                userCredentials.getPassword()
        );
    }
    public String getEmail(){
        return userBioData.getEmail();
    }

    public String getUserName(){
        return userBioData.getName();
    }
}
