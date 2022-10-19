package com.user.user_management_svc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateAFieldInUserProfile {
    @JsonProperty("new_value")
    Object newValue;
}
