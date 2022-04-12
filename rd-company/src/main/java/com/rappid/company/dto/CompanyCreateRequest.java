package com.rappid.company.dto;

import lombok.Data;
import java.util.List;

@Data
public class CompanyCreateRequest {
    private String name;
    private String commercialId ;
    private String address;
    private List<ContactCreateRequest> contactsCreateRequest;
    private List<CompanySiteCreateRequest> sitesCreateRequest;
    private String categoryId;
}
