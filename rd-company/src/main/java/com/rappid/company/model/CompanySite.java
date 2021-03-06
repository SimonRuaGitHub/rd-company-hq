package com.rappid.company.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CompanySite {
    @NotBlank(message = "site uuid can't be empty")
    private String uuid;
    @NotBlank(message = "site address can't be empty")
    @Indexed(unique = true)
    private String address;
    @NotBlank(message = "site phone can't be empty")
    private String phone;
    @NotBlank(message = "site owner name can't be empty")
    private String siteOwnerName;
}
