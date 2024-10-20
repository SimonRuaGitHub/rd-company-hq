package com.rapid.stock.service.v2;

import com.rapid.stock.dto.RackSaveRequest;
import com.rapid.stock.dto.RackSaveResponse;
import com.rapid.stock.mapper.v2.request.RackMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.RackMapperSaveResponse;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.Rack;
import com.rapid.stock.repository.v2.RackRepository;
import com.rapid.stock.service.RackService;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RackServiceTest {

    @Mock
    private RackRepository rackRepository;

    @Mock
    private RackMapperSaveRequest rackMapperSaveRequest;

    private final RackMapperSaveResponse rackSaveMapperSaveResponse = new RackMapperSaveResponse();

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private RackService rackService;

    @BeforeEach
    public void setUpServiceService() {
        this.rackService = new RackServiceImp(
                rackRepository,
                rackMapperSaveRequest,
                rackSaveMapperSaveResponse,
                validator
        );
    }

    @Test
    public void can_create_rack_with_associated_products(){
        //Given
        RackSaveRequest rackSaveRequest = Mockito.mock(RackSaveRequest.class);
        ParentProduct productAlpha = new ParentProduct();
        productAlpha.setId(Long.valueOf(235235));
        productAlpha.setName("product_name alpha");
        productAlpha.setDescription("prod_description");
        productAlpha.setCompanyId("929094");
        productAlpha.setCreatedAt(LocalDateTime.now());
        productAlpha.setProductVersions(null);
        productAlpha.setProductTypes(null);
        productAlpha.setAssociatedRacks(null);

        ParentProduct productBeta = new ParentProduct();
        productBeta.setId(Long.valueOf(235236));
        productBeta.setName("product_name beta");
        productBeta.setDescription("prod_description");
        productBeta.setCompanyId("929094");
        productBeta.setCreatedAt(LocalDateTime.now());
        productBeta.setProductVersions(null);
        productBeta.setProductTypes(null);
        productBeta.setAssociatedRacks(null);

        Rack expectedPreviousToSaveRack = Rack.builder()
                .companyId("02353")
                .name("Beers")
                .description("Duff beers rack")
                .childRacks(null)
                .products(List.of(productAlpha, productBeta))
                .build();

        Rack expectedSavedRack = Rack
                .builder()
                .companyId("02353")
                .name("Beers")
                .description("Duff beers rack")
                .childRacks(null)
                .products(List.of(productAlpha, productBeta))
                .build();
        expectedSavedRack.setId(123L);

        RackSaveResponse expectedRackSaveResponse = RackSaveResponse
                .builder()
                .id(expectedSavedRack.getId())
                .name(expectedSavedRack.getName())
                .companyId(expectedSavedRack.getCompanyId())
                .build();

        when(rackMapperSaveRequest.mapToEntity(any(RackSaveRequest.class))).thenReturn(expectedPreviousToSaveRack);
        when(rackRepository.save(any(Rack.class))).thenReturn(expectedSavedRack);

        //When
        RackSaveResponse actualRackSaveResponse = rackService.save(rackSaveRequest);

        //Then
        //Rack to save is equal to the Rack mapped by save request mapper
        ArgumentCaptor<Rack> rackArgumentCaptor = ArgumentCaptor.forClass(Rack.class);
        verify(rackRepository).save(rackArgumentCaptor.capture());
        Rack capturedRack = rackArgumentCaptor.getValue();
        assertThat(capturedRack).isEqualTo(expectedPreviousToSaveRack);

        //Rack save response is the expected one
        assertThat(actualRackSaveResponse).isEqualTo(expectedRackSaveResponse);
    }
}
