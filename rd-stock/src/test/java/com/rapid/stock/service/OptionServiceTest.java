package com.rapid.stock.service;

import com.rapid.stock.dto.OptionCategorySaveRequest;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.mapper.OptionMapper;
import com.rapid.stock.model.Option;
import com.rapid.stock.model.OptionCategory;
import com.rapid.stock.repository.OptionCategoryRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class OptionServiceTest {
    @Mock
    private OptionMapper optionMapper;

    @Mock
    private OptionCategoryRepository optionCategoryRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private OptionServiceImp optionServiceImp;

    @BeforeEach
    public void setUp(){
         optionServiceImp = new OptionServiceImp(optionCategoryRepository, optionMapper, validator);
    }

    @Test
    public void can_create_options_category_with_options(){

            //Given
           OptionCategory expectedOptionCategory = OptionCategory.builder().name("drinks")
                                                                    .descrip("Selection of drinks")
                                                                    .label("Elige tu bebida")
                                                                    .options( Arrays.asList(Option.builder()
                                                                                                   .id(1)
                                                                                                   .name("Coca cola")
                                                                                                   .price(Double.valueOf(0)).build(),
                                                                                              Option.builder()
                                                                                                        .id(2)
                                                                                                        .name("Mr tea")
                                                                                                        .price(Double.valueOf(3000)).build()
                                                                                                       )
                                                                    ).build();

            when(optionMapper.mapCategorySaveRequest(any(OptionCategorySaveRequest.class))).thenReturn(expectedOptionCategory);

            //When
            optionServiceImp.save(Mockito.mock(OptionCategorySaveRequest.class));

            //Then
            ArgumentCaptor<OptionCategory> argumentCaptor = ArgumentCaptor.forClass(OptionCategory.class);
            verify(optionCategoryRepository).insert(argumentCaptor.capture());

            assertThat(argumentCaptor.getValue()).isEqualTo(expectedOptionCategory);
    }

    @Test
    public void cannot_create_option_category_without_options(){
            //Given
            OptionCategory optionCategory = OptionCategory.builder().name("drinks")
                                                                            .descrip("Selection of drinks")
                                                                            .label("Elige tu bebida")
                                                                            .options(null).build();

            when(optionMapper.mapCategorySaveRequest(any(OptionCategorySaveRequest.class))).thenReturn(optionCategory);

            //When
            Throwable exception = assertThrows(InvalidDataFieldException.class, () -> optionServiceImp.save(Mockito.mock(OptionCategorySaveRequest.class)));

            //Thens
            assertThat(exception.getMessage()).contains("Some of the fields have invalid have invalid data or no data at all");
    }

    @Test
    public void cannot_create_option_category_with_errors_in_some_fields(){
        //Given
        OptionCategory optionCategory = OptionCategory.builder().name("drinks")
                .descrip("")
                .label("Elige tu bebida")
                .options( Arrays.asList(Option.builder()
                                        .id(1)
                                        .name("Coca cola")
                                        .price(Double.valueOf(0)).build(),
                                Option.builder()
                                        .id(2)
                                        .name("Mr tea")
                                        .price(Double.valueOf(-1)).build()
                        )
                ).build();

        int qtyFieldsWithErrors = 2;

        when(optionMapper.mapCategorySaveRequest(any(OptionCategorySaveRequest.class))).thenReturn(optionCategory);

        //When
        InvalidDataFieldException exception = assertThrows(InvalidDataFieldException.class, () -> optionServiceImp.save(Mockito.mock(OptionCategorySaveRequest.class)));

        //Thens
        assertThat(exception.getMessage()).contains("Some of the fields have invalid have invalid data or no data at all");
        assertEquals(exception.getViolations().size(), qtyFieldsWithErrors,"Actual fields with error quantity is not same as expected one");
    }
}
