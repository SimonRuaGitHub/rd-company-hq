package com.rapid.stock.service.v2;

import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.mapper.v2.request.OptionCategoryMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.OptionCategoryMapperSaveResponse;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.repository.v2.OptionCategoryRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//TODO: Fix test cases
public class OptionCategoryServiceTest {

    @Mock
    private OptionCategoryMapperSaveRequest optionCategoryMapperSaveRequest;

    private final OptionCategoryMapperSaveResponse optionCategoryMapperSaveResponse =
            new OptionCategoryMapperSaveResponse();

    @Mock
    private OptionCategoryRepository optionCategoryRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private OptionCategoryService optionCategoryService;

    @BeforeEach
    public void setUpService(){
        optionCategoryService = new OptionCategoryServiceImp(
                optionCategoryMapperSaveRequest,
                optionCategoryMapperSaveResponse,
                validator,
                optionCategoryRepository
        );
    }

    @Test
    public void can_save_option_category(){
        //Given
        OptionCategory expectedOptionCategory = new OptionCategory();
        expectedOptionCategory.setName("Drinks");
        expectedOptionCategory.setDescrip("Drinks selection for users");
        expectedOptionCategory.setCompanyId("9235444");
        expectedOptionCategory.setLabel("Select your drinks");

        //prepare mock for mapper
        OptionCategoryDTO optionCategoryDTO = Mockito.mock(OptionCategoryDTO.class);
        when(optionCategoryMapperSaveRequest.mapToEntity(optionCategoryDTO)).thenReturn(expectedOptionCategory);

        //When
        optionCategoryService.save(optionCategoryDTO);

        //Then
        ArgumentCaptor<OptionCategory> optionCategoryArgumentCaptor = ArgumentCaptor.forClass(OptionCategory.class);
        verify(optionCategoryRepository).save(optionCategoryArgumentCaptor.capture());

        OptionCategory capturedOptionCategory = optionCategoryArgumentCaptor.getValue();

        assertThat(capturedOptionCategory).isEqualTo(expectedOptionCategory);
    }

    @Test
    public void cannot_save_option_category_with_invalid_data(){
        //Given
        OptionCategory optionCategory = new OptionCategory();
        optionCategory.setName("");
        optionCategory.setDescrip(null);
        optionCategory.setCompanyId("");
        optionCategory.setLabel(null);

        //prepare mock for mapper
        OptionCategoryDTO optionCategoryDTO = Mockito.mock(OptionCategoryDTO.class);
        when(optionCategoryMapperSaveRequest.mapToEntity(optionCategoryDTO)).thenReturn(optionCategory);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> optionCategoryService.save(optionCategoryDTO) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid data or no data at all");
    }

    @Test
    public void can_save_option_category_with_products(){
        //Given
        ParentProduct parentProductOne = new ParentProduct();
        parentProductOne.setId(Long.valueOf(235235));
        parentProductOne.setName("product_name1");
        parentProductOne.setDescription("prod_description");
        parentProductOne.setCompanyId("929094");
        parentProductOne.setCreatedAt(LocalDateTime.now());
        parentProductOne.setProductVersions(null);
        parentProductOne.setOptionCategories(null);
        parentProductOne.setAssociatedRacks(null);
        parentProductOne.setProductTypes(null);

        ParentProduct parentProductTwo = new ParentProduct();
        parentProductTwo.setId(Long.valueOf(235236));
        parentProductTwo.setName("product_name2");
        parentProductTwo.setDescription("prod_description");
        parentProductTwo.setCompanyId("929094");
        parentProductTwo.setCreatedAt(LocalDateTime.now());
        parentProductTwo.setProductVersions(null);
        parentProductTwo.setOptionCategories(null);
        parentProductTwo.setAssociatedRacks(null);
        parentProductTwo.setProductTypes(null);

        OptionCategory expectedOptionCategory = new OptionCategory();
        expectedOptionCategory.setName("Drinks");
        expectedOptionCategory.setDescrip("Drinks selection for users");
        expectedOptionCategory.setCompanyId("929094");
        expectedOptionCategory.setLabel("Select your drinks");
        expectedOptionCategory.setParentProducts(List.of(parentProductOne, parentProductTwo));

        //prepare mock for mapper
        OptionCategoryDTO optionCategoryDTO = Mockito.mock(OptionCategoryDTO.class);
        when(optionCategoryMapperSaveRequest.mapToEntity(optionCategoryDTO)).thenReturn(expectedOptionCategory);

        //When
        optionCategoryService.save(optionCategoryDTO);

        //Then
        ArgumentCaptor<OptionCategory> optionCategoryArgumentCaptor = ArgumentCaptor.forClass(OptionCategory.class);
        verify(optionCategoryRepository).save(optionCategoryArgumentCaptor.capture());

        OptionCategory capturedOptionCategory = optionCategoryArgumentCaptor.getValue();

        assertThat(capturedOptionCategory).isEqualTo(expectedOptionCategory);
    }
}
