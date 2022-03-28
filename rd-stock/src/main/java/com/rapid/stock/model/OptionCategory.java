package com.rapid.stock.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class OptionCategory {
    @Id
    private String id;
    @NotBlank(message = "Option category name can't  be blank")
    private String name;
    @Field(name = "description")
    @NotBlank(message = "Option category description can't  be blank")
    private String descrip;
    @NotBlank(message = "Option category label can't  be blank")
    private String label;

    @Valid
    @NotEmpty(message = "List of options must not be empty")
    private List<Option> options;

    @Builder
    public OptionCategory(String name, String descrip, String label , List<Option> options) {
        this.name = name;
        this.descrip = descrip;
        this.label = label;
        this.options = options;
    }
}
