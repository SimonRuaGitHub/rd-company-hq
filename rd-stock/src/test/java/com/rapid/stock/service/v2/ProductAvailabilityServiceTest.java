package com.rapid.stock.service.v2;

import com.rapid.stock.dto.AvailabilitySaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.mapper.v2.request.ProductAvailabilityMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.ProductAvailabilityMapperSaveResponse;
import com.rapid.stock.model.v2.Availability;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.ProductAvailabilityRepository;
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
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductAvailabilityServiceTest {

    @Mock
    private ProductAvailabilityMapperSaveRequest paMapperSaveRequest;

    @Mock
    private ProductAvailabilityMapperSaveResponse paMapperSaveResponse;

    @Mock
    private ProductAvailabilityRepository productAvailabilityRepository;

    private ProductAvailabilityService productAvailabilityService;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @BeforeEach
    public void setUpService() {
        productAvailabilityService = new ProductAvailabilityServiceImp(
                validator,
                paMapperSaveRequest,
                paMapperSaveResponse,
                productAvailabilityRepository
        );
    }

    @Test
    public void can_save_product_availability() {
        //Given
        ParentProduct parentProduct = new ParentProduct();
        parentProduct.setId(41535L);
        parentProduct.setName("burgers");

        ProductVersion productVersion = new ProductVersion();
        productVersion.setId(41535L);
        productVersion.setVersionId(UUID.randomUUID().toString());
        productVersion.setDescription("best burgers in the town!");
        productVersion.setName("burgers");
        productVersion.setPrice(5.6);
        productVersion.setAvailable(true);
        productVersion.setCreatedAt(LocalDateTime.now());
        productVersion.setParentProduct(parentProduct);

        Availability stubAvailability = Availability.builder()
                .id( UUID.randomUUID().toString() )
                .quantityAvailable(40)
                .companySiteID( UUID.randomUUID().toString() )
                .productVersion(productVersion)
                .createdAt(LocalDateTime.now())
                .build();

        //Prepare mock for mapper
        AvailabilitySaveRequest availabilitySaveRequest = Mockito.mock(AvailabilitySaveRequest.class);
        when(paMapperSaveRequest.mapToEntity(availabilitySaveRequest)).thenReturn(stubAvailability);

        //When
        productAvailabilityService.save(availabilitySaveRequest);

        //Then
        ArgumentCaptor<Availability> argumentCaptor = ArgumentCaptor.forClass(Availability.class);
        verify(productAvailabilityRepository).save(argumentCaptor.capture());
        Availability actualAvailability = argumentCaptor.getValue();

        assertThat(actualAvailability).isEqualTo(stubAvailability);
    }

    @Test
    public void can_not_save_product_availability_with_invalid_data() {
        //Given
        Availability stubAvailability = Availability.builder()
                .id("")
                .quantityAvailable(-1)
                .companySiteID("")
                .productVersion(null)
                .createdAt(null)
                .build();

        //Prepare mock
        AvailabilitySaveRequest availabilitySaveRequest = Mockito.mock(AvailabilitySaveRequest.class);
        when(paMapperSaveRequest.mapToEntity(availabilitySaveRequest)).thenReturn(stubAvailability);

        //When
        InvalidDataFieldException exception = assertThrows(
                InvalidDataFieldException.class,
                () -> productAvailabilityService.save(availabilitySaveRequest)
        );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid data or no data at all");
    }
}