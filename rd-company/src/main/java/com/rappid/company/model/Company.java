package com.rappid.company.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document
@Data
public class Company {

       @NotBlank(message = "company name can't be blank")
       @Indexed(unique = true)
       private String name;

       @Id
       private String id;

       @NotBlank(message = "company commercial id can't be blank")
       @Indexed(unique = true)
       private String commercialId;

       @NotBlank(message = "company address can't be blank")
       private String address;

       @Valid
       @NotNull(message = "company contacts list can't be empty")
       private List<Contact> contacts;

       @Valid
       @NotNull(message = "company sites list can't be empty")
       private List<CompanySite> companySites;

       @DocumentReference
       @Valid
       @NotNull(message = "company category can't be empty")
       private Category category;

       @Builder
    public Company(String name, String commercialId, String address, List<Contact> contacts, Category category, List<CompanySite> companySites) {
        this.name = name;
        this.commercialId = commercialId;
        this.address = address;
        this.contacts = contacts;
        this.category = category;
        this.companySites = companySites;
    }
}
