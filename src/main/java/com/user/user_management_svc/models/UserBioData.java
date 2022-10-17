package com.user.user_management_svc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserBioData implements Serializable {
    @JsonProperty("name")
    private String Name;

    @JsonProperty("roll_no")
    private int Roll_Number;

    @JsonProperty("registration_no")
    private long Registration_Number;

    @JsonProperty("email")
    private String Email;
}
