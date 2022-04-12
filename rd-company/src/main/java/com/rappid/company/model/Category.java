package com.rappid.company.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;


@Document
@Data
public class Category {

    @Id
    private String id;

    @NotBlank(message = "category name can't be empty")
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
