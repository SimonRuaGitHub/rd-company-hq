package com.rapid.stock.service.v2;

import com.rapid.stock.dto.ProductTypeSaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.mapper.v2.request.ProductTypeMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.ProductTypeMapperSaveResponse;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.repository.v2.ProductTypeRepository;
import com.rapid.stock.service.ProductTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTypeServiceTest {
    @Mock
    private ProductTypeMapperSaveRequest productTypeMapperSaveRequest;

    @Mock
    private ProductTypeMapperSaveResponse productTypeMapperSaveResponse;

    @Mock
    private ProductTypeRepository productTypeRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ProductTypeService productTypeService;

    @BeforeEach
    public void setUpService(){
        productTypeService = new ProductTypeServiceImp(
                productTypeMapperSaveRequest,
                productTypeMapperSaveResponse,
                productTypeRepository,
                validator
        );
    }

    @Test
    public void can_save_product_type(){
        //Given
        ProductType expectedProductType = new ProductType();
        expectedProductType.setId(Long.valueOf(41535));
        expectedProductType.setName("burgers");

        //Prepare mock
        ProductTypeSaveRequest productTypeSaveRequest = Mockito.mock(ProductTypeSaveRequest.class);
        when(productTypeMapperSaveRequest.mapToEntity(productTypeSaveRequest)).thenReturn(expectedProductType);

        //When
        productTypeService.save(productTypeSaveRequest);

        //Then
        ArgumentCaptor<ProductType> prodTypeArgumentCaptor = ArgumentCaptor.forClass(ProductType.class);
        verify(productTypeRepository).save(prodTypeArgumentCaptor.capture());

        ProductType capturedProdType = prodTypeArgumentCaptor.getValue();

        assertThat(capturedProdType).isEqualTo(expectedProductType);
    }

    @Test
    public void cannot_save_product_type_with_blank_name(){
        //Given
        ProductTypeSaveRequest productTypeSaveRequest = Mockito.mock(ProductTypeSaveRequest.class);

        ProductType expectedProductType = new ProductType();
        expectedProductType.setId(Long.valueOf(41535));
        expectedProductType.setName(" ");

        when(productTypeMapperSaveRequest.mapToEntity(productTypeSaveRequest)).thenReturn(expectedProductType);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> productTypeService.save(productTypeSaveRequest) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid data or no data at all");
    }

    @Test
    public void cannot_save_product_type_with_numeric_name(){
        //Given
        ProductTypeSaveRequest productTypeSaveRequest = Mockito.mock(ProductTypeSaveRequest.class);

        ProductType expectedProductType = new ProductType();
        expectedProductType.setId(Long.valueOf(41535));
        expectedProductType.setName("235264");

        when(productTypeMapperSaveRequest.mapToEntity(productTypeSaveRequest)).thenReturn(expectedProductType);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> productTypeService.save(productTypeSaveRequest) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid data or no data at all");
    }

    @Test
    public void can_return_all_product_type_names(){
        //Given
        ProductType productTypeA = new ProductType();
        productTypeA.setId(Long.valueOf(1));
        productTypeA.setName("sushi");

        ProductType productTypeB = new ProductType();
        productTypeB.setId(Long.valueOf(2));
        productTypeB.setName("burgers");

        List<ProductType> productTypes = List.of(productTypeA, productTypeB);

        List<String> expectedProductTypeNames = List.of("sushi", "burgers");

        //Prepare mock
        when(productTypeRepository.findAll()).thenReturn(productTypes);

        //When
        List<String> actualProductTypeNames = productTypeService.getAllProductTypeNames();

        //Then
        assertThat(actualProductTypeNames).isEqualTo(expectedProductTypeNames);
    }
}
