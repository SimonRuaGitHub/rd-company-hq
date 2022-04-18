package com.rappid.company.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class Contact {

    @NotBlank(message = "contact phone can't be blank")
    private String phone;
    @NotBlank(message = "contact email can't be blank")
    private String email;
    @NotBlank(message = "contact name can't be blank")
    private String name;
    private boolean isMainContact;
}
