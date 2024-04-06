package com.rapid.stock.model.v2;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCT_AVAILABILITIES")
@Getter
@Setter
@NoArgsConstructor
@Profile("rational-db")
public class Availability {

       @Id
       private String id;

       @NotBlank(message = "companySiteID can't be empty or null")
       @Column(name = "company_site_id")
       private String companySiteID;

       @Min(value=0, message = "quantityAvailable can't be less than 0")
       @NotNull(message = "quantityAvailable can't be null")
       private Integer quantityAvailable;

       @NotNull(message = "createdAt can't be null")
       private LocalDateTime createdAt;

       @ManyToOne
       @JoinColumn(name = "product_versions_id", referencedColumnName = "id")
       @JsonBackReference
       private ProductVersion productVersion;

       @Builder
       public Availability(String id,
                           String companySiteID,
                           Integer quantityAvailable,
                           LocalDateTime createdAt,
                           ProductVersion productVersion) {
         this.id = id;
         this.companySiteID = companySiteID;
         this.quantityAvailable = quantityAvailable;
         this.createdAt = createdAt;
         this.productVersion = productVersion;
      }
}
