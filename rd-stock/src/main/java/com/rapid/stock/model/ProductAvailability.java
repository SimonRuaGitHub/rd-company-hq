package com.rapid.stock.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ProductAvailability {

       @Id
       private String id;

       @Indexed(unique = true, sparse = true)
       @NotBlank(message = "companySiteID can't be empty or null")
       private String companySiteID;

       @Min(value=0, message = "price can't be less than 0")
       @NotNull(message = "quantityAvailable can't be null")
       private Integer quantityAvailable;

       @NotNull(message = "createdAt can't be null")
       private LocalDateTime createdAt;

       @Builder
       public ProductAvailability(String companySiteID, Integer quantityAvailable, LocalDateTime createdAt) {
         this.companySiteID = companySiteID;
         this.quantityAvailable = quantityAvailable;
         this.createdAt = createdAt;
      }
}
