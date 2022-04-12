package com.rappid.company.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CompanySite {
    @NotBlank(message = "uuid can't be empty")
    private String uuid;
    @NotBlank(message = "address can't be empty")
    private String address;
    @NotBlank(message = "phone can't be empty")
    private String phone;
    @NotBlank(message = "site owner name can't be empty")
    private String siteOwnerName;
}