package com.rappid.company.mapper;

import com.rappid.company.dto.CompanyCreateRequest;
import com.rappid.company.dto.CompanySiteCreateRequest;
import com.rappid.company.dto.ContactCreateRequest;
import com.rappid.company.model.Category;
import com.rappid.company.model.Company;
import com.rappid.company.model.CompanySite;
import com.rappid.company.model.Contact;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CompanyMapper {

    private final MongoTemplate mongoTemplate;

    public Company mapToCompany(CompanyCreateRequest companySaveRequest) {
           return Company.builder()
                         .name(companySaveRequest.getName())
                         .commercialId(companySaveRequest.getCommercialId())
                         .address(companySaveRequest.getAddress())
                         .contacts( mapToContactsList( companySaveRequest.getContactsCreateRequest() ) )
                         .category( getCategory(companySaveRequest.getCategoryId()) )
                         .companySites( mapToSitesList( companySaveRequest.getSitesCreateRequest() ) )
                         .build();
    }

    public List<Contact> mapToContactsList(List<ContactCreateRequest> contactsCreateRequest){
           return contactsCreateRequest.stream().map(contactDto -> Contact.builder().name(contactDto.getName())
                                                                                    .email(contactDto.getEmail())
                                                                                    .phone(contactDto.getPhone())
                                                                                    .isMainContact(contactDto.isMainContact()).build())
                                                .collect(Collectors.toList());
    }

    public List<CompanySite> mapToSitesList(List<CompanySiteCreateRequest> companySiteCreateRequests){
           return companySiteCreateRequests.stream().map(companySiteDto -> CompanySite.builder()
                                                                                      .uuid(UUID.randomUUID().toString())
                                                                                      .address(companySiteDto.getAddress())
                                                                                      .phone(companySiteDto.getPhone())
                                                                                      .siteOwnerName(companySiteDto.getSiteOwnerName()).build())
                                                    .collect(Collectors.toList());

    }

    public Category getCategory(String categoryId){
           return mongoTemplate.findById(categoryId, Category.class);
    }
}
