package com.rapid.stock.service.v2;

import com.rapid.stock.dto.OptionCategoryDTO;
import com.rapid.stock.dto.ProductTypeDTO;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.mapper.v2.OptionCategoryMapper;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.ProductType;
import com.rapid.stock.repository.v2.OptionCategoryRepository;
import net.bytebuddy.asm.Advice;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OptionCategoryServiceTest {

    @Mock
    private OptionCategoryMapper optionCategoryMapper;

    @Mock
    private OptionCategoryRepository optionCategoryRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private OptionCategoryService optionCategoryService;

    @BeforeEach
    public void setUpService(){
        optionCategoryService = new OptionCategoryServiceImp(optionCategoryMapper, validator, optionCategoryRepository);
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
        when(optionCategoryMapper.mapToOptionCategory(optionCategoryDTO)).thenReturn(expectedOptionCategory);

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
        OptionCategory expectedOptionCategory = new OptionCategory();
        expectedOptionCategory.setName("");
        expectedOptionCategory.setDescrip(null);
        expectedOptionCategory.setCompanyId("");
        expectedOptionCategory.setLabel(null);

        //prepare mock for mapper
        OptionCategoryDTO optionCategoryDTO = Mockito.mock(OptionCategoryDTO.class);
        when(optionCategoryMapper.mapToOptionCategory(optionCategoryDTO)).thenReturn(expectedOptionCategory);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> optionCategoryService.save(optionCategoryDTO) );

        //Then
        assertThat(exception.getMessage()).contains("Some of the fields have invalid data or no data at all");
    }
}
