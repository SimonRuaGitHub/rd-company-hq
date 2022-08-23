package com.rapid.stock.model.v2;

import jdk.jfr.Enabled;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "OPTION_CATEGORIES")
@Getter
@Setter
@AllArgsConstructor
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
    private List options;

    @Builder
    public OptionCategory(String name, String descrip, String label , List options) {
        this.name = name;
        this.descrip = descrip;
        this.label = label;
        this.options = options;
    }
}
