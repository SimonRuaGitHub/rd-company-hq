package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.mapper.v2.ParentProductMapper;
import com.rapid.stock.model.v2.*;
import com.rapid.stock.repository.v2.ParentProductRepository;
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
import java.util.Arrays;
import java.util.List;

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
        expectedParentProduct.setCategories(null);
        expectedParentProduct.setAssociatedRacks(null);

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

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
        expectedParentProduct.setCategories(null);
        expectedParentProduct.setAssociatedRacks(List.of(rackAlpha, rackSigma));

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

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

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

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
        expectedParentProduct.setCategories(null);
        expectedParentProduct.setAssociatedRacks(null);

        ParentProductSaveRequest ppSaveRequest = Mockito.mock(ParentProductSaveRequest.class);

        when(parentProductMapper.mapSaveRequest(any(ParentProductSaveRequest.class))).thenReturn(expectedParentProduct);

        //When
        productService.save(ppSaveRequest);

        //Then
        ArgumentCaptor<ParentProduct> parProdArgumentCaptor = ArgumentCaptor.forClass(ParentProduct.class);
        verify(parentProductRepository).save(parProdArgumentCaptor.capture());

        ParentProduct capturedParentProduct = parProdArgumentCaptor.getValue();

        assertThat(capturedParentProduct).isEqualTo(expectedParentProduct);
    }

}
