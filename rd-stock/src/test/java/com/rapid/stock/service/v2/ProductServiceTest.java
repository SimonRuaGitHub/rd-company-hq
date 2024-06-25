package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.NotFoundException;
import com.rapid.stock.mapper.v2.request.ParentProductMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.ParentProductMapperSaveResponse;
import com.rapid.stock.model.v2.*;
import com.rapid.stock.repository.v2.ParentProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ParentProductMapperSaveRequest parentProductMapperSaveRequest;

    @Mock
    private ParentProductMapperSaveResponse parentProductMapperSaveResponse;

    @Mock
    private ParentProductRepository parentProductRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ProductServiceImp productService;

    @BeforeEach
    public void setUpService(){
        productService = new ProductServiceImp(parentProductRepository,
                parentProductMapperSaveRequest,
                parentProductMapperSaveResponse,
                validator
        );
    }

    @Test
    public void can_save_only_parent_product(){
        //Given
        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setId(Long.valueOf(235235));
        expectedParentProduct.setName("product_name");
        expectedParentProduct.setDescription("prod_description");
        expectedParentProduct.setCompanyId("929094");
        expectedParentProduct.setCreatedAt(LocalDateTime.now());
        expectedParentProduct.setProductVersions(null);
        expectedParentProduct.setProductTypes(null);
        expectedParentProduct.setAssociatedRacks(null);

        when(parentProductMapperSaveRequest.mapToEntity(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        productService.save(ppSaveRequest);

        //Then
        ArgumentCaptor<ParentProduct> parProdArgumentCaptor = ArgumentCaptor.forClass(ParentProduct.class);
        verify(parentProductRepository).save(parProdArgumentCaptor.capture());

        ParentProduct capturedParentProduct = parProdArgumentCaptor.getValue();

        assertThat(capturedParentProduct).isEqualTo(expectedParentProduct);
    }

    @Test
    public void can_save_parent_product_with_associated_racks(){
        //Given
        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);
        Rack rackAlpha = new Rack();
        rackAlpha.setId(Long.valueOf(235490));
        rackAlpha.setName("Rack Alpha");
        rackAlpha.setCompanyId("929094");
        rackAlpha.setParentRack(null);
        rackAlpha.setChildRacks(null);
        rackAlpha.setProducts(null);

        Rack rackSigma = new Rack();
        rackSigma.setId(Long.valueOf(235490));
        rackSigma.setName("Rack Sigma");
        rackSigma.setCompanyId("929094");
        rackSigma.setParentRack(null);
        rackSigma.setChildRacks(null);
        rackSigma.setProducts(null);

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setId(Long.valueOf(235235));
        expectedParentProduct.setName("product_name");
        expectedParentProduct.setDescription("prod_description");
        expectedParentProduct.setCompanyId("929094");
        expectedParentProduct.setCreatedAt(LocalDateTime.now());
        expectedParentProduct.setProductVersions(null);
        expectedParentProduct.setProductTypes(null);
        expectedParentProduct.setAssociatedRacks(List.of(rackAlpha, rackSigma));

        when(parentProductMapperSaveRequest.mapToEntity(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        productService.save(ppSaveRequest);

        //Then
        ArgumentCaptor<ParentProduct> parProdArgumentCaptor = ArgumentCaptor.forClass(ParentProduct.class);
        verify(parentProductRepository).save(parProdArgumentCaptor.capture());

        ParentProduct capturedParentProduct = parProdArgumentCaptor.getValue();

        assertThat(capturedParentProduct).isEqualTo(expectedParentProduct);
    }

    @Test
    public void cannot_create_main_product_with_product_versions_with_invalid_data_in_all_fields(){
        //Given
        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setName("");
        expectedParentProduct.setDescription("");
        expectedParentProduct.setCreatedAt(null);
        expectedParentProduct.setCompanyId(null);

        when(parentProductMapperSaveRequest.mapToEntity(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> productService.save(ppSaveRequest) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid data or no data at all");
    }

    @Test
    public void can_save_product_with_product_types() {
        //Given
        ProductType typeBurgers = new ProductType();
        typeBurgers.setId(Long.valueOf(62464));
        typeBurgers.setName("burgers");
        typeBurgers.setParentProducts(null);

        ProductType typeLateNight = new ProductType();
        typeLateNight.setId(Long.valueOf(62464));
        typeLateNight.setName("late nite");
        typeLateNight.setParentProducts(null);

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setId(Long.valueOf(235235));
        expectedParentProduct.setName("product_name");
        expectedParentProduct.setDescription("prod_description");
        expectedParentProduct.setCompanyId("929094");
        expectedParentProduct.setCreatedAt(LocalDateTime.now());
        expectedParentProduct.setProductVersions(null);
        expectedParentProduct.setProductTypes(List.of(typeBurgers, typeLateNight));
        expectedParentProduct.setAssociatedRacks(null);

        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        when(parentProductMapperSaveRequest.mapToEntity(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        productService.save(ppSaveRequest);

        //Then
        ArgumentCaptor<ParentProduct> parProdArgumentCaptor = ArgumentCaptor.forClass(ParentProduct.class);
        verify(parentProductRepository).save(parProdArgumentCaptor.capture());

        ParentProduct capturedParentProduct = parProdArgumentCaptor.getValue();

        assertThat(capturedParentProduct).isEqualTo(expectedParentProduct);
    }

    @Test
    public void can_save_product_with_option_categories() {
        //Given
        OptionCategory optCategorySalsas = new OptionCategory();
        optCategorySalsas.setName("Salsas");
        optCategorySalsas.setDescrip("Seleccion de salsas para el producto");
        optCategorySalsas.setLabel("Selecciona tu salsas");
        optCategorySalsas.setCompanyId("929094");

        OptionCategory optCategoryGaseosas = new OptionCategory();
        optCategoryGaseosas.setName("Gaseosas");
        optCategoryGaseosas.setDescrip("Seleccion de gaseosas para el producto");
        optCategoryGaseosas.setLabel("Selecciona tu gaseosa");
        optCategoryGaseosas.setCompanyId("929094");

        ParentProduct expectedParentProduct = new ParentProduct();
        expectedParentProduct.setId(Long.valueOf(235235));
        expectedParentProduct.setName("product_name");
        expectedParentProduct.setDescription("prod_description");
        expectedParentProduct.setCompanyId("929094");
        expectedParentProduct.setCreatedAt(LocalDateTime.now());
        expectedParentProduct.setProductVersions(null);
        expectedParentProduct.setOptionCategories(List.of(optCategorySalsas, optCategoryGaseosas));
        expectedParentProduct.setAssociatedRacks(null);
        expectedParentProduct.setProductTypes(null);

        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        when(parentProductMapperSaveRequest.mapToEntity(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        productService.save(ppSaveRequest);

        //Then
        ArgumentCaptor<ParentProduct> parProdArgumentCaptor = ArgumentCaptor.forClass(ParentProduct.class);
        verify(parentProductRepository).save(parProdArgumentCaptor.capture());

        ParentProduct capturedParentProduct = parProdArgumentCaptor.getValue();

        assertThat(capturedParentProduct).isEqualTo(expectedParentProduct);
    }

    @Test
    public void can_delete_parent_product() {
        //Given
        OptionCategory optCategoryGaseosas = new OptionCategory();
        optCategoryGaseosas.setName("Gaseosas");
        optCategoryGaseosas.setDescrip("Seleccion de gaseosas para el producto");
        optCategoryGaseosas.setLabel("Selecciona tu gaseosa");
        optCategoryGaseosas.setCompanyId("929094");

        ProductType typeLateNight = new ProductType();
        typeLateNight.setId(Long.valueOf(62464));
        typeLateNight.setName("late nite");
        typeLateNight.setParentProducts(null);

        Rack rackAlpha = new Rack();
        rackAlpha.setId(Long.valueOf(235490));
        rackAlpha.setName("Rack Alpha");
        rackAlpha.setCompanyId("929094");
        rackAlpha.setParentRack(null);
        rackAlpha.setChildRacks(null);
        rackAlpha.setProducts(null);

        ProductVersion productVersion = new ProductVersion();
        productVersion.setId(1L);
        productVersion.setName("prod_version_alpha");
        productVersion.setCreatedAt(LocalDateTime.now());
        productVersion.setFilename("/prod_version_alpha.png");
        productVersion.setPrice(2455.4D);
        productVersion.setVersionId(UUID.randomUUID().toString());

        ParentProduct parentProduct = new ParentProduct();
        long productId = 2;
        parentProduct.setId(productId);
        parentProduct.setName("product_name");
        parentProduct.setDescription("prod_description");
        parentProduct.setCompanyId("929094");
        parentProduct.setCreatedAt(LocalDateTime.now());
        parentProduct.setProductVersions(null);
        parentProduct.setOptionCategories(List.of(optCategoryGaseosas));
        parentProduct.setAssociatedRacks(List.of(rackAlpha));
        parentProduct.setProductTypes(List.of(typeLateNight));

        when(parentProductRepository.findById(productId)).thenReturn(Optional.of(parentProduct));

        //When
        productService.delete(productId);

        //Then
        verify(parentProductRepository, times(1)).delete(parentProduct);
    }

    @Test
    public void cannot_delete_unexisting_product() {
        //Given
        ParentProduct parentProduct = new ParentProduct();
        long productId = 2;
        parentProduct.setId(productId);
        parentProduct.setName("product_name");
        parentProduct.setDescription("prod_description");
        parentProduct.setCompanyId("929094");
        parentProduct.setCreatedAt(LocalDateTime.now());
        parentProduct.setProductVersions(null);

        when(parentProductRepository.findById(productId)).thenReturn(Optional.empty());

        //When
        NotFoundException notFoundException = assertThrows(
                NotFoundException.class, () -> productService.delete(productId)
        );

        //Then
        Assertions.assertThat(
                notFoundException.getMessage()
        ).isEqualTo("Parent product not found with ID: " + productId);
    }
}
