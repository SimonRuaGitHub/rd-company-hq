package com.rapid.stock.model.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Profile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Profile("decrapted")
public class Option {

    @Min(value = 1, message = "Option id can't be less or equal to 0")
    private int id;

    @NotEmpty(message = "Option name can't be blank")
    private String name;

    @Min(value = 0, message = "Option price can't be less than 0")
    @NotNull
    private Double price;

    @Valid
    private List<Availability> optionAvalabilities;
}
