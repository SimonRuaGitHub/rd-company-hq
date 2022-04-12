package com.rappid.company.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class ContactCreateRequest {

    private String phone;
    private String email;
    private String name;
    @Getter(AccessLevel.NONE)
    private boolean isMainContact;

    @JsonProperty("isMainContact")
    public boolean isMainContact(){
        return isMainContact;
    }
}
