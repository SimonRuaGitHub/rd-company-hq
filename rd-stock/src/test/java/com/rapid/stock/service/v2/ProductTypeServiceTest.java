package com.rapid.stock.service.v2;

import com.rapid.stock.dto.ProductTypeDTO;
import com.rapid.stock.dto.v2.ParentProductSaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.mapper.v2.ParentProductMapper;
import com.rapid.stock.mapper.v2.ProductTypeMapper;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.repository.v2.ParentProductRepository;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTypeServiceTest {
    @Mock
    private ProductTypeMapper productTypeMapper;

    @Mock
    private ProductTypeRepository productTypeRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ProductTypeService productTypeService;

    @BeforeEach
    public void setUpService(){
        productTypeService = new ProductTypeServiceImp(productTypeMapper, productTypeRepository, validator);
    }

    @Test
    public void can_save_product_type(){
        //Given
        ProductType expectedProductType = new ProductType();
        expectedProductType.setId(Long.valueOf(41535));
        expectedProductType.setName("burgers");

        //Prepare mock
        ProductTypeDTO productTypeDTO = Mockito.mock(ProductTypeDTO.class);
        when(productTypeMapper.mapProductType(productTypeDTO)).thenReturn(expectedProductType);

        //When
        productTypeService.save(productTypeDTO);

        //Then
        ArgumentCaptor<ProductType> prodTypeArgumentCaptor = ArgumentCaptor.forClass(ProductType.class);
        verify(productTypeRepository).save(prodTypeArgumentCaptor.capture());

        ProductType capturedProdType = prodTypeArgumentCaptor.getValue();

        assertThat(capturedProdType).isEqualTo(expectedProductType);
    }

    @Test
    public void cannot_save_product_type_with_blank_name(){
        //Given
        ProductTypeDTO productTypeDTO = Mockito.mock(ProductTypeDTO.class);

        ProductType expectedProductType = new ProductType();
        expectedProductType.setId(Long.valueOf(41535));
        expectedProductType.setName(" ");

        when(productTypeMapper.mapProductType(productTypeDTO)).thenReturn(expectedProductType);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> productTypeService.save(productTypeDTO) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid data or no data at all");
    }

    @Test
    public void cannot_save_product_type_with_numeric_name(){
        //Given
        ProductTypeDTO productTypeDTO = Mockito.mock(ProductTypeDTO.class);

        ProductType expectedProductType = new ProductType();
        expectedProductType.setId(Long.valueOf(41535));
        expectedProductType.setName("235264");

        when(productTypeMapper.mapProductType(productTypeDTO)).thenReturn(expectedProductType);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> productTypeService.save(productTypeDTO) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid data or no data at all");
    }
}
