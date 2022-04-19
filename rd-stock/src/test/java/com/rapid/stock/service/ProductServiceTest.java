package com.rapid.stock.service;

import com.rapid.stock.dto.ParentProductSaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.mapper.ParentProductMapper;
import com.rapid.stock.model.*;
import com.rapid.stock.repository.ParentProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ParentProductMapper parentProductMapper;

    @Mock
    private ParentProductRepository parentProductRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ProductServiceImp productService;

    @BeforeEach
    public void setUpService(){
        productService = new ProductServiceImp(parentProductRepository, parentProductMapper, validator);
    }

    @Test
    public void can_create_a_main_product_with_product_version_without_product_options(){
            //Given
            ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

            ProductVersion productVersion = new ProductVersion();
            productVersion.setVersionId("anyselftgeneratedstringUUID");
            productVersion.setName("prod_version_name");
            productVersion.setDescription("prod_version_description");
            productVersion.setAvailable(true);
            productVersion.setProductType(ProductType.MENU_RESTAURANT);
            productVersion.setCreatedAt(LocalDateTime.now());
            productVersion.setPrice(Double.valueOf(0));
            productVersion.setProductAvailabilities( Arrays.asList( Availability.builder()
                                                                    .createdAt(LocalDateTime.now())
                                                                    .quantityAvailable(Integer.valueOf(0))
                                                                    .companySiteID("anyID").build() ) );

            ParentProduct expectedParentProduct = new ParentProduct();
            expectedParentProduct.setProductId("23523352");
            expectedParentProduct.setProductName("product_name");
            expectedParentProduct.setProductDescription("prod_description");
            expectedParentProduct.setCreatedAt(LocalDateTime.now());
            expectedParentProduct.setProductVersions(Arrays.asList(productVersion));

            when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

            //When
            productService.save(ppSaveRequest);

            //Then
            ArgumentCaptor<ParentProduct> parProdArgumentCaptor = ArgumentCaptor.forClass(ParentProduct.class);
            verify(parentProductRepository).insert(parProdArgumentCaptor.capture());

            ParentProduct capturedParentProduct = parProdArgumentCaptor.getValue();

            assertThat(capturedParentProduct).isEqualTo(expectedParentProduct);
    }

    @Test
    public void can_create_a_main_product_with_product_version_without_product_availability(){
        //Given
        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        ProductVersion productVersion = new ProductVersion();
        productVersion.setVersionId("anyselftgeneratedstringUUID");
        productVersion.setName("prod_version_name");
        productVersion.setDescription("prod_version_description");
        productVersion.setAvailable(true);
        productVersion.setProductType(ProductType.MENU_RESTAURANT);
        productVersion.setCreatedAt(LocalDateTime.now());
        productVersion.setPrice(Double.valueOf(0));
        productVersion.setProductAvailabilities( null );

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setProductId("23523352");
        expectedParentProduct.setProductName("product_name");
        expectedParentProduct.setProductDescription("prod_description");
        expectedParentProduct.setCreatedAt(LocalDateTime.now());
        expectedParentProduct.setProductVersions(Arrays.asList(productVersion));

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        productService.save(ppSaveRequest);

        //Then
        ArgumentCaptor<ParentProduct> parProdArgumentCaptor = ArgumentCaptor.forClass(ParentProduct.class);
        verify(parentProductRepository).insert(parProdArgumentCaptor.capture());

        ParentProduct capturedParentProduct = parProdArgumentCaptor.getValue();

        assertThat(capturedParentProduct).isEqualTo(expectedParentProduct);
    }

    @Test
    public void can_create_a_main_product_without_product_versions(){
        //Given
        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setProductId("23523352");
        expectedParentProduct.setProductName("product_name");
        expectedParentProduct.setProductDescription("prod_description");
        expectedParentProduct.setCreatedAt(LocalDateTime.now());
        expectedParentProduct.setProductVersions(null);

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        productService.save(ppSaveRequest);

        //Then
        ArgumentCaptor<ParentProduct> parProdArgumentCaptor = ArgumentCaptor.forClass(ParentProduct.class);
        verify(parentProductRepository).insert(parProdArgumentCaptor.capture());

        ParentProduct capturedParentProduct = parProdArgumentCaptor.getValue();

        assertThat(capturedParentProduct).isEqualTo(expectedParentProduct);
    }

    @Test
    public void cannot_create_only_main_product_with_invalid_data_in_all_fields(){
        //Given
        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setProductId(null);
        expectedParentProduct.setProductName("");
        expectedParentProduct.setProductDescription("");
        expectedParentProduct.setCreatedAt(null);
        expectedParentProduct.setProductVersions(null);

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> productService.save(ppSaveRequest) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid have invalid data or no data at all");
        assertFalse(exception.getViolations().isEmpty());
    }

    @Test
    public void cannot_create_only_main_product_with_invalid_data_in_one_field(){
        //Given
        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setProductId(null);
        expectedParentProduct.setProductName("product_name");
        expectedParentProduct.setProductDescription("prod_description");
        expectedParentProduct.setCreatedAt(LocalDateTime.now());
        expectedParentProduct.setProductVersions(null);

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> productService.save(ppSaveRequest) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid have invalid data or no data at all");
        assertFalse(exception.getViolations().isEmpty());
    }

    @Test
    public void cannot_create_main_product_with_product_versions_with_invalid_data_in_all_fields(){
        //Given
        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);
        int expectedFields = 22;

        ProductVersion productVersionA = new ProductVersion();
        productVersionA.setVersionId("");
        productVersionA.setName("");
        productVersionA.setDescription(null);
        productVersionA.setAvailable(true);
        productVersionA.setProductType(null);
        productVersionA.setCreatedAt(null);
        productVersionA.setPrice(Double.valueOf(-1));
        productVersionA.setProductAvailabilities( Arrays.asList( Availability.builder()
                                                                        .createdAt(null)
                                                                        .quantityAvailable(-1)
                                                                        .companySiteID(null).build() ) );

        ProductVersion productVersionB = new ProductVersion();
        productVersionB.setVersionId("");
        productVersionB.setName("");
        productVersionB.setDescription(null);
        productVersionB.setAvailable(true);
        productVersionB.setProductType(null);
        productVersionB.setCreatedAt(null);
        productVersionB.setPrice(Double.valueOf(-1));
        productVersionB.setProductAvailabilities( Arrays.asList( Availability.builder()
                                                                        .createdAt(null)
                                                                        .quantityAvailable(-1)
                                                                        .companySiteID(null).build() ) );

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setProductId(null);
        expectedParentProduct.setProductName("");
        expectedParentProduct.setProductDescription("");
        expectedParentProduct.setCreatedAt(null);
        expectedParentProduct.setProductVersions(Arrays.asList(productVersionA,productVersionB));

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> productService.save(ppSaveRequest) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid have invalid data or no data at all");
        assertEquals(exception.getViolations().size(), expectedFields, "Quantity of actual invalid fields is not equal to expected one");
    }

    @Test
    public void cannot_create_main_product_with_product_versions_with_invalid_data_in_product_type(){
        //Given
        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        ProductVersion productVersion = new ProductVersion();
        productVersion.setVersionId("anyselftgeneratedstringUUID");
        productVersion.setName("prod_version_name");
        productVersion.setDescription("prod_version_description");
        productVersion.setAvailable(true);
        productVersion.setProductType(null);
        productVersion.setCreatedAt(LocalDateTime.now());
        productVersion.setPrice(Double.valueOf(0));
        productVersion.setProductAvailabilities( Arrays.asList( Availability.builder()
                                                                    .createdAt(LocalDateTime.now())
                                                                    .quantityAvailable(Integer.valueOf(0))
                                                                    .companySiteID("anyID").build() ) );

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setProductId("23523352");
        expectedParentProduct.setProductName("product_name");
        expectedParentProduct.setProductDescription("prod_description");
        expectedParentProduct.setCreatedAt(LocalDateTime.now());
        expectedParentProduct.setProductVersions(Arrays.asList(productVersion));

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> productService.save(ppSaveRequest) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid have invalid data or no data at all");
        assertFalse(exception.getViolations().isEmpty(),"It should appear some violations");
    }

    @Test
    public void can_create_a_main_product_with_product_version_and_options(){
        //Given
        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        ProductVersion productVersion = new ProductVersion();
        productVersion.setVersionId("anyselfgeneratedstringUUID");
        productVersion.setName("prod_version_name");
        productVersion.setDescription("prod_version_description");
        productVersion.setAvailable(true);
        productVersion.setProductType(ProductType.MENU_RESTAURANT);
        productVersion.setCreatedAt(LocalDateTime.now());
        productVersion.setPrice(Double.valueOf(0));
        productVersion.setProductAvailabilities( Arrays.asList( Availability.builder()
                                                                    .createdAt(LocalDateTime.now())
                                                                    .quantityAvailable(Integer.valueOf(0))
                                                                    .companySiteID("anyID").build() ) );

        productVersion.setOptionCategories( Arrays.asList(Mockito.mock(OptionCategory.class), Mockito.mock(OptionCategory.class)) );

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setProductId("23523352");
        expectedParentProduct.setProductName("product_name");
        expectedParentProduct.setProductDescription("prod_description");
        expectedParentProduct.setCreatedAt(LocalDateTime.now());
        expectedParentProduct.setProductVersions(Arrays.asList(productVersion));

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        productService.save(ppSaveRequest);

        //Then
        ArgumentCaptor<ParentProduct> parProdArgumentCaptor = ArgumentCaptor.forClass(ParentProduct.class);
        verify(parentProductRepository).insert(parProdArgumentCaptor.capture());

        ParentProduct capturedParentProduct = parProdArgumentCaptor.getValue();

        assertThat(capturedParentProduct).isEqualTo(expectedParentProduct);
    }
}
