package com.user.user_management_svc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserCredentials implements Serializable {
    @JsonProperty("email")
    private String Email;

    @JsonProperty("passphrase")
    private String Password;
}
