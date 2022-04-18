package com.rappid.company;

import com.rappid.company.dto.CompanyCreateRequest;
import com.rappid.company.exception.InvalidDataFieldException;
import com.rappid.company.exception.SaveException;
import com.rappid.company.mapper.CompanyMapper;
import com.rappid.company.model.Category;
import com.rappid.company.model.Company;
import com.rappid.company.model.CompanySite;
import com.rappid.company.model.Contact;
import com.rappid.company.repository.CompanyRepository;
import com.rappid.company.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    private final Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();

    private CompanyService companyService;

    @BeforeEach
    public void setUpService(){
          companyService = new CompanyService(companyRepository, companyMapper, validator);
    }

    @Test
    public void can_create_company_successfully(){
           //Given
           CompanyCreateRequest companyDto = Mockito.mock(CompanyCreateRequest.class);

           Company company = Company.builder()
                                    .name("Springfield Nuclear Power Plant")
                                    .commercialId("2364246-sP")
                                    .address("Sector 7-G")
                                    .contacts(Arrays.asList(Contact.builder().name("Homer Simpson")
                                            .email("homer.simpson@nuclearplantspringfield.com")
                                            .phone("193513534")
                                            .isMainContact(true).build()))
                                    .category( new Category("anyId","NUCLEAR PLANT") )
                                    .companySites( Arrays.asList(CompanySite.builder()
                                            .uuid(UUID.randomUUID().toString())
                                            .address("Sector 7-G Springfield")
                                            .phone("2354464")
                                            .siteOwnerName("Mongomery Burns").build()) )
                                    .build();

           when( companyMapper.mapToCompany(any(CompanyCreateRequest.class)) ).thenReturn(company);

           //When
           companyService.save(companyDto);

           //Then
           ArgumentCaptor<Company> companyArgCap = ArgumentCaptor.forClass(Company.class);
           verify(companyRepository).insert(companyArgCap.capture());

           Company companyCapturedValue = companyArgCap.getValue();

           assertThat(companyCapturedValue).isEqualTo(company);
    }

    @Test
    public void cannot_create_company_with_invalid_data_all_fields(){
        //Given
        int expectedViolations = 10;
        CompanyCreateRequest companyDto = Mockito.mock(CompanyCreateRequest.class);

        Company company = Company.builder()
                .name("")
                .commercialId(null)
                .address("")
                .contacts(Arrays.asList(Contact.builder().name("")
                        .email("")
                        .phone(null)
                        .isMainContact(true).build()))
                .category( null )
                .companySites( Arrays.asList(CompanySite.builder()
                        .uuid(UUID.randomUUID().toString())
                        .address("")
                        .phone(null)
                        .siteOwnerName("").build()) )
                .build();

        when( companyMapper.mapToCompany(any(CompanyCreateRequest.class)) ).thenReturn(company);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> companyService.save(companyDto));

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid have invalid data or no data at all");
        assertThat(exception.getViolations().size()).isEqualTo(expectedViolations);
    }

    @Test
    public void cannot_create_company_without_company_sites_and_contacts(){
        //Given
        int expectedViolations = 2;
        CompanyCreateRequest companyDto = Mockito.mock(CompanyCreateRequest.class);

        Company company = Company.builder()
                .name("Springfield Nuclear Power Plant")
                .commercialId("2364246-sP")
                .address("Sector 7-G")
                .contacts(null)
                .category( new Category("anyId","NUCLEAR PLANT") )
                .companySites( null )
                .build();

        when( companyMapper.mapToCompany(any(CompanyCreateRequest.class)) ).thenReturn(company);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> companyService.save(companyDto));

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid have invalid data or no data at all");
        assertThat(exception.getViolations().size()).isEqualTo(expectedViolations);
    }

    @Test
    public void cannot_create_company_without_category(){
        //Given
        int expectedViolations = 1;
        CompanyCreateRequest companyDto = Mockito.mock(CompanyCreateRequest.class);

        Company company = Company.builder()
                .name("Springfield Nuclear Power Plant")
                .commercialId("2364246-sP")
                .address("Sector 7-G")
                .contacts(Arrays.asList(Contact.builder().name("Homer Simpson")
                        .email("homer.simpson@nuclearplantspringfield.com")
                        .phone("193513534")
                        .isMainContact(true).build()))
                .category( null )
                .companySites( Arrays.asList(CompanySite.builder()
                        .uuid(UUID.randomUUID().toString())
                        .address("Sector 7-G Springfield")
                        .phone("2354464")
                        .siteOwnerName("Mongomery Burns").build()) )
                .build();

        when( companyMapper.mapToCompany(any(CompanyCreateRequest.class)) ).thenReturn(company);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> companyService.save(companyDto));

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid have invalid data or no data at all");
        assertThat(exception.getViolations().size()).isEqualTo(expectedViolations);
    }


    @Test
    public void cannot_create_company_due_to_dup_values(){
        //Given
        CompanyCreateRequest companyDto = Mockito.mock(CompanyCreateRequest.class);
        Company company = Company.builder()
                .name("Springfield Nuclear Power Plant")
                .commercialId("2364246-sP")
                .address("Sector 7-G")
                .contacts(Arrays.asList(Contact.builder().name("Homer Simpson")
                        .email("homer.simpson@nuclearplantspringfield.com")
                        .phone("193513534")
                        .isMainContact(true).build()))
                .category( new Category("anyId","NUCLEAR PLANT") )
                .companySites( Arrays.asList(CompanySite.builder()
                        .uuid(UUID.randomUUID().toString())
                        .address("Sector 7-G Springfield")
                        .phone("2354464")
                        .siteOwnerName("Mongomery Burns").build()) )
                .build();

        when( companyMapper.mapToCompany(any(CompanyCreateRequest.class)) ).thenReturn(company);
        when( companyRepository.insert(any(Company.class)) ).thenThrow(DuplicateKeyException.class);

        //When
        SaveException exception = assertThrows(SaveException.class, () -> companyService.save(companyDto));

        //Then
        assertThat(exception.getMessage()).contains("Failed to save following company object: ");
    }
}
