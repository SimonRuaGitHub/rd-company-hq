package com.rappid.company.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
@Data
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    @NotBlank(message = "category name can't be empty")
    @Indexed(unique = true)
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
