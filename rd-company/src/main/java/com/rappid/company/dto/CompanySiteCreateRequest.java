package com.rappid.company.dto;

import lombok.Data;

@Data
public class CompanySiteCreateRequest {
    private String address;
    private String phone;
    private String siteOwnerName;
}
