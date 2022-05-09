package com.rapid.stock.service;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.mapper.RackMapperSaveRequest;
import com.rapid.stock.model.ParentProduct;
import com.rapid.stock.model.Rack;
import com.rapid.stock.repository.RackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RackServiceTest {

    @Mock
    private RackRepository rackRepository;

    @Mock
    private RackMapperSaveRequest rackMapper;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Mock
    private MongoTemplate mongoTemplate;

    private RackService rackService;

    @BeforeEach
    public void setUpServiceService() {
        this.rackService = new RackServiceImp(rackRepository, rackMapper, validator, mongoTemplate);
    }

    @Test
    public void can_create_rack_without_products_and_subracks(){
         //Given
         RackDto rackDto = Mockito.mock(RackDto.class);

         Rack rack = Rack.builder()
                         .companyId("02353")
                         .name("Beers")
                         .description("Duff beers rack")
                         .racks(null)
                         .products(null)
                         .build();

         when(rackMapper.mapRackSaveRequest(any(RackDto.class))).thenReturn(rack);

         rack.setId("anyID");
         when(rackRepository.insert(any(Rack.class))).thenReturn(rack);

         //When
         rackService.save(rackDto);

         //Then
         verify(rackRepository).insert(rack);
    }

    @Test
    public void can_create_parent_rack(){
        //Given
        RackDto rackDto = Mockito.mock(RackDto.class);

        Rack childRackA = Rack.builder()
                            .companyId("02356")
                            .name("German Beers")
                            .description(null)
                            .racks(null)
                            .products(null)
                            .build();

        Rack childRackB = Rack.builder()
                                .companyId("02356")
                                .name("Belgium Beers")
                                .description(null)
                                .racks(null)
                                .products(null)
                                .build();

        Rack parentRack = Rack.builder()
                .companyId("02353")
                .name("International Beers")
                .description("Rack for beers around the world")
                .racks(Arrays.asList(childRackA, childRackB))
                .products(null)
                .build();

        when(rackMapper.mapRackSaveRequest(any(RackDto.class))).thenReturn(parentRack);

        parentRack.setId("anyID");
        when(rackRepository.insert(any(Rack.class))).thenReturn(parentRack);

        //When
        rackService.save(rackDto);

        //Then
        verify(rackRepository).insert(parentRack);
    }

}
