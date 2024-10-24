package com.rapid.stock.service.v2;

import com.rapid.stock.dto.v2.ProductVersionSaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.mapper.v2.request.ProductVersionMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.ProductVersionMapperSaveResponse;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.ProductVersionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import javax.validation.Validation;
import javax.validation.Validator;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductVersionServiceTest {
    @Mock
    private ProductVersionMapperSaveRequest productVersionMapperSaveRequest;

    @Mock
    private ProductVersionMapperSaveResponse productVersionMapperSaveResponse;

    @Mock
    private ProductVersionRepository productVersionRepository;

    @Mock
    private StorageImageService storageImageService;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private ProductVersionService productVersionService;

    @BeforeEach
    public void setUpService(){
        productVersionService = new ProductVersionServiceImp(
                productVersionMapperSaveRequest,
                productVersionMapperSaveResponse,
                productVersionRepository,
                validator,
                storageImageService
        );
    }


    @Test
    public void can_save_product_version() {
        //Given
        ParentProduct parentProduct = new ParentProduct();
        parentProduct.setId(Long.valueOf(41535));
        parentProduct.setName("burgers");

        ProductVersion expectedProductVersion = new ProductVersion();
        expectedProductVersion.setId(Long.valueOf(41535));
        expectedProductVersion.setVersionId(UUID.randomUUID().toString());
        expectedProductVersion.setDescription("best burgers in the town!");
        expectedProductVersion.setName("burgers");
        expectedProductVersion.setPrice(Double.valueOf(5.6));
        expectedProductVersion.setAvailable(true);
        expectedProductVersion.setCreatedAt(LocalDateTime.now());
        expectedProductVersion.setParentProduct(parentProduct);
        expectedProductVersion.setFilename("burger.png");

        //Prepare mock
        ProductVersionSaveRequest productVersionSaveRequest = Mockito.mock(ProductVersionSaveRequest.class);
        when(productVersionMapperSaveRequest.mapToEntity(productVersionSaveRequest)).thenReturn(expectedProductVersion);

        //When
        productVersionService.save(productVersionSaveRequest, new MockMultipartFile("anyImg", "lorum".getBytes()));

        //Then
        ArgumentCaptor<ProductVersion> argumentCaptor = ArgumentCaptor.forClass(ProductVersion.class);
        verify(productVersionRepository).save(argumentCaptor.capture());
        ProductVersion savedProductVersion = argumentCaptor.getValue();

        assertThat(savedProductVersion).isEqualTo(expectedProductVersion);
    }

    @Test
    public void can_not_save_product_version_with_invalid_data() {
        //Given
        ProductVersion expectedProductVersion = new ProductVersion();
        expectedProductVersion.setId(Long.valueOf(0));
        expectedProductVersion.setVersionId(null);
        expectedProductVersion.setDescription(null);
        expectedProductVersion.setName("");
        expectedProductVersion.setPrice(Double.valueOf(-1));
        expectedProductVersion.setAvailable(true);
        expectedProductVersion.setCreatedAt(null);
        expectedProductVersion.setParentProduct(null);

        //Prepare mock
        ProductVersionSaveRequest productVersionSaveRequest = Mockito.mock(ProductVersionSaveRequest.class);
        when(productVersionMapperSaveRequest.mapToEntity(productVersionSaveRequest)).thenReturn(expectedProductVersion);

        //When
        InvalidDataFieldException exception = assertThrows(
                InvalidDataFieldException.class,
                () -> productVersionService.save(
                        productVersionSaveRequest,
                        new MockMultipartFile("anyImg", "lorum".getBytes())
                )
        );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid data or no data at all");
    }

}
