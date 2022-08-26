package com.rapid.stock.model.v2;

import lombok.*;
import org.springframework.context.annotation.Profile;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCT_AVAILABILITIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Profile("rational-db")
public class Availability {

       @Id
       @GeneratedValue
       private Long id;

       @NotBlank(message = "companySiteID can't be empty or null")
       private String companySiteID;

       @Min(value=0, message = "quantityAvailable can't be less than 0")
       @NotNull(message = "quantityAvailable can't be null")
       private Integer quantityAvailable;

       @NotNull(message = "createdAt can't be null")
       private LocalDateTime createdAt;

       @Builder
       public Availability(String companySiteID, Integer quantityAvailable, LocalDateTime createdAt) {
         this.companySiteID = companySiteID;
         this.quantityAvailable = quantityAvailable;
         this.createdAt = createdAt;
      }
}
